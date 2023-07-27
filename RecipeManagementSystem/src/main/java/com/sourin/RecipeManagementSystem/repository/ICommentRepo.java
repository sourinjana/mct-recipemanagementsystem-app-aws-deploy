package com.sourin.RecipeManagementSystem.repository;

import com.sourin.RecipeManagementSystem.model.AuthenticationToken;
import com.sourin.RecipeManagementSystem.model.Comment;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepo extends JpaRepository<Comment,Long> {
}
