package com.example.webserviceandroid

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.util.jar.Manifest


class MainActivity : AppCompatActivity() {
    val URL_INSERT = "http://192.168.21.102/androidwebservice/insert.php"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.INTERNET)){
                Toast.makeText(this,"We need internet",Toast.LENGTH_SHORT).show()
            }
            else{
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.INTERNET),111);
            }
        }
        else{
            Toast.makeText(this,"Đã có Internet",Toast.LENGTH_LONG).show()
            InsertDB(URL_INSERT)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            111 -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    InsertDB(URL_INSERT)
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }
    fun Display(url: String){
        val request: RequestQueue  = Volley.newRequestQueue(this)
        var jsonread: JsonArrayRequest = JsonArrayRequest(Request.Method.GET,url,null,
            Response.Listener <JSONArray>{response ->
                Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
        },Response.ErrorListener {
            Toast.makeText(this, it.toString(),Toast.LENGTH_SHORT).show()
        });
        request.add(jsonread)
    }
    fun InsertDB(url: String){
        val param = HashMap<String, String>()
        param.put("hotenSV","Nguyen Van Banh")
        param.put("namsinhSV","1112")
        param.put("diachiSV","Thieu Thanh")
        val jsonobject = JSONObject(param);
        val resquest = Volley.newRequestQueue(this);
        var jsonObject1 = JsonObjectRequest(Request.Method.POST,url,jsonobject,
            Response.Listener<JSONObject> {response ->
                Toast.makeText(this, response.toString(),Toast.LENGTH_LONG).show()
        }, Response.ErrorListener {
                Toast.makeText(this, it.toString(),Toast.LENGTH_LONG).show()
        });
        resquest.add(jsonObject1)
    }
}
