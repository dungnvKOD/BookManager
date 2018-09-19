package com.dungnv.myapp.view.Dialog

import android.content.Context
import android.os.Bundle
import android.view.View
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.view.BaseDialog
import kotlinx.android.synthetic.main.dialog_them_hoa_don.*

class ThemHoaDonDialig(context: Context) : BaseDialog(context), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_them_hoa_don)

        btnOkHoaDon.setOnClickListener(this)
        btnHuyThemHoaDon.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnOkHoaDon -> {

            }
            R.id.btnHuyThemHoaDon -> {
                cancel()
            }

        }
    }
}