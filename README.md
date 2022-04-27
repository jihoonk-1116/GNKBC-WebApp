# GNKBC-WebApp Development Log
  Full-Stack development for launching Grace New York Church's Offical Web application.
  
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

# TODO List
 * Select Frontend template that is using Bootstrap, Jquery
 * Integration with Thymeleaf in order to utilize server side rendering using Spring framework
 * Design Java class following MVC (Model,View,Controller) pattern and OOP rules
 * Build Json data storage to manage static string data(ex.. church introduction) using Gson 
 * Develop admin service to change web site's information without a professional developer

# Step 1: Static web page 
  ## TODO & Completion
   - Integration with Thymeleaf in order to use the pre-built template with Spring
   - Communication with the design team in GNKBC to optimize and improve website's view
# Step 2: Basic Admin page
  ## TODO & Completion
   - Admin service for someone who is not developer
   - JSON storage for storing static string data in order to keep and reflect static data modification without Relational Data Base
   - Gson to manage the JSON storage
   - Postman to test admin service's functionality
   - Thymeleaf and inline JavaScript to deliver a changed string data from JSON storage
   - Keep MVC & OOP pattern for maintenance and prevent entire system faliure caused by a single point of faliure
   - Stricted follow : Controller - Service - Repository pattern
   - Depend on Interface class, not its implementation
