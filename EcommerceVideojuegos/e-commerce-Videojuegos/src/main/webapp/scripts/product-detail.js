document.addEventListener("DOMContentLoaded", async function () {
  const urlParams = new URLSearchParams(window.location.search);
  const productId = urlParams.get("id");

  if (productId) {
    setupAddToCartButton(productId);
    await loadProductDetails(productId);
  } else {
    console.error("No Product ID found in URL");
  }
});

function setupAddToCartButton(id) {
  const btn = document.querySelector(".add-to-car");
  if (btn) {
    btn.setAttribute("onclick", `addToCart(${id})`);
  }
}

async function loadProductDetails(id) {
  try {
    const response = await fetch(`http://localhost:8080/API-Videojuegos/api/productos?id=${id}`);

    if (response.ok) {
      const products = await response.json();

      if (products.length > 0) {
        const product = products[0];

        const titleElements = document.querySelectorAll(".game-tittle");
        titleElements.forEach((el) => (el.textContent = product.nombreProducto));

        const img = document.querySelector(".image-and-price img");
        if (img) {
          img.src = product.imagenBase64 || "imgs/placeholder.jpg";
          img.alt = product.nombreProducto;
        }

        const imgModal = document.querySelector(".item-img");

        if (imgModal) {
          imgModal.src = product.imagenBase64 || "imgs/placeholder.jpg";
          imgModal.alt = product.nombreProducto;
        }

        const price = document.querySelector(".price");
        if (price) {
          price.textContent = `MEX ${product.precio}`; // Ensure format matches your CSS
        }

        const desc = document.querySelector(".description");
        if (desc) {
          desc.textContent = product.descripcion;
        }

        const platSpan = document.getElementById("spec-platform");
        if (platSpan) platSpan.textContent = product.nombrePlataforma || "N/A";

        const devSpan = document.getElementById("spec-developer");
        if (devSpan) devSpan.textContent = product.desarrollador || "Desconocido";

        const yearSpan = document.getElementById("spec-year");
        if (yearSpan) yearSpan.textContent = product.anioLanzamiento || "N/A";

        const starContainer = document.getElementById("global-stars-container");
        const scoreText = document.getElementById("global-score-text");

        if (starContainer) {
          starContainer.innerHTML = generateStarHTML(product.promedioCalificacion || 0);
          if (scoreText)
            scoreText.textContent = `(${product.promedioCalificacion ? product.promedioCalificacion.toFixed(1) : 0})`;
        }

        const reviewsContainer = document.getElementById("reviews-list");
        if (reviewsContainer) {
          reviewsContainer.innerHTML = ""; 

          if (product.resenas && product.resenas.length > 0) {
            product.resenas.forEach((review) => {
              const reviewHTML = `
    <div class="review-item" style="display: flex;
    border-bottom: 1px solid #444;
    padding: 15px 0;
    align-content: flex-start;
    justify-content: flex-end;
    flex-direction: column;
    flex-wrap: wrap;
    align-items: flex-start;">
        <p class="user" style="color: #00ffff; font-weight:bold;">${review.nombreUsuario}</p>
        
        <div class="calification" style="justify-content: flex-start; gap: 5px;">
            ${generateStarHTML(review.calificacion)}
        </div>
        
        <p class="review-date" style="font-size:0.8em; color:#aaa;">Reseña realizada el ${review.fecha}</p>
        <p class="review-message" style="margin-top:5px;">${review.comentario}</p>
    </div>
`;
              reviewsContainer.innerHTML += reviewHTML;
            });
          } else {
            reviewsContainer.innerHTML = "<p>No hay reseñas todavía.</p>";
          }
        }
      }
    } else {
      console.error("Server returned error:", response.status);
    }
  } catch (error) {
    console.error("Error loading details:", error);
  }
}

function generateStarHTML(rating) {
  let html = "";
  for (let i = 1; i <= 5; i++) {
    const isFilled = i <= Math.round(rating);
    const colorStyle = isFilled
      ? "color: rgb(234, 199, 23); font-variation-settings: 'FILL' 1;"
      : "color: gray; font-variation-settings: 'FILL' 0;";

    html += `<span class="material-symbols-outlined" style="${colorStyle} font-size: 20px;">star</span>`;
  }
  return html;
}
