package com.example.weatherapp.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping(value = "")
    //@GetMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){
      return "login";
    }

    @PostMapping
    public String authentication(String email, String password){
        System.out.println("Username: " + email);
        System.out.println("Password: " + password);
        return "/hello";
    }

}
