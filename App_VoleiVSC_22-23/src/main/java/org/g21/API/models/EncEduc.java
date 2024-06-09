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
@Table(name = "encEduc")

public class EncEduc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEncEduc;
    private String nomeFilho;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public EncEduc(String nomeFilho, User user) {
        this.nomeFilho = nomeFilho;
        this.user = user;
    }
}
