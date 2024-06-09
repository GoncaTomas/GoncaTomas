package org.g21.API.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.g21.API.services.ArquivoPdfService;
import org.g21.API.models.ArquivoPdf;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;


@Controller
public class RelatorioController {

    @Autowired
    ArquivoPdfService arquivoPdfService;

 

    @GetMapping("/relatorio")
    public String exibirPaginaRelatorios(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");

        if ("Admin".equals(role) || "C".equals(role) || "T".equals(role)) {
            model.addAttribute("uploadLink", "/relatorio/uploadrelatorio");
            model.addAttribute("visualizarLink", "/relatorio/verrelatorio");
            return "relatorio";
        }else{
            model.addAttribute("visualizarLink", "/relatorio/verrelatorio");
            return "relatorio2";
        }    
    }

    @GetMapping("/relatorio/verrelatorio")
    public String exibirRelatorios(Model model) {
        List<ArquivoPdf> arquivos = arquivoPdfService.listarArquivos();
        model.addAttribute("arquivos", arquivos);
        return "verrelatorio";
    }

    @PostMapping("/comentarios")
    public String exibirPaginaComentarios(Model model) {
        return "comentarios";
    }
}
