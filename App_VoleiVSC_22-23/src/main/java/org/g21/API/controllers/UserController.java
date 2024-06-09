package org.g21.API.controllers;

import org.g21.API.models.Atletas;
import org.g21.API.models.Coordenador;
import org.g21.API.models.EncEduc;
import org.g21.API.models.EqTec;
import org.g21.API.models.User;
import org.g21.API.models.UserDto;
import org.g21.API.repositories.AtletasRepository;
import org.g21.API.repositories.EqTecRepository;
import org.g21.API.repositories.EncEducRepository;
import org.g21.API.repositories.UserRepository;
import org.g21.API.repositories.CoordenadorRepository;
import org.g21.API.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;



@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AtletasRepository atletaRepository;

    @Autowired
    private EqTecRepository eqtecRepository;


    @Autowired
    private CoordenadorRepository coordenadorRepository;

    @Autowired
    private EncEducRepository encEducRepository;


    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String verificarLogin(Authentication authentication, // O objeto Authentication é preenchido pelo framework com as informações de autenticação fornecidas
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 HttpSession session) {         //HttpSession para que o user permaneça autenticado em diferentes partes da app

        User user = userService.verificarLogin(username, password);

        if (user != null) {
            session.setAttribute("idUser", user.getIdUser());
            session.setAttribute("role", user.getRole());
            return "redirect:/home";
        }

        return "login";
    }


    @GetMapping("/perfil")
    public String verPerfil(HttpSession session, Model model) {
        Integer idUser = (Integer) session.getAttribute("idUser");
        User user = userRepository.findById(idUser).orElse(null);
        model.addAttribute("user", user);
        return "perfil";
    }

    @GetMapping("/perfil/editarperfil")public String exibirFormularioEdicao(Model model) {
        
        return "editarperfil";
    }

    @PostMapping("/perfil/editarperfil")
    public String editarperfil(@ModelAttribute("user") User user, HttpSession session) {
        Integer idUser = (Integer) session.getAttribute("idUser");
        User existingUser = userRepository.findById(idUser).orElse(null);

        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            existingUser.setTelefone(user.getTelefone());

            userRepository.save(existingUser); // Salvar as alterações na base de dados

            return "redirect:/perfil";
        }

        return "erro";
    }


    @GetMapping("/criarUser")
    public String criarUser(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");

        if ("Admin".equals(role)) {
            // adicionar um novo objeto UserDto ao modelo
            model.addAttribute("userDto", new UserDto());
            return "criarUser";
        }else{
            return "criarUser2";
        }    
    }

    @PostMapping("/criarUser")
    public String criarUser(@ModelAttribute("userDto") UserDto userDto) {

        // criar um novo objeto User e salva-o no repositório de users
        User user = new User(userDto.getDataNasc(), userDto.getUsername(), userDto.getPassword(), userDto.getEmail(), userDto.getTelefone(), userDto.getRole());
        userRepository.save(user);

        // verificar se o role é "A" e criar um novo objeto Atleta e salva-o no repositório de Atletas
        if (userDto.getRole().equals("A")) {
            Atletas atleta = new Atletas(null, 0, 0, 0, 0, 0, 0, 0, user);
            atletaRepository.save(atleta);
        }

        if (userDto.getRole().equals("T")) {
            EqTec eqtec = new EqTec(null, 0, user);
            eqtecRepository.save(eqtec);
        }

        if (userDto.getRole().equals("C")) {
            Coordenador coordenador = new Coordenador(null, user);
            coordenadorRepository.save(coordenador);
        }

        if (userDto.getRole().equals("E")) {
            EncEduc encEduc = new EncEduc(null, user);
            encEducRepository.save(encEduc);
        }


        // redirecionar para a página inicial
        return "/home";
}
}
