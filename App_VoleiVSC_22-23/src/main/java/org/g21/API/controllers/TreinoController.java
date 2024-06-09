package org.g21.API.controllers;

import org.g21.API.models.Treino;
import org.g21.API.services.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import jakarta.servlet.http.HttpSession;

@Controller
public class TreinoController {

    @Autowired
    TreinoService treinoService;


    @GetMapping("/treino")
    public String listarTreinos(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");

        if ("Admin".equals(role) || "C".equals(role)) {
            List<Treino> treinos = treinoService.listarTreinos();
            model.addAttribute("treino", treinos);
            return "treino";
        }else{
            List<Treino> treinos = treinoService.listarTreinos();
            model.addAttribute("treino", treinos);
            return "treino2";
        }    
    }

    @GetMapping("/treino/criartreino")
    public String criacaoTreino(Model model) {
        return "criartreino";
    }

    @PostMapping("/treino/criartreino")
    public String criarTreino(@RequestParam("datahora") String datahora,
                              @RequestParam("nome_equipa") String nome_equipa,
                              @RequestParam("nome_treinador") String nome_treinador,
                              Model model) {

        Treino novoTreino = new Treino();
        novoTreino.setDatahora(datahora);
        novoTreino.setNome_equipa(nome_equipa);
        novoTreino.setNome_treinador(nome_treinador);

        treinoService.criarTreino(novoTreino);
        model.addAttribute("treino", novoTreino);

        return "redirect:/treino";
    }


    @GetMapping("/treino/editartreino")
    public String editarTreino(Model model) {
        return "editartreino";
    }


    @PostMapping("/treino/editartreino")
    public String editarTreino(@RequestParam("cod_treino") int cod_treino,
                               @RequestParam("datahora") String datahora,
                               @RequestParam("nome_equipa") String nome_equipa,
                               @RequestParam("nome_treinador") String nome_treinador,
                                Model model) throws Exception {

        Treino treino = treinoService.getTreinoById(cod_treino);
        if (treino == null) {
            return "erro";
        }
        
        treino.setDatahora(datahora);
        treino.setNome_equipa(nome_equipa);
        treino.setNome_treinador(nome_treinador);
        
        treinoService.atualizarTreino(treino);
        model.addAttribute("treino", treino);

        return "redirect:/treino";
    }


    @GetMapping("/treino/removertreino")
    public String removerTreino(Model model) {
        return "removertreino";
    }

    @PostMapping("/treino/removertreino")
    public String removerTreino(@RequestParam("cod_treino") int id) {
        treinoService.removerTreino(id);
        return "redirect:/treino";
    }
}

