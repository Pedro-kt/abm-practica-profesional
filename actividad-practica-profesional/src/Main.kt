fun main() {
    // Ruta del archivo JSON
    val rutaJson = "src/UsuariosParaPractica.json"

    // Cargar los usuarios desde el archivo JSON
    val listaDeUsuarios = cargarUsuariosDesdeJson(rutaJson)

    // Crear el gestor de usuarios con la lista de usuarios
    val gestor = GestorUsuarios(listaDeUsuarios)

    // Llamar a la función programa que contiene la lógica del menú
    programa(gestor)
}
