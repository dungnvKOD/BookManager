package com.dungnv.myapp.view.fragment.menufragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.view.Dialog.ChangePassword
import com.dungnv.myapp.view.activity.BackgroundActivity
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : BottomSheetDialogFragment(), View.OnClickListener {


    private lateinit var rootView: View
    private lateinit var dialog: ChangePassword

    companion object {
        @SuppressLint("StaticFieldLeak")
        val newFragment = UserFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_user, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = ChangePassword(activity as BackgroundActivity)

        btnDoiMatKhau.setOnClickListener(this)
        btnDangXuat.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnDoiMatKhau -> {
                dialog.show()
                dismiss()
            }
            R.id.btnDangXuat -> {
                dismiss()
            }
        }
    }


}