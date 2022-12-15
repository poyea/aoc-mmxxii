package poyea.aoc.mmxxii.day15

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day15")), 5838453)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day15")), 12413999391794)
    }
}
