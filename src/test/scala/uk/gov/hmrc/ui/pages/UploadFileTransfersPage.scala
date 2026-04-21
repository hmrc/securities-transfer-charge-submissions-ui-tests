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

import uk.gov.hmrc.ui.pages.SecuritiesTypePage.verifyPageTitleContains

object UploadFileTransfersPage extends BasePage {

  override def pageUrl: String = "/securities-transfer-charge/stf/file-upload"

  override def pageTitle: String = "Upload your file"

  private def testDataPath(fileName: String): String =
    new java.io.File(s"src/test/resources/testData/$fileName").getAbsolutePath

  val filledFile: String     = testDataPath("Bulk Securities Transfer Charges - Filled.xlsx")
  val emptyFile: String      = testDataPath("Bulk Securities Transfer Charges - Empty.xlsx")
  val formattingFile: String = testDataPath("Bulk Securities Transfer Charges - Formatting.xlsx")
  val errorListFile: String  = testDataPath("Bulk Securities Transfer Charges - Error List.xlsx")

  def chooseFile(file: String = filledFile): Unit = {
    verifyPageTitleContains(pageTitle)
    uploadFile(file)
  }

  def selectUpload(): Unit = {
    verifyPageTitleContains(pageTitle)
    clickUploadButton()
  }
}
