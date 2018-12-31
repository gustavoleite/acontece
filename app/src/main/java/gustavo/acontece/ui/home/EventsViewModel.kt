package gustavo.acontece.ui.home

import android.arch.lifecycle.ViewModel
import gustavo.acontece.data.entity.model.EventPreview
import java.util.*

class EventsViewModel(val eventPreview: EventPreview) : ViewModel() {

    val image = eventPreview.image
    val title = eventPreview.title
    val price = eventPreview.price.toString()
    val date = eventPreview.date.get(Calendar.DAY_OF_MONTH).toString()

    lateinit var onItemPressedCallback: (EventPreview)-> Unit

    fun onItemPressed() {
        onItemPressedCallback(eventPreview)
    }
}