package poyea.aoc.mmxxii.day12

import kotlin.Int.Companion.MAX_VALUE
import kotlin.collections.ArrayDeque
import kotlin.collections.HashSet
import poyea.aoc.utils.readInput

fun find_start(grid: List<CharArray>, pred: (c: Char) -> Boolean): List<Pair<Int, Int>> {
    val start_points = mutableListOf<Pair<Int, Int>>()
    grid.forEachIndexed { i, it ->
        it.forEachIndexed { j, ij ->
            if (pred(ij)) {
                start_points.add(Pair(i, j))
            }
        }
    }
    return start_points
}

fun inside(grid: List<CharArray>, point: Pair<Int, Int>): Boolean {
    return point.first >= 0 &&
        point.second >= 0 &&
        point.first < grid.size &&
        point.second < grid[0].size
}

fun get_neighbour(grid: List<CharArray>, point: Pair<Int, Int>): List<Pair<Int, Int>> {
    val lst = mutableListOf<Pair<Int, Int>>()
    val (x, y) = point
    if (inside(grid, Pair(x + 1, y))) {
        lst.add(Pair(x + 1, y))
    }
    if (inside(grid, Pair(x - 1, y))) {
        lst.add(Pair(x - 1, y))
    }
    if (inside(grid, Pair(x, y + 1))) {
        lst.add(Pair(x, y + 1))
    }
    if (inside(grid, Pair(x, y - 1))) {
        lst.add(Pair(x, y - 1))
    }
    return lst
}

fun get_steps(grid: List<CharArray>, point: Pair<Int, Int>): Int {
    val q = ArrayDeque<Pair<Int, Int>>()
    val s = ArrayDeque<Int>()
    val vis = HashSet<Pair<Int, Int>>()

    q.addFirst(point)
    s.addFirst(0)
    while (q.isNotEmpty()) {
        val current = q.removeFirst()
        val current_steps = s.removeFirst()

        if (vis.contains(current)) {
            continue
        }

        vis.add(current)
        var current_height = grid[current.first][current.second]
        if (current_height == 'E') return current_steps
        if (current_height == 'S') current_height = 'a'

        get_neighbour(grid, current).forEach {
            val height = grid[it.first][it.second]
            if (height <= current_height + 1 && !vis.contains(it)) {
                if (height != 'E' || current_height == 'z') {
                    q.addLast(it)
                    s.addLast(current_steps + 1)
                }
            }
        }
    }
    return MAX_VALUE
}

fun part1(input: String): Int {
    val grid = input.split('\n').map { it -> it.toCharArray() }
    val start = find_start(grid, { c -> c == 'S' })
    return get_steps(grid, start[0])
}

fun part2(input: String): Int {
    val grid = input.split('\n').map { it -> it.toCharArray() }
    val lst = find_start(grid, { c -> c == 'S' || c == 'a' })
    return lst.map { get_steps(grid, it) }.min()
}

fun main() {
    println(part1(readInput("Day12")))
    println(part2(readInput("Day12")))
}
