package com.example.signup

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val url="https://team3-android.uc.r.appspot.com/regionjson"
    //private var requestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //requestQueue = Volley.newRequestQueue(this)
        //val jsonobj = JSONObject()
        btn_displayregion.setOnClickListener {
            jsonParse()
        }
    }

    private fun jsonParse() {
        //val url = "https://team3-android.uc.r.appspot.com/regionjson"
        //val jsonobj = JSONObject()
        //jsonobj.put("Region","South")
        val que = Volley.newRequestQueue(this@MainActivity)
        val request = JsonObjectRequest(Request.Method.POST, url, null,
            Response.Listener { response ->
                println("Got Info")
                val jsonArray = response.getJSONArray("RegionPosts")
                for (i in 0 until jsonArray.length()) {
                    val regionpost = jsonArray.getJSONObject(i)
                    val description = regionpost.getString("desc")
                    val title = regionpost.getString("title")
                    val picCode = regionpost.getString("piccode")

                    val imageBytes = Base64.decode(picCode, Base64.DEFAULT)
                    val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                    imageView.setImageBitmap(decodedImage)
                    textView.append("$title, $description\n\n")
                    //textView.append("$picCode\n\n")
                }

            }, Response.ErrorListener {
                Toast.makeText(applicationContext, "Couldn't Load Data", Toast.LENGTH_SHORT).show()

            })
        que.add(request)
    }

    /*private fun jsonParse() {
        //jsonobj.put("Password",password.text)
        //val url = "https://team3-android.uc.r.appspot.com/regionjson"
        val jsonobj = JSONObject()
        val que = Volley.newRequestQueue(this@MainActivity)
        val req = JsonObjectRequest(Request.Method.POST,url, jsonobj,
            Response.Listener {
                    response ->
                //println("Signed In")
                textView.text = (response.toString())
                //Toast.makeText(applicationContext, "Successfully Logged In", Toast.LENGTH_SHORT).show()
            }, Response.ErrorListener {
                //Toast("Fail")
                //println("Failed to sign in")
                println(it)
                //textView.text = "Fail"
                Toast.makeText(applicationContext, "Invalid Login", Toast.LENGTH_SHORT).show()

            })
            que.add(req)
    }*/

}
