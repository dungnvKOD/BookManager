package com.dungnv.myapp.view.Dialog

import android.content.Context
import android.os.Bundle
import android.view.View
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.view.BaseDialog
import kotlinx.android.synthetic.main.dialog_them_sach.*

class SachDialog(context: Context) : BaseDialog(context), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_them_sach)

        btnOkThemSach.setOnClickListener(this)
        btnHuyThemSach.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnOkThemSach -> {

            }

            R.id.btnHuyThemSach -> {
                cancel()
            }
        }
    }
}