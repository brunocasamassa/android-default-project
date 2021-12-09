package br.com.lea.easyvendas.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.TextView
import androidx.navigation.findNavController
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        binding.navHostFragment.findNavController()?.let { it.popBackStack() }

    }


}
