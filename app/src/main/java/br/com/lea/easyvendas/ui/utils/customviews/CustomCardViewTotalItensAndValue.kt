package br.com.lea.easyvendas.ui.utils.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.databinding.CustomCardTotalValueBinding
import com.google.android.material.card.MaterialCardView

class CustomCardViewTotalItensAndValue(context: Context, attrs: AttributeSet) :
    MaterialCardView(context, attrs) {

    private val binding: CustomCardTotalValueBinding = CustomCardTotalValueBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {


        this.setOnClickListener { Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show() }
    }

}