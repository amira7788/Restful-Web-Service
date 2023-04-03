
---
All requests first go to the DispatcherServlet
the DispatcherServlet will search for the controller
DispatcheServletAutoConfiguration by spring boot will configure the dispatcherServlet
How the response will be a Json of a requestMapping hwo return an object
@ResponseBody + JacksonhttpMessageResponse

@RestController has annotation @ResponseBody

JacksonHttpMessageConvertersConfiguration will convert to Json it's autoConfigured by spring boot for restapi
who is configuring error responses :ErrorMvcAutoConfiguration

how all jars available: spring web, Jakson, tomcat?
the dependency spring web 
----
# Request Methods for Rest api
-- Get : Retrieve details of a resource
-- Post: Create a new resource
-- PUT: Update an existing resource
-- Patch: update part of a resource
-- DELETE: Delete a resource

# users Rest api with plurals
-- All users: Get /users
-- new user: Post /users 
-- one user return: Get /users/{id}
-- delete user : DELETE /users/{id}

-- all posts of on user : Get /Users/{id}/posts
-- create a new post for one user : Post /Users/{id}/posts
-- retrieve details for one post : GET /users/{id}/posts/{post_id}
---
Add talend api extention
---
Responses Statuses:
200- Success
201- created
202- No content
401- Unauthorized (when authorization fails)
400- Bad request (such as validation error)
404- Resource Not Found
500- Server Error

--- 
## return 201 to a post request to save use with location url :
@PostMapping (path="/users")
public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
userDaoService.save(user);
URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
return  ResponseEntity.created(location).build();
}

---
# ControllerAdvice
@ControllerAdvice("com.reflectoring.controller"): we can pass a package name or list of package names in the annotation’s value or basePackages parameter. With this, the controller advice will only handle exceptions of this package’s controllers.
@ControllerAdvice(annotations = Advised.class): only controllers marked with the @Advised annotation will be handled by the controller advice.
Find other parameters in the @ControllerAdvice annotation docs.

---
https://www.bezkoder.com/spring-boot-jpa-h2-example/
https://www.bezkoder.com/spring-boot-angular-15-crud/
---

OpenApi in 2016 based on swagger de 2012
<dependency>
<groupId>org.springdoc</groupId>
<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
<version>2.0.0</version>
</dependency>
