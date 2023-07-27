package com.sourin.RecipeManagementSystem.service;

import com.sourin.RecipeManagementSystem.model.RecipePost;
import com.sourin.RecipeManagementSystem.model.User;
import com.sourin.RecipeManagementSystem.repository.IRecipePostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecipePostService {
    @Autowired
    IRecipePostRepo recipePostRepo;
    public String createInstaPost(RecipePost recipePost) {

        recipePost.setPostCreatedTimeStamp(LocalDateTime.now());
        recipePostRepo.save(recipePost);

        return "Post uploaded!!!!";
    }

    public List<RecipePost> getAllPost(User postOwner) {
        return recipePostRepo.findAllByPostOwner(postOwner);
    }

    public String removeRecipePost(Long postId, User user) {

        RecipePost post  = recipePostRepo.findById(postId).orElse(null);
        if(post != null && post.getPostOwner().equals(user))
        {
           recipePostRepo.deleteById(postId);

            return "Removed successfully";
        }
        else if (post == null)
        {
            return "Post to be deleted does not exist";
        }
        else{
            return "Un-Authorized delete detected....Not allowed";
        }
    }

    public boolean validatePost(RecipePost recipePost) {
        return (recipePost!=null && recipePostRepo.existsById(recipePost.getRecipePostId()));
    }

    public List<RecipePost> getWithRecipeName(String recipeName) {
        return recipePostRepo.findAllByRecipePostName(recipeName);
    }

    public String updateRecipeInstructions(Long postid, String recipeinstructions, User user) {
        RecipePost recipePost=recipePostRepo.findById(postid).orElse(null);
        if(recipePost!=null){

            if(recipePost.getPostOwner().equals(user)){
                recipePostRepo.updateRecipeInstructions(postid,recipeinstructions,user.getUserId());
                return "update done";
            }else{
                return "You can not allowed this !!";
            }

        }else{
            return "Post is not Valid";
        }
    }
}
