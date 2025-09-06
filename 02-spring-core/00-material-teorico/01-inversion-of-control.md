# Inversión de Control (IoC) o Inversión de Dependencia (DI)

La **Inversión de Control** (IoC) es un principio fundamental en el desarrollo de software que consiste en invertir la
responsabilidad de la creación y gestión de las dependencias de los objetos. En lugar de que una clase cree o gestione
sus propias dependencias, estas le son proporcionadas desde el exterior, generalmente por un contenedor o framework.

La **Inversión de Dependencia** (DI) es una forma concreta de aplicar IoC, donde las dependencias de una clase se
inyectan (por constructor, método o atributo) en lugar de ser instanciadas directamente dentro de la clase.

---

## Ejemplo sin Inversión de Dependencia

En este caso, la clase `ClienteService` crea directamente su dependencia `ClienteRepository`:

```java
public class ClienteService {
    private ClienteRepository repo = new ClienteRepository();
    // ...
}
```

## Ejemplo con Inversión de Dependencia (DI)

Aquí, la dependencia se inyecta desde el exterior (por ejemplo, usando Spring):

```java
public class ClienteService {
    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }
    // ...
}
```

---

## ¿Qué es el Spring Container?

El **Spring Container** (contenedor de Spring) es el núcleo del framework Spring y es responsable de crear, configurar y
gestionar el ciclo de vida de los beans (objetos) definidos en la aplicación. El contenedor aplica los principios de
Inversión de Control e Inyección de Dependencias, permitiendo que las dependencias sean gestionadas automáticamente y
facilitando el desacoplamiento entre componentes.

El contenedor de Spring se implementa principalmente a través de las interfaces `BeanFactory` y `ApplicationContext`,
siendo esta última la más utilizada en aplicaciones modernas.

El Spring Container se encarga de:

- Crear y configurar los beans definidos en la aplicación.
- Inyectar las dependencias necesarias en cada bean.
- Gestionar el ciclo de vida de los beans (inicialización, destrucción, etc.).

En resumen, el Spring Container es el "cerebro" de la aplicación Spring, responsable de aplicar la Inversión de Control
y la Inyección de Dependencias de manera automática y eficiente.

---

Para más información, consulta la documentación oficial de Spring:

- [Inyección de Dependencias (Spring Framework)](https://docs.spring.io/spring-framework/reference/core/beans/dependencies.html)
- [Contenedor de Spring (Spring Container)](https://docs.spring.io/spring-framework/reference/core/beans/basics.html)

---

> **Resumen:**
> - **IoC/DI** permite desacoplar las clases y facilita la mantenibilidad y testeo.
> - **Spring Container** es el contenedor que implementa estos principios en aplicaciones Spring.
