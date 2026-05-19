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

import uk.gov.hmrc.ui.pages.BasePage

object ConnectedPersonsPage extends BasePage {

  override def pageUrl: String = "/securities-transfer-charge/stf/connected-persons"

  sealed trait ConfirmationOption {
    def selector: String
  }

  case object Yes extends ConfirmationOption {
    val selector = "#value"
  }

  case object No extends ConfirmationOption {
    val selector = "#value-no"
  }

  // placeholder yet to finalize the title
  override def pageTitle: String =
    "Connected persons - Transfer details - securities-transfer-charge-frontend - GOV.UK " +
      "& Connected persons - Seller details - securities-transfer-charge-frontend - GOV.UK"

  def select(option: ConfirmationOption): Unit = {
    verifyExpectedContainsPageTitle(pageTitle)
    radioButton(option.selector)
    continue()
  }
}
