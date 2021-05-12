package tech.medina.adichallenge.ui.review.list.adapter

import androidx.recyclerview.widget.DiffUtil
import tech.medina.adichallenge.domain.models.Review

class ReviewDiffCallback: DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
            oldItem.text == newItem.text &&
                    oldItem.rating == newItem.rating

}