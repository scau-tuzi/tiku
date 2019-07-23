package io.swagger.service;

import io.swagger.model.UserId;
import io.swagger.model.UserInfo;
import io.swagger.pojo.dao.ProblemTag;
import io.swagger.pojo.dao.Tag;
import io.swagger.pojo.dao.TikuUser;
import io.swagger.pojo.dao.UserProblemStatus;
import io.swagger.pojo.dao.repos.ProblemTagRepository;
import io.swagger.pojo.dao.repos.TagRepository;
import io.swagger.pojo.dao.repos.TikuUserRepository;
import io.swagger.pojo.dao.repos.UserProblemStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    public class UserInfoServiceException extends Exception {
        public UserInfoServiceException(String message) {
            super(message);
        }
    }

    @Autowired
    private TikuUserRepository tikuUserRepository;

    @Autowired
    private UserProblemStatusRepository userProblemStatusRepository;
    @Autowired
    private ProblemTagRepository problemTagRepository;
    @Autowired
    private TagRepository tagRepository;

    @Override
    public void addUserInfo(UserInfo userInfo) throws UserInfoServiceException {
        @NotNull String unionid = userInfo.getUnionid();
        List<TikuUser> list = tikuUserRepository.findAllByUserUuidEquals(unionid);
        if (list.size()>=1) {
            log.warn("用户{}已存在", unionid);
            //todo 应该使用put更新
            throw new UserInfoServiceException("用户" + unionid + "已存在");
        }

        TikuUser tikuUser = new TikuUser();
        tikuUser.setUserUuid(unionid);
        tikuUser.setUsername(unionid);
        tikuUser.setGrade(userInfo.getGrade());
        tikuUserRepository.save(tikuUser);
    }

    @Override
    public UserInfo getUserInfo(UserId userId) throws UserInfoServiceException {
        @NotNull String unionid = userId.getUnionid();

        List<TikuUser> list = tikuUserRepository.findAllByUserUuidEquals(unionid);

        if (list.size()==0) {
            throw new UserInfoServiceException("用户不存在");
        }
        TikuUser tikuUser = list.get(0);
        UserInfo userInfo = new UserInfo();
        userInfo.setGrade(tikuUser.getGrade());
        userInfo.setUnionid(tikuUser.getUserUuid());

        List<UserProblemStatus> upss = userProblemStatusRepository.findAllByUserUuid(unionid);
        if (upss == null || upss.size() == 0) {
            throw new UserInfoServiceException("用户目前还没有题目");
        }
        List<Long> pids = upss.stream().map(UserProblemStatus::getProblemId).collect(Collectors.toList());
        List<Long> tids = problemTagRepository
                .findAllByIsDelAndProblemIdIn(false, pids).stream()
                .map(ProblemTag::getTagId)
                .distinct()
                .collect(Collectors.toList());
        List<String> tagnames = tagRepository.findAllByIdIn(tids).stream()
                .map(Tag::getValue)
                .distinct()
                .collect(Collectors.toList());
        userInfo.setHasTags(tagnames);
        return userInfo;
    }
}
