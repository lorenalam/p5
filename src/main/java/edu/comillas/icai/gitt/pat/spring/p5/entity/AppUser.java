package edu.comillas.icai.gitt.pat.spring.p5.entity;

import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import jakarta.persistence.*;

/**
 * TODO#2
 * Completa la entidad AppUser (cuya tabla en BD se llamará APP_USER)
 * para que tenga los siguientes campos obligatorios:
 * - id, que será la clave primaria numérica y autogenerada
 * - email, que debe tener valores únicos en toda la tabla
 * - password
 * - role, modelado con la clase Role ya existente en el proyecto
 * - name
 * Genera
 */

@Entity
//@Table(name="APP_USER")

/*Variables public o private??*/

public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    public Long id; /*Indicamos que va a ser el id (clave primaria) y que el valor se genera automaticamente (con la estrategia de que se autoincremente)*/

    @Column(nullable = false, unique=true)
    public String email; /*Indicamos que la informacion que metemos en ese campo (columna) en la DB sera no nula y unica */

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    public Role role; /* un rol de la clase Rol que es un enum (como una lista)*/

    @Column(nullable = false)
    public String name;

    /*Getters y setters autogenerados con intelij */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}