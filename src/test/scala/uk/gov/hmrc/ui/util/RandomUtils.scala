/*
 * Copyright 2025 HM Revenue & Customs
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

import java.time.{LocalDate, Period}
import scala.util.Random

object RandomUtils {

  def randString(howManyChars: Integer): String =
    Random.alphanumeric take howManyChars mkString ""

  def randNumbers(howManyNos: Integer): String =
    Seq.fill(howManyNos)(Random.nextInt(9)).mkString("")

  def dateRange(from: LocalDate, to: LocalDate, step: Period): Iterator[LocalDate] =
    Iterator.iterate(from)(_.plus(step)).takeWhile(!_.isAfter(to))

  def generateRandomYearOfBirth(): String = {
    val currentDate = LocalDate.now()
    val minAge      = 18
    val maxAge      = 150
    val randomAge   = Random.nextInt(maxAge - minAge + 1) + minAge
    currentDate.minusYears(randomAge).toString
  }

  def generateRandomMonthOfBirth(): String =
    (Random.nextInt(12) + 1).toString

  def generateRandomDayOfBirth(year: Int, month: Int): String = {
    val maxDay = month match {
      case 1 | 3 | 5 | 7 | 8 | 10 | 12 => 31
      case 4 | 6 | 9 | 11              => 30
      case 2                           =>
        if (isLeapYear(year)) 29 else 28
    }
    (Random.nextInt(maxDay) + 1).toString
  }

  def isLeapYear(year: Int): Boolean =
    (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}
