package tech.medina.adichallenge.ui.product.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.*
import tech.medina.adichallenge.R
import tech.medina.adichallenge.databinding.FragmentItemListBinding
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.ui.common.BaseFragment
import tech.medina.adichallenge.ui.product.ProductViewModel
import tech.medina.adichallenge.ui.product.list.adapter.ProductGridAdapter
import tech.medina.adichallenge.ui.product.list.adapter.ProductListAdapter
import tech.medina.adichallenge.domain.models.GALLERY
import tech.medina.adichallenge.domain.models.SORT

class ProductListFragment : BaseFragment() {

    companion object {
        fun create(): ProductListFragment = ProductListFragment()
    }

    private var productList: List<Product> = listOf()
    override val viewModel: ProductViewModel by viewModels()
    private lateinit var binding: FragmentItemListBinding
    private var gallery: GALLERY = GALLERY.LIST
    private var sort: SORT = SORT.DEFAULT

    private val onProductClick: (Product) -> Unit = { clickedProduct ->
        navigator.goToDetail(baseActivity, clickedProduct.id)
    }

    override fun getBindingRoot(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView(savedInstanceState: Bundle?) {
        initObservers()
    }

    private fun setupToolbar() {
        if (binding.toolbar?.menu?.isEmpty() == true) {
            binding.toolbar?.apply {
                inflateMenu(R.menu.product_list_menu)
                setOnMenuItemClickListener(::onMenuItemClicked)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //todo save search text
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        //todo get search text
    }


    private fun initObservers() {
        viewModel.products.observe(this, Observer {
            it?.let { products ->
                if (products.isEmpty()) {
                    onGetProductsEmpty()
                } else {
                    productList = products
                    setupToolbar()
                    setupRecyclerView()
                }
            }
        })
        viewModel.loader.observe(this, Observer {
            it?.let { show ->
                if (show) showLoader() else hideLoader()
            }
        })
        viewModel.error.observe(this, Observer {
            it?.let { error ->
                onGetProductsError(error)
            }
        })
    }

    private fun onGetProductsEmpty() {

    }

    private fun onGetProductsError(error: Any?) {

    }

    private fun showLoader() {

    }

    private fun hideLoader() {

    }

    //RECYCLERVIEW

    private fun setupRecyclerView() {
        binding.itemList.apply {
            setupRecyclerViewScrollEffect(this)
            adapter = getRecyclerViewAdapter().apply {
                submitList(productList)
            }
            layoutManager = getRecyclerViewLayoutManager()
        }
    }

    private fun setupRecyclerViewScrollEffect(recyclerView: RecyclerView) {
        if (gallery == GALLERY.LIST && recyclerView.onFlingListener == null) {
            //We add the scroll effect to show only one product in the screen
            if (recyclerView.onFlingListener == null) PagerSnapHelper().attachToRecyclerView(recyclerView)
        } else if (gallery == GALLERY.GRID && recyclerView.onFlingListener != null) {
            //We must remove all scroll effects
            recyclerView.onFlingListener = null
            recyclerView.clearOnScrollListeners()
        }
    }

    private fun getRecyclerViewAdapter() =
        if (gallery == GALLERY.LIST) {
            ProductListAdapter(imageLoader, onProductClick)
        } else {
            ProductGridAdapter(imageLoader, onProductClick)
        }

    private fun getRecyclerViewLayoutManager() =
        if (gallery == GALLERY.LIST) {
            LinearLayoutManager(requireContext())
        } else {
            GridLayoutManager(requireContext(), requireContext().resources.getInteger(R.integer.product_grid_columns))
        }

    //MENU ITEMS: SORT & GALLERY

    private fun onMenuItemClicked(item: MenuItem): Boolean {
        item.isChecked = true
        return when (item.itemId) {
            R.id.list_menu_sort_az -> sortProductsBy(SORT.AZ)
            R.id.list_menu_sort_za -> sortProductsBy(SORT.ZA)
            R.id.list_menu_sort_price_low_high -> sortProductsBy(SORT.PRICE_LOW_HIGH)
            R.id.list_menu_sort_price_high_low -> sortProductsBy(SORT.PRICE_HIGH_LOW)
            R.id.list_menu_gallery_list -> showProductsIn(GALLERY.LIST)
            R.id.list_menu_gallery_grid -> showProductsIn(GALLERY.GRID)
            else -> false
        }
    }

    private fun showProductsIn(newGallery: GALLERY): Boolean {
        if (gallery != newGallery) {
            gallery = newGallery
            setupRecyclerView()
        }
        return true
    }

    private fun sortProductsBy(newSort: SORT): Boolean {
        if (sort != newSort) {
            viewModel.sortProductsBy(newSort)
            sort = newSort
        }
        return true
    }

}