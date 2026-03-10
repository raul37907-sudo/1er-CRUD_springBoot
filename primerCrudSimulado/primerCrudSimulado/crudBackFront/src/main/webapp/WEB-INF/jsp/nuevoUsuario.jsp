<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Nuevo Usuario</title>
</head>
<body>
    <h2>Registrar Usuario</h2>
    <form id="crearUsuarioForm">
        Nombre: <input type="text" name="nombre" required/><br/>
        Paterno: <input type="text" name="paterno" required/><br/>
        Materno: <input type="text" name="materno"/><br/>
        Edad: <input type="number" name="edad" required/><br/>
        Email: <input type="email" name="mail" required/><br/>
        Rol: <input type="text" name="rol"/><br/>
        <button type="submit">Guardar</button>
        <button type="button" id="cancelBtn">Cancelar</button>
    </form>

    <script>
      window.onload = () => {
        const token = sessionStorage.getItem("jwtToken");
        if (!token) {
          window.location.href = "/usuarios/login";
          return;
        }

        // ✅ Manejar envío del formulario con fetch
        document.getElementById("crearUsuarioForm").addEventListener("submit", async (e) => {
          e.preventDefault(); // evitar submit tradicional

          const formData = new FormData(e.target);
          const usuario = Object.fromEntries(formData.entries());

          try {
            const response = await fetch("/api/usuarios/guardar", {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
              },
              body: JSON.stringify(usuario)
            });

            const resultado = await response.json(); // será true o false

            if (resultado === true) {
              alert("Usuario guardado correctamente");
              window.location.href = "/usuarios/listaUsuarios"; // redirigir a la lista
            } else {
              alert("Error al guardar usuario");
              // Redirigir a la vista listaUsuarios
                          window.location.href = "/usuarios/nuevoUsuario";
            }
          } catch (error) {
            console.error("Error:", error);
            alert("Error de conexión al guardar usuario");
          }
        });

        // ✅ Botón cancelar → regresa a la lista
        document.getElementById("cancelBtn").addEventListener("click", () => {
          window.location.href = "/usuarios/listaUsuarios";
        });
      };
    </script>
</body>
</html>
