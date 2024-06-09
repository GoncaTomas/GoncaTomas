package org.g21.API.controllers;

import org.g21.API.models.Atletas;
import org.g21.API.services.AtletasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;


@Controller
public class AtletasController {

    @Autowired
    AtletasService atletasService;

    @GetMapping("/atletas")
    public String listaAtletas(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");

        if ("Admin".equals(role) || "C".equals(role)) {
            List<Atletas> listaAtletas = atletasService.getListaAtletas();
            model.addAttribute("atletas", listaAtletas);
            return "atletas";
        } else {
            List<Atletas> listaAtletas = atletasService.getListaAtletas();
            model.addAttribute("atletas", listaAtletas);
            return "atletas2";
        }
    }

    
    @GetMapping("/atletas/editaratleta")
    public String editarAtleta(Model model) {
        return "editaratleta";
    }
    @PostMapping("/atletas/editaratleta")
    public String editarAtleta(@RequestParam("idAtleta") int idAtleta,
                               @RequestParam("posicao") String posicao,
                               @RequestParam("peso") double peso,
                               @RequestParam("altura") double altura,
                               @RequestParam("percentMassaMuscular") double percentMassaMuscular,
                               @RequestParam("percentMassaGorda") double percentMassaGorda,
                               @RequestParam("imc") double imc,
                               @RequestParam("impulsao") double impulsao,
                               @RequestParam("forca") double forca,
                               Model model) throws Exception {

    Atletas atleta = atletasService.getAtletaById(idAtleta); // Procura o atleta pelo ID
    if (atleta == null) {
        // Se o atleta não foi encontrado
        return "erro";
    }

    // Atualiza os dados do atleta
        atleta.setPosicao(posicao);
        atleta.setPeso(peso);
        atleta.setAltura(altura);
        atleta.setPercentMassaMuscular(percentMassaMuscular);
        atleta.setPercentMassaGorda(percentMassaGorda);
        atleta.setImc(imc);
        atleta.setImpulsao(impulsao);
        atleta.setForca(forca);

        atletasService.atualizarAtleta(atleta); // Chama o método do serviço para atualizar o atleta na base de dados
        return "redirect:/atletas";
    }


    @GetMapping("/atletas/removeratleta")
    public String removerAtleta(Model model) {
        return "removeratleta";
    }


    @PostMapping("/atletas/removeratleta")
    public String removerAtleta(@RequestParam("id") int id, Model model) {
        atletasService.deleteAtletaById(id);        
        model.addAttribute("mensagem", "Atleta removido com sucesso!");
        return "redirect:/atletas";
    }

}
