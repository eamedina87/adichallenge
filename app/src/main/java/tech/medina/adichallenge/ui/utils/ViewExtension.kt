package tech.medina.adichallenge.ui.utils

import android.view.View

fun View.visible(visible: Boolean = true) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}