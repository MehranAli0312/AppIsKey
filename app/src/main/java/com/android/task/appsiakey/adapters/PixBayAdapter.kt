package com.android.task.appsiakey.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.task.appsiakey.databinding.ItemviewBinding
import com.android.task.appsiakey.model.Hit

class PixBayAdapter(
    private val itemClick: (String) -> Unit
) :
    RecyclerView.Adapter<PixBayAdapter.MyViewHolder>() {

    private var bookList = emptyList<Hit>()

    class MyViewHolder(val binding: ItemviewBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = bookList[position]
        with(holder.binding) {
            user.text = currentItem.user
            tags.text = currentItem.tags
            likes.text = currentItem.likes.toString()
            largeImageURL.text = currentItem.largeImageURL

            root.setOnClickListener {
                itemClick.invoke(currentItem.largeImageURL)
            }
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun setData(book: List<Hit>) {
        this.bookList = book
        notifyDataSetChanged()
    }
}