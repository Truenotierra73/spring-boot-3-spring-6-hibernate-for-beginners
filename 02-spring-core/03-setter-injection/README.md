# Inyección de dependencias por Setter en Spring

La inyección de dependencias por setter es una de las formas en que Spring puede proporcionar las dependencias a los beans. En este enfoque, Spring llama automáticamente a los métodos setter de una clase para inyectar las dependencias requeridas.

## ¿Cómo es la inyección de dependencia por setter?

A continuación se muestra un ejemplo de cómo implementar la inyección de dependencias por setter en Spring usando anotaciones:

```java
// ...otras importaciones...

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoController {
    private Coach coach;

    // Inyección de dependencia por setter
    @Autowired
    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    // ...otros métodos...
}
```

En este ejemplo, Spring detecta el método `setCoach` anotado con `@Autowired` y automáticamente inyecta el bean `Coach` cuando crea el bean `DemoController`.

## ¿Es obligatorio usar @Autowired en cada setter?

No es obligatorio agregar la anotación `@Autowired` en cada setter que se quiera inyectar, pero es recomendable para mayor claridad y compatibilidad.

Desde Spring 4.3, si una clase tiene un solo método setter, Spring puede inyectar la dependencia automáticamente aunque no tenga `@Autowired`, siempre que el bean sea único en el contexto. Sin embargo, si hay más de un setter, o si quieres dejar explícito que ese método debe ser usado para la inyección, es buena práctica agregar siempre la anotación `@Autowired` en cada setter que desees que Spring utilice para inyectar dependencias.

**Recomendación:**
Siempre usa `@Autowired` en los setters que quieras que Spring inyecte, para evitar confusiones y asegurar el comportamiento esperado, especialmente si tu clase tiene varios setters o si el proyecto puede migrar entre versiones de Spring.

## ¿No poner @Autowired hace que la dependencia sea opcional?

No. Si no agregas la anotación `@Autowired` en un setter, el comportamiento depende de la versión de Spring y de cuántos setters haya en la clase:

- **Si hay un solo setter:** Desde Spring 4.3, Spring puede inyectar la dependencia automáticamente aunque no tenga `@Autowired`, siempre que el bean sea único en el contexto. Esto no significa que la dependencia sea opcional, sino que Spring la inyectará si puede resolver el bean.
- **Si hay varios setters:** Spring solo inyectará dependencias en los setters que tengan la anotación `@Autowired`. Si no la pones, ese setter no será considerado para inyección automática.
- **Para dependencias opcionales:** Si realmente quieres que la dependencia sea opcional, debes usar `@Autowired(required = false)` en el setter. Así, si el bean no existe, Spring no lanzará una excepción.

**Resumen:**
No poner `@Autowired` no hace que la dependencia sea opcional, solo puede hacer que Spring no la inyecte (o la inyecte solo en casos muy específicos). Para dependencias opcionales, usa `@Autowired(required = false)`.

## ¿Cómo funciona?

Cuando Spring detecta un método setter anotado con `@Autowired` (o cuando se configura en XML), el contenedor invoca ese método y le pasa el bean adecuado. Esto permite que la dependencia se establezca después de que el objeto ha sido construido.

Por ejemplo, el siguiente código sería equivalente a lo que hace Spring detrás de escenas:

```java
Coach theCoach = new CricketCoach();
DemoController demoController = new DemoController();
demoController.setCoach(theCoach);
```

Spring automatiza este proceso, detectando los beans y llamando al método `setCoach()` para inyectar la dependencia `Coach` en `DemoController`. Así, el desarrollador no necesita crear ni asociar manualmente los objetos, ya que el framework se encarga de gestionar el ciclo de vida y las dependencias de los beans.

## Ventajas

- Permite la inyección opcional de dependencias.
- Facilita el cambio de dependencias sin modificar el constructor.

## Desventajas

- Las dependencias no están disponibles hasta que se llama al setter.
- Puede dificultar la creación de objetos inmutables.

> **Recomendación:** Utiliza la inyección por setter cuando la dependencia sea opcional o pueda cambiarse después de la construcción del bean.
