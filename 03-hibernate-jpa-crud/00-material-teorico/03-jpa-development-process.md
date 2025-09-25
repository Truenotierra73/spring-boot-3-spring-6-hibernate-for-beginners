# Proceso de desarrollo con JPA en Spring Boot

A continuación se describe el proceso recomendado para desarrollar una aplicación con JPA en Spring Boot, siguiendo buenas prácticas y utilizando ejemplos para cada paso.

## 1. Configuración del proyecto

Agrega las dependencias necesarias en tu archivo `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

## 2. Configuración de la base de datos

En `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mi_base_de_datos
spring.datasource.username=usuario
spring.datasource.password=contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

## 3. Definición de entidades JPA

Crea una clase anotada con `@Entity` que represente una tabla:

```java
import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    // ...otros atributos, getters y setters...
}
```

## 4. Creación de repositorios

Crea una interfaz que extienda `JpaRepository`:

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Métodos personalizados si es necesario
}
```

## 5. Implementación de servicios

Crea una clase de servicio para la lógica de negocio:

```java
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
    // ...otros métodos de negocio...
}
```

## 6. Controladores REST

Crea un controlador para exponer la API:

```java
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> obtenerClientes() {
        return clienteService.listarTodos();
    }
    // ...otros endpoints...
}
```

## 7. Pruebas y validación

Utiliza pruebas unitarias e integrales para asegurar la calidad del código.

---

# Anotaciones JPA más importantes

Las anotaciones JPA permiten mapear clases Java a tablas de la base de datos y definir relaciones y restricciones. A continuación, se listan las más utilizadas, indicando si es posible especificar un nombre alternativo como parámetro:

- `@Entity`
  - **Descripción:**
    La anotación `@Entity` indica que una clase es una entidad JPA, es decir, que su instancia será gestionada por el EntityManager de JPA y se mapeará a una tabla en la base de datos. Cada objeto de la clase representa una fila de la tabla correspondiente.
    - Permite mapear la clase a una tabla de la base de datos.
    - Es posible especificar un nombre alternativo para la entidad usando el parámetro `name`, por ejemplo: `@Entity(name = "NombreAlternativo")`. Si no se especifica, el nombre de la entidad será el de la clase.
    - La clase debe tener un campo anotado con `@Id` que actúe como clave primaria.
    - El nombre de la tabla, por defecto, será el mismo que el de la clase, pero puede personalizarse con la anotación `@Table`.
    - Se pueden definir relaciones, restricciones y configuraciones adicionales mediante otras anotaciones JPA.
  - **Ejemplo:**
    ```java
    @Entity(name = "ProductoEntity")
    public class Producto { ... }
    ```
    ```java
    // Ejemplo completo
    import jakarta.persistence.Entity;
    import jakarta.persistence.Id;
    
    @Entity(name = "ClienteEntidad")
    public class Cliente {
        @Id
        private Long id;
        // ...otros atributos y métodos...
    }
    ```
    > **Nota:** Si una clase no está anotada con `@Entity`, JPA no la gestionará ni la mapeará a ninguna tabla en la base de datos.

- `@Table`
  - **Descripción:**
    La anotación `@Table` se utiliza para indicar el nombre de la tabla de la base de datos a la que se mapeará la entidad JPA. Por defecto, si no se especifica, el nombre de la tabla será el mismo que el de la clase. Permite personalizar el nombre de la tabla y otros aspectos como el esquema, los índices y las restricciones únicas.
    - Es posible especificar un nombre alternativo para la tabla usando el parámetro `name`, por ejemplo: `@Table(name = "productos")`.
    - También se pueden definir parámetros adicionales como `schema`, `catalog`, `uniqueConstraints` e `indexes`.
  - **Ejemplo:**
    ```java
    @Table(name = "productos")
    public class Producto { ... }
    ```
    ```java
    // Ejemplo con parámetros adicionales
    @Table(name = "clientes", schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
    public class Cliente { ... }
    ```
    > **Nota:** Si no se utiliza `@Table`, la entidad se mapeará a una tabla con el mismo nombre que la clase por defecto.

- `@Id`: Indica el campo clave primaria.
  - **¿Permite nombre alternativo?** No, esta anotación no admite parámetros de nombre.
  - **Ejemplo:**
    ```java
    @Id
    private Long id;
    ```

- `@GeneratedValue`: Estrategia de generación de la clave primaria.
  - **¿Permite nombre alternativo?** Sí, mediante el parámetro `generator` para especificar un generador personalizado.
  - **Ejemplo:**
    ```java
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "miGeneradorPersonalizado")
    ```

- `@Column`: Configura detalles de la columna.
  - **¿Permite nombre alternativo?** Sí, mediante el parámetro `name`.
  - **Ejemplo:**
    ```java
    @Column(name = "nombre_columna", nullable = false, length = 50)
    private String nombre;
    ```

- `@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`: Definen relaciones entre entidades.
  - **¿Permite nombre alternativo?** No directamente, pero se puede especificar el nombre de la columna de unión usando `@JoinColumn`.
  - **Ejemplo:**
    ```java
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    ```

- `@JoinColumn`: Especifica la columna de unión en relaciones.
  - **¿Permite nombre alternativo?** Sí, mediante el parámetro `name`.
  - **Ejemplo:**
    ```java
    @JoinColumn(name = "cliente_id")
    ```

- `@Transient`: Marca un campo que no será persistido.
  - **¿Permite nombre alternativo?** No, esta anotación no admite parámetros de nombre.
  - **Ejemplo:**
    ```java
    @Transient
    private int edadTemporal;
    ```

- `@Embedded` y `@Embeddable`: Para objetos embebidos.
  - **¿Permite nombre alternativo?**
    - `@Embedded`: No admite nombre alternativo.
    - `@Embeddable`: No admite nombre alternativo.
  - **Ejemplo:**
    ```java
    @Embeddable
    public class Direccion { ... }
    @Embedded
    private Direccion direccion;
    ```

> Las anotaciones `@Entity` y `@Table` suelen utilizarse en conjunto, pero no es obligatorio.
>
> `@Entity` es imprescindible para que la clase sea reconocida como entidad JPA y se mapee a una tabla.
>
> `@Table` es opcional y se utiliza para personalizar el nombre de la tabla y otros detalles (como esquema, índices, restricciones únicas).
> Si no se especifica `@Table`, la entidad se mapeará a una tabla cuyo nombre será el de la clase.
>
> Por buenas prácticas, se recomienda usar ambas cuando se necesita un nombre de tabla diferente al de la clase o configuraciones adicionales.
>
> **Otras anotaciones que suelen utilizarse en conjunto:**
>
> - `@Id` + `@GeneratedValue`: Para definir la clave primaria y su generación automática.
> - Anotaciones de relación (`@ManyToOne`, `@OneToMany`, `@OneToOne`, `@ManyToMany`) + `@JoinColumn`/`@JoinTable`: Para definir relaciones y columnas de unión.
> - `@Column` junto con validaciones (`@NotNull`, `@Size`, etc.) o configuraciones adicionales (`@Lob`, `@Enumerated`).
> - `@Embedded` + `@Embeddable`: Para objetos embebidos.
>
> Estas combinaciones permiten definir de forma precisa el mapeo, las relaciones y las restricciones entre las entidades y la base de datos, siguiendo buenas prácticas de desarrollo.

---

# Estrategias de generación de ID

JPA permite varias estrategias para la generación de claves primarias:

- `GenerationType.IDENTITY`: Delega la generación al motor de la base de datos (autoincremental en MySQL).
  - **Ejemplo:**
    ```java
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    ```

- `GenerationType.SEQUENCE`: Utiliza una secuencia de la base de datos (más común en Oracle o PostgreSQL).
  - **Ejemplo:**
    ```java
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
    @SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_seq", allocationSize = 1)
    ```

- `GenerationType.TABLE`: Utiliza una tabla especial para generar los IDs.
  - **Ejemplo:**
    ```java
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "cliente_tabla")
    @TableGenerator(name = "cliente_tabla", table = "secuencias", pkColumnName = "nombre", valueColumnName = "valor", pkColumnValue = "cliente", allocationSize = 1)
    ```

- `GenerationType.AUTO`: Delega a JPA la estrategia más adecuada según la base de datos.
  - **Ejemplo:**
    ```java
    @GeneratedValue(strategy = GenerationType.AUTO)
    ```

## Estrategia personalizada de generación de ID

Puedes crear una estrategia personalizada implementando la interfaz `IdentifierGenerator` de Hibernate:

```java
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;
import java.util.UUID;

public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        return UUID.randomUUID().toString();
    }
}
```

Y luego usarla en tu entidad:

```java
@Entity
public class Cliente {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "paquete.CustomIdGenerator")
    private String id;
    // ...
}
```

> **Nota:** Cambia `paquete.CustomIdGenerator` por el paquete real donde se encuentra tu clase generadora.

---

> **Buenas prácticas:**
> - Utiliza nombres descriptivos para entidades y atributos.
> - Aplica validaciones y restricciones en las entidades.
> - Separa la lógica de negocio en servicios.
> - Utiliza DTOs para exponer datos en la API.
> - Documenta tu código y utiliza comentarios en español.

# Referencias oficiales

- [Documentación oficial de Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Documentación oficial de Hibernate ORM](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html)
- [Documentación oficial de JPA (Jakarta Persistence)](https://jakarta.ee/specifications/persistence/3.1/jakarta-persistence-spec-3.1.html)
- [Documentación oficial de Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Documentación oficial de MySQL](https://dev.mysql.com/doc/)
