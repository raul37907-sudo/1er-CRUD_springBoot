document.addEventListener("DOMContentLoaded", () => {
    const token = sessionStorage.getItem("jwtToken");
    if (!token) {
        window.location.href = "/usuarios/login";
    }

    // ✅ Cargar roles desde la ruta genérica
    async function cargarRoles() {
        console.log("Entrando a cargarRoles...");
        const select = document.getElementById("idRolDto");
        try {
            const res = await fetch("http://localhost:8082/api/roles/listar");
            if (!res.ok) throw new Error("No se pudieron cargar los roles");
            const roles = await res.json();
            console.log("Roles recibidos:", roles);

            select.innerHTML = '<option value="">Seleccione un rol</option>';
            roles.forEach(r => {
                const opt = document.createElement("option");
                opt.value = r.idRolDto;
                opt.textContent = r.rolNomDto;
                select.appendChild(opt);
            });
        } catch (err) {
            console.error("Error cargando roles:", err);
            const mensaje = document.getElementById("mensajeRegistro");
            mensaje.innerHTML = `<p class="error">No se pudieron cargar los roles. Intente más tarde.</p>`;
        }
    }

    cargarRoles();

    // ✅ Validaciones
    const regexSoloLetras = /^[A-Za-zÁÉÍÓÚáéíóúñÑ\s]+$/;

    const nombreInput = document.getElementById("nombre");
    const paternoInput = document.getElementById("paterno");
    const maternoInput = document.getElementById("materno");
    const edadInput = document.getElementById("edad");

    const mensajeNombre = document.getElementById("mensajeNombre");
    const mensajePaterno = document.getElementById("mensajePaterno");
    const mensajeMaterno = document.getElementById("mensajeMaterno");
    const mensajeEdad = document.getElementById("mensajeEdad");

    // Validación en tiempo real
    nombreInput.addEventListener("input", () => {
        mensajeNombre.textContent = regexSoloLetras.test(nombreInput.value) ? "" : "El nombre solo puede contener letras.";
    });

    paternoInput.addEventListener("input", () => {
        mensajePaterno.textContent = regexSoloLetras.test(paternoInput.value) ? "" : "El apellido paterno solo puede contener letras.";
    });

    maternoInput.addEventListener("input", () => {
        if (maternoInput.value && !regexSoloLetras.test(maternoInput.value)) {
            mensajeMaterno.textContent = "El apellido materno solo puede contener letras.";
        } else {
            mensajeMaterno.textContent = "";
        }
    });

    edadInput.addEventListener("input", () => {
        const edad = parseInt(edadInput.value, 10);
        if (isNaN(edad)) {
            mensajeEdad.textContent = "";
        } else if (edad < 18) {
            mensajeEdad.textContent = "Debes tener al menos 18 años para registrarte.";
        } else {
            mensajeEdad.textContent = "";
        }
    });

    // ✅ Manejar envío del formulario con validaciones
    const form = document.getElementById("crearUsuarioForm");
    form.addEventListener("submit", async function(e) {
        e.preventDefault(); // evitar submit tradicional

        // Validaciones finales
        if (!regexSoloLetras.test(nombreInput.value)) {
            mensajeNombre.textContent = "El nombre solo puede contener letras.";
            return;
        }
        if (!regexSoloLetras.test(paternoInput.value)) {
            mensajePaterno.textContent = "El apellido paterno solo puede contener letras.";
            return;
        }
        if (maternoInput.value && !regexSoloLetras.test(maternoInput.value)) {
            mensajeMaterno.textContent = "El apellido materno solo puede contener letras.";
            return;
        }
        const edad = parseInt(edadInput.value, 10);
        if (isNaN(edad) || edad < 18) {
            mensajeEdad.textContent = "Debes tener al menos 18 años para registrarte.";
            return;
        }

        // Si pasa todas las validaciones → enviar al backend
        const formData = new FormData(e.target);
        const usuario = Object.fromEntries(formData.entries());
        console.log("Usuario a guardar:", usuario);

        try {
            const response = await fetch("/api/usuarios/guardar", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(usuario)
            });

            const resultado = await response.json();
            if (resultado === true) {
                alert("Usuario guardado correctamente");
                window.location.href = "/usuarios/listaUsuarios";
            } else {
                alert("Error al guardar usuario en la base de datos");
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
});
