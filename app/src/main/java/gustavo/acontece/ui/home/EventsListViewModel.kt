package gustavo.acontece.ui.home

import android.arch.lifecycle.ViewModel
import gustavo.acontece.data.entity.model.EventPreview
import gustavo.acontece.utils.extensions.toBrCurrency
import gustavo.acontece.utils.extensions.toDayMonthYear

class EventsListViewModel(private val eventPreview: EventPreview) : ViewModel() {

    val image = eventPreview.image
    val title = eventPreview.title
    val price = eventPreview.price.toBrCurrency()
    val date = eventPreview.date.toDayMonthYear()

    var onItemPressedCallback: ((EventPreview) -> Unit)? = null

    fun onItemPressed() {
        onItemPressedCallback?.invoke(eventPreview)
    }
}