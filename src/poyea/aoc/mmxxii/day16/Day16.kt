package poyea.aoc.mmxxii.day16

import poyea.aoc.utils.readInput

data class State(val current: String, val minutes: Int, val opened: Set<Valve>)

data class Valve(val from: String, val to: List<String>, val rate: Int) {
    companion object {
        fun from(line: String): Valve {
            return Valve(
                from = line.substringAfter("Valve ").substringBefore(" "),
                to = line.substringAfter("to valve").substringAfter(" ").split(", "),
                rate = line.substringAfter("rate=").substringBefore(";").toInt()
            )
        }
    }
}

fun findDistances(valves: Map<String, Valve>): Map<String, Map<String, Int>> {
    return valves.keys.map { valve ->
        val distances = mutableMapOf<String, Int>().withDefault { Int.MAX_VALUE }.apply { put(valve, 0) }
        val queue = mutableListOf(valve)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            valves[current]!!.to.forEach { neighbour ->
                val dist = distances[current]!! + 1
                if (dist < distances.getValue(neighbour)) {
                    distances[neighbour] = dist
                    queue.add(neighbour)
                }
            }
        }
        distances
    }.associateBy { it.keys.first() }
}

fun traverse(
    minutes: Int,
    start: Valve,
    current: Valve,
    remaining: Set<Valve>,
    cache: MutableMap<State, Int>,
    elephant: Boolean,
    distances: Map<String, Map<String, Int>>
): Int {
    val pressure = minutes * current.rate
    val currentState = State(current.from, minutes, remaining)

    return pressure + cache.getOrPut(currentState) {
        val maxCurrent = remaining
            .filter { to -> distances[current.from]!![to.from]!! < minutes }
            .takeIf { it.isNotEmpty() }
            ?.maxOf { to ->
                val remainingMinutes = minutes - 1 - distances[current.from]!![to.from]!!
                traverse(remainingMinutes, start, to, remaining - to, cache, elephant, distances)
            }
            ?: 0
        maxOf(maxCurrent, if (elephant) traverse(26, start, start, remaining, mutableMapOf(), false, distances) else 0)
    }
}

fun part1(input: String): Int {
    val valves = input.split("\n").map(Valve::from).associateBy(Valve::from)
    val usefulValves = valves.filter { it.value.rate > 0 }
    val distances = findDistances(valves)

    return traverse(30, valves.getValue("AA"), valves.getValue("AA"), usefulValves.values.toSet(), mutableMapOf(), false, distances)
}

fun part2(input: String): Int {
    val valves = input.split("\n").map(Valve::from).associateBy(Valve::from)
    val usefulValves = valves.filter { it.value.rate > 0 }
    val distances = findDistances(valves)

    return traverse(26, valves.getValue("AA"), valves.getValue("AA"), usefulValves.values.toSet(), mutableMapOf(), true, distances)
}

fun main() {
    println(part1(readInput("Day16")))
    println(part2(readInput("Day16")))
}
