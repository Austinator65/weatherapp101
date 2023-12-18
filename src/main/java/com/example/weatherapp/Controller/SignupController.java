package com.example.weatherapp.Controller;


import com.example.weatherapp.Dto.UserDto;
import com.example.weatherapp.Service.UserService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import javax.servlet.http.HttpServletRequest;

@Controller
@Data
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;
    UserDto userDto = new UserDto();


    @GetMapping
    public String showSignupForm(Model model) {
        model.addAttribute("userDto", userDto);
        System.out.println(model.getAttribute("userDto"));
        return "signup";
    }



    @PostMapping
    public String login(@ModelAttribute("userDto") UserDto userDto){
        System.out.println("Received UserDto: " + userDto);
        //System.out.println(userDto.getFirstName());
        //setUserDto(userDto);
        userService.registerUser(userDto);
        System.out.println(getUserDto());
        return "redirect:/login";
    }


}
