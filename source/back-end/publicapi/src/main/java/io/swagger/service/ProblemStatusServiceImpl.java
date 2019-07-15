package io.swagger.service;

import io.swagger.model.ProblemIdList;
import io.swagger.model.StatusInfo;
import io.swagger.pojo.dao.Problem;
import io.swagger.pojo.dao.TikuUser;
import io.swagger.pojo.dao.UserProblemStatus;
import io.swagger.pojo.dao.repos.ProblemRepository;
import io.swagger.pojo.dao.repos.TikuUserRepository;
import io.swagger.pojo.dao.repos.UserProblemStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class ProblemStatusServiceImpl implements ProblemStatusService {

    /**
     * 参数不正确
     */
    public class ProblemStatusArgumentException extends Exception{

        public ProblemStatusArgumentException(String message) {
            super(message);
        }
    }
    @Autowired
    private UserProblemStatusRepository userProblemStatusRepository;
    @Autowired
    private TikuUserRepository tikuUserRepository;

    @Autowired
    private ProblemRepository problemRepository;
    /**更新题目状态信息
     * @param statusInfo
     */
    @Override
    @Transient
    public void updateProblemStatus(StatusInfo statusInfo) throws ProblemStatusArgumentException {
        //参数校验
        @NotNull String problemId = statusInfo.getProblemId();
        Long aLong = Long.valueOf(problemId);
        Optional<Problem> byId = problemRepository.findById(aLong);
        if(!byId.isPresent()){
           throw new ProblemStatusArgumentException("问题id["+problemId+"]不存在");
        }

        Long date = statusInfo.getDate();
        if(date == null){
            date= new Date().getTime();
        }

        //剩下两个不会为空的

        @NotNull String unionid = statusInfo.getUnionid();
        Optional<TikuUser> byUserUuidEquals = tikuUserRepository.findByUserUuidEquals(unionid);
        if(!byUserUuidEquals.isPresent()){
            //todo 注册
            log.warn("用户的uuid：{}不存在",unionid);
            //throw  new ProblemStatusArgumentException("用户uuid"+unionid+"不存在");
            TikuUser tikuUser = new TikuUser();
            tikuUser.setUserUuid(unionid);
            tikuUser.setGrade("一年级");
            tikuUserRepository.save(tikuUser);
        }

        @NotNull String status = statusInfo.getStatus();

        UserProblemStatus userProblemStatus = new UserProblemStatus();
        userProblemStatus.setStatus(status);
        userProblemStatus.setUserUuid(unionid);
        userProblemStatus.setProblemId(aLong);
        userProblemStatus.setDate(new Date(date));
        userProblemStatusRepository.save(userProblemStatus);
    }

    @Override
    public List<UserProblemStatus> getProblemStatus(ProblemIdList problemIdList) throws ProblemStatusArgumentException {
        //可能有多种组合形式，jpa不支持那么复杂的，只能手动了
        // todo 手写sql
        List<UserProblemStatus> res=new ArrayList<>();
        log.debug("开始查找");
        // 问题id
        List<String> ids = problemIdList.getIds();
        if(ids!=null && ids.size()>0){
            log.debug("开始按问题ids:{}查找",ids);
            List<Long> collect = ids.stream().map(Long::valueOf).collect(toList());
            List<UserProblemStatus> allByProblemIdIn = userProblemStatusRepository.findAllByProblemIdIn(collect);
            if(allByProblemIdIn ==null || allByProblemIdIn.size()==0){
                log.debug("没找到满足{}的数据",collect);
                return new ArrayList<>();
            }

            res = allByProblemIdIn.stream().peek(res::add).collect(toList());
            log.debug("按问题查找结束，结果大小{}",res.size());
            if(res.size()==0){
                return res;
            }
        }

        List<String> unionids = problemIdList.getUnionids();
        if(unionids!=null && unionids.size()>0){
            log.debug("开始按用户id{}查询",unionids);
            List<UserProblemStatus> allByUserUuidIn = userProblemStatusRepository.findAllByUserUuidIn(unionids);
            if(allByUserUuidIn == null || allByUserUuidIn.size()==0 ){
                log.debug("没找到满足{}的数据",unionids);
                return  new ArrayList<>();
            }
            log.debug("查到{}个数据",allByUserUuidIn.size());
            if(res.size()>0){
                log.debug("开始合并");
                res = merge(res, allByUserUuidIn);
                log.debug("合并后有{}个数据",res.size());
            }else{
                res = allByUserUuidIn.stream().peek(res::add).collect(toList());
            }
            log.debug("按用户id查询结束，结果大小{}",res.size());
            if(res.size()==0){
                return res;
            }
        }


        List<String> status = problemIdList.getStatus();
        if(status!=null && status.size()>0) {
            log.debug("开始按状态{}查找",status);
            if(res.size()==0){
                throw  new  ProblemStatusArgumentException("不能单指定状态，必须指定用户或者问题");
            }

            res = res.stream().filter((u) -> {
                for (String s : status) {
                    if (u.getStatus().contains(s)) {
                        return true;
                    }
                }
                return false;
            }).collect(toList());
            log.debug("遍历结果{}",res.size());
            if(res.size()==0){
                return res;
            }
        }

        @Valid BigDecimal startTime = problemIdList.getStartTime();
        @Valid BigDecimal endTIme = problemIdList.getEndTime();
        log.debug("时间{}，{}",startTime,endTIme);

        if(startTime!=null){
            if(endTIme==null){
                throw new ProblemStatusArgumentException("开始时间和结束时间必须同时存在");
            }
            long s = startTime.longValueExact();
            long e =endTIme.longValueExact();
            log.debug("开始按时间查询");
            Date date1 = new Date(s);
            log.debug(date1.toGMTString());
            res = res.stream().filter((u) -> {
                Date date = u.getDate();
                if(date==null){
                    return false;
                }
                long time = u.getDate().getTime();
                log.debug("time:{}",time);
                return (time >= s && time < e);
            }).collect(toList());
            log.debug("过滤结果大小{}",res.size());
            if(res.size()==0){
                return res;
            }
        }
        // todo 标签
        return res;
    }

    public List<UserProblemStatus> merge(List<UserProblemStatus> a,List<UserProblemStatus> b){
        return  a.stream().filter((u)->{
            List<UserProblemStatus> collect = b.stream().filter((uu) -> uu.getId().equals(u.getId())).collect(toList());
            return collect.size()>0;
        }).collect(toList());
    }
}
