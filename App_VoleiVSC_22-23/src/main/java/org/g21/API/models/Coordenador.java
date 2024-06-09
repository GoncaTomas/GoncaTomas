package org.g21.API.models;

import jakarta.persistence.*;
//import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
//@Builder
@NoArgsConstructor
@Table(name = "coordenador")
public class Coordenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCoordenador;
    private String equipa;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public User getUser() {
      return user;
  }

  public void setUser(User user) {
      this.user = user;
  }
  
  public Coordenador(String equipa, User user) {
      this.equipa = equipa;
      this.user = user;
  }
}
