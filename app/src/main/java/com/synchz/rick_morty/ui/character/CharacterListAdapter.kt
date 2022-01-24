package com.synchz.rick_morty.ui.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.synchz.rick_morty.R
import com.synchz.rick_morty.databinding.ItemListCharacterBinding
import com.synchz.rick_morty.databinding.ItemLoaderBinding
import com.synchz.rick_morty.domain.entities.Character
import kotlinx.android.synthetic.main.item_end.view.*
import kotlinx.android.synthetic.main.item_list_character.view.*
import kotlinx.android.synthetic.main.item_loader.view.*

const val VIEW_TYPE_ITEM = 0
const val VIEW_TYPE_LOADING = 1
const val VIEW_TYPE_END = 2

class CharacterListAdapter(private val listener: CharacterClickListener): PagedListAdapter<Character, RecyclerView.ViewHolder>(diffCallback){

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
            VIEW_TYPE_ITEM -> return DeliveryVH(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_list_character, parent, false
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
                (holder as DeliveryVH).bind(getItem(position) as Character)
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

    inner class DeliveryVH(private val binding: ItemListCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character?) {
            character?.let {
                itemView.tvName.text = character.name
                itemView.tvSpecies.text = character.species
                itemView.tvGender.text = character.gender
                itemView.tvStatus.text = character.status
                Glide.with(itemView.context)
                    .load(character.image)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(itemView.ivCharacter)
                itemView.setOnClickListener {
                    listener.onCharacterTapped(
                        character,
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
        val diffCallback: DiffUtil.ItemCallback<Character> =
            object : DiffUtil.ItemCallback<Character>() {
                override fun areItemsTheSame(
                    oldItem: Character,
                    newItem: Character
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Character,
                    newItem: Character
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }


    interface CharacterClickListener {
        fun onCharacterTapped(
            character: Character,
            adapterPosition: Int
        )
    }
}