package com.github.weiggle.jetpack.fragmentresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.weiggle.jetpack.databinding.FragmentBLayoutBinding

/**
 * @author wei.li
 * @created on 2022/5/10
 * @desc desc
 *
 */
class FragmentB : Fragment() {


    lateinit var viewBinding: FragmentBLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewBinding = FragmentBLayoutBinding.inflate(inflater, container, false)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.setFragmentResultListener("fragmentA", this) { requestKey, bundle ->
            println("requestKey=========>${requestKey}")
            val string = bundle.getString("text")
            viewBinding.text.text = viewBinding.text.text.toString() + " \n" + string
        }

        parentFragmentManager.setFragmentResultListener("fragmentD", this) { requestKey, bundle ->
            println("requestKey=========>${requestKey}")
            val string = bundle.getString("text")
            viewBinding.text.text = viewBinding.text.text.toString() + " \n" + string
        }


        viewBinding.text.setOnClickListener {
            parentFragmentManager.setFragmentResult("fragmentB", Bundle().apply {
                putString("text", "from B")
            })
        }

        childFragmentManager.beginTransaction()
            .replace(viewBinding.fragmentContainer.id, FragmentC())
            .commitAllowingStateLoss()

    }

}