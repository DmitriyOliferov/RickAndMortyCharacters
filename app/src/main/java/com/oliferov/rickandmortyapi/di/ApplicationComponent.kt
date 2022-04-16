package com.oliferov.rickandmortyapi.di

import android.app.Application
import com.oliferov.rickandmortyapi.presentation.CharactersListActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

    fun inject(activity: CharactersListActivity)
}