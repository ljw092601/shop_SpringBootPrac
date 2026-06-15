package com.example.shop.Comment;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    @PostMapping("/comment")
    public String addComment(@ModelAttribute Comment comment) {
        commentRepository.save(comment);
        // 저장 후 해당 아이템 detail 페이지로 리다이렉트
        return "redirect:/detail/" + comment.getParentId();
    }

}
