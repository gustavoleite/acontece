package gustavo.acontece.utils.extensions

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CurrencyFormatterTest {

    @Test
    fun `must convert to string and add br currency symbol`() {
        assertThat(0.0.toBrCurrency(), `is`("R$ 0,00"))
        assertThat(0.5.toBrCurrency(), `is`("R$ 0,50"))
        assertThat(5.3.toBrCurrency(), `is`("R$ 5,30"))
        assertThat(23.56.toBrCurrency(), `is`("R$ 23,56"))
        assertThat(968.23.toBrCurrency(), `is`("R$ 968,23"))
        assertThat(1435.0.toBrCurrency(), `is`("R$ 1.435,00"))
        assertThat(12435.0.toBrCurrency(), `is`("R$ 12.435,00"))
        assertThat(123435.09.toBrCurrency(), `is`("R$ 123.435,09"))
    }
}