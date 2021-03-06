package gustavo.acontece.data.repository

import gustavo.acontece.data.api.EventApi
import gustavo.acontece.data.entity.model.*
import gustavo.acontece.data.entity.output.CuponOutput
import gustavo.acontece.data.entity.output.EventOutput
import gustavo.acontece.data.entity.output.PeopleOutput
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import java.util.*

class EventDetailRepositoryImplTest {

    private lateinit var eventRepositoryImpl: EventRepositoryImpl

    @MockK
    private lateinit var eventApi: EventApi

    private val cuponsItemOutputList = listOf(CuponOutput("1", 2.14, "5"))
    private val peopleItemOutputList = listOf(PeopleOutput("1", "Kelvin", "10", "url"))
    private val eventOutputList = listOf(
        EventOutput(
            "1",
            "title",
            1234,
            "",
            25.54,
            "-123",
            "-456",
            "EventDetail description",
            cuponsItemOutputList,
            peopleItemOutputList
        )
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        eventRepositoryImpl = EventRepositoryImpl(eventApi)
    }

    @Test
    fun `fetch events test`() {
        every {
            eventApi.getAllEvents()
        } returns Single.just(
            eventOutputList
        )
        val expectedValue =
            listOf(EventPreview("1", "title", "", 25.54, Calendar.getInstance().apply { timeInMillis = 1234 }))
        eventRepositoryImpl.fetchEvents().test().assertValue(expectedValue)
    }

    @Test
    fun `fetch event detail test`() {
        every {
            eventApi.getEvent("1")
        } returns Single.just(
            eventOutputList.first()
        )
        val peopleList = listOf(People("10", "Kelvin", "url"))
        val location = Location(-123.00, -456.00, "title")
        val cuponList = listOf(Cupon("5", 2.14))
        val expectedValue = EventDetail(
            "1",
            "title",
            "",
            25.54,
            Calendar.getInstance().apply { timeInMillis = 1234 },
            "EventDetail description",
            location,
            peopleList,
            cuponList
        )
        eventRepositoryImpl.fetchEventDetail("1").test().assertValue(expectedValue)
    }
}