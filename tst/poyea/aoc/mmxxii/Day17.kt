package poyea.aoc.mmxxii.day17

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day17")), 3071)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day17")), 1523615160362)
    }
}
