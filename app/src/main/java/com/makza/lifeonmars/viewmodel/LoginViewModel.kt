package com.makza.lifeonmars.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {

    private val context = application

//    fun initDataBase(){
//        val daoUser = UserDataBase.getInstance(context = context).getUserDao()
//        REPOSITORY = UserRealization(userDao = daoUser)
//    }

//    suspend fun getAllUsers(): Flow<List<UserModel>> {
//        println("~~ ${REPOSITORY.getAllUsers(userDataBase = UserDataBase.getInstance(context)
//            .getUserDao()
//            .insert(userModel = UserModel(userId = 0, userLogin = "lol", userPassword = "pass")))}")
//        return REPOSITORY.getAllUsers(userDataBase = UserDataBase.getInstance(context))
//    }

    private val _loginSF = MutableStateFlow("")
    val loginSF = _loginSF.asStateFlow()

    fun setLogin(inputLogin: String){
        _loginSF.value = inputLogin
    }

    private val _passwordSF = MutableStateFlow("")
    val passwordSF = _passwordSF.asStateFlow()

    fun setPassword(inputPass: String){
        _passwordSF.value = inputPass
    }

    fun login(login: String, pass: String) = viewModelScope.launch {
        //тут будет проверка верификации, сохранение пользователя, вход
        delay(3000L)
    }

    fun testPrint(){
        println("~~ login: ${_loginSF.value}  password: ${_passwordSF.value}")
    }

}