package com.example.retrofit_kotlin

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.retrofit_kotlin.databinding.ActivityMainBinding
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private var progressDialog: ProgressDialog? = null

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)


        binding.buttonGet.setOnClickListener{getUserByID()}
        binding.buttonPut.setOnClickListener{updateUserByID()}
        binding.buttonDelete.setOnClickListener{deleteUserByID()}
        binding.buttonPost.setOnClickListener{addUser()}
    }

    private fun addUser() {
        lifecycleScope.launch {
            showLoading("Creating,Please wait....")
            val body = JsonObject().apply {
                addProperty("name", "hung2")
                addProperty("job", "Android2")
            }
            val result = apiService.create(body)
            if (result.isSuccessful()) {
                Log.e("oooooo", "create: ${result.body()}")
            } else {
                Log.e("oooooo", "create fail: ${result.body()}")
            }
            progressDialog?.dismiss()
        }
    }

    private fun deleteUserByID() {
        lifecycleScope.launch{
            showLoading("Deleting,Please wait....")
            val result  = apiService.deleteUser("2")
            if(result.isSuccessful()){
                Log.e("oooooo","delete: ${result.body()}")
            }else{
                Log.e("oooooo","delete fail: ${result.body()}")
            }
            progressDialog?.dismiss()
        }
    }

    private fun updateUserByID() {
       lifecycleScope.launch{
           showLoading("Updating,Please wait....")
           val body = JsonObject().apply {
               addProperty("name","hung")
               addProperty("job","Android")
           }
           val result = apiService.updateUser("2",body)
           if(result.isSuccessful()){
               Log.e("oooooo","update: ${result.body()}")
           }else{
               Log.e("oooooo","update fail: ${result.body()}")
           }
           progressDialog?.dismiss()
       }
    }

    private fun getUserByID() {
        lifecycleScope.launch{
            showLoading("Getting,Please wait....")
            val result = apiService.getUserById("2")
            if(result.isSuccessful()){
                Log.e("oooooo","getUserById: ${result.body()?.data}")
            }else{
                Log.e("oooooo","getUserById fail: ${result.body()?.data}")
            }
            progressDialog?.dismiss()
        }
    }

    private fun showLoading(msg: String){
        progressDialog = ProgressDialog.show(this,null,msg,true)

    }
}