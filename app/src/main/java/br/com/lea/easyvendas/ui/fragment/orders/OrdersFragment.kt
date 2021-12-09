package br.com.lea.easyvendas.ui.fragment.orders

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.databinding.FragmentOrdersBinding
import br.com.lea.easyvendas.domain.model.orders.ProductVO
import br.com.lea.easyvendas.ui.fragment.BaseFragment
import br.com.lea.easyvendas.ui.utils.customviews.stores.AlertFinishPurchase
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.view.customviews.c4.OnListProductListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrdersFragment : BaseFragment() {

    lateinit var binding: FragmentOrdersBinding

    private val viewModel by viewModel<OrdersViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()

        with(binding) {
            headerSearch.onSearchSubmitted = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }

            btnContinueOrder.setOnClickListener {
                 AlertFinishPurchase(requireContext())
            }


            with(binding.listProductsComponent) {
                addOnListProductListener = (object : OnListProductListener {
                    override fun onSuccessAdded(totalItens: String, totalPrice: String) {
                        Toast.makeText(
                            requireContext(),
                            "totalitens$totalItens _ totalprice$totalPrice",
                            Toast.LENGTH_LONG
                        ).show()

                    }

                    override fun onSuccessRemoved(totalItens: String, totalPrice: String) {
                        Toast.makeText(
                            requireContext(),
                            "totalitens$totalItens _ totalprice$totalPrice",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onSuccessEdited(totalItens: String, totalPrice: String) {
                        Toast.makeText(
                            requireContext(),
                            "totalitens$totalItens _ totalprice$totalPrice",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onLoadMoreItens() {
                        Log.d(this.javaClass.name, "loading more itens...")
                    }

                    override fun onItemCLicked(productVO: ProductVO) {
                        findNavController().navigate(R.id.action_orders_to_detailProductFragment)
                    }

                })
                renderList(ProductVO.mockList(), childFragmentManager)
            }



        }

        // viewModel.requestProducts("suggested")




    }

    private fun setupObservers() {
        viewModel.orderLiveData.observe(this.viewLifecycleOwner) {
            when (it) {
                is OrdersCommand.onProductsRequested -> {
                    with(binding.listProductsComponent) {
                        addOnListProductListener = (object : OnListProductListener {
                            override fun onSuccessAdded(totalItens: String, totalPrice: String) {
                                Toast.makeText(
                                    requireContext(),
                                    "totalitens$totalItens _ totalprice$totalPrice",
                                    Toast.LENGTH_LONG
                                ).show()

                            }

                            override fun onSuccessRemoved(totalItens: String, totalPrice: String) {
                                Toast.makeText(
                                    requireContext(),
                                    "totalitens$totalItens _ totalprice$totalPrice",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            override fun onSuccessEdited(totalItens: String, totalPrice: String) {
                                Toast.makeText(
                                    requireContext(),
                                    "totalitens$totalItens _ totalprice$totalPrice",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            override fun onLoadMoreItens() {
                                Log.d(this.javaClass.name, "loading more itens...")
                            }

                            override fun onItemCLicked(productVO: ProductVO) {
                                findNavController().navigate(R.id.action_orders_to_detailProductFragment)
                            }

                        })
                        renderList(it.response, childFragmentManager)
                    }

                }
            }
        }
    }


}