package com.example.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_regions.*
import org.json.JSONObject

class Regions : AppCompatActivity() {
    val url="https://team3-android.uc.r.appspot.com/regionjson"
    //private var requestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regions)
        btn_displayregion.setOnClickListener {
            jsonParse()
        }
    }

    private fun jsonParse() {
        //val url = "https://team3-android.uc.r.appspot.com/regionjson"
        val jsonobj = JSONObject()
        jsonobj.put("Region","South")
        val que = Volley.newRequestQueue(this@Regions)
        val request = JsonObjectRequest(Request.Method.POST, url, jsonobj,
            Response.Listener { response ->
                //println("Signed In")
                val jsonArray = response.getJSONArray("RegionPosts")
                for (i in 0 until jsonArray.length()) {
                    val regionpost = jsonArray.getJSONObject(i)
                    val description = regionpost.getString("desc")
                    val title = regionpost.getString("title")
                    textView.append("$title, $description\n\n")
                }

            }, Response.ErrorListener {
                //Toast("Fail")
                //println("Failed to get region post data")
                //println(it)
                //textView3.text = "Fail"
                Toast.makeText(applicationContext, "Couldn't Load Data", Toast.LENGTH_SHORT).show()

            })
        que.add(request)
    }

}