package com.example.squaredirectoryproject.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.squaredirectoryproject.R
import com.example.squaredirectoryproject.data.model.Employee
import com.example.squaredirectoryproject.data.model.Employees

class Adapter(private val employees: Employees) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.employee_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bindView(employees)
    }

    override fun getItemCount(): Int {
        return employees.employees.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val profileImage: ImageView = itemView.findViewById(R.id.profile_image)
        val fullName: TextView = itemView.findViewById(R.id.full_name)
        val bio: TextView = itemView.findViewById(R.id.biography)
        val team: TextView = itemView.findViewById(R.id.team)
        val phone: TextView = itemView.findViewById(R.id.phone_number)
        val email: TextView = itemView.findViewById(R.id.email_address)

        fun bindView(employees: Employees) {
            Glide.with(itemView.context)
                .load(employees.employees.get(adapterPosition).photo_url_small)
                .circleCrop()
                .into(profileImage)

            fullName.text = employees.employees.get(adapterPosition).full_name
            team.text = employees.employees.get(adapterPosition).team
            bio.text = employees.employees.get(adapterPosition).biography
            phone.text = employees.employees.get(adapterPosition).phone_number
            email.text = employees.employees.get(adapterPosition).email_address
        }
    }

    fun clear() {
        employees.employees.toMutableList().clear()
        notifyDataSetChanged()
    }

    fun addAll(employeesList: Collection<Employee>){
        employees.employees.toMutableList().addAll(employeesList)
        notifyDataSetChanged()
    }
}