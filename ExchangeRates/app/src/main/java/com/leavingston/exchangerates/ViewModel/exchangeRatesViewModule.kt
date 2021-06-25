package com.leavingston.exchangerates.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leavingston.exchangerates.loadingState.LoadingState
import com.leavingston.exchangerates.models.Example
import com.leavingston.exchangerates.repository.ExchangeRatesRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// ვიუ მოდელი არის ერთ ერთი ძირითადი კომპონენტი MVVM არქიტექტურაში .
// ViewModel - არის დამაკავშირებელი view სა და data - ს შორის , როგორც ორი კუნძულს შორის ხიდი .
// აქ ხდება ძირითადი მონაცემების გადამუშავება და შემდეგ რესფონსის სახით მისდის view-ს(fragment,activity e.t.c.) - გადამუშავებული დათა --
// მიღების შემდეგ , ჩვენი ვიუს კომპონენტში ვანიჭებთ მოცემულ ინფრომაციას , ლეიაუთზე აღნიშნულ ობიექტებს
class exchangeRatesViewModule(private val repo : ExchangeRatesRepo ) : ViewModel(), Callback<Example> {

    // ეს არის enum კლასის ობიექტი სადაც ვინახავთ ჩვენს მიერ მიღებული ქოლის რესფონსს
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState
    // აქ ვინახავთ ქოლიდან დაბრუნებულ რესფონსს , თვითონ ეს კი არის ინკაფსულაციის მაგალითი
    private val _data = MutableLiveData<Example>()
    val data : LiveData<Example>
        get() = _data

    //ინიტ ბლოკი , ახდენს ინიციალიზაციას შიგ მოცემული მეთოდისა თუ პარამეტრისა
    init {
        fetchData()
    }

    fun fetchData() {
        _loadingState.postValue(LoadingState.LOADED)
        repo.getRates().enqueue(this)

    }

    //ეს არის ჩვენი ქოლბექის რესფონსი , თუ შედგა სერვერთან კავშირი და დაგვიბრუნდა პასუხი
    override fun onResponse(call: Call<Example>, response: Response<Example>) {
        // აქ მოწმდება თუ დავუკავშირდით სერვერს და ყველაფერმა კარგად ჩაიარა
        if (response.isSuccessful){
            Log.e("wywrywwywy" , "$response")

            _data.postValue(response.body())
            _loadingState.postValue(LoadingState.LOADED)
            //აქ ყველა დანარჩენ შემთხვევას ვაფიქსირებთ , სერვერის გასხმას , ნელ კავშირს და სხვა მრავალ ყლეობას
        }else {
            Log.e("Tag" , "$response")

            _loadingState.postValue(LoadingState.error(response.toString()))
        }
    }
    // თუ სერვერს ვერ დავუკავშირდით მოდის ამ მეთოდის პასუხი
    override fun onFailure(call: Call<Example>, t: Throwable) {

        _loadingState.postValue(LoadingState.error(t.message))
        Log.e("Tag" , "$t")
    }




}