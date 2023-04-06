package com.latcha.restfulwebservices.versioning;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping(path = "/v1/person")
    public PersonV1 getFirstVersionPerson() {
        return new PersonV1("Amira Abdellatif");
    }

    //URI versioning twitter
    @GetMapping(path = "/v2/person")
    public PersonV2 getSecondVersionPerson() {
        return new PersonV2(new Name("Amira", "Said"));
    }

    //request param versioning
    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getPersonRequestParam() {
        return new PersonV1("Amira Abdellatif");

    }

    //header request versioning
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV2 getSecondVersionPersonRequestHeader() {
        return new PersonV2(new Name("Amira", "Said"));
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV1 getFirstVersionPersonRequestHeader() {
        return new PersonV1("Amira Abdellatif");
    }

    //Media type header -github
    @GetMapping(path = "/person/header", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionPersonProduces() {
        return new PersonV2(new Name("Amira", "Said"));
    }



}
