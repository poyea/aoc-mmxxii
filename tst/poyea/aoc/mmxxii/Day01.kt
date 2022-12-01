package poyea.aoc.mmxxii.day01

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day01")), 66186)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day01")), 196804)
    }
}
