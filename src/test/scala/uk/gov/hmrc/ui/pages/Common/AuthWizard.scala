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

package uk.gov.hmrc.ui.pages.Common

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.pages.Common.AuthWizard.click
import uk.gov.hmrc.ui.util.TestDataConstants.*
import uk.gov.hmrc.ui.util.{Env, TestDataConstants, Urls}

object AuthWizard extends BasePage {

  override def pageUrl: String = s"${Env.baseUrl}/auth-login-stub/gg-sign-in"

  override def pageTitle: String = "auth login stub"

  val url: String = s"${Env.baseUrl}"

  val redirectUrl: By     = By.id("redirectionUrl")
  val affinityGroup: By   = By.id("affinityGroupSelect")
  val enrolmentKey: By    = By.id("enrolment[0].name")
  val identifierName: By  = By.id("input-0-0-name")
  val identifierValue: By = By.id("input-0-0-value")
  val btnSubmit: By       = By.id("submit")

  def loginAs(userType: String): Unit = {
    val affinityValue = userType.toLowerCase match {
      case "individual"   => affinityIndividual
      case "organisation" => affinityOrganisation
      case "agent"        => affinityAgent
      case _              =>
        throw new IllegalArgumentException(
          s"Invalid user type: $userType. Must be 'individual', 'organisation', or 'agent'"
        )
    }

    AuthWizard.navigateToPage(url + Urls.SUBMISSION)
    driver.findElement(affinityGroup).sendKeys(affinityValue)
    driver.findElement(enrolmentKey).sendKeys(enrolmentsEnrolmentKey)
    driver.findElement(identifierName).sendKeys(enrolmentsIdentifierName)
    driver.findElement(identifierValue).sendKeys(enrolmentsIdentifierValue)
    click(btnSubmit)
  }
}
