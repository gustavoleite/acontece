package gustavo.acontece.ui.eventdetail

import android.arch.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import gustavo.acontece.BaseTest
import gustavo.acontece.SynchronousTestSchedulerRule
import gustavo.acontece.data.entity.model.Cupon
import gustavo.acontece.data.entity.model.EventDetail
import gustavo.acontece.data.entity.model.Location
import gustavo.acontece.data.entity.model.People
import gustavo.acontece.data.repository.EventRepositoryImpl
import gustavo.acontece.utils.resource.ResourceProviderImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.*

@RunWith(RobolectricTestRunner::class)
class EventDetailViewModelTest : BaseTest() {

    lateinit var viewModel: EventDetailViewModel

    @MockK
    lateinit var repository: EventRepositoryImpl

    @MockK
    lateinit var mockResponse: Observer<EventDetail>

    @Rule
    @JvmField
    val testRule = SynchronousTestSchedulerRule()

    val peopleList = listOf(People("10", "Kelvin", "url"))
    val location = Location(-123.00, -456.00, "title")
    val cuponList = listOf(Cupon("5", 2.14))
    val eventDetail = EventDetail(
        "1",
        "title",
        "url",
        25.54,
        Calendar.getInstance().apply { timeInMillis = 1234 },
        "description",
        location,
        peopleList,
        cuponList
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = EventDetailViewModel(repository, ResourceProviderImpl(ApplicationProvider.getApplicationContext()))
    }

    @Test
    fun `when load data with success must set event detail`() {
        every {
            repository.fetchEventDetail("1")
        } returns Single.just(
            eventDetail
        )

        viewModel.event.observe(mockLifecycleOwner(), mockResponse)
        viewModel.loadData("1")
        verify { mockResponse.onChanged(eventDetail) }
        assertThat(viewModel.description.get(), `is`("description"))
        assertThat(viewModel.cupon.get(), `is`("R$ 23,40"))
        assertThat(viewModel.image.get(), `is`("url"))
        assertThat(viewModel.date.get(), `is`("31/12/1969"))
        assertThat(viewModel.price.get(), `is`("R$ 25,54"))
    }
}