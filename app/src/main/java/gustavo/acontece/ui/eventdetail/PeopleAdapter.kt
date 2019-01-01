package gustavo.acontece.ui.eventdetail

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import gustavo.acontece.R
import gustavo.acontece.data.entity.model.EventPreview
import gustavo.acontece.data.entity.model.People
import gustavo.acontece.databinding.ItemPeopleBinding
import javax.inject.Inject

class PeopleAdapter @Inject constructor() : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    private var people: List<People> = emptyList()

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val binding = holder.binding
        val viewModel = PeopleViewModel(people[position])
        binding.viewModel = viewModel
    }

    override fun getItemCount(): Int = people.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val binding = DataBindingUtil.inflate<ItemPeopleBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_people,
            parent,
            false
        )

        return PeopleViewHolder(binding)
    }

    fun setPeopleList(people: List<People>) {
        this.people = people
        notifyDataSetChanged()
    }

    class PeopleViewHolder(val binding: ItemPeopleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setClickListener(callback: ((EventPreview) -> Unit)?){

        }
    }
}