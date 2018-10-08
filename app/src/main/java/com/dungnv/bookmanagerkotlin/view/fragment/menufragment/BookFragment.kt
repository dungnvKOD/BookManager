package com.dungnv.myapp.view.fragment.menufragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.bookmanagerkotlin.model.data.BookData
import com.dungnv.bookmanagerkotlin.model.data.BookTypeData
import com.dungnv.bookmanagerkotlin.view.adapter.BookAdapter
import com.dungnv.bookmanagerkotlin.view.adapter.LoaiSachRecyclerItemTouchHelper
import com.dungnv.bookmanagerkotlin.view.adapter.SachRecyclerItemTouchHelper
import com.dungnv.bookmanagerkotlin.view.adapter.TypeBookAdapter
import com.dungnv.bookmanagerkotlin.view.fragment.menufragment.AddBookFragment
import com.dungnv.myapp.model.Entity.Book
import com.dungnv.myapp.view.activity.BackgroundActivity
import com.dungnv.myapp.view.fragment.BackGroundFragment
import kotlinx.android.synthetic.main.fragment_book.*
import kotlinx.android.synthetic.main.fragment_the_loai.*

class BookFragment : Fragment(), View.OnClickListener, BookAdapter.OnClickItemLestener, SachRecyclerItemTouchHelper.RecyclerItemTouchHelperListener {


    private lateinit var rootView: View
    private lateinit var bookData: BookData
    private lateinit var bookAdapter: BookAdapter
    private lateinit var books: ArrayList<Book>
    private lateinit var bookTypeData: BookTypeData

    companion object {
        val newFragment = BookFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_book, container, false)
        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as BackgroundActivity).setSupportActionBar(toobarBook)
        (activity as BackgroundActivity).supportActionBar!!.setDisplayShowTitleEnabled(false)
        toobarBook.navigationIcon = (activity as BackgroundActivity).resources.getDrawable(R.drawable.ic_arrow_back_black_24dp, (activity as BackgroundActivity).theme)
        toobarBook.setNavigationOnClickListener {
            BackGroundFragment().BaseFragment().popbacktask()

        }
        bookTypeData = BookTypeData(activity!!)
        bookData = BookData(activity!!)
        books = bookData.getBook()!!
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rcvBook.layoutManager = linearLayoutManager

        bookAdapter = BookAdapter(activity!!, books, bookTypeData)
        rcvBook.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        rcvBook.adapter = bookAdapter

        val itemTouchHelperCallback = SachRecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rcvBook)


        fabBook.setOnClickListener(this)
        bookAdapter.setOnClickItemListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fabBook -> {

                BackGroundFragment().BaseFragment().addFragment(AddBookFragment(object : AddBookFragment.OnAddBookListener {
                    override fun onAddBook(book: Book, id: String) {
                        bookData.insertBook(book, id)
//                        bookAdapter.notifyDataSetChanged()
                        bookAdapter.insertItem(book)
                    }
                }))
            }
        }
    }

    override fun onEditItem(book: Book, position: Int) {
        //ok


    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        if (viewHolder is BookAdapter.ViewHolder) {
//            val name = books[viewHolder.getAdapterPosition()].title
            val deletedItem = books[viewHolder.getAdapterPosition()]
            val deletedIndex = viewHolder.getAdapterPosition()
            bookData.remove(books[position].idBook!!)
            bookAdapter.removeItem(viewHolder.getAdapterPosition())
            val snackbar = Snackbar.make(coordinator_layout_sach, " Đã xóa!", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }
}