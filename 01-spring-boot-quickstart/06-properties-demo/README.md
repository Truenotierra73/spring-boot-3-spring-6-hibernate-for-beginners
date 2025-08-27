# Uso de application.properties y @Value en Spring Boot

Spring Boot permite configurar propiedades de la aplicación mediante el archivo `application.properties`. Estas
propiedades pueden ser inyectadas en las clases de servicio utilizando la anotación `@Value`.

## Definir propiedades en application.properties

Puedes definir tus propiedades personalizadas en el archivo `src/main/resources/application.properties`. Por ejemplo:

```properties
# Definición de propiedades personalizadas
app.name=MiAplicacion
app.version=1.0.0
app.description=Esta es una aplicación de ejemplo usando Spring Boot.
```

## Inyectar propiedades en una clase de servicio

Para inyectar estas propiedades en una clase de servicio, utiliza la anotación `@Value
`. Aquí tienes un ejemplo de cómo hacerlo:

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppService {
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.description}")
    private String appDescription;

    public void printAppInfo() {
        System.out.println("Nombre de la aplicación: " + appName);
        System.out.println("Versión de la aplicación: " + appVersion);
        System.out.println("Descripción de la aplicación: " + appDescription);
    }
}
```

En este ejemplo, las propiedades definidas en `application.properties` se inyectan en los campos `appName`, `appVersion`
y `appDescription` de la clase `AppService`.

## Uso del servicio

Puedes utilizar el servicio en cualquier parte de tu aplicación, por ejemplo, en un controlador:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    @Autowired
    private AppService appService;

    @GetMapping("/app-info")
    public void getAppInfo() {
        appService.printAppInfo();
    }
}
```

Cuando accedas a la ruta `/app-info`, se imprimirá la información de la aplicación utilizando las propiedades
inyectadas.

## Conclusión

Usar `application.properties` junto con la anotación `@Value` es una forma sencilla
y efectiva de gestionar la configuración de tu aplicación en Spring Boot. Esto permite mantener las configuraciones
separadas del código, facilitando su mantenimiento y actualización.

# Propiedades heredadas de Spring Boot

Spring Boot permite configurar el comportamiento de la aplicación mediante propiedades en el archivo
`application.properties` o `application.yml`. Estas propiedades se dividen en varias categorías:

## Categorías principales:

- **Servidor:** Configuración del servidor embebido (puerto, contexto, etc.)
- **Base de datos:** Conexión y parámetros de acceso a la base de datos
- **Seguridad:** Opciones de autenticación y autorización
- **Registro (logging):** Nivel y formato de logs
- **Spring:** Configuraciones específicas del framework
- **Aplicación:** Propiedades personalizadas

Enlace a la documentación oficial para una lista completa de propiedades:
[Spring Boot Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)

## Propiedades comunes y ejemplos:

- `server.port=8080` — Cambia el puerto en el que se ejecuta la aplicación
- `server.servlet.context-path=/miapp` — Define la ruta base de la aplicación
- `spring.datasource.url=jdbc:mysql://localhost:3306/mi_db` — Configura la URL de la base de datos
- `spring.datasource.username=usuario` — Usuario de la base de datos
- `spring.datasource.password=contraseña` — Contraseña de la base de datos
- `logging.level.org.springframework=INFO` — Nivel de log para Spring
- `spring.application.name=miaplicacion` — Nombre de la aplicación

Estas propiedades permiten personalizar el entorno de ejecución sin modificar el código fuente, facilitando la gestión y
el despliegue en diferentes entornos.
