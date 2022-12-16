package com.pns.trending.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pns.trending.data.entities.Repo
import com.pns.trending.databinding.ActivityMainBinding
import com.pns.trending.utilities.Utilities.EXPANDED_ITEM_POS
import com.pns.trending.utilities.Utilities.isInternetAvailable
import com.pns.trending.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: RepoAdapter
    private var list = ArrayList<Repo>()
    private lateinit var mainViewModel: MainViewModel
    private var status by Delegates.notNull<Boolean>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.reposLiveData.observe(this) {
            list.clear()
            list.addAll(it)
            list.shuffle()
            adapter.notifyDataSetChanged()
        }

        mainViewModel.status.observe(this){

            status = it

            if (it){
                binding.mainErrorHolder.visibility = View.GONE
                binding.mainSwipeRefreshHolder.visibility = View.VISIBLE
            } else{
                binding.mainSwipeRefreshHolder.visibility = View.GONE
                binding.mainErrorHolder.visibility = View.VISIBLE
            }
        }

        binding.mainSwipeRefreshHolder.apply {
            setOnRefreshListener {
                if (isInternetAvailable(applicationContext)) {

                    Handler(Looper.getMainLooper()).postDelayed({

                        binding.mainErrorHolder.visibility = View.GONE
                        binding.mainSwipeRefreshHolder.visibility = View.VISIBLE

                        adapter.notifyDataSetChanged()
                        isRefreshing = false
                    }, 2000)

                } else {
                    isRefreshing = false
                    binding.mainSwipeRefreshHolder.visibility = View.GONE
                    binding.mainErrorHolder.visibility = View.VISIBLE
                }
            }
        }

        binding.mainRetryBtn.setOnClickListener {
            if (status){
                binding.mainErrorHolder.visibility = View.GONE
                binding.mainSwipeRefreshHolder.visibility = View.VISIBLE
            } else {
                Toast.makeText(applicationContext, "Error!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setRecyclerView() {
        adapter = RepoAdapter(list, this)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.apply {
            mainRecyclerView.adapter = adapter
            mainRecyclerView.layoutManager = linearLayoutManager
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onClick(holder: LinearLayout, list: List<Repo>, position: Int) {

        if (list[position].expanded) {
            holder.visibility = View.GONE
            list[position].expanded = false
        } else {
            holder.visibility = View.VISIBLE
            list[position].expanded = true
        }

        EXPANDED_ITEM_POS = position
        adapter.notifyDataSetChanged()
    }
}