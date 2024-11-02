package com.tanimul.goza_ta.presentations.fragments.home.presentations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tanimul.goza_ta.common.domain.model.Recommended
import com.tanimul.goza_ta.databinding.ItemCategoryBinding
import com.tanimul.goza_ta.databinding.ItemSuggestionBinding
import com.tanimul.goza_ta.presentations.fragments.home.domain.model.Category
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
class SuggestionAdapter(val viewModel: HomeViewModel) :
    ListAdapter<Recommended, SuggestionAdapter.SuggestionViewHolder>(DiffCallBack()) {
    class SuggestionViewHolder(val binding: ItemSuggestionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: Recommended, homeViewModel: HomeViewModel) {
            binding.apply {
                item = data
                viewModel = homeViewModel
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SuggestionViewHolder {
        return SuggestionViewHolder(
            ItemSuggestionBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: SuggestionViewHolder, position: Int) {
        holder.onBind(getItem(position), viewModel)
    }

    private class DiffCallBack : DiffUtil.ItemCallback<Recommended>() {
        override fun areItemsTheSame(oldItem: Recommended, newItem: Recommended): Boolean =
            oldItem.propertyName == newItem.propertyName

        override fun areContentsTheSame(oldItem: Recommended, newItem: Recommended): Boolean =
            oldItem == newItem
    }
}

@BindingAdapter(value = ["bindSuggestion", "bindViewModel"], requireAll = true)
@ExperimentalCoroutinesApi
fun RecyclerView.bindSuggestion(
    data: List<Recommended>?, viewModel: HomeViewModel
) {
    Timber.d("onBind: $data")
    if (adapter == null) adapter = SuggestionAdapter(viewModel)
    val value = data ?: emptyList()
    val adapter = adapter as? SuggestionAdapter
    adapter?.submitList(value)
}