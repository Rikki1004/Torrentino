package com.rikkimikki.torrentino.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.databinding.ItemTorrentBinding
import com.rikkimikki.torrentino.domain.pojo.server.GetStoreResponse
import com.squareup.picasso.Picasso


class TorrentsAdapter: ListAdapter<GetStoreResponse, TorrentsAdapter.TorrentViewHolder>(TorrentsDiffCallback){

    var onTorrentClickListener: OnTorrentClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TorrentViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_torrent, parent, false)
        return TorrentViewHolder(view)
    }

    override fun onBindViewHolder(holder: TorrentViewHolder, position: Int) {
        val torrent = currentList[position]
        with(holder.viewBinding){
            textViewStoreTitle.text = torrent.title
            textViewStorePeers.text = torrent.allPeers
            textViewStoreSize.text ="%.2f".format(torrent.torrent_size / 1024 / 1024 / 1024) + " Gb"
            textViewStoreSpeed.text = torrent.uploadSpeed
            Picasso.get().load(torrent.poster).placeholder(R.drawable.placeholder).into(imageViewStorePoster)
            buttonStoreDelete.setOnClickListener {
                onTorrentClickListener?.onDeleteClick(torrent)
            }
            buttonStorePlay.setOnClickListener {
                onTorrentClickListener?.onPlayClick(torrent)
            }
        }

    }

    inner class TorrentViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var viewBinding = ItemTorrentBinding.bind(itemView)
    }


    interface OnTorrentClickListener {
        fun onPlayClick(torrent:GetStoreResponse)
        fun onDeleteClick(torrent:GetStoreResponse)
    }

}