package com.ebalefn.dest.client;

import com.ebalefn.dest.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service", url = "http://user-app:8080/users")
public interface UserClient {
    @GetMapping("/")
    List<UserDTO> getAllUsers();

    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);
}
