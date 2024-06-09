package org.g21.API.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.g21.API.models.ArquivoPdf;
import org.g21.API.repositories.ArquivoPdfRepository;

@Controller
public class VerArquivoController {

    @Autowired
    private ArquivoPdfRepository arquivoPdfRepository; // Repositório para acesso à base de dados

    @GetMapping("relatorio/verrelatorio/{id}")
    public void visualizarPdf(@PathVariable("id") Long id, HttpServletResponse response) {
        try {
            // Busca o arquivo PDF na base de dados pelo ID
            ArquivoPdf arquivoPdf = arquivoPdfRepository.findById(id).orElse(null);

            if (arquivoPdf == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            // Configura a resposta HTTP para mostrar o arquivo PDF no navegador
            response.setContentType("application/pdf");
            response.setContentLength(arquivoPdf.getConteudo().length);
            response.getOutputStream().write(arquivoPdf.getConteudo());
            response.getOutputStream().flush();
        } catch (IOException e) {
            // Tratamento de erros
            e.printStackTrace();
        }
    }
}

