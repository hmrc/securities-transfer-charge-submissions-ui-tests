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

import uk.gov.hmrc.ui.conf.TestConfiguration
import uk.gov.hmrc.ui.pages.SecuritiesTypePage.verifyPageTitleContains

object TempPlaceholderFileUploadedPage extends BasePage {

  override def pageUrl: String = "/securities-transfer-charge/stf/file-upload"

  override def pageTitle: String =
    "File uploaded"

  def verifyPageTitle(): Unit =
    verifyPageTitleContains(pageTitle)

  def verifyPageUploadedTitle(pageTitle: String): Unit =
    verifyPageTitleContains("File uploaded")

  def navigateToBulkEmptyPage(): Unit =
    navigateToPage(
      s"${TestConfiguration.baseUrl("securities-transfer-charge-submissions")}${BulkEmptyPage.pageUrl}"
    )

  def navigateToBulkErrorPage(): Unit =
    navigateToPage(
      s"${TestConfiguration.baseUrl("securities-transfer-charge-submissions")}${BulkErrorPage.pageUrl}"
    )

  def navigateToBulkFormattingPage(): Unit =
    navigateToPage(
      s"${TestConfiguration.baseUrl("securities-transfer-charge-submissions")}${BulkFormattingPage.pageUrl}"
    )

  def navigateToBulkErrorListPage(): Unit =
    navigateToPage(
      s"${TestConfiguration.baseUrl("securities-transfer-charge-submissions")}${BulkErrorListPage.pageUrl}"
    )
}
