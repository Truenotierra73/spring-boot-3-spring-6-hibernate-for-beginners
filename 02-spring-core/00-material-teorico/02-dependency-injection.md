# Inyección de Dependencias en Spring

La **Inyección de Dependencias (Dependency Injection, DI)** es un principio fundamental en el desarrollo de aplicaciones
con Spring. Permite desacoplar los componentes de una aplicación, facilitando la reutilización, el mantenimiento y las
pruebas.

## ¿Qué es la Inyección de Dependencias?

La inyección de dependencias es un patrón de diseño que consiste en proporcionar a una clase sus dependencias desde el
exterior, en lugar de que la propia clase las cree o gestione. De esta manera, los objetos no son responsables de
instanciar sus dependencias, sino que estas les son "inyectadas" por un contenedor externo (en este caso, el contenedor
de Spring).

## ¿Cómo se lleva a cabo en Spring?

Spring gestiona la inyección de dependencias a través de su contenedor de inversión de control (IoC). Existen varias
formas de realizar la inyección de dependencias en Spring:

1. **Inyección por constructor:**
    - Las dependencias se pasan a través del constructor de la clase.
    - Es la forma recomendada, ya que permite definir las dependencias obligatorias de manera explícita.
    - Ejemplo:
      ```java
      @Component
      public class MiServicio {
          private final MiRepositorio repositorio;
 
          public MiServicio(MiRepositorio repositorio) {
              this.repositorio = repositorio;
          }
      }
      ```

2. **Inyección por setter:**
    - Las dependencias se establecen mediante métodos setter.
    - Útil para dependencias opcionales.
    - Ejemplo:
      ```java
      @Component
      public class MiServicio {
          private MiRepositorio repositorio;
 
          @Autowired
          public void setRepositorio(MiRepositorio repositorio) {
              this.repositorio = repositorio;
          }
      }
      ```

3. **Inyección por campo:**
    - Las dependencias se inyectan directamente en los campos de la clase.
    - Es la forma menos recomendada, ya que dificulta las pruebas y el mantenimiento.
    - Ejemplo:
      ```java
      @Component
      public class MiServicio {
          @Autowired
          private MiRepositorio repositorio;
      }
      ```

## Anotaciones principales

- `@Component`, `@Service`, `@Repository`, `@Controller`: Indican que una clase es un componente gestionado por Spring.
- `@Autowired`: Indica a Spring que debe inyectar la dependencia correspondiente.
- `@Qualifier`: Permite especificar cuál implementación inyectar cuando hay varias disponibles.

## ¿Qué es un Spring Bean?

Un **Spring Bean** es cualquier objeto que es instanciado, ensamblado y gestionado por el contenedor de Spring. Los
beans representan los componentes principales de una aplicación Spring y son definidos, por lo general, mediante
anotaciones como `@Component`, `@Service`, `@Repository` o `@Controller`, o bien a través de la configuración en
archivos de configuración.

El ciclo de vida de un bean es gestionado completamente por el contenedor de Spring, lo que permite aplicar
funcionalidades transversales como la inyección de dependencias, la gestión de transacciones, la seguridad, entre otros.

## Ventajas de la Inyección de Dependencias

- Favorece el bajo acoplamiento entre componentes.
- Facilita la reutilización y el mantenimiento del código.
- Permite realizar pruebas unitarias de manera sencilla mediante el uso de mocks o stubs.

---

> **Resumen:**
>
> La inyección de dependencias es un pilar en el desarrollo con Spring, permitiendo construir aplicaciones
> modulares, escalables y fáciles de mantener.
