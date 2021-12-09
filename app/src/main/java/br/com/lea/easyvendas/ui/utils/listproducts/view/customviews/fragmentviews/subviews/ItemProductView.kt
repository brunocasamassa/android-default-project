package br.com.lea.easyvendas.ui.utils.listproducts.view.customviews.fragmentviews.subviews

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.FrameLayout
import br.com.lea.easyvendas.R
import com.bumptech.glide.Glide
import com.lea.commons.extensions.safeInflate
import com.lea.commons.extensions.show
import com.lea.commons.extensions.toMoneySimbol
import kotlinx.android.synthetic.main.item_product_view.view.*
import kotlinx.android.synthetic.main.view_product_item.view.*
import kotlinx.android.synthetic.main.view_product_item.view.descriptionView
import kotlinx.android.synthetic.main.view_product_item.view.imageProductView
import kotlinx.android.synthetic.main.view_product_item.view.productTitleView

import kotlin.properties.Delegates

class ItemProductView : FrameLayout {


    var imageUrl by Delegates.observable<String?>(null) { _, _, newImage ->
        Glide.with(imageProductView.context)
            .load(newImage)
            .error(R.drawable.ic_close)
            .into(imageProductView)
    }

    var titleText by Delegates.observable<String?>(null) { _, _, newTitle ->
        if (newTitle.isNullOrEmpty()) {
            productTitleView.show(false)
        } else {
            with(productTitleView) {
                text = newTitle
                show(true)
            }
        }
    }

    var priceText: String? by Delegates.observable<String?>(null) { _, _, newPrice ->
        priceView.text = newPrice?.toMoneySimbol()
    }


    var descriptionText: String? by Delegates.observable<String?>(null) { _, _, newDescription ->
         descriptionView.text = newDescription
    }

    var quantity: Int? by Delegates.observable<Int?>(0) { _, _, newQuantity ->
        amountProductView.show(true)
        amountProductView.setStockLevel(30, newQuantity ?:0 )
    }

    private fun isValidPrice(value: String?): Boolean {
        if (value == null) return false
        return value.replace(",", ".").toDouble() > 0
    }

    constructor(context: Context) : super(context)

    init {
        safeInflate(context, R.layout.view_product_item, this)
    }


}
