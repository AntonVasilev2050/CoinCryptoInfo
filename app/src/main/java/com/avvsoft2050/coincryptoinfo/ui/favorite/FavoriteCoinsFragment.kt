package com.avvsoft2050.coincryptoinfo.ui.favorite

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
import com.avvsoft2050.coincryptoinfo.databinding.FragmentFavoriteBinding
import com.avvsoft2050.coincryptoinfo.pojo.Coins
import com.avvsoft2050.coincryptoinfo.pojo.FavoriteCoins
import com.avvsoft2050.coincryptoinfo.ui.coins.CoinsViewModel

class FavoriteCoinsFragment : Fragment() {
    private lateinit var coinsViewModel: CoinsViewModel
    private lateinit var favoriteCoinsViewModel: FavoriteCoinsViewModel
    private var _binding: FragmentFavoriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var rvFavoriteCoinsMarketsList: RecyclerView
    private var coinsListFromDataBase: MutableList<Coins> = mutableListOf()
    private var favoriteCoinsListFromDatabase: MutableList<Coins> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        coinsViewModel = ViewModelProvider(this).get(CoinsViewModel::class.java)
        favoriteCoinsViewModel = ViewModelProvider(this).get(FavoriteCoinsViewModel::class.java)
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        rvFavoriteCoinsMarketsList = root.findViewById(R.id.rvFavoriteCoinsMarketsList)
        val adapter = CoinsAdapter(this.requireActivity())
        rvFavoriteCoinsMarketsList.adapter = adapter
        favoriteCoinsViewModel.favoriteCoinsList.observe(viewLifecycleOwner, Observer {
            favoriteCoinsListFromDatabase = it.toMutableList()

        })
        coinsViewModel.coinsList.observe(viewLifecycleOwner, Observer {
            coinsListFromDataBase = it.toMutableList()
            for (i in 0 until favoriteCoinsListFromDatabase.size) {
                for (j in 0 until coinsListFromDataBase.size) {
                    if (favoriteCoinsListFromDatabase[i].symbol.equals(coinsListFromDataBase[j].symbol)) {
                        favoriteCoinsListFromDatabase.set(i, coinsListFromDataBase[j])
                    }
                }
            }
            adapter.coinsList = favoriteCoinsListFromDatabase
        })
        adapter.onCoinClickListener = object : CoinsAdapter.OnCoinClickListener {
            override fun onCoinClick(coins: Coins) {
                val intent =
                    CoinDetailActivity.newIntent(this@FavoriteCoinsFragment.activity, coins.symbol)
                startActivity(intent)
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}