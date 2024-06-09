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
@Table(name = "eqTec")
public class EqTec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEqTec;
    private String escalaoAtuacao;
    private int mesesExperiencia;
    @ManyToOne
    @JoinColumn(name = "id_user")

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public EqTec(String escalaoAtuacao, int mesesExperiencia, User user) {
        this.escalaoAtuacao = escalaoAtuacao;
        this.mesesExperiencia = mesesExperiencia;
        this.user = user;
    }
}
