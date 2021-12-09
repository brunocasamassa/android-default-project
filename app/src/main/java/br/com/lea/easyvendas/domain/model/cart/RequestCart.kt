package br.com.lea.easyvendas.domain.model.cart

import br.com.lea.easyvendas.domain.model.orders.ProductVO

data class RequestCart(val productVOS:List<ProductVO> = listOf(), val totalPrice:String = "0")
