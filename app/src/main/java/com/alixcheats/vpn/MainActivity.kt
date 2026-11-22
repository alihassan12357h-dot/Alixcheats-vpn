
package com.alixcheats.vpn

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    private val green = Color.rgb(57, 255, 20)
    private val bg = Color.rgb(5, 9, 7)
    private var selected = "Pakistan"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = bg
        window.navigationBarColor = bg

        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.gravity = Gravity.CENTER
        root.setPadding(20, 25, 20, 20)
        root.setBackgroundColor(bg)

        fun addText(value: String, size: Float, color: Int) {
            val t = TextView(this)
            t.text = value
            t.textSize = size
            t.setTextColor(color)
            t.gravity = Gravity.CENTER
            t.setPadding(10, 12, 10, 12)
            root.addView(t)
        }

        addText("♛", 45f, green)
        addText("ALI X CHEATS", 30f, Color.WHITE)
        addText("VPN", 32f, green)
        addText("FAST • SECURE • FREE", 15f, Color.LTGRAY)
        addText("NOT CONNECTED", 18f, Color.WHITE)

        val connect = Button(this)
        connect.text = "⏻  CONNECT"
        connect.textSize = 22f
        connect.setTextColor(Color.BLACK)
        connect.setBackgroundColor(green)
        connect.setOnClickListener {
            Toast.makeText(
                this,
                "VPN server will be added later!",
                Toast.LENGTH_LONG
            ).show()
        }
        root.addView(connect)

        addText("SELECT SERVER", 18f, green)

        val countries = listOf(
            "Pakistan", "Singapore", "Germany",
            "USA", "Netherlands"
        )

        countries.forEach { country ->
            val item = Button(this)
            item.text = if (selected == country) "✓ $country" else country
            item.setOnClickListener {
                selected = country
                Toast.makeText(this, country, Toast.LENGTH_SHORT).show()
            }
            root.addView(item)
        }

        addText("ALI X CHEATS VPN • FREE", 13f, green)
        setContentView(root)
    }
}
