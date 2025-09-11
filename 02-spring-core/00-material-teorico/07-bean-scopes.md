# Ámbito de un Bean (Bean Scope)

El **ámbito de un Bean** (Bean Scope) en Spring define el ciclo de vida y la visibilidad de un bean dentro del contenedor de Spring. Es decir, determina cuántas instancias de un bean se crean y cómo se comparten estas instancias en la aplicación.

## Tipos de Ámbitos de Bean en Spring

Spring proporciona varios tipos de ámbitos para los beans:

| Ámbito         | Descripción                                                                                                                                            | Uso típico                                                        |
|----------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------|
| **Singleton**  | Solo se crea una única instancia del bean para todo el contenedor de Spring. Todos los requests al bean retornan la misma instancia. **(Por defecto)** | Beans sin estado compartido.                                      |
| **Prototype**  | Se crea una nueva instancia del bean cada vez que se solicita al contenedor.                                                                           | Beans con estado o que requieren ser independientes.              |
| **Request**    | Se crea una nueva instancia del bean para cada request HTTP. **(Solo aplicaciones web)**                                                               | Beans que mantienen información específica de una petición web.    |
| **Session**    | Se crea una nueva instancia del bean para cada sesión HTTP. **(Solo aplicaciones web)**                                                                | Beans que mantienen información específica de una sesión de usuario. |
| **Application**| Una única instancia del bean por contexto de aplicación web (ServletContext). **(Solo aplicaciones web)**                                              | Beans que comparten información a nivel de aplicación web.         |
| **Websocket**  | Una instancia del bean por cada sesión de WebSocket. **(Solo aplicaciones web)**                                                                       | Beans que mantienen información específica de una sesión WebSocket.|

## Cómo cambiar el ámbito de un Bean
El ámbito de un bean se puede cambiar de las siguientes formas:

### 1. Usando la anotación `@Scope`
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MiBean {
    // ...
}
```

### 2. Definiendo el scope en el archivo de configuración XML
```xml
<bean id="miBean" class="com.ejemplo.MiBean" scope="prototype" />
```

### 3. Usando la anotación `@Bean` en una clase de configuración
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    @Scope("prototype")
    public MiBean miBean() {
        return new MiBean();
    }
}
```

### 4. Usando constantes de `ConfigurableBeanFactory`

Spring provee constantes para los scopes más comunes en la clase `ConfigurableBeanFactory`, lo que ayuda a evitar errores de tipeo y mejora la mantenibilidad del código. Por ejemplo:

```java
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MiBean {
    // ...
}
```

## Ejemplos de Singleton y Prototype

### Ejemplo Singleton (por defecto)
```java
import org.springframework.stereotype.Component;

@Component
public class MiBeanSingleton {
    // ...
}
```

### Ejemplo Prototype
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MiBeanPrototype {
    // ...
}
```

> **Nota:** Los ámbitos `request`, `session`, `application` y `websocket` solo están disponibles en aplicaciones web Spring.

## Referencias
- [Spring Framework - Bean Scopes](https://docs.spring.io/spring-framework/reference/core/beans/factory-scopes.html)
