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
import uk.gov.hmrc.ui.pages.Common.{AboutYourSecuritiesTransfersPage, AddAReference, AuthWizard}
import uk.gov.hmrc.ui.pages.Single.SecuritiesTypePage.{No, Yes}
import uk.gov.hmrc.ui.pages.Single.TaxRatePage.{HalfRate, OneAndHalfRate}
import uk.gov.hmrc.ui.pages.Single.*
import uk.gov.hmrc.ui.tags.Smoke
import uk.gov.hmrc.ui.util.TestDataConstants.*
import uk.gov.hmrc.ui.util.TestDataGenerator.{generateRandomString, getUKPostCode}

class S3SubmissionsAgentSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("STC Agent Journeys") {
    Scenario("Submission of a user as an Agent", Smoke) {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      AddAReference.enterValue()

      When("User enters Buyers's details")
      BuyersNamePage.enterName(generateRandomString(10))
      AddressCountryPage.enterCountry(ukCountry)
      FindAddressPage.enterPostCode(getUKPostCode)
      SelectAddressPage.selectAddress()
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
      BuyDatePage.enterDate()
      TaxRatePage.select(HalfRate)
      SecuritiesTypePage.select(Yes)
      TransferDetailsPage.enterValues()

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verifyDues(checkYourAnswers)
    }

    Scenario(
      "Submission of a user as an Agent - Add buyer's & seller's address by manually entering UK address"
    ) {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to Submissions start page - Edit Buyer's details")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      AddAReference.enterValue()

      When("User enters Buyers's details")
      BuyersNamePage.enterName(generateRandomString(10))
      AddressCountryPage.enterCountry(ukCountry)
      FindAddressPage.clickEnterTheAddressManually()
      EnterAddressPage.enterAddressDetails(addressLine1, getUKPostCode)
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
      BuyDatePage.enterDate()
      TaxRatePage.select(OneAndHalfRate)
      SecuritiesTypePage.select(Yes)
      TransferDetailsPage.enterValues()

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verifyDues(checkYourAnswers)
    }

    Scenario("Submission of a user as an Agent - Edit and update buyer's & seller's address to non UK address") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      AddAReference.enterValue()

      When("User enters Buyers's details")
      BuyersNamePage.enterName(generateRandomString(10))
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
      BuyDatePage.enterDate()
      TaxRatePage.select(HalfRate)
      SecuritiesTypePage.select(Yes)
      TransferDetailsPage.enterValues()

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verifyDues(checkYourAnswers)
    }

    Scenario("Submission of a user as an Agent with non connected persons") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      AddAReference.enterValue()

      When("User enters Buyers's details")
      BuyersNamePage.enterName(generateRandomString(10))
      AddressCountryPage.enterCountry(ukCountry)
      FindAddressPage.enterPostCode(getUKPostCode)
      SelectAddressPage.selectAddress()
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
      BuyDatePage.enterDate()
      TaxRatePage.select(OneAndHalfRate)
      SecuritiesTypePage.select(Yes)
      TransferDetailsPage.enterValues(false)

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verifyDues(checkYourAnswers)
    }

    Scenario("Submission of a user as an Agent with no relief option") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      AddAReference.enterValue()

      When("User enters Buyers's details")
      BuyersNamePage.enterName(generateRandomString(10))
      AddressCountryPage.enterCountry(ukCountry)
      FindAddressPage.enterPostCode(getUKPostCode)
      SelectAddressPage.selectAddress()
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
      BuyDatePage.enterDate()
      TaxRatePage.select(HalfRate)
      SecuritiesTypePage.select(Yes)
      TransferDetailsPage.enterValues()

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verifyDues(checkYourAnswers)
    }

    Scenario("Submission of a user as an Agent with other securities type") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      AddAReference.enterValue()

      When("User enters Buyers's details")
      BuyersNamePage.enterName(generateRandomString(10))
      AddressCountryPage.enterCountry(ukCountry)
      FindAddressPage.enterPostCode(getUKPostCode)
      SelectAddressPage.selectAddress()
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
      BuyDatePage.enterDate()
      TaxRatePage.select(OneAndHalfRate)
      SecuritiesTypePage.select(No)
      TypeOfSecuritiesPage.enterValues()
      HowMuchPaidPage.enterValues()
      TotalMarketValuePage.enterValues()

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verifyDues(checkYourAnswers)
    }

    Scenario("Submission of a user as an Agent with other securities type for non connected persons") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to Submissions start page")
      SubmissionsDashboardPage.createNewSubmission()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      AddAReference.enterValue()

      When("User enters Buyers's details")
      BuyersNamePage.enterName(generateRandomString(10))
      AddressCountryPage.enterCountry(ukCountry)
      FindAddressPage.enterPostCode(getUKPostCode)
      SelectAddressPage.selectAddress()
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
      BuyDatePage.enterDate()
      TaxRatePage.select(HalfRate)
      SecuritiesTypePage.select(No)
      TypeOfSecuritiesPage.enterValues()
      HowMuchPaidPage.enterValues()

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verifyDues(checkYourAnswers)
    }
  }
}
