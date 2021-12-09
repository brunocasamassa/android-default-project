package br.com.lea.easyvendas.ui.fragment.orders

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lea.apimanager.SafeResponse
import br.com.lea.apimanager.safeRequest
import br.com.lea.easyvendas.data.orders.OrdersRepository
import br.com.lea.easyvendas.domain.model.catalog.Catalog
import br.com.lea.easyvendas.domain.model.orders.ProductVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class OrdersViewModel(
    val repo: OrdersRepository,
    private val dispatcher: CoroutineContext = Dispatchers.Main + SupervisorJob()
) : ViewModel() {


    val orderLiveData = MutableLiveData<OrdersCommand>()



    @RequiresApi(Build.VERSION_CODES.M)
    fun requestProducts(route:String) {
        viewModelScope.launch(dispatcher) {
            when(val response = safeRequest { repo.getOrders(route) }){
                is SafeResponse.Success->{
                    orderLiveData.value = OrdersCommand.onProductsRequested(ProductVO.createListFromCatalog(response.value))
                }
                is SafeResponse.GenericError->{
                    Log.e("errorPromotions: ", response.errorMessage)

                }
                is SafeResponse.NetworkError ->{
                    Log.e("errorPromotions: ", response.errorMessage)
                }

            }}
    }
}



sealed class OrdersCommand {
    data class onProductsRequested(val response: List<ProductVO>) : OrdersCommand()
}