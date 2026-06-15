package com.example.shop.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(String username, String password, String displayName) {
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));
        member.setDisplayName(displayName);
        userRepository.save(member);
    }
}
