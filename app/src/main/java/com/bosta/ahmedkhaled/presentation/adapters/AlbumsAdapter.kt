package com.bosta.ahmedkhaled.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bosta.ahmedkhaled.R
import com.bosta.ahmedkhaled.data.model.response.GetAlbumsResponse
import com.bosta.ahmedkhaled.presentation.navigators.AlbumsNavigator


class AlbumsAdapter(private val listener: AlbumsNavigator) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // init view
        val title: TextView = itemView.findViewById(R.id.title_albumsItem)
    }

    private val differCallback = object : DiffUtil.ItemCallback<GetAlbumsResponse>() {
        override fun areItemsTheSame(
            oldItem: GetAlbumsResponse,
            newItem: GetAlbumsResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GetAlbumsResponse,
            newItem: GetAlbumsResponse
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        return AlbumsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.title.text = item.title

        holder.title.setOnClickListener {
            listener.clickOnAlbumItem(item)
        }

    }

}
