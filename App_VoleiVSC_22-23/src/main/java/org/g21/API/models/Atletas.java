package org.g21.API.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "atleta")
public class Atletas{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAtleta;
    private String posicao;
    @Column(columnDefinition = "double default 0")
    private double peso;
    @Column(columnDefinition = "double default 0")
    private double altura;
    @Column(columnDefinition = "double default 0")
    private double percentMassaMuscular;
    @Column(columnDefinition = "double default 0")
    private double percentMassaGorda;
    @Column(columnDefinition = "double default 0")
    private double imc;
    @Column(columnDefinition = "double default 0")
    private double impulsao;
    @Column(columnDefinition = "double default 0")
    private double forca;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Atletas(String posicao, double peso, double altura, double percentMassaMuscular, double percentMassaGorda, double imc, double impulsao, double forca, User user) {
        this.posicao = posicao;
        this.peso = peso;
        this.altura = altura;
        this.percentMassaMuscular = percentMassaMuscular;
        this.percentMassaGorda = percentMassaGorda;
        this.imc = imc;
        this.impulsao = impulsao;
        this.forca = forca;
        this.user = user;
    }
    
}


