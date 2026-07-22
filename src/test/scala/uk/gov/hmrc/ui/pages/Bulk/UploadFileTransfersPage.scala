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

  override def pageUrl: String =
    "/securities-transfer-charge/stf/file-upload"

  override def pageTitle: String =
    "Upload your file - Transfer details" + serviceName

  object FileName {
    val Filled            = "One valid row.xlsx"
    val ErrorList         = "Error List.xlsx"
    val ManyErrors        = "Many Errors.xlsx"
    val Formatting        = "Formatting.pdf"
    val Empty             = "Empty File.xlsx"
    val PasswordProtected = "Password Protected.xlsx"
    val Template          = "Error Template.xlsx"
    val MoreThanMaxRows   = "10k+1 valid rows.xlsx"
  }

  private def testDataPath(directory: String, fileName: String): String = {
    val resource =
      getClass.getResource(s"/testData/${directory.toLowerCase}/STF ${directory.toLowerCase} - $fileName")

    if (resource == null)
      throw new IllegalArgumentException(
        s"Test resource not found: testData/${directory.toLowerCase}/STF ${directory.toLowerCase} - $fileName"
      )

    new java.io.File(resource.toURI).getAbsolutePath
  }

  def chooseFile(directory: String, fileName: String): Unit = {
    verifyPageTitleContains(pageTitle)
    uploadFile(testDataPath(directory, fileName))
  }

  def selectUpload(): Unit = {
    verifyPageTitleContains(pageTitle)
    clickUploadButton()
  }
}
