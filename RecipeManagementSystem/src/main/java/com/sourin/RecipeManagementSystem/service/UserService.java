package com.sourin.RecipeManagementSystem.service;

import com.sourin.RecipeManagementSystem.model.AuthenticationToken;
import com.sourin.RecipeManagementSystem.model.Comment;
import com.sourin.RecipeManagementSystem.model.RecipePost;
import com.sourin.RecipeManagementSystem.model.User;
import com.sourin.RecipeManagementSystem.model.dto.SignInInput;
import com.sourin.RecipeManagementSystem.repository.IUserRepo;
import com.sourin.RecipeManagementSystem.service.emailUtility.EmailHandler;
import com.sourin.RecipeManagementSystem.service.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @Autowired
    RecipePostService recipePostService;

    @Autowired
    CommentService commentService;
    public String userSignUp(User user) {


        String newEmail=user.getUserEmail();

        if(newEmail==null){
            return "Invalid Email ";
        }
        User existingUser=userRepo.findFirstByUserEmail(newEmail);

        if(existingUser!=null){
            return "Email already registered!!! Please Sign In !!";
        }

        try {
            String encrypPassword= PasswordEncrypter.encryptPassword(user.getUserPassword());
            user.setUserPassword(encrypPassword);
            userRepo.save(user);
            return "Sign Up Completed !! Please Sign In";

        } catch (Exception e) {
            return "Internal error occurred during sign up";
        }
    }

    public String userSignIn(SignInInput signInInput) {
        String newEmail=signInInput.getUserEmail();

        if(newEmail==null){
            return "Invalid Email ";
        }

        User existingUser=userRepo.findFirstByUserEmail(newEmail);
        if(existingUser==null){
            return "You hava do not Account !! Please SignUp First !!";
        }

        try {
            String encrypPassword=PasswordEncrypter.encryptPassword(signInInput.getUserPassword());

            if(existingUser.getUserPassword().equals(encrypPassword)){
                AuthenticationToken authenticationToken=new AuthenticationToken(existingUser);
                EmailHandler.sendEmail(newEmail,"OTP-Auth",authenticationToken.getTokenValue());
                authenticationTokenService.saveAuthToken(authenticationToken);
                return "Token sent to your email";
            }else{
                return "Invalid credentials!!!";
            }
        } catch (Exception e) {

            return "Internal error occurred during sign in";
        }
    }

    public String createInstaPost(RecipePost recipePost, String userEmail) {
        User postOwner=userRepo.findFirstByUserEmail(userEmail);

        recipePost.setPostOwner(postOwner);

        return recipePostService.createInstaPost(recipePost);
    }

    public List<RecipePost> getAllPost(String userEmail) {
        User postOwner=userRepo.findFirstByUserEmail(userEmail);
        return recipePostService.getAllPost(postOwner);
    }


    public String removeRecipePost(Long postId, String email) {

        User user = userRepo.findFirstByUserEmail(email);
        return recipePostService.removeRecipePost(postId,user);
    }



    public String signOutUser(String email) {
        User user = userRepo.findFirstByUserEmail(email);
        return authenticationTokenService.signOutUser(user);
    }

    public String addComment(Comment comment, String commenterEmail) {
        boolean postValid = recipePostService.validatePost(comment.getRecipePost());
        if(postValid) {
            User commenter = userRepo.findFirstByUserEmail(commenterEmail);
            comment.setCommenterUser(commenter);
            return commentService.addComment(comment);
        }
        else {
            return "Cannot comment on Invalid Post!!";
        }
    }

    public String removeComment(Long commentId, String email) {
        return commentService.removeComment(commentId,email);
    }

    public List<RecipePost> getWithRecipeName(String recipeName) {

        return recipePostService.getWithRecipeName(recipeName);
    }


    public String updateRecipeInstructions(Long postid, String recipeinstructions, String userEmail) {
        User user = userRepo.findFirstByUserEmail(userEmail);
        return recipePostService.updateRecipeInstructions(postid,recipeinstructions,user);
    }


    public List<User> getAllUser() {

        return userRepo.findAll();
    }
}
