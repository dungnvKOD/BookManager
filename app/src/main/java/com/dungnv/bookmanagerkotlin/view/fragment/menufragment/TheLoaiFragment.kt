package com.dungnv.myapp.view.fragment.menufragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.view.Dialog.TheLoaiSachDialog
import com.dungnv.myapp.view.Dialog.ThemHoaDonDialig
import com.dungnv.myapp.view.activity.BackgroundActivity
import com.dungnv.myapp.view.fragment.BackGroundFragment
import kotlinx.android.synthetic.main.fragment_book.*
import kotlinx.android.synthetic.main.fragment_the_loai.*

class TheLoaiFragment : Fragment(), View.OnClickListener {


    private lateinit var rootView: View

    companion object {
        val newFragment = TheLoaiFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_the_loai, container, false)
        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as BackgroundActivity).setSupportActionBar(toobarTheLoai)
        (activity as BackgroundActivity).supportActionBar!!.setDisplayShowTitleEnabled(false)
        toobarTheLoai.navigationIcon = (activity as BackgroundActivity).resources.getDrawable(R.drawable.ic_arrow_back_black_24dp, (activity as BackgroundActivity).theme)
        toobarTheLoai.setNavigationOnClickListener {
            BackGroundFragment().BaseFragment().popbacktask()

        }

        fabTheLoai.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fabTheLoai -> {

                val dialog = TheLoaiSachDialog(activity as BackgroundActivity)
                dialog.show()
            }
        }
    }
}