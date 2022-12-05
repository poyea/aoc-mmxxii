package poyea.aoc.mmxxii.day05

import poyea.aoc.utils.readInput

typealias Stack = ArrayDeque<Char>

data class InputData(val parts: List<String>, val allStacks: List<Stack>)

fun processInput(input: String): InputData {
    return input.let {
        val parts = input.split("\n\n")
        val stacks = parts[0].split("\n").dropLast(1)
        val allStacks = List(stacks.last().length / 4 + 1) { Stack() }
        stacks.forEach {
            it.chunked(4).forEachIndexed { index, s ->
                if (!s[1].isWhitespace()) {
                    allStacks[index].addLast(s[1])
                }
            }
        }
        InputData(parts, allStacks)
    }
}

fun part1(input: String): String {
    return input.let {
        val (parts, allStacks) = processInput(input)
        parts[1].split("\n").forEach {
            val tokens = it.split(" ")
            val count = tokens[1].toInt()
            val start = tokens[3].toInt()
            val end = tokens[5].toInt()
            for (i in 1..count) {
                allStacks[end - 1].addFirst(allStacks[start - 1].removeFirst())
            }
        }
        allStacks.map { it.removeFirst() }.joinToString("")
    }
}

fun part2(input: String): String {
    return input.let {
        val (parts, allStacks) = processInput(input)
        parts[1].split("\n").forEach {
            val tokens = it.split(" ")
            val count = tokens[1].toInt()
            val start = tokens[3].toInt()
            val end = tokens[5].toInt()
            for (element in allStacks[start - 1].take(count).reversed()) {
                allStacks[end - 1].addFirst(element)
                allStacks[start - 1].removeFirst()
            }
        }
        allStacks.map { it.removeFirst() }.joinToString("")
    }
}

fun main() {
    println(part1(readInput("Day05")))
    println(part2(readInput("Day05")))
}
