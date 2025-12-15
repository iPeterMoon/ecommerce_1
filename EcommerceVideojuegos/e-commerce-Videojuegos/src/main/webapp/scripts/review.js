/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
let calificacionActual = 0;

function abrirModalResena(idVideojuego) {
    document.getElementById('modalVideojuegoId').value = idVideojuego;
    document.getElementById('modalResena').style.display = 'flex';
    document.getElementById('resenaTexto').value = '';
    setRating(0);

    console.log("Abriendo modal para videojuego ID:", idVideojuego);//debug para verificar q llega el id 
}

function cerrarModalResena() {
    document.getElementById('modalResena').style.display = 'none';
}

function setRating(n) {
    calificacionActual = n;
    const estrellas = document.querySelectorAll('.star');
    estrellas.forEach((star, index) => {
        if (index < n) {
            star.style.color = '#f59e0b';
        } else {
            star.style.color = '#ccc'; 
        }
    });
}

async function enviarResena() {
    const idJuego = document.getElementById('modalVideojuegoId').value;
    const texto = document.getElementById('resenaTexto').value;
    const token = localStorage.getItem("token"); // para recuperar el token

    if (calificacionActual === 0) {
        alert("Por favor selecciona una calificación de estrellas.");
        return;
    }

    if (!token) {
        alert("No has iniciado sesión.");
        window.location.href = "login.jsp";
        return;
    }

    const datosResena = {//datos a enviar
        idProducto: idJuego,
        calificacion: calificacionActual,
        comentario: texto
    };

    try {
        const response = await fetch('http://localhost:8080/API-Videojuegos/api/resenas', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify(datosResena)
        });

        if (response.ok) {
            alert("¡Reseña publicada exitosamente!");
            cerrarModalResena();
        } else {
            const errorData = await response.json();
            alert("Error: " + (errorData.error || "No se pudo publicar la reseña"));
        }
    } catch (error) {
        console.error("Error al enviar reseña:", error);
        alert("Error de conexión con el servidor.");
    }
}

window.onclick = function (event) {//esto nomas es para q se cierre el modal si hacen click fuera de el
    const modal = document.getElementById('modalResena');
    if (event.target == modal) {
        cerrarModalResena();
    }
}

