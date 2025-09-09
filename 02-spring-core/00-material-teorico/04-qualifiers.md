# Qualifiers en Spring

## ¿Qué son y para qué sirven los Qualifiers en Spring?

En Spring, los **Qualifiers** son anotaciones que permiten especificar cuál bean debe inyectarse cuando existen múltiples candidatos del mismo tipo. Se utilizan junto con `@Autowired` para evitar ambigüedades en la inyección de dependencias.

Sirven para:
- Resolver conflictos cuando hay más de un bean del mismo tipo en el contexto de Spring.
- Permitir una inyección de dependencias precisa y controlada.
- Mejorar la legibilidad y mantenibilidad del código al dejar explícito qué implementación se está utilizando.

## ¿Cuándo utilizarlos?

Debes utilizar `@Qualifier` cuando:
- Hay varias implementaciones de una misma interfaz o clase.
- Necesitas especificar cuál bean debe ser inyectado en un punto concreto.
- Quieres evitar errores de ambigüedad en la inyección automática de dependencias.

## Ejemplos de casos de uso

### Ejemplo 1: Múltiples implementaciones de una interfaz

```java
// Interfaz
public interface Notificador {
    void notificar(String mensaje);
}

// Implementación Email
@Component("emailNotificador")
public class EmailNotificador implements Notificador {
    @Override
    public void notificar(String mensaje) {
        // Lógica para enviar email
    }
}

// Implementación SMS
@Component("smsNotificador")
public class SmsNotificador implements Notificador {
    @Override
    public void notificar(String mensaje) {
        // Lógica para enviar SMS
    }
}

// Uso de @Qualifier en el constructor
@Service
public class AlertaService {
    private final Notificador notificador;

    @Autowired
    public AlertaService(@Qualifier("emailNotificador") Notificador notificador) {
        this.notificador = notificador;
    }
}
```

### Ejemplo 2: Uso con campos

```java
@Autowired
@Qualifier("smsNotificador")
private Notificador notificador;
```

### Ejemplo 3: Qualifier personalizado

Puedes crear tus propios qualifiers para mayor claridad:

```java
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface NotificadorEmail {
}

// Aplicación del qualifier personalizado
@Component
@NotificadorEmail
public class EmailNotificador implements Notificador {
    // ...
}

@Autowired
@NotificadorEmail
private Notificador notificador;
```

### Ejemplo 4: Uso de @Qualifier con métodos setter

```java
@Service
public class MensajeriaService {
    private Notificador notificador;

    @Autowired
    public void setNotificador(@Qualifier("smsNotificador") Notificador notificador) {
        this.notificador = notificador;
    }
}
```

En este ejemplo, el bean `smsNotificador` se inyecta mediante un método setter, lo que puede ser útil para pruebas o cuando la dependencia puede cambiar después de la construcción del bean.

## Resumen

- Los Qualifiers resuelven ambigüedades en la inyección de dependencias.
- Se usan junto con `@Autowired` para especificar el bean deseado.
- Son esenciales cuando existen múltiples beans del mismo tipo.

### Documentación oficial

- [Spring Framework - @Qualifier](https://docs.spring.io/spring-framework/reference/core/beans/dependencies/fine-tuning.html#beans-autowired-annotation)
- [Spring Boot - Dependency Injection](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.developing-auto-configuration-beans)
