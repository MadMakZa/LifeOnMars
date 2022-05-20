package com.makza.lifeonmars.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.makza.lifeonmars.view.AddPostScreen
import com.makza.lifeonmars.view.LoginView
import com.makza.lifeonmars.view.NetworkScreen
import com.makza.lifeonmars.viewmodel.LoginViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Login.screen_route) {
        composable(BottomNavItem.Login.screen_route) {
            LoginView(viewModel = LoginViewModel(application = Application()))
        }
        composable(BottomNavItem.MyNetwork.screen_route) {
            NetworkScreen()
        }
        composable(BottomNavItem.AddPost.screen_route) {
            AddPostScreen()
        }
    }
}