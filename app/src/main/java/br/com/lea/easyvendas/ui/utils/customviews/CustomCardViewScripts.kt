package br.com.lea.easyvendas.ui.utils.customviews

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import br.com.lea.easyvendas.BuildConfig
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.databinding.ItemScriptsDayBinding

class CustomCardViewScripts (context: Context, attrs: AttributeSet) : LinearLayout(context, attrs){

    private val binding : ItemScriptsDayBinding= ItemScriptsDayBinding.inflate (
        LayoutInflater.from(context), this,true
    )

    init {

        val atributos = context.obtainStyledAttributes(attrs, R.styleable.CustomCardViewScripts)

        with(binding){
           textAddress.text = atributos.getString(R.styleable.CustomCardViewScripts_address)
           textTime.text = atributos.getString(R.styleable.CustomCardViewScripts_time)
           textRota.text = atributos.getString(R.styleable.CustomCardViewScripts_rota)

            viewCard.setOnClickListener {
                cardOnClickListener.invoke()
            }
            textRota.setOnClickListener {
                btnRotaOnClickListener.invoke()
            }
        }

    }

    companion object {
        private var cardOnClickListener: () -> Unit={}
        fun setOnClickListenerCard(callback: () -> Unit) {
            cardOnClickListener=callback
        }

        private var btnRotaOnClickListener: () -> Unit={}
        fun setOnClickListenerBtnRota(callback: () -> Unit) {
            btnRotaOnClickListener=callback
        }
    }

}