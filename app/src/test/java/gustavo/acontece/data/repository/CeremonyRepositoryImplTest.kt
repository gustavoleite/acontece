package gustavo.acontece.data.repository

import gustavo.acontece.data.api.CerimonyApi
import gustavo.acontece.data.entity.domain.Ceremony
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

class CeremonyRepositoryImplTest {

    private lateinit var ceremonyRepositoryImpl: CeremonyRepositoryImpl

    @Mock
    private lateinit var cerimonyApi: CerimonyApi

    private val cuponsItemOutputList = listOf(CuponsItemOutput("1", 10, "1"))
    private val peopleItemOutputList = listOf(PeopleItemOutput("1", "Kelvin", "1", ""))
    private val eventOutputList = listOf(EventOutput("1", "Event title", 1234,"",25.54, "-123", "-456", "Event description", cuponsItemOutputList, peopleItemOutputList))

    @Before
    fun setup() {
        initMocks(this)
        ceremonyRepositoryImpl = CeremonyRepositoryImpl(cerimonyApi)
    }

    @Test
    fun `fetch ceremonies`() {
        `when`(cerimonyApi.getAllEvents()).thenReturn(
            Single.just(
                eventOutputList
            )
        )
        val expectedValue = Single.just(listOf(Ceremony("1", "Event", "", 25.54, Calendar.getInstance())))
        assertThat(ceremonyRepositoryImpl.fetchCeremonies(), `is`(expectedValue))
    }
}