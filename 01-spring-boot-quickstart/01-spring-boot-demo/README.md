# ¿Qué es `spring-boot-starter-web`?

`spring-boot-starter-web` es una dependencia de Spring Boot que facilita la creación de aplicaciones web y servicios RESTful en Java. Incluye todo lo necesario para desarrollar aplicaciones web modernas de manera rápida y sencilla.

## ¿Qué incluye?

Al agregar `spring-boot-starter-web` a tu proyecto, se incluyen automáticamente las siguientes características y dependencias:

- **Spring MVC:** Framework para construir aplicaciones web y APIs REST.
- **Tomcat (por defecto):** Servidor embebido para ejecutar la aplicación sin necesidad de instalar un servidor externo.
- **Jackson:** Biblioteca para la serialización y deserialización de objetos Java a JSON y viceversa.
- **Validación:** Soporte para la validación de datos en las solicitudes web.
- **Herramientas de desarrollo:** Facilita el desarrollo y pruebas de endpoints web.

Esta dependencia es ideal para comenzar rápidamente con el desarrollo de aplicaciones web y microservicios en el ecosistema Spring Boot.

---

## ¿Qué es la anotación `@RestController`?

La anotación `@RestController` es una especialización de `@Controller` en Spring Boot que se utiliza para crear controladores REST. Al aplicar esta anotación a una clase, Spring la reconoce como un componente que gestiona solicitudes HTTP y devuelve datos directamente en formato JSON o XML, en lugar de renderizar vistas.

### ¿Qué hace exactamente?
- Marca la clase como un controlador REST.
- Combina la funcionalidad de `@Controller` y `@ResponseBody`, lo que significa que los métodos de la clase devuelven datos directamente en el cuerpo de la respuesta HTTP.
- Facilita la creación de APIs RESTful, ya que no es necesario agregar `@ResponseBody` a cada método.

### ¿Cómo se utiliza?

```java
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class EjemploController {
    @GetMapping("/saludo")
    public String saludo() {
        return "¡Hola desde Spring Boot!";
    }
}
```

En este ejemplo, cualquier solicitud GET a `/saludo` recibirá como respuesta el texto "¡Hola desde Spring Boot!" directamente en el cuerpo de la respuesta HTTP.

---

## ¿Qué es la anotación `@GetMapping`?

La anotación `@GetMapping` se utiliza en Spring Boot para asociar métodos de un controlador con solicitudes HTTP GET. Permite definir la URL que debe ser atendida por el método y facilita la creación de endpoints RESTful.

### ¿Qué hace exactamente?
- Indica que el método debe responder a solicitudes HTTP GET en la ruta especificada.
- Permite definir rutas, parámetros y encabezados específicos para el endpoint.
- Es una forma más concisa y legible de usar `@RequestMapping(method = RequestMethod.GET)`.

### ¿Cómo se utiliza?

```java
@GetMapping("/ruta")
public String ejemplo() {
    return "Respuesta a una solicitud GET";
}
```

En este ejemplo, cualquier solicitud GET a `/ruta` será gestionada por el método `ejemplo`, devolviendo el texto "Respuesta a una solicitud GET" en el cuerpo de la respuesta HTTP.
