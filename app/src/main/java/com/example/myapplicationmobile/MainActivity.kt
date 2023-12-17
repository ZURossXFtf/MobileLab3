package com.example.myapplicationmobile

import android.os.Bundle
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    private lateinit var leftCheck: CheckBox
    private lateinit var rightCheck: CheckBox
    private lateinit var redInput: EditText
    private lateinit var greenInput: EditText
    private lateinit var blueInput: EditText
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // находим все элементы интерфейса
        leftCheck = findViewById(R.id.left_half)
        rightCheck = findViewById(R.id.right_half)


        // создаем диалоговое окно для ввода цвета
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Input color")
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_input_color, null)
        builder.setView(dialogLayout)
        builder.setPositiveButton("OK", null)
        builder.setNegativeButton("Cancel", null)
        dialog = builder.create()

        // находим все элементы диалогового окна
        val redInputDialog = dialogLayout.findViewById<EditText>(R.id.red_input_dialog)
        val greenInputDialog = dialogLayout.findViewById<EditText>(R.id.green_input_dialog)
        val blueInputDialog = dialogLayout.findViewById<EditText>(R.id.blue_input_dialog)
        val leftDialog = dialogLayout.findViewById<CheckBox>(R.id.left_dialog)
        val rightDialog = dialogLayout.findViewById<CheckBox>(R.id.right_dialog)
        val okButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
        okButton.setOnClickListener {
            // обрабатываем нажатие кнопки OK в диалоговом окне
            val red = redInputDialog.text.toString().toIntOrNull()
            val green = greenInputDialog.text.toString().toIntOrNull()
            val blue = blueInputDialog.text.toString().toIntOrNull()
            if (red != null && green != null && blue != null && red in 0..255 && green in 0..255 && blue in 0..255) {
                // цвет введен правильно, закрываем диалоговое окно
                dialog.dismiss()
                // меняем цвет главного окна в зависимости от установки флажков
                val color = Color.rgb(red, green, blue)
                if (leftDialog.isChecked && rightDialog.isChecked) {
                    findViewById<View>(R.id.left_half).setBackgroundColor(color)
                    findViewById<View>(R.id.right_half).setBackgroundColor(color)
                } else if (leftDialog.isChecked) {
                    findViewById<View>(R.id.left_half).setBackgroundColor(color)
                } else if (rightDialog.isChecked) {
                    findViewById<View>(R.id.right_half).setBackgroundColor(color)
                }
            }
        }
        val cancelButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        cancelButton.setOnClickListener {
            // обрабатываем нажатие кнопки Cancel в диалоговом окне
            dialog.cancel()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.input_color -> {
                // показываем диалоговое окно для ввода цвета
                dialog.show()
            }
            R.id.change -> {
                // меняем цвет главного окна в зависимости от установки флажков
                val red = redInput.text.toString().toIntOrNull()
                val green = greenInput.text.toString().toIntOrNull()
                val blue = blueInput.text.toString().toIntOrNull()
                if (red != null && green != null && blue != null && red in 0..255 && green in 0..255 && blue in 0..255) {
                    val color = Color.rgb(red, green, blue)
                    if (leftCheck.isChecked && rightCheck.isChecked) {
                        findViewById<View>(R.id.left_half).setBackgroundColor(color)
                        findViewById<View>(R.id.right_half).setBackgroundColor(color)
                    } else if (leftCheck.isChecked) {
                        findViewById<View>(R.id.left_half).setBackgroundColor(color)
                    } else if (rightCheck.isChecked) {
                        findViewById<View>(R.id.right_half).setBackgroundColor(color)
                    }
                }
            }
            R.id.exit -> {
                // завершаем работу приложения
                finish()
            }
            R.id.help -> {
                // показываем сообщение с помощью
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Help")
                builder.setMessage("This app allows you to change the color of the main window. " +
                        "Use the Input color command to enter a new color, " +
                        "and the Change command to apply it to the main window. " +
                        "Check the Left and Right checkboxes to select " +
                        "which part of the window will be colored. " +
                        "Exit command closes the app.")
                builder.setPositiveButton("OK", null)
                builder.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}