document.addEventListener('DOMContentLoaded', loadOrderDetails);

async function loadOrderDetails() {
    const urlParams = new URLSearchParams(window.location.search);
    const orderId = urlParams.get('id');

    if (!orderId) {
        showError("Pedido no especificado", "No se proporcionó un ID de pedido.");
        return;
    }

    try {
        const token = localStorage.getItem("token");
        if (!token) {
            window.location.href = "login.jsp";
            return;
        }

        const response = await fetch(`http://localhost:8080/API-Videojuegos/api/pedidos/${orderId}`, {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + token, 
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            if (response.status === 401) {
                alert("Sesión expirada");
                window.location.href = "login.jsp";
                return;
            } else if (response.status === 403) {
                showError("Acceso Denegado", "No tienes permiso para ver este pedido.");
                return;
            } else if (response.status === 404) {
                showError("No Encontrado", "El pedido solicitado no existe.");
                return;
            }
            throw new Error("Error al cargar el pedido");
        }

        const pedido = await response.json();
        renderOrder(pedido);

    } catch (error) {
        console.error(error);
        showError("Error de Conexión", "Ocurrió un error inesperado.");
    }
}

function renderOrder(pedido) {
    document.getElementById('loading-message').style.display = 'none';
    document.getElementById('order-content').style.display = 'block';

    document.getElementById('user-name-title').textContent = pedido.usuario.nombre;
    document.getElementById('order-id').textContent = pedido.idPedido;
    
    const date = new Date(pedido.fechaHora);
    document.getElementById('order-date').textContent = date.toLocaleDateString('es-MX', { 
        day: 'numeric', month: 'short', year: 'numeric', hour: '2-digit', minute:'2-digit' 
    });

    document.getElementById('client-name').textContent = pedido.usuario.nombre;
    document.getElementById('client-email').textContent = pedido.usuario.correo;

    if(pedido.pago) {
        document.getElementById('payment-method').textContent = pedido.pago.metodoPago || 'N/A';
        document.getElementById('payment-ref').textContent = pedido.pago.referencia || 'Sin referencia';
        document.getElementById('payment-status').textContent = pedido.pago.estadoPago || 'N/A';
    } else {
        document.getElementById('payment-method').textContent = "Pendiente / No registrado";
    }

    if (pedido.usuario.direcciones && pedido.usuario.direcciones.length > 0) {
        const d = pedido.usuario.direcciones[0];
        document.getElementById('client-address').textContent = `${d.calle} ${d.numeroExterior}, ${d.colonia}, ${d.ciudad}`;
    } else {
        document.getElementById('client-address').textContent = "Dirección no disponible";
    }

    const itemsContainer = document.getElementById('items-container');
    itemsContainer.innerHTML = '';
    
    let totalCalculado = 0;

    const puedeResenar = pedido.estadoPedido === 'ENTREGADO';

    pedido.items.forEach(item => {
        const subtotal = item.subtotal || (item.precioUnitario * item.cantidad);
        totalCalculado += subtotal;

        const img = item.imagenBase64 || 'imgs/placeholder.png';
        const precioFormatted = item.precioUnitario.toLocaleString("es-MX", {style: "currency", currency: "MXN"});

        let botonResenaHTML = '';

        if (puedeResenar) {
            botonResenaHTML = `
                <button type="button" class="review-btn" onclick="abrirModalResena(${item.idProducto})">
                    ★ Escribir Reseña
                </button>
            `;
        }

        const itemRow = document.createElement('div');
        itemRow.className = 'product-row';

        itemRow.innerHTML = `
            <div class="product-info-left"> 
                <img src="${img}" alt="${item.nombreProducto}" style="width: 80px; height: 80px; object-fit: cover; border-radius: 4px;" />
                <div class="tittle-quantity">
                    <p>${item.nombreProducto}</p>
                    <p>Cantidad: ${item.cantidad}</p>
                </div>
            </div>
            
            <div class="product-info-right">
                <p class="product-price">${precioFormatted}</p>
                ${botonResenaHTML} </div>
        `;

        itemsContainer.appendChild(itemRow);
    });

    const totalFinal = (pedido.pago && pedido.pago.monto) ? pedido.pago.monto : totalCalculado;
    const totalDisplay = totalFinal.toLocaleString("es-MX", {style:"currency", currency:"MXN"});
    
    document.getElementById('subtotal-amount').textContent = totalDisplay;
    document.getElementById('total-amount').textContent = totalDisplay;

    const statusText = document.getElementById('status-text');
    const statusIcon = document.getElementById('status-icon');
    
    statusText.textContent = pedido.estadoPedido;
    
    let iconSrc = 'icons/pending.svg';
    if(pedido.estadoPedido === 'ENTREGADO' || pedido.estadoPedido === 'COMPLETADO') iconSrc = 'icons/check.svg';
    else if(pedido.estadoPedido === 'ENVIADO') iconSrc = 'icons/box.svg';
    else if(pedido.estadoPedido === 'CANCELADO') iconSrc = 'icons/refund.svg'; 

    statusIcon.src = iconSrc;
}

function showError(title, message) {
    const loader = document.getElementById('loading-message');
    if(loader) loader.style.display = 'none';
    
    const errorContainer = document.getElementById('error-container');
    if(errorContainer) {
        document.getElementById('error-title').textContent = title;
        document.getElementById('error-text').textContent = message;
        errorContainer.style.display = 'block';
    }
}