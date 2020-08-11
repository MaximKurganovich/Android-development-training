package com.skillbox.a11homework

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


@Suppress("DEPRECATION")
class DialogChoiceFragment : DialogFragment() {
    private val tagList =
        Array(FragmentArticle.getTags().size) { index -> (FragmentArticle.getTags()[index].tag) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        val checkedItems = BooleanArray(tagList.size)
        val list = mutableListOf<String>()
        return AlertDialog.Builder(requireContext())
            .setTitle("Filter")
            .setMultiChoiceItems(
                tagList, checkedItems
            ) { _, which, isChecked ->
                checkedItems[which] = isChecked
            }.setPositiveButton(R.string.ok_button_in_dialog) { _, _ ->
                for (item in tagList.indices) {
                    if (!checkedItems[item]) {
                        continue
                    } else {
                        list.add(tagList[item])
                    }
                }
                // При выборе фильтров и нажатии на кнопку данные передаются в активити
                mListener?.onComplete(list)
            }
            .setNegativeButton(R.string.cancel_button_in_dialog) { dialog, _ ->
                dialog.cancel()
            }.create()
    }


    //Создается интерфейс для передачи данных в активити. Создается переменная типа интерфейс
    interface OnCompleteListener {
        fun onComplete(list: MutableList<String>)
    }

    private var mListener: OnCompleteListener? = null

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            mListener = activity as OnCompleteListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$activity must implement OnCompleteListener")
        }
    }
}