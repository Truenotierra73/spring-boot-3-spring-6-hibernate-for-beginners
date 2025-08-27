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



