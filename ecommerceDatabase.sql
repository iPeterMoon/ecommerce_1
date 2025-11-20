create database ecommerceVideojuegos;
USE ecommerceVideojuegos;


CREATE TABLE plataformas (
    id_plataforma BIGINT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE clasificaciones (
    id_clasificacion BIGINT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE categorias (
    id_categoria BIGINT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE carritos_compra (
    id_carrito BIGINT PRIMARY KEY,
    total DECIMAL(10, 2) DEFAULT 0.00
);

CREATE TABLE videojuegos (
    id_videojuego BIGINT PRIMARY KEY,
    nombre varchar(255) NOT NULL,
    desarrollador VARCHAR(255),
    ano_lanzamiento VARCHAR(4),
    id_clasificacion BIGINT,
    FOREIGN KEY (id_clasificacion) REFERENCES clasificaciones(id_clasificacion)
);

CREATE TABLE usuarios (
    id_usuario BIGINT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    telefono VARCHAR(15),
    correo VARCHAR(255) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    cuenta_activa BOOLEAN DEFAULT TRUE,
    rol VARCHAR(50),
    id_carrito BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (id_carrito) REFERENCES carritos_compra(id_carrito)
);

CREATE TABLE productos (
    id_producto BIGINT PRIMARY KEY,
    nombre_producto varchar (255) NOT NULL,
    imagen_url VARCHAR(500),
    precio DECIMAL(10, 2) NOT NULL,
    descripcion TEXT,
    id_plataforma BIGINT,
    id_videojuego BIGINT,
    FOREIGN KEY (id_videojuego) REFERENCES videojuegos(id_videojuego) ON DELETE CASCADE,
    FOREIGN KEY (id_plataforma) REFERENCES plataformas(id_plataforma)
);

CREATE TABLE videojuego_categoria (
    id_videojuego BIGINT,
    id_categoria BIGINT,
    PRIMARY KEY (id_videojuego, id_categoria),
    FOREIGN KEY (id_videojuego) REFERENCES videojuego(id_videojuego),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE direcciones (
    id_direccion BIGINT PRIMARY KEY,
    calle VARCHAR(255) NOT NULL,
    numero_exterior VARCHAR(50),
    colonia VARCHAR(100),
    ciudad VARCHAR(100) NOT NULL,
    estado VARCHAR(100) NOT NULL,
    codigo_postal VARCHAR(10) NOT NULL,
    id_usuario BIGINT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE pedidos (
    id_pedido BIGINT PRIMARY KEY,
    estado_pedido VARCHAR(20) NOT NULL 
        CHECK (estado_pedido IN ('PENDIENTE', 'ENVIADO', 'ENTREGADO')),
    id_usuario BIGINT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE resenas (
    id_resena BIGINT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    id_producto BIGINT NOT NULL,
    fecha DATE NOT NULL,
    calificacion INT,
    comentario TEXT,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE TABLE pagos (
    id_pago BIGINT PRIMARY KEY,
    referencia VARCHAR(255),
    monto DECIMAL(10, 2) NOT NULL,
    metodo_pago VARCHAR(20) NOT NULL 
        CHECK (metodo_pago IN ('TARJETA', 'TRANSFERENCIA', 'CONTRAENTREGA')),
    fecha_hora TIMESTAMP NOT NULL,
    estado_pago VARCHAR(20) NOT NULL 
        CHECK (estado_pago IN ('PENDIENTE', 'REALIZADO')),
    id_pedido BIGINT NOT NULL UNIQUE, -- 'UNIQUE' asegura la relación 1:1
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido)
);

CREATE TABLE items (
    id_item BIGINT PRIMARY KEY,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    cantidad INT NOT NULL,
    id_producto BIGINT NOT NULL,
    id_carrito BIGINT, -- NULO si está en un pedido
    id_pedido BIGINT,  -- NULO si está en un carrito
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto),
    FOREIGN KEY (id_carrito) REFERENCES carritos_compra(id_carrito),
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido)
);