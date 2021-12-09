package br.com.lea.easyvendas.ui.utils.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.databinding.ItemCreditCardBinding
import br.com.lea.easyvendas.ui.fragment.home.BaseWidgetContainer

class CustomCreditCard(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs),BaseWidgetContainer {

    constructor(context: Context) : this(context, null)


    private val binding: ItemCreditCardBinding = ItemCreditCardBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {

        context.obtainStyledAttributes(attrs, R.styleable.CustomCreditCardLeao).apply {

                getString(R.styleable.CustomCreditCardLeao_textMetaValue)?.let { textMetaValor ->
                    setTextMetaValor(textMetaValor)
                }

                getString(R.styleable.CustomCreditCardLeao_textToday)?.let { textToday ->
                    setTextToday(textToday)
                }

                getString(R.styleable.CustomCreditCardLeao_textFirstDayMonth)?.let {textFirstDayMonth ->
                    setTextFirstDayMonth("")
                }

                getString(R.styleable.CustomCreditCardLeao_textLastDayMonth)?.let {textLastDayMonth ->
                    setTextLastDayMonth(textLastDayMonth)
                }

                getString(R.styleable.CustomCreditCardLeao_textPorcent)?.let {textPorcent ->
                    setTextPorcent(textPorcent)
                }

                getString(R.styleable.CustomCreditCardLeao_textValorAtual)?.let { textValorAtual ->
                    setTextValorAtual(textValorAtual)
                }

            }


            with(binding){
                  cardView.setOnClickListener {
                  cardOnClickListener.invoke()
            }
         }

        }

    fun setTextMetaValor(textMetaValor: String){
        this.binding.textMetaValor.text = textMetaValor

    }
    fun setTextToday(textToday: String){
        this.binding.textToday.text = textToday
    }

    fun setTextFirstDayMonth(textFirstDayMonth: String){
        this.binding.textFirstDayMonth.text = textFirstDayMonth
    }

    fun setTextLastDayMonth(textLastDayMonth: String){
        this.binding.textLastDayMonth.text = textLastDayMonth
    }

    fun setTextPorcent(textPorcent: String){
        this.binding.textPorcent.text = textPorcent
    }


    fun setTextValorAtual(textValorAtual: String){
        this.binding.textValorAtual.text = textValorAtual
    }

    override fun getWidgetTag(): String {
        return this.javaClass.name
    }

    override fun getWidgetView(): View {
        return this
    }

    companion object{
        private var cardOnClickListener: () -> Unit={}

        fun setOnClickListenerCard(callback: () -> Unit) {
            cardOnClickListener=callback

        }
}

}