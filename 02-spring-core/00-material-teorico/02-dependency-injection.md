# Inyección de Dependencias en Spring

La **Inyección de Dependencias (Dependency Injection, DI)** es un principio fundamental en el desarrollo de aplicaciones
con Spring. Permite desacoplar los componentes de una aplicación, facilitando la reutilización, el mantenimiento y las
pruebas.

---

## ¿Qué es la Inyección de Dependencias?

La inyección de dependencias es un patrón de diseño que consiste en proporcionar a una clase sus dependencias desde el
exterior, en lugar de que la propia clase las cree o gestione. Así, los objetos no son responsables de instanciar sus
dependencias, sino que estas les son "inyectadas" por un contenedor externo (en este caso, el contenedor de Spring).

---

## ¿Cómo se realiza en Spring?

Spring gestiona la inyección de dependencias a través de su contenedor de inversión de control (IoC). Existen varias
formas de realizar la inyección de dependencias en Spring:

### 1. Inyección por constructor (recomendada)

- Las dependencias se pasan a través del constructor de la clase.
- Permite definir las dependencias obligatorias de manera explícita.
- Facilita la inmutabilidad y las pruebas unitarias.
- Ejemplo:
  ```java
  @Service
  public class MiServicio {
      private final MiRepositorio repositorio;

      // Inyección por constructor
      public MiServicio(MiRepositorio repositorio) {
          this.repositorio = repositorio;
      }
  }
  ```

### 2. Inyección por setter

- Las dependencias se establecen mediante métodos setter.
- Útil para dependencias opcionales.
- Ejemplo:
  ```java
  @Service
  public class MiServicio {
      private MiRepositorio repositorio;

      // Inyección por setter
      @Autowired
      public void setRepositorio(MiRepositorio repositorio) {
          this.repositorio = repositorio;
      }
  }
  ```

### 3. Inyección por campo (no recomendada)

- Las dependencias se inyectan directamente en los campos de la clase.
- Dificulta las pruebas y el mantenimiento.
- Ejemplo:
  ```java
  @Service
  public class MiServicio {
      // Inyección directa en el campo
      @Autowired
      private MiRepositorio repositorio;
  }
  ```

---

## Uso de la anotación `@Autowired`

La anotación `@Autowired` es utilizada por Spring para indicar que una dependencia debe ser inyectada automáticamente
por el contenedor. Puede aplicarse a constructores, métodos setter o directamente a campos.

### ¿Qué hace @Autowired exactamente?

`@Autowired` le dice a Spring: **"Busca en tu contenedor un bean del tipo que necesito e inyéctamelo automáticamente"**.

**Proceso interno:**

1. **Escaneo**: Spring encuentra la anotación `@Autowired`
2. **Búsqueda**: Busca en su contenedor de beans uno que coincida por tipo
3. **Resolución**: Si hay múltiples candidatos, usa estrategias de resolución (`@Primary`, `@Qualifier`)
4. **Inyección**: Asigna automáticamente la instancia del bean
5. **Gestión**: Spring gestiona el ciclo de vida de ambos objetos

### ¿Es obligatoria?

- **Constructor:** Desde Spring 4.3, si una clase tiene un único constructor, la anotación `@Autowired` no es
  obligatoria en el constructor, ya que Spring lo detecta automáticamente.
- **Setter y campo:** Es obligatoria para que Spring realice la inyección.
- **Varios constructores:** Se debe indicar explícitamente cuál debe usar Spring con `@Autowired`.

### ¿Qué función cumple?

- Indica al contenedor de Spring que debe resolver e inyectar la dependencia correspondiente.
- Permite la inyección automática de beans, facilitando el desacoplamiento y la configuración de la aplicación.
- Elimina la necesidad de crear manualmente las instancias de las dependencias.

### Ejemplos de uso

- **En constructor (no obligatoria si es el único constructor):**
  ```java
  @Service
  public class ClienteService {
      private final ClienteRepository clienteRepository;

      // Constructor único, @Autowired es opcional
      public ClienteService(ClienteRepository clienteRepository) {
          this.clienteRepository = clienteRepository;
      }
      
      public Cliente buscarPorId(Long id) {
          return clienteRepository.findById(id);
      }
  }
  ```

