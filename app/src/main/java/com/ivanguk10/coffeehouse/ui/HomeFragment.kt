package com.ivanguk10.coffeehouse.ui

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivanguk10.coffeehouse.R
import com.ivanguk10.coffeehouse.adapters.HotProductAdapter
import com.ivanguk10.coffeehouse.adapters.NewsAndSalesAdapter
import com.ivanguk10.coffeehouse.data.util.NetworkResult
import com.ivanguk10.coffeehouse.databinding.FragmentHomeBinding
import com.ivanguk10.coffeehouse.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModel()
    private val newsAndSalesAdapter by lazy { NewsAndSalesAdapter(requireContext()) }
    private val hotProductAdapter by lazy { HotProductAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val drawer = activity?.findViewById<DrawerLayout>(R.id.drawerLayout)
        binding.toolbar.setNavigationOnClickListener {
            drawer?.openDrawer(Gravity.LEFT)
        }

        binding.newsAndSalesTvAll.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_scanQrFragment)
        }

        setUpRecyclerView()

//        mainViewModel.insertHotProduct(
//            HotProduct(
//                1,
//                "Cappuccino",
//                300
//            )
//        )
//
//        mainViewModel.insertHotProduct(
//            HotProduct(
//                2,
//                "Raf",
//                400
//            )
//        )
//
//        mainViewModel.insertNews(
//            NewsAndSalesEntity(
//                3,
//                "Wonderful Cup",
//                "Color changing cup!"
//            )
//        )
//
//        mainViewModel.insertNews(
//            NewsAndSalesEntity(
//                4,
//                "Happy Wednesday",
//                "Get free sandwich when buying a latte!"
//            )
//        )
//        mainViewModel.insertNews(
//            NewsAndSalesEntity(
//                5,
//                "Happy Friday",
//                "30% sale for our custom Thermal Mug"
//            )
//        )

//        val workManager = WorkManager.getInstance(requireContext().applicationContext)
//        val request = OneTimeWorkRequestBuilder<MyWorker>()
//            .build()
//
//        workManager.enqueue(request)

//        mainViewModel.readNewsAndSales.observe(viewLifecycleOwner, { newsAndSales ->
//            newsAndSalesAdapter.setData(newsAndSales)
//        })
        mainViewModel.getNews()
        mainViewModel.newsResponse.observe(viewLifecycleOwner, { response  ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let { newsAndSalesAdapter.setData(it) }
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

        mainViewModel.readHotProducts.observe(viewLifecycleOwner, { hotProducts ->
            hotProductAdapter.setData(hotProducts)
        })

        binding.getPointsBackground.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_showQrFragment)
        }

        binding.coffeeButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_coffeeFragment)
        }

        binding.teaAndCacaoButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_teaFragment)
        }

        return binding.root
    }

    private fun setUpRecyclerView() {
        binding.salesRecyclerView.adapter = newsAndSalesAdapter
        binding.salesRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.hitsRecyclerView.adapter = hotProductAdapter
        binding.hitsRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}