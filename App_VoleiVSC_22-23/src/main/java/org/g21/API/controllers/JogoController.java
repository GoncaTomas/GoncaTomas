package org.g21.API.controllers;

import org.g21.API.models.Jogo;
import org.g21.API.services.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import jakarta.servlet.http.HttpSession;

@Controller
public class JogoController {

    @Autowired
    JogoService jogoService;

    @GetMapping("/jogo")
    public String listarJogos(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");

        if ("Admin".equals(role) || "C".equals(role)) {
            List<Jogo> jogos = jogoService.listarJogos();
            model.addAttribute("jogo", jogos);
            return "jogo";
        }else{
            List<Jogo> jogos = jogoService.listarJogos();
            model.addAttribute("jogo", jogos);
            return "jogo2";
        }    
    }

    @GetMapping("/jogo/criarjogo")
    public String criacaoJogo(Model model) {
        return "criarjogo";
    }

    @PostMapping("/jogo/criarjogo")
    public String criarJogo(@RequestParam("datahora") String datahora,
                            @RequestParam("local") String local,
                            @RequestParam("equipaAdv") String equipaAdv,
                            Model model) {

        Jogo novoJogo = new Jogo();
        novoJogo.setDatahora(datahora);
        novoJogo.setLocal(local);
        novoJogo.setEquipaAdv(equipaAdv);

        jogoService.criarJogo(novoJogo);
        model.addAttribute("jogo");


        return "redirect:/jogo";
    }


    @GetMapping("/jogo/editarjogo")
    public String editarJogo(Model model) {
        return "editarjogo";
    }


    @PostMapping("/jogo/editarjogo")
    public String editarJogo(@RequestParam("idJogo") int idJogo,
                             @RequestParam("datahora") String datahora,
                             @RequestParam("local") String local,
                             @RequestParam("equipaAdv") String equipaAdv,

                             Model model) throws Exception {


        Jogo jogo = jogoService.getJogoById(idJogo);
        if (jogo == null) {
            return "erro";
        }
        
        jogo.setDatahora(datahora);
        jogo.setLocal(local);
        jogo.setEquipaAdv(equipaAdv);



                jogoService.atualizarJogo(jogo);
                model.addAttribute("jogo", jogo);

                return "redirect:/jogo";
            }


            @GetMapping("/jogo/removerjogo")
            public String removerJogo(Model model) {
                return "removerjogo";
            }

    @PostMapping("/jogo/removerjogo")
    public String removerJogo(@RequestParam("idJogo") int id) {
        jogoService.removerJogo(id);
        return "redirect:/jogo";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}
