<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo Usuario para acceso</title>
    <link rel="stylesheet" href="/css/stylNewUserSesion.css">
 <!--  local  host  -->
<script src="/js/newUserSesion.js" defer></script>

</head>
<body>


<!-- Contenedor para mensajes -->
<div id="mensajeRegistro" ></div>

<form id="registroForm">
    <h2>Crea tu cuenta</h2>

    <label for="mail">Correo electrónico:</label>
    <input type="email" id="mail" name="mail" required />

    <label for="password">Contraseña:</label>
    <input type="password" id="password" name="password" required />

    <label for="confirmPassword">Confirmar contraseña:</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required />

    <label for="id_rol">Rol:</label>
    <select  id="id_rol" name="id_rol" required>
        <option value="">Seleccione un rol</option>
    </select>

    <input type="submit" value="Registrar"/>
</form>



</body>
</html>