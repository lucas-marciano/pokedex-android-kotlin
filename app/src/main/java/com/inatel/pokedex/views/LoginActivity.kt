package com.inatel.pokedex.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.util.Log
import android.view.View
import com.inatel.pokedex.R
import com.inatel.pokedex.retrofit.RetrofitInitializer
import com.inatel.pokedex.utils.Utils
import org.jetbrains.anko.startActivity
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    var builder: AlertDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun authentication(view: View) {
        initDialog()
        callRetrofit()
    }

    private fun initDialog(){
        builder = Utils.builAlertDialogNoAction(this, resources.getString(R.string.alert_waiting), false)
    }

    private fun closeDialog(){
        builder?.dismiss()
    }

    private fun callRetrofit() {
        val call = RetrofitInitializer.userService()
                .authenticate(
                        tiUsername.editText.toString(),
                        tiPassword.editText.toString()
                )

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                response?.body()?.let {
                    startActivity<PokemonsListActivity>()
                }
                closeDialog()
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.e("LoginActivity", t.toString())
                closeDialog()
                longToast(resources.getString(R.string.authentication_error))

            }
        })
    }
}
