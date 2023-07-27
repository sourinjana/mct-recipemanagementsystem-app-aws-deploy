package com.sourin.RecipeManagementSystem.controller;

import com.sourin.RecipeManagementSystem.model.Comment;
import com.sourin.RecipeManagementSystem.model.RecipePost;
import com.sourin.RecipeManagementSystem.model.User;
import com.sourin.RecipeManagementSystem.model.dto.SignInInput;
import com.sourin.RecipeManagementSystem.service.AuthenticationTokenService;
import com.sourin.RecipeManagementSystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

   // for user account
    @PostMapping("signup/user")
    public String userSignUp(@RequestBody User user){
        return userService.userSignUp(user);
    }

    @PostMapping("signin/user")
    public String userSignIn(@RequestBody @Valid SignInInput signInInput){
        return userService.userSignIn(signInInput);
    }


    @DeleteMapping("user/signOut")
    public String signOutUser(@RequestParam String email, @RequestParam String token)
    {
        if(authenticationTokenService.authVerify(email,token)) {
            return userService.signOutUser(email);
        }
        else {
            return "Sign out not allowed for non authenticated user.";
        }

    }

    // for post
    @PostMapping("recipe/post")
    public String createRecipePost(@RequestBody RecipePost recipePost, @RequestParam String userEmail, @RequestParam String userTokenValue){

        if(authenticationTokenService.authVerify(userEmail,userTokenValue)){
            return userService.createInstaPost(recipePost,userEmail);
        }else{
            return "Not an Authenticated user activity!!! You can not Create Post";
        }
    }



    @GetMapping("recipes/post")
    public List<RecipePost> getAllPost(@RequestParam String userEmail, @RequestParam String userTokenValue){
        if(authenticationTokenService.authVerify(userEmail,userTokenValue)){
            return userService.getAllPost(userEmail);
        }

        return null;
    }


    @DeleteMapping("recipe/post")
    public String removeRecipePost(@RequestParam Long postId, @RequestParam String email, @RequestParam String token)
    {
        if(authenticationTokenService.authVerify(email,token)) {
            return userService.removeRecipePost(postId,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }


    // for comment @PostMapping("comment")
        @PostMapping("comment")
        public String addComment(@RequestBody Comment comment, @RequestParam String commenterEmail, @RequestParam String commenterToken)
        {
            if(authenticationTokenService.authVerify(commenterEmail,commenterToken)) {
                return userService.addComment(comment,commenterEmail);
            }
            else {
                return "Not an Authenticated user activity!!!";
            }
        }



    @DeleteMapping("comment")
    public String removeComment(@RequestParam Long commentId, @RequestParam String email, @RequestParam String token)
    {
        if(authenticationTokenService.authVerify(email,token)) {
            return userService.removeComment(commentId,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }



    //search with recipe name
  @GetMapping("filter/recipe/post/{recipename}")
    public List<RecipePost> getWithRecipeName(@PathVariable String recipename,@RequestParam String userEmail, @RequestParam String userTokenValue){
        if(authenticationTokenService.authVerify(userEmail,userTokenValue)){
            return userService.getWithRecipeName(recipename);
        }

        return null;
    }



    //update recipeInstructions

    @PutMapping("recipe/post/{postid}/{recipeinstructions}")
    public String updateRecipeInstructions(@PathVariable Long postid,@PathVariable String recipeinstructions , @RequestParam String userEmail, @RequestParam String userToken) {
        if (authenticationTokenService.authVerify(userEmail, userToken)) {
            return userService.updateRecipeInstructions(postid, recipeinstructions, userEmail);
        } else {
            return "Not an Authenticated user activity!!!";
        }


    }


    @GetMapping("users")
    public List<User> getAllUser(@RequestParam String userEmail, @RequestParam String userTokenValue){
        if(authenticationTokenService.authVerify(userEmail,userTokenValue)){
            return userService.getAllUser();
        }

        return null;
    }


}
