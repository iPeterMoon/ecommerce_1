let currentPage = 1;
let isLoading = false;
const pageSize = 16;

let currentFilters = {
    platforms: [],
    genres: [],
    year: null,
    rating: null,
};

document.addEventListener("DOMContentLoaded", () => {
    setupEventListeners();

    const observer = new IntersectionObserver((entries) => {
        if (entries[0].isIntersecting) {
            loadMoreProducts();
        }
    });

    const sentinel = document.getElementById("sentinel");
    if (sentinel) {
        observer.observe(sentinel);
    }
});

function setupEventListeners() {
    document.querySelectorAll(".filter-platform").forEach((cb) => {
        cb.addEventListener("change", () => {
            updateCheckboxFilter(cb, "platforms");
        });
    });

    document.querySelectorAll(".filter-genre").forEach((cb) => {
        cb.addEventListener("change", () => {
            updateCheckboxFilter(cb, "genres");
        });
    });

    const yearInput = document.getElementById("filter-year");
    if (yearInput) {
        yearInput.addEventListener(
            "input",
            debounce((e) => {
                currentFilters.year = e.target.value;
                resetAndReload();
            }, 500)
        );
    }

    const stars = document.querySelectorAll(".rating-stars .material-symbols-outlined:not(.trash-icon)");
    const starContainer = document.querySelector(".rating-stars");

    stars.forEach((star, index) => {
        star.addEventListener("click", () => {
            if (currentFilters.rating === index + 1) {
                currentFilters.rating = null; 
            } else {
                currentFilters.rating = index + 1;
            }
            updateStarVisuals(currentFilters.rating || 0);
            resetAndReload();
        });

        star.addEventListener("mouseover", () => {
            updateStarVisuals(index + 1, true); 
        });
    });

    starContainer.addEventListener("mouseleave", () => {
        updateStarVisuals(currentFilters.rating || 0);
    });

    const trashBtn = document.querySelector(".trash-icon");
    if (trashBtn) {
        trashBtn.addEventListener("click", () => {
            currentFilters.rating = null;
            updateStarVisuals(0);
            resetAndReload(); 
        });
    }
}

function updateCheckboxFilter(checkbox, filterKey) {
    const value = checkbox.value;
    if (checkbox.checked) {
        currentFilters[filterKey].push(value);
    } else {
        currentFilters[filterKey] = currentFilters[filterKey].filter((item) => item !== value);
    }
    resetAndReload();
}


function updateStarVisuals(count, isHover = false) {
    const stars = document.querySelectorAll(".rating-stars .material-symbols-outlined:not(.trash-icon)");
    stars.forEach((s, i) => {
        if (i < count) {
            s.classList.add("star-filled");
        } else {
            s.classList.remove("star-filled");
        }
    });

    if (!isHover) {
        const trashBtn = document.querySelector(".trash-icon");
        if (trashBtn) {
            if (count > 0) {
                trashBtn.id = "visible-trashcan";
            } else {
                trashBtn.id = "trashcan";
            }
        }
    }
}

function resetAndReload() {
    currentPage = 1;
    document.getElementById("product-container").innerHTML = "";
    document.getElementById("sentinel").innerHTML = "<p>Cargando más juegos...</p>";
    loadMoreProducts();
}

async function loadMoreProducts() {
    if (isLoading) return;
    isLoading = true;

    try {
        const baseUrl = "http://localhost:8080/API-Videojuegos/api/productos";
        const params = new URLSearchParams();

        params.append("page", currentPage);
        params.append("size", pageSize);

        if (currentFilters.year) params.append("year", currentFilters.year);
        if (currentFilters.rating) params.append("rating", currentFilters.rating);

        if (currentFilters.platforms.length > 0) params.append("platform", currentFilters.platforms.join(","));
        if (currentFilters.genres.length > 0) params.append("genre", currentFilters.genres.join(","));

        const response = await fetch(`${baseUrl}?${params.toString()}`);

        if (!response.ok) throw new Error("Error en la petición");

        const newProducts = await response.json();

        if (newProducts.length === 0) {
            document.getElementById("sentinel").innerHTML = "<p>No se encontraron más resultados.</p>";
            return;
        }

        renderProducts(newProducts);
        currentPage++;
    } catch (error) {
        console.error("Error cargando productos:", error);
        document.getElementById("sentinel").innerHTML = "<p>Error al cargar.</p>";
    } finally {
        isLoading = false;
    }
}

function renderProducts(products) {
    const container = document.getElementById("product-container");
    products.forEach((product) => {
        const card = document.createElement("a");
        card.className = "game-card";
        card.href = `producto.jsp?id=${product.idProducto}`;
        card.innerHTML = `<img src="${product.imagenBase64 || "imgs/placeholder.jpg"}" alt="${product.nombreProducto}">`;
        container.appendChild(card);
    });
}

function debounce(func, wait) {
    let timeout;
    return function (...args) {
        clearTimeout(timeout);
        timeout = setTimeout(() => func.apply(this, args), wait);
    };
}