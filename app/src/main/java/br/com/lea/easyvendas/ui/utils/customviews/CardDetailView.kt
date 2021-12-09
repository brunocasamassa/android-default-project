package br.com.lea.easyvendas.ui.utils.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.databinding.CustomCardsDetailBinding
import br.com.lea.easyvendas.domain.model.orders.CardDetail
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

class CardDetailView(context: Context, attributeSet: AttributeSet?) :
    MaterialCardView(context, attributeSet) {

    constructor(context: Context) : this(context, null)


    private val binding: CustomCardsDetailBinding = CustomCardsDetailBinding.inflate(
        LayoutInflater.from(context), this, true
    )


    init {
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.CustomCardViewDatail)

        with(binding){
            textDescription.text = attrs.getText(R.styleable.CustomCardViewDatail_textTitleCard)
            textDate.text = attrs.getString(R.styleable.CustomCardViewDatail_textDateCard)

        }
    }

    fun renderCard(cardDetail: CardDetail){

        val tvImage = this.findViewById<AppCompatImageView>(R.id.imageLogoComponent)
        val tvT = this.findViewById<AppCompatTextView>(R.id.textTitleStore)
        val tvD = this.findViewById<AppCompatTextView>(R.id.textDescription)
        val tvDate = this.findViewById<AppCompatTextView>(R.id.textDate)
        tvT.text = cardDetail.nameStore
        tvD.text = cardDetail.datail
        tvDate.text = cardDetail.date

        Glide.with(this)
            .load(cardDetail.miniImage)
            .into(tvImage)


    }

}
