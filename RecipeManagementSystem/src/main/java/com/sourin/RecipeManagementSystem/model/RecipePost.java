package com.sourin.RecipeManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sourin.RecipeManagementSystem.model.enums.Type;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RecipePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipePostId;
    @NotBlank
    private String recipePostName;
    @NotBlank
    private String recipeIngredients;
    @NotBlank
    private String recipeInstructions;
    @NotBlank
    private String postLocation;
    @Enumerated(EnumType.STRING)
    private Type recipeTypes;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // hide this in json but not in database table column
    private LocalDateTime postCreatedTimeStamp;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinColumn(name = "fk_post_user_id")
    private User postOwner;


}
