package com.exchangeinformant.aop;

import com.exchangeinformant.services.UserInfoService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class UserInfoAspect {

    private final UserInfoService userInfoService;

    public UserInfoAspect(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Before("@annotation(UserInfoInsertion)")
    @SneakyThrows
    public void saveUserInfo() {
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        log.info("Info about user with id {} has been added", principal.getName());
        userInfoService.save(principal);
    }

}
