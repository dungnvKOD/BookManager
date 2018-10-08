package com.dungnv.bookmanagerkotlin.view.fragment.menufragment

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.bookmanagerkotlin.common.Constants
import com.dungnv.bookmanagerkotlin.common.Key
import com.dungnv.bookmanagerkotlin.view.adapter.RecyclerItemTouchHelper
import com.dungnv.bookmanagerkotlin.view.adapter.UserAdapter
import com.dungnv.myapp.model.Entity.User
import com.dungnv.myapp.model.data.UserData
import com.dungnv.myapp.view.Dialog.ChangePassword
import com.dungnv.myapp.view.activity.BackgroundActivity
import com.dungnv.myapp.view.fragment.BackGroundFragment
import kotlinx.android.synthetic.main.fragement_user.*

class UserFragment : Fragment(), UserAdapter.OnClickItemLestener, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private lateinit var rootView: View
    private lateinit var userAdapter: UserAdapter
    private lateinit var userData: UserData
    private lateinit var brocastResover: OnListenerBrocast
    private lateinit var intentFilter: IntentFilter
    private lateinit var userTemp: User
    private var users: ArrayList<User> = ArrayList()

    companion object {
        private const val TAG = "UserFragment"
        val newFragment = UserFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragement_user, container, false)
        userData = UserData(activity!!)
        brocastResover = OnListenerBrocast()
        intentFilter = IntentFilter(Key.ACTION_S)
        (activity as BackgroundActivity).registerReceiver(brocastResover, intentFilter)
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

        txtNameUserU.text = (activity as BackgroundActivity)!!.name
        txtPhoneNumberUserU.text = (activity as BackgroundActivity)!!.phone

        //lay danh user tru thang hien tai dang nhap
        users = userData.getAllUser((activity as BackgroundActivity)!!.email!!)

        if (Constants.isDebug) Log.d(TAG, "...DUNG...: ${(activity as BackgroundActivity)!!.email!!}")

//        if (Constants.isDebug) Log.d(TAG, "...DUNG...:${users.size} ,${(activity as BackgroundActivity).myUser}")
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rcvUser.layoutManager = linearLayoutManager
        userAdapter = UserAdapter(activity!!, users)

        rcvUser.itemAnimator = DefaultItemAnimator()
        rcvUser.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))


        rcvUser.adapter = userAdapter

        val itemTouchHelperCallback = RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rcvUser)

    }

//    override fun onRemoveItem(user: User, position: Int) {
////        userData.remove(user.userName!!)
//        userData.remove(users[position].userName!!)
////        users.removeAt(position)
//        userAdapter.removeItem(user, position)
//
//    }

    override fun onEditItem(user: User, position: Int) {
//        Toast.makeText(activity!!, "asadsa", Toast.LENGTH_LONG).show()
        val dialog = ChangePassword(activity!!)
        userTemp = user
        (activity as BackgroundActivity).user = user
        dialog.show()

    }

    inner class OnListenerBrocast : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent!!.action) {

                Key.ACTION_S -> {
                    val bundle = intent.extras
                    val name = bundle.getString(Key.NAME_S)
                    val phone = bundle.getString(Key.PHONE_S)
                    val pass = bundle.getString(Key.PASS_S)

                    userTemp.name = name
                    userTemp.password = pass
                    userTemp.phoneNumber = phone

                    userData.updateUser(userTemp)
                    rcvUser.adapter = userAdapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as BackgroundActivity).unregisterReceiver(brocastResover)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        if (viewHolder is UserAdapter.ViewHolder) {
            val name = users[viewHolder.getAdapterPosition()].name
            val deletedItem = users[viewHolder.getAdapterPosition()]
            val deletedIndex = viewHolder.getAdapterPosition()
            userData.remove(users[position].userName!!)
            userAdapter.removeItem(viewHolder.getAdapterPosition())
            val snackbar = Snackbar.make(coordinator_layout, "$name Đã xóa!", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }
}