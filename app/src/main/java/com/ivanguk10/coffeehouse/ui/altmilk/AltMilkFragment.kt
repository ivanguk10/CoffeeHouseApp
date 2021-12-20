package com.ivanguk10.coffeehouse.ui.altmilk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ivanguk10.coffeehouse.R
import com.ivanguk10.coffeehouse.adapters.MenuAdapter
import com.ivanguk10.coffeehouse.data.util.NetworkResult
import com.ivanguk10.coffeehouse.databinding.FragmentAltMilkBinding
import com.ivanguk10.coffeehouse.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AltMilkFragment : Fragment() {

    private var _binding: FragmentAltMilkBinding? = null
    private val binding get() = _binding!!
    private val menuAdapter by lazy { MenuAdapter(5) }
    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAltMilkBinding.inflate(layoutInflater, container, false)

        setUpRecyclerView()

        mainViewModel.getAlts()
        mainViewModel.altsResponse.observe(viewLifecycleOwner, { response ->
            when(response)  {
                is NetworkResult.Success -> {
                    response.data?.let { menuAdapter.setAltData(it) }
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
        binding.altRecyclerView.adapter = menuAdapter
        binding.altRecyclerView.layoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}