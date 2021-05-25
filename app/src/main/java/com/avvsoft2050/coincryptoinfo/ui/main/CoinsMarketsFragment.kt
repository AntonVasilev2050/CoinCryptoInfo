package com.avvsoft2050.coincryptoinfo.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.avvsoft2050.coincryptoinfo.R
import com.avvsoft2050.coincryptoinfo.adapters.CoinsMarketsAdapter
import com.avvsoft2050.coincryptoinfo.databinding.FragmentMainBinding
import kotlinx.android.synthetic.*

//import kotlinx.android.synthetic.main.fragment_main.*

class CoinsMarketsFragment : Fragment() {

    private lateinit var coinsMarketsViewModel: CoinsMarketsViewModel
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var rvCoinsMarketsList : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        coinsMarketsViewModel =
            ViewModelProvider(this).get(CoinsMarketsViewModel::class.java)

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        rvCoinsMarketsList = root.findViewById(R.id.rvCoinsMarketsList)
        val adapter = CoinsMarketsAdapter()
        rvCoinsMarketsList.adapter = adapter
        coinsMarketsViewModel.coinsMarketsList.observe(viewLifecycleOwner, Observer {
            adapter.coinsMarketsList = it
        })

//        val textView: TextView = binding.textHome
//        coinsMarketsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}