package org.g21.API.controllers;

import org.g21.API.models.Message;
import org.g21.API.models.User;
import org.g21.API.repositories.MessageRepository;
import org.g21.API.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/chat")
    public String chatPage(HttpSession session, Model model) {
        Integer idUser = (Integer) session.getAttribute("idUser");
        User user = userRepository.findById(idUser).orElse(null);
        model.addAttribute("user", user);
        
        // Recupera as mensagens da base de dados
        List<Message> messages = messageRepository.findAll();
        
        // Adiciona as mensagens ao modelo
        model.addAttribute("messages", messages);
        
        return "chat";
    }
    

    @PostMapping("/chat")
    public String enviarMensagem(HttpSession session,
                             @RequestParam("conteudo_mensagem") String conteudoMensagem) {
    Integer idUser = (Integer) session.getAttribute("idUser");
    User user = userRepository.findById(idUser).orElse(null);

    if (user != null) {
        Message message = new Message();
        message.setNome_user(user.getUsername());
        message.setConteudo_mensagem(conteudoMensagem);
        message.setTimestamp(LocalDateTime.now());

        messageRepository.save(message);
    }

    return "redirect:/chat";
}


}



