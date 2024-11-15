package com.example.core.base

import androidx.activity.ComponentActivity

abstract class CoreBaseActivity: ComponentActivity() {
    abstract fun hideBottomBar()
    abstract fun showBottomBar()
}