document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("loginForm");
    const feedback = document.getElementById("loginMsg");
    const userMail = document.getElementById("userMail");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const mail = document.getElementById("mail").value.trim();
        const password = document.getElementById("password").value.trim();

        if (!mail || !password) {
            feedback.className = "error";
            feedback.innerText = "Por favor llena todos los campos.";
            return;
        }

        try {
            const response = await fetch("http://localhost:8080/auth/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ mail, password })
            });

            const data = await response.json();

            if (response.ok && data.token) {
                feedback.className = "success";
                feedback.innerText = "Login correcto";

                // Guardar usuario y token en sessionStorage
                sessionStorage.setItem("jwtToken", data.token);
                sessionStorage.setItem("mail", mail);



                // Redirigir a la vista listaUsuarios
                window.location.href = "/usuarios/listaUsuarios";


            } else {
                feedback.className = "error";
                feedback.innerText = data.message || "Credenciales inválidas";
            }
        } catch (error) {
            console.error("Error:", error);
            feedback.className = "error";
            feedback.innerText = "Error de conexión con el servidor";
        }
    });

    // 👇 si ya hay usuario logueado, mostrar su correo
    const savedMail = sessionStorage.getItem("mail");
    if (savedMail) {
        userMail.innerText = savedMail;
    }
});
