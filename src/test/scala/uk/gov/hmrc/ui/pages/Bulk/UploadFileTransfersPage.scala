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
import uk.gov.hmrc.ui.util.TestDataConstants.{serviceName, stf}

object UploadFileTransfersPage extends BasePage {

  override def pageUrl: String = "/securities-transfer-charge/stf/file-upload"

  val pageTitles: Seq[String] = Seq(
    "Upload your file - Transfer details" + serviceName
  )

  object FileName {
    val Filled            = "one_valid_row.xlsx"
    val ErrorList         = "error_list.xlsx"
    val ManyErrors        = "many_errors.xlsx"
    val Formatting        = "formatting.pdf"
    val Empty             = "empty_file.xlsx"
    val EmptyRow          = "empty_row.xlsx"
    val PasswordProtected = "password_protected.xlsx"
    val Template          = "error_template.xlsx"
    val MoreThanMaxRows   = "10k_plus_1_valid_rows.xlsx"
  }

  private def getTestDataPath(directory: String, fileName: String, prefix: String): String = {
    val dirSubPath   = prefix.toLowerCase
    val dirPath      = directory.toLowerCase
    val resourcePath = s"/testData/$dirPath/$dirSubPath/${prefix.toLowerCase}_${dirPath}_$fileName"
    val resource     = getClass.getResource(resourcePath)

    if (resource == null)
      throw new IllegalArgumentException(
        s"Test resource not found: $resourcePath"
      )

    new java.io.File(resource.toURI).getAbsolutePath
  }

  def chooseFile(directory: String, fileName: String, prefix: String = stf): Unit = {
    verifyPageTitleIsOneOf(pageTitles)
    uploadFile(getTestDataPath(directory, fileName, prefix))
  }

  def selectUpload(): Unit = {
    verifyPageTitleIsOneOf(pageTitles)
    clickUploadButton()
  }
}
