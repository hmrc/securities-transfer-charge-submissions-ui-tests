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
import uk.gov.hmrc.ui.pages.Common.{AboutYourSecuritiesTransfersPage, AuthWizard}
import uk.gov.hmrc.ui.pages.Single.{CheckYourAnswersPage, SubmissionsDashboardPage}
import uk.gov.hmrc.ui.tags.{QAOnly, Smoke}
import uk.gov.hmrc.ui.util.TestDataConstants.affinityOrganisation

class S5SubmissionsBusinessBulkSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("STC Bulk Submission Business Journeys") {

    Scenario("Bulk submission of a user as an Organisation - one valid row", Smoke) {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityOrganisation)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityOrganisation, FileName.Filled)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verifyBulkDues("£125.00", "31 January 2026")
    }

    Scenario("Bulk submission of a user as an Organisation - less than 25 errors or fewer") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityOrganisation)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityOrganisation, FileName.ErrorList)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      BulkErrorPage.verifyErrors()
    }

    Scenario("Bulk submission of a user as an Organisation - more than 25 errors") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityOrganisation)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityOrganisation, FileName.ManyErrors)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      BulkErrorListPage.verifyErrors()
    }

    Scenario("Bulk submission of a user as an Organisation - wrong file format", QAOnly) {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityOrganisation)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityOrganisation, FileName.Formatting)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      BulkErrorTypePage.verifyError()
    }

    Scenario("Bulk submission of a user as an Organisation - Empty File") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityOrganisation)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityOrganisation, FileName.Empty)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      BulkErrorEmptyPage.verifyError()
    }

    Scenario("Bulk submission of a user as an Organisation - Empty Row") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityOrganisation)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityOrganisation, FileName.EmptyRow)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      BulkErrorEmptyPage.verifyError()
    }

    Scenario("Bulk submission of a user as an Organisation - Password protected file", QAOnly) {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityOrganisation)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityOrganisation, FileName.PasswordProtected)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      BulkErrorPasswordPage.verifyError()
    }

    Scenario("Bulk submission of a user as an Organisation - Error Template file") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityOrganisation)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityOrganisation, FileName.Template)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      BulkErrorTemplatePage.verifyError()
    }

    Scenario("Bulk submission of a user as an Organisation - Too Many rows") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityOrganisation)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()

      And("User uploads a file")
      UploadFileTransfersPage.chooseFile(affinityOrganisation, FileName.MoreThanMaxRows)
      UploadFileTransfersPage.selectUpload()
      WeAreCheckingYourFilePage.verify()

      Then("User verifies check your answers for details entered")
      BulkErrorRowsPage.verifyError()
    }
  }
}
