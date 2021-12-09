package br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.view.customviews.c4

import br.com.lea.easyvendas.domain.model.orders.ProductVO

interface OnItemClickListener {

    fun onAddToCartClicked(position: Int)
    fun onAmountClicked(position: Int, amount: Int, originalValue: Int)
    fun onProductClicked(position: Int, isStore: Boolean)
    fun onStockLimitClicked(position: Int)
    fun onRemoveClicked(position: Int, amount: Int, originalValue: Int)

}

interface OnListProductListener {

    fun onSuccessAdded(totalItens: String, totalPrice:String)
    fun onSuccessRemoved(totalItens: String, totalPrice:String)
    fun onSuccessEdited(totalItens: String, totalPrice:String)
    fun onLoadMoreItens()
    fun onItemCLicked(productVO: ProductVO)


}
