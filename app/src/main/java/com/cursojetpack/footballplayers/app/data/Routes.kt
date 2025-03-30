package com.cursojetpack.footballplayers.app.data

import kotlinx.serialization.Serializable

/*las sealed class se usan para crear una jerarquia de clases
* donde pueda existir un numero finito de posibilidades,
* sin acceso a crear nuevas o alterar las existentes*/

sealed class Routes (val route: String) {
    //uso de @serializable para poder pasar parametros opcionales a la ruta
    //y poder emplear estas estructuras como el contenido de las rutas
    @Serializable
    data object LoginRoute

    @Serializable
    data object HomeRoute
}

//ejemplo de navegacion con parametros obligatorios y opcionales con
//el metodo convencional de navegacion
/*
    object Home: com.cursojetpack.footballplayers.app.data.Routes("home/{user}/{password}"){
        fun createRoute(user: String, password: String) = "home/$user/$password"
    }

    object Login: com.cursojetpack.footballplayers.app.data.Routes("login")*/
    /*object Edit: Routes("edit/{edad}") {
        asi se define una ruta para parametros obligatorios
        fun createRoute(edad: Int) = "edit/$edad"
    }
    object Add: Routes("add?name={name}") {
        asi se define una ruta para parametros opcionales

        asi se aplica para pasar la ruta con valor
        private fun createRoute(name: String) = "add?name=$name"
        asi se aplica para pasar la ruta con valor por defecto
        private fun createEmptyRoute() = "add"
        fun asigneRoute(name: String) = if (name.isNotEmpty()) createRoute(name) else createEmptyRoute()
    }
    object Search: Routes("search")*/