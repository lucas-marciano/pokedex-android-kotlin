package com.inatel.pokedex.views

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.inatel.pokedex.R
import com.inatel.pokedex.model.User
import com.inatel.pokedex.retrofit.RetrofitInitializer
import com.inatel.pokedex.utils.Utils
import kotlinx.android.synthetic.main.activity_create_user.*
import okhttp3.ResponseBody
import org.jetbrains.anko.design.snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class CreateUserActivity : AppCompatActivity() {
    var builder: AlertDialog? = null
    val context: Activity = this

    private fun TAG(): String = "CreateUserActivity"

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        setSupportActionBar(toolbarCreateUser)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun createUser(view: View) {
        if (checkTheFields()) createUserService(view) else snackbar(view, resources.getString(R.string.field_error))
    }

    private fun checkTheFields(): Boolean = tiFirstName.editText.toString().isEmpty()
            && tiLastName.editText.toString().isEmpty()
            && tiUserName.editText.toString().isEmpty()
            && tiEmail.editText.toString().isEmpty()
            && tiPassword.editText.toString().isEmpty()

    private fun createUserService(view: View) {
        builder = Utils.builAlertDialogNoAction(this, resources.getString(R.string.alert_waiting), false)

        val user = User("$tiFirstName.editText.toString() $tiLastName.editText.toString()",
                tiFirstName.editText.toString(),
                tiLastName.editText.toString(),
                tiUserName.editText.toString(),
                tiEmail.editText.toString(),
                tiPassword.editText.toString())

        val call = RetrofitInitializer.userService().new(user)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                response?.body()?.let {
                    context.finish()
                    snackbar(view, resources.getString(R.string.message_success_new_user))
                }
                builder?.dismiss()
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.e(TAG(), t.toString())
                builder?.dismiss()
                snackbar(view, resources.getString(R.string.new_user_error))
            }
        })
    }
}
