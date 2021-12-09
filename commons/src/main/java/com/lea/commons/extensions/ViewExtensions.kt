package com.lea.commons.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.contains

fun ViewGroup.safeAddView(view: View, index: Int = -1) {
    if (!view.removeFromParent() && this.contains(view)){
        this.removeView(view)
        this.endViewTransition(view)
    }

    this.addView(view, index)
}

fun View.removeFromParent(): Boolean {
    val parent = this.parent
    return if (parent != null && parent is ViewGroup) {
        parent.removeView(this)
        parent.endViewTransition(this)
        true
    } else {
        false
    }
}

fun LayoutInflater.safeInflate(@LayoutRes viewResourceId: Int, parent: ViewGroup?, attachToRoot: Boolean = false): View {
    val existingView = parent?.children?.find { it.id == viewResourceId }
    parent?.clearDisappearingChildren()

    return if (existingView == null) {
        this.inflate(viewResourceId, parent, attachToRoot)
    } else {
        existingView.removeFromParent()
        existingView
    }
}

fun View.safeInflate(context: Context?, @LayoutRes resourceId: Int, root: ViewGroup?): View {
    val inflater = LayoutInflater.from(context)
    return inflater.safeInflate(resourceId, root, root != null)
}

fun View.show(isVisible:Boolean){
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.showInvisible(isVisible:Boolean){
    this.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}
