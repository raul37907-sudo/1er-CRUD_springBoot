<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Nuevo Usuario</title>

    <!-- JS externo  carga la logica para crear un  registrio  de la  tabla y  agregas el defer  para q ue se carge  proimero el dom y  despues el  js -->
  <!--    <script src="${pageContext.request.contextPath}/js/newRegistroTable.js" defer></script>   de esta  manera  no  se carga el  js  servidor externo   -->
<script src="/js/newRegistroTable.js" defer></script> <!--  local  host -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/newRegTable.css">

</head>
<body>

    <div id="mensajeRegistro"></div>  <!--  contenedor  para  mostrar  los  mensajes -->
    <form id="crearUsuarioForm">
    <h2>Crea  tu  perfil</h2>
        Nombre: <input type="text"id="nombre"  name="nombre" required/><br/>
        <span id="mensajeNombre" style="color:red;"></span><br/>
        Paterno: <input type="text"id="paterno" name="paterno" required/><br/>
        <span id="mensajePaterno" style="color:red;"></span><br/>
        Materno: <input type="text" id="materno" name="materno"/><br/>
        <span id="mensajeMaterno" style="color:red;"></span><br/>
        Edad: <input type="text" id="edad" name="edad" required/><br/>
        <span id="mensajeEdad" style="color: rgb(243, 3, 3);"></span><br/> <!--  contenedor para mostrar el mensaje de validación de edad -->
        Email: <input type="email" name="mail" required/><br/>
        <label for="idRolDto">Rol:</label>
            <select  id="idRolDto" name="idRolDto" required>
                <option value="">Seleccione un rol</option>
            </select>
<br/><br/>

        <button type="submit">Guardar</button>
        <button type="button" id="cancelBtn">Cancelar</button>
    </form>


</body>
</html>
