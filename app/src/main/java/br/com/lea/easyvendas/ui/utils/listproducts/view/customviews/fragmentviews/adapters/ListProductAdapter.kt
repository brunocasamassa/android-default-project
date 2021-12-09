package br.com.lea.easyvendas.ui.utils.listproducts.view.customviews.fragmentviews.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.ui.utils.listproducts.view.customviews.fragmentviews.holders.ItemViewHolder
import br.com.lea.easyvendas.ui.utils.listproducts.view.customviews.fragmentviews.subviews.ItemProductView
import br.com.lea.easyvendas.ui.utils.listproducts.view.customviews.fragmentviews.subviews.ItemAmountView
import br.com.lea.easyvendas.domain.model.orders.ProductVO
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.view.customviews.c4.OnItemClickListener
import com.lea.commons.extensions.show
import com.lea.commons.extensions.toPx


class ListProductAdapter(val context: Context?) :
    RecyclerView.Adapter<ItemViewHolder>() {

    var onBindView: (ProductVO) -> Unit = {}
    var listProductVOS: List<ProductVO> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClicked: OnItemClickListener? =
        null
    var showAddButton = true

    fun reset() {
        listProductVOS.forEach {
            it.quantity = 0
            it.onCart = false
        }
        notifyDataSetChanged()
    }

    fun updateProduct(
        sku: String?,
        quantity: Int?,
        loading: Boolean? = null,
        onCart: Boolean? = null
    ) {

        val index = listProductVOS.indexOfFirst { it.sku == sku }
        if (index != -1) {
            val item = listProductVOS[index]
            item.let {
                it.quantity = quantity ?: item.quantity
                it.sku = sku ?: item.sku
                it.onCart = onCart ?: item.onCart
                it.loading = loading ?: item.loading
            }
            notifyItemChanged(index)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val productHolderView = ItemProductView(parent.context)
        val lp = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        productHolderView.layoutParams = lp
        return ItemViewHolder(productHolderView, onItemClicked, !showAddButton)
    }

    override fun getItemId(position: Int): Long {
        return position.hashCode().toLong()
    }

    override fun getItemCount(): Int {
        return listProductVOS.size
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val isLastItem = (position == itemCount-1)
        val products = listProductVOS[position]
        if (payloads.isNotEmpty()) {
            updateViewState(holder, products)
            if (isLastItem){
                val lp = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                lp.height = 79.toPx()

                holder.itemView.layoutParams = lp

            }
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    private fun updateViewState(
        holder: ItemViewHolder,
        productVO: ProductVO
    ) {
        with(holder.itemView) {
            with(findViewById<ItemAmountView>(R.id.amountProductView)) {
                show(productVO.quantity != 0)
                quantity = productVO.quantity
                setStockLevel(
                    productVO.stockLevel,
                    productVO.quantity
                )
            }

        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val product = listProductVOS[position]
        with(holder.itemView as ItemProductView) {
            this.titleText =
                if (product.name.length <= 25) "${product.name}             ." else product.name
            this.priceText = product.price
            this.imageUrl = product.thumbnail
            this.descriptionText = "mocked description"
            this.quantity = product.quantity

            updateViewState(holder, product)

            onBindView(product)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


}

