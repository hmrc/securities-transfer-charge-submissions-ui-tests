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

package uk.gov.hmrc.ui.pages.CommonPages

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.pages.CommonPages.AuthWizard.click
import uk.gov.hmrc.ui.util.TestDataConstants.*
import uk.gov.hmrc.ui.util.{Env, TestDataConstants, Urls}

object AuthWizard extends BasePage {

  override def pageUrl: String = s"${Env.baseUrl}/auth-login-stub/gg-sign-in"

  override def pageTitle: String = "auth login stub"

  val url: String = s"${Env.baseUrl}"

  val redirectUrl: By     = By.id("redirectionUrl")
  val affinityGroup: By   = By.id("affinityGroupSelect")
  val confidenceLevel: By = By.id("confidenceLevel")
  val nino: By            = By.id("nino")
  val addPreset: By       = By.id("add-ident-btn-0")
  val enrolmentKey: By    = By.id("enrolment[0].name")
  val identifierName: By  = By.id("input-0-0-name")
  val identifierValue: By = By.id("input-0-0-value")
  val btnSubmit: By       = By.id("submit")
  val givenName: By       = By.id("itmp.givenName")
  val familyName: By      = By.id("itmp.familyName")

  def loginAsIndividual(): Unit = {
    AuthWizard.navigateToPage(url + Urls.SUBMISSION)
    driver.findElement(affinityGroup).sendKeys(affinityIndividual)
    driver.findElement(enrolmentKey).sendKeys(enrolmentsEnrolmentKey)
    driver.findElement(identifierName).sendKeys(enrolmentsIdentifierName)
    driver.findElement(identifierValue).sendKeys(enrolmentsIdentifierValue)
    click(btnSubmit)
  }

  def loginAsOrganisation(): Unit = {
    AuthWizard.navigateToPage(url + Urls.SUBMISSION)
    driver.findElement(affinityGroup).sendKeys(affinityOrganisation)
    driver.findElement(enrolmentKey).sendKeys(enrolmentsEnrolmentKey)
    driver.findElement(identifierName).sendKeys(enrolmentsIdentifierName)
    driver.findElement(identifierValue).sendKeys(enrolmentsIdentifierValue)
    click(btnSubmit)
  }
}
