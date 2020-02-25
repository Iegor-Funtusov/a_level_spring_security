package ua.com.alevel.security.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import ua.com.alevel.security.persistence.entity.Message;
import ua.com.alevel.security.persistence.repository.UserTokenRepository;
import ua.com.alevel.security.service.MessageService;
import ua.com.alevel.security.util.SecurityUtil;

@Controller
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserTokenRepository userTokenRepository;

    @GetMapping("/home")
    public String home(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", userTokenRepository.findByUserId(SecurityUtil.getUserId()).getToken());
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<Message[]> response = restTemplate.exchange(
                "http://localhost:8081/message/all/users",
                HttpMethod.GET,
                entity,
                Message[].class);

        model.addAttribute("msgs", response.getBody());
        return "userhome";
    }

    @PostMapping("/messages")
    public String saveMessage(Message message) {
        messageService.add(message);
        return "redirect:/home";
    }
}
