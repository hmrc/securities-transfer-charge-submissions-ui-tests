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

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import scala.util.Random

object TestDataGenerator {

  private val random = new Random()

  // Can return 0
  def randomInt(maxExclusive: Int): String =
    random.nextInt(maxExclusive).toString

  // Starts from 1
  def randomIntFromOne(maxInclusive: Int): String =
    (random.nextInt(maxInclusive) + 1).toString

  def generateRandomString(length: Int): String = {
    val chars = ('A' to 'Z') ++ ('a' to 'z') ++ ('0' to '9')
    (1 to length).map(_ => chars(random.nextInt(chars.length))).mkString
  }

  def generateRandomAmount(maxDigits: Int): BigDecimal = {
    val numDigits = random.nextInt(maxDigits) + 1

    val wholePart =
      if (numDigits == 1) {
        (random.nextInt(9) + 1).toString
      } else {
        val firstDigit = random.nextInt(9) + 1
        val restDigits = (1 until numDigits).map(_ => random.nextInt(10)).mkString
        s"$firstDigit$restDigits"
      }

    val numDecimals = random.nextInt(3)

    if (numDecimals == 0) {
      BigDecimal(wholePart)
    } else {
      val decimalPart =
        (1 to numDecimals).map(_ => random.nextInt(10)).mkString

      BigDecimal(s"$wholePart.$decimalPart")
    }
  }

  private val env: String = Option(System.getProperty("environment")).map(_.toLowerCase).getOrElse("local")

  def getUKPostCode: String = env match {
    case "qa" | "staging" => ukPostCodeQA
    case _                => ukPostCode
  }

  def generateRandomDate(): (Int, Int, Int) = {
    val startDate = LocalDate.of(2026, 1, 1)
    val endDate   = LocalDate.now()

    val daysBetween = ChronoUnit.DAYS.between(startDate, endDate)

    val randomDate = startDate.plusDays(Random.nextLong(daysBetween + 1))

    val day   = randomDate.getDayOfMonth
    val month = randomDate.getMonthValue
    val year  = randomDate.getYear

    (day, month, year)
  }

  def addDays(date: LocalDate, count: Int): LocalDate =
    date.plusDays(count)
}
