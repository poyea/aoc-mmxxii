package poyea.aoc.mmxxii.day10

import poyea.aoc.utils.readInput

private const val WIDTH = 40

fun part1(input: String): Int {
    var cycle = 1
    var register = 1
    var sum = 0
    var flags = 0
    input.split("\n").map { it.split(" ") }.forEach {
        when (it[0]) {
            "noop" -> cycle += 1
            else -> {
                for (j in 0..1) {
                    cycle += 1
                    if (j == 1) {
                        register += it[1].toInt()
                    }
                    for (i in 0..5) {
                        if (cycle >= 20 + i * 40 && (flags and (1 shl i)) == 0) {
                            sum += (20 + i * 40) * register
                            flags = flags or (1 shl i)
                        }
                    }
                }

            }
        }
    }
    return sum
}

fun part2(input: String): String {
    var cycle = 1
    var register = 1
    var crtImage = ""
    input.split("\n").map { it.split(" ") }.forEach {
        when (it[0]) {
            "noop" -> {
                crtImage += if ((cycle - 1) % WIDTH in register - 1..register + 1) "#" else "."
                crtImage += if (cycle % WIDTH == 0) "\n" else ""
                cycle += 1
            }

            else -> {
                for (j in 0..1) {
                    crtImage += if ((cycle - 1) % WIDTH in register - 1..register + 1) "#" else "."
                    crtImage += if (cycle % WIDTH == 0) "\n" else ""
                    if (j == 1) {
                        register += it[1].toInt()
                    }
                    cycle += 1
                }
            }
        }
    }
    return crtImage
}

fun main() {
    println(part1(readInput("Day10")))
    println(part2(readInput("Day10")))
}
