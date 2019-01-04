package gustavo.acontece.ui.events

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import gustavo.acontece.R
import gustavo.acontece.data.entity.model.EventPreview
import gustavo.acontece.databinding.ItemEventBinding
import javax.inject.Inject

class EventsListAdapter @Inject constructor() : RecyclerView.Adapter<EventsListAdapter.EventsViewHolder>() {

    private var events: List<EventPreview> = emptyList()
    private var itemClick: ((EventPreview) -> Unit)? = null
    private var itemShare: ((String) -> Unit)? = null

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val binding = holder.binding
        val viewModel = EventsListViewModel(events[position])
        binding.viewModel = viewModel
        holder.setItemCallback(itemClick)
        holder.setShareCallback(itemShare)
    }

    override fun getItemCount(): Int = events.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val binding = DataBindingUtil.inflate<ItemEventBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_event,
            parent,
            false
        )

        return EventsViewHolder(binding)
    }

    fun setEventPreviewList(events: List<EventPreview>) {
        this.events = events
        notifyDataSetChanged()
    }

    fun setItemCallback(itemClick: ((EventPreview) -> Unit)?) {
        this.itemClick = itemClick
    }

    fun setShareCallback(itemShare: ((String) -> Unit)?) {
        this.itemShare = itemShare
    }

    class EventsViewHolder(val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setItemCallback(callback: ((EventPreview) -> Unit)?) {
            binding.viewModel?.onItemPressedCallback = { eventPreview ->
                callback?.invoke(eventPreview)
            }
        }

        fun setShareCallback(callback: ((String) -> Unit)?) {
            binding.viewModel?.onSharePressedCallback = { text ->
                callback?.invoke(text)
            }
        }
    }
}