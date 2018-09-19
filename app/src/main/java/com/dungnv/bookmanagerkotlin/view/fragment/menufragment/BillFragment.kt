package com.dungnv.myapp.view.fragment.menufragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.view.Dialog.ThemHoaDonDialig
import com.dungnv.myapp.view.activity.BackgroundActivity
import com.dungnv.myapp.view.fragment.BackGroundFragment
import kotlinx.android.synthetic.main.fragment_bill.*

class BillFragment : Fragment(), View.OnClickListener {


    private lateinit var rootView: View

    companion object {
        val newFragment = BillFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_bill, container, false)
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

        fabThemHoaDon.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fabThemHoaDon -> {

                val dialog = ThemHoaDonDialig(activity as BackgroundActivity)
                dialog.show()
            }
        }
    }

}