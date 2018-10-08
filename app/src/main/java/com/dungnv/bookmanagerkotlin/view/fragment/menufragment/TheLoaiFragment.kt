package com.dungnv.bookmanagerkotlin.view.fragment.menufragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.bookmanagerkotlin.common.Constants
import com.dungnv.bookmanagerkotlin.model.data.BookTypeData
import com.dungnv.bookmanagerkotlin.view.Dialog.DialogUpdateBookType
import com.dungnv.bookmanagerkotlin.view.adapter.LoaiSachRecyclerItemTouchHelper
import com.dungnv.bookmanagerkotlin.view.adapter.TypeBookAdapter
import com.dungnv.myapp.model.Entity.BookType
import com.dungnv.myapp.view.Dialog.TheLoaiSachDialog
import com.dungnv.myapp.view.activity.BackgroundActivity
import com.dungnv.myapp.view.fragment.BackGroundFragment
import kotlinx.android.synthetic.main.fragment_book.*
import kotlinx.android.synthetic.main.fragment_the_loai.*
import java.util.*

class TheLoaiFragment : Fragment(),
        View.OnClickListener,
        TypeBookAdapter.OnClickItemLestener,
        LoaiSachRecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private lateinit var bookTypeAdapter: TypeBookAdapter
    private lateinit var bookTypeData: BookTypeData

    private lateinit var rootView: View
    private lateinit var bookTypes: ArrayList<BookType>

    companion object {
        @SuppressLint("StaticFieldLeak")
        val newFragment = TheLoaiFragment()
        private const val TAG = "TheLoaiFragment"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_the_loai, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

        (activity as BackgroundActivity).setSupportActionBar(toobarTheLoai)
        (activity as BackgroundActivity).supportActionBar!!.setDisplayShowTitleEnabled(false)
        toobarTheLoai.navigationIcon = (activity as BackgroundActivity).resources.getDrawable(R.drawable.ic_arrow_back_black_24dp, (activity as BackgroundActivity).theme)
        toobarTheLoai.setNavigationOnClickListener {
            BackGroundFragment().BaseFragment().popbacktask()
        }
        fabTheLoai.setOnClickListener(this)

    }

    private fun initData() {
        bookTypeData = BookTypeData(activity!!)
        bookTypes = bookTypeData.getBookType()!!
        bookTypes.reverse()
        if (Constants.isDebug) Log.d(TAG, "...DUNG...: $bookTypes")

        if (bookTypes != null) {
            val linearLayoutManager = LinearLayoutManager(activity)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            rcvTheLoai.layoutManager = linearLayoutManager
            bookTypeAdapter = TypeBookAdapter(activity!!, bookTypes)
            rcvTheLoai.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            rcvTheLoai.adapter = bookTypeAdapter
        }
        val itemTouchHelperCallback = LoaiSachRecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rcvTheLoai)
        bookTypeAdapter.setOnClickItemListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fabTheLoai -> {
                val dialog = TheLoaiSachDialog(activity as BackgroundActivity, object : TheLoaiSachDialog.OnDialogListener {
                    override fun onDialogOkListener(bookType: BookType) {
                        bookTypeData.insertBookType(bookType)
                        bookTypeAdapter.insertItem(bookType)
                    }
                })
                dialog.show()
            }
        }
    }

    override fun onEditItem(bookType: BookType, position: Int) {
        (activity as BackgroundActivity).bookType = bookType
        val dialogUpdateBookType = DialogUpdateBookType(activity as BackgroundActivity, object : DialogUpdateBookType.OnClickItemListener {
            override fun onClickItem(bookT: BookType) {


                Toast.makeText(activity, "${bookT.idBookType},${bookT.nameType},${bookT.location},${bookT.description}", Toast.LENGTH_LONG).show()
                bookTypeData.update(bookT.idBookType!!, bookT.nameType!!, bookT.location, bookT.description!!)
                bookTypeAdapter.change(position, bookT)
                rcvTheLoai.adapter = bookTypeAdapter

            }
        })
        dialogUpdateBookType.show()
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        if (viewHolder is TypeBookAdapter.ViewHolder) {
            val name = bookTypes[viewHolder.getAdapterPosition()].nameType
            val deletedItem = bookTypes[viewHolder.getAdapterPosition()]
            val deletedIndex = viewHolder.getAdapterPosition()
            bookTypeData.remove(bookTypes[position].idBookType!!)
            bookTypeAdapter.removeItem(viewHolder.getAdapterPosition())
            val snackbar = Snackbar.make(coordinator_layout_the_loai, "$name Đã xóa!", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }

}