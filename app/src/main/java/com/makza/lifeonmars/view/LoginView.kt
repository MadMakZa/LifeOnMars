package com.makza.lifeonmars.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.makza.lifeonmars.R
import com.makza.lifeonmars.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginView(viewModel: LoginViewModel) {
    var login by rememberSaveable { mutableStateOf(viewModel.loginSF.value) }
    var password by rememberSaveable { mutableStateOf(viewModel.passwordSF.value) }
    val maxChars = 40
    val context = LocalContext.current
    var passwordVisibility by remember { mutableStateOf(false) }
    val iconPV = if (passwordVisibility) {
        painterResource(id = R.drawable.ic_launcher_background)
    }else{
        painterResource(id = R.drawable.ic_launcher_foreground)}

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        TextField(
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            value = login,
            onValueChange = {
                if(it.length <= maxChars)
                    login = it
            },
            label = { Text("login") },
            singleLine = true
        )

        Spacer(modifier = Modifier.padding(top = 5.dp))

        TextField(
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            value = password,
            onValueChange = {
                if(it.length <= maxChars)
                    password = it
            },
            label = { Text("password") },
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        painter = iconPV,
                        contentDescription = "visibility icon"
                    )
                }
            },
            visualTransformation = if(passwordVisibility){
                VisualTransformation.None
            }else{
                PasswordVisualTransformation()
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.padding(top = 5.dp))

        Button(
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White
            ),
            onClick = {
                if (login.isNotBlank() && password.isNotBlank()) {
                    viewModel.setLogin(login)
                    viewModel.setPassword(password)
                    viewModel.testPrint()
                    Toast.makeText(context, "Logging", Toast.LENGTH_LONG).show()
//                    viewModel.viewModelScope.launch {
//                        viewModel.getAllUsers()
//                    }
                }else{
                    Toast.makeText(context, "Error: invalid e-mail or password", Toast.LENGTH_LONG).show()
                }
            }) {
            Text("Login")
        }
    }
}