package com.synchz.rick_morty.ui.character

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.synchz.rick_morty.R
import com.synchz.rick_morty.databinding.FragmentItemListBinding
import com.synchz.rick_morty.domain.entities.Character
import com.synchz.rick_morty.ui.base.BaseFragment
import com.synchz.rick_morty.ui.common.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_item_list.*

@AndroidEntryPoint
class CharacterListFragment : BaseFragment<FragmentItemListBinding, CharacterListViewModel>(), CharacterListAdapter.CharacterClickListener {

    private val characterListAdapter = CharacterListAdapter(this)

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

    override val viewModel: CharacterListViewModel by viewModels()

    private fun init() {
        initRecyclerView()
        initDataSource()
        initBoundaryCallbacks()
        characterListAdapter.retryLive.observe(viewLifecycleOwner, {
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
        rvList.adapter = characterListAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initDataSource() {
        swipeRefresh.setOnRefreshListener {
            viewModel.refreshList()
        }
        viewModel.characterListSource.observe(viewLifecycleOwner, {
            if (it.size != 0) {
                characterListAdapter.submitList(it)
                characterListAdapter.notifyDataSetChanged()
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
                    if (characterListAdapter.itemCount < 1) {
                        retryBtn.visibility = View.VISIBLE
                    }
                    characterListAdapter.showLoading(false)
                    val err = if (it == Status.NETWORK_ERROR) "Network Error"
                        else "Error Occurred"
                    characterListAdapter.showRetry(true, err)
                    Snackbar.make(root, err, Snackbar.LENGTH_LONG).show()
                    hideRefresher()
                }
                Status.PAGE_LOADING -> {
                    hideRefresher()
                    characterListAdapter.showLoading(true)
                }
                Status.LOADED -> {
                    hideRefresher()
                    retryBtn.visibility = View.GONE
                    characterListAdapter.setIsLastItem(true)
                }
                else -> {
                    hideRefresher()
                    retryBtn.visibility = View.GONE
                    characterListAdapter.showLoading(showLoader = false)
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

    override fun onCharacterTapped(character: Character, adapterPosition: Int) {

    }

}