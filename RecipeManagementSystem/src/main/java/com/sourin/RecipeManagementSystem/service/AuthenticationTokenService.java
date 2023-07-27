package com.sourin.RecipeManagementSystem.service;

import com.sourin.RecipeManagementSystem.model.AuthenticationToken;
import com.sourin.RecipeManagementSystem.model.User;
import com.sourin.RecipeManagementSystem.repository.IAuthenticationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {
    @Autowired
    IAuthenticationTokenRepo authenticationTokenRepo;

    public void saveAuthToken(AuthenticationToken authenticationToken){
        authenticationTokenRepo.save(authenticationToken);
    }

    public boolean authVerify(String userEmail, String userTokenValue) {

        AuthenticationToken tokenValue=authenticationTokenRepo.findFirstByTokenValue(userTokenValue);
        if(tokenValue == null)
        {
            return false;
        }
        String email=tokenValue.getUser().getUserEmail();
        if(tokenValue.getTokenValue().equals(userTokenValue) && email.equals(userEmail)){
            return true;
        }


        return false;
    }

    public String signOutUser(User user) {
        AuthenticationToken authenticationToken = authenticationTokenRepo.findFirstByUser(user);
        authenticationTokenRepo.delete(authenticationToken);
        return "User Signed out successfully";
    }
}
