/*
 * Copyright 2026 HM Revenue & Customs
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

package uk.gov.hmrc.ui.pages.Bulk

import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.util.TestDataConstants.serviceName

object UploadFileTransfersPage extends BasePage {

  override def pageUrl: String = "/securities-transfer-charge/stf/file-upload"

  override def pageTitle: String = "Upload your file - Transfer details" + serviceName

  private def testDataPath(fileName: String): String = {
    val resource = getClass.getResource(s"/testData/individual/$fileName")
    if (resource == null) throw new IllegalArgumentException(s"Test resource not found: testData/individual/$fileName")
    new java.io.File(resource.toURI).getAbsolutePath
  }

  val filledFile: String            = testDataPath("STF Individual - One valid row.xlsx")
  val errorListFile: String         = testDataPath("STF Individual - Error List.xlsx")
  val manyErrorsFile: String        = testDataPath("STF Individual - Many Errors.xlsx")
  val formattingFile: String        = testDataPath("STF Individual - Formatting.pdf")
  val emptyFile: String             = testDataPath("STF Individual - Empty File.xlsx")
  val passwordProtectedFile: String = testDataPath("STF Individual - Password Protected.xlsx")
  val templateFile: String          = testDataPath("STF Individual - Error Template.xlsx")
  val moreThanMaxRows: String       = testDataPath("STF Individual - 10k+1 valid rows.xlsx")

  def chooseFile(file: String = filledFile): Unit = {
    verifyPageTitleContains(pageTitle)
    uploadFile(file)
  }

  def selectUpload(): Unit = {
    verifyPageTitleContains(pageTitle)
    clickUploadButton()
  }
}
