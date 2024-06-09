package org.g21.API.models;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    private String dataNasc;
    private String username;
    private String password;
    private String email;
    private int idade;
    private int telefone;
    private String role;

}