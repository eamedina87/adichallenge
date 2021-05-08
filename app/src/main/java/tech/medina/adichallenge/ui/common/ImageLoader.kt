package tech.medina.adichallenge.ui.common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ImageLoader @Inject constructor(@ApplicationContext private val context: Context) {

    private val glide by lazy {
        Glide.with(context)
    }

    fun loadWithUrl(url: String?, targetView: ImageView, placeholder: Int = 0) { //todo change ph
        glide.load(url)
            .placeholder(placeholder)
            .into(targetView)
    }

}