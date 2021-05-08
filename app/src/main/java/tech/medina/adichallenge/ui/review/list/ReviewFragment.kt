package tech.medina.adichallenge.ui.review.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import tech.medina.adichallenge.databinding.FragmentItemDetailBinding
import tech.medina.adichallenge.ui.common.BaseFragment
import tech.medina.adichallenge.ui.review.ReviewViewModel


/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ProductDetailActivity]
 * on handsets.
 */
class ReviewFragment : BaseFragment() {

    override val viewModel: ReviewViewModel by viewModels()
    lateinit var binding: FragmentItemDetailBinding

    override fun getBindingRoot(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

}