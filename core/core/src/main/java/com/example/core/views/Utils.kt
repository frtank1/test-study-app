package com.example.core.views

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast

class Utils {
    companion object {
        fun copyText(context: Context, link: String) {
            Toast.makeText(context, "текс скопирован", Toast.LENGTH_SHORT).show()
            val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("link", link)
            clipboardManager.setPrimaryClip(clip)
        }

        fun sendToat(text:String, context: Context){
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }
}