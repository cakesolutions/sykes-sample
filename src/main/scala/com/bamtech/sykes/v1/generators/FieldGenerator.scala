package com.bamtech.sykes.v1.generators

import org.scalacheck.{Arbitrary, Gen}

object FieldGenerator {

  private val defaultStringGen = StringGenerator.randomAlphanumeric
  private val defaultIntGen = Arbitrary.arbInt.arbitrary

  private val stringGeneratorsByFieldName = Map(
    "date" -> DateGenerator.randomDate,
    "id" -> IdGenerator.randomId
  )

  private val intGeneratorsByFieldName = Map(
    "stars" -> RatingGenerator.randomRating
  )

  def randomStringField(fieldName: String): Gen[String] =
    stringGeneratorsByFieldName
      .getOrElse(fieldName, defaultStringGen)

  def randomIntField(fieldName: String): Gen[Int] =
    intGeneratorsByFieldName
      .getOrElse(fieldName, defaultIntGen)

}
