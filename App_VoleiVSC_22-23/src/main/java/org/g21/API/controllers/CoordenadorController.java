package org.g21.API.controllers;

import org.g21.API.models.Coordenador;
import org.g21.API.services.CoordenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class CoordenadorController {

    @Autowired
    CoordenadorService coordenadorService;

    @GetMapping("/coordenador")
    public String listaCoordenador(Model model) {
        List<Coordenador> listaCoordenador = coordenadorService.getListaCoordenador();
        model.addAttribute("coordenador", listaCoordenador);
        return "coordenador";
    }


    @GetMapping("/coordenador/editarcoordenador")
    public String editarCoordenador(Model model) {
        return "editarcoordenador";
    }

    @PostMapping("/coordenador/editarcoordenador")
    public String criarCoordenador(@RequestParam("idCoordenador") int idCoordenador,
                                   @RequestParam("equipa") String equipa,
                                   Model model) {
        Coordenador coordenador = coordenadorService.getCoordenador(idCoordenador); // Procura pelo ID
            if (coordenador == null) {
                return "erro";
            }                            
        
        coordenador.setEquipa(equipa);

        coordenadorService.atualizarCoordenador(coordenador);

        return "redirect:/coordenador";
    }

    @GetMapping("/coordenador/removercoordenador")
    public String removerCoordenador(Model model) {
        return "removercoordenador";
    }


    @PostMapping("/coordenador/removercoordenador")
    public String removerCoordenador(@RequestParam("id") int id, Model model) {
        coordenadorService.deleteCoordenadorById(id);        
        model.addAttribute("mensagem", "Coordenador removido com sucesso!");
        return "redirect:/coordenador";
    }
}
