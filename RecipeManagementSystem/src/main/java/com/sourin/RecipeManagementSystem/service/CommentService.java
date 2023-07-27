package com.sourin.RecipeManagementSystem.service;

import com.sourin.RecipeManagementSystem.model.Comment;
import com.sourin.RecipeManagementSystem.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    ICommentRepo commentRepo;
    public String addComment(Comment comment) {
        comment.setCommentCreationTimeStamp(LocalDateTime.now());
        commentRepo.save(comment);
        return "Comment has been added!!!";
    }

    public String removeComment(Long commentId, String email) {
        Comment comment=commentRepo.findById(commentId).orElse(null);

        if(comment!=null){
            if(authorizeCommentRemover(email,comment))
            {
                commentRepo.delete(comment);
                return "comment deleted successfully";
            }
            else
            {
                return "Unauthorized delete detected...Not allowed!!!!";
            }

        }
        else
        {
            return "Invalid Comment";
        }
        }

    private boolean authorizeCommentRemover(String email, Comment comment) {
        String commentOwnerEmail=comment.getCommenterUser().getUserEmail();
        String postOwnerEmail=comment.getRecipePost().getPostOwner().getUserEmail();

        return postOwnerEmail.equals(email) || commentOwnerEmail.equals(email);
    }
}

