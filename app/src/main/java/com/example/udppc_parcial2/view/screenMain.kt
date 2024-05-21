package com.example.udppc_parcial2.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.udppc_parcial2.R
import com.example.udppc_parcial2.ui.theme.MainColor
import com.example.udppc_parcial2.viewModel.Pet
import com.example.udppc_parcial2.viewModel.PetService
import com.example.udppc_parcial2.viewModel.ScreenAddPetViewModel
import com.example.udppc_parcial2.viewModel.ScreenMainViewModel
import com.example.udppc_parcial2.viewModel.appNavegation.appScreens

@SuppressLint("ComposableNaming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenMain(navController: NavController, viewModel: ScreenMainViewModel) {
    val context = LocalContext.current
    val repository = "https://github.com/Chocolatamargo2607/UD.PPC_Parcial2.git"
    val repositoryintent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(repository)) }
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val onSearch: (String) -> Unit = {
        Toast.makeText(context, query, Toast.LENGTH_SHORT).show()
        active = false
    }

    val pets: List<Pet>? = viewModel.pets.value


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        TopAppBar(
            title = { Text(text=" My Little Pet ღ", onTextLayout = { }) },
            colors = TopAppBarDefaults.topAppBarColors(
                MainColor,
                titleContentColor = Color.White
            ),
            navigationIcon = { IconButton(onClick ={ context.startActivity(repositoryintent)}) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    tint = Color.White
                )}
            }
        )
        Image(
            painter = painterResource(id = R.drawable.logo_pet),
            contentDescription = "Logo"
        )
        Button(onClick = { navController.navigate(route = appScreens.screenAddPet.router)},
            colors = ButtonDefaults.buttonColors(MainColor)) {
            Text(text = "Register petღ")
        }
        SearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = onSearch,
            active = active,
            onActiveChange = { active = it },
            placeholder = {
                Box { Text(text = "Search") }
            },
            trailingIcon = {
                IconButton(
                    onClick = { onSearch(query) },
                    enabled = query.isNotEmpty()
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            }
        ) {

        }
        Button(onClick = {
            println("Imprimiendo")
            pets?.forEach{
                    pet ->
                println("Name: ${pet.name}, Type: ${pet.type}, Age: ${pet.age}, Breed: ${pet.breed}, ${pet.image.toString()}")

            }
        },
            colors = ButtonDefaults.buttonColors(MainColor)) {
            Text(text = "BLA BLA BLA")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun screenMainpreview() {
    val context = LocalContext.current
    val service = PetService()
    val viewModel = ScreenMainViewModel(context,service)
    screenMain(NavController(context),viewModel)
}
