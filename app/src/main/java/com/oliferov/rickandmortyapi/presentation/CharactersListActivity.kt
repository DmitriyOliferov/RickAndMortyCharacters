package com.oliferov.rickandmortyapi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.oliferov.rickandmortyapi.databinding.ActivityCharactersListBinding
import com.oliferov.rickandmortyapi.domain.Character
import com.oliferov.rickandmortyapi.presentation.adapter.CharacterAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersListActivity : AppCompatActivity() {

    private lateinit var viewModel: CharacterViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding by lazy {
        ActivityCharactersListBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as CharacterApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CharacterAdapter(this)
        adapter.onCharacterClickListener = object : CharacterAdapter.OnCharacterClickListener {
            override fun onCharacterClick(character: Character) {
                startActivity(
                    CharacterDetailActivity.newIntent(
                        this@CharactersListActivity,
                        character.id
                    )
                )
            }
        }
        binding.rvCharactersList.adapter = adapter
        viewModel = ViewModelProvider(this, viewModelFactory)[CharacterViewModel::class.java]
        viewModel.loadData()
        viewModel.charactersList.observe(this) {
            adapter.submitList(it)
        }
    }
}