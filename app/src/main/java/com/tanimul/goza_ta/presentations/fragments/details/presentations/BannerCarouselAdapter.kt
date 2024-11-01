package com.tanimul.goza_ta.presentations.fragments.details.presentations

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tanimul.goza_ta.databinding.ItemBannerBinding
import com.youth.banner.adapter.BannerAdapter


class BannerCarouselAdapter(
    mDatas: List<String?>,
) :
    BannerAdapter<String?, BannerCarouselAdapter.BannerViewHolder?>(mDatas) {

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {

        val binding =
            ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(binding)
    }


    inner class BannerViewHolder(val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindView(
        holder: BannerViewHolder?,
        data: String?,
        position: Int,
        size: Int
    ) {
        holder?.binding?.apply {
            url = data
        }

    }

}
