# Spring Boot Starter Security

## ¿Qué es spring-boot-starter-security?

`spring-boot-starter-security` es una dependencia de Spring Boot que integra el módulo Spring Security en tu aplicación. Spring Security proporciona autenticación, autorización y otras características de seguridad para aplicaciones Java, facilitando la protección de endpoints y la gestión de usuarios.

## ¿Para qué sirve?

- Proteger endpoints REST y web.
- Configurar autenticación y autorización de usuarios.
- Implementar seguridad basada en roles.
- Prevenir ataques comunes (CSRF, XSS, etc).

## Ejemplo básico de uso

Al agregar la dependencia en tu `pom.xml`, todos los endpoints quedan protegidos por defecto. Spring Boot genera una página de login y un usuario por defecto:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

Al iniciar la aplicación, verás en la consola una contraseña generada automáticamente para el usuario `user`.

## Configuración en application.properties

Puedes definir usuarios y roles en el archivo `application.properties`:

```properties
spring.security.user.name=usuario
spring.security.user.password=contraseña
spring.security.user.roles=USER
```

## Seguridad en los endpoints de Actuator

Cuando agregas `spring-boot-starter-security` a tu proyecto, los endpoints de Actuator quedan protegidos por defecto y requieren autenticación. Por ejemplo, endpoints como `/actuator/metrics`, `/actuator/env`, `/actuator/beans`, etc., solo pueden ser accedidos por usuarios autenticados. Sin embargo, `/actuator/health` y `/actuator/info` suelen estar permitidos sin autenticación, aunque esto puede personalizarse.

### Ejemplo de configuración para permitir acceso público a ciertos endpoints

Puedes personalizar el acceso a los endpoints de Actuator en tu clase de configuración de seguridad:

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/actuator/health", "/actuator/info").permitAll()
            .requestMatchers("/actuator/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .formLogin();
    return http.build();
}
```

## Recursos útiles
- [Documentación oficial](https://docs.spring.io/spring-boot/docs/current/reference/html/application-security.html)
- [Guía de Spring Security](https://spring.io/projects/spring-security)
