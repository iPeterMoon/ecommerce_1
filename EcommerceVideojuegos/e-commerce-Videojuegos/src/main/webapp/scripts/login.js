const API_BASE_URL = 'http://localhost:8080/API-Videojuegos/api/'; 

const VIEWS_SERVLET_URL = 'InicioSesion'; 

//Maneja el evento de submit del formulario de login
async function handleLogin(event) {
    event.preventDefault(); // Prevenir el envío tradicional del form
    
    // Obtener los valores del formulario
    const email = document.getElementById('mail').value;
    const password = document.getElementById('pswd').value;
    const submitBtn = document.getElementById('submitBtn');
    const errorDiv = document.getElementById('errorMessage');
    
    // Deshabilitar el botón mientras se procesa
    submitBtn.disabled = true;
    submitBtn.textContent = 'Iniciando sesión...';
    errorDiv.style.display = 'none';
    
    try {
        // Hacer la petición POST a la API
        const response = await fetch(`${API_BASE_URL}auth/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: email,
                password: password
            })
        });
        
        if (response.ok) {
            // Login exitoso
            const data = await response.json();
            
            // Guardar el token y datos del usuario en localStorage
            localStorage.setItem('token', data.token);
            localStorage.setItem('usuario', JSON.stringify(data.usuario));
            
            // También guardar en sessionStorage como respaldo
            sessionStorage.setItem('token', data.token);
            sessionStorage.setItem('usuario', JSON.stringify(data.usuario));
            
            const servletResponse = await fetch(VIEWS_SERVLET_URL, {
                method: 'POST',
                headers: {'Content-Type': 'application/json' },
                body: JSON.stringify(data.usuario)
            });
            
            if(!servletResponse.ok) {
                console.error('Error al sincronizar la sesión en el servidor');
                localStorage.removeItem('token');
                localStorage.removeItem('usuario');
                sessionStorage.removeItem('token');
                sessionStorage.removeItem('usuario');
                mostrarError('Error de servidor. Intente de nuevo.')
            }
            
            // Redirigir a la página principal
            window.location.href = 'index.jsp';
            
        } else {
            // Login fallido
            const errorData = await response.json();
            mostrarError(errorData.error || 'Correo o contraseña incorrectos. Intenta de nuevo.');
        }
        
    } catch (error) {
        console.error('Error al iniciar sesión:', error);
        mostrarError('Error de conexión. Por favor, intenta de nuevo.');
    } finally {
        // Rehabilitar el botón
        submitBtn.disabled = false;
        submitBtn.textContent = 'Iniciar sesión';
    }
}

//Muestra un mensaje de error en el formulario
function mostrarError(mensaje) {
    const errorDiv = document.getElementById('errorMessage');
    errorDiv.textContent = mensaje;
    errorDiv.style.display = 'block';
}

/**
 * Verifica si hay un parámetro de error en la URL (compatibilidad con versión anterior)
 */
window.addEventListener('DOMContentLoaded', () => {
    const urlParams = new URLSearchParams(window.location.search);
    const error = urlParams.get('error');
    
    if (error === '1') {
        mostrarError('Correo o contraseña incorrectos. Intenta de nuevo.');
    }
});