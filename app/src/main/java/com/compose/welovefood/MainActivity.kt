package com.compose.welovefood

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.compose.welovefood.databinding.ActivityMainBinding
import com.compose.welovefood.utils.EmojiUnicode
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtons()
    }

    private fun setupButtons() {
        with(binding.startpageSearchInputField) {

            onFocusChangeListener = OnFocusChangeListener { _, focused ->
                val keyboard = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                if (focused) keyboard.showSoftInput(
                    binding.startpageSearchInputField,
                    0
                ) else keyboard.hideSoftInputFromWindow(binding.startpageSearchInputField.windowToken, 0)
            }

            setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH || event != null && event.action == KeyEvent.ACTION_DOWN) {
                    Log.d("MainActivity", "Search button clicked")
                    binding.startpageSearchInputField.clearFocus()

                    // compares the text in lowercase and finds if there is a match with the enum
                    val text = binding.startpageSearchInputField.text.toString().lowercase()
                    val emoji = EmojiUnicode.values().find { it.keyWord == text }?.let {
                        getEmojiByUnicode(it.unicode)
                    } ?: ""

                    Snackbar.make(binding.root, "Let's search for the best $text $emoji", Snackbar.LENGTH_SHORT).show()
                    true
                } else {
                    false
                }
            }
        }
    }

    fun getEmojiByUnicode(unicode: Int): String? {
        return String(Character.toChars(unicode))
    }
}
