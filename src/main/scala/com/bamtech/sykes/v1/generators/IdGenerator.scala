package com.bamtech.sykes.v1.generators

import org.scalacheck.Gen

object IdGenerator {

  private def validId: Gen[String] = Gen.uuid.map(_.toString)

  private val invalidId: Gen[String] = StringGenerator.randomAlphanumeric

  val randomId: Gen[String] = Gen.oneOf(
    validId,
    invalidId
  )

}
