package com.dungnv.bookmanagerkotlin.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.model.Entity.User
import kotlinx.android.synthetic.main.cart_list_item.view.*
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.ArrayList

class UserAdapter(context: Context, private val users: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var onClickItemLestener: OnClickItemLestener

    private val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = inflater.inflate(R.layout.cart_list_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val user = users[p1]
        p0.txtName.text = user.name
        p0.txtPhoneNumber.text = user.phoneNumber

        p0.itemView.setOnClickListener {
            onClickItemLestener.onEditItem(user, p0.adapterPosition)
        }
//        p0.itemView.setOnLongClickListener {
//            onClickItemLestener.onRemoveItem(user, p0.adapterPosition)
//            return@setOnLongClickListener true
//        }
    }

    fun setOnClickItemListener(onClickItemLestener: OnClickItemLestener) {
        this.onClickItemLestener = onClickItemLestener
    }

    fun removeItem(position: Int) {
        users.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtName = view.txtNameU
        val txtPhoneNumber = view.txtPhoneU
        val txtGioiTinh = view.txtGioiTinhIU

        val viewBackground: RelativeLayout = view.view_background
        val viewForeground: LinearLayout = view.view_foreground
    }

    interface OnClickItemLestener {
        //        fun onRemoveItem(user: User, position: Int)
        fun onEditItem(user: User, position: Int)
    }

}