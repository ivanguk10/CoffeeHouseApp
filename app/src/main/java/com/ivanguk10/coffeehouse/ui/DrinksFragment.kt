package com.ivanguk10.coffeehouse.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ivanguk10.coffeehouse.adapters.MenuAdapter
import com.ivanguk10.coffeehouse.data.util.NetworkResult
import com.ivanguk10.coffeehouse.databinding.FragmentDrinksBinding
import com.ivanguk10.coffeehouse.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DrinksFragment : Fragment() {

    private var _binding: FragmentDrinksBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel by viewModel<MainViewModel>()
    private val menuAdapter by lazy { MenuAdapter(3) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDrinksBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setUpRecyclerView()

        mainViewModel.getDrinks()
        mainViewModel.drinkResponse.observe(viewLifecycleOwner, { response ->
            when(response)  {
                is NetworkResult.Success -> {
                    response.data?.let { menuAdapter.setDrinksData(it) }
                }
                is NetworkResult.Error -> {
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_LONG).show()
                }
            }
        })

        return binding.root
    }

    private fun setUpRecyclerView() {
        binding.drinksRecyclerView.adapter = menuAdapter
        binding.drinksRecyclerView.layoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}