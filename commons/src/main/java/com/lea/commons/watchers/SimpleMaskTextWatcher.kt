package com.lea.commons.watchers

import android.text.Editable
import android.text.TextWatcher

class SimpleMaskTextWatcher(val printableMask: String) : TextWatcher {
    private val GenericChar = '?'
    private val chars: CharArray
    private var editing: Boolean

    private fun fixTextWithMask(editable: Editable) {
        val result = applyMask(editable.toString())
        editable.replace(0, editable.length, result)
    }

    private fun extractTextFromEditableWithoutSpecialChars(editable: String): String {
        return removeMask(editable)
    }

    fun removeMask(text: String): String {
        return text.replace("[^0-9a-zA-Z]".toRegex(), "") //NON-NLS
    }

    fun applyMask(text: String): String {
        val currentText = extractTextFromEditableWithoutSpecialChars(text).toCharArray()
        var currentTextPos = 0
        var result = ""
        for (i in chars.indices) {
            if (currentTextPos >= currentText.size) {
                break
            }
            val posChar = chars[i]
            if (posChar == GenericChar) {
                result += currentText[currentTextPos]
                currentTextPos++
            } else {
                result += posChar
            }
        }
        return result
    }

    override fun afterTextChanged(editable: Editable) {
        if (!editing) {
            editing = true
            fixTextWithMask(editable)
            editing = false
        }
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        // do nothing
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        // do nothing
    }

    init {
        chars = printableMask.replace("[0-9a-zA-Z]".toRegex(), GenericChar.toString() + "")
            .toCharArray() //NON-NLS
        editing = false
    }
}