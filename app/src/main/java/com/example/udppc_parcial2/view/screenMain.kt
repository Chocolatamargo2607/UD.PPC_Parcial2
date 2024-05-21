package com.example.udppc_parcial2.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.udppc_parcial2.R
import com.example.udppc_parcial2.dataManagement.PetDTO
import com.example.udppc_parcial2.ui.theme.MainColor
import com.example.udppc_parcial2.viewModel.appNavegation.appScreens
import com.example.udppc_parcial2.viewmodels.PetViewModel



@SuppressLint("ComposableNaming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenMain(navController: NavController, petViewModel : PetViewModel = viewModel()) {

    var pets by remember { mutableStateOf<List<PetDTO>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val repository = "https://github.com/Chocolatamargo2607/UD.PPC_Parcial2.git"
    val repositoryintent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(repository)) }
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val onSearch: (String) -> Unit = {
        Toast.makeText(context, query, Toast.LENGTH_SHORT).show()
        active = false
    }
    

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
            Text(text = "Add New Pet")
        }


        petViewModel.fetchPets(
            onResult = { fetchedPets ->
                pets = fetchedPets
            },
            onError = { error ->
                errorMessage = error.localizedMessage
            }
        )



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
        errorMessage?.let {
            Text(text = "Error: $it", color = Color.Red)
        }

        PetList(pets = pets)

    }

}

@Composable
fun PetList(pets: List<PetDTO>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(pets) { pet ->
            PetCard(pet = pet)
        }
    }
}

@Composable
fun PetItem(pet: PetDTO) {
    Column {
        Text(text = "Name: ${pet.name}")
        Text(text = "${pet.type}")
    }
}

@Composable
fun PetCard(pet: PetDTO) {
    val context = LocalContext.current
    var open_Dialog = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(17.dp)
            .clickable {
                open_Dialog.value = true
            }
        ,elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {


        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.Black
            )
            PetItem(pet = pet)
        }

        if (open_Dialog.value) {
            AlertDialog(
                onDismissRequest = { open_Dialog.value = false },
                title = { Text(text = "${pet.name}") },
                text = {
                    Column {
                        Text(text = "Age: ${pet.age}")
                        Text(text = "Type Pet: ${pet.type}")
                        Text(text = "Breed: ${pet.breed}")

                    }
                },
                confirmButton = {
                    Button(onClick = { open_Dialog.value = false }) {
                        Text(text = "Close")
                    }
                }
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun screenMainpreview() {
    screenMain(NavController(LocalContext.current))
}
