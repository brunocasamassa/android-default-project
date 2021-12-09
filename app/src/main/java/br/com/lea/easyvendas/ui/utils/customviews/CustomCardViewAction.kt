package br.com.lea.easyvendas.ui.utils.customviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import br.com.lea.easyvendas.BuildConfig
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.databinding.ItemPromoActionBinding

class CustomCardViewAction(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val binding: ItemPromoActionBinding=ItemPromoActionBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        val atributos=context.obtainStyledAttributes(attrs, R.styleable.CustomCardViewAction)

        with(binding){
            textDesPromo.text=atributos.getString(R.styleable.CustomCardViewAction_itemPromo)
            textDate.text=atributos.getString(R.styleable.CustomCardViewAction_date)
            textDescription.text=atributos.getString(R.styleable.CustomCardViewAction_description)
            textPorcentagem.text=atributos.getString(R.styleable.CustomCardViewAction_porcentagen)

            itemCardProduct.setOnClickListener {
                cardOnClickListener.invoke()
            }
        }

    }

    companion object {

        private var cardOnClickListener: () -> Unit={}
        fun setOnClickListenerCard(callback: () -> Unit) {
            cardOnClickListener=callback
        }

    }


}