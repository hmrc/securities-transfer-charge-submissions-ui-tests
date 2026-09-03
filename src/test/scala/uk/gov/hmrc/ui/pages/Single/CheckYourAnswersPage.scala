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

package uk.gov.hmrc.ui.pages.Single

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.util.TestDataConstants.serviceName

import scala.runtime.stdLibPatches.Predef.assert
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object CheckYourAnswersPage extends BasePage {

  override def pageUrl: String = "/securities-transfer-charge/stf/check-your-answers"

  // placeholder yet to finalize the title
  val pageTitles: Seq[String] = Seq(
    "Check your answers" + serviceName,
    "Check your answers - Securities Transfer (STF)" + serviceName,
    "Check your answers - Share buyback (SH03)" + serviceName
  )

  // Test data values
  var consideration: BigDecimal         = 0.00
  var marketValue: BigDecimal           = 0.00
  var taxRate: Double                   = 0.005
  var reliefMultiplier: Double          = 1
  var expectedPaymentDueDate: LocalDate = _

  def setTestData(
    considerationBD: BigDecimal = 0.00,
    marketValueBD: BigDecimal = 0.00,
    taxRateD: Double = 0.005,
    reliefMultD: Double = 1.0,
    expectedPaymentDateOpt: Option[LocalDate] = None
  ): Unit = {
    consideration = considerationBD
    marketValue = marketValueBD
    taxRate = taxRateD
    reliefMultiplier = reliefMultD
    expectedPaymentDateOpt.foreach(d => expectedPaymentDueDate = d)
  }

  def readTaxDueText(): String = {
    val el   = driver.findElement(By.cssSelector("h2.govuk-heading-m"))
    val text = el.getText.trim
    logger.info(s"Tax due raw text: $text")
    text
  }

  def readTaxDueAmount(): BigDecimal = {

    val element = w.until(
      ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//h2[contains(normalize-space(.), 'Tax due:')]")
      )
    )

    val text = element.getText

    logger.info(s"Tax due raw text: $text")

    val pattern = "£\\s*([0-9,]+(?:\\.[0-9]{1,2})?)".r

    val amountStr = pattern
      .findFirstMatchIn(text)
      .map(_.group(1))
      .getOrElse(
        throw new AssertionError(
          s"Could not parse tax due amount from '$text'"
        )
      )

    BigDecimal(amountStr.replace(",", ""))
  }

  def readPaymentDueText(): String = {
    val el   = driver.findElement(By.xpath("//p[contains(., 'Payment due by')]/strong"))
    val text = el.getText.trim
    logger.info(s"Payment due raw text: $text")
    text
  }

  def readPaymentDueDate(): LocalDate = {
    val text = readPaymentDueText()
    val fmt  = DateTimeFormatter.ofPattern("d MMMM uuuu", Locale.ENGLISH)
    LocalDate.parse(text, fmt)
  }

  def readPaymentDueDateStr: String = readPaymentDueDate().toString

  def verifyTaxDue(): Unit = {
    val actualTaxDue   = readTaxDueAmount()
    logger.info(s"Actual tax due: $actualTaxDue")
    logger.info(s"Consideration: $consideration")
    logger.info(s"Market value: $marketValue")
    logger.info(s"Tax rate: $taxRate")
    logger.info(s"Relief multiplier: $reliefMultiplier")
    val taxableValue   = if (consideration > marketValue) consideration else marketValue
    val expectedTaxDue =
      (taxableValue * taxRate * reliefMultiplier).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    logger.info(s"Expected tax due: $expectedTaxDue")
    assert(actualTaxDue == expectedTaxDue, s"Expected tax due amount not found")
  }

  def verifyBulkTaxDue(expectedTaxDue: BigDecimal): Unit = {
    val actualTaxDue = readTaxDueAmount()
    logger.info(s"Actual tax due: $actualTaxDue")
    logger.info(s"Expected tax due: $expectedTaxDue")
    assert(actualTaxDue == expectedTaxDue, s"Expected tax due amount not found")
  }

  def verifyPaymentDue(): Unit = {
    val actualPaymentDueDate = readPaymentDueDate()
    logger.info(s"Actual payment due date: $actualPaymentDueDate")
    logger.info(s"Expected payment due date: $expectedPaymentDueDate")
    assert(actualPaymentDueDate == expectedPaymentDueDate, s"Expected payment due date not found")
  }

  def verifyBulkPaymentDue(expectedPaymentDueDate: LocalDate): Unit = {
    val actualPaymentDueDate = readPaymentDueDate()
    logger.info(s"Actual payment due date: $actualPaymentDueDate")
    logger.info(s"Expected payment due date: $expectedPaymentDueDate")
    assert(actualPaymentDueDate == expectedPaymentDueDate, s"Expected payment due date not found")
  }

  def verifyDues(): Unit = {
    verifyPageTitleIsOneOf(pageTitles)

    verifyTaxDue()
    verifyPaymentDue()

    continue()
  }

  def verifyBulkDues(taxDue: String, paymentDueDate: String): Unit = {
    verifyPageTitleIsOneOf(pageTitles)

    verifyBulkTaxDue(BigDecimal(taxDue.stripPrefix("£").replace(",", "")))
    verifyBulkPaymentDue(LocalDate.parse(paymentDueDate, DateTimeFormatter.ofPattern("d MMMM uuuu", Locale.ENGLISH)))

    continue()
  }
}
