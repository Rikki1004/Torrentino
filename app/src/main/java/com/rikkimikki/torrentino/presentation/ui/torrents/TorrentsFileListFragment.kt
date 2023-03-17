package com.rikkimikki.torrentino.presentation.ui.torrents

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.databinding.FragmentTorrentsFileListBinding
import com.rikkimikki.torrentino.domain.pojo.server.MiniTorrent
import com.rikkimikki.torrentino.utils.dataToMiniTorrents

class TorrentsFileListFragment : Fragment() {
    private var _binding: FragmentTorrentsFileListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TorrentsViewModel

    private lateinit var data: String
    private lateinit var hash: String
    private lateinit var title: String
    private lateinit var torrents: List<MiniTorrent>
    private lateinit var selectedTorrent: MiniTorrent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            if (it.containsKey(ARG_TORRENTS)){
                data = it.getString(ARG_TORRENTS)!!
                hash = it.getString(ARG_HASH)!!
                title = it.getString(ARG_TITLE)!!
            }
            else
                throw Exception()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTorrentsFileListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[TorrentsViewModel::class.java]

        torrents = dataToMiniTorrents(data)
        initAdapter()
        initObservers()
        setListener()
        }

    private fun initObservers() {
        viewModel.needLaunchActivity.observe(viewLifecycleOwner){
            startActivity(it)
        }
    }

    private fun initAdapter() {
        val adapter: ArrayAdapter<MiniTorrent> = ArrayAdapter<MiniTorrent>(
            requireContext(),
            android.R.layout.simple_list_item_1, torrents
        )
        binding.listViewStoreSeries.adapter = adapter
    }

    private fun setListener() {
        binding.listViewStoreSeries.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, i, _ ->
                selectedTorrent = torrents[i]
                val builder = AlertDialog.Builder(context)
                builder.setMessage(getString(R.string.play_on_tv_or_phone))
                    .setPositiveButton(getString(R.string.play_on_phone), dialogClickListener)
                    .setNegativeButton(getString(R.string.play_on_tv), dialogClickListener).show()
            }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private var dialogClickListener =
        DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    viewModel.playTorrentLocal(selectedTorrent.id,hash)
                }
                DialogInterface.BUTTON_NEGATIVE ->
                    viewModel.playTorrentRemote(title,selectedTorrent.id,hash)
            }
        }

    companion object {
        private const val ARG_TORRENTS = "torrents"
        private const val ARG_HASH = "hash"
        private const val ARG_TITLE = "title"

        fun newInstance(data: String,hash:String,title:String) =
            TorrentsFileListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TORRENTS, data)
                    putString(ARG_HASH, hash)
                    putString(ARG_TITLE, title)
                }
            }
    }
}