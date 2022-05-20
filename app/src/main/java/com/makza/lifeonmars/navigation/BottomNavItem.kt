package com.makza.lifeonmars.navigation

import com.makza.lifeonmars.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Login : BottomNavItem("Login", R.drawable.ic_launcher_foreground,"login")
    object MyNetwork: BottomNavItem("My Network",R.drawable.ic_launcher_foreground,"my_network")
    object AddPost: BottomNavItem("Post",R.drawable.ic_launcher_foreground,"add_post")
}