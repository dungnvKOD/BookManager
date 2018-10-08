package com.dungnv.bookmanagerkotlin.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.model.Entity.BookType
import kotlinx.android.synthetic.main.item_book_type.view.*
import kotlinx.android.synthetic.main.item_them_laoi_sach.view.*
import java.util.ArrayList

class TypeBookAdapter(val context: Context, private val bookTypes: ArrayList<BookType>) : RecyclerView.Adapter<TypeBookAdapter.ViewHolder>() {
    private lateinit var onClickItemLestener: OnClickItemLestener

    private val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_book_type, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookTypes.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val bookType = bookTypes[p1]
        p0.txtNameType.text = bookType.nameType
        p0.txtMota.text = bookType.description

        p0.itemView.setOnClickListener {
            onClickItemLestener.onEditItem(bookType, p1)
        }

    }

    fun setOnClickItemListener(onClickItemLestener: OnClickItemLestener) {
        this.onClickItemLestener = onClickItemLestener
    }

    fun removeItem(position: Int) {
        bookTypes.removeAt(position)
        notifyItemRemoved(position)
    }

    fun insertItem(bookType: BookType) {
        bookTypes.add(0, bookType)
        Toast.makeText(context, "${bookTypes.size}", Toast.LENGTH_LONG).show()
        notifyDataSetChanged()
    }

    fun change(position: Int, bookType: BookType) {
        bookTypes[position] = bookType
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNameType = view.txtNameBookType
        val txtMota = view.txtMoTaBookType
        val txtViTri = view.txtViTriLoaiSach

        val viewBackgroundBookType: RelativeLayout = view.view_backgroundBookType
        val viewForegroundBookType: LinearLayout = view.view_foregroundBookType
    }

    interface OnClickItemLestener {
        fun onEditItem(bookType: BookType, position: Int)
    }

}