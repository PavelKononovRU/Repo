package com.exchangeinformant.services;

import java.security.Principal;

public interface UserInfoService {
    void save(Principal principal);
}
