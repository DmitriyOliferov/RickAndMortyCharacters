package com.oliferov.rickandmortyapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.oliferov.rickandmortyapi.data.network.ApiFactory
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.coroutineScope

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}