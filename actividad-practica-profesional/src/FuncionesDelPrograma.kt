fun programa(gestor: GestorUsuarios) {
    println("¡Bienvenido al ABM hecho por Pedrito! \nIngrese la opción a realizar:")

    while (true) {
        mostrarOpciones()

        // Intentar obtener una entrada válida para la opción
        val opcion = readln().toIntOrNull()

        if (opcion == null || opcion !in 1..5) {
            println("Opción inválida. Por favor ingrese un número entre 1 y 5.")
            continue
        }

        when (opcion) {
            1 -> gestor.darAlta()
            2 -> gestor.modificar()
            3 -> gestor.eliminar()
            4 -> gestor.mostrar()
            5 -> {
                println("¿Está seguro que quiere salir del sistema? [S/N] \nIngrese opción:")

                val opcionSalida = readln().uppercase()

                when (opcionSalida) {
                    "S" -> break
                    "N" -> println("Has sido devuelto al menú nuevamente")
                    else -> println("Opción inválida, vuelve a intentar.")
                }
            }
        }
    }
}

fun mostrarOpciones() {
    println("1. Dar de alta un usuario")
    println("2. Modificar un usuario")
    println("3. Dar de baja un usuario")
    println("4. Mostrar todos los usuarios")
    println("5. Salir del sistema")
}
