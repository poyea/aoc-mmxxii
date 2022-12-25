package poyea.aoc.mmxxii.day25

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day25Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day25")), "122-12==0-01=00-0=02")
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(), "*")
    }
}
