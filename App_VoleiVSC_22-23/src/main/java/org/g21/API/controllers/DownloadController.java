package org.g21.API.controllers;

import org.g21.API.models.ArquivoPdf;
import org.g21.API.repositories.ArquivoPdfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class DownloadController {

    @Autowired
    private ArquivoPdfRepository arquivoPdfRepository;

    @GetMapping("/download/{id}")
    public void downloadArquivo(@PathVariable("id") Long id, HttpServletResponse response) {
        try {
            // Procura o arquivo PDF na base de dados pelo ID
            ArquivoPdf arquivoPdf = arquivoPdfRepository.findById(id).orElse(null);

            if (arquivoPdf == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            // Configura a resposta HTTP para fazer o download do arquivo
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + arquivoPdf.getNome() + "\"");
            response.setContentLength(arquivoPdf.getConteudo().length);
            response.getOutputStream().write(arquivoPdf.getConteudo());
            response.getOutputStream().flush();
        } catch (IOException e) {
            // Tratamento de erros
            e.printStackTrace();
        }
    }
}

