# Lazy Initialization en Spring

La **Lazy Initialization** (Inicialización Perezosa) es una técnica utilizada en Spring para retrasar la creación de beans hasta que realmente se necesiten, en lugar de instanciarlos al inicio del contexto de la aplicación.

## ¿Para qué sirve?

La carga perezosa ayuda a optimizar el uso de recursos, mejorar los tiempos de arranque de la aplicación y evitar la creación innecesaria de beans que podrían no ser utilizados durante la ejecución.

## Configuración de Lazy Initialization

### 1. A nivel de bean
Puedes usar la anotación `@Lazy` en una clase o en la declaración de un bean:

```java
@Component
@Lazy
public class MiBean {
    // ...
}
```

O en la configuración:

```java
@Bean
@Lazy
public MiBean miBean() {
    return new MiBean();
}
```

### 2. A nivel de inyección
Puedes indicar que una dependencia debe inyectarse de forma perezosa:

```java
@Autowired
@Lazy
private MiBean miBean;
```

### 3. A nivel global (Spring Boot 2.2+)
Puedes habilitar la inicialización perezosa para todos los beans en el archivo `application.properties`:

```
spring.main.lazy-initialization=true
```

O programáticamente:

```java
SpringApplication app = new SpringApplication(MiAplicacion.class);
app.setLazyInitialization(true);
```

## Ventajas
- Reduce el tiempo de arranque de la aplicación.
- Optimiza el uso de memoria y recursos.
- Evita la creación de beans innecesarios.

## Desventajas
- Puede retrasar la detección de errores de configuración hasta el momento de uso.
- Puede introducir latencia en la primera utilización del bean.
- No es recomendable para beans críticos o que deben estar disponibles inmediatamente.

## ¿Cuándo es conveniente usar Lazy Initialization?
- En aplicaciones grandes donde el tiempo de arranque es importante.
- Cuando hay beans que rara vez se utilizan.
- En entornos de desarrollo para acelerar el ciclo de pruebas.

## ¿Cuándo NO es conveniente?
- Para beans esenciales que deben estar disponibles desde el inicio.
- Cuando la latencia en la primera llamada no es aceptable.
- Si necesitas detectar errores de configuración al inicio.

## Documentación oficial
- [Spring Framework - Lazy Initialization](https://docs.spring.io/spring-framework/reference/core/beans/factory-lazy-init.html)
- [Spring Boot - Lazy Initialization](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.lazy-initialization)

