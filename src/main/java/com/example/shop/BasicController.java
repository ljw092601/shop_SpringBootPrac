package com.example.shop;

import com.example.shop.Member.UserRepository;
import com.example.shop.Member.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class BasicController {
    @GetMapping("/")
    String hello() {
        return "index.html";
    }

    @GetMapping("/about")
    @ResponseBody
    String hello_about() {
        return "I'm best!";
    }

    @GetMapping("/date")
    @ResponseBody
    String print_date() {
        return LocalDate.now().toString();
    }

}
