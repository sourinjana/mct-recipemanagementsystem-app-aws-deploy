package com.sourin.RecipeManagementSystem.model;

import com.sourin.RecipeManagementSystem.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotBlank
    private String userName;
    @NotBlank
    private String userBio;
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Invalid email format")    @Column(unique = true)
    private String userEmail;
    @NotBlank
    private String userPassword;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}
