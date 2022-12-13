package poyea.aoc.mmxxii.day13

import kotlin.collections.MutableList
import poyea.aoc.utils.readInput

fun wrap(list: MutableList<Char>, len: Int): MutableList<Char> {
    list.add(len, ']')
    list.add(0, '[')
    return list
}

fun isInOrder(l: MutableList<Char>, r: MutableList<Char>): Boolean {
    var left = l
    var right = r
    var left_number =
        if (!left.first().isDigit()) null
        else left.takeWhile { it.isDigit() }.joinToString("").toInt()
    var right_number =
        if (!right.first().isDigit()) null
        else right.takeWhile { it.isDigit() }.joinToString("").toInt()

    if (left[0] == '[' && right_number != null) right = wrap(right, 1 + right_number / 10)
    if (right[0] == '[' && left_number != null) left = wrap(left, 1 + left_number / 10)
    return when {
        left[0] == ']' && right[0] != ']' -> true
        left[0] != ']' && right[0] == ']' -> false
        left_number == (right_number ?: -1) ->
            isInOrder(
                left.subList(1 + left_number / 10, left.size),
                right.subList(1 + right_number!! / 10, right.size)
            )
        left_number != null && right_number != null -> left_number < right_number
        else -> isInOrder(left.subList(1, left.size), right.subList(1, right.size))
    }
}

fun compare(s1: String, s2: String): Int {
    return if (isInOrder(s1.toMutableList(), s2.toMutableList())) -1 else 1
}

fun part1(input: String): Int {
    return input
        .split("\n")
        .windowed(2, 3)
        .mapIndexed { i, packets ->
            if (compare(packets.first(), packets.last()) != 1) i + 1 else 0
        }
        .sum()
}

fun part2(input: String): Int {
    val lst = input.split("\n").windowed(2, 3)
    val comp: (String, String) -> Int = { s1: String, s2: String ->
        if (isInOrder(s1.toMutableList(), s2.toMutableList())) -1 else 1
    }
    val sorted = (lst.flatten() + "[[2]]" + "[[6]]").sortedWith({ s1, s2 -> compare(s1, s2) })
    return (1 + sorted.indexOf("[[2]]")) * (1 + sorted.indexOf("[[6]]"))
}

fun main() {
    println(part1(readInput("Day13")))
    println(part2(readInput("Day13")))
}
