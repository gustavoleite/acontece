package gustavo.acontece.data.entity.mapper

import gustavo.acontece.data.entity.model.EventPreview
import gustavo.acontece.data.entity.output.EventOutput
import gustavo.acontece.utils.extensions.toCalendar

object EventMapper {
    fun toEventPreviewList(output: List<EventOutput>) : List<EventPreview> {
        return output.map {
            with(it) {
                EventPreview(id,title,image,price, date.toCalendar())
            }
        }.toList()
    }
}