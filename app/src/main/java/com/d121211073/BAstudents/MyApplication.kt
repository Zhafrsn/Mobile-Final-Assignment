package com.d121211073.BAstudents

import android.app.Application
import com.d121211073.BAstudents.data.AppContainer
import com.d121211073.BAstudents.data.DefaultAppContainer

class MyApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}