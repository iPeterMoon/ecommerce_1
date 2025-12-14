async function addToCart(id) {
  try {
    const response = await fetch(`http://localhost:8080/API-Videojuegos/api/cart/add?id=${id}`, {
      method: "POST",
    });

    if (response.ok) {
      const data = await response.json();
      console.log("Total items now:", data.totalItems);

      const badge = document.getElementById("cart-badge");
      if (badge) {
        badge.textContent = data.totalItems;
        badge.style.display = "block";
      }

    } else {
      const errorText = await response.text();
      console.error("Server Error:", response.status, errorText);
      alert("Error: " + errorText);
    }
  } catch (e) {
    console.error(e);
  }
}

async function loadShoppingCart() {
  try {
    const response = await fetch("http://localhost:8080/API-Videojuegos/api/cart");

    if (!response.ok) throw new Error("Error fetching cart");

    const items = await response.json();
    renderCartItems(items);
    updateSummary(items);
  } catch (error) {
    console.error("Error:", error);
    document.getElementById("cart-container").innerHTML = "<p>Tu carrito está vacío o hubo un error.</p>";
  }
}

function renderCartItems(items) {
  const container = document.getElementById("cart-container");
  container.innerHTML = ""; 

  if (items.length === 0) {
    container.innerHTML = "<p style='padding: 20px; text-align: center;'>El carrito está vacío.</p>";
    return;
  }

  items.forEach((item) => {
    const row = document.createElement("div");
    row.className = "product-row";

    row.innerHTML = `
            <img src="icons/x-button.svg" alt="Eliminar" class="delete-button" onclick="removeItem(${
              item.idProducto
            })" style="cursor:pointer;" />
            <img src="${item.imagenBase64 || "imgs/placeholder.png"}" alt="${
      item.nombreProducto
    }" class="product-img" />
            
            <div class="tittle">
                <p>Título</p>
                <p class="game-name">${item.nombreProducto}</p>
            </div>
            
            <div class="quantity">
                <p>Cantidad</p>
                <p class="product-quantity">${item.cantidad}</p>
            </div>
            
            <div class="price">
                <p>Precio</p>
                <p class="product-price">$${item.precioUnitario}</p>
            </div>
            
            <div class="subtotal">
                <p>Subtotal</p>
                <p class="product-subtotal">$${item.subtotal}</p>
            </div>
        `;

    container.appendChild(row);
  });
}

function updateSummary(items) {
  let totalQty = 0;
  let totalPrice = 0;

  items.forEach((item) => {
    totalQty += item.cantidad;
    totalPrice += parseFloat(item.subtotal);
  });

  document.getElementById("txt-total-items").textContent = `(${totalQty})`;

  const formattedPrice = totalPrice.toLocaleString("es-MX", { style: "currency", currency: "MXN" });

  document.getElementById("txt-total-price").textContent = formattedPrice;
  document.getElementById("txt-final-price").textContent = "MEX " + formattedPrice;
}

async function removeItem(id) {
  const response = await fetch(`http://localhost:8080/API-Videojuegos/api/cart/remove?id=${id}`, {
    method: "DELETE",
  });

  if (response.ok) {
    loadShoppingCart();
    const data = await response.json();
  } else {
    console.error("Error removing item");
  }
}
