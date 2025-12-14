document.addEventListener('DOMContentLoaded', loadUserProfile);

// Variable global para almacenar los datos del usuario temporalmente
let currentUserData = null;

async function loadUserProfile() {
    const token = localStorage.getItem('token');
    const loader = document.getElementById('loading-overlay');
    const content = document.getElementById('account-content');

    if (!token) {
        window.location.href = "login.jsp";
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/API-Videojuegos/api/users/profile', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            if (response.status === 401) {
                alert("Sesión expirada.");
                localStorage.removeItem('token');
                window.location.href = "login.jsp";
                return;
            }
            throw new Error(`Error ${response.status}`);
        }

        const user = await response.json();
        currentUserData = user; // Guardar referencia

        renderUserData(user);
        renderAddresses(user.direcciones);

        if(loader) loader.style.display = 'none';
        if(content) content.style.display = 'block';

    } catch (error) {
        console.error(error);
        if(loader) loader.style.display = 'none';
        alert("Error cargando perfil: " + error.message);
    }
}

function renderUserData(user) {
    const headerName = document.getElementById('header-user-name');
    if(headerName) headerName.textContent = user.nombre || 'Usuario';

    document.getElementById('name').value = user.nombre || '';
    document.getElementById('phone').value = user.telefono || '';
    document.getElementById('mail').value = user.correo || '';
}

function renderAddresses(direcciones) {
    const container = document.getElementById('addresses-container');
    if (!container) return;
    container.innerHTML = '';

    if (!direcciones || direcciones.length === 0) {
        container.innerHTML = '<p>No tienes direcciones guardadas. <a href="#" onclick="openAddAddressModal(event)">Agrega una</a></p>';
        return;
    }

    // Ordenar por ID descendente para ver las nuevas arriba
    direcciones.sort((a, b) => b.idDireccion - a.idDireccion);

    direcciones.forEach(dir => {
        const div = document.createElement('div');
        div.className = 'address';
        div.innerHTML = `
            <div class="address-info">
                <span class="address-name">${dir.nombre || 'Dirección'}</span>
                <div class="two-column-wrapper">
                    <span><p>${dir.calle} #${dir.numeroExterior || 'S/N'}</p></span>
                    <span><p>${dir.colonia}</p></span>
                </div>
                <div class="two-column-wrapper">
                    <span><p>${dir.ciudad}</p></span>
                    <span><p>${dir.estado}, CP ${dir.codigoPostal}</p></span>
                </div>
            </div>
            <a href="#" class="edit-icon" onclick="openEditAddress(event, ${dir.idDireccion})">
                <img src="icons/edit.svg" alt="editar">
            </a>
            <a href="#" class="delete-icon" onclick="deleteAddress(event, ${dir.idDireccion})">
                <img src="icons/trash.svg" alt="eliminar">
            </a>
        `;
        container.appendChild(div);
    });
}

// --- ACTUALIZAR PERFIL ---
async function updateProfile(event) {
    event.preventDefault();
    
    // Elementos de feedback
    const errorDiv = document.getElementById('profile-error');
    const successDiv = document.getElementById('profile-success');
    errorDiv.style.display = 'none';
    successDiv.style.display = 'none';

    // Obtener valores
    const nombre = document.getElementById('name').value.trim();
    const telefono = document.getElementById('phone').value.trim();
    const actualPswd = document.getElementById('actual-pswd').value;
    const newPswd = document.getElementById('new-pswd').value;

    // VALIDACIONES LOCALES
    if (!nombre) {
        showError(errorDiv, "El nombre no puede estar vacío.");
        return;
    }
    if (!telefono || isNaN(telefono) || telefono.length < 10) {
        showError(errorDiv, "Ingresa un teléfono válido (mínimo 10 dígitos).");
        return;
    }
    if (newPswd && !actualPswd) {
        showError(errorDiv, "Para cambiar la contraseña, debes ingresar tu contraseña actual.");
        return;
    }

    try {
        const token = localStorage.getItem('token');
        const payload = {
            nombre: nombre,
            telefono: telefono,
            passwordActual: actualPswd || null,
            nuevaPassword: newPswd || null
        };

        const response = await fetch('http://localhost:8080/API-Videojuegos/api/users/profile', {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });

        const data = await response.json();

        if (!response.ok) {
            throw new Error(data.error || "Error al actualizar perfil");
        }

        // Éxito
        successDiv.style.display = 'block';
        
        // Limpiar campos de contraseña
        document.getElementById('actual-pswd').value = '';
        document.getElementById('new-pswd').value = '';
        
        // Recargar datos para asegurar consistencia
        loadUserProfile();

    } catch (error) {
        showError(errorDiv, error.message);
    }
}

