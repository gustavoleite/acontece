package gustavo.acontece.ui.checkin

import android.arch.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import gustavo.acontece.BaseTest
import gustavo.acontece.data.repository.EventRepositoryImpl
import gustavo.acontece.utils.Event
import gustavo.acontece.utils.resource.ResourceProviderImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CheckinViewModelTest : BaseTest() {

    lateinit var viewModel: CheckinViewModel

    @MockK
    lateinit var repository: EventRepositoryImpl

    @MockK
    lateinit var mockCloseNavigation: Observer<Event<Boolean>>

    @MockK
    lateinit var mockStatusRequest : Observer<Status>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = CheckinViewModel(repository, ResourceProviderImpl(ApplicationProvider.getApplicationContext()))
    }

    @Test
    fun `must set static data`() {
        assertThat(viewModel.checkinTitle, `is`("Fazer check-in"))
        assertThat(viewModel.nameHint, `is`("Nome"))
        assertThat(viewModel.emailHint, `is`("E-mail"))
    }

    @Test
    fun `when close button pressed must trigger action`() {
        viewModel.navigation.observe(mockLifecycleOwner(), mockCloseNavigation)
        viewModel.onClosePressed()
        verify { mockCloseNavigation.onChanged(Event(true)) }
    }

    @Test
    fun `when make checkin with success must trigger action`() {
        every {
            repository.makeCheckin(any(), any(), any())
        } returns Single.just(
            true
        )

        viewModel.requestStatus.observe(mockLifecycleOwner(), mockStatusRequest)
        viewModel.onCheckinPressed()
        verify { mockStatusRequest.onChanged(Status.COMPLETE) }
    }
}