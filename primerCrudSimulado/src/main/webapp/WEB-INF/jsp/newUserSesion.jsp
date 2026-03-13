<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo Usuario para acceso</title>
    <link rel="stylesheet" href="/css/newSesion.css">


</head>
<body>
<h2>Registrar Nuevo Acceso con E-mail</h2>

<!-- Contenedor para mensajes -->
<div id="mensajeRegistro"></div>

<form id="registroForm">
    <label for="mail">Correo electrónico:</label>
    <input type="email" id="mail" name="mail" required />

    <label for="password">Contraseña:</label>
    <input type="password" id="password" name="password" required />

    <label for="confirmPassword">Confirmar contraseña:</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required />

    <label for="id_rol">Rol:</label>
    <select  id="id_rol" name="id_rol" required>
        <option values="">
    </select>

    <input type="submit" value="Registrar"/>
</form>

<script>
async function cargarRoles() {
    const select = document.getElementById("id_rol");
    try {
        // Ajusta la URL al endpoint que devuelve los roles en JSON
        const res = await fetch("http://localhost:8080/auth/api/roles");

        if (!res.ok) throw new Error("No se pudieron cargar los roles");
        const roles = await res.json(); // espera: [{ "id_rol":1, "rol":"Administrador" }, ...]
        // Vaciar opciones previas (excepto la primera)
        select.length = 1;
        roles.forEach(r => {
            const opt = document.createElement("option");
            opt.value = r.id_rol;        // valor enviado al backend
            opt.textContent = r.rol;     // texto visible al usuario
            select.appendChild(opt);



        });
    } catch (err) {
        console.error("Error cargando roles:", err);
        const mensaje = document.getElementById("mensajeRegistro");
        mensaje.innerHTML = `<p class="error">No se pudieron cargar los roles. Intente más tarde.</p>`;
    }
}

document.addEventListener("DOMContentLoaded", cargarRoles);
document.querySelectorAll('#mensajeRegistro').length ;

document.getElementById("registroForm").addEventListener("submit", async function(e) {
    e.preventDefault();

    const feedback = document.getElementById("mensajeRegistro");
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const idRol = document.getElementById("id_rol").value;

    if (password !== confirmPassword) {
        feedback.innerHTML = `<p class="error">Las contraseñas no coinciden</p>`;
        return;
    }

    if (!idRol) {
        feedback.innerHTML = `<p class="error">Seleccione un rol</p>`;
        return;
    }

    const payload = {
        mail: document.getElementById("mail").value,
        password: password,
        id_rol: parseInt(idRol, 10)   // enviar número, no texto
    };

   try {
       const response = await fetch("http://localhost:8080/auth/creaNuevoAcceso", {
           method: "POST",
           headers: { "Content-Type": "application/json" },
           body: JSON.stringify(payload)
       });

       const data = await response.json();
 console.log('response.ok', response.ok, 'status', response.status);
 console.log('data', data.message);
 console.log('typeof message:', typeof data.message, 'length:', (data.message||'').length);
 console.log('feedback element before:', feedback);

       if (!response.ok) {
              //   feedback.innerHTML = `<p class="error">${data.message}</p>`;


                 feedback.innerHTML = `<p class="error">El correo  ya existe</p>`;


       } else {
          // feedback.innerHTML = `<p class="success">${data.message }</p>`;  no  esta  imprimiendo  el  mensaje que llega  del  back
             feedback.innerHTML = `<p class="success">Usuario registrado  de forma  correcta </p>`;
           setTimeout(() => window.location.href = "/usuarios/login", 2000);
       }



   } catch (error) {
       console.error("Error:", error);
       feedback.innerHTML = `<p class="error">Error de conexión con el servidor</p>`;
   }



});
</script>

</body>
</html>