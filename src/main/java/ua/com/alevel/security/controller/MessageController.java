package ua.com.alevel.security.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.alevel.security.persistence.entity.Message;
import ua.com.alevel.security.service.MessageService;

@Controller
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("msgs", messageService.getAllByUser());
        return "userhome";
    }

    @PostMapping("/messages")
    public String saveMessage(Message message) {
        messageService.add(message);
        return "redirect:/home";
    }
}
