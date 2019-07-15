package io.swagger.service;

import io.swagger.model.UserId;
import io.swagger.model.UserInfo;

public interface UserInfoService {
    void addUserInfo(UserInfo userInfo) throws UserInfoServiceImpl.UserInfoServiceException;

    UserInfo getUserInfo(UserId userId) throws UserInfoServiceImpl.UserInfoServiceException;
}
