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

package uk.gov.hmrc.ui.pages.individualPages

import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.util.TestDataGenerator.generateRandomAmount

object HowMuchPaidPage extends BasePage {

  override def pageUrl: String = "/securities-transfer-charge/stf/consideration"

  // placeholder yet to finalize the title
  override def pageTitle: String =
    "How much did you pay for these securities? - securities-transfer-charge-frontend - GOV.UK"

  def enterValues(): Unit = {
    verifyPageTitle(pageTitle)
    input(Locators.txtValue, generateRandomAmount(10))
    continue()
  }
}
