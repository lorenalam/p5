package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class RepositoryIntegrationTest {
    @Autowired TokenRepository tokenRepository;
    @Autowired AppUserRepository appUserRepository;

    /**
     * TODO#9
     * Completa este test de integración para que verifique
     * que los repositorios TokenRepository y AppUserRepository guardan
     * los datos correctamente, y las consultas por AppToken y por email
     * definidas respectivamente en ellos retornan el token y usuario guardados.
     */
    @Test
    void saveTest() {
        // Given: un usuario válido
        AppUser user = new AppUser();
        user.email = "mario@comillas.com";
        user.password = "Password17";
        user.role = Role.USER;
        user.name = "Mario";
        appUserRepository.save(user);

        // When: lo buscamos por email
        Optional<AppUser> foundUser = appUserRepository.findByEmail("mario@comillas.com");

        // Then: compruebos que se ha guardado bien
        assertTrue(foundUser.isPresent());
        assertEquals("Mario", foundUser.get().name);

        // Given: un token vinculado a ese usuario
        Token token = new Token();
        token.appUser = user;
        tokenRepository.save(token);

        // When: lo buscamos por AppUser
        Optional<Token> foundToken = tokenRepository.findByAppUser(user);

        // Then: comprobamos que también se ha guardado bien
        assertTrue(foundToken.isPresent());
        assertEquals(user.email, foundToken.get().appUser.email);
    }

    /**
     * TODO#10
     * Completa este test de integración para que verifique que
     * cuando se borra un usuario, automáticamente se borran sus tokens asociados.
     */
    @Test
    void deleteCascadeTest() {
        // Given: un usuario con token
        AppUser user = new AppUser();
        user.email = "borrar@comillas.com";
        user.password = "Password17";
        user.role = Role.USER;
        user.name = "Borrar";
        appUserRepository.save(user);

        Token token = new Token();
        token.appUser = user;
        tokenRepository.save(token);

        // When: eliminamos al usuario
        appUserRepository.delete(user);

        // Then: comprobamos que el usuario ha sido borrado
        assertEquals(0, appUserRepository.count());
    }

}