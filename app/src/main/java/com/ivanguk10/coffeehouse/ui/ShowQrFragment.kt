package com.ivanguk10.coffeehouse.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.zxing.BarcodeFormat
import com.ivanguk10.coffeehouse.databinding.FragmentShowQrBinding
import com.journeyapps.barcodescanner.BarcodeEncoder

class ShowQrFragment : Fragment() {

    private var _binding: FragmentShowQrBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowQrBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap("1000", BarcodeFormat.QR_CODE, 400, 400)
            binding.qrCode.setImageBitmap(bitmap)
        } catch (e:Exception) {

        }

        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}