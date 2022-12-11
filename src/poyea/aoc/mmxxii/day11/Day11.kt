package poyea.aoc.mmxxii.day11

import poyea.aoc.utils.readInput

fun simulate(input: String, rounds: Int): Long {
    val monkeys = input.split("\n\n").map { it.split("\n") }
    val items = monkeys.map {
        it[1].split(":")[1].split(",").map { iy -> iy.trim().toLong() }.toMutableList()
    }
    val ops = monkeys.map { it[2].split(":")[1].trim().split(" = ")[1] }
    val tests = monkeys.map { it[3].split("Test: divisible by ")[1] }.map { it.toLong() }
    val mod = tests.reduce(Long::times) // this is... basically LCM of tests
    val trueMonkeys = monkeys.map { it[4].split("If true: throw to monkey ")[1] }.map { it.toInt() }
    val falseMonkeys = monkeys.map { it[5].split("If false: throw to monkey ")[1] }.map { it.toInt() }
    val timesInspected = MutableList(trueMonkeys.size) { 0L }

    for (round in 1..rounds) {
        for ((i, monkeyItems) in items.withIndex()) {
            fun applyOperator(n: Long): Long {
                val op = ops[i].split(" ")
                return when (op[2]) {
                    "old" -> n * n
                    else -> {
                        if (op[1] == "*") {
                            n * op[2].toLong()
                        } else {
                            n + op[2].toLong()
                        }
                    }
                }
            }
            for (item: Long in monkeyItems) {
                val currentLevel = if (rounds > 2023) applyOperator(item) % mod else applyOperator(item) / 3L
                if ((currentLevel % tests[i]) == 0L) {
                    items[trueMonkeys[i]].add(currentLevel)
                } else {
                    items[falseMonkeys[i]].add(currentLevel)
                }
            }
            timesInspected[i] = timesInspected[i] + monkeyItems.size
            monkeyItems.clear()
        }
    }
    return timesInspected.sortedDescending().take(2).reduce { acc, i -> acc * i }
}

fun part1(input: String): Long {
    return simulate(input, 20)
}

fun part2(input: String): Long {
    return simulate(input, 10000)
}

fun main() {
    println(part1(readInput("Day11")))
    println(part2(readInput("Day11")))
}
