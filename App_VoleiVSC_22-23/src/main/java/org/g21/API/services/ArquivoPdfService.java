package org.g21.API.services;

import java.io.IOException;
import java.io.InputStream;
//import java.io.InputStream;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.g21.API.models.ArquivoPdf;
import org.g21.API.repositories.ArquivoPdfRepository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

//import java.util.Optional;


@Service
public class ArquivoPdfService {

    private ArquivoPdfRepository arquivopdfRepository;
  
    public ArquivoPdfService(ArquivoPdfRepository arquivopdfRepository) {
        this.arquivopdfRepository = arquivopdfRepository;
    }

    public List<ArquivoPdf> getAllPdfs() {
        return arquivopdfRepository.findAll();
    }

    public ArquivoPdf getPdfById(Long id) {
        return arquivopdfRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ArquivoPdf com id " + id + " não encontrado"));
    }
        

    public void savePdf(ArquivoPdf arquivoPdf) {
        arquivopdfRepository.save(arquivoPdf);
    }

    public void deletePdfById(Long id) {
        arquivopdfRepository.deleteById(id);
    }

    public String readPdf(InputStream inputStream) {
        PDDocument document = null;
        try {
            document = PDDocument.load(inputStream);
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<ArquivoPdf> listarArquivos() {
        return arquivopdfRepository.findAll();
    }

}

