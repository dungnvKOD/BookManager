package com.dungnv.bookmanagerkotlin.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.bookmanagerkotlin.model.data.BookTypeData
import com.dungnv.myapp.model.Entity.Book
import kotlinx.android.synthetic.main.item_sach.view.*

class BookAdapter(context: Context, private var books: ArrayList<Book>, private val bookTypeData: BookTypeData) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    companion object {
        private const val TAG = "BookAdapter"
    }

    private lateinit var onClickItemLestener: OnClickItemLestener


    private val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_sach, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return books.size

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val book = books[p1]
        p0.txtName.text = book.title
        p0.txtGia.text = "SoLuong : " + book.quanlity.toString()
        Log.d(TAG, "...DUNG...:${book.theLoai} ")


        p0.itemView.setOnClickListener {
            onClickItemLestener.onEditItem(book, p0.adapterPosition)
        }

    }

    fun setOnClickItemListener(onClickItemLestener: OnClickItemLestener) {
        this.onClickItemLestener = onClickItemLestener
    }

    fun removeItem(position: Int) {
        books.removeAt(position)
        notifyItemRemoved(position)
    }

    fun insertItem(book: Book) {
        books.add(0, book)
//        notifyItemInserted(0)
        notifyDataSetChanged()
    }

    fun change(position: Int, book: Book) {
        books[position] = book

        notifyItemChanged(position)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName = view.txtNameBook
        val txtGia = view.txtGiaBook

//        val txttheLoai = view.txtTheLoaiBook

        val viewBackgroundBookType: RelativeLayout = view.view_backgroundBook
        val viewForegroundBookType: LinearLayout = view.view_foregroundBook
    }

    interface OnClickItemLestener {
        //        fun onRemoveItem(user: User, position: Int)
        fun onEditItem(book: Book, position: Int)
    }

}