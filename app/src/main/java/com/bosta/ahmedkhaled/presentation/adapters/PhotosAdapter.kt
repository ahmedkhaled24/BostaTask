package com.bosta.ahmedkhaled.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bosta.ahmedkhaled.R
import com.bosta.ahmedkhaled.data.model.response.GetPhotosResponse
import com.bosta.ahmedkhaled.presentation.navigators.PhotosNavigator
import com.squareup.picasso.Picasso


class PhotosAdapter(private val listener: PhotosNavigator) :
    RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // init view
        val photoIv: ImageView = itemView.findViewById(R.id.photoIV)
    }

    private val differCallback = object : DiffUtil.ItemCallback<GetPhotosResponse>() {
        override fun areItemsTheSame(
            oldItem: GetPhotosResponse,
            newItem: GetPhotosResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GetPhotosResponse,
            newItem: GetPhotosResponse
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val item = differ.currentList[position]
        Picasso.get().load(item.url).into(holder.photoIv)

        holder.photoIv.setOnClickListener {
            listener.clickOnPhoto(item.url)
        }

    }

}
