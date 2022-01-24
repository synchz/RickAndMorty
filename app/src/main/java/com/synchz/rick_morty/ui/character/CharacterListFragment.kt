package com.synchz.rick_morty.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.synchz.rick_morty.R
import com.synchz.rick_morty.databinding.FragmentCharacterListBinding
import com.synchz.rick_morty.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.synchz.rick_morty.domain.entities.Character
import com.synchz.rick_morty.ui.common.Status
import kotlinx.android.synthetic.main.fragment_character_list.*

@AndroidEntryPoint
class CharacterListFragment : BaseFragment<FragmentCharacterListBinding, CharacterListViewModel>(), CharacterListAdapter.CharacterClickListener {

    private val characterListAdapter = CharacterListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun getViewBinding(): FragmentCharacterListBinding = FragmentCharacterListBinding.inflate(layoutInflater)

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
        rvDeliveryList.layoutManager = LinearLayoutManager(context)
        rvDeliveryList.setHasFixedSize(true)
        rvDeliveryList.adapter = characterListAdapter
    }

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
        viewModel.boundaryCallback.status.observe(viewLifecycleOwner, Observer {
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