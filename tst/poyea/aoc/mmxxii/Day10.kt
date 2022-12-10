package poyea.aoc.mmxxii.day10

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day10")), 14820)
    }

    @Test
    fun part2Ans() {
        assertEquals(
            part2(readInput("Day10")),
            "###..####.####.#..#.####.####.#..#..##..\n" +
                    "#..#....#.#....#.#..#....#....#..#.#..#.\n" +
                    "#..#...#..###..##...###..###..####.#..#.\n" +
                    "###...#...#....#.#..#....#....#..#.####.\n" +
                    "#.#..#....#....#.#..#....#....#..#.#..#.\n" +
                    "#..#.####.####.#..#.####.#....#..#.#..#.\n"
        )
    }
}
