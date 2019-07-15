package io.swagger.service;

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

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;
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
}
