
async function addToCart(id) {

    try {
        const token = localStorage.getItem("token");

        if (!token) {
            window.location.href = "login.jsp";
            return;
        }

        const verify = await fetch("http://localhost:8080/API-Videojuegos/api/auth/verify", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ token: token }),
        });

        if (verify.status === 401) {
            alert("Your session has expired. Please log in again.");
            localStorage.removeItem("token");
            window.location.href = "login.jsp";
            return;
        }

        if (!verify.ok) {
            const data = await verify.json();
            window.alert(data.error);
            return;
        }

        const response = await fetch(`http://localhost:8080/API-Videojuegos/api/cart/add?id=${id}`, {
            method: "POST",
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
            },
        });

        if (response.ok) {
            const data = await response.json();

            const badge = document.getElementById("cart-badge");
            if (badge) {
                badge.textContent = data.totalItems;
                badge.style.display = "block";
            }
        } else {
            const errorText = await response.text();
            alert("Error: " + errorText);
        }
    } catch (e) {
    }
}


async function loadShoppingCart() {

    try {
        const token = localStorage.getItem("token");

        if (!token) {
            window.location.href = "login.jsp";
            return;
        }

        const verify = await fetch("http://localhost:8080/API-Videojuegos/api/auth/verify", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ token: token }),
        });

        if (verify.status === 401) {
            alert("Your session has expired.");
            localStorage.removeItem("token");
            window.location.href = "login.jsp";
            return;
        }

        if (!verify.ok) {
            const data = await verify.json();
            window.alert(data.error);
            return;
        }

        const response = await fetch("http://localhost:8080/API-Videojuegos/api/cart", {
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
            },
        });

        if (!response.ok) throw new Error(`HTTP Error: ${response.status}`);

        const items = await response.json();
        renderCartItems(items);
        updateSummary(items);

    } catch (error) {
        console.error("Fatal error in loadShoppingCart:", error);
        document.getElementById("cart-container").innerHTML = "<p>Your cart is empty or an error occurred.</p>";
    }
}


function renderCartItems(items) {
    const container = document.getElementById("cart-container");
    container.innerHTML = "";

    if (items.length === 0) {
        container.innerHTML = "<p style='padding: 20px; text-align: center;'>The cart is empty.</p>";
        return;
    }

    items.forEach((item) => {
        const row = document.createElement("div");
        row.className = "product-row";

        row.innerHTML = `
            <img src="icons/x-button.svg" alt="Remove" class="delete-button" onclick="removeItem(${item.idProducto})" style="cursor:pointer;" />
            <img src="${item.imagenBase64 || "imgs/placeholder.png"}" alt="${item.nombreProducto}" class="product-img" />
            
            <div class="tittle">
                <p>Title</p>
                <p class="game-name">${item.nombreProducto}</p>
            </div>
            
            <div class="quantity">
                <p>Quantity</p>
                <p class="product-quantity">${item.cantidad}</p>
            </div>
            
            <div class="price">
                <p>Price</p>
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
    console.log(`[DEBUG] Attempting to remove item ID: ${id}`);
    
    const token = localStorage.getItem("token");
    if (!token) {
        alert("No active session");
        return;
    }

    const response = await fetch(`http://localhost:8080/API-Videojuegos/api/cart/remove?id=${id}`, {
        method: "DELETE",
        headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
        },
    });

    if (response.ok) {
        loadShoppingCart();
    } 
}

async function loadOrderSummary() {
    try {
        const token = localStorage.getItem("token");
        
        if (!token) {
            window.location.href = "login.jsp";
            return;
        }

        const userResponse = await fetch("http://localhost:8080/API-Videojuegos/api/auth/me", {
            method: "GET",
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
            },
        });

        if (userResponse.ok) {
            const user = await userResponse.json();

            if (!user.direccion || user.direccion.trim() === "") {
                alert("You do not have a registered address. Please add one to continue.");
                window.location.href = "agregar_direccion.jsp"; // Or your edit profile page
                return; 
            }

            const addressContainer = document.querySelector(".shipment-information");
            if (addressContainer) {
                addressContainer.innerHTML = `
                    <p style="font-weight:bold;">Shipping Address</p>
                    <p>${user.direccion}</p>
                    <p>${user.ciudad || ''}, CP ${user.cp || ''}</p>
                `;
            }
        } else if (userResponse.status === 401) {
             alert("Session expired.");
             window.location.href = "login.jsp";
             return;
        }

        const cartResponse = await fetch("http://localhost:8080/API-Videojuegos/api/cart", {
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
            },
        });

        if (cartResponse.ok) {
            const items = await cartResponse.json();
            renderOrderItems(items);
        }

    } catch (error) {
        console.error("Error in loadOrderSummary:", error);
    }
}

function renderOrderItems(items) {
    const container = document.getElementById("order-items-container");
    const subtotalEl = document.getElementById("order-subtotal");
    const totalEl = document.getElementById("order-total");
    
    container.innerHTML = "";
    let total = 0;

    items.forEach(item => {
        total += item.subtotal;

        const row = document.createElement("div");
        row.className = "product-row";
        row.innerHTML = `
            <div class="product-info-left">
                <img src="${item.imagenBase64 || 'imgs/placeholder.png'}" alt="${item.nombreProducto}" />
                <div class="tittle-quantity">
                    <p>${item.nombreProducto}</p>
                    <p>Quantity: ${item.cantidad}</p>
                </div>
            </div>
            <p class="product-price">$${item.subtotal.toFixed(2)}</p>
        `;
        container.appendChild(row);
    });

    const formattedTotal = total.toLocaleString("es-MX", { style: "currency", currency: "MXN" });
    if(subtotalEl) subtotalEl.textContent = formattedTotal;
    if(totalEl) totalEl.textContent = formattedTotal;
}