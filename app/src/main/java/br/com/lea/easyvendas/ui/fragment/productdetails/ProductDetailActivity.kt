package br.com.lea.easyvendas.ui.fragment.productdetails

import android.app.Activity
import android.os.Bundle
import br.com.lea.easyvendas.databinding.ProductDetailActivityBinding

class ProductDetailActivity:Activity() {

    lateinit var binding: ProductDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductDetailActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val ft = fragmentManager?.beginTransaction()

      /*  with(ProductsDetailFragment.newInstance(intent?.getSerializableExtra(ProductsDetailFragment.PRODUCT_DETAIL_KEY) as Product)) {
            ft?.add(this,this.tag)?.commit()
        }*/

    }
}