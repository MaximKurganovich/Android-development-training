package com.skillbox.a11homework

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


@Suppress("DEPRECATION")
class DialogChoiceFragment : DialogFragment() {
    private val tagList =
        Array(FragmentArticle.getTags().size) { index -> (FragmentArticle.getTags()[index].tag) }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showDialogWithMultipleChoice()
    }

    //Создается интерфейс для передачи данных в активити. Создается переменная типа интерфейс
    interface OnCompleteListener {
        fun onComplete(list: List<DataForFragmentArticle>)
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

    //Метод, который запускает диалоговое окно и устанавливает для него свойства
    private fun showDialogWithMultipleChoice() {
        val checkedItems = BooleanArray(tagList.size)
        val list = mutableListOf<String>()
        AlertDialog.Builder(requireContext())
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
                var filteredArticlesList = AppActivity().getScreens()
                if (list.size > 0 && list.size != tagList.size) {
                    for (item in list.indices) {
                        filteredArticlesList =
                            filteredArticlesList.filter { it.tag == list[item] }
                    }
                }
                // При выборе фильтров и нажатии на кнопку данные передаются в активити
                mListener?.onComplete(filteredArticlesList)
            }
            .setNegativeButton(R.string.cancel_button_in_dialog) { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

}