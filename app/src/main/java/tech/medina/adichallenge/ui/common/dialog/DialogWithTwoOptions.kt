package tech.medina.adichallenge.ui.common.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import tech.medina.adichallenge.databinding.LayoutDialogTwoOptionsBinding

class DialogWithTwoOptions(private val title: String? = null,
                           private val message: String,
                           private val leftButtonText: String? = null,
                           private val rightButtonText: String? = null,
                           private val leftButtonFunction: (() -> Unit)? = null,
                           private val rightButtonFunction: (() -> Unit)? = null,
                             ): DialogFragment() {

    private lateinit var binding: LayoutDialogTwoOptionsBinding
    private var dismissedByUser: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutDialogTwoOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title?.let {
            binding.dialogTitle.visibility = View.VISIBLE
            binding.dialogTitle.text = it
        }
        binding.dialogDescription.text = message
        leftButtonText?.let {
            binding.dialogLeftButton.text = it
        }
        rightButtonText?.let {
            binding.dialogRightButton.text = it
        }
        binding.dialogRightButton.setOnClickListener {
            rightButtonFunction?.invoke()
            dismiss()
            dismissedByUser = true
        }
        binding.dialogLeftButton.setOnClickListener {
            leftButtonFunction?.invoke()
            dismiss()
            dismissedByUser = true
        }
    }

}