package poyea.aoc.utils

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads from the given input txt file.
 */
fun readInput(name: String) = File("src/poyea/aoc/data", "$name.txt").readText(Charsets.UTF_8)

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
