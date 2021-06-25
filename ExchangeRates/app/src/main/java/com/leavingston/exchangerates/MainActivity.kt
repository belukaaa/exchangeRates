package com.leavingston.exchangerates

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.leavingston.exchangerates.ViewModel.DBViewModel
import com.leavingston.exchangerates.ViewModel.EurRatesViewModel
import com.leavingston.exchangerates.ViewModel.Factory
import com.leavingston.exchangerates.ViewModel.exchangeRatesViewModule
import com.leavingston.exchangerates.databinding.ActivityMainBinding
import com.leavingston.exchangerates.models.Example
import com.leavingston.exchangerates.models.ratesModel
import com.leavingston.exchangerates.repository.roomRepository
import com.leavingston.exchangerates.room.DAO
import com.leavingston.exchangerates.room.DataBase
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.format.DateTimeFormatter
import java.util.*

// მეინ ექთივითი არის ლანჩერ ექთივითი საიდანაც ისტარტება ჩვენი აპლიკაცია , გაწერილია მანიფესტ ფაილში
// რა თქმაუნდა შეგვიძლია გამშვები ექთივითის შეცვლა
// ამ ექთივითზე მიბმული ლეიაუთის მოქმედებები ხდება ამ ექთივითში
class MainActivity : AppCompatActivity() {

    var GEL : Double = 0.0
    var EUR : Double = 0.0
    var date = ""


    private lateinit var factory: Factory
    private lateinit var DBViewModel : DBViewModel
    private lateinit var DAO : DAO
    private lateinit var DB : DataBase
    private lateinit var repository: roomRepository

    //ვიუმოდელების ინიციალიზაცია , დი - ს შემდეგ
    private val exchangeViewModel by viewModel<exchangeRatesViewModule>()
    private val eurRatesViewModel by viewModel<EurRatesViewModel>()

    // ვიუ ბაინდის ობიექტი რომლის ინიციალიზაცია საჭიროა ონქრიეითში
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ექშენ და სტატუს ბარის დასამალი + style.xml იდან უნდა შეცვლა
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        // აქ ხდება ვიუ ბანდინგის ინიციალიზაცია და ესეტება ჩვენს ექთივითის
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        initViewModel(this)

        // აქ ხდება ვიუმოდელიდან წამოღება იმ ინფორმაციის რომელზეც ვაკეთებთ ქოლებს
        downloadData()

        //ღილაკზე დაწკაპუნების შემთხვევაში მეორდება ქოლის მეთოდი
        binding.showRatesButton.setOnClickListener {

            if (isNetworkAvailable()){
                downloadData()
            }
            else{
                downloadSavedData()
            }

            val currentDateTime = Calendar.getInstance().time

            binding.timeWhenUpdated.text = currentDateTime.toString()
        }



    }

    private fun downloadSavedData() {
        DBViewModel.getRates().observe(this , Observer {
            setDefaultsFromDb(it)
        })
    }

    fun setDefaultsFromDb(ratesModel: ratesModel?){
        val EUR1 = ratesModel?.EUR
        val USD1 = ratesModel?.GEL
        binding.USD.text = "$USD1 ლარს"
        binding.EUR.text = "$EUR1 ლარს"
        binding.timeWhenUpdated.text = ratesModel?.UPDATED_TIME
    }

    private fun initViewModel(activity: MainActivity) {
        DB = DataBase.getDatabase(activity)
        DAO = DB.dao()
        repository = roomRepository(DAO)
        factory = Factory(repository)
        DBViewModel = ViewModelProviders.of(this, factory).get(com.leavingston.exchangerates.ViewModel.DBViewModel::class.java)

    }

    //აქ ხდება ინფორმაციის წამოღება ვიუმოდელიდან , ვიუმოდელი თავის წილად უკავშირდება რეპოზიტორის , ის ინტერფეისით აზავნის ქოლს სერვერზე და მოგვდის მონაცემები
    private fun downloadData(){
        exchangeViewModel.data.observe(this , Observer {
                succesUSD(it)

            })

            exchangeViewModel.loadingState.observe(this, androidx.lifecycle.Observer {
                if (it.status.name == "FAILED") {
                    if (isNetworkAvailable()) {
                        downloadSavedData()
                    } else {
                        downloadSavedData()
                    }
                }
            })

            eurRatesViewModel.data.observe(this , Observer{
                succesEUR(it)
            })
        eurRatesViewModel.loadingState.observe(this , Observer{
            if (it.status.name == "FAILED"){
                downloadSavedData()
            }
        })


    }
    private fun succesUSD(result: Example) {
        val USD = result?.conversionRates?.GEL.toString()
        GEL = result.conversionRates.GEL
        binding.USD.text = "$USD ლარს"


    }
    private fun succesEUR(it: Example?) {
        val currentDateTime = Calendar.getInstance().time
        val EUR = it?.conversionRates?.GEL.toString()
        binding.EUR.text = "$EUR ლარს"
        binding.timeWhenUpdated.text = currentDateTime.toString()

        this.EUR = it?.conversionRates?.GEL!!
        date = currentDateTime.toString()
        DBViewModel.deleteRates()
        DBViewModel.saveRates(ratesModel(0,GEL , this.EUR , date))


    }



    // აქ მოწმდება ინტერნეტი არის ჩართული თუ არა
    fun isNetworkAvailable(): Boolean {
        val conManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo = conManager.activeNetworkInfo
        return internetInfo != null && internetInfo.isConnected
    }


}