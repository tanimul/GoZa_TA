package com.tanimul.goza_ta.presentations.fragments.see_all.presentations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tanimul.goza_ta.common.domain.model.Recommended
import com.tanimul.goza_ta.databinding.ItemPlaceBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@OptIn(ExperimentalCoroutinesApi::class)
class PlacesAdapter(val viewModel: SeeAllViewModel) :
    ListAdapter<Recommended, PlacesAdapter.PlacesViewHolder>(DiffCallBack()) {
    class PlacesViewHolder(val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: Recommended, seeAllViewModel: SeeAllViewModel) {
            binding.apply {
                item = data
                viewModel = seeAllViewModel
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): PlacesViewHolder {
        return PlacesViewHolder(
            ItemPlaceBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        holder.onBind(getItem(position), viewModel)
    }

    private class DiffCallBack : DiffUtil.ItemCallback<Recommended>() {
        override fun areItemsTheSame(oldItem: Recommended, newItem: Recommended): Boolean =
            oldItem.propertyName == newItem.propertyName

        override fun areContentsTheSame(oldItem: Recommended, newItem: Recommended): Boolean =
            oldItem == newItem
    }
}

@BindingAdapter(value = ["bindPlaces", "bindViewModel"], requireAll = true)
@OptIn(ExperimentalCoroutinesApi::class)
fun RecyclerView.bindPlaces(
    data: List<Recommended>?, viewModel: SeeAllViewModel
) {
    Timber.d("onBind: $data")
    if (adapter == null) adapter = PlacesAdapter(viewModel)
    val value = data ?: emptyList()
    val adapter = adapter as? PlacesAdapter
    adapter?.submitList(value)
}