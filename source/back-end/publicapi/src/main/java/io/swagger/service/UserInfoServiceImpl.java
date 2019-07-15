package io.swagger.service;

import io.swagger.model.UserInfo;
import io.swagger.pojo.dao.TikuUser;
import io.swagger.pojo.dao.repos.TikuUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    public class  UserInfoServiceException extends Exception{
        public UserInfoServiceException(String message) {
            super(message);
        }
    }
    @Autowired
    private TikuUserRepository tikuUserRepository;

    @Override
    public void addUserInfo(UserInfo userInfo) throws UserInfoServiceException {
        @NotNull String unionid = userInfo.getUnionid();

        Optional<TikuUser> byUserUuidEquals = tikuUserRepository.findByUserUuidEquals(unionid);
        if(byUserUuidEquals.isPresent()){
            log.warn("用户{}已存在",unionid);
            throw new UserInfoServiceException("用户"+unionid+"已存在");
        }

        TikuUser tikuUser = new TikuUser();
        tikuUser.setUserUuid(unionid);
        tikuUser.setUsername(unionid);
        tikuUser.setGrade(userInfo.getGrade());
        tikuUserRepository.save(tikuUser);
    }
}