- **En setter (obligatoria):**
  ```java
  @Service
  public class PedidoService {
      private PedidoRepository pedidoRepository;

      @Autowired
      public void setPedidoRepository(PedidoRepository pedidoRepository) {
          this.pedidoRepository = pedidoRepository;
      }
      
      public Pedido crearPedido(Pedido pedido) {
          return pedidoRepository.save(pedido);
      }
  }
  ```

- **En campo (obligatoria, pero no recomendada):**
  ```java
  @Service
  public class FacturaService {
      @Autowired
      private FacturaRepository facturaRepository;
      
      public List<Factura> obtenerTodasLasFacturas() {
          return facturaRepository.findAll();
      }
  }
  ```

- **Dependencias opcionales:**
  Se puede indicar que una dependencia es opcional usando `@Autowired(required = false)`.
  ```java
  @Service
  public class EmailService {
      @Autowired(required = false)
      private NotificacionService notificacionService;
      
      public void enviarEmail(String mensaje) {
          // Verificar si el servicio está disponible
          if (notificacionService != null) {
              notificacionService.notificar(mensaje);
          }
          // Continuar con la lógica de envío de email
      }
  }
  ```

### Casos de uso comunes en Spring Boot

1. **Inyección de Repository en Service:**
   ```java
   @Service
   public class UsuarioService {
       private final UsuarioRepository usuarioRepository;
       
       public UsuarioService(UsuarioRepository usuarioRepository) {
           this.usuarioRepository = usuarioRepository;
       }
   }
   ```

2. **Inyección de Service en Controller:**
   ```java
   @RestController
   @RequestMapping("/api/usuarios")
   public class UsuarioController {
       private final UsuarioService usuarioService;
       
       public UsuarioController(UsuarioService usuarioService) {
           this.usuarioService = usuarioService;
       }
       
       @GetMapping("/{id}")
       public Usuario obtenerUsuario(@PathVariable Long id) {
           return usuarioService.buscarPorId(id);
       }
   }
   ```

3. **Inyección de múltiples dependencias:**
   ```java
   @Service
   public class ProcesadorPedido {
       private final PedidoRepository pedidoRepository;
       private final EmailService emailService;
       private final InventarioService inventarioService;
       
       public ProcesadorPedido(PedidoRepository pedidoRepository,
                             EmailService emailService,
                             InventarioService inventarioService) {
           this.pedidoRepository = pedidoRepository;
           this.emailService = emailService;
           this.inventarioService = inventarioService;
       }
       
       public void procesarPedido(Pedido pedido) {
           // Lógica de procesamiento usando todas las dependencias
           inventarioService.verificarDisponibilidad(pedido);
           pedidoRepository.save(pedido);
           emailService.enviarConfirmacion(pedido);
       }
   }
   ```

### ¿Por qué usar @Autowired?

**Ventajas:**

- **Automatización**: Spring gestiona las dependencias automáticamente
- **Desacoplamiento**: Las clases no necesitan saber cómo crear sus dependencias
- **Flexibilidad**: Fácil cambio de implementaciones
- **Testabilidad**: Fácil inyección de mocks en pruebas unitarias

**Comparación - Sin Spring vs Con Spring:**

```java
// SIN SPRING - Acoplamiento fuerte
public class PedidoService {
	private PedidoRepository repository = new PedidoRepositoryJpaImpl();
	private EmailService emailService = new EmailServiceImpl();
	// Difícil de testear y cambiar implementaciones
}

// CON SPRING - Bajo acoplamiento
@Service
public class PedidoService {
	private final PedidoRepository repository;
	private final EmailService emailService;

	// Spring inyecta las implementaciones correctas
	public PedidoService(PedidoRepository repository, EmailService emailService) {
		this.repository = repository;
		this.emailService = emailService;
	}
}
```

---

> **Resumen**
>
> La clave está en entender que `@Autowired` elimina la necesidad de usar `new` para crear dependencias manualmente. *
*Spring** se encarga de todo: crear, gestionar y proporcionar los objetos que tu código necesita.

---

