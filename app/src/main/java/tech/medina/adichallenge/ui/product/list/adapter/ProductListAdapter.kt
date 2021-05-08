package tech.medina.adichallenge.ui.product.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tech.medina.adichallenge.R
import tech.medina.adichallenge.databinding.ItemProductBinding
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.ui.common.ImageLoader

class ProductListAdapter(
    private val imageLoader: ImageLoader,
    private val clickFunction: (Product) -> Unit
): ListAdapter<Product, ProductListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder.create(binding, imageLoader, clickFunction)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemProductBinding,
        private val imageLoader: ImageLoader,
        private val clickFunction: (Product) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(data: Product) = with(binding) {
            imageLoader.loadWithUrl(data.imageUrl, image)
            name.text = data.name
            price.text = context.getString(R.string.product_price, data.currency, data.price)
        }

        companion object {
            fun create(
                binding: ItemProductBinding,
                imageLoader: ImageLoader,
                clickFunction: (Product) -> Unit
            ): ViewHolder = ViewHolder(binding, imageLoader, clickFunction)
        }

    }

    class DiffCallback: DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem.reviews == newItem.reviews

    }
}