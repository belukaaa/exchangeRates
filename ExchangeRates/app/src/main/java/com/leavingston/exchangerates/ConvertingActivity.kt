package com.leavingston.exchangerates

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.leavingston.exchangerates.ViewModel.DBViewModel
import com.leavingston.exchangerates.ViewModel.Factory
import com.leavingston.exchangerates.databinding.ConvertingLayoutBinding
import com.leavingston.exchangerates.models.ratesModel
import com.leavingston.exchangerates.repository.roomRepository
import com.leavingston.exchangerates.room.DAO
import com.leavingston.exchangerates.room.DataBase

class ConvertingActivity : AppCompatActivity() {

    private lateinit var factory: Factory
    private lateinit var DBViewModel : DBViewModel
    private lateinit var DAO : DAO
    private lateinit var DB : DataBase
    private lateinit var repository: roomRepository

    val KEY1 = "MODEL_CLASS"

    companion object{
        fun buildIntent(context: Context?,model: ratesModel): Intent {
            val intent = Intent(context,ConvertingActivity::class.java)
            val KEY1 = "MODEL_CLASS"
            intent.putExtra(KEY1 , model)
            return intent
        }
    }

    private lateinit var binding: ConvertingLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ConvertingLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val ratesModelValues = intent.getParcelableExtra<ratesModel>(KEY1)

        initViewModel(this)
        okey(ratesModelValues)
    }

    private fun initViewModel(convertingActivity: ConvertingActivity) {
        DB = DataBase.getDatabase(convertingActivity)
        DAO = DB.dao()
        repository = roomRepository(DAO)
        factory = Factory(repository)
        DBViewModel = ViewModelProviders.of(this, factory).get(com.leavingston.exchangerates.ViewModel.DBViewModel::class.java)

    }

    fun okey(ratesModel: ratesModel?){



        binding.USDtoConvert.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })


    }



}