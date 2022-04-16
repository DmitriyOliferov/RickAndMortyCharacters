package com.oliferov.rickandmortyapi.presentation

import android.app.Application
import com.oliferov.rickandmortyapi.di.DaggerApplicationComponent

class CharacterApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}