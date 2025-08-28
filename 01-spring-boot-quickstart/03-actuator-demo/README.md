# Spring Boot Actuator

## ¿Qué es `spring-boot-starter-actuator`?

Es una dependencia de Spring Boot que proporciona funcionalidades para monitorear y gestionar aplicaciones en producción. Permite exponer endpoints HTTP para obtener métricas, información del sistema, salud de la aplicación, entre otros datos útiles para administración y diagnóstico.

## ¿Para qué sirve?

- Monitorear el estado y salud de la aplicación.
- Obtener métricas de uso, rendimiento y recursos.
- Consultar información de configuración y entorno.
- Realizar trazas y auditoría de eventos.
- Facilitar la integración con herramientas externas de monitoreo.

## Ejemplos de uso

1. **Agregar la dependencia en `pom.xml`:**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

2. **Acceder a endpoints básicos:**

- `/actuator/health`: Muestra el estado de salud de la aplicación.
- `/actuator/info`: Muestra información adicional definida en `application.properties`.
- `/actuator/metrics`: Muestra métricas de la aplicación (memoria, CPU, etc).

3. **Configurar endpoints en `application.properties`:**

```properties
# Incluir endpoints específicos
management.endpoints.web.exposure.include=health,info,metrics
# Excluir endpoints específicos
management.endpoints.web.exposure.exclude=env,beans
```

### Configuración del endpoint `info`

Puedes personalizar la información que se muestra en `/actuator/info` agregando propiedades en `application.properties`:

```properties
info.app.name=Actuator Demo
info.app.description=Aplicación de ejemplo usando Spring Boot Actuator
info.app.version=1.0.0
```

Al acceder a `/actuator/info`, verás:

```json
{
  "app": {
    "name": "Actuator Demo",
    "description": "Aplicación de ejemplo usando Spring Boot Actuator",
    "version": "1.0.0"
  }
}
```

### Mostrar variables de entorno en el endpoint `info`

La propiedad `management.info.env.enabled` permite incluir las variables de entorno del sistema en la respuesta del endpoint `/actuator/info`.

- Si la configuras en `true`, se mostrarán las variables de entorno bajo la clave `env`.
- Si la configuras en `false` (valor por defecto), no se mostrarán.

Ejemplo en `application.properties`:

```properties
management.info.env.enabled=true
```

Esto puede ser útil para depuración, pero puede exponer información sensible. Se recomienda usarlo solo en desarrollo o con endpoints protegidos.

## Nota
Por defecto, algunos endpoints están protegidos y requieren configuración adicional para ser accesibles desde fuera de localhost o para exponer más información.

Para más detalles, consulta la [documentación oficial](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html).
