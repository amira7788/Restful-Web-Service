
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

----

Code Changes for Next Step - https://github.com/in28minutes/master-spring-and-spring-boot/tree/main/12-rest-api/01-step-by-step-changes/v2.md#step-21



URLs
URI Versioning

V1: http://localhost:8080/v1/person

@GetMapping("/v1/person")

V2: http://localhost:8080/v2/person

@GetMapping("/v2/person")

Request Param Versioning

V1: http://localhost:8080/person?version=1

@GetMapping(path = "/person", params = "version=1")

V2: http://localhost:8080/person?version=2

@GetMapping(path = "/person", params = "version=2")

Header Versioning

V1: http://localhost:8080/person/header

HEADER - X-API-VERSION:1

@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")

V2: http://localhost:8080/person/header

HEADER - X-API-VERSION:2

@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")

Content Negotiation Versioning

V1: http://localhost:8080/person/accept

HEADER - Accept:application/vnd.company.app-v1+json

@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")

V2: http://localhost:8080/person/accept

HEADER - Accept:application/vnd.company.app-v1+json

@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")

V1 Response
{
"name": "Bob Charlie"
}
V2 Response

{
"name": {
"firstName": "Bob",
"lastName": "Charlie"
}
}

---
# versioning:
1- URI versioning: -Twitter
 http://localhost/V1/Person
 http://localhost/V2/Person

2- Request Parameter versioning : -Amazon
 http://localhost/person?version=1
 http://localhost/person?version=2

3- (Custom) headers versioning -Microsoft
add header:
X-API-VERSION -> value = 1
X-API-VERSION -> value = 2

4- Media type versioning - Github
produces = application/vmd-company-app-v1+json
produces = application/vmd-company-app-v2+json
accept -> application/vmd-company-app-v1+json

---
# filter
## static filter
@JsonFilter or @ @JsonIgnoreProperties 
## dynamic filter
JsonFilter with FilterProvider
SimpleBeanPropertyFilter 
MappingJacksonValue return for dynamic filter

---
# HAL format 
Json Hypertext Application Language
we have to return a type EntityManage on response of the ressource
this EntityManager wil have a link ofusing WebMvcLinkBuilder

this is HAL:
"links": [
{
"rel": "user-by-id",
"href": "http://localhost:8080/users/10001"
}
]

HAl explorer:
API to use Hal
Spring boot Hal explorer

---

Code Changes for Next Step - https://github.com/in28minutes/master-spring-and-spring-boot/tree/main/12-rest-api/01-step-by-step-changes/v2.md#step-35



Launch MySQL as Docker Container
docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=social-media-user --env MYSQL_PASSWORD=dummypassword --env MYSQL_DATABASE=social-media-database --name mysql --publish 3306:3306 mysql:8-oracle


mysqlsh commands
mysqlsh
\connect social-media-user@localhost:3306
\sql
use social-media-database
select * from user_details;
select * from post;
\quit


/pom.xml Modified
<dependency>
<groupId>mysql</groupId>
<artifactId>mysql-connector-java</artifactId>
</dependency>


/src/main/resources/application.properties Modified
#spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.show-sql=true
spring.datasource.url=jdbc:mysql://localhost:3306/social-media-database
spring.datasource.username=social-media-user
spring.datasource.password=dummypassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

---
Spring security
intersept request
filter chains
1/ check: all requests should be authenticated
 if a request is not authenticated a web page show
3/ CSRF-> POST, PUT
httpauthetication requested for rest API

we will create a configuration file to define filter chain