// --- GESTIÓN DE DIRECCIONES (MODAL) ---

function openAddAddressModal(event) {
    if(event) event.preventDefault();
    
    // Limpiar formulario y poner en modo "Crear"
    document.getElementById('address-form').reset();
    document.getElementById('address-id').value = ''; // ID vacío = Crear
    document.getElementById('modal-title').textContent = "Agregar Dirección";
    document.getElementById('address-error').style.display = 'none';
    
    window.location.hash = "add-address-modal";
}

function openEditAddress(event, id) {
    if(event) event.preventDefault();

    // Buscar la dirección en los datos locales
    const direccion = currentUserData.direcciones.find(d => d.idDireccion === id);
    if (!direccion) return;

    // Llenar formulario
    document.getElementById('address-id').value = direccion.idDireccion;
    document.getElementById('address-name').value = direccion.nombre;
    document.getElementById('street').value = direccion.calle;
    document.getElementById('ext-number').value = direccion.numeroExterior;
    document.getElementById('colonia').value = direccion.colonia;
    document.getElementById('postal-code').value = direccion.codigoPostal;
    document.getElementById('city').value = direccion.ciudad;
    document.getElementById('state').value = direccion.estado;

    document.getElementById('modal-title').textContent = "Editar Dirección";
    document.getElementById('address-error').style.display = 'none';

    window.location.hash = "add-address-modal";
}

function closeModal(event) {
    if(event) event.preventDefault();
    window.location.hash = "";
}

async function handleAddressSubmit(event) {
    event.preventDefault();
    
    const errorDiv = document.getElementById('address-error');
    errorDiv.style.display = 'none';

    // Obtener datos
    const id = document.getElementById('address-id').value;
    const isEdit = !!id;

    // Aseguramos que los nombres de las propiedades coincidan EXACTAMENTE con DireccionDTO.java
    const payload = {
        nombre: document.getElementById('address-name').value.trim(),
        calle: document.getElementById('street').value.trim(),
        numeroExterior: document.getElementById('ext-number').value.trim(),
        colonia: document.getElementById('colonia').value.trim(),
        codigoPostal: document.getElementById('postal-code').value.trim(),
        ciudad: document.getElementById('city').value.trim(),
        estado: document.getElementById('state').value.trim()
    };

    // Validar campos vacíos
    for (const key in payload) {
        if (!payload[key]) {
            errorDiv.textContent = "Todos los campos son obligatorios.";
            errorDiv.style.display = 'block';
            return;
        }
    }

    try {
        const token = localStorage.getItem('token');
        const url = isEdit 
            ? `http://localhost:8080/API-Videojuegos/api/users/addresses/${id}`
            : 'http://localhost:8080/API-Videojuegos/api/users/addresses';
        
        const method = isEdit ? 'PUT' : 'POST';

        const response = await fetch(url, {
            method: method,
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });

        // Manejo robusto de la respuesta
        const contentType = response.headers.get("content-type");
        
        if (!response.ok) {
            if (contentType && contentType.includes("application/json")) {
                const data = await response.json();
                throw new Error(data.error || "Error al guardar dirección");
            } else {
                // Aquí capturamos el error "Unrecognized field..."
                const textError = await response.text();
                console.error("Error del servidor (Texto):", textError);
                
                // Extraer mensaje útil si es posible, o mostrar genérico
                if (textError.includes("Unrecognized field")) {
                    throw new Error("Error interno: El servidor no reconoció un campo enviado. Revisa la consola.");
                }
                throw new Error("Error del servidor: " + response.status + ". " + textError);
            }
        }

        // Éxito: cerrar modal y recargar
        closeModal();
        await loadUserProfile(); 

    } catch (error) {
        console.error(error);
        errorDiv.textContent = error.message;
        errorDiv.style.display = 'block';
    }
}

async function deleteAddress(event, id) {
    if(event) event.preventDefault();

    if(!confirm("¿Estás seguro de que deseas eliminar esta dirección?")) return;

    try {
        const token = localStorage.getItem('token');
        const response = await fetch(`http://localhost:8080/API-Videojuegos/api/users/addresses/${id}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error("No se pudo eliminar la dirección");
        }

        await loadUserProfile();

    } catch (error) {
        alert(error.message);
    }
}

function showError(element, message) {
    element.textContent = message;
    element.style.display = 'block';
}