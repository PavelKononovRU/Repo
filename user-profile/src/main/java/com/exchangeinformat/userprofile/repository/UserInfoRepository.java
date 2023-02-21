package com.exchangeinformat.userprofile.repository;

import com.exchangeinformat.userprofile.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
}
