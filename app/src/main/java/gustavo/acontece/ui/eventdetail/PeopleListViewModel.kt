package gustavo.acontece.ui.eventdetail

import android.arch.lifecycle.ViewModel
import gustavo.acontece.data.entity.model.People

class PeopleListViewModel(people: People) : ViewModel() {
    val name = people.name
    val selfie = people.picture
}