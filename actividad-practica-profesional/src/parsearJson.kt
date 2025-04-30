import org.json.JSONArray
import org.json.JSONObject
import java.io.File

// Función para cargar usuarios desde un archivo JSON
fun cargarUsuariosDesdeJson(rutaArchivo: String): MutableList<Usuarios> {
    val usuarios = mutableListOf<Usuarios>()

    // Leer todo el contenido del archivo como texto
    val contenido = File(rutaArchivo).readText()

    // Parsear el contenido a un JSONArray
    val jsonArray = JSONArray(contenido)

    // Recorrer cada objeto dentro del JSONArray
    for (i in 0 until jsonArray.length()) {
        val objeto = jsonArray.getJSONObject(i)

        val nombre = objeto.getString("nombre")
        val apellido = objeto.getString("apellido")
        val correo = objeto.getString("correo")
        val contraseña = objeto.getString("contraseña")

        usuarios.add(Usuarios(nombre, apellido, correo, contraseña))
    }

    return usuarios
}

// Función para guardar los usuarios en un archivo JSON
fun guardarUsuariosEnJson(rutaArchivo: String, listaUsuarios: List<Usuarios>) {
    val jsonArray = JSONArray()

    // Recorrer la lista de usuarios y convertirla a JSON
    for (usuario in listaUsuarios) {
        val jsonObject = JSONObject()
        jsonObject.put("nombre", usuario.nombre)
        jsonObject.put("apellido", usuario.apellido)
        jsonObject.put("correo", usuario.correo)
        jsonObject.put("contraseña", usuario.contraseña)

        jsonArray.put(jsonObject)
    }

    // Escribir el archivo JSON con formato indentado
    File(rutaArchivo).writeText(jsonArray.toString(4))
}
