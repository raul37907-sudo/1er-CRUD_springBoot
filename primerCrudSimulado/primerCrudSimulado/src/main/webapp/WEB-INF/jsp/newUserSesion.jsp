<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo Usuario para acceso</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        form {
            margin-top: 20px;
        }
        label {
            display: block;       /* fuerza que cada label quede en su propio renglón */
            margin-top: 10px;
            font-weight: bold;
        }
        input {
            width: 250px;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .success {
            color: green;
            margin-top: 10px;
        }
        .error {
            color: red;
            margin-top: 10px;
        }
    </style>
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

    <label for="rol">Rol:</label>
    <input type="text" id="rol" name="rol" />

    <input type="submit" value="Registrar"/>
</form>

<script>
document.getElementById("registroForm").addEventListener("submit", async function(e) {
    e.preventDefault();

    const feedback = document.getElementById("mensajeRegistro");
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    // Validación en el front
    if (password !== confirmPassword) {
        feedback.innerHTML = `<p class="error">Las contraseñas no coinciden</p>`;
        return;
    }

    const payload = {
        mail: document.getElementById("mail").value,
        password: password,
        rol: document.getElementById("rol").value
    };

   try {
       const response = await fetch("http://localhost:8080/auth/creaNuevoAcceso", {
           method: "POST",
           headers: { "Content-Type": "application/json" },
           body: JSON.stringify(payload)
       });

       const data = await response.json();

       if (response.ok) {
           // Éxito
           feedback.innerHTML = `<p class="success">${data.message}</p>`;
           setTimeout(() => window.location.href = "/usuarios/login", 2000);
       } else {
           // Error (400)
           feedback.innerHTML = `<p class="error">El  E-mail  ya existe </p>`;
       }
   } catch (error) {
       console.error("Error:", error);
       feedback.innerHTML = `<p class="error">Error de conexión con el servidor</p>`;

   }


});

</script>







</body>
</html>