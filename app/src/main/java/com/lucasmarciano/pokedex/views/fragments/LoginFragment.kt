package com.lucasmarciano.pokedex.views.fragments


import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucasmarciano.pokedex.R
import com.lucasmarciano.pokedex.model.User
import com.lucasmarciano.pokedex.network.RetrofitInitializer
import com.lucasmarciano.pokedex.utils.builderAlert
import com.lucasmarciano.pokedex.utils.isEmpty
import com.lucasmarciano.pokedex.views.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import okhttp3.ResponseBody
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : Fragment(), View.OnClickListener {

    var builder: AlertDialog? = null

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onStart() {
        super.onStart()
        buttonLogin.setOnClickListener(this)
        tvNewUser.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.buttonLogin -> authentication()
            R.id.tvNewUser -> replaceFragment()
        }
    }

    private fun authentication() {
        authenticationService()
    }

    private fun authenticationService() {
        if (tiUsername.editText!!.isEmpty("") || tiPassword.editText!!.isEmpty("")) {
            activity!!.toast(resources.getString(R.string.message_error_fiel_empty))
        } else {
            builder = activity!!.builderAlert(resources.getString(R.string.alert_waiting))
            builder!!.show()

            val user = User()
            user.username = tiUsername.editText!!.text.toString()
            user.password = tiPassword.editText!!.text.toString()

            val call = RetrofitInitializer.userService().authenticate(user)

            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response?.body() != null)
                        activity!!.startActivity<MainActivity>()
                    else
                        activity!!.toast(resources.getString(R.string.authentication_error))

                    builder?.dismiss()
                }

                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    Log.e("LoginFragment", t.toString())
                    builder?.dismiss()
                    activity!!.toast(resources.getString(R.string.message_error_connection))
                }
            })
        }
    }

    private fun replaceFragment() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.root_layout, CreateUserFragment.newInstance())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
