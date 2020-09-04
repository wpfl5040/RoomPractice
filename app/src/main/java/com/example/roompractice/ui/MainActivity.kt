package com.wpfl5.roompractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roompractice.ui.adapter.UserAdapter
import com.example.roompractice.ui.viewmodel.MainViewModel
import com.example.roompractice.ui.viewmodel.ViewModelFactory
import com.wpfl5.roompractice.databinding.ActivityMainBinding
import com.wpfl5.roompractice.db.entity.User


class MainActivity : AppCompatActivity() {
    private val layoutRes = R.layout.activity_main
    private lateinit var binding: ActivityMainBinding
    private val mainVM: MainViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(application)).get(MainViewModel::class.java)
    }
    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        binding.apply {
            mainViewModel = mainVM
            recyclerUser.adapter = adapter
            btnAddUser.setOnClickListener {
                mainVM.insert(User("addId","addName"))
            }
        }

        userObserver()

    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
    }

    private fun userObserver(){
        mainVM.allUsers.observe(this, Observer {
            adapter.submitList(it)
        })
    }

}