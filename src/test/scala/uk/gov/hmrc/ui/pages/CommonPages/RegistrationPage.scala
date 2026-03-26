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

package uk.gov.hmrc.ui.pages.CommonPages

import uk.gov.hmrc.ui.pages.BasePage

object RegistrationPage extends BasePage {

  override def pageUrl: String = "register-securities-transfer-charge/register"

  override def pageTitle: String =
//    "Stamp taxes on shares - HMRC - Stamp tax on shares - GOV.UK"
    "Register to tell us about a securities transfer - securities-transfer-charge-reg-frontend - GOV.UK"
//  Register to tell us about a securities transfer -securities - transfer - charge - reg - frontend - GOV.UK
  def startRegistration(): Unit = {
    verifyPageTitle(RegistrationPage.pageTitle)
    continue()
  }
}
