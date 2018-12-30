package gustavo.acontece.ui

import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(): ViewModel() {
    val title = "Ola mundo"
}