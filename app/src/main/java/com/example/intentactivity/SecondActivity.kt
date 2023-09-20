package com.example.intentactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.intentactivity.MainActivity.Companion.EXTRA_NAME
import com.example.intentactivity.ThirdActivity.Companion.EXTRA_ADDRESS
import com.example.intentactivity.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
            if (result.resultCode == Activity.RESULT_OK){
                val data = result.data

                val name  = data?.getStringExtra(EXTRA_NAME)
                val address = data?.getStringExtra(EXTRA_ADDRESS)

                binding.tvShowName.text = "Hai $name di $address"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_NAME)

        with(binding){

            tvShowName.text = "Hai $name !"
            btnToThird.setOnClickListener{
                val intent =
                    Intent(this@SecondActivity, ThirdActivity::class.java)
                        .apply { putExtra(EXTRA_NAME, name) }
                launcher.launch(intent)
            }
        }
    }
}