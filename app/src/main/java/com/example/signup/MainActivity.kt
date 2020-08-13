package com.example.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val url="https://team3-android.uc.r.appspot.com/loginjson"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonobj = JSONObject()
        btn_login.setOnClickListener {
            println("start of click")
            jsonobj.put("Username",username.text)
            jsonobj.put("Password",password.text)

            val que = Volley.newRequestQueue(this@MainActivity)
            val req = JsonObjectRequest(Request.Method.POST,url, jsonobj,
                Response.Listener {
                    response ->
                    //println("Signed In")
                    //textView5.text = (response.toString())
                    Toast.makeText(applicationContext, "Successfully Logged In", Toast.LENGTH_SHORT).show()
                }, Response.ErrorListener {
                    //Toast("Fail")
                    //println("Failed to sign in")
                    //println(it)
                    //textView3.text = "Fail"
                    Toast.makeText(applicationContext, "Invalid Login", Toast.LENGTH_SHORT).show()

                })
                que.add(req)
        }

}
}