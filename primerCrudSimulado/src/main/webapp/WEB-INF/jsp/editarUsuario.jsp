<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>

    <!-- Tipografía agradable -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/stylEditUser.css">


</head>
<body>
    <div class="page-wrapper">
        <div class="card" role="main" aria-labelledby="titleEditar">
            <h2 id="titleEditar">Editar Usuario</h2>

            <form id="editarForm" novalidate>
                <div>
                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" required>
                </div>

                <div>
                    <label for="paterno">Paterno</label>
                    <input type="text" id="paterno">
                </div>

                <div>
                    <label for="materno">Materno</label>
                    <input type="text" id="materno">
                </div>

                <div>
                    <label for="edad">Edad</label>
                    <input type="number" id="edad" min="0">
                </div>

                <div class="full">
                    <label for="mail">Mail</label>
                    <input type="email" id="mail" required>
                </div>

                <div class="full">
                    <label for="rol">Rol</label>
                    <input type="text" id="rol">
                </div>

                <div class="actions full">
                    <button type="submit" class="btn btn-update">Actualizar</button>
                    <button type="button" id="cancelBtn" class="btn btn-cancel">Cancelar</button>
                </div>

                <div id="msg" aria-live="polite"></div>
            </form>
        </div>
    </div>

    <script>
        window.onload = async () => {
            const token = sessionStorage.getItem("jwtToken");
            if (!token) {
                window.location.href = "/usuarios/login";
                return;
            }

            const pathParts = window.location.pathname.split("/");
            const id = pathParts[pathParts.length - 1];

            try {
                const response = await fetch(`/api/usuarios/${id}`, {
                    headers: { "Authorization": "Bearer " + token }
                });

                if (!response.ok) throw new Error("Error al obtener usuario");

                const usuario = await response.json();

                document.getElementById("nombre").value = usuario.nombre || "";
                document.getElementById("paterno").value = usuario.paterno || "";
                document.getElementById("materno").value = usuario.materno || "";
                document.getElementById("edad").value = usuario.edad || "";
                document.getElementById("mail").value = usuario.mail || "";
                document.getElementById("rol").value = usuario.rol || "";

            } catch (err) {
                document.getElementById("msg").innerText = "No se pudo cargar el usuario";
            }

            document.getElementById("cancelBtn").addEventListener("click", () => {
                window.location.href = "/usuarios/listaUsuarios";
            });
        };

        document.getElementById("editarForm").addEventListener("submit", async function(e) {
            e.preventDefault();
            const token = sessionStorage.getItem("jwtToken");
            const pathParts = window.location.pathname.split("/");
            const id = pathParts[pathParts.length - 1];

            const payload = {
                nombre: document.getElementById("nombre").value,
                paterno: document.getElementById("paterno").value,
                materno: document.getElementById("materno").value,
                edad: document.getElementById("edad").value,
                mail: document.getElementById("mail").value,
                rol: document.getElementById("rol").value
            };

            try {
                const response = await fetch(`/api/usuarios/editar/${id}`, {
                    method: "PUT",
                    headers: {
                        "Authorization": "Bearer " + token,
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(payload)
                });

                const resultado = await response.json();
                if (resultado === true) {
                    document.getElementById("msg").innerText = "Usuario actualizado correctamente";
                    setTimeout(() => window.location.href = "/usuarios/listaUsuarios", 2000);
                } else {
                    document.getElementById("msg").innerText = "Error al actualizar usuario";
                }

            } catch (err) {
                document.getElementById("msg").innerText = "Error de conexión con el servidor";
            }
        });
    </script>
</body>
</html>

