package com.ivanguk10.coffeehouse.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ivanguk10.coffeehouse.databinding.FragmentItemBinding
import com.ivanguk10.coffeehouse.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ItemFragment : Fragment() {

    private var _binding: FragmentItemBinding? = null
    private val binding = _binding!!
    private val args by navArgs<ItemFragmentArgs>()
    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        when(args.menuType) {
            "dessert" -> {
                binding.menuTitle.text = "dessert"
            }
            "coffee" -> {
                binding.menuTitle.text = "coffee"
            }
            "tea" -> {
                binding.menuTitle.text = "tea"
            }
            "drinks" -> {
                binding.menuTitle.text = "drinks"
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}