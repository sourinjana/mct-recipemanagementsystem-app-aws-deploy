package com.sourin.RecipeManagementSystem.repository;

import com.sourin.RecipeManagementSystem.model.AuthenticationToken;
import com.sourin.RecipeManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationTokenRepo extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findFirstByTokenValue(String userTokenValue);

    AuthenticationToken findFirstByUser(User user);
}
