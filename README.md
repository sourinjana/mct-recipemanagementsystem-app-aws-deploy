# Recipe Management System


* **FrameWorks and Language Used**
     * SpringBoot
     * Java

* **Dependencies**
     * SpringBoot Web
     * JPA
     * SQL DataBase
     * Lombok
     * Validation
     * Swagger
     * JavaMail

* **Data Flow**
     * Controller ( Here Make All API )
          1. UserController
             *  **All Get API**
                 1. @GetMapping("recipes/post")
                 2. @GetMapping("filter/recipe/post/{recipename}")
                 3. @GetMapping("users")

             *   **All Post API**
                 1. @PostMapping("signup/user")
                 2. @PostMapping("signin/user")
                 3. @PostMapping("recipe/post") 
                 4. @PostMapping("comment")
             *   **All Delete API**
                 1. @DeleteMapping("user/signOut")
                 2. @DeleteMapping("recipe/post")
                 3. @DeleteMapping("comment")
             *   **All Put API
                 1. @PutMapping("recipe/post/{postid}/{recipeinstructions}")     

          
     * Service ( Here Make All Logic )
          1. UserService
          2. RecipePostService
          3. CommentService
          4. AuthenticationTokenService
          5. PasswordEncrypter
          6. EmailHandler
          

     * Repository ( Here Make All DataBase Connection )
          1. IUserRepo
          2. IRecipePostRepo
          3. ICommentRepo
          4. IAuthenticationTokenRepo
     * Model

          1. User
          2. RecipePost
          3. Comment
          4. AuthenticationToken
          5. EnumType :-
              1. Type
              2. Gender
     


* **Database and Server Used**
     * SQL DataBase and AWS Server

* **Summary**
  * This is a Recipe Management System Program.
  * Here a User First SignUp Then Signin.
  * Then User Create Recipe Post.And Another User Comment on this Recipe Post.
  * There are Some validation About Detele,Update,get and Post.

                       **Two type of Mapping Use**.
  1. OneToOne
  2. ManyToOne

  






