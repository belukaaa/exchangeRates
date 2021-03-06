package com.leavingston.exchangerates

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

// მეინ ექთივითი არის ლანჩერ ექთივითი საიდანაც ისტარტება ჩვენი აპლიკაცია , გაწერილია მანიფესტ ფაილში
// რა თქმაუნდა შეგვიძლია გამშვები ექთივითის შეცვლა
// ამ ექთივითზე მიბმული ლეიაუთის მოქმედებები ხდება ამ ექთივითში
class MainActivity : AppCompatActivity() {
    val TAG = "fdfafaf"
    private var GEL : Double = 0.0
    private var EUR : Double = 0.0
    private var data = ""

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


        checkNetworkStatus()

        initViewModel(this)
        // აქ ხდება ვიუმოდელიდან წამოღება იმ ინფორმაციის რომელზეც ვაკეთებთ ქოლებს


        chechkWhatToDo()

        //ღილაკზე დაწკაპუნების შემთხვევაში მეორდება ქოლის მეთოდი
        binding.showRatesButton.setOnClickListener {
            chechkWhatToDo()
            }


        binding.saveRatesButton.setOnClickListener {
            if(isNetworkAvailable()) {
                saveToDB(ratesModel(0, GEL, EUR, data))
            }
        }
        binding.button.setOnClickListener {
            if(GEL != 0.0 && EUR != 0.0){
                startActivity(ConvertingActivity.buildIntent(this , ratesModel(0,GEL,EUR,data)))
            }
        }
    }

    private fun downloadSavedData() {


            DBViewModel.getRates().observe(this, Observer {
                setDefaultsFromDb(it)
            })
        
    }

    private fun chechkWhatToDo(){
        if (isNetworkAvailable()){
            downloadData()
        }else{
            downloadSavedData()
        }
    }
    fun setInvisible(view : View){
        view.visibility = View.INVISIBLE
    }
    fun setVisible(view: View){
        view.visibility = View.VISIBLE
    }

    fun setDefaultsFromDb(ratesModel: ratesModel?) {

        if (ratesModel?.GEL == null) {
//            setInvisible(binding.button)
            setInvisible(binding.saveRatesButton)
            setInvisible(binding.showRatesButton)
            setInvisible(binding.valuteCurseHeader)
            binding.USD.text = "დაუკავშირდი ინტერნეტს"
            setInvisible(binding.textView7)
            setInvisible(binding.textView6)
            setInvisible(binding.EUR)
            setInvisible(binding.timeWhenUpdated)

        } else {
//            setVisible(binding.button)
            setVisible(binding.showRatesButton)
            setVisible(binding.valuteCurseHeader)
            setVisible(binding.textView6)
            setVisible(binding.textView7)
            setVisible(binding.saveRatesButton)
            setVisible(binding.timeWhenUpdated)
            val EUR1 = ratesModel?.EUR
            val USD1 = ratesModel?.GEL
            binding.USD.text = "$USD1 ლარს"
            binding.EUR.text = "$EUR1 ლარს"
            binding.timeWhenUpdated.text = ratesModel?.UPDATED_TIME
        }
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
        binding.valuteCurseHeader.visibility = View.VISIBLE
        binding.textView6.visibility = View.VISIBLE
        binding.textView7.visibility = View.VISIBLE
        binding.EUR.visibility = View.VISIBLE
        binding.timeWhenUpdated.visibility = View.VISIBLE


        exchangeViewModel.data.observe(this , Observer {
                succesUSD(it)
            })
        eurRatesViewModel.data.observe(this , Observer{
            succesEUR(it)
        })



            exchangeViewModel.loadingState.observe(this, androidx.lifecycle.Observer {
                if (it.status.name == "FAILED") {
                        downloadSavedData()
                }
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
        val currentDateTime = Calendar.getInstance().time
        data = currentDateTime.toString()

        saveToDB(ratesModel(0,result.conversionRates.GEL,this.EUR,data))

    }
    private fun succesEUR(it: Example?) {
        val currentDateTime = Calendar.getInstance().time
        val EUR = it?.conversionRates?.GEL.toString()
        binding.EUR.text = "$EUR ლარს"
        binding.timeWhenUpdated.text = currentDateTime.toString()

        this.EUR = it?.conversionRates?.GEL!!
        data = currentDateTime.toString()

    }

    private fun saveToDB(ratesModel: ratesModel) {
        DBViewModel.deleteRates()
        DBViewModel.saveRates(ratesModel)
    }


    // აქ მოწმდება ინტერნეტი არის ჩართული თუ არა
    fun isNetworkAvailable(): Boolean {
        val conManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo = conManager.activeNetworkInfo
        return internetInfo != null && internetInfo.isConnected
    }
    private fun checkNetworkStatus(){
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network : Network) {
                    Log.e(TAG, "The default network is now: " + network)
                    CoroutineScope(Dispatchers.Main).launch {
                        downloadData()
                        internetIsOn()
                    }

                }

                override fun onLost(network : Network) {
                    Log.e(TAG, "The application no longer has a default network. The last default network was " + network)

                    CoroutineScope(Dispatchers.Main).launch {

                        internetIsOff()

                    }

                }

                override fun onCapabilitiesChanged(network : Network, networkCapabilities : NetworkCapabilities) {
                    Log.e(TAG, "The default network changed capabilities: " + networkCapabilities)
                }

                override fun onLinkPropertiesChanged(network : Network, linkProperties : LinkProperties) {
                    Log.e(TAG, "The default network changed link properties: " + linkProperties)
                }
            })
        }
    }

    suspend fun internetIsOn(){
        setVisible(binding.showRatesButton)
        setVisible(binding.valuteCurseHeader)
        setVisible(binding.textView6)
        setVisible(binding.textView7)
        setVisible(binding.saveRatesButton)
        setVisible(binding.timeWhenUpdated)
    }

   suspend fun internetIsOff(){
        setInvisible(binding.saveRatesButton)
        setInvisible(binding.showRatesButton)
        setInvisible(binding.valuteCurseHeader)
        binding.USD.text = "დაუკავშირდი ინტერნეტს"
        setInvisible(binding.textView7)
        setInvisible(binding.textView6)
        setInvisible(binding.EUR)
        setInvisible(binding.timeWhenUpdated)
    }


}