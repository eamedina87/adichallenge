package tech.medina.adichallenge.ui.review.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tech.medina.adichallenge.databinding.ItemReviewBinding
import tech.medina.adichallenge.domain.models.Review

class ReviewListAdapter: ListAdapter<Review, ReviewListAdapter.ViewHolder>(ReviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder.create(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemReviewBinding
    ): RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(data: Review) = with(binding) {
            average.text = data.rating.toString()
            stars.progress = data.rating
            message.text = data.text
        }

        companion object {
            fun create(
                binding: ItemReviewBinding
            ): ViewHolder = ViewHolder(binding)
        }

    }

}