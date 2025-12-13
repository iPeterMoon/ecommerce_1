// Esperar a que el HTML cargue completamente antes de ejecutar
    document.addEventListener("DOMContentLoaded", function() {
        cargarJuegosDestacados();
    });

    async function cargarJuegosDestacados() {
        const contenedor = document.querySelector(".games-grid.landing-grid");
        
        try {
            const response = await fetch("http://localhost:8080/API-Videojuegos/api/productos");
            
            if (!response.ok) {
                throw new Error("Error en la red: " + response.status);
            }

            const productos = await response.json();

            contenedor.innerHTML = ''; 

            productos.forEach(producto => {
                const card = document.createElement("a");

                card.href = `producto.jsp?id=${producto.idProducto}`; 
                card.className = "game-card";

                const img = document.createElement("img");
               
                img.src = producto.imagenBase64 || "icons/placeholder-game.png"; 
                img.alt = producto.nombreProducto;

                card.appendChild(img);

                contenedor.appendChild(card);
            });

        } catch (error) {
            console.error("Hubo un problema cargando los juegos:", error);
            contenedor.innerHTML = '<p style="color: white;">No se pudieron cargar los juegos destacados.</p>';
        }

    }

