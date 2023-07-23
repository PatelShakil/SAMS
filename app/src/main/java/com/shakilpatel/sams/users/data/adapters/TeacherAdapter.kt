package com.shakilpatel.sams.users.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.florent37.expansionpanel.ExpansionLayout
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection
import com.shakilpatel.sams.R
import com.shakilpatel.sams.admin.data.models.TeacherModel
import com.shakilpatel.sams.databinding.SampleTeacherExpansionBinding

class TeacherAdapter : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {
    var list: ArrayList<TeacherModel>

    private val expansionsCollection = ExpansionLayoutCollection()

    constructor(list: ArrayList<TeacherModel>) : super() {
        this.list = list
    }

    init {
        expansionsCollection.openOnlyOne(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        return TeacherViewHolder(LayoutInflater.from(parent.context.applicationContext).inflate(R.layout.sample_teacher_expansion,null,false))
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.setData(list[position])
        expansionsCollection.add(holder.expansionLayout)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class TeacherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var expansionLayout: ExpansionLayout
        var binding : SampleTeacherExpansionBinding
        init {
            expansionLayout = itemView.findViewById(R.id.expansionLayout)
            expansionLayout.minimumWidth = 5000
            binding = SampleTeacherExpansionBinding.bind(itemView)
            binding.linear.minimumWidth = 5000
            binding.collection.minimumWidth = 5000
        }
        fun setData(obj : TeacherModel){
            binding.techerName.text = if(obj.gender.lowercase().equals("male")) {"Mr. " }
                else {"Mrs. "} + obj.name.split(" ")[0]
            binding.teacherFullname.text =  obj.name
            binding.teacherDes.text = "Designation : " + obj.des
            binding.teacherQua.text = "Qualification : "+obj.qualification
        }
    }
}