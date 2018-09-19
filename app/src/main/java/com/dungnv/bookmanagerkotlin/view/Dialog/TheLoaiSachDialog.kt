package com.dungnv.myapp.view.Dialog

import android.content.Context
import android.os.Bundle
import android.view.View
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.view.BaseDialog
import kotlinx.android.synthetic.main.dialog_them_the_loai.*

class TheLoaiSachDialog(context: Context) : BaseDialog(context), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_them_the_loai)


        btnOkTheLoaiPass.setOnClickListener(this)
        btnHuyThemTheLoaiPass.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnOkTheLoaiPass -> {

            }
            R.id.btnHuyThemTheLoaiPass -> {
                cancel()
            }

        }
    }
}