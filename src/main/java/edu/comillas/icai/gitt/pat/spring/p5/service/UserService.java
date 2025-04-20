package edu.comillas.icai.gitt.pat.spring.p5.service;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileRequest;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileResponse;
import edu.comillas.icai.gitt.pat.spring.p5.model.RegisterRequest;
import edu.comillas.icai.gitt.pat.spring.p5.repository.TokenRepository;
import edu.comillas.icai.gitt.pat.spring.p5.repository.AppUserRepository;
import edu.comillas.icai.gitt.pat.spring.p5.util.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * TODO#6
 * Completa los métodos del servicio para que cumplan con el contrato
 * especificado en el interface UserServiceInterface, utilizando
 * los repositorios y entidades creados anteriormente
 */

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    TokenRepository tokenRepository;

   Hashing hashing= new Hashing();

    public Token login(String email, String password) {

        Optional<AppUser> appUser = appUserRepository.findByEmail(email);

        if (appUser.isPresent()) {
            AppUser user = appUser.get();

            if (hashing.compare(user.password,password)) {
                Token token = new Token();
                token.appUser = user;
                return tokenRepository.save(token);
            }
        }
        return null; // si no hay usuario o la password no coincide
    }

    public AppUser authentication(String tokenId) {
       Optional<Token> token= tokenRepository.findById(tokenId);
        if (token.isPresent()) { /* si el token ya existe (no es nulo) devuélveme el usuario*/
            return token.get().appUser;
        }else {
            return null;
        }
    }

    public ProfileResponse profile(AppUser appUser) { /*nos llega un objeto tipo usuario y queremos devolver la info del usuario en un objeto profile response*/
        ProfileResponse profileResponse= new ProfileResponse(appUser.name,appUser.email,appUser.role); /*al que le pasamos la info en los atributos del constructor*/
        return profileResponse; /*el record profile response esta definido en la carpeta model!!*/
    }


    public ProfileResponse profile(AppUser appUser, ProfileRequest profile) { /*nos llega un objeto tipo PROFILE!! para actualizar y queremos devolver la info nueva igual que antes, pero habiendola guardado antes en la DB y eso solo se puede hacer con un objeto appuser*/
        appUser.name = profile.name();  /*hacemos que los nuevos datos que nos llegan del profile request se metan en el profile(datos definitivos)*/
        appUser.role = profile.role();
        appUser.password = profile.password();

        appUserRepository.save(appUser); /*Importante no olvidarnos de guardar el usuario nuevo en la base de datos*/

/* En vez de volver a repetir codigo como este podemos usar el primer metodo profile que hemos definido!!*/
//        ProfileResponse profileResponse= new ProfileResponse(appUser.name,appUser.email,appUser.role);
//
//        return profileResponse;
        return profile(appUser);
    }

    public ProfileResponse profile(RegisterRequest register) { /* queremos registrar un nuevo usuario en la base de datos, los datos nos vienen en forma de objeto RegisterRequest y los copiamos a un appuser que sera guardado en la base de datos*/
        AppUser appUser= new AppUser();

        appUser.name = register.name();
        appUser.email = register.email();
        appUser.role = register.role();
        appUser.password = hashing.hash(register.password());
        appUserRepository.save(appUser);    /*guardamos nuevo usuario*/


        return profile(appUser); /*acordarse de devolver ProfileResponse y no appuser!!!*/
    }


    public void logout(String tokenId) {    /*eliminamos SOLO el token de un usuario ya que se ha salido de la pagina cerrando sesión (no se borra nada de la DB de appuser, solo de token), cuando vuelva a entrar se le asignara otro token de sesion */
        tokenRepository.deleteById(tokenId);
    }

    public void delete(AppUser appUser) {
      appUserRepository.delete(appUser);  /* no hacemos deleteby porque queremos borrar el usuario entero, antes hemos configurado el borrado en cascada por lo que si la sesión seguía activa se eliminara su token también*/
    }

}
