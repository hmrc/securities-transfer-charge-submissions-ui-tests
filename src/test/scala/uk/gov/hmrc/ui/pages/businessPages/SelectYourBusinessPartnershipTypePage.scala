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

object SelectYourBusinessPartnershipTypePage extends BasePage {

  override def pageUrl: String = "/register-securities-transfer-charge/org/type-of-partnership"

  sealed trait ConfirmationOption {
    def selector: String
  }

  case object General extends ConfirmationOption {
    val selector = "#value_0"
  }

  case object Scottish extends ConfirmationOption {
    val selector = "#value_1"
  }

  case object ScottishLimited extends ConfirmationOption {
    val selector = "#value_2"
  }

  case object Limited extends ConfirmationOption {
    val selector = "#value_3"
  }

  case object LimitedLiability extends ConfirmationOption {
    val selector = "#value_4"
  }

  // placeholder yet to finalize the title
  override def pageTitle: String =
    "What is your business partnership type? - securities-transfer-charge-reg-frontend - GOV.UK"

  def selectType(option: ConfirmationOption = ScottishLimited): Unit = {
    verifyPageTitle(pageTitle)
    radioButton(option.selector)
    continue()
  }
}
