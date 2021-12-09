package br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import br.com.lea.apimanager.SafeResponse
import br.com.lea.apimanager.safeRequest
import br.com.lea.easyvendas.domain.model.cart.RequestCart
import br.com.lea.easyvendas.domain.model.orders.ProductVO
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.viewmodel.usecase.AddProductsInCartUseCase
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.viewmodel.usecase.GetCartUseCase
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.viewmodel.usecase.RemoveProductUseCase
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.viewmodel.usecase.UpdateProductUseCase
import com.lea.commons.constants.ProjectConstants
import com.lea.commons.constants.ProjectConstants.CART_KEY
import com.orhanobut.hawk.Hawk

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ProductListViewModel(
    private val getUpdateProductUseCase: UpdateProductUseCase,
    private val removeProductUseCase: RemoveProductUseCase,
    private val addProductsToCardUserCase: AddProductsInCartUseCase,
    private val getCartUseCase: GetCartUseCase,
    dispatcher: CoroutineContext = Dispatchers.IO + SupervisorJob()
) : ViewModel() {

    private val mutableViewState = MutableLiveData<ProductListViewState>()
    val viewState: LiveData<ProductListViewState> = mutableViewState

    private var coroutineScope = CoroutineScope(dispatcher)

    //fixme todos estes metodos terão modificações na integração

    fun verifyCartItems() {
        Hawk.get<RequestCart>(CART_KEY)?.let{
            onLoadCart(it)
            return
        }

        getCart()
    }

    private fun getCart(){
        coroutineScope.launch {
            val response = safeRequest { getCartUseCase() }

            when (response) {
                is SafeResponse.Success -> {
                    Hawk.put(ProjectConstants.CART_KEY, response.value)
                    ProductListViewState.OnCartRequested(response.value).run()
                }
            }
        }
    }

    fun addToCartButton(item: RequestCart, productVO: ProductVO) {
        coroutineScope.launch {
            when (val response = safeRequest { addProductsToCardUserCase(item) }) {
                is SafeResponse.Success -> {
                    response.value?.let { cartVO ->
                        Hawk.put(ProjectConstants.CART_KEY, response.value)
                        ProductListViewState.FinishAddProductToCart(cartVO, item).run()
                    }
                }
                is SafeResponse.GenericError -> onUnexpectedError(response.error?.message(), productVO)
                is SafeResponse.NetworkError -> onNetworkError(productVO )
            }
        }
    }

    private fun onUnexpectedError(mesage: String?, productVO: ProductVO) {
        ProductListViewState.ShowGenericErrorDialog(mesage ,productVO  ).run()
    }

    private fun onNetworkError(productVO: ProductVO) {
        ProductListViewState.ShowGenericErrorDialog(
            "Verifique sua conexão com a internet.",productVO
         )
            .run()
    }

    private fun ProductListViewState.run() {
        mutableViewState.postValue(this)
    }


    fun editItemCart(productVO: ProductVO, item: RequestCart) {
        coroutineScope.launch {
            val response = safeRequest { getUpdateProductUseCase(productVO.sku, item) }

            when (response) {
                is SafeResponse.Success -> getUpdateProductResponseSuccess(response.value, productVO, productVO.sku)
                is SafeResponse.GenericError -> getUpdateProductResponseFail(response.error?.message(), productVO)
                is SafeResponse.NetworkError -> onNetworkError( productVO)
            }
        }
    }

    private fun getUpdateProductResponseSuccess(requestCart: RequestCart, item: ProductVO, sku: String) {
        Hawk.put(ProjectConstants.CART_KEY, requestCart)
        ProductListViewState.SuccessOnEditProduct(requestCart, item, sku).run()
    }

    private fun getUpdateProductResponseFail(message: String?, productVO: ProductVO) {
        ProductListViewState.FailOnEditProduct(message, productVO).run()
    }

    fun removeItemCart(productVO: ProductVO) {
        coroutineScope.launch {
            val response = safeRequest { removeProductUseCase(productVO.sku, true) }

            when (response) {
                is SafeResponse.Success -> removeProductSuccess(response.value, productVO.sku)
                is SafeResponse.NetworkError -> onNetworkError( productVO)
                is SafeResponse.GenericError -> onUnexpectedError("Ocorreu um erro inesperado.", productVO)
            }
        }
    }

    private fun removeProductSuccess(requestCart: RequestCart, productId: String) {
        Hawk.put(ProjectConstants.CART_KEY, requestCart)
        ProductListViewState.SuccessOnRemoveProduct(requestCart, productId).run()
    }

    private fun onLoadCart(cart: RequestCart) {
        ProductListViewState.OnCartRequested(cart).run()
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}
