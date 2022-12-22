package poyea.aoc.mmxxii.day22

import poyea.aoc.utils.readInput

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
}

sealed class Instruction {
    object Left : Instruction()

    object Right : Instruction()

    data class Move(val steps: Int) : Instruction()
}

enum class Dir(val left: () -> Dir, val right: () -> Dir, val offset: Point, val score: Int) {
    EAST(left = { NORTH }, right = { SOUTH }, offset = Point(1, 0), score = 0),
    SOUTH(left = { EAST }, right = { WEST }, offset = Point(0, 1), score = 1),
    WEST(left = { SOUTH }, right = { NORTH }, offset = Point(-1, 0), score = 2),
    NORTH(left = { WEST }, right = { EAST }, offset = Point(0, -1), score = 3),
}

fun calculate(y: Int, x: Int, dir: Dir): Int {
    return 1000 * (y + 1) + 4 * (x + 1) + dir.score
}

fun part1(input: String): Int {
    val lines = input.split("\n")
    val grid =
        lines
            .dropLast(2)
            .flatMapIndexed { y, line ->
                line.mapIndexedNotNull { x, c -> if (c == ' ') null else Point(x, y) to c }
            }
            .associate { it }

    val instructions =
        """\d+|[LR]"""
            .toRegex()
            .findAll(lines.last())
            .map {
                when (it.value) {
                    "L" -> Instruction.Left
                    "R" -> Instruction.Right
                    else -> Instruction.Move(it.value.toInt())
                }
            }
            .toList()

    var position = Point(grid.keys.filter { it.y == 0 }.minOf { it.x }, 0)
    var dir = Dir.EAST

    instructions.forEach { instruction ->
        when (instruction) {
            is Instruction.Left -> dir = dir.left()
            is Instruction.Right -> dir = dir.right()
            is Instruction.Move ->
                generateSequence(position to dir) { (p, d) ->
                        val next = p + d.offset
                        when {
                            next in grid && grid[next] == '#' -> p to d
                            next !in grid -> {
                                val rotatedDir = dir.right().right()
                                val (newPos, newDir) =
                                    generateSequence(position) { it + rotatedDir.offset }
                                        .takeWhile { it in grid }
                                        .last() to dir
                                if (grid[newPos] == '.') newPos to newDir else p to d
                            }
                            else -> next to d
                        }
                    }
                    .take(instruction.steps + 1)
                    .last()
                    .let { (p, d) ->
                        position = p
                        dir = d
                    }
        }
    }

    return calculate(position.y, position.x, dir)
}

fun part2(input: String): Int {
    val lines = input.split("\n")
    val grid =
        lines
            .dropLast(2)
            .flatMapIndexed { y, line ->
                line.mapIndexedNotNull { x, c -> if (c == ' ') null else Point(x, y) to c }
            }
            .associate { it }

    val instructions =
        """\d+|[LR]"""
            .toRegex()
            .findAll(lines.last())
            .map {
                when (it.value) {
                    "L" -> Instruction.Left
                    "R" -> Instruction.Right
                    else -> Instruction.Move(it.value.toInt())
                }
            }
            .toList()

    var position = Point(grid.keys.filter { it.y == 0 }.minOf { it.x }, 0)
    var dir = Dir.EAST

    instructions.forEach { instruction ->
        when (instruction) {
            is Instruction.Left -> dir = dir.left()
            is Instruction.Right -> dir = dir.right()
            is Instruction.Move ->
                generateSequence(position to dir) { (p, d) ->
                        val next = p + d.offset
                        when {
                            next in grid && grid[next] == '#' -> p to d
                            next !in grid -> {
                                val (newPos, newDir) =
                                    when (Triple(d, p.x / 50, p.y / 50)) {
                                        Triple(Dir.NORTH, 1, 0) ->
                                            Point(0, 100 + p.x) to Dir.EAST
                                        Triple(Dir.WEST, 1, 0) ->
                                            Point(0, 149 - p.y) to Dir.EAST
                                        Triple(Dir.NORTH, 2, 0) ->
                                            Point(p.x - 100, 199) to Dir.NORTH
                                        Triple(Dir.EAST, 2, 0) ->
                                            Point(99, 149 - p.y) to Dir.WEST
                                        Triple(Dir.SOUTH, 2, 0) ->
                                            Point(99, -50 + p.x) to Dir.WEST
                                        Triple(Dir.EAST, 1, 1) ->
                                            Point(50 + p.y, 49) to Dir.NORTH
                                        Triple(Dir.WEST, 1, 1) ->
                                            Point(p.y - 50, 100) to Dir.SOUTH
                                        Triple(Dir.NORTH, 0, 2) ->
                                            Point(50, p.x + 50) to Dir.EAST
                                        Triple(Dir.WEST, 0, 2) ->
                                            Point(50, 149 - p.y) to Dir.EAST
                                        Triple(Dir.EAST, 1, 2) ->
                                            Point(149, 149 - p.y) to Dir.WEST
                                        Triple(Dir.SOUTH, 1, 2) ->
                                            Point(49, 100 + p.x) to Dir.WEST
                                        Triple(Dir.EAST, 0, 3) ->
                                            Point(p.y - 100, 149) to Dir.NORTH
                                        Triple(Dir.SOUTH, 0, 3) ->
                                            Point(p.x + 100, 0) to Dir.SOUTH
                                        Triple(Dir.WEST, 0, 3) ->
                                            Point(p.y - 100, 0) to Dir.SOUTH
                                        else -> Point(-99999, -99999) to Dir.EAST
                                    }
                                if (grid[newPos] == '.') newPos to newDir else p to d
                            }
                            else -> next to d
                        }
                    }
                    .take(instruction.steps + 1)
                    .last()
                    .let { (p, d) ->
                        position = p
                        dir = d
                    }
        }
    }

    return calculate(position.y, position.x, dir)
}

fun main() {
    println(part1(readInput("Day22")))
    println(part2(readInput("Day22")))
}
