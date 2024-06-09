package org.g21.API.controllers;

import org.g21.API.models.EqTec;
import org.g21.API.services.EqTecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.servlet.http.HttpSession;

@Controller
public class EqTecController {

    @Autowired
    EqTecService eqTecService;

    @GetMapping("/equipaTecnica")
    public String listaEqTec(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");

        if ("Admin".equals(role) || "C".equals(role)) {
            List<EqTec> listaEqTec = eqTecService.getListaEqTec();
            model.addAttribute("eqTec", listaEqTec);
            return "equipaTecnica";
        }else{
            List<EqTec> listaEqTec = eqTecService.getListaEqTec();
            model.addAttribute("eqTec", listaEqTec);
            return "equipaTecnica2";
        }        
    }

    @GetMapping("/equipaTecnica/editarequipaTecnica")
    public String criacaoEqTec(Model model) {
        return "editarequipaTecnica";
    }

    @PostMapping("/equipaTecnica/editarequipaTecnica")
    public String criarEqTec(@RequestParam("idEqTec") int idEqTec,
                             @RequestParam("escalaoAtuacao") String escalaoAtuacao,
                             @RequestParam("mesesExperiencia") int mesesExperiencia,
                             Model model) {

        EqTec eqTec = eqTecService.getEqTecById(idEqTec); // Procura o profissional pelo ID
            if (eqTec == null) {
            // Se o não foi encontrado
                return "erro";
            }  
            

        // Atualiza os dados    
        eqTec.setEscalaoAtuacao(escalaoAtuacao);
        eqTec.setMesesExperiencia(mesesExperiencia);


        eqTecService.atualizareqTec(eqTec); // Chama o método do serviço para atualizar o profissional na base de dados
        return "redirect:/equipaTecnica";
    }



    @GetMapping("/equipaTecnica/removerequipaTecnica")
    public String removerEqTec(Model model) {
        return "removerequipaTecnica";
    }


    @PostMapping("/equipaTecnica/removerequipaTecnica")
    public String removerEqTec(@RequestParam("id") int id, Model model) {
        eqTecService.deleteEqTecById(id);        
        model.addAttribute("mensagem", "Atleta removido com sucesso!");
        return "redirect:/equipaTecnica";
    }

}

