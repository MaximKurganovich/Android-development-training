package com.skillbox.homework6

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.with(this)
            .load(R.drawable.icon_sc)
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

        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                makeLoginOperation()
            }

        })
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
            connect(progressBar.id, ConstraintSet.TOP, R.id.button, ConstraintSet.BOTTOM)
            connect(progressBar.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
            connect(progressBar.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT)
        }.applyTo(container)

        Handler().postDelayed({
            editLogin.text.clear()
            editLogin.isEnabled = true
            editPassword.text.clear()
            editPassword.isEnabled = true
            check.isEnabled = true
            button.isEnabled = true
            container.removeView(progressBar)
            Toast.makeText(this, R.string.login_toast, Toast.LENGTH_SHORT).show()
        }, 2000)
    }

//    Метод, который проверяет выполнения всех условий (введены логин и пароль и принял ли пользователь
//    соглашение). Этот метод есть в обработчиках EditText и CheckBox. Как только выполняются все условия, кнопка
//    входа становится активной

    private fun checkFieldsForEmptyValues() {
        val login = editLogin.text.toString()
        val password = editPassword.text.toString()

        button.isEnabled = !(login == "" || password == "" || !check.isChecked)
    }
}
