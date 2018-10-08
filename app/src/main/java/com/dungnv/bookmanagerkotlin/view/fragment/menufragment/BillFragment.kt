package com.dungnv.myapp.view.fragment.menufragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.bookmanagerkotlin.base.IdRandom
import com.dungnv.bookmanagerkotlin.model.data.BillData
import com.dungnv.bookmanagerkotlin.view.adapter.HoaDonAdapter
import com.dungnv.myapp.model.Entity.Bill
import com.dungnv.myapp.view.Dialog.ThemHoaDonDialig
import com.dungnv.myapp.view.activity.BackgroundActivity
import com.dungnv.myapp.view.fragment.BackGroundFragment
import kotlinx.android.synthetic.main.fragment_bill.*
import java.util.ArrayList

class BillFragment : Fragment(), View.OnClickListener {

    private lateinit var rootView: View

    private lateinit var billData: BillData
    private lateinit var hoaDonAdapter: HoaDonAdapter
    private var bills: ArrayList<Bill>? = null


    companion object {
        @SuppressLint("StaticFieldLeak")

        private const val TAG = "BillFragment"


        val newFragment = BillFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_bill, container, false)
        billData = BillData(activity!!)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as BackgroundActivity).setSupportActionBar(toobarBill)
        (activity as BackgroundActivity).supportActionBar!!.setDisplayShowTitleEnabled(false)
        toobarBill.navigationIcon = (activity as BackgroundActivity).resources.getDrawable(R.drawable.ic_arrow_back_black_24dp, (activity as BackgroundActivity).theme)
        toobarBill.setNavigationOnClickListener {
            BackGroundFragment().BaseFragment().popbacktask()

        }
        bills = ArrayList()
        bills = billData.getAllItem()!!
        Log.d(TAG, "...DUNG...: ${bills!!.size}")
        if (bills != null) {
            val linearLayoutManager = LinearLayoutManager(activity)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            rcvill.layoutManager = linearLayoutManager
            hoaDonAdapter = HoaDonAdapter(context!!, bills!!)
            rcvill.adapter = hoaDonAdapter

        }

        fabThemHoaDon.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fabThemHoaDon -> {
                val dialog = ThemHoaDonDialig(activity as BackgroundActivity,
                        object : ThemHoaDonDialig.OnClickItemListener {
                            override fun onClickItem(date: String, id: String) {
                                val bill = Bill(id, date)
                                billData.insertItem(bill)
//                                hoaDonAdapter.
                                Toast.makeText(activity, date.toString(), Toast.LENGTH_LONG).show()
                            }

                        })
                dialog.show()

            }
        }
    }
}