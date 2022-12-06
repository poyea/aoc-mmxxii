package poyea.aoc.mmxxii.day06

import kotlin.test.Test
import kotlin.test.assertEquals
import poyea.aoc.utils.readInput as readInput

class Day06Test {
    @Test fun part1Ans() {
        assertEquals(part1(readInput("Day06")), 1080)
    }

    @Test fun part2Ans() {
        assertEquals(part2(readInput("Day06")), 3645)
    }
}
