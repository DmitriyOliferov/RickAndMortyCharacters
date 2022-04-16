package com.oliferov.rickandmortyapi.di

import android.app.Application
import com.oliferov.rickandmortyapi.MainActivity
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    interface Factory {
        fun create(
            application: Application
        ): ApplicationComponent
    }

    fun inject(activity: MainActivity)
}