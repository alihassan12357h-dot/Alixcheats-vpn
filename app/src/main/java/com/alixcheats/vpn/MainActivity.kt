
package com.alixcheats.vpn

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.*
import android.view.View

class MainActivity : Activity() {

    private val red = Color.rgb(255, 35, 55)
    private val bg = Color.rgb(9, 9, 12)
    private val card = Color.rgb(23, 22, 27)
    private var selected = "Pakistan"
    private var connected = false

    private lateinit var status: TextView
    private lateinit var serverBox: LinearLayout
    private lateinit var connect: TextView

    private val countries = listOf(
        Triple("🇵🇰", "Pakistan", "35 ms"),
        Triple("🇸🇬", "Singapore", "65 ms"),
        Triple("🇩🇪", "Germany", "110 ms"),
        Triple("🇺🇸", "USA", "180 ms"),
        Triple("🇳🇱", "Netherlands", "125 ms")
    )

    private fun shape(color: Int, radius: Float): GradientDrawable {
        return GradientDrawable().apply {
            setColor(color)
            cornerRadius = radius
        }
    }

    private fun gradient(): GradientDrawable {
        return GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            intArrayOf(
                Color.rgb(75, 8, 20),
                Color.rgb(28, 10, 17),
                Color.rgb(15, 15, 20)
            )
        ).apply {
            cornerRadius = 28f
            setStroke(1, Color.rgb(130, 25, 42))
        }
    }

    private fun makeText(
        value: String,
        size: Float,
        color: Int,
        bold: Boolean = false
    ): TextView {
        return TextView(this).apply {
            text = value
            textSize = size
            setTextColor(color)
            gravity = Gravity.CENTER_VERTICAL
            if (bold) setTypeface(null, Typeface.BOLD)
        }
    }

    private fun addSpace(parent: LinearLayout, height: Int) {
        parent.addView(View(this), LinearLayout.LayoutParams(1, height))
    }

    private fun addLabel(parent: LinearLayout, value: String) {
        val t = makeText(value, 17f, Color.WHITE, true)
        parent.addView(t)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = bg
        window.navigationBarColor = bg

        val scroll = ScrollView(this)
        scroll.setBackgroundColor(bg)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 22, 20, 25)
        }

        scroll.addView(root)

        // HEADER
        val header = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }

        val logo = TextView(this).apply {
            text = "♛"
            textSize = 38f
            setTextColor(red)
            gravity = Gravity.CENTER
        }

        header.addView(logo)

        val brand = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(12, 0, 0, 0)
        }

        brand.addView(makeText("ALI X CHEATS", 21f, Color.WHITE, true))
        brand.addView(makeText("RED DEMON EDITION", 10f, red, true))

        header.addView(brand)

        val version = TextView(this).apply {
            text = "V1.0"
            textSize = 11f
            setTextColor(red)
            gravity = Gravity.CENTER
            background = shape(card, 12f)
            setPadding(14, 10, 14, 10)
        }

        header.addView(version, LinearLayout.LayoutParams(
            -2, -2
        ).apply {
            gravity = Gravity.CENTER_VERTICAL
            weight = 1f
        })

        root.addView(header)

        addSpace(root, 25)

        // HERO BANNER
        val hero = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            background = gradient()
            setPadding(18, 24, 18, 24)
        }

        hero.addView(makeText(
            "NEXT LEVEL GAMING",
            12f, red, true
        ).apply { gravity = Gravity.CENTER })

        addSpace(hero, 10)

        hero.addView(makeText(
            "ENTER THE BATTLE",
            27f, Color.WHITE, true
        ).apply { gravity = Gravity.CENTER })

        hero.addView(makeText(
            "YOUR CONNECTION. YOUR CONTROL.",
            11f, Color.LTGRAY, true
        ).apply {
            gravity = Gravity.CENTER
            setPadding(0, 8, 0, 0)
        })

        root.addView(hero, LinearLayout.LayoutParams(
            -1, -2
        ))

        addSpace(root, 25)

        // CONNECTION SECTION
        addLabel(root, "YOUR VPN CONNECTION")
        addSpace(root, 12)

        val connectionCard = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            background = shape(card, 25f)
            setPadding(18, 22, 18, 22)
        }

        status = makeText(
            "●  NOT CONNECTED",
            14f, Color.LTGRAY, true
        ).apply { gravity = Gravity.CENTER }

        connectionCard.addView(status)

        addSpace(connectionCard, 24)

        connect = TextView(this).apply {
            text = "⏻"
            textSize = 53f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            background = GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                intArrayOf(
                    Color.rgb(255, 45, 65),
                    Color.rgb(130, 0, 25)
                )
            ).apply {
                shape = GradientDrawable.OVAL
                setStroke(3, Color.rgb(255, 95, 105))
            }
            elevation = 15f
        }

        connectionCard.addView(connect, LinearLayout.LayoutParams(
            145, 145
        ).apply {
            gravity = Gravity.CENTER
        })

        addSpace(connectionCard, 18)

        connectionCard.addView(makeText(
            "TAP TO CONNECT",
            14f, red, true
        ).apply { gravity = Gravity.CENTER })

        addSpace(connectionCard, 8)

        connectionCard.addView(makeText(
            "SELECT A SERVER TO GET STARTED",
            10f, Color.LTGRAY
        ).apply { gravity = Gravity.CENTER })

        connect.setOnClickListener {
            Toast.makeText(
                this,
                "VPN servers will be added later!",
                Toast.LENGTH_LONG
            ).show()
        }

        root.addView(connectionCard)

        addSpace(root, 28)

        // SERVER SECTION
        addLabel(root, "CHOOSE YOUR SERVER")

        addSpace(root, 5)

        root.addView(makeText(
            "Select your preferred location",
            12f, Color.LTGRAY
        ))

        addSpace(root, 15)

        serverBox = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        root.addView(serverBox)

        refreshServers()

        addSpace(root, 25)

        // FOOTER
        val footer = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            background = shape(card, 20f)
            setPadding(15, 18, 15, 18)
        }

        footer.addView(makeText(
            "♛  ALI X CHEATS VPN",
            16f, red, true
        ).apply { gravity = Gravity.CENTER })

        footer.addView(makeText(
            "NEXT LEVEL GAMING • RED DEMON EDITION",
            9f, Color.LTGRAY
        ).apply {
            gravity = Gravity.CENTER
            setPadding(0, 8, 0, 0)
        })

        root.addView(footer)

        setContentView(scroll)
    }

    private fun refreshServers() {
        serverBox.removeAllViews()

        countries.forEach { item ->

            val active = selected == item.second

            val row = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
                setPadding(15, 12, 15, 12)

                background = shape(
                    if (active) Color.rgb(65, 17, 28) else card,
                    18f
                )

                if (active) {
                    background = GradientDrawable().apply {
                        setColor(Color.rgb(65, 17, 28))
                        cornerRadius = 18f
                        setStroke(2, red)
                    }
                }
            }

            row.addView(makeText(
                item.first, 28f, Color.WHITE
            ))

            val info = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(13, 0, 0, 0)
            }

            info.addView(makeText(
                item.second, 16f, Color.WHITE, true
            ))

            info.addView(makeText(
                "VPN SERVER",
                9f, Color.LTGRAY
            ))

            row.addView(info, LinearLayout.LayoutParams(
                0, -2, 1f
            ))

            row.addView(makeText(
                item.third,
                12f, red, true
            ))

            row.setOnClickListener {
                selected = item.second
                refreshServers()
            }

            serverBox.addView(row, LinearLayout.LayoutParams(
                -1, -2
            ).apply {
                setMargins(0, 0, 0, 12)
            })
        }
    }
}
