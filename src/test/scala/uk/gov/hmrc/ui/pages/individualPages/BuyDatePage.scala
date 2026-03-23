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

object BuyDatePage extends BasePage {

  override def pageUrl: String = "/securities-transfer-charge/stf/charging-point"

  // placeholder yet to finalize the title
  override def pageTitle: String =
    "When did you buy these securities? - securities-transfer-charge-frontend - GOV.UK"

  def enterDate(date: String, month: String, year: String): Unit = {
    verifyPageTitle(pageTitle)
    input(Locators.txtDate, date)
    input(Locators.txtMonth, month)
    input(Locators.txtYear, year)
    continue()
  }
}
