package br.com.lea.easyvendas.ui.utils.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.databinding.CustomButtonRadiusWhiteBinding

class CustomButtonRadiusWhite(context: Context,attrs: AttributeSet) : LinearLayout(context, attrs){

    private val binding: CustomButtonRadiusWhiteBinding= CustomButtonRadiusWhiteBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        context.obtainStyledAttributes(attrs, R.styleable.CustomButtonRadius).apply{
            binding.btnCustomWhite.text =  getString(R.styleable.CustomButtonRadius_text)
        }

    }
}