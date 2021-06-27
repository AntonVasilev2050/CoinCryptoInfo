package com.avvsoft2050.coincryptoinfo.ui.coins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.avvsoft2050.coincryptoinfo.ui.CoinDetailActivity
import com.avvsoft2050.coincryptoinfo.R
import com.avvsoft2050.coincryptoinfo.adapters.CoinsAdapter
import com.avvsoft2050.coincryptoinfo.database.AppDatabase
import com.avvsoft2050.coincryptoinfo.databinding.FragmentCoinsBinding
import com.avvsoft2050.coincryptoinfo.pojo.Coins
import com.avvsoft2050.coincryptoinfo.pojo.FavoriteCoins
import com.avvsoft2050.coincryptoinfo.ui.favorite.FavoriteCoinsViewModel
import kotlinx.android.synthetic.main.activity_coin_detail.*

//import kotlinx.android.synthetic.main.fragment_coins.*

class CoinsFragment : Fragment() {

    private lateinit var coinsViewModel: CoinsViewModel
    private lateinit var favoriteCoinsMarketsViewModel: FavoriteCoinsViewModel
    private var _binding: FragmentCoinsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var rvCoinsMarketsList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        coinsViewModel =
            ViewModelProvider(this).get(CoinsViewModel::class.java)
        favoriteCoinsMarketsViewModel =
            ViewModelProvider(this).get(FavoriteCoinsViewModel::class.java)
        _binding = FragmentCoinsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        rvCoinsMarketsList = root.findViewById(R.id.rvCoinsMarketsList)
        val adapter = CoinsAdapter(this.requireActivity())
        rvCoinsMarketsList.adapter = adapter

        coinsViewModel.coinsList.observe(viewLifecycleOwner, Observer {
            adapter.coinsList = it
        })
        adapter.onCoinClickListener = object : CoinsAdapter.OnCoinClickListener {
            override fun onCoinClick(coins: Coins) {
                val intent = CoinDetailActivity.newIntent(this@CoinsFragment.activity, coins.symbol)
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