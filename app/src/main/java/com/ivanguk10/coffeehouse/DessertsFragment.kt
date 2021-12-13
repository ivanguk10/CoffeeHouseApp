package com.ivanguk10.coffeehouse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ivanguk10.coffeehouse.adapters.MenuAdapter
import com.ivanguk10.coffeehouse.data.util.NetworkResult
import com.ivanguk10.coffeehouse.databinding.FragmentDessertsBinding
import com.ivanguk10.coffeehouse.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DessertsFragment : Fragment() {

    private var _binding: FragmentDessertsBinding? = null
    private val binding get() = _binding!!
    private val dessertsAdapter by lazy { MenuAdapter(4) }
    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDessertsBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setUpRecyclerView()

        mainViewModel.getDesserts()
        mainViewModel.dessertsResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let { dessertsAdapter.setDessertsData(it) }
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
        binding.dessertsRecyclerView.adapter = dessertsAdapter
        binding.dessertsRecyclerView.layoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}