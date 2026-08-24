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
import uk.gov.hmrc.ui.pages.Bulk.*
import uk.gov.hmrc.ui.pages.Bulk.UploadFileTransfersPage.*
import uk.gov.hmrc.ui.pages.Common.AboutYourSecuritiesTransfersPage.More
import uk.gov.hmrc.ui.pages.Common.{AboutYourSecuritiesTransfersPage, AddAReference, AuthWizard}
import uk.gov.hmrc.ui.pages.Single.SubmissionsDashboardPage
import uk.gov.hmrc.ui.tags.{QAOnly, Smoke}
import uk.gov.hmrc.ui.util.TestDataConstants.affinityAgent

class S6SubmissionsAgentBulkSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("STC Bulk Submission Agent Journeys") {

    Scenario("Bulk submission of a user as an Agent - one valid row", Smoke) {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityAgent, FileName.Filled)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()
      AddAReference.enterValue()

      Then("User verifies check your answers for details entered")
//      CheckYourAnswersPage.verifyDues(checkYourAnswers)
    }

    Scenario("Bulk submission of a user as an Agent - less than 25 errors or fewer") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityAgent, FileName.ErrorList)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      BulkErrorPage.verifyErrors()
    }

    Scenario("Bulk submission of a user as an Agent - more than 25 errors") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityAgent, FileName.ManyErrors)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      BulkErrorListPage.verifyErrors()
    }

    Scenario("Bulk submission of a user as an Agent - wrong file format", QAOnly) {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityAgent, FileName.Formatting)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      BulkErrorTypePage.verifyError()
    }

    Scenario("Bulk submission of a user as an Agent - Empty File") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityAgent, FileName.Empty)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      BulkErrorEmptyPage.verifyError()
    }

    Scenario("Bulk submission of a user as an Agent - Empty Row") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityAgent, FileName.EmptyRow)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      BulkErrorEmptyPage.verifyError()
    }

    Scenario("Bulk submission of a user as an Agent - Password protected file", QAOnly) {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityAgent, FileName.PasswordProtected)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      BulkErrorPasswordPage.verifyError()
    }

    Scenario("Bulk submission of a user as an Agent - Error Template file") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityAgent, FileName.Template)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      BulkErrorTemplatePage.verifyError()
    }

    Scenario("Bulk submission of a user as an Agent - Too Many rows") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityAgent, FileName.MoreThanMaxRows)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      BulkErrorRowsPage.verifyError()
    }
  }
}
