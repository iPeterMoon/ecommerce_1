let currentPage = 1;
let isLoading = false;
const pageSize = 16; 

document.addEventListener("DOMContentLoaded", () => {
    const observer = new IntersectionObserver((entries) => {
        if (entries[0].isIntersecting) {
            loadMoreProducts();
        }
    });

    const sentinel = document.getElementById('sentinel');
    if (sentinel) {
        observer.observe(sentinel)
    }
});

async function loadMoreProducts() {
    if (isLoading) return;
    isLoading = true;

    try {
        console.log(`Cargando página ${currentPage}...`);

        const response = await fetch(`http://localhost:8080/API-Videojuegos/api/productos?page=${currentPage}&size=${pageSize}`);
        
        if (!response.ok) throw new Error("Error en la petición");

        const newProducts = await response.json();

        if (newProducts.length === 0) {
            document.getElementById('sentinel').innerHTML = "<p>¡No hay más productos!</p>";
            return; 
        }
        renderProducts(newProducts);

        currentPage++;

    } catch (error) {
        console.error("Error cargando productos:", error);
    } finally {
        isLoading = false;
    }
}

function renderProducts(products) {
    const container = document.getElementById('product-container');

    products.forEach(product => {
        const card = document.createElement('a');
        card.className = "game-card";
        
        card.href = `producto.jsp?id=${product.idProducto}`; 

        card.innerHTML = `
            <img src="${product.imagenBase64 || 'imgs/placeholder.jpg'}" alt="${product.nombreProducto}">
            `;

        container.appendChild(card);
    });
}