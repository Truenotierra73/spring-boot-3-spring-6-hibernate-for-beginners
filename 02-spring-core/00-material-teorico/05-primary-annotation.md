# Anotación @Primary en Spring

## ¿Qué es y para qué sirve la anotación @Primary?

La anotación `@Primary` en Spring se utiliza para indicar que un bean debe ser considerado como la opción predeterminada cuando existen múltiples beans del mismo tipo en el contexto de la aplicación. Cuando Spring encuentra varias implementaciones candidatas para una inyección de dependencias y una de ellas está marcada con `@Primary`, esa será la que se inyecte por defecto, a menos que se especifique explícitamente otra mediante `@Qualifier`.

## Ejemplo de uso

```java
// Interfaz
public interface Notificador {
    void notificar(String mensaje);
}

// Implementación principal
@Component
@Primary // Esta será la opción predeterminada
public class EmailNotificador implements Notificador {
    @Override
    public void notificar(String mensaje) {
        // Lógica para enviar email
    }
}

// Otra implementación
@Component
public class SmsNotificador implements Notificador {
    @Override
    public void notificar(String mensaje) {
        // Lógica para enviar SMS
    }
}

// Servicio que utiliza la inyección
@Service
public class AlertaService {
    private final Notificador notificador;

    @Autowired
    public AlertaService(Notificador notificador) {
        this.notificador = notificador; // Se inyecta EmailNotificador por defecto
    }
}
```

## ¿Se pueden utilizar varias anotaciones @Primary?

No, solo puede haber un bean marcado como `@Primary` para cada tipo en el contexto de Spring. Si se marcan varios beans del mismo tipo con `@Primary`, Spring lanzará una excepción indicando que hay más de un bean primario definido.

## Diferencia entre @Qualifier y @Primary

- `@Primary` define el bean predeterminado para un tipo cuando hay varias opciones.
- `@Qualifier` permite especificar explícitamente cuál bean se debe inyectar, incluso si existe un bean marcado como `@Primary`.

## ¿Se pueden utilizar ambos al mismo tiempo?

Sí, se pueden utilizar ambos. Si existe un bean marcado como `@Primary`, será el predeterminado, pero si se usa `@Qualifier`, este tiene prioridad y se inyectará el bean especificado por el `@Qualifier`.

### Ejemplo combinando @Primary y @Qualifier

```java
@Service
public class MensajeriaService {
    private final Notificador notificador;

    @Autowired
    public MensajeriaService(@Qualifier("smsNotificador") Notificador notificador) {
        this.notificador = notificador; // Se inyecta SmsNotificador aunque EmailNotificador sea @Primary
    }
}
```

## Prioridades

1. Si se usa `@Qualifier`, siempre tiene prioridad sobre `@Primary`.
2. Si no se usa `@Qualifier` y hay un bean `@Primary`, se inyecta ese bean.
3. Si no hay ni `@Qualifier` ni `@Primary` y existen varios beans del mismo tipo, Spring lanzará una excepción `NoUniqueBeanDefinitionException`.

## Resumen

- `@Primary` define el bean predeterminado para un tipo.
- Solo puede haber un bean `@Primary` por tipo.
- `@Qualifier` tiene prioridad sobre `@Primary`.
- Se pueden usar ambos para un control más preciso de la inyección.

### Documentación oficial

- [Spring Framework - @Primary](https://docs.spring.io/spring-framework/reference/core/beans/dependencies/fine-tuning.html#beans-primary)
- [Spring Framework - @Qualifier](https://docs.spring.io/spring-framework/reference/core/beans/dependencies/fine-tuning.html#beans-autowired-annotation)

