package gustavo.acontece.ui.events

import android.arch.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import gustavo.acontece.BaseTest
import gustavo.acontece.SynchronousTestSchedulerRule
import gustavo.acontece.data.entity.model.EventPreview
import gustavo.acontece.data.repository.EventRepositoryImpl
import gustavo.acontece.utils.EventObserver
import gustavo.acontece.utils.resource.ResourceProviderImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
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
class EventsViewModelTest : BaseTest() {

    lateinit var viewModel: EventsViewModel

    @MockK
    lateinit var repository: EventRepositoryImpl

    @MockK
    lateinit var mockList: Observer<List<EventPreview>>

    @MockK
    @RelaxedMockK
    lateinit var mockError: EventObserver<String>

    @Rule
    @JvmField
    val testRule = SynchronousTestSchedulerRule()

    val eventPreviewList = listOf(
        EventPreview("1", "title", "", 25.54, Calendar.getInstance().apply { timeInMillis = 1234 }),
        EventPreview("2", "title", "", 73.09, Calendar.getInstance().apply { timeInMillis = 456 })
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = EventsViewModel(repository, ResourceProviderImpl(ApplicationProvider.getApplicationContext()))
    }


    @Test
    fun `when load data with success must set event preview list`() {
        every {
            repository.fetchEvents()
        } returns Single.just(
            eventPreviewList
        )

        viewModel.eventPreviewList.observe(mockLifecycleOwner(), mockList)
        viewModel.loadData()
        verify { mockList.onChanged(eventPreviewList) }
    }

    @Test
    fun `when load data with error must set error message`() {
        every {
            repository.fetchEvents()
        } returns Single.error(Exception(""))
        viewModel.loadData()
        assertThat(viewModel.errorMessage.value?.peekContent(), `is`("Ops, algo deu errado!"))
    }
}