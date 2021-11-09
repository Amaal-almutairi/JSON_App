package com.example.json_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    var info:CUR? = null
    lateinit var user:EditText
    lateinit var btnconvert:Button
    lateinit var date:TextView
    lateinit var tvcur:TextView
    lateinit var tvdisplay:TextView
    lateinit var spinner: Spinner
    var swich:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        user = findViewById(R.id.edvalue)
        btnconvert = findViewById(R.id.btnconvert)
        date = findViewById(R.id.tvdate)
        tvcur = findViewById(R.id.tvcur)
        tvdisplay = findViewById(R.id.tvdisplay)
        spinner = findViewById(R.id.spin)
     //   date.text="Date"
      //  tvcur.text="Enter EURO Value"
        val curList = arrayListOf("inr", "usd", "aud", "SAR", "cny", "jpy")
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, curList)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    p0: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    swich = position

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(
                        applicationContext,
                        "please select at least one currency",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

        }
        btnconvert.setOnClickListener {
            var sel = user.text.toString()
            if (sel.isNotEmpty()) {
                var currency: Double = sel.toDouble()
                // lambda method
                // first we go to calculate function and do some maths then display  value in show function
                getdata(onResult = {
                    info = it
                    when (swich) {
                        0 ->show(calculate(info?.eur?.inr?.toDouble(),currency))
                        1 ->show(calculate(info?.eur?.usd?.toDouble(),currency))
                        2 ->show(calculate(info?.eur?.aud?.toDouble(),currency))
                        3 ->show(calculate(info?.eur?.SAR?.toDouble(),currency))
                        4 ->show(calculate(info?.eur?.cny?.toDouble(),currency))
                        5 ->show(calculate(info?.eur?.jpy?.toDouble(),currency))
                    }

                })
            } else {
                Toast.makeText(
                    applicationContext,
                    "please select at least one currency",
                    Toast.LENGTH_SHORT
                ).show()
            }
            user.text.clear()
            user.clearFocus()
        }
    }
        fun show(calc:Double){
            tvdisplay.text = String.format("%3f",calc)
        }
       fun calculate(c:Double?,sel:Double):Double{
           var s = 0.0
           if (c !== null){
               s =(c * sel)

           }
           return s
       }
    // unit is temporary variable we use it to store onResult
        fun getdata(onResult:(CUR?) ->Unit){
            val api = APIClient().getClient()?.create(APIInterface::class.java)
             if (api !=null){
                 api.getdata()?.enqueue(object :Callback<CUR>{
                     override fun onResponse(call: Call<CUR>, response: Response<CUR>) {
                         onResult(response.body())
                     }

                     override fun onFailure(call: Call<CUR>, t: Throwable) {
                     onResult(null)
                         Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                     }

                 })
             }

        }

    }
