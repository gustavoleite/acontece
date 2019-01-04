package gustavo.acontece.ui.checkin

import android.arch.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import gustavo.acontece.BaseTest
import gustavo.acontece.SynchronousTestSchedulerRule
import gustavo.acontece.data.repository.EventRepositoryImpl
import gustavo.acontece.utils.EventObserver
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

@RunWith(RobolectricTestRunner::class)
class CheckinViewModelTest : BaseTest() {

    lateinit var viewModel: CheckinViewModel

    @MockK
    lateinit var repository: EventRepositoryImpl

    @MockK
    lateinit var observerCloseButton: EventObserver<Boolean>

    @MockK
    lateinit var mockCheckinStatusRequest: Observer<CheckinStatus>

    @Rule
    @JvmField
    val testRule = SynchronousTestSchedulerRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
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
        viewModel.onClosePressed()
        assertThat(viewModel.closeEvent.value?.peekContent(), `is`(true))
    }

    @Test
    fun `when make checkin with success must trigger complete status`() {
        every {
            repository.makeCheckin("1", "Ruan", "ruan@domain.com")
        } returns Single.just(
            true
        )

        viewModel.eventId = "1"
        viewModel.name.set("Ruan")
        viewModel.email.set("ruan@domain.com")
        viewModel.requestStatus.observe(mockLifecycleOwner(), mockCheckinStatusRequest)
        viewModel.onCheckinPressed()
        verify {
            mockCheckinStatusRequest.onChanged(CheckinStatus.COMPLETE)
        }
    }

    @Test
    fun `when make checkin with error must trigger error status`() {
        every {
            repository.makeCheckin("1", "Ruan", "ruan@domain.com")
        } returns Single.error(Exception(""))

        viewModel.eventId = "1"
        viewModel.name.set("Ruan")
        viewModel.email.set("ruan@domain.com")
        viewModel.requestStatus.observe(mockLifecycleOwner(), mockCheckinStatusRequest)
        viewModel.onCheckinPressed()
        verify {
            mockCheckinStatusRequest.onChanged(CheckinStatus.ERROR)
        }
    }
}