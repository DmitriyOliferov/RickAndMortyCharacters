package com.oliferov.rickandmortyapi.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oliferov.rickandmortyapi.R
import com.oliferov.rickandmortyapi.databinding.ActivityDetailCharacterBinding

class CharacterDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailCharacterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_ID)) {
            finish()
            return
        }
        val id = intent.getIntExtra(EXTRA_ID, EXTRA_ID_DEFAULT)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, CharacterDetailFragment.newInstance(id))
                .commit()
        }
    }

    companion object {
        private const val EXTRA_ID = "id"
        private const val EXTRA_ID_DEFAULT = 1

        fun newIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, CharacterDetailActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            return intent
        }
    }
}