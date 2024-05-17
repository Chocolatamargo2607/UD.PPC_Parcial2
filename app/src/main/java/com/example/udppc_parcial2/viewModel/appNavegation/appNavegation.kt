package com.example.udppc_parcial2.viewModel.appNavegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.udppc_parcial2.view.screenAddPet
import com.example.udppc_parcial2.view.screenMain

@Composable
fun appNavegation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = appScreens.screenMain.router){

        composable(route= appScreens.screenMain.router){ screenMain(navController) }
        composable(route= appScreens.screenAddPet.router){ screenAddPet(navController)}

    }
}