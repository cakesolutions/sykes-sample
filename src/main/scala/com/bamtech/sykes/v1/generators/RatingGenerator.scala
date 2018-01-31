package com.bamtech.sykes.v1.generators

import org.scalacheck.Gen

object RatingGenerator {

  private val validRating: Gen[Int] = Gen.choose(0, 5)

  private val invalidRating: Gen[Int] = Gen.choose(-9000, 9000)

  val randomRating: Gen[Int] = Gen.frequency(
    1 -> invalidRating,
    3 -> validRating
  )

}
