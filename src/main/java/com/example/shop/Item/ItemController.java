package com.example.shop.Item;

import com.example.shop.Comment.Comment;
import com.example.shop.Comment.CommentRepository;
import com.example.shop.Member.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final S3Service s3Service;
    private final CommentRepository commentRepository;

    @GetMapping("/list")
    public String redirectList() {
        return "redirect:/list/page/1";
    }

    @GetMapping("/list/page/{num}")
    String getListPage(@PathVariable Integer num, Model model) {
        var result = itemRepository.findPageBy(PageRequest.of(num-1, 5));
        model.addAttribute("items", result);
        return "list.html";
    }

    @GetMapping("/register")
    String register(Model model) {
        return "write.html";
    }

    @PostMapping("/add")
    String add(String name, String price, String category, String image) {
        itemService.saveItem(name, price, category, image);
        return "redirect:/register";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Integer id,
                  @RequestParam(defaultValue = "0") int page,
                  Model model) {

        var result = itemRepository.findById(id);
        if (result.isEmpty()) return "redirect:/list";

        Item item = result.get();
        model.addAttribute("item", item);

        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "id"));
        Page<Comment> comments = commentRepository.findByParentId(id, pageable);

        model.addAttribute("comments", comments);     // Page<Comment>
        model.addAttribute("currentPage", page);      // 현재 페이지 인덱스(0-base)
        return "detail.html";
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable String id, Model model) {
        int intId;
        try {
            intId = Integer.parseInt(id);
            System.out.println("변환 성공: " + intId);
        } catch (NumberFormatException e) {
            System.out.println("변환 불가: " + id);
            return "redirect:/list";
        }
        var result = itemRepository.findById(intId);
        Item item = null;
        if (result.isPresent()) {
            item = result.get();
        }
        else {
            return "redirect:/list";
        }
        model.addAttribute("item", item);
        return "edit.html";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute("item") Item item) {
        itemService.updateItem(id, item.getName(), item.getPrice(), item.getCategory(), item.getImage());
        return "redirect:/list";
    }

    @DeleteMapping("/del")
    ResponseEntity<Object> del(@RequestBody Map<String, String> body) {
        itemService.deleteItem(body.get("id"));
        return ResponseEntity.ok().build();

    }

    @GetMapping("presigned-url")
    @ResponseBody
    String getURL(@RequestParam String filename) {
        var result = s3Service.createPresignedUrl("test/" + filename);
        return result;
    }

    @PostMapping("/search")
    String search(@RequestBody @RequestParam String keyword, Model model) {
        itemRepository.findAllByNameContains(keyword);
        return "redirect:/list";
    }

}
