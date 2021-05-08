package tech.medina.adichallenge.ui.product.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tech.medina.adichallenge.R
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
            imageLoader.loadWithUrl(data.imageUrl, image)
            name.text = data.name
            //If we have Euro as currency we add first the price, then the symbol. Else symbol, then price
            price.text = if (data.currency == context.getString(R.string.currency_euro)) {
                context.getString(R.string.product_price, data.price, data.currency)
            } else {
                context.getString(R.string.product_price, data.currency, data.price)
            }
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