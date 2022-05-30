# GNKBC-WebApp Development Log
  Full-Stack development for launching Grace New York Church's Offical Web application.
  
  * Static Web page, Admin service, Bulletin service in Java Spring <br>
  * Google Authentication
  * contact us using AWS Lambda 
  * CI&CD Deployment on AWS
  
# Requirements 
  - Cost efficient
  - Web App that is able to easily update church's information without a developer
  - Responsive Web App
  - User Management
  - Design Java class following MVC (Model,View,Controller) pattern and OOP rules
  - RDB with JPA, Spring Data
  

# Stack
  ## Frontend
   * Thymeleaf - SSR with Spring framework
   * Jquery
   * Bootstrap
   * Quiljs 
  ## Backend
   * Java Spring framework
   * JPA
   * RDB
   * JSON
   * Spring Data
   * Spring Security
  ## Tools 
   * Lombok
   * Postman 
   * InteliJ
   * Junit
   * Gson
   * Gradle
   * AWS Lambda
   * Docker
   * H2
   * JenKins

  ## Setting up JenKins on EC2
  
    1. Install Java 8 
        - sudo yum install java-1.8.0-openjdk
        - java -version
        - alternatives --config java
        
    2. Add Jenkins directory
        - sudo wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat/jenkins.repo 
        trusted package...
        - sudo rpm --import http://pkg.jenkins-ci.org/redhat/jenkins-ci.org.key
    3. Install
        - sudo yum install jenkins 
    4. Start 
       - sudo systemctl start jenkins
       - sudo systemctl status jenkins.service
- ![Screen Shot 2022-05-30 at 3 35 12 PM](https://user-images.githubusercontent.com/76544061/171051370-3852a002-bf59-464c-ba62-720263cc5961.png)
  
  ## Add Inbound Rule for Jenkins, port 8080
     
      
       ![Screen Shot 2022-05-30 at 3 40 52 PM](https://user-images.githubusercontent.com/76544061/171051897-ea8de72c-d703-41c1-9d92-077f2e2c889e.png)



   

# Step 1: Static web page 
  ## TODO & Completion
   - Select Frontend template that is using Bootstrap, Jquery
   - Integration with Thymeleaf in order to use the pre-built template with Spring framework
   - Communication with the design team in GNKBC to optimize and improve website's view
# Step 2: Basic Admin page
  ## TODO & Completion
   - Admin service for someone who is not developer
   - JSON storage for storing static string data in order to keep static data and reflect data modification without a Relational Data Base
   - GSON libary to manage the JSON storage
   - Postman to test admin service's functionality
   - Thymeleaf and JavaScript to deliver a changed string data to front view from the Static data repository
   - Keep MVC & OOP pattern for maintenance and prevent entire system faliure caused by a single point of faliure
   - Stricted follow the dependent flow: Controller - Service - Repository 
  ## Development Note
   - @Qualifier
   - @PostConstruct , @PreDestroy
   - GSON
  ## Static String Data Management
    - 
  ## Static Image File Management
# Step 3: Authentication via Google and Configuration Spring security

# Step 4: Integration post writer with a rich text editor at frontend & jQuery AJAX

# Step 5: Design Relational Data Base UML
   * Persistence Context Flow
   * <img width="1302" alt="Screen Shot 2022-05-23 at 2 05 17 PM" src="https://user-images.githubusercontent.com/76544061/169909971-7d2814d5-b3cb-4231-95a1-6cb3b3cd7879.png">

# Step 6: Implementation data & entity class using JPA and connecting with H2 database

# Step 6: Member and Post CRUD operators using JPA 
  
# Step 7: Development Contact us page using AWS Lambda

# Step 8: Build Docker image and configure CI&CD environment

# Step 9: Integration with AWS and Deployment
