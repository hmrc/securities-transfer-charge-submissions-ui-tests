/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.util

import uk.gov.hmrc.ui.util.TestDataConstants.*

import scala.util.Random

object TestDataGenerator {

  private val validPrefixLetters = "ABCEGHJKLMNPRSTWXYZ" // Excludes D, F, I, Q, U, V
  private val suffixLetters      = "ABCD"

  def generateNino(): String = {
    val random = new Random()

    val firstLetter  = validPrefixLetters(random.nextInt(validPrefixLetters.length))
    val secondLetter = validPrefixLetters(random.nextInt(validPrefixLetters.length))

    val digits = (1 to 6).map(_ => random.nextInt(10)).mkString

    val suffix = suffixLetters(random.nextInt(suffixLetters.length))

    s"$firstLetter$secondLetter$digits$suffix"
  }

  def generateRandomString(length: Int): String = {
    val chars  = ('A' to 'Z') ++ ('a' to 'z') ++ ('0' to '9')
    val random = new Random()
    (1 to length).map(_ => chars(random.nextInt(chars.length))).mkString
  }

  def generateRandomInteger(maxDigits: Int): String = {
    val random = new Random()

    val numDigits = random.nextInt(maxDigits) + 1

    (1 to numDigits).map(_ => random.nextInt(10)).mkString
  }

  def generateRandomAmount(maxDigits: Int): String = {
    val random = new Random()

    val numDigits = random.nextInt(maxDigits) + 1

    val wholePart = if (numDigits == 1) {
      (random.nextInt(9) + 1).toString
    } else {
      val firstDigit = random.nextInt(9) + 1
      val restDigits = (1 until numDigits).map(_ => random.nextInt(10)).mkString
      s"$firstDigit$restDigits"
    }

    val numDecimals = random.nextInt(3)

    if (numDecimals == 0) {
      wholePart
    } else {
      val decimalPart = (1 to numDecimals).map(_ => random.nextInt(10)).mkString
      s"$wholePart.$decimalPart"
    }
  }

  def generateNino(prefix: String = "AA"): String = {
    val num    = Random.nextInt(1000000)
    val suffix = "C"
    f"$prefix$num%06d$suffix"
  }

  val testNino: String = generateNino("AB")

  private val env: String = Option(System.getProperty("environment")).map(_.toLowerCase).getOrElse("local")

  def getUKPostCode: String = env match {
    case "qa" | "staging" => ukPostCodeQA
    case _                => ukPostCode
  }
}
