package com.dungnv.bookmanagerkotlin.view.fragment.menufragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.bookmanagerkotlin.common.Constants
import com.dungnv.bookmanagerkotlin.model.data.BookTypeData
import com.dungnv.myapp.model.Entity.Book
import com.dungnv.myapp.model.Entity.BookType
import com.dungnv.myapp.view.activity.BackgroundActivity
import com.dungnv.myapp.view.fragment.BackGroundFragment
import kotlinx.android.synthetic.main.fragment_add_book.*

@SuppressLint("ValidFragment")
class AddBookFragment(private val onAddBookListener: OnAddBookListener) : Fragment(), View.OnClickListener {
    companion object {
        private const val TAG = "AddBookFragment"
    }


    private lateinit var rootView: View
    private lateinit var bookTypeData: BookTypeData
    private lateinit var bookTypes: ArrayList<BookType>
    private var idBookType: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_add_book, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as BackgroundActivity).setSupportActionBar(toobarAddBook)
        (activity as BackgroundActivity).supportActionBar!!.title = "User"
       if ((activity as BackgroundActivity).check){



       }
        toobarAddBook.navigationIcon = (activity as BackgroundActivity).resources.getDrawable(R.drawable.ic_arrow_back_black_24dp, (activity as BackgroundActivity).theme)
        toobarAddBook.setNavigationOnClickListener {
            BackGroundFragment().BaseFragment().popbacktask()
        }
        btnOkThemSach.setOnClickListener(this)
        initData()
    }

    private fun initData() {

        bookTypeData = BookTypeData(activity!!)
        val names: ArrayList<String> = bookTypeData.getNameBookType()!!
        val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, names)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
        spLoaiSach.adapter = arrayAdapter
        spLoaiSach.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (Constants.isDebug) Log.d(TAG, "...DUNG...:${names[position]} ")
                idBookType = bookTypeData.getIdBookType(names[position])

            }
        }

    }

    interface OnAddBookListener {
        fun onAddBook(book: Book, id: String)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnOkThemSach -> {

                val name = edtTieuDeSachDialog.text.toString().trim()
                val gia = edtGiaBanSachDialog.text.toString().trim()
                val soLuong = edtSoLuongSachDialog.text.toString().trim()
                val nxb = edtNhaXuatBanSachLoaiDialog.text.toString().trim()
                val tacgGia = edtTacGiaSachDialog.text.toString().trim()
                val book = Book(name, gia.toFloat(), soLuong.toInt(), nxb, tacgGia)

                if (Constants.isDebug) Log.d(TAG, "...DUNG...: $name,$gia,$soLuong,$nxb,$tacgGia,$idBookType")

                onAddBookListener.onAddBook(book, idBookType!!)
                BackGroundFragment().BaseFragment().popbacktask()

            }
        }
    }
}