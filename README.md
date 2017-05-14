Landscape Water Capacity Calculator
===================================

This is a fast parallel Kotlin solution for the problem presented in this talk: https://youtu.be/ftcIcn8AmSY

This is a port of a [Java solution](https://github.com/davidleston/landscapeWaterCapacity).
This solution uses coroutines instead of a parallel stream of Suppliers.
Also used is an extension method to provide a sum method for Stream<BigDecimal> similar to the sum method on IntSream.
