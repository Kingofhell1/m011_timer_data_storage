package com.example.m011_timer_data_storage

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.m011_timer_data_storage.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val repository = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateUI()
        binding.buttonSave.setOnClickListener {
            val inputText = binding.editText.text.toString().trim()
            if(inputText == "") {
                Toast.makeText(this, "Нелья сохранить пустой файл", Toast.LENGTH_LONG).show()
            }
            else{
                repository.saveText(inputText, this)
                binding.editText.let { it.text?.clear() }
                updateUI()
            }
        }
        binding.buttonClear.setOnClickListener{
            if(binding.textView.text.toString().trim() == "") {
                Toast.makeText(this, "Нету файлов для удаления", Toast.LENGTH_LONG).show()
            }
            else{
                repository.clearText(this)
                updateUI()
            }

        }
    }

    private fun updateUI(){
        binding.textView.text = repository.getText(this)
    }
}