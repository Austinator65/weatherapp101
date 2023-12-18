package com.example.weatherapp.Service;

import com.example.weatherapp.Dto.UserDto;
import com.example.weatherapp.Model.User;
import com.example.weatherapp.Repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class UserService {
    @Autowired
    private final UserRepository userRepository;


    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public void registerUser(UserDto userDto){
        User user = new User();
        System.out.println("Serveci data: " + userDto.getFirstName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        System.out.println("Main user: "+user.getFirstName());
        userRepository.save(user);

        //return employee.getEmployeename();
    };


}
