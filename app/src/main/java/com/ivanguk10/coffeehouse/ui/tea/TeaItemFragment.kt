package com.ivanguk10.coffeehouse.ui.tea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ivanguk10.coffeehouse.R
import com.ivanguk10.coffeehouse.data.util.NetworkResult
import com.ivanguk10.coffeehouse.databinding.FragmentDessertItemBinding
import com.ivanguk10.coffeehouse.databinding.FragmentTeaItemBinding
import com.ivanguk10.coffeehouse.viewmodels.ItemViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeaItemFragment : Fragment() {

    private var _binding: FragmentTeaItemBinding? = null
    private val binding get() = _binding!!
    private val itemViewModel by viewModel<ItemViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeaItemBinding.inflate(layoutInflater, container, false)

        val id = 1
        itemViewModel.getSingleTea(id)
        itemViewModel.teaResponse.observe(viewLifecycleOwner, { response  ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let { setItemData(it.name, it.price) }
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

    private fun setItemData(title: String, price: Float) {
        binding.menuTitleTea.text = title
        binding.teaPrice.text = price.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}