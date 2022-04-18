package com.oliferov.rickandmortyapi.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.oliferov.rickandmortyapi.R
import com.oliferov.rickandmortyapi.databinding.FragmentCharacterDetailBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CharacterDetailFragment : Fragment() {
    private lateinit var viewModel: CharacterViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding: FragmentCharacterDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentCoinDetailBinding is null")

    private val component by lazy {
        (requireActivity().application as CharacterApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val characterId = getIdInArgs()
        viewModel = ViewModelProvider(this, viewModelFactory)[CharacterViewModel::class.java]
        viewModel.getCharacterDetail(characterId).observe(viewLifecycleOwner) {
            with(binding) {
                with(requireActivity().resources) {
                    tvName.text = it.name
                    tvGender.text = String.format(getString(R.string.gender_detail), it.gender)
                    tvSpecies.text = String.format(getString(R.string.species_detail), it.species)
                    tvStatus.text = String.format(getString(R.string.status_detail), it.status)
                    tvEpisode.text = String.format(getString(R.string.episode_detail), it.episode)
                    tvLocation.text =
                        String.format(getString(R.string.location_detail), it.location)
                    Picasso.get().load(it.avatarUrl).into(ivAvatar)
                }

            }
        }
    }

    private fun getIdInArgs() = requireArguments().getInt(EXTRA_ID)

    companion object {
        private const val EXTRA_ID = "id"

        fun newInstance(id: Int): Fragment {
            return CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_ID, id)
                }
            }
        }
    }
}