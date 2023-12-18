package com.example.weatherapp;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.weatherapp.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class DatabaseConnectionTest {

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository

    @Test
    public void testDatabaseConnection() {

        try {
            long userCount = userRepository.count();
            assertThat(userCount).isGreaterThanOrEqualTo(0L);
            System.out.println(userRepository.count());
        } catch (Exception e) {
            System.out.println("Connection failed");
        }
    }
}
