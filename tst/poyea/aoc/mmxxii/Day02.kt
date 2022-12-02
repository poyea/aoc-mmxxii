package poyea.aoc.mmxxii.day02

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day02")), 13446)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day02")), 13509)
    }
}
