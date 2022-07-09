package pl.mlisowski.projectmanagement.security.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @PostMapping("/login")
    public void login(@RequestParam String email, @RequestParam String password){

    }

}
