package com.github.weiggle.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.weiggle.room.adapter.StudentAdapter
import com.github.weiggle.room.database.StudentDatabase
import com.github.weiggle.room.databinding.ActivityMainBinding
import com.github.weiggle.room.entity.Student

class MainActivity : AppCompatActivity() {

    val viewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var adapter = StudentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        initRecyclerView()

        viewBinding.add.setOnClickListener {
            add()
        }

        viewBinding.query.setOnClickListener {
            query()
        }

        viewBinding.update.setOnClickListener {

        }

        viewBinding.delete.setOnClickListener {
            delete()
        }
    }

    var index = 0
    private fun add() {
        val studentDao = StudentDatabase.getDatabase(this).studentDao()
        studentDao.insert(Student(index, "name=>${index}", 10 + index))
        index++
    }

    private fun query() {
        val studentDao = StudentDatabase.getDatabase(this).studentDao()
        val student = studentDao.getStudent()
        adapter.update(student)
    }

    private fun update() {
        val studentDao = StudentDatabase.getDatabase(this).studentDao()
        val student = studentDao.getStudent()
        val first = student.first()
        first.name = first.name + "===>update"
    }

    private fun delete() {
        val studentDao = StudentDatabase.getDatabase(this).studentDao()
        val student = studentDao.getStudent()
        studentDao.deleteStudentById(student.first().id)
    }


    private fun initRecyclerView() {
        viewBinding.recyclerViwe.layoutManager = LinearLayoutManager(this)
        viewBinding.recyclerViwe.adapter = adapter
    }
}