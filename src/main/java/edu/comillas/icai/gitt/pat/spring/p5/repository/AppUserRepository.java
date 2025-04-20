package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * TODO#4
 * Crea el repositorio para la entidad AppUser de modo que,
 * además de las operaciones CRUD, se pueda consultar el AppUser asociado
 * a un email dado
 */

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
/* metodo para consultar usuario (toda la info del usuario) buscando por email*/

    Optional<AppUser> findByEmail(String email); /* Optional por si no encuentra un appUser con ese email para que no devuelva null y pete*/
                                                /* Devuelve un objeto java AppUser, cuando se implemente en la api devolvera un json*/
}