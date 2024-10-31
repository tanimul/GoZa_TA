package com.tanimul.goza_ta.presentations.fragments.home.presentations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tanimul.goza_ta.databinding.ItemCategoryBinding
import com.tanimul.goza_ta.presentations.fragments.home.domain.model.Category
import timber.log.Timber

class CategoriesAdapter(val viewModel: HomeViewModel) :
    ListAdapter<Category, CategoriesAdapter.CategoriesViewHolder>(DiffCallBack()) {
    class CategoriesViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: Category, homeViewModel: HomeViewModel) {
            binding.apply {
                item = data
                viewModel = homeViewModel
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CategoriesViewHolder {
        return CategoriesViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.onBind(getItem(position), viewModel)
    }

    private class DiffCallBack : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
            oldItem == newItem
    }
}

@BindingAdapter(value = ["bindCategories", "bindViewModel"], requireAll = true)
fun RecyclerView.bindCategories(
    data: List<Category>?, viewModel: HomeViewModel
) {
    Timber.d("onBind: $data")
    if (adapter == null) adapter = CategoriesAdapter(viewModel)
    val value = data ?: emptyList()
    val adapter = adapter as? CategoriesAdapter
    adapter?.submitList(value)
}