package gustavo.acontece.data.entity.mapper

import gustavo.acontece.data.entity.input.CheckinInput
import gustavo.acontece.data.entity.model.*
import gustavo.acontece.data.entity.output.CuponOutput
import gustavo.acontece.data.entity.output.DataOutput
import gustavo.acontece.data.entity.output.EventOutput
import gustavo.acontece.data.entity.output.PeopleOutput
import gustavo.acontece.utils.extensions.toCalendar

object EventMapper {
    fun toEventPreviewList(output: List<EventOutput>): List<EventPreview> {
        return output.map {
            with(it) {
                EventPreview(id, title, image, price, date.toCalendar())
            }
        }.toList()
    }

    fun toEvent(output: EventOutput): EventDetail {
        return with(output) {
            EventDetail(
                id,
                title,
                image,
                price,
                date.toCalendar(),
                description,
                toLocation(latitude, longitude, title),
                toPeopleList(peopleList),
                toCuponList(cuponList)
            )
        }
    }

    fun toCheckinInput(eventId: String, name: String, email: String): CheckinInput {
        return CheckinInput(
            eventId,
            name,
            email
        )
    }

    fun toBoolean(dataOutput: DataOutput): Boolean {
        return dataOutput.code == "200"
    }

    private fun toLocation(latitude: String, longitude: String, title: String): Location {
        return Location(latitude.toDouble(), longitude.toDouble(), title)
    }

    private fun toPeopleList(peopleOutputList: List<PeopleOutput>): List<People> {
        return peopleOutputList.map {
            with(it) {
                People(id, name, picture)
            }
        }.toList()
    }

    private fun toCuponList(cuponOutputList: List<CuponOutput>): List<Cupon> {
        return cuponOutputList.map {
            with(it) {
                Cupon(id, discount)
            }
        }.toList()
    }
}