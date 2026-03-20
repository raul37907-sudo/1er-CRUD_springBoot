<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>

    <!-- Tipografía agradable -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/stylEditUser.css">
<script src="/js/headerEditar.js" defer></script> <!--  local  host -->


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
                  <label for="rolAct">Rol actual</label>
                  <input type="text" id="rolAct" readonly>
              </div>



                <div class="full">
                <label for="idRolDto">Rol</label>
                        <select  id="idRolDto" name="idRolDto" required>
                                 <option value="">Seleccione un rol</option>
                       </select>
                </div>

                <div class="actions full">
                    <button type="submit" class="btn btn-update">Actualizar</button>
                    <button type="button" id="cancelBtn" class="btn btn-cancel">Cancelar</button>
                </div>

                <div id="msg" aria-live="polite"></div>
            </form>
        </div>
    </div>


</body>
</html>

