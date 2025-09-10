# Ámbito de un Bean (Bean Scope)

El **ámbito de un Bean** (Bean Scope) en Spring define el ciclo de vida y la visibilidad de un bean dentro del contenedor de Spring. Es decir, determina cuántas instancias de un bean se crean y cómo se comparten estas instancias en la aplicación.

## Tipos de Ámbitos de Bean en Spring

Spring proporciona varios tipos de ámbitos para los beans:

### 1. Singleton (por defecto)
- **Descripción:** Solo se crea una única instancia del bean para todo el contenedor de Spring. Todos los requests al bean retornan la misma instancia.
- **Uso típico:** Beans sin estado compartido.

### 2. Prototype
- **Descripción:** Se crea una nueva instancia del bean cada vez que se solicita al contenedor.
- **Uso típico:** Beans con estado o que requieren ser independientes.

### 3. Request (solo aplicaciones web)
- **Descripción:** Se crea una nueva instancia del bean para cada request HTTP.
- **Uso típico:** Beans que mantienen información específica de una petición web.

### 4. Session (solo aplicaciones web)
- **Descripción:** Se crea una nueva instancia del bean para cada sesión HTTP.
- **Uso típico:** Beans que mantienen información específica de una sesión de usuario.

### 5. Application (solo aplicaciones web)
- **Descripción:** Una única instancia del bean por contexto de aplicación web (ServletContext).
- **Uso típico:** Beans que comparten información a nivel de aplicación web.

### 6. Websocket (solo aplicaciones web)
- **Descripción:** Una instancia del bean por cada sesión de WebSocket.
- **Uso típico:** Beans que mantienen información específica de una sesión WebSocket.

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
