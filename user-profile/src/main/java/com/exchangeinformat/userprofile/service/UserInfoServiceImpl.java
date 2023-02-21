package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.entity.UserInfo;
import com.exchangeinformat.userprofile.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository userInfoRepository;

    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserInfo getById(String id) {
        return userInfoRepository.findById(id).orElse(null);
    }
}
