package com.example.xpmarvel.ui.characters.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.xpmarvel.R
import com.example.xpmarvel.databinding.FragmentHomeBinding
import com.example.xpmarvel.ui.favorites.presentation.FavoritesFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy

class HomeFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentHomeBinding
    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {
        }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        navHost =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
                ?: return
        navController = navHost.navController

        setupViewPager()
    }

    private fun setupViewPager() {
        val fragmentList = arrayListOf<Fragment>(
            CharactersListFragment(),
            FavoritesFragment()
        )

        val sectionsPagerAdapter =
            activity?.supportFragmentManager?.let {
                SectionsPagerAdapter(
                    fragmentList,
                    requireActivity().supportFragmentManager,
                    lifecycle
                )
            }
        val tabs = viewDataBinding.tabs
        val viewPager = viewDataBinding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(tabs, viewPager,
            TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = requireActivity().getText(R.string.characters)
                    1 -> tab.text = requireActivity().getText(R.string.favorites)
                }
            }).attach()
    }
}