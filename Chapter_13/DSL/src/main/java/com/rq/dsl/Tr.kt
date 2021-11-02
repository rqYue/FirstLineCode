package com.rq.dsl

import java.lang.StringBuilder

class Tr {

    private val children = ArrayList<Td>()

    fun td(block: Td.() -> String) {
        val td = Td()
        td.content = td.block()
        children.add(td)
    }

    fun html(): String {
        val builder = StringBuilder()
        builder.append("\n\t<tr>")
        for (childTag in children) {
            builder.append(childTag.html())
        }

        builder.append("/n/t/tr>")
        return builder.toString()
    }
}