package poyea.aoc.mmxxii.day21

import poyea.aoc.utils.readInput

fun part1(input: String): Long {
    val numberOf = mutableMapOf<String, Long>()
    val children = mutableMapOf<String, Pair<String, String>>()
    val operations = mutableMapOf<String, Char>()

    input.split("\n").forEach {
        val (name, rest) = it.split(": ")
        if (rest.any { it.isDigit() }) numberOf[name] = rest.toLong()
        else {
            children[name] = rest.take(4) to rest.takeLast(4)
            operations[name] = rest[5]
        }
    }

    fun traverse(monkey: String): Long {
        return if (monkey in numberOf) numberOf[monkey]!!
        else {
            val (left, right) = children[monkey]!!
            val k1 = traverse(left)
            val k2 = traverse(right)
            numberOf[left] = k1
            numberOf[right] = k2
            when (operations[monkey]!!) {
                '+'  -> k1 + k2
                '-'  -> k1 - k2
                '*'  -> k1 * k2
                else -> k1 / k2
            }
        }
    }

    return traverse("root")
}

fun part2(input: String): Long {
    val numberOf = mutableMapOf<String, Long>()
    val children = mutableMapOf<String, Pair<String, String>>()
    val operations = mutableMapOf<String, Char>()

    input.split("\n").forEach {
        val (name, rest) = it.split(": ")
        if (rest.any { it.isDigit() }) numberOf[name] = rest.toLong()
        else {
            children[name] = rest.take(4) to rest.takeLast(4)
            operations[name] = rest[5]
        }
    }

    var trailToHuman = listOf<String>()

    fun traverse(monkey: String, trail: List<String> = listOf()): Long {
        if (monkey == "humn") trailToHuman = trail.drop(1) + "humn"
        return if (monkey in numberOf) numberOf[monkey]!!
        else {
            val (left, right) = children[monkey]!!
            val k1 = traverse(left, trail + monkey)
            val k2 = traverse(right, trail + monkey)
            numberOf[left] = k1
            numberOf[right] = k2
            when (operations[monkey]!!) {
                '+' -> k1 + k2
                '-' -> k1 - k2
                '*' -> k1 * k2
                else -> k1 / k2
            }
        }
    }

    traverse("root")

    val (p1, p2) = children["root"]!!
    val toEqual = if (p1 == trailToHuman.first()) numberOf[p2]!! else numberOf[p1]!!

    val output = trailToHuman.windowed(2).fold(initial = toEqual) { acc, (monkey, next) ->
        val (child1, child2) = children[monkey]!!
        val other = if (child1 == next) numberOf[child2]!! else numberOf[child1]!!
        val isLeft = children[monkey]?.second == next
        when (operations[monkey]!!) {
            '+' -> acc - other
            '-' -> if (isLeft) other - acc else acc + other
            '*' -> acc/other
            else -> if (isLeft) other/acc else acc*other
        }
    }

    return output
}

fun main() {
    println(part1(readInput("Day21")))
    println(part2(readInput("Day21")))
}
