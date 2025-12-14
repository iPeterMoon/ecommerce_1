document.addEventListener('DOMContentLoaded', () => {
});

async function saveAddress(event) {
    event.preventDefault(); 

    const errorDiv = document.getElementById('form-error');
    if (errorDiv) errorDiv.style.display = 'none';

    const token = localStorage.getItem('token');
    if (!token) {
        window.location.href = "login.jsp";
        return;
    }

    const addressName = document.getElementById('addressName').value.trim();
    const street = document.getElementById('street').value.trim();
    const extNumber = document.getElementById('extNumber').value.trim();
    const colony = document.getElementById('colony').value.trim();
    const zipCode = document.getElementById('zipCode').value.trim();
    const city = document.getElementById('city').value.trim();
    const state = document.getElementById('state').value.trim();

    const payload = {
        nombre: addressName,
        calle: street,
        numeroExterior: extNumber,
        colonia: colony,
        codigoPostal: zipCode,
        ciudad: city,
        estado: state
    };


    // 4. Basic Validation
    for (const key in payload) {
        if (!payload[key]) {
            showError("Por favor llena todos los campos.");
            return;
        }
    }

    try {
        const response = await fetch('http://localhost:8080/API-Videojuegos/api/users/addresses', {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });


        if (!response.ok) {
            const contentType = response.headers.get("content-type");
            if (contentType && contentType.includes("application/json")) {
                const data = await response.json();
                throw new Error(data.error || "Error al guardar dirección");
            } else {
                const text = await response.text();
                throw new Error("Error del servidor: " + text);
            }
        }
        
        alert("Dirección agregada correctamente.");
        window.location.href = "realizar_pedido.jsp"; 

    } catch (error) {
        showError(error.message);
    }
}

function showError(message) {
    const errorDiv = document.getElementById('form-error');
    if (errorDiv) {
        errorDiv.textContent = message;
        errorDiv.style.display = 'block';
    } else {
        alert(message);
    }
}