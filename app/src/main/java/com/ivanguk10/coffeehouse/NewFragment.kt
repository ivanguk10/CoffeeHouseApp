package com.ivanguk10.coffeehouse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivanguk10.coffeehouse.databinding.FragmentNewBinding

class NewFragment: Fragment() {
    private var _binding: FragmentNewBinding? = null
    private val binding get() = _binding!!
    private var recyclerTranslation = 0
    private var oldX = 0
    private val xValues = mutableListOf(0, 0, 0, 0, 0)
    private var xValuesToCount = mutableListOf<Int>()

    private val hourlyWeatherAdapter by lazy { HourlyWeatherAdapter() }

    private val listOfWeather = listOf(
        HourlyWeatherModel("01", "cloudy", "nne", 2.3f, 16, 1150, 1350, 75),
        HourlyWeatherModel("02", "cloudy", "nne", 2.4f, 16, 1150, 1350, 80),
        HourlyWeatherModel("03", "cloudy", "nne", 2.6f, 16, 1150, 1350, 80),
        HourlyWeatherModel("04", "cloudy", "nne", 2.3f, 16, 1150, 1350, 80),
        HourlyWeatherModel("05", "cloudy", "nne", 2.7f, 16, 1150, 1350, 80),
        HourlyWeatherModel("06", "cloudy", "nne", 2.3f, 17, 1150, 1350, 80),
        HourlyWeatherModel("07", "cloudy", "nne", 2.1f, 17, 1150, 1350, 80),
        HourlyWeatherModel("08", "cloudy", "nne", 2.2f, 18, 1150, 1350, 70),
        HourlyWeatherModel("09", "cloudy", "nne", 2.3f, 18, 1150, 1350, 70),
        HourlyWeatherModel("10", "cloudy", "nne", 2.6f, 21, 1150, 1350, 70),
        HourlyWeatherModel("11", "cloudy", "nne", 2.3f, 21, 1150, 1350, 70),
        HourlyWeatherModel("12", "cloudy", "nne", 2.3f, 22, 1150, 1350, 70),
        HourlyWeatherModel("13", "cloudy", "nne", 2.6f, 23, 1150, 1350, 72),
        HourlyWeatherModel("14", "cloudy", "nne", 2.3f, 23, 1150, 1350, 74),
        HourlyWeatherModel("15", "cloudy", "nne", 2.3f, 23, 1150, 1350, 70),
        HourlyWeatherModel("16", "cloudy", "nne", 2.5f, 23, 1150, 1350, 75),
        HourlyWeatherModel("17", "cloudy", "nne", 2.3f, 23, 1150, 1350, 79),
        HourlyWeatherModel("18", "cloudy", "nne", 2.4f, 23, 1150, 1350, 75),
        HourlyWeatherModel("19", "cloudy", "nne", 2.3f, 23, 1150, 1350, 75),
        HourlyWeatherModel("20", "cloudy", "nne", 2.8f, 23, 1150, 1350, 75),
        HourlyWeatherModel("21", "cloudy", "nne", 2.3f, 23, 1150, 1350, 75),
        HourlyWeatherModel("22", "cloudy", "nne", 2.9f, 21, 1150, 1350, 75),
        HourlyWeatherModel("23", "cloudy", "nne", 1.5f, 18, 1150, 1350, 75),
        HourlyWeatherModel("24", "cloudy", "nne", 1.5f, 17, 1150, 1350, 75)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        hourlyWeatherAdapter.setData(listOfWeather)
        binding.hourlyWeatherRecyclerView.scrollToPosition(15 - 1)

//        binding.hourlyWeatherRecyclerView.setOnScrollChangeListener { view, scrollX, scrollY, oldScrollX, oldScrollY ->
//            if (scrollX > 0) {
//                Log.i("Scroll", scrollX.toString())
//                binding.unitsShort.isVisible = false
//            }
//            Log.i("Scroll", scrollY.toString())
//        }
        binding.hourlyWeatherRecyclerView.addOnScrollListener(recyclerViewListener)
    }


    override fun renderState(state: HourlyWeatherFragmentViewModel.State) {
        Unit
    }

    override fun onEvent(event: HourlyWeatherFragmentViewModel.Event) {
        Unit
    }

    private fun setUpRecyclerView() {
        binding.hourlyWeatherRecyclerView.adapter = hourlyWeatherAdapter
        binding.hourlyWeatherRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}