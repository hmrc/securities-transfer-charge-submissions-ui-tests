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

object ConfirmAddressPage extends BasePage {

  override def pageUrl: String = "/securities-transfer-charge/stf/confirm-address"

  // placeholder yet to finalize the title
  override def pageTitle: String =
    "Review and confirm your address - securities-transfer-charge-frontend - GOV.UK " +
      "& Confirm the seller’s address - - GOV.UK " +
      "& Confirm the buyer’s address - - GOV.UK " +
      "& Review and confirm - - GOV.UK " +
      "& Confirm business address - Business details - securities-transfer-charge-frontend - GOV.UK" +
      "& Confirm the business’s address - - GOV.UK"

  def confirm(): Unit                      = {
    verifyPageTitleContains(pageTitle)
    continue()
  }
  def clickEnterTheAddressManually(): Unit = {
    verifyPageTitleContains(pageTitle)
    click(Locators.lnkEditAddr)
  }
}
