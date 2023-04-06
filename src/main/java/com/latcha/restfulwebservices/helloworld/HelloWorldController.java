package com.latcha.restfulwebservices.helloworld;

import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Locale;

//Rest api
@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    //hello-world
    //return "hello world
    //@RequestMapping(method = RequestMethod.GET, path="/hello-world")
    @GetMapping(path="/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }
    //Path parameters
    // /user/{id}/todos/{id}

    @GetMapping(path="/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World %s",name));
    }
    @GetMapping(path="/hello-world-internationalized")
    public String helloWorldInternationalized() {
        Locale local = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", local);
        //return "Hello World V2";
    }

}
