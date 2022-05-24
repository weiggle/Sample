package com.github.weiggle.jetpack.fragmentresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.weiggle.jetpack.R
import com.github.weiggle.jetpack.databinding.FragmentALayoutBinding

/**
 * @author wei.li
 * @created on 2022/5/10
 * @desc desc
 *
 */
class FragmentA : Fragment() {

    lateinit var viewBinding: FragmentALayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewBinding = FragmentALayoutBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction()
            .replace(viewBinding.fragmentContainer.id, FragmentB())
            .commitAllowingStateLoss()

        childFragmentManager.setFragmentResultListener("fragmentB",
            this) { requestKey, bundle ->

            viewBinding.text.text = viewBinding.text.text.toString() + "\n" + bundle["text"]

        }

        viewBinding.text.setOnClickListener {
            childFragmentManager.setFragmentResult("fragmentA", Bundle().apply {
                putString("text", "from A")
            })

            childFragmentManager.setFragmentResult("fragmentD", Bundle().apply {
                putString("text", "from A-D")
            })
        }


    }
}