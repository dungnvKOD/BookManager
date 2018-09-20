package com.dungnv.bookmanagerkotlin.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.model.Entity.User
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.ArrayList

class UserAdapter(context: Context, private val users: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var onClickItemLestener: OnClickItemLestener

    private val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_user, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val user = users[p1]
        p0.txtName.text = user.name
//        p0.txtGioiTinh.text=user.
        p0.txtPhoneNumber.text = user.phoneNumber

        p0.itemView.setOnClickListener {
            onClickItemLestener.onEditItem(user, p0.adapterPosition)
        }
        p0.itemView.setOnLongClickListener {
            onClickItemLestener.onRemoveItem(user, p0.adapterPosition)
            return@setOnLongClickListener true
        }

    }

    fun setOnClickItemListener(onClickItemLestener: OnClickItemLestener) {
        this.onClickItemLestener = onClickItemLestener
    }

    fun removeItem(user: User, position: Int) {
        users.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName = view.txtNameIU
        val txtPhoneNumber = view.txtPhoneNumberIU
        val txtGioiTinh = view.txtGioiTinhIU
    }

    interface OnClickItemLestener {
        fun onRemoveItem(user: User, position: Int)
        fun onEditItem(user: User, position: Int)
    }

}