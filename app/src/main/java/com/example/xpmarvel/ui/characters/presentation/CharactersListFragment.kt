package com.example.xpmarvel.ui.characters.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.xpmarvel.R
import com.example.xpmarvel.ui.characters.view_model.CharactersListViewModel
import com.example.xpmarvel.core.MainApplication
import com.example.xpmarvel.domain.models.CharacterModel
import com.example.xpmarvel.databinding.FragmentCharactersListBinding
import javax.inject.Inject

class CharactersListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<CharactersListViewModel> { vmFactory }

    private lateinit var viewDataBinding: FragmentCharactersListBinding
    private lateinit var listAdapter: CharactersListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as MainApplication).appComponent.characterComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentCharactersListBinding.inflate(inflater, container, false).apply {
            this.viewModel = this@CharactersListFragment.viewModel
        }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.refreshListener = this
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        setupListAdapter()
        setupViewModel()
        viewModel.loadData()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    override fun onRefresh() {
        viewModel.loadData()
    }

    private fun setupListAdapter() {
        viewDataBinding.viewModel?.let {
            listAdapter = CharactersListAdapter(viewModel)
            viewDataBinding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            viewDataBinding.recyclerView.adapter = listAdapter
        }
    }

    private fun setupViewModel() {
        viewModel.apply {
            items.observe(viewLifecycleOwner, Observer {
                listAdapter.submitList(it)
            })
            openCharacterDetail = {
                openDetails(it)
            }
            showToast = {
                showFavoriteToast()
            }
        }
    }

    private fun openDetails(character: CharacterModel) {
        val bundle = bundleOf("character" to character)
        findNavController().navigate(R.id.characterDetailFragment, bundle)
    }

    private fun showFavoriteToast() {
        Toast.makeText(
            requireContext(),
            requireContext().getText(R.string.added_to_favorites),
            Toast.LENGTH_LONG
        ).show()
    }
}