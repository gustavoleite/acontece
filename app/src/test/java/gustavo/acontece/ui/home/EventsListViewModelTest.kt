package gustavo.acontece.ui.home

import gustavo.acontece.data.entity.model.EventPreview
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.util.*

class EventsListViewModelTest {

    private val calendar = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_MONTH, 31)
        set(Calendar.MONTH, 11)
        set(Calendar.YEAR, 2018)
    }
    private val eventPreview = EventPreview("1", "title", "url", 835.67, calendar)

    @Test
    fun `must set static data`() {
        val viewModel = EventsListViewModel(eventPreview)
        assertThat(viewModel.title, `is`("title"))
        assertThat(viewModel.image, `is`("url"))
        assertThat(viewModel.price, `is`("R$ 835,67"))
        assertThat(viewModel.date, `is`("31/12/2018"))
    }
}