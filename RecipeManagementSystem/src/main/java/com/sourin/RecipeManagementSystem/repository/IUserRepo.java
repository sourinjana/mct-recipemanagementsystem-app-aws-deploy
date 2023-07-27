package com.sourin.RecipeManagementSystem.repository;

import com.sourin.RecipeManagementSystem.model.AuthenticationToken;
import com.sourin.RecipeManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Long> {
    User findFirstByUserEmail(String newEmail);
}
