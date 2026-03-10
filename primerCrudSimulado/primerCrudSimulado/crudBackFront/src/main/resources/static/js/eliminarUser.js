// usuarios.js
document.addEventListener("DOMContentLoaded", () => {
// ✅ Validar token y redirigir al login si no existe
function validarToken() {
    const token = sessionStorage.getItem("jwtToken");
    if (!token) {
        window.location.href = "/usuarios/login";
        return null;
    }
    return token;
}

// ✅ Función para eliminar usuario
async function eliminarUsuario(mail) {
    const token = validarToken();
    if (!token) return;

    if (confirm("¿Seguro que deseas eliminar al usuario con correo: " + mail + "?")) {
        try {
            const response = await fetch("/api/usuarios/mail/" + mail, {
                method: "DELETE",
                headers: {
                    "Authorization": "Bearer " + token
                }
            });
            if (response.ok) {
                alert("Usuario eliminado correctamente");
                cargarUsuarios(); // recargar la tabla
            } else if (response.status === 401) {
                alert("Sesión expirada, redirigiendo al login...");
                window.location.href = "/usuarios/login";
            } else {
                alert("Error al eliminar usuario");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("Error de conexión al eliminar usuario");
        }
    } else {
        alert("Eliminación cancelada");
    }
}
});