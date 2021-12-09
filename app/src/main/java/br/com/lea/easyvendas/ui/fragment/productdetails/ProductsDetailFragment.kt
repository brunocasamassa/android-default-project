package br.com.lea.easyvendas.ui.fragment.productdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.domain.model.orders.ProductVO
 import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class ProductsDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products_detail,null)
       // binding = FragmentProductsDetailBinding.inflate(inflater, container, false)


        var imageArray:ArrayList<Int> = ArrayList()
        imageArray.add(R.drawable.img_promo_action)
        imageArray.add(R.drawable.img_promo_action)
        imageArray.add(R.drawable.img_promo_action)

        view.findViewById<AppCompatImageView>(R.id.button_close).setOnClickListener { findNavController().popBackStack() }

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = arguments?.get(PRODUCT_DETAIL_KEY) as? ProductVO

        product?.let {  Toast.makeText(context,it.name?:"",Toast.LENGTH_SHORT).show()  }

    }

    companion object {
        const val PRODUCT_DETAIL_KEY = "_PRODUCT_DETAIL_KEY"
        fun newInstance(productVO:ProductVO): ProductsDetailFragment {
            val bundle = Bundle().apply {
                putSerializable(PRODUCT_DETAIL_KEY,productVO)
            }
            return ProductsDetailFragment().apply { arguments = bundle }
        }
    }


}