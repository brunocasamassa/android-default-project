package br.com.lea.easyvendas.ui.utils.listproducts.view.customviews.fragmentviews.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.view.customviews.c4.OnItemClickListener
import br.com.lea.easyvendas.ui.utils.listproducts.view.customviews.fragmentviews.subviews.ItemAmountView
import com.google.android.material.card.MaterialCardView


class ItemViewHolder(itemView: View, onItemClickListener: OnItemClickListener?, isStore: Boolean) :
    RecyclerView.ViewHolder(itemView) {
    init {

        itemView.findViewById<ItemAmountView>(R.id.amountProductView).onAmountChangeListener =
            { amount, originalValue ->
                onItemClickListener?.onAmountClicked(
                    adapterPosition,
                    amount,
                    originalValue
                )
            }

        itemView.findViewById<ItemAmountView>(R.id.amountProductView).onRemoveChangeListener =
            { amount, originalValue ->
                onItemClickListener?.onRemoveClicked(
                    adapterPosition,
                    amount,
                    originalValue
                )
            }

        itemView.findViewById<ItemAmountView>(R.id.amountProductView).onStockLevelMaxListener =
            {
                onItemClickListener?.onStockLimitClicked(adapterPosition)
            }

        itemView.findViewById<MaterialCardView>(R.id.baseCardProductView).setOnClickListener {
            onItemClickListener?.onProductClicked(adapterPosition, isStore)
        }

    }

}
