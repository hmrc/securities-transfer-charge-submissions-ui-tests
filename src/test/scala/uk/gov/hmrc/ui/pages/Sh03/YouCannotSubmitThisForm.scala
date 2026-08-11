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

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.util.TestDataConstants.serviceName

import scala.runtime.stdLibPatches.Predef.assert

object YouCannotSubmitThisForm extends BasePage {

  override def pageUrl: String = "/securities-transfer-charge/sh03/agent/cannot-submit-form"

  // placeholder yet to finalize the title
  val pageTitles: Seq[String] = Seq(
    "You cannot submit this form - Share buyback (SH03)" + serviceName
  )

  def verify(expectedTitle: String): Unit = {
    verifyPageTitleIsOneOf(pageTitles)
    val panelTitle = driver.findElement(By.cssSelector(".govuk-heading-l"))

    val actualText = panelTitle.getText.trim

    assert(
      actualText == expectedTitle,
      s"Expected confirmation panel title '$expectedTitle' but found '$actualText'"
    )
  }
}
