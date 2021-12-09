package br.com.lea.easyvendas.ui.utils.listproducts.view.customviews.fragmentviews

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.postDelayed
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.ui.utils.listproducts.view.customviews.fragmentviews.adapters.ListProductAdapter
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.viewmodel.ProductListViewModel
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.viewmodel.ProductListViewState
import br.com.lea.easyvendas.domain.model.cart.RequestCart
import br.com.lea.easyvendas.domain.model.orders.ProductVO
import br.com.lea.easyvendas.ui.fragment.BaseFragment
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.view.customviews.c4.OnItemClickListener
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.view.customviews.c4.OnListProductListener
import com.lea.commons.constants.ProjectConstants
import com.lea.commons.extensions.safeInflate
import com.lea.commons.extensions.show
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.list_product_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragmentView : BaseFragment(), OnItemClickListener {

    override fun onAddToCartClicked(position: Int) {
        val product = gridProductAdapter.listProductVOS[position]

        handler.removeCallbacksAndMessages(product.sku)
        gridProductAdapter.updateProduct(product.sku, 1, false)
        addToCart(product)

    }

    override fun onProductClicked(position: Int, isStore: Boolean) {
        val product = gridProductAdapter.listProductVOS[position]


        addOnListProductListener.onItemCLicked(product)

        /*   val intent = Intent(requireContext(), ProductDetailActivity::class.java)
           intent.putExtra(ProductsDetailFragment.PRODUCT_DETAIL_KEY,product)

           startActivity(intent)*/

    }

    override fun onStockLimitClicked(position: Int) {
        Toast.makeText(requireContext(), "Limite atingido", Toast.LENGTH_LONG).show()
    }


    override fun onRemoveClicked(position: Int, amount: Int, originalValue: Int) {
        val product = gridProductAdapter.listProductVOS[position]
        gridProductAdapter.updateProduct(product.sku, 0, null, true)
        viewModel.removeItemCart(product)
    }

    override fun onAmountClicked(position: Int, amount: Int, originalValue: Int) {
        val product = gridProductAdapter.listProductVOS[position]

        gridProductAdapter.updateProduct(
            product.sku,
            amount,
            false
        )
    }

    private val handler = Handler()

    private val viewModel by viewModel<ProductListViewModel>()
    val gridProductAdapter by lazy { ListProductAdapter(context) }

    lateinit var addOnListProductListener: OnListProductListener


    fun loadList(productVOS: List<ProductVO>, showAddButton: Boolean) {
        onLoadProducts(productVOS, showAddButton)
        handler.post { setupRecyclerView() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.safeInflate(R.layout.list_product_fragment, container, false)

    override fun onResume() {
        super.onResume()
        gridProductAdapter.reset()
        if (gridProductAdapter.showAddButton) {
            viewModel.verifyCartItems()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.viewState.removeObservers(this)
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ProductListViewState.OnCartRequested -> setupViewState(it)
                is ProductListViewState.FinishAddProductToCart -> {
                    it.value?.let { cartVO ->
                        updateListWithCart(cartVO)

                        if (this::addOnListProductListener.isInitialized) {
                            val requestCart = cartVO

                            val totalItems = (requestCart)
                                .toString()

                            addOnListProductListener.onSuccessAdded(totalItems, cartVO.totalPrice)
                        }
                    }
                }
                is ProductListViewState.ShowErrorMessage -> gridProductAdapter.notifyDataSetChanged()
                is ProductListViewState.ShowGenericErrorDialog -> {
                    gridProductAdapter.updateProduct(
                        sku = it.productVO.sku,
                        quantity = 0,
                        loading = false
                    )
                    showGenericError(it.message)
                }
                is ProductListViewState.FailOnEditProduct -> {
                    checkProductError(it.productVO)
                    showGenericError(it.message)
                }
                is ProductListViewState.SuccessOnEditProduct -> {
                    updateListWithCart(it.value)

                    if (this::addOnListProductListener.isInitialized) {

                        val totalItems = (it.value.productVOS.sumBy { rci -> rci.quantity })
                            .toString()

                        addOnListProductListener.onSuccessEdited(totalItems, it.value.totalPrice)
                    }
                }
                is ProductListViewState.SuccessOnRemoveProduct -> {
                    onSuccessRemoveProduct(it.value, it.productId)
                }
            }
        })
    }

    fun updateListWithCart(cart: RequestCart) {
        cart.productVOS.forEach { requestCartItem ->
            val quantity = cart.productVOS
                .filter { it.sku == requestCartItem.sku }
                .sumBy { it.quantity }

            gridProductAdapter.updateProduct(
                sku = requestCartItem.sku,
                quantity = quantity,
                loading = false,
                onCart = true
            )
        }
    }

    private fun checkProductError(productVO: ProductVO) {
        val cart = Hawk.get<RequestCart>(ProjectConstants.CART_KEY)
        val item = cart?.productVOS?.find { it.sku == productVO.sku }
        if (item != null) {
            gridProductAdapter.updateProduct(
                item.sku,
                item.quantity,
                loading = false,
                onCart = true
            )
        } else {
            gridProductAdapter.updateProduct(
                productVO.sku,
                0,
                loading = false,
                onCart = false
            )
        }
    }

    private fun onSuccessRemoveProduct(cart: RequestCart, productId: String) {
        gridProductAdapter.updateProduct(productId, 0, loading = false, onCart = false)
        cart.productVOS.forEach {
            gridProductAdapter.updateProduct(
                it.sku,
                it.quantity,
                null,
                true
            )
        }

        if (this::addOnListProductListener.isInitialized) {
            addOnListProductListener.onSuccessRemoved((cart.productVOS.sumBy { it.quantity }
                .toString()), cart.totalPrice)
        }
    }


    private fun setupViewState(viewState: ProductListViewState.OnCartRequested) {
        viewState?.value?.productVOS?.forEach { item ->
            val quantity = viewState.value.productVOS
                .filter { it.sku == item.sku }
                .sumBy { it.quantity }

            gridProductAdapter.updateProduct(
                sku = item.sku,
                quantity = quantity,
                loading = false,
                onCart = true
            )
        }
    }


    fun setupRecyclerView() {
        with(listProductsRecycler) {
            adapter = gridProductAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                context, RecyclerView.VERTICAL, false
            )
            itemAnimator = null

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    recyclerView.layoutManager?.let {
                        it as LinearLayoutManager
                        if (it.itemCount > 0 && (it.itemCount - it.findLastVisibleItemPosition() <= 2)) {
                            if (this@ListFragmentView::addOnListProductListener.isInitialized) {
                                addOnListProductListener.onLoadMoreItens()
                            }
                        }
                    }
                }
            })
        }

        gridProductAdapter.onItemClicked = this
    }

    private fun onLoadProducts(
        productVOS: List<ProductVO>,
        showAddButton: Boolean
    ) {

        gridProductAdapter.listProductVOS = productVOS
        gridProductAdapter.showAddButton = showAddButton

        progressBarListIcon?.let { it.show(false) }
    }

    private fun showGenericError(msg: String?) {
        Toast.makeText(
            context,
            msg ?: "Aconteceu um erro inesperado",
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        const val COLUMN_NUMBERS = "columnNumber"
        private const val DELAY_TIME_MS = 2000L
        private const val REQUEST_ID = 555

        fun newInstance(): ListFragmentView {
            val bundle = Bundle().apply {
            }
            return ListFragmentView().apply { arguments = bundle }
        }
    }

    private fun addToCart(productVO: ProductVO) {
        handler.postDelayed(DELAY_TIME_MS) {
            gridProductAdapter.updateProduct(productVO.sku, productVO.quantity, null, true)
            viewModel.addToCartButton(
                RequestCart(),
                productVO
            )
        }
    }

    private fun editToCart(amount: Int, productVO: ProductVO) {
        handler.postDelayed(DELAY_TIME_MS) {
            val request = RequestCart(
            )
            gridProductAdapter.updateProduct(productVO.sku, amount, true)
            viewModel.editItemCart(productVO, request)
        }
    }

}
