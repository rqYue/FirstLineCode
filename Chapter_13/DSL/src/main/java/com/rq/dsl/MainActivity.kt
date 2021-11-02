package com.rq.dsl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var libs = Dependency().dependencies {
            implementation("com.squareup.retrofit2:retrofit:2.9.0")
            implementation("com.squareup.retrofit2:converter-gson:2.9.0")
        }

        for (lib in libs) {
            println(lib)
        }

        main()
    }

//    <table> <tr>
//    <td>Apple</td>
//    <td>Grape</td>
//    <td>Orange</td>
//    </tr> <tr>
//    <td>Pear</td>
//    <td>Banana</td>
//    <td>Watermelon</td>
//    </tr>
//    </table>

    fun main() {
        val html = Table().table {
            tr {
                td { "Apple" }
                td { "Grape" }
                td { "Orange" }
            }

            tr {
                td { "Pear" }
                td { "Banana" }
                td { "Watermelon" }
            }
        }
        println(html)
    }

    fun main1() {
        val html = Table().table {
            repeat(2) {
                tr {
                    val fruits = listOf("Apple", "Grape", "Orange")
                    for (fruit in fruits) {
                        td { fruit }
                    }
                }
            }
        }
        println(html)
    }

}