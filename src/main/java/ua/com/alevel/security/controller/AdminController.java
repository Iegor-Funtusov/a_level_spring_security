package ua.com.alevel.security.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.alevel.security.service.MessageService;

@Controller
@AllArgsConstructor
public class AdminController {

    private final MessageService messageService;

    @GetMapping("/admin/home")
    public String home(Model model) {
        model.addAttribute("msgs", messageService.getAll());
        return "adminhome";
    }
}
