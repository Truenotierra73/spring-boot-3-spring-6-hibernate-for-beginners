# Spring Boot DevTools

## ¿Qué es spring-boot-devtools?

`spring-boot-devtools` es una dependencia de Spring Boot que proporciona herramientas para mejorar la experiencia de desarrollo. Su principal objetivo es acelerar el ciclo de desarrollo permitiendo la recarga automática de la aplicación cuando se detectan cambios en el código fuente o archivos de configuración. También incluye otras utilidades como el reinicio automático, deshabilitación de cachés, LiveReload y soporte para plantillas.

### Características principales:
- **Reinicio automático:** Reinicia la aplicación cuando se detectan cambios en el código.
- **Recarga de recursos estáticos:** Actualiza automáticamente archivos estáticos como HTML, CSS y JS.
- **LiveReload:** Permite que el navegador se actualice automáticamente al detectar cambios.
- **Deshabilitación de cachés:** Facilita el desarrollo de aplicaciones web al evitar el almacenamiento en caché de recursos.

## ¿Cómo agregar DevTools?

Agrega la siguiente dependencia en tu archivo `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
</dependency>
```

## Configuración en IntelliJ IDEA

Por defecto, IntelliJ IDEA no reinicia la aplicación automáticamente al guardar los archivos. Para que DevTools funcione correctamente, debes habilitar la opción de compilación automática:

1. Ve a **File > Settings > Build, Execution, Deployment > Compiler**.
2. Activa la opción **Build project automatically**.
3. Presiona `Ctrl+Shift+A` y busca "Registry".
4. En el registro, habilita la opción **compiler.automake.allow.when.app.running**.

**Nota:** Si no encuentras la opción en el registro, busca en **File > Settings > Advanced Settings** y activa **Allow auto-make to start even if developed application is running** (o una opción similar). En versiones recientes de IntelliJ IDEA, la configuración puede estar en esta sección.

Con esto, cada vez que guardes un archivo, IntelliJ IDEA compilará automáticamente el proyecto y DevTools reiniciará la aplicación.

## Recomendaciones
- No incluyas DevTools en producción, ya que está pensado solo para desarrollo.
- Si usas LiveReload, instala la extensión en tu navegador para aprovechar la recarga automática.

---

> **Referencia:** [Documentación oficial de Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools)
