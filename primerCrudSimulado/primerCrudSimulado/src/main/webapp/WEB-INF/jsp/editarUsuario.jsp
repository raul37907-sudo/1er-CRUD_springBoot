<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
    <style>
        form {
            width: 60%;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 6px;
            background-color: #f9f9f9;
        }
        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }
        input {
            width: 95%;
            padding: 8px;
            margin-top: 5px;
        }
        .btn {
            margin-top: 15px;
            padding: 8px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            color: white;
        }
        .btn-update { background-color: #007BFF; }
        .btn-cancel { background-color: #6C757D; }
        #msg {
            text-align: center;
            margin-top: 15px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h2 style="text-align:center;">Editar Usuario</h2>

    <form id="editarForm">
        <label>Nombre:</label>
        <input type="text" id="nombre" required>

        <label>Paterno:</label>
        <input type="text" id="paterno">

        <label>Materno:</label>
        <input type="text" id="materno">

        <label>Edad:</label>
        <input type="number" id="edad">

        <label>Mail:</label>
        <input type="email" id="mail" required>

        <label>Rol:</label>
        <input type="text" id="rol">

        <button type="submit" class="btn btn-update">Actualizar</button>
        <button type="button" id="cancelBtn" class="btn btn-cancel">Cancelar</button>
    </form>

    <div id="msg"></div>

    <script>
        window.onload = async () => {
            const token = sessionStorage.getItem("jwtToken");
            if (!token) {
                window.location.href = "/usuarios/login";
                return;
            }

            // ✅ Obtener id desde el path /usuarios/editar/{id}
            const pathParts = window.location.pathname.split("/");
            const id = pathParts[pathParts.length - 1];

            try {
                const response = await fetch(`/api/usuarios/${id}`, {
                    headers: { "Authorization": "Bearer " + token }
                });

                if (!response.ok) throw new Error("Error al obtener usuario");

                const usuario = await response.json();

                // Rellenar formulario
                document.getElementById("nombre").value = usuario.nombre || "";
                document.getElementById("paterno").value = usuario.paterno || "";
                document.getElementById("materno").value = usuario.materno || "";
                document.getElementById("edad").value = usuario.edad || "";
                document.getElementById("mail").value = usuario.mail || "";
                document.getElementById("rol").value = usuario.rol || "";

            } catch (err) {
                document.getElementById("msg").innerText = "No se pudo cargar el usuario";
            }

            // ✅ Botón cancelar → regresa a la lista
            document.getElementById("cancelBtn").addEventListener("click", () => {
                window.location.href = "/usuarios/listaUsuarios";
            });
        };

        // ✅ Enviar actualización
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

