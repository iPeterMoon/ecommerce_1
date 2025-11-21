drop database if exists ecommerceVideojuegos;

create database ecommerceVideojuegos;

use ecommerceVideojuegos;

CREATE TABLE plataformas (
    id_plataforma BIGINT PRIMARY KEY auto_increment,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE clasificaciones (
    id_clasificacion BIGINT PRIMARY KEY auto_increment,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE categorias (
    id_categoria BIGINT PRIMARY KEY auto_increment,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE carritos_compra (
    id_carrito BIGINT PRIMARY KEY auto_increment,
    total DECIMAL(10, 2) DEFAULT 0.00
);

CREATE TABLE videojuegos (
    id_videojuego BIGINT PRIMARY KEY auto_increment,
    nombre varchar(255) NOT NULL,
    desarrollador VARCHAR(255),
    ano_lanzamiento VARCHAR(4),
    id_clasificacion BIGINT,
    FOREIGN KEY (id_clasificacion) REFERENCES clasificaciones(id_clasificacion)
);

CREATE TABLE usuarios (
    id_usuario BIGINT PRIMARY KEY auto_increment,
    nombre VARCHAR(255) NOT NULL,
    telefono VARCHAR(15),
    correo VARCHAR(255) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    cuenta_activa BOOLEAN DEFAULT TRUE,
    rol VARCHAR(50),
    id_carrito BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (id_carrito) REFERENCES carritos_compra(id_carrito)
);

-- CAMBIO IMPORTANTE AQUÍ: Se reemplazó imagen_url por imagen LONGBLOB
CREATE TABLE productos (
    id_producto BIGINT PRIMARY KEY auto_increment,
    nombre_producto varchar (255) NOT NULL,
    imagen LONGBLOB, -- Columna para guardar la imagen en bytes
    precio DECIMAL(10, 2) NOT NULL,
    stock INT DEFAULT 0,  
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
    FOREIGN KEY (id_videojuego) REFERENCES videojuegos(id_videojuego),
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);

CREATE TABLE direcciones (
    id_direccion BIGINT PRIMARY KEY auto_increment,
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
    id_pedido BIGINT PRIMARY KEY auto_increment,
    fecha_hora TIMESTAMP NOT NULL,
    estado_pedido VARCHAR(20) NOT NULL 
        CHECK (estado_pedido IN ('PENDIENTE', 'ENVIADO', 'ENTREGADO','CANCELADO')),
    id_usuario BIGINT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE resenas (
    id_resena BIGINT PRIMARY KEY auto_increment,
    id_usuario BIGINT NOT NULL,
    id_producto BIGINT NOT NULL,
    fecha DATE NOT NULL,
    calificacion INT,
    comentario TEXT,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE TABLE pagos (
    id_pago BIGINT PRIMARY KEY auto_increment,
    referencia VARCHAR(255),
    monto DECIMAL(10, 2) NOT NULL,
    metodo_pago VARCHAR(20) NOT NULL 
        CHECK (metodo_pago IN ('TARJETA', 'TRANSFERENCIA', 'CONTRAENTREGA')),
    fecha_hora TIMESTAMP NOT NULL,
    estado_pago VARCHAR(20) NOT NULL 
        CHECK (estado_pago IN ('PENDIENTE', 'REALIZADO')),
    id_pedido BIGINT NOT NULL UNIQUE, 
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido)
);

CREATE TABLE items (
    id_item BIGINT PRIMARY KEY auto_increment,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    cantidad INT NOT NULL,
    id_producto BIGINT NOT NULL,
    id_carrito BIGINT, 
    id_pedido BIGINT,  
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto),
    FOREIGN KEY (id_carrito) REFERENCES carritos_compra(id_carrito),
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido)
);

-- 1. Inserts Catálogos Base
INSERT INTO clasificaciones (nombre) VALUES 
('M for Mature'), 
('E for Everyone'),
('E10+ (Everyone 10+)'),
('T (Teen)'),
('RP (Rating Pending)');

INSERT INTO plataformas (nombre) VALUES 
('PlayStation 5'), 
('PC'), 
('Xbox Series X'),
('Nintendo Switch'),
('Móvil');

INSERT INTO categorias (nombre) VALUES
('Acción'),
('Aventura'),
('RPG'),
('Estrategia'),
('Deportes'),
('Sandbox'),
('Shooter'),
('Puzzle'),
('Simulación'),
('Terror');

-- 2. Inserts Videojuegos
INSERT INTO videojuegos (id_videojuego, nombre, desarrollador, ano_lanzamiento, id_clasificacion) VALUES 
(101, 'Elden Ring', 'FromSoftware', '2022', 1),
(102, 'Stardew Valley', 'ConcernedApe', '2016', 2),
(103, 'Cyberpunk 2077', 'CD Projekt Red', '2020', 1);

-- 3. Inserts Productos 

INSERT INTO productos (id_producto, nombre_producto, precio, stock, id_plataforma, id_videojuego) VALUES 
(501, 'Elden Ring - PS5 Edition', 59.99, 100, 1, 101),
(502, 'Stardew Valley - PC', 14.99, 500, 2, 102),
(503, 'Cyberpunk 2077 - Xbox Series X', 39.99, 80, 3, 103);

-- 4. Inserts Carritos
INSERT INTO carritos_compra (id_carrito) VALUES 
(1), 
(2),
(3);

-- 5. Inserts Usuarios
INSERT INTO usuarios (id_usuario, nombre, correo, contrasena, rol, id_carrito) VALUES  
(1001, 'Ana García', 'ana.garcia@email.com', '9b8769a4a742959a2d0298c36fb70623f2dfacda8436237df08d8dfd5b37374c', 'CLIENTE', 1),
(1002, 'Carlos López', 'carlos.lopez@email.com', '1d4598d1949b47f7f211134b639ec32238ce73086a83c2f745713b3f12f817e5', 'CLIENTE', 2),
(1003, 'Mafia Boss', 'mafia.boss@mundito.com', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'ADMIN', 3);

-- PEDIDOS DE EJEMPLO
INSERT INTO pedidos (id_pedido, fecha_hora, estado_pedido, id_usuario) VALUES 
(10001, NOW(), 'PENDIENTE', 1001);

INSERT INTO pagos (id_pago, monto, metodo_pago, fecha_hora, estado_pago, id_pedido) VALUES 
(20001, 14.99, 'TARJETA', NOW(), 'PENDIENTE', 10001);

INSERT INTO items (id_item, precio_unitario, cantidad, id_producto, id_pedido) VALUES 
(30001, 14.99, 1, 502, 10001);

INSERT INTO pedidos (id_pedido, fecha_hora, estado_pedido, id_usuario) VALUES 
(10002, '2025-11-10 09:30:00', 'ENVIADO', 1002);

INSERT INTO pagos (id_pago, monto, metodo_pago, fecha_hora, estado_pago, id_pedido) VALUES 
(20002, 99.98, 'TRANSFERENCIA', '2025-11-10 09:31:00', 'REALIZADO', 10002);

INSERT INTO items (id_item, precio_unitario, cantidad, id_producto, id_pedido) VALUES 
(30002, 59.99, 1, 501, 10002);

INSERT INTO items (id_item, precio_unitario, cantidad, id_producto, id_pedido) VALUES 
(30003, 39.99, 1, 503, 10002);

-- RESEÑAS DE EJEMPLO

-- 1. Reseña para Elden Ring  
INSERT INTO resenas (id_usuario, id_producto, fecha, calificacion, comentario)
VALUES (1002, 501, CURDATE(), 5, 'Simplemente espectacular. La dirección artística y la jugabilidad son de otro nivel. Me ha costado, pero cada victoria se siente increíble.');

-- 2. Reseña para Stardew Valley 
INSERT INTO resenas (id_usuario, id_producto, fecha, calificacion, comentario)
VALUES (1001, 502, CURDATE(), 5, 'El juego más relajante que he probado. Perfecto para desconectar después del trabajo. La música y los personajes son encantadores.');

-- 3. Reseña para Stardew Valley(para que salgan 2)
INSERT INTO resenas (id_usuario, id_producto, fecha, calificacion, comentario)
VALUES (1002, 502, CURDATE(), 4, 'Muy adictivo, aunque al principio el ritmo es un poco lento. Una vez que te enganchas a la granja no puedes parar.');

-- 4. Reseña para Cyberpunk 2077 
INSERT INTO resenas (id_usuario, id_producto, fecha, calificacion, comentario)
VALUES (1001, 503, CURDATE(), 3, 'La historia es buena y los gráficos en Series X son brutales, pero todavía me encontré con algunos bugs visuales que me sacaron de la experiencia.');