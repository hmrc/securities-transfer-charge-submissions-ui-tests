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

package uk.gov.hmrc.ui.pages.Sh03

import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.pages.Sh03.CompanyDetails.{ConfirmationOption, Yes}
import uk.gov.hmrc.ui.pages.Sh03.MaximumAmountShares.{saveAndContinue, verifyPageTitle}
import uk.gov.hmrc.ui.pages.Single.ReliefApplyingForPage.input
import uk.gov.hmrc.ui.util.TestDataConstants.serviceName

object CompanyNumber extends BasePage {

  override def pageUrl: String = "/securities-transfer-charge/sh03/org/company-number"

  // placeholder yet to finalize the title
  override def pageTitle: String                             =
    "What is the company registration number (CRN)? - Share buyback (SH03)" + serviceName
  def enterCrnNumber(option: ConfirmationOption = Yes): Unit = {
    verifyPageTitle(pageTitle)
    input(Locators.txtCompanyRegistrationNumber, "12345678")
    saveAndContinue()
  }
}
