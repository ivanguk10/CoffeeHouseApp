package com.ivanguk10.coffeehouse.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ivanguk10.coffeehouse.R
import com.ivanguk10.coffeehouse.adapters.MenuAdapter
import com.ivanguk10.coffeehouse.data.util.NetworkResult
import com.ivanguk10.coffeehouse.databinding.FragmentCoffeeBinding
import com.ivanguk10.coffeehouse.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CoffeeFragment : Fragment() {

    private var _binding: FragmentCoffeeBinding? = null
    private val binding get() = _binding!!
    private val menuAdapter by lazy { MenuAdapter() }
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoffeeBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setUpRecyclerView()

        mainViewModel.getCoffee()
        mainViewModel.coffeeResponse.observe(viewLifecycleOwner, { response ->
            when(response)  {
                is NetworkResult.Success -> {
                   response.data?.let { menuAdapter.setData(it) }
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
        binding.coffeeRecyclerView.adapter = menuAdapter
//        binding.coffeeRecyclerView.layoutManager = GridLayoutManager(
//            requireContext(),
//            2,
//            RecyclerView.VERTICAL,
//            false
//        )
        binding.coffeeRecyclerView.layoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}