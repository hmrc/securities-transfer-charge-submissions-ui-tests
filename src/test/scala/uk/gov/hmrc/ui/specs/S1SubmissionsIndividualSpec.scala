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
import uk.gov.hmrc.ui.pages.*
import SecuritiesTypePage.{Other, Shares}
import TaxRatePage.{HalfRate, OneAndHalfRate}
import uk.gov.hmrc.ui.pages.AboutYourSecuritiesTransfersPage.More
import uk.gov.hmrc.ui.util.TestDataConstants.*
import uk.gov.hmrc.ui.util.TestDataGenerator.{generateRandomString, getUKPostCode}

class S1SubmissionsIndividualSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("STC Individual Journeys") {
    Scenario("Submission of a user as an Individual") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsIndividual()

      When("User navigates to Submissions start page - Buyer's details")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      ConfirmAddressPage.confirm()

      When("User enters seller's details")
      SellersNamePage.enterName(generateRandomString(10))
      AddressCountryPage.enterCountry(ukCountry)
      FindAddressPage.enterPostCode(getUKPostCode)
      SelectAddressPage.selectAddress()
      ConfirmAddressPage.confirm()

      And("User selects and enters transfer details")
      ConnectedPersonsPage.select(ConnectedPersonsPage.Yes)
      ApplyingForReliefPage.select(ApplyingForReliefPage.Yes)
      ReliefApplyingForPage.enterRelief(CRRelief)
      BusinessBuyingInPage.enterValues()
      BuyDatePage.enterDate(buyDate, buyMonth, BuyYear)
      TaxRatePage.select(HalfRate)
      SecuritiesTypePage.select(Shares)
      TransferDetailsPage.enterValues()

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario(
      "Submission of a user as an Individual - Edit and update buyer's & seller's address to another UK address"
    ) {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsIndividual()

      When("User navigates to Submissions start page - Edit Buyer's details")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      ConfirmAddressPage.clickEnterTheAddressManually()
      AddressCountryPage.enterCountry(ukCountry)
      FindAddressPage.enterPostCode(getUKPostCode)
      SelectAddressPage.selectAddress()
      ConfirmAddressPage.confirm()

      When("User enters seller's details")
      SellersNamePage.enterName(generateRandomString(10))
      AddressCountryPage.enterCountry(ukCountry)
      FindAddressPage.clickEnterTheAddressManually()
      EnterAddressPage.enterAddressDetails(addressLine1, getUKPostCode)
      ConfirmAddressPage.confirm()

      And("User selects and enters transfer details")
      ConnectedPersonsPage.select(ConnectedPersonsPage.Yes)
      ApplyingForReliefPage.select(ApplyingForReliefPage.Yes)
      ReliefApplyingForPage.enterRelief(CRRelief)
      BusinessBuyingInPage.enterValues()
      BuyDatePage.enterDate(buyDate, buyMonth, BuyYear)
      TaxRatePage.select(OneAndHalfRate)
      SecuritiesTypePage.select(Shares)
      TransferDetailsPage.enterValues()

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of a user as an Individual - Edit and update buyer's & seller's address to non UK address") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsIndividual()

      When("User navigates to Submissions start page - Edit Buyer's details")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      ConfirmAddressPage.clickEnterTheAddressManually()
      AddressCountryPage.enterCountry(nonUkCountry)
      EnterAddressPage.enterAddressDetails(addressLine1, nonUkPostCode)
      ConfirmAddressPage.confirm()

      When("User enters seller's details")
      SellersNamePage.enterName(generateRandomString(10))
      AddressCountryPage.enterCountry(nonUkCountry)
      EnterAddressPage.enterAddressDetails(addressLine1, nonUkPostCode)
      ConfirmAddressPage.confirm()

      And("User selects and enters transfer details")
      ConnectedPersonsPage.select(ConnectedPersonsPage.Yes)
      ApplyingForReliefPage.select(ApplyingForReliefPage.Yes)
      ReliefApplyingForPage.enterRelief(CRRelief)
      BusinessBuyingInPage.enterValues()
      BuyDatePage.enterDate(buyDate, buyMonth, BuyYear)
      TaxRatePage.select(HalfRate)
      SecuritiesTypePage.select(Shares)
      TransferDetailsPage.enterValues()

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of a user as an Individual with non connected persons") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsIndividual()

      When("User navigates to Submissions start page - Buyer's details")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      ConfirmAddressPage.confirm()

      When("User enters seller's details")
      SellersNamePage.enterName(generateRandomString(10))
      AddressCountryPage.enterCountry(ukCountry)
      FindAddressPage.enterPostCode(getUKPostCode)
      SelectAddressPage.selectAddress()
      ConfirmAddressPage.confirm()

      And("User selects and enters transfer details")
      ConnectedPersonsPage.select(ConnectedPersonsPage.No)
      ApplyingForReliefPage.select(ApplyingForReliefPage.Yes)
      ReliefApplyingForPage.enterRelief(CRRelief)
      BusinessBuyingInPage.enterValues()
      BuyDatePage.enterDate(buyDate, buyMonth, BuyYear)
      TaxRatePage.select(OneAndHalfRate)
      SecuritiesTypePage.select(Shares)
      TransferDetailsPage.enterValues(false)

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of a user as an Individual with no relief option") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsIndividual()

      When("User navigates to Submissions start page - Buyer's details")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      ConfirmAddressPage.confirm()

      When("User enters seller's details")
      SellersNamePage.enterName(generateRandomString(10))
      AddressCountryPage.enterCountry(ukCountry)
      FindAddressPage.enterPostCode(getUKPostCode)
      SelectAddressPage.selectAddress()
      ConfirmAddressPage.confirm()

      And("User selects and enters transfer details")
      ConnectedPersonsPage.select(ConnectedPersonsPage.Yes)
      ApplyingForReliefPage.select(ApplyingForReliefPage.No)
      BusinessBuyingInPage.enterValues()
      BuyDatePage.enterDate(buyDate, buyMonth, BuyYear)
      TaxRatePage.select(HalfRate)
      SecuritiesTypePage.select(Shares)
      TransferDetailsPage.enterValues()

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of a user as an Individual with other securities type") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsIndividual()

      When("User navigates to Submissions start page - Buyer's details")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      ConfirmAddressPage.confirm()

      When("User enters seller's details")
      SellersNamePage.enterName(generateRandomString(10))
      AddressCountryPage.enterCountry(ukCountry)
      FindAddressPage.enterPostCode(getUKPostCode)
      SelectAddressPage.selectAddress()
      ConfirmAddressPage.confirm()

      And("User selects and enters transfer details")
      ConnectedPersonsPage.select(ConnectedPersonsPage.Yes)
      ApplyingForReliefPage.select(ApplyingForReliefPage.Yes)
      ReliefApplyingForPage.enterRelief(CRRelief)
      BusinessBuyingInPage.enterValues()
      BuyDatePage.enterDate(buyDate, buyMonth, BuyYear)
      TaxRatePage.select(OneAndHalfRate)
      SecuritiesTypePage.select(Other)
      TypeOfSecuritiesPage.enterValues()
      HowMuchPaidPage.enterValues()
      TotalMarketValuePage.enterValues()

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of a user as an Individual with other securities type for non connected persons") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsIndividual()

      When("User navigates to Submissions start page - Buyer's details")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      ConfirmAddressPage.confirm()

      When("User enters seller's details")
      SellersNamePage.enterName(generateRandomString(10))
      AddressCountryPage.enterCountry(ukCountry)
      FindAddressPage.enterPostCode(getUKPostCode)
      SelectAddressPage.selectAddress()
      ConfirmAddressPage.confirm()

      And("User selects and enters transfer details")
      ConnectedPersonsPage.select(ConnectedPersonsPage.No)
      ApplyingForReliefPage.select(ApplyingForReliefPage.Yes)
      ReliefApplyingForPage.enterRelief(CRRelief)
      BusinessBuyingInPage.enterValues()
      BuyDatePage.enterDate(buyDate, buyMonth, BuyYear)
      TaxRatePage.select(HalfRate)
      SecuritiesTypePage.select(Other)
      TypeOfSecuritiesPage.enterValues()
      HowMuchPaidPage.enterValues()

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Bulk submission of a user as an Individual") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsIndividual()

      When("User navigates to Submissions start page - Buyer's details")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()
      UploadFileTransfersPage.chooseFile()
      UploadFileTransfersPage.selectUpload()

      // To be replaced with bulk check your answers
      TempPlaceholderFileUploadedPage.verifyPageTitle()
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of an empty bulk file as a user as an Individual") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsIndividual()

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

    Scenario("Submission of a bulk file with errors as a user as an Individual") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsIndividual()

      When("User navigates to Submissions start page - Buyer's details")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore(More)
      HowUseTemplateTransfersPage.selectContinue()
      UploadFileTransfersPage.chooseFile(UploadFileTransfersPage.formattingFile)
      UploadFileTransfersPage.selectUpload()
      
      // Remove next step when navigation has been wired in
      TempPlaceholderFileUploadedPage.navigateTo(BulkErrorPage)
      BulkErrorPage.selectUpload()
      UploadFileTransfersPage.chooseFile()
      UploadFileTransfersPage.selectUpload()

      // To be replaced with bulk check your answers
      TempPlaceholderFileUploadedPage.verifyPageTitle()
      TempPlaceholderFileUploadedPage.navigateTo(CheckYourAnswersPage)
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of a bulk file with formatting errors as a user as an Individual") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsIndividual()

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

    Scenario("Submission of a bulk file with error list as a user as an Individual") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsIndividual()

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
  }
}
