package ua.com.alevel.security.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.alevel.security.persistence.entity.UserToken;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Integer> {

    UserToken findByToken(String token);
    UserToken findByUserId(Integer userId);
}
