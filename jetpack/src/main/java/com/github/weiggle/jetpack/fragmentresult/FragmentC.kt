package com.github.weiggle.jetpack.fragmentresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.weiggle.jetpack.databinding.FragmentCLayoutBinding

/**
 * @author wei.li
 * @created on 2022/5/10
 * @desc desc
 *
 */
class FragmentC : Fragment() {

    lateinit var viewBinding: FragmentCLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewBinding = FragmentCLayoutBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}