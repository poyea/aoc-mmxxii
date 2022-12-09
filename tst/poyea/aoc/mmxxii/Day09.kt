package poyea.aoc.mmxxii.day09

import kotlin.test.Test
import kotlin.test.assertEquals
import poyea.aoc.utils.readInput as readInput

class Day09Test {
    @Test fun part1Ans() {
        assertEquals(part1(readInput("Day09")), 6023)
    }

    @Test fun part2Ans() {
        assertEquals(part2(readInput("Day09")), 2533)
    }
}
