package com.example.weatherapp.Controller;

import com.example.weatherapp.Dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/main")
public class MainController {
    @GetMapping
    public String showSignupForm(Model model) {

        return "test";
    }


}
