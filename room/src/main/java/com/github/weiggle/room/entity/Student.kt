package com.github.weiggle.room.entity

import androidx.room.*

@Entity(tableName = "student")
class Student constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var age: Int,
)


@Dao
interface StudentDao {
    @Query("SELECT * FROM student")
    fun getStudent(): List<Student>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(student: Student)

    @Query("DELETE FROM student WHERE id =:id")
    fun deleteStudentById(id: Int)

    @Query("DELETE FROM student")
    fun deleteAll()
}