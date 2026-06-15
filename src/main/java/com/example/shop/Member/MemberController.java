package com.example.shop.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/sign")
    public String sign(Model model) {
        return "account.html";
    }

    @PostMapping("/account")
    String account(String username, String password, String displayName) {
        userService.registerUser(username, password, displayName);
        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication authentication, Model model) {
        if(authentication.isAuthenticated()){
            return "my-page.html";
        }
        else {
            return "login.html";
        }
    }
}
