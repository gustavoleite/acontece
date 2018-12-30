package gustavo.acontece.data.entity.mapper

import gustavo.acontece.data.entity.domain.EventPreview
import gustavo.acontece.data.entity.output.EventOutput
import java.util.*

object EventMapper {
    fun toEventPreviewList(output: List<EventOutput>) : List<EventPreview> {
        return output.map {
            with(it) {
                EventPreview (id,title,image,price, Calendar.getInstance())
            }
        }.toList()
    }
}