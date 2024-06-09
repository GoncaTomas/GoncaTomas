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
@Table(name = "treino")

public class Treino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cod_treino;
    private String datahora;
    private String nome_equipa;
    private String nome_treinador;
}
