package com.dungnv.myapp.view.Dialog

import android.content.Context
import android.os.Bundle
import android.view.View
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.bookmanagerkotlin.base.IdRandom
import com.dungnv.myapp.model.Entity.BookType
import com.dungnv.myapp.view.BaseDialog
import kotlinx.android.synthetic.main.dialog_them_the_loai.*

class TheLoaiSachDialog(context: Context, private var onDialogListener: OnDialogListener) : BaseDialog(context), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_them_the_loai)


        btnOkTheLoaiPass.setOnClickListener(this)
        btnHuyThemTheLoaiPass.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnOkTheLoaiPass -> {
                var id: String? = IdRandom().randomId("B")

                val name = edtTenSachTheLoaiDialog.text.toString().trim()
                val moTa = edtMoTaTheLoaiDialog.text.toString().trim()
                val viTri = edtViTriTheLoaiDialog.text.toString().trim()
                val bookType = BookType(id, name, moTa, viTri.toInt())
                onDialogListener.onDialogOkListener(bookType)
                cancel()
            }
            R.id.btnHuyThemTheLoaiPass -> {
                cancel()
            }
        }
    }


    interface OnDialogListener {
        fun onDialogOkListener(bookType: BookType)
    }

}