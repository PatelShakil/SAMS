package com.shakilpatel.sams.users.ui.teachers

import android.os.Bundle
import com.shakilpatel.sams.databinding.ActivityTeachersBinding
import com.shakilpatel.sams.admin.data.models.TeacherModel
import com.shakilpatel.sams.users.data.adapters.TeacherAdapter
import com.shakilpatel.sams.users.data.repo.TeacherRepo
import com.shakilpatel.sams.utils.BaseActivity


class TeachersActivity : BaseActivity() {
    lateinit var binding: ActivityTeachersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeachersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Teachers"
        val viewModel = TeacherViewModel(TeacherRepo())

        viewModel.teacherList.observe(this){
            val adapter = TeacherAdapter(it as ArrayList<TeacherModel>)
            binding.teachersRv.adapter = adapter
            adapter.notifyDataSetChanged()
        }




    }
}