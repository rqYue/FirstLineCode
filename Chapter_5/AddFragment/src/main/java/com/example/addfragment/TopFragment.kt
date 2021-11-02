package com.example.addfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class TopFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 在 fragment 中获取到 activity
//        if(activity != null){
//            val mainActivity = activity as MainActivity
//        }

        return inflater.inflate( R.layout.fragment_top, container, false)
    }

}