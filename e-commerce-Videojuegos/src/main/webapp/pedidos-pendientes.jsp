<%-- 
    Document   : pedidos-pendientes
    Created on : 14 nov 2025, 1:38:30 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Pedidos pendientes</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/pedidos_pendientes.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
</head>

<body class="tron-grid grid-container">
    <header>
        <div class="brand-mobile">
            <a href="index.html">
                <img class="logo" src="imgs/logo-mundito.svg" alt="logo" />
            </a>
            <a href="index.html">
                <h1>MunditoGames</h1>
            </a>
        </div>
        <div class="brand-title-desktop">
            <a href="index.html">
                <h1>MunditoGames</h1>
            </a>
        </div>

        <nav>
            <ul>
                <li class="nav-element">
                    <a href="">
                        <img src="icons/search.svg" alt="search" />
                    </a>
                </li>

                <li class="nav-element">
                    <a href="shopping-cart.html">
                        <img src="icons/cart.svg" alt="cart" />
                    </a>
                </li>
                <li class="nav-element">
                    <a href="login.html">
                        <img src="icons/user.svg" alt="search" />
                    </a>
                </li>
            </ul>
        </nav>
    </header>




    <main class="container">
        <div class="search-div">
            <input type="text" id="search" class="search" placeholder="Buscar...">
        </div>
        <div class="card-form-container">
            <div class="header-data">
            </div>
            <div class="form-content">
                <div class="payment-container">
                    <div class="payment-info">
                        <div class="pedido">
                            <p class="pedido-texto">Pedido - </p>
                            <p class="pedido">N°: 10733</p>
                        </div>
                        <div class="pago-metodo">
                            <p class="payment-method">Fecha del pedido: 15 Oct, 2025</p>
                            <p class="payment-method">Método de pago: Tarjeta</p>
                        </div>
                        <div class="pedido">
                            <p class="payment-status">Estado de pago: </p>
                            <a class="status"><img src="icons/pending.svg" class="check" />Pendiente</a>
                        </div>
                    </div>
                    <div class="payment-aprrove-button">
                        <a href="???.html" class="approve-btn">Validar pago</a>
                    </div>
                </div>

                <div class="payment-container">
                    <div class="payment-info">
                        <div class="pedido">
                            <p class="pedido-texto">Pedido - </p>
                            <p class="pedido">N°: 1921</p>
                        </div>
                        <div class="pago-metodo">
                            <p class="payment-method">Fecha del pedido: 07 Dic, 2025</p>
                            <p class="payment-method">Método de pago: Tarjeta</p>
                        </div>
                        <div class="pedido">
                            <p class="payment-status">Estado de pago: </p>
                            <a class="status"><img src="icons/pending.svg" class="check" />Pendiente</a>
                        </div>
                    </div>
                    <div class="payment-aprrove-button">
                        <a href="???.html" class="approve-btn">Validar pago</a>
                    </div>
                </div>

                <div class="payment-container">
                    <div class="payment-info">
                        <div class="pedido">
                            <p class="pedido-texto">Pedido - </p>
                            <p class="pedido">N°: 1267</p>
                        </div>
                        <div class="pago-metodo">
                            <p class="payment-method">Fecha del pedido: 29 Feb, 2025</p>
                            <p class="payment-method">Método de pago: Tarjeta</p>
                        </div>
                        <div class="pedido">
                            <p class="payment-status">Estado de pago: </p>
                            <a class="status"><img src="icons/pending.svg" class="check" />Pendiente</a>
                        </div>
                    </div>
                    <div class="payment-aprrove-button">
                        <a href="???.html" class="approve-btn">Validar pago</a>
                    </div>
                </div>
            </div>
    </main>

    <footer class="site-footer">
        <div class="footer-container">

            <div class="footer-column">
                <a href="index.html" class="logo-container">
                    <img src="imgs/logo-mundito.svg" alt="logo">
                    <h4>MunditoGames</h4>
                </a>
                <p>Tu universo de videojuegos. Encuentra los mejores títulos, ofertas y la comunidad más apasionada del
                    gaming.</p>
            </div>

            <div class="footer-column">
                <h4>Navegación</h4>
                <ul class="footer-links">
                    <li><a href="index.html">Inicio</a></li>
                    <li><a href="catalogo.html">Catálogo de Productos</a></li>
                    <li><a href="account.html">Mi Cuenta</a></li>
                    <li><a href="shopping-cart.html">Carrito de Compras</a></li>
                </ul>
            </div>

            <div class="footer-column">
                <h4 style="margin-top: 1.5rem;">Síguenos</h4>
                <ul class="social-icons">
                    <li><a href="#" aria-label="Facebook"><img src="icons/facebook.svg" alt="Facebook"></a></li>
                    <li><a href="#" aria-label="Instagram"><img src="icons/instagram.svg" alt="Instagram"></a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; 2025 MunditoGames. Todos los derechos reservados.</p>
        </div>
    </footer>
</body>

</html>