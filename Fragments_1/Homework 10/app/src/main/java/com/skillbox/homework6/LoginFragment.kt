package com.skillbox.homework6

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment(R.layout.login_fragment) {
    private val logTag = "MyActivity"

    private var stateError: FormState = FormState(message = "")

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Glide.with(this)
            .load(R.drawable.icon_sc)
            .centerCrop()
            .into(icon)
        button.isEnabled = false

        //        Обработчик, который реагирует на изменения в EditText для ввода логина.
//        Если в поле есть символы, запускается метод проверки (метод и описание в конце кода)

        editLogin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkFieldsForEmptyValues()
            }
        })

//        Обработчик, который реагирует на изменения в EditText для ввода пароля.
//        Если в поле есть символы, запускается метод проверки (метод и описание в конце кода)

        editPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkFieldsForEmptyValues()
            }
        })

//        Обработчик, который реагирует на изменения состояния checkBox.
//        Если флаг установлен, то запускается метод проверки (метод и описание в конце кода)

        check.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                checkFieldsForEmptyValues()
            }
        })

        button.setOnClickListener { makeLoginOperation() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_VALIDATION_ERROR, stateError)
    }


    //    Метод для обработчика кнопки. При нажатии на кнопку все элементы становятся недоступными,
//    создается progressBar, далее он добавляется в ConstraintLayout. Через 2 секунды элементы становятся
//    доступны для взаимодействия, progressBar удаляется. В принципе можно обойтись без этого метода,
//    скопировав все в обработчик кнопки
    private fun makeLoginOperation() {
        editLogin.isEnabled = false
        editPassword.isEnabled = false
        check.isEnabled = false
        button.isEnabled = false
        progressBar.visibility = View.VISIBLE

        if (accountValidation()) {
            Handler().postDelayed({
                progressBar.visibility = View.GONE
                successLogin()
            }, 2000)
        } else {
            Handler().postDelayed({
                editLogin.isEnabled = true
                editPassword.isEnabled = true
                check.isEnabled = true
                button.isEnabled = true
                progressBar.visibility = View.GONE
                textView.text = "Некорректный логин или пароль"
                stateError.message = textView.text.toString()
            }, 2000)
        }
    }

//    Метод, который проверяет выполнения всех условий (введены логин и пароль и принял ли пользователь
//    соглашение). Этот метод есть в обработчиках EditText и CheckBox. Как только выполняются все условия, кнопка
//    входа становится активной

    private fun checkFieldsForEmptyValues() {
        val login = editLogin.text.toString()
        val password = editPassword.text.toString()

        button.isEnabled = !(login == "" || password == "" || !check.isChecked)
    }

    //    Метод, который проверяет через регулярные выражения корректность введенного логина и пароля
    private fun accountValidation(): Boolean {
        val loginVerification = Patterns.EMAIL_ADDRESS.matcher(editLogin.text).matches()
        val passwordVerification = """\w*""".toRegex()
        return loginVerification && passwordVerification.matches(editPassword.text)
    }

//    Данный метод заменяет текущий фрагмент на другой

    private fun successLogin() {
        val trans = fragmentManager?.beginTransaction()
        trans?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        trans?.replace(R.id.mainContainer, MainFragment(), TAG_MAIN_FRAGMENT)
        trans?.commit()
    }

    companion object {
        private const val KEY_VALIDATION_ERROR = "validation error"
        private const val TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT"
    }
}