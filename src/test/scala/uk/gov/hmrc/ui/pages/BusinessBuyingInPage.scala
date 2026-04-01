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

import uk.gov.hmrc.ui.util.TestDataConstants.validCRN
import uk.gov.hmrc.ui.util.TestDataGenerator.generateRandomString

object BusinessBuyingInPage extends BasePage {

  override def pageUrl: String = "/securities-transfer-charge/stf/securities-target"

  // placeholder yet to finalize the title
  override def pageTitle: String =
    "What business are you buying these securities in? - securities-transfer-charge-frontend - GOV.UK " +
      "& Which business are the securities being bought in? - securities-transfer-charge-frontend - GOV.UK"

  def enterValues(): Unit = {
    verifyPageTitleContains(pageTitle)
    input(Locators.txtBusinessName, generateRandomString(10))
    input(Locators.txtCRN, validCRN)
    continue()
  }
}
