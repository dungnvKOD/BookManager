package com.dungnv.myapp.view.fragment.menufragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.bookmanagerkotlin.view.fragment.menufragment.UserFragment
import com.dungnv.myapp.view.Dialog.ChangePassword
import com.dungnv.myapp.view.activity.BackgroundActivity
import com.dungnv.myapp.view.fragment.BackGroundFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*


class UserBottomSheetFragment : BottomSheetDialogFragment(), View.OnClickListener {


    private lateinit var rootView: View
    private lateinit var dialog: ChangePassword

    companion object {
        @SuppressLint("StaticFieldLeak")
        val newFragment = UserBottomSheetFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = ChangePassword(activity as BackgroundActivity)
        init()
        btnDoiMatKhauBU.setOnClickListener(this)
        btnDangXuatBU.setOnClickListener(this)
    }

    private fun init() {
        txtNameUserBU.text = (activity as BackgroundActivity).user!!.name
        txtPhoneNumberUserBU.text = (activity as BackgroundActivity).user!!.phoneNumber

    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnDoiMatKhauBU -> {
//                dialog.show()
                BackGroundFragment().BaseFragment().addFragment(UserFragment.newFragment)
                dismiss()
            }
            R.id.btnDangXuatBU -> {
                //TODO truoc khi dang xuat gan toan bo data ve null


                dismiss()
            }
        }
    }


}