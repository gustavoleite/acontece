package gustavo.acontece.ui.eventdetail

import gustavo.acontece.data.entity.model.People
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class PeopleListViewModelTest {

    private val people = People("1", "Sophia", "url")

    @Test
    fun `must set static data`() {
        val viewModel = PeopleListViewModel(people)
        assertThat(viewModel.name, `is`("Sophia"))
        assertThat(viewModel.selfie, `is`("url"))
    }
}