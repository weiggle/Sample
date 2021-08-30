package com.github.weiggle.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.github.weiggle.room.databinding.StudentItemLayoutBinding
import com.github.weiggle.room.entity.Student

/**
 * @author wei.li
 * @created on 2021/8/30
 * @desc desc
 *
 */
class StudentAdapter() : RecyclerView.Adapter<StudentViewHolder>() {
    var data: List<Student>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val viewBinding = StudentItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(viewBinding!!.root).apply {
            itemLayoutBinding = viewBinding
        }
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        if (null == data) return
        val viewBinding1 = holder.itemLayoutBinding as? StudentItemLayoutBinding
        viewBinding1?.let {
            val student = data!![position]
            it.id.text = student.id.toString()
            it.name.text = student.name
            it.age.text = student.age.toString()
        }
    }

    fun update(data: List<Student>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {

        return if (data.isNullOrEmpty()) 0 else data!!.size
    }
}


class StudentViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var itemLayoutBinding: ViewBinding?=null

}