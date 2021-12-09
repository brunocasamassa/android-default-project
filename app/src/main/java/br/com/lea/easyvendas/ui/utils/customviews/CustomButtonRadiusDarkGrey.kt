package br.com.lea.easyvendas.ui.utils.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.databinding.CustomButtonRadiusDarkgreyBinding

class CustomButtonRadiusDarkGrey(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs){

    private val binding: CustomButtonRadiusDarkgreyBinding= CustomButtonRadiusDarkgreyBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
//        context.obtainStyledAttributes(attrs, R.styleable.CustomButtonRadius).apply{
//            binding.customButtonDarkgrey.text =  getString(R.styleable.CustomButtonRadius_text)
//        }
    }
}