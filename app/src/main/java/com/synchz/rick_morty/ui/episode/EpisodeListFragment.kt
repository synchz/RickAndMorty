package com.synchz.rick_morty.ui.episode

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.synchz.rick_morty.R
import com.synchz.rick_morty.databinding.FragmentItemListBinding
import com.synchz.rick_morty.domain.entities.Episode
import com.synchz.rick_morty.ui.base.BaseFragment
import com.synchz.rick_morty.ui.common.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_item_list.*

@AndroidEntryPoint
class EpisodeListFragment : BaseFragment<FragmentItemListBinding, EpisodeListViewModel>(), EpisodeListAdapter.EpisodeClickListener {

    private val locationListAdapter = EpisodeListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun getViewBinding(): FragmentItemListBinding = FragmentItemListBinding.inflate(layoutInflater)

    override val viewModel: EpisodeListViewModel by viewModels()

    private fun init() {
        initRecyclerView()
        initDataSource()
        initBoundaryCallbacks()
        locationListAdapter.retryLive.observe(viewLifecycleOwner, {
            viewModel.retry()
        })
        retryBtn.setOnClickListener {
            retryBtn.visibility = View.GONE
            viewModel.refreshList()
        }
    }

    private fun initRecyclerView() {
        rvList.layoutManager = LinearLayoutManager(context)
        rvList.setHasFixedSize(true)
        rvList.adapter = locationListAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initDataSource() {
        swipeRefresh.setOnRefreshListener {
            viewModel.refreshList()
        }
        viewModel.episodeListSource.observe(viewLifecycleOwner, {
            if (it.size != 0) {
                locationListAdapter.submitList(it)
                locationListAdapter.notifyDataSetChanged()
            }
            hideRefresher()
        })
    }

    private fun initBoundaryCallbacks() {
        viewModel.boundaryCallback.status.observe(viewLifecycleOwner, {
            when (it) {
                Status.LOADING -> {
                    showRefresher()
                }
                Status.ERROR, Status.NETWORK_ERROR -> {
                    hideRefresher()
                    if (locationListAdapter.itemCount < 1) {
                        retryBtn.visibility = View.VISIBLE
                    }
                    locationListAdapter.showLoading(false)
                    val err = if (it == Status.NETWORK_ERROR) "Network Error"
                        else "Error Occurred"
                    locationListAdapter.showRetry(true, err)
                    Snackbar.make(root, err, Snackbar.LENGTH_LONG).show()
                    hideRefresher()
                }
                Status.PAGE_LOADING -> {
                    hideRefresher()
                    locationListAdapter.showLoading(true)
                }
                Status.LOADED -> {
                    hideRefresher()
                    retryBtn.visibility = View.GONE
                    locationListAdapter.setIsLastItem(true)
                }
                else -> {
                    hideRefresher()
                    retryBtn.visibility = View.GONE
                    locationListAdapter.showLoading(showLoader = false)
                }
            }
        })
    }

    private fun hideRefresher() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }

    private fun showRefresher() {
        swipeRefresh.isRefreshing = true
    }

    override fun onEpisodeTapped(episode: Episode, adapterPosition: Int) {

    }

}