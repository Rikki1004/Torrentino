package com.rikkimikki.torrentino.presentation.ui.torrents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.databinding.FragmentSearchBinding
import com.rikkimikki.torrentino.databinding.FragmentTorrentsBinding
import com.rikkimikki.torrentino.domain.pojo.server.GetStoreResponse
import com.rikkimikki.torrentino.presentation.adapters.SearchAdapter
import com.rikkimikki.torrentino.presentation.adapters.TorrentsAdapter
import com.rikkimikki.torrentino.presentation.ui.films.FilmDetailFragment
import com.rikkimikki.torrentino.presentation.ui.search.SearchViewModel

class TorrentsFragment : Fragment() {
    private var _binding: FragmentTorrentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TorrentsAdapter
    private lateinit var viewModel: TorrentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTorrentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[TorrentsViewModel::class.java]

        val recycleView = binding.recycleViewTorrents
        adapter = TorrentsAdapter()

        adapter.onTorrentClickListener = object : TorrentsAdapter.OnTorrentClickListener{
            override fun onPlayClick(torrent: GetStoreResponse) {
                val fragment = TorrentsFileListFragment.newInstance(torrent.data?:"",torrent.hash,torrent.title)
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.torrensFragmentContainer,fragment)
                    .addToBackStack(null)
                    .commit()

            }

            override fun onDeleteClick(torrent: GetStoreResponse) {
                viewModel.deleteTorrent(torrent.hash)
            }
        }

        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = adapter

        viewModel.getTorrents().observe(viewLifecycleOwner){
            adapter.submitList(it)
            println("aaa")
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}