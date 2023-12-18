package com.example.weatherapp.Controller;


import com.example.weatherapp.Dto.UserDto;
import com.example.weatherapp.Service.UserService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Data
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;
    UserDto userDto = new UserDto();
    @PostMapping
    //@GetMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model){
            model.addAttribute("userDto", userDto);
            userService.findByEmail(userDto.getEmail());
            System.out.println(model.getAttribute("userDto"));
            return "test";
    }

//    @PostMapping
//    public String authentication(@ModelAttribute("userDto") UserDto userDto){
//        System.out.println("Received UserDto: " + userDto);
//
//        return "test";
//    }

}
