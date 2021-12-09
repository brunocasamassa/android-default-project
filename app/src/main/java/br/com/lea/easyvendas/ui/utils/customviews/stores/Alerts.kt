package br.com.lea.easyvendas.ui.utils.customviews.stores

import android.app.Dialog
import android.content.Context
import android.widget.ImageView
import br.com.lea.easyvendas.R

fun AlertFinishPurchase(context: Context){
    val dialog = Dialog(context)

    dialog.setContentView(R.layout.ic_alert_purchase_completed)

    val btnClose = dialog.findViewById<ImageView>(R.id.btnClose)
    btnClose.setOnClickListener {
        dialog.dismiss()
    }
    dialog.show()
}


