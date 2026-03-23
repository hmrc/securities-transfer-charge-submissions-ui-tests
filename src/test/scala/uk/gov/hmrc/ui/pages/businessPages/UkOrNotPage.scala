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

package uk.gov.hmrc.ui.pages.businessPages

import uk.gov.hmrc.ui.pages.BasePage

object UkOrNotPage extends BasePage {

  override def pageUrl: String = "register-securities-transfer-charge/org/uk-or-not"

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
    "Does your company operate in the UK? - securities-transfer-charge-reg-frontend - GOV.UK"

  def confirmDetails(option: ConfirmationOption = Yes): Unit = {
    verifyPageTitle(pageTitle)
    radioButton(option.selector)
    continue()
  }
}
