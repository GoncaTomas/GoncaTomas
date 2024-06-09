package org.g21.API.models;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "arquivos_pdf")
public class ArquivoPdf {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Lob
    private byte[] conteudo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUpload;

    public ArquivoPdf() {
    }

    public ArquivoPdf(String nome, byte[] conteudo) {
        this.nome = nome;
        this.conteudo = conteudo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }

    public Date getDataUpload() {
        return dataUpload;
    }
    
    public void setDataUpload(Date dataUpload) {
        this.dataUpload = dataUpload;
    }
  
}
