package com.ivanguk10.coffeehouse.ui.altmilk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ivanguk10.coffeehouse.R
import com.ivanguk10.coffeehouse.data.util.NetworkResult
import com.ivanguk10.coffeehouse.databinding.FragmentAltMilkItemBinding
import com.ivanguk10.coffeehouse.viewmodels.ItemViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AltMilkItemFragment : Fragment() {

    private var _binding: FragmentAltMilkItemBinding? = null
    private val binding get() = _binding!!
    private val itemViewModel by viewModel<ItemViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAltMilkItemBinding.inflate(layoutInflater, container, false)

        val id = 1
        itemViewModel.getSingleAlt(id)
        itemViewModel.altsResponse.observe(viewLifecycleOwner, { response  ->
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
        binding.menuTitleDrinkAlt.text = title
        binding.altPrice.text = price.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}