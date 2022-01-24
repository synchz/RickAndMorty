package com.synchz.rick_morty.ui.location

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.synchz.rick_morty.R
import com.synchz.rick_morty.databinding.ItemListLocationBinding
import com.synchz.rick_morty.databinding.ItemLoaderBinding
import com.synchz.rick_morty.domain.entities.Location
import com.synchz.rick_morty.ui.character.VIEW_TYPE_END
import com.synchz.rick_morty.ui.character.VIEW_TYPE_ITEM
import com.synchz.rick_morty.ui.character.VIEW_TYPE_LOADING
import kotlinx.android.synthetic.main.item_end.view.*
import kotlinx.android.synthetic.main.item_list_location.view.*
import kotlinx.android.synthetic.main.item_loader.view.*

class LocationListAdapter(private val listener: LocationClickListener): PagedListAdapter<Location, RecyclerView.ViewHolder>(diffCallback){

    private var showLoader = false
    private var retryObservable = false
    private var endObservable = false
    var retryLive = MutableLiveData<Boolean>()
    private var retryMsg = ""

    override fun getItemViewType(position: Int): Int {
        return when {
            position < super.getItemCount() -> VIEW_TYPE_ITEM
            endObservable -> VIEW_TYPE_END
            else -> VIEW_TYPE_LOADING
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> return LocationVH(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_list_location, parent, false
                )
            )
            VIEW_TYPE_END -> return EndVH(
                LayoutInflater.from(parent.context).inflate(R.layout.item_end, parent, false)
            )
            else -> LoadingVH(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_loader, parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder, position: Int
    ) {
        when {
            getItemViewType(position) == VIEW_TYPE_END -> (holder as EndVH).bind()
            getItemViewType(position) == VIEW_TYPE_LOADING -> (holder as LoadingVH).bind(
                showLoader,
                retryObservable
            )
            else -> try {
                (holder as LocationVH).bind(getItem(position) as Location)
            } catch (e: Exception) {

            }
        }
    }

    inner class EndVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            itemView.endChip.visibility = View.VISIBLE
            itemView.endTV.visibility = View.VISIBLE
            itemView.endChip.setOnClickListener {
                itemView.endChip.visibility = View.GONE
                itemView.endTV.visibility = View.GONE
            }
        }
    }

    inner class LoadingVH(private val binding: ItemLoaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(isLoading: Boolean, retry: Boolean) {
            binding.isLoadingBool = isLoading
            binding.retryBool = retry
            itemView.errorTV.text = retryMsg
            itemView.retry.setOnClickListener {
                retryLive.postValue(true)
            }
        }
    }

    inner class LocationVH(private val binding: ItemListLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(location: Location?) {
            location?.let {
                itemView.tvName.text = location.name
                itemView.tvType.text = location.type
                itemView.tvStatus.text = location.dimension

                itemView.setOnClickListener {
                    listener.onLocationTapped(
                        location,
                        adapterPosition
                    )
                }
            }
        }
    }

    fun showLoading(showLoader: Boolean) {
        this.endObservable = false
        this.showLoader = showLoader
        this.retryObservable = false
        notifyDataSetChanged()
    }

    fun showRetry(show: Boolean, msg: String) {
        this.retryMsg = msg
        this.endObservable = false
        this.showLoader = false
        this.retryObservable = show
        notifyDataSetChanged()
    }

    fun setIsLastItem(isLast: Boolean) {
        this.retryObservable = false
        this.showLoader = false
        this.endObservable = isLast
        notifyDataSetChanged()
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (showLoader || retryObservable || endObservable)
    }


    companion object {
        val diffCallback: DiffUtil.ItemCallback<Location> =
            object : DiffUtil.ItemCallback<Location>() {
                override fun areItemsTheSame(
                    oldItem: Location,
                    newItem: Location
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Location,
                    newItem: Location
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }


    interface LocationClickListener {
        fun onLocationTapped(
            location: Location,
            adapterPosition: Int
        )
    }
}