<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Usuarios Registrados</title>
    <link rel="stylesheet" href="/css/stylesListUser.css">
    <script src="/js/headerListUser.js" defer></script>
    <script src="/js/eliminarUser.js" defer></script>

</head>
<body>
    <!-- Bloque de correo + logout arriba a la derecha -->
    <div class="user-profile-container">
        <div class="user-mail" id="userMail"></div>
        <button id="btnLogout" class="btn btn-logout">Logout</button>
    </div>

    <h2 style="text-align:center;">Usuarios Registrados</h2>

    <div id="usuariosContainer"></div>

    <div style="text-align:center; margin-top:20px;">
<!-- botón que actúa como enlace -->
<a href="${pageContext.request.contextPath}/usuarios/crear" class="btn btn-create">Crear Nuevo Usuario</a>
    </div>

</body>
</html>






