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

package uk.gov.hmrc.ui.specs

import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.Common.AboutYourSecuritiesTransfersPage.More
import uk.gov.hmrc.ui.pages.Bulk.{BulkEmptyPage, BulkErrorListPage, BulkFormattingPage, BulkTooManyErrorsPage, HowUseTemplateTransfersPage, TempPlaceholderFileUploadedPage, UploadFileTransfersPage}
import uk.gov.hmrc.ui.pages.Common.{AboutYourSecuritiesTransfersPage, AuthWizard}
import uk.gov.hmrc.ui.pages.Single.{CheckYourAnswersPage, SubmissionsDashboardPage}
import uk.gov.hmrc.ui.util.TestDataConstants.checkYourAnswers

class S4SubmissionsIndividualBulkSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("STC Bulk Submission Individual Journeys") {

    Scenario("Bulk submission of a user as an Individual") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs("individual")

      When("User navigates to Submissions start page - Buyer's details")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()
      UploadFileTransfersPage.chooseFile()
      UploadFileTransfersPage.selectUpload()

      // To be replaced with bulk check your answers
      TempPlaceholderFileUploadedPage.verifyPageTitle()
      TempPlaceholderFileUploadedPage.navigateTo(CheckYourAnswersPage)
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of an empty bulk file as a user as an Individual") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs("individual")

      When("User navigates to Submissions start page - Buyer's details")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()
      UploadFileTransfersPage.chooseFile(UploadFileTransfersPage.emptyFile)
      UploadFileTransfersPage.selectUpload()

      // Remove next step when navigation has been wired in
      TempPlaceholderFileUploadedPage.navigateTo(BulkEmptyPage)

      //      Wondering if this step should be in the spec as the title is part of the A/C?
      BulkEmptyPage.verifyPageTitleContains("There are no transfers in your file")
      BulkEmptyPage.selectUpload()
      UploadFileTransfersPage.chooseFile()
      UploadFileTransfersPage.selectUpload()

      // To be replaced with bulk check your answers
      TempPlaceholderFileUploadedPage.verifyPageTitle()
      TempPlaceholderFileUploadedPage.navigateTo(CheckYourAnswersPage)
      CheckYourAnswersPage.verify(checkYourAnswers)

    }

    Scenario("Submission of a bulk file with error list (25 or less errors) as a user as an Individual") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs("individual")

      When("User navigates to Submissions start page - Buyer's details")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()
      UploadFileTransfersPage.chooseFile(UploadFileTransfersPage.errorListFile)
      UploadFileTransfersPage.selectUpload()

      // Remove next step when navigation has been wired in
      TempPlaceholderFileUploadedPage.navigateTo(BulkErrorListPage)
      BulkErrorListPage.selectUpload()
      UploadFileTransfersPage.chooseFile()
      UploadFileTransfersPage.selectUpload()

      // To be replaced with bulk check your answers
      TempPlaceholderFileUploadedPage.verifyPageTitle()
      TempPlaceholderFileUploadedPage.navigateTo(CheckYourAnswersPage)
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of a bulk file with many errors (26 or more errors) as a user as an Individual") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs("individual")

      When("User navigates to Submissions start page - Buyer's details")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()
      UploadFileTransfersPage.chooseFile(UploadFileTransfersPage.manyErrorsFile)
      UploadFileTransfersPage.selectUpload()

      // Remove next step when navigation has been wired in
      TempPlaceholderFileUploadedPage.navigateTo(BulkTooManyErrorsPage)
      BulkTooManyErrorsPage.selectUpload()
      UploadFileTransfersPage.chooseFile()
      UploadFileTransfersPage.selectUpload()

      // To be replaced with bulk check your answers
      TempPlaceholderFileUploadedPage.verifyPageTitle()
      TempPlaceholderFileUploadedPage.navigateTo(CheckYourAnswersPage)
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of a bulk file with formatting errors as a user as an Individual") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs("individual")

      When("User navigates to Submissions start page - Buyer's details")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()
      UploadFileTransfersPage.chooseFile(UploadFileTransfersPage.formattingFile)
      UploadFileTransfersPage.selectUpload()

      // Remove next step when navigation has been wired in
      TempPlaceholderFileUploadedPage.navigateTo(BulkFormattingPage)
      BulkFormattingPage.selectUpload()
      UploadFileTransfersPage.chooseFile()
      UploadFileTransfersPage.selectUpload()

      // To be replaced with bulk check your answers
      TempPlaceholderFileUploadedPage.verifyPageTitle()
      TempPlaceholderFileUploadedPage.navigateTo(CheckYourAnswersPage)
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

  }
}
