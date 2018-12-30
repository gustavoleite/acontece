package gustavo.acontece.data.repository

import gustavo.acontece.data.api.EventApi
import gustavo.acontece.data.entity.domain.EventPreview
import gustavo.acontece.data.entity.output.CuponsItemOutput
import gustavo.acontece.data.entity.output.EventOutput
import gustavo.acontece.data.entity.output.PeopleItemOutput
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import java.util.*

class EventPreviewRepositoryImplTest {

    private lateinit var ceremonyRepositoryImpl: EventRepositoryImpl

    @Mock
    private lateinit var eventApi: EventApi

    private val cuponsItemOutputList = listOf(CuponsItemOutput("1", 10, "1"))
    private val peopleItemOutputList = listOf(PeopleItemOutput("1", "Kelvin", "1", ""))
    private val eventOutputList = listOf(EventOutput("1", "Event title", 1234,"",25.54, "-123", "-456", "Event description", cuponsItemOutputList, peopleItemOutputList))

    @Before
    fun setup() {
        initMocks(this)
        ceremonyRepositoryImpl = EventRepositoryImpl(eventApi)
    }

    @Test
    fun `fetch ceremonies`() {
        `when`(eventApi.getAllEvents()).thenReturn(
            Single.just(
                eventOutputList
            )
        )
        val expectedValue = Single.just(listOf(EventPreview("1", "Event", "", 25.54, Calendar.getInstance())))
        assertThat(ceremonyRepositoryImpl.fetchCeremonies(), `is`(expectedValue))
    }
}