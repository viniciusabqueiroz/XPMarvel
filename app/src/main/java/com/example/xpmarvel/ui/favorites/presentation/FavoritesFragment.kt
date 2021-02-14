package com.example.xpmarvel.ui.favorites.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.xpmarvel.core.MainApplication
import com.example.xpmarvel.databinding.FragmentFavoritesBinding
import com.example.xpmarvel.ui.favorites.view_model.FavoritesViewModel
import javax.inject.Inject

class FavoritesFragment : Fragment() {
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<FavoritesViewModel> { vmFactory }

    private lateinit var viewDataBinding: FragmentFavoritesBinding
    private lateinit var listAdapter: FavoritesListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as MainApplication).appComponent.favoriteCharacterComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentFavoritesBinding.inflate(inflater, container, false).apply {
            this.viewModel = this@FavoritesFragment.viewModel
        }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        setupListAdapter()
        setupViewModel()
        viewModel.loadData()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    private fun setupListAdapter() {
        viewDataBinding.viewModel?.let {
            listAdapter = FavoritesListAdapter()
            viewDataBinding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            viewDataBinding.recyclerView.adapter = listAdapter
        }
    }

    private fun setupViewModel() {
        viewModel.apply {
            items.observe(viewLifecycleOwner, Observer {
                listAdapter.submitList(it)
            })
        }
    }
}

