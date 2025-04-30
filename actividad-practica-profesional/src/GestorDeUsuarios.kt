class GestorUsuarios(private val listaDeUsuarios: MutableList<Usuarios>) {

    fun esCorreoValido(correo: String): Boolean {
        return correo.contains("@") && correo.contains(".")
    }

    fun darAlta() {
        println("Ingrese los datos del nuevo usuario:")
        println("Nombre:")
        val nombre = readln().trim()
        println("Apellido:")
        val apellido = readln().trim()
        println("Correo:")
        var correo = readln().trim()

        while (!esCorreoValido(correo)) {
            println("Correo inválido. Debe contener '@' y '.'")
            correo = readln().trim()
        }

        println("Contraseña:")
        val contraseña = readln().trim()

        val usuarioNuevo = Usuarios(nombre, apellido, correo, contraseña)
        listaDeUsuarios.add(usuarioNuevo)
        guardarUsuariosEnJson( "src/UsuariosParaPractica.json", listaDeUsuarios) // Guardamos los cambios en el archivo
        println("Usuario agregado con éxito.")
        println(usuarioNuevo)
    }

    // Función para modificar un usuario
    fun modificar() {
        if (listaDeUsuarios.isEmpty()) {
            println("No hay usuarios para modificar.")
            return
        }

        println("¿Qué usuario desea modificar?")

        // Mostrar lista de usuarios con su índice
        listaDeUsuarios.forEachIndexed { index, usuario ->
            println("${index + 1}. $usuario")
        }

        print("Ingrese el número del usuario a modificar: ")
        val indice = readln().toIntOrNull()

        if (indice == null || indice !in 1..listaDeUsuarios.size) {
            println("Número inválido.")
            return
        }

        val usuarioSeleccionado = listaDeUsuarios[indice - 1]

        println("¿Qué desea modificar?")
        println("1. Nombre")
        println("2. Apellido")
        println("3. Correo")
        println("4. Contraseña")
        print("Opción: ")
        val opcion = readln().toIntOrNull()

        if (opcion !in 1..4) {
            println("Opción inválida.")
            return
        }

        print("Ingrese el nuevo valor: ")
        val nuevoValor = readln().trim()

        // Modificar el usuario según la opción seleccionada
        val usuarioModificado = when (opcion) {
            1 -> usuarioSeleccionado.copy(nombre = nuevoValor)
            2 -> usuarioSeleccionado.copy(apellido = nuevoValor)
            3 -> {
                // Verificar si el correo tiene el formato adecuado
                if (!nuevoValor.contains("@")) {
                    println("El correo debe contener '@'. Intente nuevamente.")
                    return
                }
                usuarioSeleccionado.copy(correo = nuevoValor)
            }
            4 -> usuarioSeleccionado.copy(contraseña = nuevoValor)
            else -> usuarioSeleccionado
        }

        // Reemplazar el usuario en la lista
        listaDeUsuarios[indice - 1] = usuarioModificado

        println("Usuario modificado con éxito:")
        println(usuarioModificado)

        // Guardar los cambios en el archivo JSON
        guardarUsuariosEnJson("src/UsuariosParaPractica.json", listaDeUsuarios)
    }

    // Función para eliminar un usuario
    fun confirmarAccion(mensaje: String): Boolean {
        println("$mensaje (S/N): ")
        val confirmacion = readln().trim().uppercase()
        return confirmacion == "S"
    }

    fun eliminar() {
        if (listaDeUsuarios.isEmpty()) {
            println("No hay usuarios para eliminar.")
            return
        }

        println("¿Qué usuario desea eliminar?")
        listaDeUsuarios.forEachIndexed { index, usuario ->
            println("${index + 1}. ${usuario.nombre} ${usuario.apellido} - ${usuario.correo}")
        }

        print("Ingrese el número del usuario a eliminar: ")
        val indice = readln().toIntOrNull()

        if (indice == null || indice !in 1..listaDeUsuarios.size) {
            println("Número inválido.")
            return
        }

        val usuarioAEliminar = listaDeUsuarios[indice - 1]

        if (confirmarAccion("¿Está seguro que desea eliminar al usuario ${usuarioAEliminar.nombre}?")) {
            listaDeUsuarios.removeAt(indice - 1)
            guardarUsuariosEnJson("src/UsuariosParaPractica.json", listaDeUsuarios)
            println("Usuario eliminado con éxito.")
        } else {
            println("Eliminación cancelada.")
        }
    }


    // Función para mostrar todos los usuarios
    fun mostrar() {
        if (listaDeUsuarios.isEmpty()) {
            println("No hay usuarios para mostrar.")
        } else {
            listaDeUsuarios.forEachIndexed { index, usuario ->
                println("${index + 1}. Nombre: ${usuario.nombre} - Apellido:  ${usuario.apellido} - Correo: ${usuario.correo}")
            }
        }
    }
}
