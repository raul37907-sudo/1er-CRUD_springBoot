    // ✅ Cargar roles desde  la ruta generaica
           async function cargarRoles() {
      console.log("Entrando a cargarRoles...");
               const select = document.getElementById("idRolDto");
               try {
                   // Ajusta la URL al endpoint que devuelve los roles en JSON
                   const res = await fetch("http://localhost:8082/api/roles/listar");

                   if (!res.ok) throw new Error("No se pudieron cargar los roles");
                   const roles = await res.json(); // espera: [{ "idRolDto":1, "rolNomDto":"Administrador" }, ...]
     console.log("Roles recibidos:", roles);

     select.innerHTML = '<option value="">Seleccione un rol</option>';
                   // Vaciar opciones previas (excepto la primera)
                 //  select.length = 1;
                   roles.forEach(r => {
                       const opt = document.createElement("option");
                       opt.value = r.idRolDto; // valor enviado del backend es el  id  del  rol debe de ser  el  mismo  nombre  qu e tiene el id del  dto  qu e regresa
                       opt.textContent = r.rolNomDto;// texto visible al usuario el nombre  del  rol
                       select.appendChild(opt);
                        console.log("Roles recibidos para actualizar  un usuario  de la tabla :", opt);

                   });
               } catch (err) {
                   console.error("Error cargando roles:", err);
                   const mensaje = document.getElementById("mensajeRegistro");
                   mensaje.innerHTML = `<p class="error">No se pudieron cargar los roles. Intente más tarde.</p>`;
               }
           }

cargarRoles();
   // document.addEventListener("DOMContentLoaded", cargarRoles);
    //se ejecutará cuando todo el HTML haya sido cargado y parseado por el navegador.
    //Es parecido a window.onload, pero más rápido porque no espera a que se carguen imágenes, hojas de estilo, etc.
    //En tu caso, asegura que el <select id="idRolDto"> ya existe en el DOM antes de intentar llenarlo.


    //document.querySelectorAll('#mensajeRegistro').length ;
     //busca todos los elementos en el DOM que tengan el id="mensajeRegistro".
    //Como los id deben ser únicos en un documento HTML, normalmente habrá 0 o 1 elemento.
    //.length devuelve cuántos encontró:  0 → no existe ningún elemento con ese id.
                                      //    1 → existe un elemento con ese id.

                                    //      >1 → sería un error de HTML porque no deberías repetir el mismo id.


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
// es lo que  viaja  en  el  objeto  que  llega del back
                const usuario = await response.json();

                document.getElementById("nombre") .value = usuario.nombre  || "";
                document.getElementById("paterno").value = usuario.paterno || "";
                document.getElementById("materno").value = usuario.materno || "";
                document.getElementById("edad")   .value = usuario.edad    || "";
                document.getElementById("mail")   .value = usuario.mail    || "";
                document.getElementById("rolAct") .value = usuario.nomRol  || "";


// marcar en el select el rol actual
const select = document.getElementById("idRolDto");
if (usuario.idRol) {
    select.value = usuario.idRol;
}
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
//  los identificadores del  inicio  deben  ser exactamente  los  mismos del  dto  al que llegan
            const payload = {
                nombre: document.getElementById("nombre").value,
                paterno: document.getElementById("paterno").value,
                materno: document.getElementById("materno").value,
                edad: document.getElementById("edad").value,
                mail: document.getElementById("mail").value,
                idRolDto : document.getElementById("idRolDto").value
            };
console.log("Payload que se enviará al backend:", payload);
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
