package com.skillbox.a16homework

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

interface CorrectOrDelete {
    fun correctOrDeleteElement(key: Int)
}

//Диалог фрагмент, который вызывается при нажатии на любой элемент списка. Предлагает удалить или отредактировать элемент
class DialogueForSelection : DialogFragment() {

    private val mListener: CorrectOrDelete?
        get() = parentFragment as? CorrectOrDelete ?: activity as? CorrectOrDelete

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(requireContext().resources.getText(R.string.choice))
            .setItems(R.array.correctOrDelete) { _, which -> mListener?.correctOrDeleteElement(which) }
            .create()
    }
}