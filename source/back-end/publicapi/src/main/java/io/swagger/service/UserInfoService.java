package io.swagger.service;

import io.swagger.model.UserInfo;

public interface UserInfoService {
    void addUserInfo(UserInfo userInfo) throws UserInfoServiceImpl.UserInfoServiceException;
}
