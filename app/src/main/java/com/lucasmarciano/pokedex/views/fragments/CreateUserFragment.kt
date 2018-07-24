package com.lucasmarciano.pokedex.views.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucasmarciano.pokedex.R
import com.lucasmarciano.pokedex.model.User
import com.lucasmarciano.pokedex.network.RetrofitInitializer
import com.lucasmarciano.pokedex.utils.builderAlert
import com.lucasmarciano.pokedex.utils.isEmpty
import kotlinx.android.synthetic.main.fragment_create_user.*
import okhttp3.ResponseBody
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 *
 */
class CreateUserFragment : Fragment() {
    var builder: AlertDialog? = null

    companion object {
        fun newInstance(): CreateUserFragment {
            return CreateUserFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).setSupportActionBar(toolbarCreateUser)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        btCreateUser.setOnClickListener { createUser() }
    }

    private fun createUser() {
        if (checkTheFields()) createUserService() else activity!!.toast(resources.getString(R.string.field_error))
    }

    private fun checkTheFields(): Boolean =
            tiFirstName.editText!!.isEmpty("")
                    && tiLastName.editText!!.isEmpty("")
                    && tiUserName.editText!!.isEmpty("")
                    && tiEmail.editText!!.isEmpty("")
                    && tiPassword.editText!!.isEmpty("")

    private fun createUserService() {
        builder = activity!!.builderAlert(resources.getString(R.string.alert_waiting))

        val user = User(
                tiUserName.editText!!.text.toString(),
                tiPassword.editText!!.text.toString(),
                "$tiFirstName.editText!!.text.toString() $tiLastName.editText!!.text.toString()",
                tiFirstName.editText!!.text.toString(),
                tiLastName.editText!!.text.toString(),
                tiEmail.editText!!.text.toString(), "USER", "", "")

        val call = RetrofitInitializer.userService().new(user)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                response?.body()?.let {
                    activity!!.toast(resources.getString(R.string.message_success_new_user))
                    activity!!.finish()
                }
                builder?.dismiss()
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.e("CreateUserFragment", t.toString())
                activity!!.toast(resources.getString(R.string.new_user_error))
                builder?.dismiss()
            }
        })
    }

}
