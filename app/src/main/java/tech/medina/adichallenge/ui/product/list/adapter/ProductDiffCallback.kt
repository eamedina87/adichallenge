package tech.medina.adichallenge.ui.product.list.adapter

import androidx.recyclerview.widget.DiffUtil
import tech.medina.adichallenge.domain.models.Product

class ProductDiffCallback: DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.name == newItem.name && oldItem.description == newItem.description

}