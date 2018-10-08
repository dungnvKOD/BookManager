package com.dungnv.bookmanagerkotlin.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.model.Entity.Bill
import kotlinx.android.synthetic.main.item_bill.view.*

class HoaDonAdapter(val context: Context, val bills: ArrayList<Bill>) : RecyclerView.Adapter<HoaDonAdapter.VieHolder>() {
    private val inflater = LayoutInflater.from(context)
    private lateinit var onClickItemListener: OnClickItemListener

    companion object {
        private const val TAG = "HoaDonAdapter"
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VieHolder {
        val view: View = inflater.inflate(R.layout.item_bill, p0, false)
        return VieHolder(view)

    }

    override fun getItemCount(): Int {
        return bills.size

    }

    override fun onBindViewHolder(p0: VieHolder, p1: Int) {
        val bill: Bill = bills[p1]
        Log.d(TAG, "...DUNG...: ${bill.idInvoice}")
        p0.txtMaHoaDon.text = bill.idInvoice
        p0.txtNgayMua.text = bill.purchasing

        p0.itemView.setOnClickListener {
            onClickItemListener.onClickItem(bill, p1)

        }
    }

    class VieHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtMaHoaDon: TextView = view.txtMaHoaDon
        val txtNgayMua: TextView = view.txtNgayMua
    }

    interface OnClickItemListener {
        fun onClickItem(bill: Bill, position: Int)
    }

    fun setOnClickListener(onClickItemListener: OnClickItemListener) {
        this.onClickItemListener = onClickItemListener

    }

}