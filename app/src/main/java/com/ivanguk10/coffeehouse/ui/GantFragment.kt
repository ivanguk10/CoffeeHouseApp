package com.ivanguk10.coffeehouse.ui

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.ivanguk10.coffeehouse.databinding.FragmentGantBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GantFragment : Fragment() {

    private var _binding: FragmentGantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGantBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}