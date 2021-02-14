package com.example.xpmarvel.ui.character_detail.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xpmarvel.ui.character_detail.view_model.CharacterDetailViewModel
import com.example.xpmarvel.core.MainApplication
import com.example.xpmarvel.databinding.FragmentCharacterDetailBinding
import javax.inject.Inject

class CharacterDetailFragment : Fragment() {
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<CharacterDetailViewModel> { vmFactory }

    private lateinit var viewDataBinding: FragmentCharacterDetailBinding
    private lateinit var comicsListAdapter: ComicsListAdapter
    private lateinit var seriesListAdapter: SeriesListAdapter

    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as MainApplication).appComponent.characterDetailComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentCharacterDetailBinding.inflate(inflater, container, false).apply {
            this.viewModel = this@CharacterDetailFragment.viewModel
        }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewModel.setCharacter(args.character)
        setupComicsListAdapter()
        setupSeriesListAdapter()
        setTitle(args.character.name)
    }

    private fun setupComicsListAdapter() {
        viewModel.apply {
            comicsListAdapter = ComicsListAdapter()
        }
        viewDataBinding.comicsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewDataBinding.comicsRecyclerView.adapter = comicsListAdapter
        comicsListAdapter.submitList(args.character.comics)
    }

    private fun setupSeriesListAdapter() {
        viewModel.apply {
            seriesListAdapter = SeriesListAdapter()
        }
        viewDataBinding.seriesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewDataBinding.seriesRecyclerView.adapter = seriesListAdapter
        seriesListAdapter.submitList(args.character.series)
    }

    private fun setTitle(characterName: String) {
        activity?.title = characterName
    }
}