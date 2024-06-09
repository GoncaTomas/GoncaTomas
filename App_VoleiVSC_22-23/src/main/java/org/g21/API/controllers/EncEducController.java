package org.g21.API.controllers;


import org.g21.API.models.EncEduc;
import org.g21.API.services.EncEducService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class EncEducController {
    @Autowired
    EncEducService encEducService;

    @GetMapping("/encEduc")
    public String listaEncEduc(Model model) {
        List<EncEduc> listaEncEduc = EncEducService.getListaEncEduc();
        model.addAttribute("encEduc", listaEncEduc);
        return "encEduc";
    }

    @GetMapping("/encEduc/editarencEduc")
    public String editarEncEduc(Model model) {
        return "editarencEduc";
    }

    @PostMapping("/encEduc/editarencEduc")
    public String editarEncEduc(@RequestParam("idEncEduc") int idEncEduc,
                                   @RequestParam("nomeFilho") String nomeFilho,
                                   Model model) {
        EncEduc encEduc = encEducService.getEncEduc(idEncEduc); // Procura pelo ID
            if (encEduc == null) {
                return "erro";
            }                            
        
        encEduc.setNomeFilho(nomeFilho);

        encEducService.atualizarEncEduc(encEduc);

        return "redirect:/encEduc";
    }

    @GetMapping("/encEduc/removerencEduc")
    public String removerEncEduc(Model model) {
        return "removerencEduc";
    }


    @PostMapping("/encEduc/removerencEduc")
    public String removerEncEduc(@RequestParam("id") int id, Model model) {
        encEducService.deleteEncEducById(id);        
        model.addAttribute("mensagem", "Encarregado de Educação removido com sucesso!");
        return "redirect:/encEduc";
    }

}



