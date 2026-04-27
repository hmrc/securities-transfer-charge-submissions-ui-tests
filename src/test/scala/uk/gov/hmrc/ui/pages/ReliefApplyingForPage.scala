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

object ReliefApplyingForPage extends BasePage {

  override def pageUrl: String = "/securities-transfer-charge/stf/select-relief"

  // placeholder yet to finalize the title
  override def pageTitle: String =
    "What relief are you applying for? - Transfer details - securities-transfer-charge-frontend - GOV.UK " +
      "& What relief is the business applying for? - Transfer details - securities-transfer-charge-frontend - GOV.UK"

  def enterRelief(relief: String): Unit = {
    verifyExpectedContainsPageTitle(pageTitle)
    input(Locators.dropDownRelief, relief)
    continue()
    // additional continue is to select and continue to next page
    continue()
  }
}
