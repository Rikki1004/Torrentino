package com.rikkimikki.torrentino.presentation.ui.torrents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rikkimikki.torrentino.R

class TorrentsContainerFragment : Fragment() {
    private val torrentFragment = TorrentsFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_torrents_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.torrensFragmentContainer, torrentFragment, null)
            .commit()
    }
}