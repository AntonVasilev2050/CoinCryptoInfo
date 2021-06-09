package com.avvsoft2050.coincryptoinfo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.avvsoft2050.coincryptoinfo.ui.CoinDetailActivity
import com.avvsoft2050.coincryptoinfo.R
import com.avvsoft2050.coincryptoinfo.adapters.CoinsMarketsAdapter
import com.avvsoft2050.coincryptoinfo.databinding.FragmentMainBinding
import com.avvsoft2050.coincryptoinfo.pojo.CoinsMarkets
import com.avvsoft2050.coincryptoinfo.pojo.FavoriteCoinsMarkets
import com.avvsoft2050.coincryptoinfo.ui.favorite.FavoriteCoinsMarketsViewModel
import kotlinx.android.synthetic.main.activity_coin_detail.*

//import kotlinx.android.synthetic.main.fragment_main.*

class CoinsMarketsFragment : Fragment() {

    private lateinit var coinsMarketsViewModel: CoinsMarketsViewModel
    private lateinit var favoriteCoinsMarketsViewModel: FavoriteCoinsMarketsViewModel
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
        favoriteCoinsMarketsViewModel = ViewModelProvider(this).get(FavoriteCoinsMarketsViewModel::class.java)
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        rvCoinsMarketsList = root.findViewById(R.id.rvCoinsMarketsList)
        val adapter = CoinsMarketsAdapter(this.requireActivity())
        rvCoinsMarketsList.adapter = adapter
        coinsMarketsViewModel.coinsMarketsList.observe(viewLifecycleOwner, Observer {
            adapter.coinsMarketsList = it
        })
        adapter.onCoinClickListener = object : CoinsMarketsAdapter.OnCoinClickListener{
            override fun onCoinClick(coinsMarkets: CoinsMarkets) {
                val intent = CoinDetailActivity.newIntent(this@CoinsMarketsFragment.activity, coinsMarkets.symbol)
                startActivity(intent)
            }
        }
//        adapter.onCoinClickFavoriteListener = object : CoinsMarketsAdapter.OnCoinClickFavoriteListener{
//            override fun onCoinClickFavoriteListener(coinsMarkets: CoinsMarkets) {
//                TODO("Not yet implemented")
//            }
//
//        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}