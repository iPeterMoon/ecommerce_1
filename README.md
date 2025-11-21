# 🎮 E-commerce de Videojuegos - Proyecto Web Avance 3

Este proyecto es una plataforma web completa para la venta y gestión de videojuegos. Implementa una arquitectura modular utilizando el patrón MVC, utilizando tecnologías estándar de la industria Java Enterprise para asegurar escalabilidad, mantenimiento y seguridad.

Por el momento no se encuentra toda la aplicación funcional.
Solamente el area de administración de el admin para gestionar los datos necesarios que levantan la pagina.

Usuario administrador para probar el modulo:
Correo: mafia.boss@mundito.com
Contraseña: admin123

OJO: para poder usar esas credenciales se necesita correr el script de la base de datos: 'ecommerceDatabase.sql'
---

## 🛠️ Tecnologías Utilizadas

* **Java (JDK 17+)**: Lenguaje de programación principal.
* **Jakarta EE / Java Web**: Uso de Servlets y JSPs para la lógica web y la interfaz.
* **Maven**: Gestión de dependencias y construcción de sub-módulos.
* **Hibernate / JPA**: Framework ORM para la persistencia de datos y manejo de entidades.
* **MySQL**: Motor de base de datos relacional.
* **CSS3 / HTML5**: Estilizado de la interfaz de usuario (`webapp/styles`).

---

## 🚀 Cómo Empezar

### 📋 Prerrequisitos

Asegúrate de tener instalado lo siguiente en tu entorno:
1.  JDK (Java Development Kit) versión 17 o superior.
2.  Maven.
3.  Servidor de Base de Datos MySQL. (Viene en los archivos solo para correr una vez.)
4.  Un servidor de aplicaciones (Tomcat).

### ⚙️ Instalación y Configuración

1.  **Clonar el repositorio**:
    ```bash
    git clone <URL_DE_TU_REPOSITORIO>
    ```

2.  **Configurar la Base de Datos**:
    * Asegúrate de crear un esquema en MySQL llamado `ecommerce_videojuegos` (o el nombre definido en tu `persistence.xml`).
    * Verifica las credenciales en `EcommerceVideojuegos/Persistencia/src/main/resources/META-INF/persistence.xml`.

3.  **Construir el proyecto (Build)**:
    Navega a la raíz del proyecto y ejecuta:
    ```bash
    mvn clean install
    ```

### ▶️ Ejecutar la Aplicación

1.  **Despliegue (Deploy)**:
    Toma el archivo `.war` generado en:
    `EcommerceVideojuegos/e-commerce-Videojuegos/target/e-commerce-Videojuegos-1.0-SNAPSHOT.war`
2.  **Servidor**:
    Despliégalo en tu servidor de aplicaciones preferido (ej. Payara Server o Tomcat).
3.  **Acceso**:
    Abre tu navegador en: `http://localhost:8080/e-commerce-Videojuegos-1.0-SNAPSHOT/`

---

### 📦 Módulos del Proyecto

1.  **`DominioBD` (Entidades)**:
    Contiene el mapeo ORM de la base de datos.
    * **Clases Clave**: `Usuario`, `Videojuego`, `Pedido`, `Producto`, `CarritoCompra`.
    * **Enums**: `RolUsuario`, `MetodoPago`, `EstadoPedido`.

2.  **`Persistencia` (DAO - Data Access Object)**:
    Maneja toda la comunicación con la base de datos usando JPA.
    * **`GenericDAO`**: Implementación genérica para operaciones CRUD.
    * **DAOs Específicos**: `UsuarioDAO`, `VideojuegoDAO`, `PedidoDAO`, etc.
    * **`JPAUtil`**: Gestión del EntityManagerFactory.

3.  **`DTOS` (Data Transfer Objects)**:
    Objetos planos utilizados para transportar datos entre la vista y el modelo sin exponer las entidades de la base de datos directamente.
    * Ejemplos: `UsuarioDTO`, `VideojuegoDTO`, `PedidoDTO`.

4.  **`e-commerce-Videojuegos` (Web / WAR)**:
    Módulo principal que contiene la interfaz y los controladores.
    * **Vista (JSP)**: Páginas como `catalogo.jsp`, `shopping-cart.jsp`, `login.jsp`, `admin-options.jsp`.
    * **Controlador (Servlets)**: `InicioSesionServlet`, `VideojuegoServlet`, `ProductoServlet`.
    * **Modelo de Negocio (BO)**: Clases como `UsuarioBO` o `PedidoBO` que contienen la lógica de negocio antes de llamar a la persistencia.

---

## ✨ Funcionalidades Principales

* **Gestión de Usuarios**: Registro, Login (con filtros de autenticación y roles Admin/Cliente).
* **Catálogo**: Visualización de videojuegos con filtros y búsqueda.
* **Carrito de Compras**: Agregar items, gestionar cantidades y procesar compra.
* **Gestion de Pedidos**: Gestionar los pedidos realizados por los clientes, cambiar el estado del envio, cancelar el pedido y revisar los detalles de cada uno.
* **Administración**: CRUD de videojuegos, moderación de reseñas y gestión de usuarios.
* **Reseñas**: Los usuarios pueden dejar comentarios y calificaciones en los productos.

---

## 👨‍💻 Equipo de Desarrollo
* [Luciano Barcelo Murrieta] - [00000252086]
* [Benjamin Soto Coronado] - [00000253183]
* [Pedro Luna Esquer] - [00000252687]
* [Erick Sebastian Moreno Vargas] - [00000252840]