package gustavo.acontece.data.repository

import gustavo.acontece.data.api.EventApi
import gustavo.acontece.data.entity.model.EventPreview
import gustavo.acontece.data.entity.output.CuponOutput
import gustavo.acontece.data.entity.output.EventOutput
import gustavo.acontece.data.entity.output.PeopleOutput
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.util.*

class EventRepositoryImplTest {

    private lateinit var ceremonyRepositoryImpl: EventRepositoryImpl

    @MockK
    private lateinit var eventApi: EventApi

    private val cuponsItemOutputList = listOf(CuponOutput("1", 10, "1"))
    private val peopleItemOutputList = listOf(PeopleOutput("1", "Kelvin", "1", ""))
    private val eventOutputList = listOf(EventOutput("1", "Event title", 1234,"",25.54, "-123", "-456", "Event description", cuponsItemOutputList, peopleItemOutputList))

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `fetch events`() {
        every { eventApi.getAllEvents() } returns Single.just(
            eventOutputList
        )
        ceremonyRepositoryImpl = EventRepositoryImpl(eventApi)
        val expectedValue = Single.just(listOf(EventPreview("1", "Event", "", 25.54, Calendar.getInstance())))
        assertThat(ceremonyRepositoryImpl.fetchEvents(), `is`(expectedValue))
    }
}