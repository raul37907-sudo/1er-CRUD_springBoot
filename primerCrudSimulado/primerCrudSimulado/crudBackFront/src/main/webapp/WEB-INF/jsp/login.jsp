<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <!-- CSS externo -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylesLogin.css">
</head>
<body>
<div class="login-box">
    <!-- 👇 correo del usuario logueado <div class="user-mail" id="userMail">
    </div>-->

    <h2>Iniciar Sesión</h2>
    <div id="loginMsg"></div>

    <form id="loginForm" method="POST" action=""">  <!-- limpia los  datps  de retoeno de l a url -->
        <label for="mail">Correo electrónico:</label>
        <input type="email" id="mail" name="mail" required placeholder="Ingresa tu E-mail" />

        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" required placeholder="Ingresa tu Contraseña" />

        <button type="submit">Login</button>
    </form>

    <!-- 👇 leyenda para registro -->
    <div class="register-link">
        ¿No tienes cuenta? <a href="${pageContext.request.contextPath}/usuarios/crearNewUser">Regístrate</a>
    </div>
</div>

<!-- JS externo  carga la logica -->
<script src="${pageContext.request.contextPath}/js/headerLogin.js"></script>
</body>
</html>

