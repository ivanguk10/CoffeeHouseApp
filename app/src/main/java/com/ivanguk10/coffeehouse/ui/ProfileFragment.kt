package com.ivanguk10.coffeehouse.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ivanguk10.coffeehouse.R
import com.ivanguk10.coffeehouse.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val navArgs: ProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        if (navArgs.day != 0 && navArgs.month != 0 && navArgs.year != 0) {
            binding.profileDateValue.setText(setDateFromArgs())
        }

        binding.profileBack.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }
        binding.profileNameValue.setSelection(binding.profileNameValue.length())

        binding.profileDateValue.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_chooseDateFragment)
        }

        return binding.root
    }

    private fun setDateFromArgs(): String {
        var day = ""
        var month = ""
        if (navArgs.day < 10)
            day = "0${navArgs.day}"
        else
            day = navArgs.day.toString()

        if (navArgs.month < 10)
            month = "0${navArgs.month}"
        else
            month = navArgs.month.toString()


        return "${day}.${month}.${navArgs.year}"
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}