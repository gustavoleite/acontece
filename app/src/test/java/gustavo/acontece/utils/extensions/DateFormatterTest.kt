package gustavo.acontece.utils.extensions

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.util.*

class DateFormatterTest {

    @Test
    fun `must converter calendar to day, month and year as string`() {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, 31)
            set(Calendar.MONTH, 11)
            set(Calendar.YEAR, 2018)
        }
        assertThat(calendar.toDayMonthYear(), `is`("31/12/2018"))
    }

    @Test
    fun `must converter long to calendar`() {
        val calendar = Calendar.getInstance().apply {
            time = Date(1546283080)
        }
        assertThat(1546283080.toLong().toCalendar(), `is`(calendar))
    }
}