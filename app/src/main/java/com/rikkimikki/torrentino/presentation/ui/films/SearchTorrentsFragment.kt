package com.rikkimikki.torrentino.presentation.ui.films

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rikkimikki.torrentino.databinding.FragmentSearchTorrentsBinding
import com.rikkimikki.torrentino.domain.pojo.torrent.Data

class SearchTorrentsFragment : Fragment() {
    private var _binding: FragmentSearchTorrentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TorrentsViewModel

    private lateinit var film: String
    private lateinit var poster: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            if (it.containsKey(ARG_FILM) && it.containsKey(ARG_POSTER)){
                film = it.getString(ARG_FILM)!!
                poster = it.getString(ARG_POSTER)!!
            }
            else
                throw Exception()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchTorrentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[TorrentsViewModel::class.java]

        viewModel.torrents.observe(viewLifecycleOwner){
            binding.scrollViewTorrents

            val adapter: ArrayAdapter<Data> = ArrayAdapter<Data>(
                requireContext(),
                R.layout.simple_list_item_1, it.data
            )
            binding.scrollViewTorrents.adapter = adapter
            setListener(it.data)


        }
        viewModel.getTorrents(film)
    }

    private fun setListener(torrents:List<Data>) {
        binding.scrollViewTorrents.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val selectedFilm = torrents[i]
            selectedFilm.poster = poster
            viewModel.addTorrent(selectedFilm)
            /*val builder = AlertDialog.Builder(this@SearchTorrentsActivity)
            builder.setMessage("Воспроизвести на телефоне или на телевизоре?")
                .setPositiveButton("Телефон", dialogClickListener)
                .setNegativeButton("Телевизор", dialogClickListener).show()*/
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val ARG_FILM = "film"
        private const val ARG_POSTER = "poster"

        fun newInstance(film: String, poster: String) =
            SearchTorrentsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_FILM, film)
                    putString(ARG_POSTER, poster)
                }
            }
    }
}