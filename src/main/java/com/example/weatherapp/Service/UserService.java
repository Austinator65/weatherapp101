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
   // @Autowired
    private final UserRepository userRepository;


    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public void registerUser(UserDto userDto){
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setPassword(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        //return employee.getEmployeename();
    };


}
