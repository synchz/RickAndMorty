package com.synchz.rick_morty.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.synchz.rick_morty.R
import com.synchz.rick_morty.ui.character.CharacterListAdapter
import com.synchz.rick_morty.ui.character.CharacterListFragment
import com.synchz.rick_morty.ui.location.LocationListFragment

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> CharacterListFragment()
            1 -> LocationListFragment()
            else -> LocationListFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 3
    }
}