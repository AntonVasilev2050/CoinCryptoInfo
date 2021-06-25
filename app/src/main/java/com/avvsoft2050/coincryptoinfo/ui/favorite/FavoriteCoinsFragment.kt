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

class FavoriteCoinsFragment : Fragment() {

    private lateinit var favoriteCoinsMarketsViewModel: FavoriteCoinsViewModel
    private var _binding: FragmentFavoriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var rvFavoriteCoinsMarketsList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteCoinsMarketsViewModel =
            ViewModelProvider(this).get(FavoriteCoinsViewModel::class.java)

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        rvFavoriteCoinsMarketsList = root.findViewById(R.id.rvFavoriteCoinsMarketsList)
        val adapter = CoinsAdapter(this.requireActivity())
        rvFavoriteCoinsMarketsList.adapter = adapter
        favoriteCoinsMarketsViewModel.favoriteCoinsMarketsList.observe(viewLifecycleOwner, Observer {
            adapter.coinsList = it
        } )
        adapter.onCoinClickListener = object : CoinsAdapter.OnCoinClickListener{
            override fun onCoinClick(coins: Coins) {
                val intent = CoinDetailActivity.newIntent(this@FavoriteCoinsFragment.activity, coins.symbol)
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