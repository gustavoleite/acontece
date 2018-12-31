package gustavo.acontece.ui.home

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import gustavo.acontece.R
import gustavo.acontece.data.entity.model.EventPreview
import gustavo.acontece.databinding.EventItemBinding
import javax.inject.Inject

class EventsListAdapter @Inject constructor() : RecyclerView.Adapter<EventsListAdapter.EventsViewHolder>() {

    private var events: List<EventPreview> = emptyList()
    private var itemClick: ((EventPreview) -> Unit)? = null

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val binding = holder.binding
        val eventPreview = events[position]

        val viewModel = EventsListViewModel(eventPreview)
        binding.viewModel = viewModel

        holder.setClickListener(itemClick)
    }

    override fun getItemCount(): Int = events.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val binding = DataBindingUtil.inflate<EventItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.event_item,
            parent,
            false
        )

        return EventsViewHolder(binding)
    }

    fun setEventPreviewList(events: List<EventPreview>) {
        this.events = events
        notifyDataSetChanged()
    }

    fun setClickListener(itemClick: ((EventPreview) -> Unit)?) {
        this.itemClick = itemClick
    }

    class EventsViewHolder(val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setClickListener(callback: ((EventPreview) -> Unit)?){
            binding.viewModel?.onItemPressedCallback = {
                    eventPreview -> callback?.invoke(eventPreview)
            }
        }
    }
}