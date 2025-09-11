# Ciclo de vida de un Bean en Spring

El ciclo de vida de un Bean en Spring define las distintas etapas por las que pasa un objeto gestionado por el contenedor de Spring, desde su creación hasta su destrucción. Comprender este ciclo es fundamental para aprovechar al máximo el framework y gestionar correctamente los recursos.

## Etapas principales del ciclo de vida

1. **Instanciación**
   - El contenedor de Spring crea una instancia del Bean utilizando el constructor predeterminado o el especificado.

2. **Inyección de dependencias**
   - Spring inyecta las dependencias requeridas en el Bean, ya sea a través de la inyección por constructor, por setter o por campos.

3. **Configuración de propiedades**
   - Se configuran las propiedades del Bean definidas en el contexto de Spring.

4. **Callbacks de inicialización**
   - Si el Bean implementa la interfaz `InitializingBean` (método `afterPropertiesSet()`) o tiene un método anotado con `@PostConstruct`, estos métodos se ejecutan después de la inyección de dependencias.
   - También se puede definir un método de inicialización personalizado en la configuración del Bean.

5. **El Bean está listo para usarse**
   - El Bean puede ser utilizado por la aplicación.

6. **Callbacks de destrucción**
   - Antes de que el contenedor destruya el Bean (por ejemplo, al cerrar el contexto de la aplicación), se ejecutan los métodos de destrucción.
   - Si el Bean implementa la interfaz `DisposableBean` (método `destroy()`) o tiene un método anotado con `@PreDestroy`, estos métodos se ejecutan.
   - También se puede definir un método de destrucción personalizado en la configuración del Bean.

## Resumen gráfico

```mermaid
graph TD;
    A[Instanciación] --> B[Inyección de dependencias];
    B --> C[Configuración de propiedades];
    C --> D[Inicialización (@PostConstruct, afterPropertiesSet, método personalizado)];
    D --> E[Bean listo para usar];
    E --> F[Destrucción (@PreDestroy, destroy, método personalizado)];
```

## Ejemplo práctico

```java
@Component
public class EjemploBean implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() {
        // Código de inicialización
    }

    @Override
    public void destroy() {
        // Código de limpieza antes de la destrucción
    }

    @PostConstruct
    public void init() {
        // Inicialización personalizada
    }

    @PreDestroy
    public void cleanup() {
        // Limpieza personalizada
    }
}
```

> **Nota:**
> - En el caso de uso de `singleton`, el ciclo de vida es gestionado automáticamente por el contenedor de Spring.
> - En el caso de beans `prototype`, el contenedor no gestiona la destrucción, por lo que los métodos de destrucción no se llaman automáticamente.

## Hooks del ciclo de vida de un Bean y casos de uso

Spring proporciona varios ganchos (hooks) que permiten ejecutar lógica personalizada en diferentes etapas del ciclo de vida de un Bean. A continuación se describen los principales hooks y ejemplos de uso para cada uno:

### 1. **@PostConstruct**
- **Cuándo se ejecuta:** Después de la inyección de dependencias y antes de que el Bean esté disponible para su uso.
- **Caso de uso:** Inicializar recursos, cargar configuraciones, abrir conexiones, validar propiedades.
- **Ejemplo real:** Cargar datos de configuración desde un archivo externo o inicializar un pool de conexiones.

### 2. **afterPropertiesSet() (InitializingBean)**
- **Cuándo se ejecuta:** Similar a `@PostConstruct`, se invoca después de la inyección de dependencias.
- **Caso de uso:** Inicialización avanzada, lógica que depende de todas las propiedades inyectadas.
- **Ejemplo real:** Verificar que todas las dependencias críticas estén presentes o inicializar componentes complejos.

### 3. **Método de inicialización personalizado**
- **Cuándo se ejecuta:** Definido en la configuración del Bean (`init-method`), se ejecuta después de los anteriores.
- **Caso de uso:** Inicialización específica que no puede resolverse con anotaciones o interfaces.
- **Ejemplo real:** Registrar el Bean en un sistema externo o realizar tareas de auditoría.

### 4. **@PreDestroy**
- **Cuándo se ejecuta:** Justo antes de que el Bean sea destruido por el contenedor.
- **Caso de uso:** Liberar recursos, cerrar conexiones, guardar estados.
- **Ejemplo real:** Cerrar conexiones a bases de datos, liberar hilos o guardar información en disco.

### 5. **destroy() (DisposableBean)**
- **Cuándo se ejecuta:** Al destruir el Bean, junto con `@PreDestroy`.
- **Caso de uso:** Limpieza avanzada, lógica de cierre que requiere acceso a dependencias.
- **Ejemplo real:** Notificar a otros sistemas que el Bean dejará de estar disponible.

### 6. **Método de destrucción personalizado**
- **Cuándo se ejecuta:** Definido en la configuración del Bean (`destroy-method`).
- **Caso de uso:** Liberar recursos específicos o realizar tareas de cierre no cubiertas por otros hooks.
- **Ejemplo real:** Eliminar archivos temporales o limpiar cachés.

> **Recomendación:**
> - Usar `@PostConstruct` y `@PreDestroy` para la mayoría de los casos, ya que son más declarativos y desacoplados.
> - Reservar las interfaces (`InitializingBean`, `DisposableBean`) para casos donde se requiera lógica más compleja o acceso a dependencias del contenedor.
> - Los métodos personalizados son útiles cuando se necesita flexibilidad adicional en la configuración XML o JavaConfig.

## Referencias
- [Documentación oficial de Spring: Bean Lifecycle](https://docs.spring.io/spring-framework/reference/core/beans/factory.html#beans-factory-lifecycle)
