package tech.medina.adichallenge.ui.product.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tech.medina.adichallenge.databinding.ItemProductGridBinding
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.ui.common.ImageLoader

class ProductGridAdapter(
    private val imageLoader: ImageLoader,
    private val clickFunction: (Product) -> Unit
): ListAdapter<Product, ProductGridAdapter.ViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder.create(binding, imageLoader, clickFunction)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemProductGridBinding,
        private val imageLoader: ImageLoader,
        private val clickFunction: (Product) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(data: Product) = with(binding) {
            container.setOnClickListener{ clickFunction.invoke(data) }
            imageLoader.loadWithUrl(data.imageUrl, image)
            name.text = data.name
            price.text = data.getFormattedPrice(context)
        }

        companion object {
            fun create(
                binding: ItemProductGridBinding,
                imageLoader: ImageLoader,
                clickFunction: (Product) -> Unit
            ): ViewHolder = ViewHolder(binding, imageLoader, clickFunction)
        }

    }
}