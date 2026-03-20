document.addEventListener("DOMContentLoaded", () => {
    async function cargarUsuarios() {
        const token = sessionStorage.getItem("jwtToken");
        if (!token) {
            window.location.href = "/usuarios/login";
            return;
        }

        try {
            const response = await fetch("/api/usuarios/listar", {
                method: "GET",
                headers: { "Authorization": "Bearer " + token }
            });

            if (response.status === 401) {
                alert("Sesión expirada");
                window.location.href = "/usuarios/login";
                return;
            }

            const usuarios = await response.json();
            renderizarTabla(usuarios);

        } catch (error) {
            document.getElementById("usuariosContainer").innerHTML =
                "<p style='color:red; text-align:center;'>Error al conectar con la API.</p>";
        }
    }

    function renderizarTabla(usuarios) {
        let html = "<table><tr><th>ID</th><th>Nombre</th><th>Paterno</th><th>Materno</th><th>Edad</th><th>Mail</th><th>Rol</th><th>Acciones</th></tr>";

        usuarios.forEach(u => {
            html += `<tr>
                <td>${u.id || ''}</td>
                <td>${u.nombre || ''}</td>
                <td>${u.paterno ? u.paterno.trim() : ''}</td>
                <td>${u.materno ? u.materno.trim() : ''}</td>
                <td>${u.edad || ''}</td>
                <td>${u.mail || ''}</td>
                <td>${u.nomRol ? u.nomRol.trim() : ''}</td>
                <td>
                    <button class="btn btn-edit" onclick="location.href='/usuarios/editar/${u.id}'">Editar</button>
                    <button class="btn btn-delete" onclick="eliminarUsuario('${u.mail}')">Eliminar</button>
                </td>
            </tr>`;
        });
        html += "</table>";
        document.getElementById("usuariosContainer").innerHTML = html;
    }

    function crearUsuario() {
        window.location.href = "/usuarios/crear";
    }

    async function eliminarUsuario(mail) {
        const token = sessionStorage.getItem("jwtToken");
        if (!confirm("¿Seguro que deseas eliminar este usuario?")) return;

        try {
            const response = await fetch(`/api/usuarios/eliminar/${mail}`, {
                method: "DELETE",
                headers: { "Authorization": "Bearer " + token }
            });

            if (response.ok) {
                alert("Usuario eliminado correctamente");
                cargarUsuarios();
            } else {
                alert("Error al eliminar usuario");
            }
        } catch (error) {
            alert("Error de conexión con el servidor");
        }
    }

    // Inicialización al cargar la página
    cargarUsuarios();
    const savedMail = sessionStorage.getItem("mail");
    document.getElementById("userMail").innerText = savedMail || "Usuario";

    document.getElementById("btnLogout").addEventListener("click", async () => {
        try {
            await fetch("/auth-session/clear", { method: "POST" });
        } catch (e) { console.error("Error en logout servidor", e); }

        sessionStorage.clear();
        window.location.href = "/usuarios/login";
    });
});
