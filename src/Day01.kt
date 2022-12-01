import java.util.*
import kotlin.math.max

fun main() {

    fun part1(input: List<String>): Int {
        var max = 0;

        var cur = 0;
        for (line in input) {
            if (line.isBlank()) {
                max = max(max, cur);
                cur = 0;
            } else {
                cur += line.toInt();
            }
        }
        max = max(max, cur);

        return max;
    }

    fun part2(input: List<String>): Int {
        val pq = PriorityQueue<Int>();

        var cur = 0;
        for (line in input) {
            if (line.isBlank()) {
                pq.offer(cur);
                if (pq.size > 3) {
                    pq.poll();
                }
                cur = 0;
            } else {
                cur += line.toInt();
            }
        }
        pq.offer(cur);
        if (pq.size > 3) {
            pq.poll();
        }

        var sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }

        return sum;
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
