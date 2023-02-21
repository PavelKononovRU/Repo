package com.exchangeinformant.services;

import com.exchangeinformant.model.UserInfo;
import com.exchangeinformant.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository infoRepository;
    @Value("${quotes.supplier}")
    private String serviceName;

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
                .lastVisit(visitSetter(id))
                .lastRequest(LocalDateTime.now())
                .source(serviceName)
                .build();
        infoRepository.save(userInfo);
    }
    private int counterSetter(String id) {
        if (infoRepository.findById(id).isEmpty()) {
            return 1;
        }else {
            if(infoRepository.findById(id).orElseThrow().getLastVisit().getMonth().equals(LocalDateTime.now().getMonth())){
                return infoRepository.findById(id).orElseThrow().getCounter() + 1;
            } else{
                return 1;
            }
        }
    }
    private LocalDateTime visitSetter(String id) {
        if (infoRepository.findById(id).isEmpty()) {
            return LocalDateTime.now();
        } else {
            return infoRepository.findById(id).orElseThrow().getLastVisit();
        }
    }
    private Map<String, Object> getExtID(Principal principal) {
        JwtAuthenticationToken kp = (JwtAuthenticationToken) principal;
        Jwt token = kp.getToken();
        return token.getClaims();
    }
}
