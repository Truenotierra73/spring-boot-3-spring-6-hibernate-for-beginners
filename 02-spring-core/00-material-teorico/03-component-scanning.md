# Component Scanning en Spring Boot

Component Scanning es una funcionalidad de Spring que permite detectar automáticamente las clases anotadas con estereotipos como `@Component`, `@Service`, `@Repository` o `@Controller` dentro de un paquete y sus subpaquetes. Esto significa que Spring puede registrar automáticamente estos beans en el contexto de la aplicación sin necesidad de declararlos manualmente en un archivo de configuración.

Cuando se utiliza Component Scanning, Spring busca en los paquetes especificados (por defecto, el paquete donde se encuentra la clase principal) todas las clases que tengan estas anotaciones y las agrega al contenedor de Spring para su gestión.

## ¿Qué es y qué hace la anotación `@SpringBootApplication`?

La anotación `@SpringBootApplication` es una anotación de conveniencia que se utiliza en la clase principal de una aplicación Spring Boot. Esta anotación combina tres anotaciones importantes:

- `@Configuration`: Indica que la clase puede ser utilizada por el contenedor de Spring como una fuente de definiciones de beans.
- `@EnableAutoConfiguration`: Le dice a Spring Boot que configure automáticamente la aplicación según las dependencias que encuentre en el classpath.
- `@ComponentScan`: Habilita el Component Scanning, permitiendo que Spring detecte automáticamente los beans en el paquete donde se encuentra la clase anotada y en sus subpaquetes.

Por lo tanto, al usar `@SpringBootApplication`, no solo se configura la aplicación automáticamente, sino que también se habilita el escaneo de componentes.

## ¿Cómo habilitar el escaneo de paquetes fuera del paquete principal?

Por defecto, `@ComponentScan` escanea el paquete donde está ubicada la clase principal y todos sus subpaquetes. Si necesitas que Spring escanee otros paquetes que no están dentro del paquete principal, puedes personalizar el comportamiento de la siguiente manera:

1. **Usando el atributo `basePackages` de `@ComponentScan`:**

```java
@SpringBootApplication
@ComponentScan(basePackages = {"com.ejemplo.paquete1", "com.otro.paquete2"})
public class MiAplicacion {
    public static void main(String[] args) {
        SpringApplication.run(MiAplicacion.class, args);
    }
}
```

2. **Solo con `@SpringBootApplication`** (a partir de Spring Boot 1.3+):
Puedes pasar el atributo `scanBasePackages` directamente:

```java
@SpringBootApplication(scanBasePackages = {"com.ejemplo.paquete1", "com.otro.paquete2"})
public class MiAplicacion {
    // ...
}
```

Esto le indica a Spring Boot que escanee explícitamente los paquetes indicados, además del paquete principal.

### Documentación oficial
- [Component Scanning (Spring Framework)](https://docs.spring.io/spring-framework/reference/core/beans/classpath-scanning.html)
- [@SpringBootApplication (Spring Boot)](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/autoconfigure/SpringBootApplication.html)
- [Guía de referencia de Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.structuring-your-code)
- [@ComponentScan (Spring Framework)](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/ComponentScan.html)
