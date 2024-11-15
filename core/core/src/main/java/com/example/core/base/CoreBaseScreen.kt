package com.example.core.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import com.example.core.navigation.ResultNavigation
import trikita.log.Log


abstract class CoreBaseScreen: Screen {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content(){

    }

    open fun onDispose(){}

    @Composable
    fun SubscribeError(viewModel: CoreBaseViewModel){
        val message = viewModel.showErrorMessageEvent.collectAsStateWithLifecycle().value
        if(!message.isNullOrEmpty()){
            Log.e("subscribeError", message)
            viewModel.cleareError()
        }
    }

    fun onBack(result:Any){
        ResultNavigation.setValue(result)
    }

    fun getResultScreen():Any?{
        return ResultNavigation.getValue()
    }

    @Composable
    fun ShowBottomBar(){
        LocalContext.current.getCoreBaseActivity().showBottomBar()
    }
}