package com.exchangeinformant.services;

import com.exchangeinformant.model.UserInfo;
import com.exchangeinformant.repository.UserInfoRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository infoRepository;

    public UserInfoServiceImpl(UserInfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    @Override
    public void save(Principal principal) {
        Map<String, Object> userMap = getExtID(principal);
        String id = userMap.get("sub").toString();
        UserInfo userInfo = UserInfo.builder()
                .userId(id)
                .counter(counterSetter(id))
                .lastVisit(LocalDateTime.now())
                .lastRequest(LocalDateTime.now())
                .source("bcs")
                .build();
        infoRepository.save(userInfo);
    }
    private int counterSetter(String id) {
        if (infoRepository.findById(id).isEmpty()) {
            return 1;
        }else {
            return infoRepository.findById(id).get().getCounter() + 1;
        }
    }
    private Map<String, Object> getExtID(Principal principal) {
        JwtAuthenticationToken kp = (JwtAuthenticationToken) principal;
        Jwt token = kp.getToken();
        return token.getClaims();
    }
}
