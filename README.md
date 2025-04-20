# P5
Aplicación web que usa Spring JPA para persistir los datos de un API REST de gestión de usuarios.
El API permite el registro de nuevos usuarios y su identificación mediante email y password.
Una vez identificados, se emplea una cookie de sesión para autenticar las peticiones que permiten 
a los usuarios leer, modificar y borrar sus datos. También existe un endpoint para cerrar la sesión.  

## Endpoints

// TODO#1: rellena la tabla siguiente analizando el código del proyecto

| Método | Ruta                        | Descripción                                 | Respuestas                       |
|--------|-----------------------------|---------------------------------------------|----------------------------------|
| POST   | /api/users                  | Registro de nuevo usuario                   | 201 Created / 400 Bad Request    |
| POST   | /api/users/me/session       | Login del usuario                           | 201 Created (Token) / 400        |
| GET    | /api/users/me               | Consultar perfil del usuario autenticado    | 200 OK / 401 Unauthorized        |
| PUT    | /api/users/me               | Actualizar perfil del usuario               | 200 OK / 400 / 401 Unauthorized  |
| DELETE | /api/users/me               | Eliminar cuenta del usuario                 | 204 No Content / 401 Unauthorized|
| DELETE | /api/users/me/session       | Logout del usuario                          | 204 No Content / 401 Unauthorized|

Sé que en esta práctica hay bastante código comentado, pero no es porque el código no se entienda. Son anotaciones que he ido dejando para poder repasar todo bien de cara al examen final. Me ha parecido útil sobreexplicar cada parte para tenerlo como guía completa.
Espero que esto no afecte a la nota por tema de estética, ya que en esta práctica no he visto que se indicara que hubiese que evitarlo (como sí se comentó en la anterior).

## Comandos 

- Construcción: 
  ```sh
  ./mvnw clean package
  ```

- Ejecución: 
  ```sh
  ./mvnw spring-boot:run
  ```

- Tests:
  ```sh
  ./mvnw test
  ```
