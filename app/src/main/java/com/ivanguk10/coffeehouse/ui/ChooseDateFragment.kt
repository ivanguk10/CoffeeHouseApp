package com.ivanguk10.coffeehouse.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.ivanguk10.coffeehouse.R
import com.ivanguk10.coffeehouse.databinding.FragmentChooseDateBinding


class ChooseDateFragment : DialogFragment() {

    private var _binding: FragmentChooseDateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseDateBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner


        binding.okBtn.setOnClickListener {
            sendAndNavigateDateToProfile()
        }

        binding.cancelBtn.setOnClickListener {
            findNavController().navigate(R.id.action_chooseDateFragment_to_profileFragment)
        }


        return binding.root
    }

    private fun sendAndNavigateDateToProfile() {
        val day = binding.calendar.dayOfMonth
        val month = binding.calendar.month
        val year = binding.calendar.year

        val action = ChooseDateFragmentDirections.actionChooseDateFragmentToProfileFragment(
            day, month, year
        )
        findNavController().navigate(action)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}