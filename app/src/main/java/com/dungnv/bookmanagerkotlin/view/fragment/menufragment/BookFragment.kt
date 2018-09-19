package com.dungnv.myapp.view.fragment.menufragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.view.Dialog.SachDialog
import com.dungnv.myapp.view.activity.BackgroundActivity
import com.dungnv.myapp.view.fragment.BackGroundFragment
import kotlinx.android.synthetic.main.fragment_bill.*
import kotlinx.android.synthetic.main.fragment_book.*

class BookFragment : Fragment(), View.OnClickListener {

    private lateinit var rootView: View

    companion object {
        val newFragment = BookFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_book, container, false)
        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as BackgroundActivity).setSupportActionBar(toobarBook)
        (activity as BackgroundActivity).supportActionBar!!.setDisplayShowTitleEnabled(false)
        toobarBook.navigationIcon = (activity as BackgroundActivity).resources.getDrawable(R.drawable.ic_arrow_back_black_24dp, (activity as BackgroundActivity).theme)
        toobarBook.setNavigationOnClickListener {
            BackGroundFragment().BaseFragment().popbacktask()

        }

        fabBook.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fabBook -> {
                val dialog = SachDialog(activity as BackgroundActivity)
                dialog.show()
            }

        }
    }

}