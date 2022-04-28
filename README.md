# GNKBC-WebApp Development Log
  Full-Stack development for launching Grace New York Church's Offical Web application.
  
  * Static Web page, Admin service, Bulletin service in Java Spring <br>
  * User auth, contact us in AWS Lambda 
  
# Requirements 
  - Cost efficient
  - Web App that is able to easily update church's information without a developer
  - Responsive Web App
  - User Management
  - Design Java class following MVC (Model,View,Controller) pattern and OOP rules
  

# Stack
  ## Frontend
   * Thymeleaf - SSR with Spring framework
   * Jquery
   * Bootstrap
  ## Backend
   * Java Spring framework
   * JPA
   * RDB
  ## Tools 
   * Lombok
   * Postman 
   * InteliJ
   * Junit
   * Gson
   * Gradle
   

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
   - Depend on Interface class, not its implementation
  ## Development Note
   - @Qualifier
   - @PostConstruct , @PreDestroy
   - GSON
