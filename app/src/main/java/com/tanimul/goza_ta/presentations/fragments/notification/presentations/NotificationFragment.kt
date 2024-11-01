package com.tanimul.goza_ta.presentations.fragments.notification.presentations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tanimul.goza_ta.R
import com.tanimul.goza_ta.base.BaseFragment
import com.tanimul.goza_ta.databinding.FragmentNotificationBinding
import com.tanimul.goza_ta.databinding.FragmentSeeAllBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : BaseFragment<FragmentNotificationBinding>(R.layout.fragment_notification) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}