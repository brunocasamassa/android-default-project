package br.com.lea.easyvendas.ui.utils.listproducts.view.customviews.fragmentviews.subviews

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import br.com.lea.easyvendas.R
import com.lea.commons.extensions.safeInflate
import com.lea.commons.extensions.show
import kotlinx.android.synthetic.main.view_amount_item.view.*
import kotlin.properties.Delegates

class ItemAmountView : FrameLayout {

    var quantity: Int by Delegates.observable<Int>(-1) { _, _, newQuantity ->
        when (quantity == ZERO_VALUE) {
            true -> {
                show(true)
                enableButton(true)
            }
            false -> {
                if (newQuantity == 1) {
                    productDecreaseButton.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_thrash
                        )
                    )
                }else {
                    productDecreaseButton.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_less_green
                        )
                    )
                }
                show(true)
                enableButton(true)
                amountTextView.text = newQuantity.toString()
            }
        }
    }

    var stockLevel: Int = -1
    fun setStockLevel(stock: Int, quantity: Int) {
        this.stockLevel = stock
        if(quantity == stock) {
            productIncreaseButton.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_lock_new
                )
            )
            enableButton(true)
        }
    }


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    init {
        safeInflate(context, R.layout.view_amount_item, this)
        setupListeners()
    }

    var onAmountChangeListener: (Int, Int) -> Unit = { _, _ -> }
    var onStockLevelMaxListener: () -> Unit = {}
    var onRemoveChangeListener: (Int, Int) -> Unit = { _, _ -> }


    private fun enableButton(isEnabled: Boolean) {
        productDecreaseButton.isEnabled = isEnabled
        productIncreaseButton.isEnabled = isEnabled
    }


    private fun setupListeners() {
        productDecreaseButton.setOnClickListener {
            productIncreaseButton.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_plus_green
                )
            )
            val originalValue = amountTextView.text.toString().toIntOrNull()


            //decreasing and performing view state
            val amount = originalValue?.minus(INCREASE_AMOUNT)
            when (amount) {
                0 -> {
                    enableButton(false)
                    onRemoveChangeListener(amount, originalValue)
                }
                1 -> {
                    productDecreaseButton.setImageDrawable(
                        ContextCompat.getDrawable(context, R.drawable.ic_thrash)
                    )
                    onAmountChangeListener(amount, originalValue)
                    amountTextView.text = amount.toString()
                }
                else -> {
                    productDecreaseButton.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_less_green
                        )
                    )
                    onAmountChangeListener(amount!!, originalValue)
                    amountTextView.text = amount.toString()
                }

            }

        }

        productIncreaseButton.setOnClickListener {
            productDecreaseButton.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_less_green
                )
            )
            val originalValue = amountTextView.text.toString().toIntOrNull()
            originalValue?.takeIf { it < stockLevel }?.let {
                val amount = it + INCREASE_AMOUNT
                amountTextView.text = amount.toString()
                productIncreaseButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_plus_green
                    )
                )
                onAmountChangeListener(amount, originalValue)
            }
            originalValue?.takeIf { it >= stockLevel }?.let {
                productIncreaseButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_lock_new
                    )
                )
                onStockLevelMaxListener()
            }
        }
    }

    private companion object {
        private const val ZERO_VALUE = 0
        private const val INCREASE_AMOUNT = 1
    }


}
