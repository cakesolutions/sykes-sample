package com.bamtech.sykes.v1.generators

import org.scalacheck.Gen

object StringGenerator {

  private val numDigitGen = Gen.choose('0', '9')
  private val alphaDigitGen = Gen.oneOf(
    Gen.choose('A', 'Z'),
    Gen.choose('a', 'z')
  )

  val randomAlphanumeric: Gen[String] = for {
    length <- Gen.choose(1, 40)
    chars <- Gen.listOfN(length, Gen.frequency(
      (1, numDigitGen),
      (4, alphaDigitGen)
    ))
  } yield chars.mkString

}
