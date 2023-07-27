package com.sourin.RecipeManagementSystem.repository;

import com.sourin.RecipeManagementSystem.model.AuthenticationToken;
import com.sourin.RecipeManagementSystem.model.RecipePost;
import com.sourin.RecipeManagementSystem.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecipePostRepo extends JpaRepository<RecipePost,Long> {
    List<RecipePost> findAllByPostOwner(User postOwner);

    List<RecipePost> findAllByRecipePostName(String recipeName);
    @Transactional
    @Modifying
    @Query(value = "update recipe_post set recipe_instructions= :recipeinstructions where recipe_post_id= :postid and fk_post_user_id= :id",nativeQuery = true)
    void updateRecipeInstructions(Long postid, String recipeinstructions, Long id);


}
