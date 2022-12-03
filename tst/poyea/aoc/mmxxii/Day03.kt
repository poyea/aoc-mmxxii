package poyea.aoc.mmxxii.day03

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day03")), 7428)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day03")), 2650)
    }
}
