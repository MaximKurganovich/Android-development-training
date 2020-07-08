package com.skillbox.homework6

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.invoke
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {

//    Лаунчер запускает активити, а после получает результат и выводит его в textView

    private val resultLauncher = prepareCall(ActivityResultContracts.Dial()) { resultCode ->
        resultCode ?: toast("Результат не поступил")
        if (resultCode) {
            textViewResult.text = ("Результат успешен")
        } else {
            textViewResult.text = ("Результат не успешен")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


//        Листнер кнопки, который сначала проверяет введен ли номер телефона, а потом запускает метод
//        запуска активити с введенным номером телефона
        buttonCall.setOnClickListener {
            val checkingPhoneNumber = Patterns.PHONE.matcher(editPhoneNumber.text).matches()
            if (checkingPhoneNumber)
                dialPhoneNumber(editPhoneNumber.text.toString())
            else
                toast("Invalid phone number")
        }

    }
    //    Метод, который проверяет есть ли активити на телефоне способное произвести звонок и запускает
//    данное активити с веденным пользователем номером телефоном
    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            resultLauncher(phoneNumber)
//            startActivity(intent)
        }
    }


    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}