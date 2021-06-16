package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.apiModule
import com.example.myapplication.di.networkModule
import com.example.myapplication.di.repoModule
import com.example.myapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ChartsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ChartsApplication)
            modules(
                listOf(
                    apiModule,
                    networkModule,
                    repoModule,
                    viewModelModule
                )
            )
        }
    }
}