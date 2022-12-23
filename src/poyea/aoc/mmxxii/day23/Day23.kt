package poyea.aoc.mmxxii.day23

import poyea.aoc.utils.readInput

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
}

enum class Direction(val neighbours: (Point) -> Set<Point>, val destination: (Point) -> Point) {
    EAST(
        neighbours = { (x, y) -> setOf(Point(x + 1, y - 1), Point(x + 1, y), Point(x + 1, y + 1)) },
        destination = { (x, y) -> Point(x + 1, y) }
    ),
    SOUTH(
        neighbours = { (x, y) -> setOf(Point(x - 1, y + 1), Point(x, y + 1), Point(x + 1, y + 1)) },
        destination = { (x, y) -> Point(x, y + 1) }
    ),
    WEST(
        neighbours = { (x, y) -> setOf(Point(x - 1, y - 1), Point(x - 1, y), Point(x - 1, y + 1)) },
        destination = { (x, y) -> Point(x - 1, y) }
    ),
    NORTH(
        neighbours = { (x, y) -> setOf(Point(x - 1, y - 1), Point(x, y - 1), Point(x + 1, y - 1)) },
        destination = { (x, y) -> Point(x, y - 1) }
    )
}

data class Grid(val elves: Set<Point>, val directions: List<Direction>) {
    fun performRound(): Grid {
        val (nonMovers, movers) =
            elves.partition { elf ->
                directions.none { dir -> dir.neighbours(elf).any(elves::contains) }
            }
        val propositions =
            movers.associateWith { elf ->
                directions
                    .firstOrNull { dir -> dir.neighbours(elf).none(elves::contains) }
                    ?.destination
                    ?.invoke(elf) ?: elf
            }
        val validPropositions =
            propositions.values.groupingBy { it }.eachCount().filter { it.value == 1 }.keys
        val movedOrLeft =
            propositions.map { (current, next) -> if (next in validPropositions) next else current }

        return Grid(
            elves = nonMovers union movedOrLeft,
            directions = directions.drop(1) + directions.first()
        )
    }

    fun countEmptyTiles(): Int {
        val min = Point(elves.minOf { it.x }, elves.minOf { it.y })
        val max = Point(elves.maxOf { it.x }, elves.maxOf { it.y })
        val width = max.x - min.x + 1
        val height = max.y - min.y + 1
        return (width * height) - elves.size
    }

    companion object {
        fun from(input: List<String>): Grid {
            return Grid(
                elves =
                    input
                        .flatMapIndexed { y, line ->
                            line.mapIndexedNotNull { x, c -> if (c != '#') null else Point(x, y) }
                        }
                        .toSet(),
                directions =
                    listOf(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST)
            )
        }
    }
}

fun part1(input: String): Int {
    return generateSequence(Grid.from(input.lines()), Grid::performRound).elementAt(10).countEmptyTiles()
}

fun part2(input: String): Int {
    return generateSequence(Grid.from(input.lines()), Grid::performRound)
        .zipWithNext()
        .takeWhile { it.first.elves != it.second.elves }
        .count() + 1
}

fun main() {
    println(part1(readInput("Day23")))
    println(part2(readInput("Day23")))
}
