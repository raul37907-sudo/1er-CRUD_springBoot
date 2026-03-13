async function eliminarUsuario(mail) {
  console.log('Eliminar usuario llamado con mail:', mail);
  const token = sessionStorage.getItem("jwtToken");
  if (!token) {
    console.warn('No hay token en sessionStorage');
    window.location.href = "/usuarios/login";
    return;
  }

  if (!mail) {
    alert('Mail inválido');
    return;
  }

  if (!confirm("¿Seguro que deseas eliminar al usuario con correo: " + mail + "?")) {
    console.log('Eliminación cancelada por el usuario');
    return;
  }

  const mailEncoded = encodeURIComponent(mail);
  const url = `/api/usuarios/mail/${mailEncoded}`;

  try {
    console.log('Enviando DELETE a', url);
    const response = await fetch(url, {
      method: "DELETE",
      headers: {
        "Authorization": "Bearer " + token,
        "Accept": "application/json"
      }
    });

    console.log('Status', response.status, 'ok', response.ok);
    let body = null;
    try { body = await response.json(); } catch (e) { body = await response.text().catch(()=>null); }
    console.log('Response body:', body);

    if (response.status === 401) {
      alert("Sesión expirada, redirigiendo al login...");
      window.location.href = "/usuarios/login";
      return;
    }

    if (response.ok) {
      alert(body?.message ?? "Usuario eliminado correctamente");
      if (typeof cargarUsuarios === 'function') cargarUsuarios();
      else location.reload();
      return;
    }

    const errMsg = body?.message ?? body ?? `Error ${response.status}`;
    alert("No se pudo eliminar: " + errMsg);
  } catch (error) {
    console.error("Fetch error al eliminar usuario:", error);
    alert("Error de conexión al eliminar usuario");
  }
}
