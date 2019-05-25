package com.nfjokes.api;


import com.nfjokes.model.User;
import com.nfjokes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@CrossOrigin
public class SecurityController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    public User login(@RequestBody User user) {
        User registeredUser = userService.findByEmail(user.getEmail());
        if(registeredUser != null && passwordEncoder.matches(user.getPassword(),registeredUser.getPassword())){
            Authentication authentication = new UsernamePasswordAuthenticationToken(registeredUser, null, registeredUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return registeredUser;
        }
        return null;
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }
}