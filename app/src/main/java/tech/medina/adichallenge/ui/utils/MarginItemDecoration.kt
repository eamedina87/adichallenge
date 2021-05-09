package tech.medina.adichallenge.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    private val marginTop: Int? = null,
    private val marginBottom: Int? = null,
    private val marginStart: Int? = null,
    private val marginEnd: Int? = null,
    private val columns: Int?,
    private val listSize: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            val position = parent.getChildAdapterPosition(view)
            if (marginBottom != null && position != listSize - 1 ) {
                bottom = marginBottom
            }
            if (marginEnd != null && columns != null && position % columns == 0) {
                right = marginEnd
            }
        }
    }
}