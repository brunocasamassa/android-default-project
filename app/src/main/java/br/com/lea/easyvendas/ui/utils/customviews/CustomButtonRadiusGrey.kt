package br.com.lea.easyvendas.ui.utils.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.databinding.CustomButtonRadiusGreyBinding

class CustomButtonRadiusGrey(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs){

    private val binding: CustomButtonRadiusGreyBinding= CustomButtonRadiusGreyBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        context.obtainStyledAttributes(attrs, R.styleable.CustomButtonRadius).apply{
            binding.customButtonGrey.text =  getString(R.styleable.CustomButtonRadius_text)
        }

    }
}