package com.skillbox.homework6

import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val logTag = "MyActivity"

    private var stateError: FormState = FormState(message = "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            stateError = savedInstanceState.getParcelable(KEY_VALIDATION_ERROR)!!
            if (stateError.message != "") textView.text = stateError.message
        }

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

        anr.setOnClickListener { Thread.sleep(12000) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_VALIDATION_ERROR, stateError)
    }

    override fun onStart() {
        super.onStart()
        Log.v(logTag, "onStart ${hashCode()}")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(logTag, "onRestart ${hashCode()}")
    }

    override fun onResume() {
        super.onResume()
        Log.v(logTag, "onResume ${hashCode()}")
    }

    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
        super.onTopResumedActivityChanged(isTopResumedActivity)
        Log.d(logTag, "onTopResumedActivityChanged $isTopResumedActivity ${hashCode()}")
    }

    override fun onPause() {
        super.onPause()
        Log.i(logTag, "onPause ${hashCode()}")
    }

    override fun onStop() {
        super.onStop()
        Log.wtf(logTag, "onStop ${hashCode()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(logTag, "onDestroy ${hashCode()}")
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

        val progressBar = ProgressBar(this).apply {
                id = View.generateViewId()
            }

        container.addView(progressBar)
        ConstraintSet().apply {
            constrainHeight(progressBar.id, ConstraintSet.WRAP_CONTENT)
            constrainWidth(progressBar.id, ConstraintSet.WRAP_CONTENT)
            connect(progressBar.id, ConstraintSet.TOP, R.id.anr, ConstraintSet.BOTTOM)
            connect(progressBar.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
            connect(progressBar.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT)
        }.applyTo(container)

    if (accountValidation()) {
        Handler().postDelayed({
            editLogin.text.clear()
            editLogin.isEnabled = true
            editPassword.text.clear()
            editPassword.isEnabled = true
            check.isEnabled = true
            button.isEnabled = true
            container.removeView(progressBar)
            textView.text = "Введите логин и пароль"
            stateError.message = ""
            Toast.makeText(this, R.string.login_toast, Toast.LENGTH_SHORT).show()
        }, 2000)
    } else {
        Handler().postDelayed({
            editLogin.isEnabled = true
            editPassword.isEnabled = true
            check.isEnabled = true
            button.isEnabled = true
            container.removeView(progressBar)
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
    private fun accountValidation (): Boolean {
        val loginVerification = """\w*@\w*\.\w{2,3}""".toRegex()
        val passwordVerification = """\w*""".toRegex()
        return loginVerification.matches(editLogin.text) && passwordVerification.matches(editPassword.text)
    }

companion object {
    private const val KEY_VALIDATION_ERROR = "validation error"
}
}
