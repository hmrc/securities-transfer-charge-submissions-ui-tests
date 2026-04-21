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

object BulkErrorListPage extends BasePage {

  override def pageUrl: String = "/securities-transfer-charge/stf/bulk-error-list"

  override def pageTitle: String = "There is a problem with your uploaded file"

  def selectUpload(): Unit = {
    verifyPageTitleContains(pageTitle)
    navigateToPage(
      s"${TestConfiguration.baseUrl("securities-transfer-charge-submissions")}${UploadFileTransfersPage.pageUrl}"
    )
  }

}
