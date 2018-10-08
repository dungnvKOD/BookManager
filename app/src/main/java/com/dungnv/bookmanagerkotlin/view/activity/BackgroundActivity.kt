package com.dungnv.myapp.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.model.Entity.BookType
import com.dungnv.myapp.model.Entity.User
import com.dungnv.myapp.view.BaseNewFragment
import com.dungnv.myapp.view.fragment.WaitFragment

class BackgroundActivity : AppCompatActivity() {
    //    var myUser: User? = null
    var user: User? = null
    var bookType: BookType? = null
    var check=false

    var email: String? = null
    var pass: String? = null
    var phone: String? = null
    var name: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fameLayout, WaitFragment.newFragment)
                .commit()
    }

    inner class BaseFragment : BaseNewFragment(this.supportFragmentManager, R.id.fameLayout)

}
