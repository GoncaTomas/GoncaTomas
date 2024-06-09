package org.g21.API.controllers;

import org.g21.API.models.ArquivoPdf;
import org.g21.API.repositories.ArquivoPdfRepository;
import org.g21.API.services.ArquivoPdfService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.g21.API.services.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//import java.io.File;
import java.io.IOException;
import java.util.Date;
//import java.util.List;
import java.util.Optional;

@Controller
public class UploadController {

   
  // private RelatorioService relatorioService;

    @Autowired
    private ArquivoPdfRepository arquivopdfRepository;

    @Autowired
    private ArquivoPdfService arquivopdfService;


    @GetMapping("/relatorio/uploadrelatorio")
    public String viewFile(@RequestParam(name = "idRelatorio", required = false) Long id, Model model) {
        Optional<ArquivoPdf> optionalRelatorio = arquivopdfRepository.findById(id);
            if (optionalRelatorio.isPresent()) {
                ArquivoPdf arquivoPdf = optionalRelatorio.get();
                byte[] arquivoBytes = arquivoPdf.getConteudo();
            if (arquivoBytes != null) {
                // Passa o arquivo em bytes para a view
                model.addAttribute("arquivoBytes", arquivoBytes);
                return "verrelatorio";
            } else {
                // Trata o caso em que o arquivo não existe ou ocorreu algum erro
                model.addAttribute("errorMessage", "O relatório solicitado não foi encontrado.");
                return "erro";
            }
            } else {
                model.addAttribute("errorMessage", "O relatório solicitado não foi encontrado.");
            return "erro";
        }
    }

    @PostMapping("/relatorio/uploadrelatorio")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
        // Converte o arquivo numa matriz de bytes
            byte[] arquivoBytes = file.getBytes();
        // Cria um novo relatório na base de dados com o nome e o arquivo em bytes
            ArquivoPdf arquivoPdf = new ArquivoPdf();
            arquivoPdf.setNome(file.getOriginalFilename());
            arquivoPdf.setConteudo(arquivoBytes);
            arquivoPdf.setDataUpload(new Date());
            arquivopdfRepository.save(arquivoPdf);
            String text = arquivopdfService.readPdf(file.getInputStream());
            return ResponseEntity.ok(text);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
