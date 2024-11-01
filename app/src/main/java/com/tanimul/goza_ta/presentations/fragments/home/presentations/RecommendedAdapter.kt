package com.tanimul.goza_ta.presentations.fragments.home.presentations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tanimul.goza_ta.common.domain.model.Recommended
import com.tanimul.goza_ta.databinding.ItemRecommendedBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@OptIn(ExperimentalCoroutinesApi::class)
class RecommendedAdapter(val viewModel: HomeViewModel) :
    ListAdapter<Recommended, RecommendedAdapter.RecommendedViewHolder>(DiffCallBack()) {
    class RecommendedViewHolder(val binding: ItemRecommendedBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: Recommended, homeViewModel: HomeViewModel) {
            binding.apply {
                item = data
                viewModel = homeViewModel
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecommendedViewHolder {
        return RecommendedViewHolder(
            ItemRecommendedBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: RecommendedViewHolder, position: Int) {
        holder.onBind(getItem(position), viewModel)
    }

    private class DiffCallBack : DiffUtil.ItemCallback<Recommended>() {
        override fun areItemsTheSame(oldItem: Recommended, newItem: Recommended): Boolean =
            oldItem.propertyName == newItem.propertyName

        override fun areContentsTheSame(oldItem: Recommended, newItem: Recommended): Boolean =
            oldItem == newItem
    }
}

@BindingAdapter(value = ["bindRecommended", "bindViewModel"], requireAll = true)
@OptIn(ExperimentalCoroutinesApi::class)
fun RecyclerView.bindRecommended(
    data: List<Recommended>?, viewModel: HomeViewModel
) {
    Timber.d("onBind: $data")
    if (adapter == null) adapter = RecommendedAdapter(viewModel)
    val value = data ?: emptyList()
    val adapter = adapter as? RecommendedAdapter
    adapter?.submitList(value)
}