package com.sbweb.freelecspringbootwebservice.web;

import com.sbweb.freelecspringbootwebservice.config.auth.LoginUser;
import com.sbweb.freelecspringbootwebservice.config.auth.dto.SessionUser;
import com.sbweb.freelecspringbootwebservice.service.posts.PostsService;
import com.sbweb.freelecspringbootwebservice.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        PostResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "update";
    }
}
