package com.dungnv.bookmanagerkotlin.view.fragment.menufragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.bookmanagerkotlin.view.adapter.UserAdapter
import com.dungnv.myapp.model.Entity.User
import com.dungnv.myapp.model.data.UserData
import com.dungnv.myapp.view.activity.BackgroundActivity
import com.dungnv.myapp.view.fragment.BackGroundFragment
import kotlinx.android.synthetic.main.fragement_user.*

class UserFragment : Fragment(), UserAdapter.OnClickItemLestener {


    private lateinit var rootView: View
    private lateinit var userAdapter: UserAdapter
    private lateinit var userData: UserData
    private var users: ArrayList<User> = ArrayList()

    companion object {
        val newFragment = UserFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragement_user, container, false)
        userData = UserData(activity!!)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BackgroundActivity).setSupportActionBar(toobar_user)
        (activity as BackgroundActivity).supportActionBar!!.title = "User"
        toobar_user.navigationIcon = (activity as BackgroundActivity).resources.getDrawable(R.drawable.ic_arrow_back_black_24dp, (activity as BackgroundActivity).theme)
        toobar_user.setNavigationOnClickListener {
            BackGroundFragment().BaseFragment().popbacktask()
        }

        init()
        userAdapter.setOnClickItemListener(this)
    }

    private fun init() {
        users = userData.getAllUser()
        userAdapter = UserAdapter(activity!!, users)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rcvUser.layoutManager = linearLayoutManager
        rcvUser.adapter = userAdapter
    }

    override fun onRemoveItem(user: User, position: Int) {
        userData.remove(user.userName!!)
//        users.removeAt(position)
        userAdapter.removeItem(user, position)

    }

    override fun onEditItem(user: User, position: Int) {
        Toast.makeText(activity!!, "asadsa", Toast.LENGTH_LONG).show()
    }
}