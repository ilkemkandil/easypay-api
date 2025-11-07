package com.easypay.api;

import com.easypay.api.model.User;
import com.easypay.api.model.enums.Role;
import com.easypay.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    void insertUserIntoDb() {
        User user = new User("Lionel Messi", "lieonel.messi@barcelona.com", "xxx", "02125352658", Set.of(Role.USER));
        userRepository.save(user);

        boolean exists = userRepository.existsByEmail("lieonel.messi@barcelona.com");
        assertThat(exists).isTrue();
    }

}
