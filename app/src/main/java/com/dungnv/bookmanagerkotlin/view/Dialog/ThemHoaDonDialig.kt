package com.dungnv.myapp.view.Dialog

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.bookmanagerkotlin.base.IdRandom
import com.dungnv.myapp.view.BaseDialog
import kotlinx.android.synthetic.main.dialog_them_hoa_don.*
import java.util.*

class ThemHoaDonDialig(context: Context, private val onClickItemListener: OnClickItemListener) : BaseDialog(context), View.OnClickListener {

    private var datePicker = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_them_hoa_don)

        val calendar: Calendar = Calendar.getInstance()
        val ngay = calendar.get(Calendar.DAY_OF_MONTH)
        val thang = calendar.get(Calendar.MONTH)
        val nam = calendar.get(Calendar.YEAR)
        calendar.set(ngay, thang, nam)
        datePicker = calendar.timeInMillis.toInt()

        btnNgayMuaDialog.text = Date(calendar.timeInMillis).toString()
        val id = IdRandom().randomId("HD")
        edtMaHoaDonDialog.setText(id)

        btnOkHoaDon.setOnClickListener(this)
        btnHuyThemHoaDon.setOnClickListener(this)
        btnNgayMuaDialog.setOnClickListener(this)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(v: View?) {
        when (v!!.id) {

            R.id.btnNgayMuaDialog -> {
                val calendar: Calendar = Calendar.getInstance()
                val datePickerDialog: DatePickerDialog = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    btnNgayMuaDialog.text = Date(calendar.timeInMillis).toString()

                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                datePickerDialog.show()

            }

            R.id.btnOkHoaDon -> {
                val date = btnNgayMuaDialog.text.toString()
                val id = edtMaHoaDonDialog.text.toString()
                onClickItemListener.onClickItem(date, id)


            }
            R.id.btnHuyThemHoaDon -> {
                cancel()
            }
        }
    }

    interface OnClickItemListener {
        fun onClickItem(date: String, id: String)
    }
}