package tech.medina.adichallenge.ui.product.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tech.medina.adichallenge.databinding.ItemProductListBinding
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.ui.common.ImageLoader

class ProductListAdapter(
    private val imageLoader: ImageLoader,
    private val clickFunction: (Product) -> Unit
): ListAdapter<Product, ProductListAdapter.ViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder.create(binding, imageLoader, clickFunction)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemProductListBinding,
        private val imageLoader: ImageLoader,
        private val clickFunction: (Product) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(data: Product) = with(binding) {
            button.setOnClickListener { clickFunction.invoke(data) }
            container.setOnClickListener{ clickFunction.invoke(data) }
            imageLoader.loadWithUrl(data.imageUrl, image)
            name.text = data.name
            price.text = data.getFormattedPrice(context)
        }

        companion object {
            fun create(
                binding: ItemProductListBinding,
                imageLoader: ImageLoader,
                clickFunction: (Product) -> Unit
            ): ViewHolder = ViewHolder(binding, imageLoader, clickFunction)
        }

    }

}