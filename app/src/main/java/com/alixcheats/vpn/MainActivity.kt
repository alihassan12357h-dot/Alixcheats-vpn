
package com.alixcheats.vpn

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {

    private val green = Color.rgb(57, 255, 20)
    private val bg = Color.rgb(5, 8, 7)
    private val card = Color.rgb(17, 23, 19)
    private var selected = "Pakistan"

    private lateinit var serverList: LinearLayout
    private lateinit var status: TextView

    private fun rounded(color: Int, radius: Float): GradientDrawable {
        return GradientDrawable().apply {
            setColor(color)
            cornerRadius = radius
        }
    }

    private fun text(
        value: String,
        size: Float,
        color: Int,
        bold: Boolean = false
    ): TextView {
        return TextView(this).apply {
            text = value
            textSize = size
            setTextColor(color)
            gravity = Gravity.CENTER
            if (bold) setTypeface(null, Typeface.BOLD)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = bg
        window.navigationBarColor = bg

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(22, 22, 22, 24)
            setBackgroundColor(bg)
        }

        val scroll = ScrollView(this)
        scroll.addView(root)

        val header = LinearLayout(this).apply {
            gravity = Gravity.CENTER_VERTICAL
            orientation = LinearLayout.HORIZONTAL
        }

        val logo = text("♛", 35f, green, true)
        header.addView(logo)

        val brand = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(12, 0, 0, 0)
        }

        brand.addView(text("ALI X CHEATS", 20f, Color.WHITE, true))
        brand.addView(text("PREMIUM VPN", 11f, green, true))
        header.addView(brand)

        root.addView(header)

        val line = TextView(this).apply {
            setBackgroundColor(Color.rgb(35, 65, 39))
        }
        root.addView(line, LinearLayout.LayoutParams(
            -1, 1
        ).apply { setMargins(0, 22, 0, 25) })

        root.addView(text("YOUR CONNECTION", 13f, Color.LTGRAY, true))
        root.addView(text("Private. Fast. Secure.", 23f, Color.WHITE, true))

        root.addView(text(" ", 10f, Color.WHITE))

        status = text("●  NOT CONNECTED", 15f, Color.LTGRAY, true)
        root.addView(status)

        val connect = TextView(this).apply {
            text = "⏻"
            textSize = 52f
            gravity = Gravity.CENTER
            setTextColor(Color.BLACK)
            background = rounded(green, 200f)
            elevation = 12f
        }

        root.addView(connect, LinearLayout.LayoutParams(
            175, 175
        ).apply {
            gravity = Gravity.CENTER
            setMargins(0, 25, 0, 12)
        })

        root.addView(text("TAP TO CONNECT", 13f, green, true))

        connect.setOnClickListener {
            Toast.makeText(
                this,
                "VPN server will be added later!",
                Toast.LENGTH_LONG
            ).show()
        }

        root.addView(text(" ", 15f, Color.WHITE))
        root.addView(text("SELECT YOUR SERVER", 19f, Color.WHITE, true))
        root.addView(text("Choose your preferred location", 12f, Color.LTGRAY))

        serverList = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        root.addView(serverList)

        val countries = listOf(
            Triple("🇵🇰", "Pakistan", "35 ms"),
            Triple("🇸🇬", "Singapore", "65 ms"),
            Triple("🇩🇪", "Germany", "110 ms"),
            Triple("🇺🇸", "USA", "180 ms"),
            Triple("🇳🇱", "Netherlands", "125 ms")
        )

        countries.forEach { item ->
            val row = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
                setPadding(15, 12, 15, 12)
                background = rounded(
                    if (selected == item.second) Color.rgb(25, 48, 28) else card,
                    18f
                )
            }

            val flag = text(item.first, 28f, Color.WHITE)
            row.addView(flag)

            val name = text(item.second, 16f, Color.WHITE, true)
            name.gravity = Gravity.CENTER_VERTICAL
            name.setPadding(15, 0, 0, 0)

            row.addView(name, LinearLayout.LayoutParams(
                0, 55, 1f
            ))

            val ping = text(item.third, 12f, green)
            row.addView(ping)

            row.setOnClickListener {
                selected = item.second
                Toast.makeText(
                    this,
                    "$selected selected",
                    Toast.LENGTH_SHORT
                ).show()
                refreshServers(countries)
            }

            serverList.addView(row, LinearLayout.LayoutParams(
                -1, -2
            ).apply {
                setMargins(0, 9, 0, 5)
            })
        }

        root.addView(text(" ", 12f, Color.WHITE))
        root.addView(text(
            "ALI X CHEATS VPN  •  VERSION 1.0",
            11f, green, true
        ))

        setContentView(scroll)
    }

    private fun refreshServers(
        countries: List<Triple<String, String, String>>
    ) {
        serverList.removeAllViews()

        countries.forEach { item ->
            val row = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
                setPadding(15, 12, 15, 12)
                background = rounded(
                    if (selected == item.second) Color.rgb(25, 48, 28) else card,
                    18f
                )
            }

            row.addView(text(item.first, 28f, Color.WHITE))

            val name = text(item.second, 16f, Color.WHITE, true)
            name.gravity = Gravity.CENTER_VERTICAL
            name.setPadding(15, 0, 0, 0)
            row.addView(name, LinearLayout.LayoutParams(0, 55, 1f))

            row.addView(text(item.third, 12f, green))

            row.setOnClickListener {
                selected = item.second
                refreshServers(countries)
            }

            serverList.addView(row, LinearLayout.LayoutParams(
                -1, -2
            ).apply {
                setMargins(0, 9, 0, 5)
            })
        }
    }
}
