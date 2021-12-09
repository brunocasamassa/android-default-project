package br.com.lea.easyvendas.ui.utils.listproducts.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager

import br.com.lea.easyvendas.ui.utils.listproducts.view.customviews.fragmentviews.ListFragmentView
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.view.customviews.c4.OnListProductListener
import br.com.lea.easyvendas.R

import br.com.lea.easyvendas.domain.model.orders.ProductVO
import com.lea.commons.extensions.show
import kotlinx.android.synthetic.main.list_product_fragment.*

class ListProductComponent(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet), OnC4InstanceListener {

    lateinit var localFragment: ListFragmentView
    override fun getListFragmentView(): ListFragmentView {
        return if (this::localFragment.isInitialized) localFragment else ListFragmentView.newInstance()
    }

    init {
        View.inflate(context, R.layout.frame_list_product_component, null)
    }

    fun renderList(
        productVOS: List<ProductVO>,
        manager: FragmentManager,
        showAddButton: Boolean = true
    ) {
        with(this.getListFragmentView()) {
            localFragment = this
            if(isListenerInitialized) {addOnListProductListener = this@ListProductComponent.addOnListProductListener
            }
            loadList(productVOS, showAddButton)

            // manager.popBackStackImmediate()

            val ft = manager.beginTransaction()
            if (this.isAdded) {
                ft.show(this)
            } else
                ft.replace(this@ListProductComponent.id, this).commitNow()
        }
    }


    lateinit var addOnListProductListener: OnListProductListener

    val isListenerInitialized get() = this::addOnListProductListener.isInitialized

    fun showLoadingProgress() = getListFragmentView().progressBarListIcon.show(true)
}

interface OnC4InstanceListener {

    fun getListFragmentView(): ListFragmentView

}
