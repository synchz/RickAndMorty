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

@AndroidEntryPoint
class CharacterListFragment : BaseFragment<FragmentCharacterListBinding, CharacterListViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun getViewBinding(): FragmentCharacterListBinding = FragmentCharacterListBinding.inflate(layoutInflater)

    override val viewModel: CharacterListViewModel by viewModels()

}