function handleLogout(event) {
    // 1. Prevenir que el formulario se envíe inmediatamente
    event.preventDefault();

    // 2. Limpiar el LocalStorage y SessionStorage (token y usuario)
    localStorage.removeItem("token");
    localStorage.removeItem("usuario");
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("usuario");

    // 3. Enviar el formulario manualmente al servidor para limpiar la sesión Java
    event.target.submit();
}
