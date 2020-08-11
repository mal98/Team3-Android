package com.example.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val url="http://0.0.0.0:5000/testingjson"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonobj = JSONObject()
        btn_login.setOnClickListener {

            jsonobj.put("username",username.text)
            jsonobj.put("password",password.text)

            val que = Volley.newRequestQueue(this@MainActivity)
            val req = JsonObjectRequest(Request.Method.POST,url,null,
                Response.Listener {
                    response ->
                    Toast(response.['success'].toString())
                }, Response.ErrorListener {
                    Toast("Something went wrong")

                })
                que.add(req)
        }

    }
}