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
import uk.gov.hmrc.ui.util.TestDataConstants.serviceName
import uk.gov.hmrc.ui.util.TestDataGenerator.generateRandomString

object RolePurchasingCompany extends BasePage {

  override def pageUrl: String = "/securities-transfer-charge/sh03/agent/share-purchase"

  sealed trait ConfirmationOption {
    def selector: String
  }

  case object Director extends ConfirmationOption { val selector = "#role" }
  case object Secretary extends ConfirmationOption { val selector = "#role-2" }
  case object PersonAuthorised extends ConfirmationOption { val selector = "#role-3" }
  case object Administrator extends ConfirmationOption { val selector = "#role-4" }
  case object Receiver extends ConfirmationOption { val selector = "#role-5" }
  case object ReceiverManager extends ConfirmationOption { val selector = "#role-6" }
  case object CICManager extends ConfirmationOption { val selector = "#role-7" }
  case object UKSocietas extends ConfirmationOption { val selector = "#role-8" }
  case object NotProvided extends ConfirmationOption { val selector = "#role-10" }

  val pageTitles: Seq[String] = Seq(
    "Who at the purchasing company provided the details of this share buyback? - Share buyback (SH03)" + serviceName,
    "What is your role within the purchasing company? - Share buyback (SH03)" + serviceName
  )

  def select(option: ConfirmationOption = Director): Unit = {
    verifyPageTitleIsOneOf(pageTitles)
    radioButton(option.selector)
    if (option == UKSocietas) {
      input(Locators.txtUKSOrgan, generateRandomString(10))
    }
    saveAndContinue()
  }
}
