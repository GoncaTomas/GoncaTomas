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
@Table(name = "user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    private String dataNasc;
    private String username;
    private String password;
    private String email;
    private int telefone;
    private String role;

    public User(String dataNasc, String username, String password, String email, int telefone, String role) {
        this.dataNasc = dataNasc;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefone = telefone;
        this.role = role;
    }
}    

