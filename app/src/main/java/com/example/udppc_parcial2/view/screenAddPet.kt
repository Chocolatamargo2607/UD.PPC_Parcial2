package com.example.udppc_parcial2.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.udppc_parcial2.dataManagement.Helper
import com.example.udppc_parcial2.dataManagement.PetDTO
import com.example.udppc_parcial2.dataManagement.PetsService
import com.example.udppc_parcial2.ui.theme.MainColor
import com.example.udppc_parcial2.viewModel.appNavegation.appScreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenAddPet(navController: NavController) {


    var context = LocalContext.current
    val scope = rememberCoroutineScope()

    var name by remember {
        mutableStateOf("")
    }
    var type by remember {
        mutableStateOf("")
    }
    var age by remember {
        mutableStateOf("")
    }
    var breed by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ){
        Row() {
            TopAppBar(
                title = { Text(text=" Storage the pet") },
                colors = TopAppBarDefaults.topAppBarColors(
                    MainColor,
                    titleContentColor = Color.White
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainColor)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            TextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text(text = "Name Plant", color = MainColor)
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.background

                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Description Assigment", color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = type,
                onValueChange = {
                    type = it
                },
                placeholder = {
                    Text(text = "Write the description",color = MainColor)
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.background

                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = age,
                onValueChange = {
                    age = it
                },label = {
                    Text(text = "Age (In months)", color = MainColor)
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.background

                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = breed,
                onValueChange = {
                    breed = it
                },
                placeholder = {
                    Text(text = "Date Assigment",color = MainColor)
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.background

                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.navigate(route = appScreens.screenMain.router)
            },colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(text = "Photo", color = MainColor)
            }
            Spacer(modifier = Modifier.height(20.dp))




            Row(
                modifier = Modifier.padding(16.dp)
            ){
                Button(onClick = {

                    name = ""
                    type = ""
                    age= ""
                    breed= ""
                },colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text(text = " Save ", color = MainColor)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = {
                    navController.navigate(route = appScreens.screenMain.router)
                },colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text(text = "Cancel", color = MainColor)
                }

            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun screenAddPet() {
    screenAddPet(NavController(LocalContext.current))
}
