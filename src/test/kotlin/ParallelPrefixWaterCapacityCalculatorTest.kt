import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import java.math.BigInteger
import org.junit.jupiter.api.Assertions.*
import java.util.*

object ParallelPrefixWaterCapacityCalculatorSpec : Spek({
    it("empty") {
        assertEquals(BigInteger.ZERO, fromHeights())
    }
    it("one value") {
        assertEquals(BigInteger.ZERO, fromHeights(42))
    }
    it("one duplicate value") {
        assertEquals(BigInteger.ZERO, fromHeights(42, 42))
    }
    it("two values") {
        assertEquals(BigInteger.ZERO, fromHeights(1, 2))
    }
    it("smallest valley") {
        assertEquals(BigInteger.ONE, fromHeights(2, 1, 2))
    }
    it("smallest mountain") {
        assertEquals(BigInteger.ZERO, fromHeights(1, 2, 1))
    }
    it("equal peaks") {
        assertEquals(BigInteger.valueOf(2), fromHeights(1, 2, 1, 1, 2, 1))
    }
    it("performance test") {
        val random = Random(1)
        val heights = LongArray(10_000_000)
        for (i in heights.indices) {
            heights[i] = Math.abs(random.nextLong())
        }
        val volumeOfWater = fromHeights(*heights)
        println(volumeOfWater)
    }
})