import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemOffsetDecoration(private val offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val spanCount = (parent.layoutManager as? GridLayoutManager)?.spanCount ?: 1

        val column = position % spanCount
        outRect.left = column * offset / spanCount
        outRect.right = offset - (column + 1) * offset / spanCount

        if (position >= spanCount) {
            outRect.top = offset
        }
    }
}
