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

package uk.gov.hmrc.ui.pages

import org.openqa.selenium.By

object KOPage extends BasePage {

  override def pageUrl: String = "/register-securities-transfer-charge/lowConfidenceKickOut"

  // placeholder yet to finalize the title
  override def pageTitle: String =
    "Before you use the service - securities-transfer-charge-reg-frontend - GOV.UK"

  val lowCl            = "Before you use the service"
  val checkYourDetails = "You must update the details on your government gateway account"
  val nonUk            = "You cannot use this service"
  val trust            = "You cannot use this service"
  val general          = "You cannot use this service"
  val scottish         = "You cannot use this service"

  val pageTitleLowCl            = "Before you use the service - securities-transfer-charge-reg-frontend - GOV.UK"
  val pageTitleCheckYourDetails =
    "You must update the details on your government gateway account - securities-transfer-charge-reg-frontend - GOV.UK"
  val pageTitleNonUk            = "You cannot use this service - securities-transfer-charge-reg-frontend - GOV.UK"
  val pageTitleTrust            = "You cannot use this service - securities-transfer-charge-reg-frontend - GOV.UK"
  val pageTitleGeneral          = "You cannot use this service - securities-transfer-charge-reg-frontend - GOV.UK"
  val pageTitleScottish         = "You cannot use this service - securities-transfer-charge-reg-frontend - GOV.UK"

  def getPageTitleForHeading(expectedHeading: String): String = expectedHeading match {
    case `lowCl`            => pageTitleLowCl
    case `checkYourDetails` => pageTitleCheckYourDetails
    case `nonUk`            => pageTitleNonUk
    case `trust`            => pageTitleTrust
    case `general`          => pageTitleGeneral
    case `scottish`         => pageTitleScottish
    case _                  => pageTitle // fallback to default
  }

  def validateErrorMessage(expectedHeading: String): Unit = {
    val matchingPageTitle = getPageTitleForHeading(expectedHeading)
    verifyPageTitle(matchingPageTitle)
    val headingText       = waitForVisibilityOfElement(By.cssSelector("h1")).getText.trim

    assert(
      headingText == expectedHeading,
      s"Expected heading text '$expectedHeading' but found '$headingText'"
    )
  }
}
