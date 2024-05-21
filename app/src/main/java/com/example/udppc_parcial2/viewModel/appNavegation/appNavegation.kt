package com.example.udppc_parcial2.viewModel.appNavegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.udppc_parcial2.view.screenAddPet
import com.example.udppc_parcial2.view.screenMain
import com.example.udppc_parcial2.viewModel.PetService
import com.example.udppc_parcial2.viewModel.ScreenAddPetViewModel
import com.example.udppc_parcial2.viewModel.ScreenMainViewModel

@Composable
fun appNavegation(){
    val context = LocalContext.current
    val service = PetService()
    val viewModel = ScreenAddPetViewModel(context,service)
    val viewModel1 = ScreenMainViewModel(context,service)
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = appScreens.screenMain.router){

        composable(route= appScreens.screenMain.router){ screenMain(navController,viewModel1) }
        composable(route= appScreens.screenAddPet.router){ screenAddPet(navController,viewModel)}

    }
}