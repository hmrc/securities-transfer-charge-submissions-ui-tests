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
import uk.gov.hmrc.ui.pages.Common.{AboutYourSecuritiesTransfersPage, AuthWizard}
import uk.gov.hmrc.ui.pages.Sh03.RolePurchasingCompany.*
import uk.gov.hmrc.ui.pages.Sh03.SharePurchase.ToPlaceIntoTreasury
import uk.gov.hmrc.ui.pages.Sh03.*
import uk.gov.hmrc.ui.pages.Single.*
import uk.gov.hmrc.ui.tags.Smoke
import uk.gov.hmrc.ui.util.TestDataConstants.*

class S7SH03AgentSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SH03 Agent Journeys") {
    Scenario("Submission of a user as an Agent", Smoke) {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to SH03 start page")
      SubmissionsDashboardPage.createNewSh03()
      BeforeYouStart.clickOnContinue()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      AddAReference.enterValue()

      And("User selects and enters transfer details")
      CompanyDetails.enterValues()
      SharePurchase.select()
      TreasuryShares.select()
      ConnectedPersonsPage.select(ConnectedPersonsPage.Yes)
      ApplyingForReliefPage.select(ApplyingForReliefPage.Yes)
      ReliefApplyingForPage.enterRelief(CRRelief)
      TransferDetailsPage.enterValues()
      MaximumAmountShares.enterMaxValue()
      MinimumAmountShares.enterMinValue()
      BuyDatePage.enterDate(buyDate, buyMonth, BuyYear)
      RolePurchasingCompany.select()

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of a user as an Agent - No Treasury shares") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to SH03 start page")
      SubmissionsDashboardPage.createNewSh03()
      BeforeYouStart.clickOnContinue()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      AddAReference.enterValue()

      And("User selects and enters transfer details")
      CompanyDetails.enterValues()
      SharePurchase.select()
      TreasuryShares.select(TreasuryShares.No)
      ConnectedPersonsPage.select(ConnectedPersonsPage.Yes)
      ApplyingForReliefPage.select(ApplyingForReliefPage.Yes)
      ReliefApplyingForPage.enterRelief(CRRelief)
      TransferDetailsPage.enterValues()
      MaximumAmountShares.enterMaxValue()
      MinimumAmountShares.enterMinValue()
      BuyDatePage.enterDate(buyDate, buyMonth, BuyYear)
      RolePurchasingCompany.select(Secretary)

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of a user as an Agent - To place into treasury") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs("agent")

      When("User navigates to SH03 start page")
      SubmissionsDashboardPage.createNewSh03()
      BeforeYouStart.clickOnContinue()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      AddAReference.enterValue()

      And("User selects and enters transfer details")
      CompanyDetails.enterValues()
      SharePurchase.select(ToPlaceIntoTreasury)
      ConnectedPersonsPage.select(ConnectedPersonsPage.Yes)
      ApplyingForReliefPage.select(ApplyingForReliefPage.Yes)
      ReliefApplyingForPage.enterRelief(CRRelief)
      TransferDetailsPage.enterValues()
      MaximumAmountShares.enterMaxValue()
      MinimumAmountShares.enterMinValue()
      BuyDatePage.enterDate(buyDate, buyMonth, BuyYear)
      RolePurchasingCompany.select(PersonAuthorised)

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of a user as an Agent - No PLC") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to SH03 start page")
      SubmissionsDashboardPage.createNewSh03()
      BeforeYouStart.clickOnContinue()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      AddAReference.enterValue()

      And("User selects and enters transfer details")
      CompanyDetails.enterValues(CompanyDetails.No)
      SharePurchase.select()
      TreasuryShares.select()
      ConnectedPersonsPage.select(ConnectedPersonsPage.Yes)
      ApplyingForReliefPage.select(ApplyingForReliefPage.Yes)
      ReliefApplyingForPage.enterRelief(CRRelief)
      TransferDetailsPage.enterValues()
      BuyDatePage.enterDate(buyDate, buyMonth, BuyYear)
      RolePurchasingCompany.select(Administrator)

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of a user as an Agent - Receiver") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to SH03 start page")
      SubmissionsDashboardPage.createNewSh03()
      BeforeYouStart.clickOnContinue()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      AddAReference.enterValue()

      And("User selects and enters transfer details")
      CompanyDetails.enterValues()
      SharePurchase.select()
      TreasuryShares.select()
      ConnectedPersonsPage.select(ConnectedPersonsPage.Yes)
      ApplyingForReliefPage.select(ApplyingForReliefPage.Yes)
      ReliefApplyingForPage.enterRelief(CRRelief)
      TransferDetailsPage.enterValues()
      MaximumAmountShares.enterMaxValue()
      MinimumAmountShares.enterMinValue()
      BuyDatePage.enterDate(buyDate, buyMonth, BuyYear)
      RolePurchasingCompany.select(Receiver)

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of a user as an Agent - Receiver manager") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to SH03 start page")
      SubmissionsDashboardPage.createNewSh03()
      BeforeYouStart.clickOnContinue()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      AddAReference.enterValue()

      And("User selects and enters transfer details")
      CompanyDetails.enterValues()
      SharePurchase.select()
      TreasuryShares.select()
      ConnectedPersonsPage.select(ConnectedPersonsPage.Yes)
      ApplyingForReliefPage.select(ApplyingForReliefPage.Yes)
      ReliefApplyingForPage.enterRelief(CRRelief)
      TransferDetailsPage.enterValues()
      MaximumAmountShares.enterMaxValue()
      MinimumAmountShares.enterMinValue()
      BuyDatePage.enterDate(buyDate, buyMonth, BuyYear)
      RolePurchasingCompany.select(ReceiverManager)

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of a user as an Agent - CIC Manager") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to SH03 start page")
      SubmissionsDashboardPage.createNewSh03()
      BeforeYouStart.clickOnContinue()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      AddAReference.enterValue()

      And("User selects and enters transfer details")
      CompanyDetails.enterValues()
      SharePurchase.select()
      TreasuryShares.select()
      ConnectedPersonsPage.select(ConnectedPersonsPage.Yes)
      ApplyingForReliefPage.select(ApplyingForReliefPage.Yes)
      ReliefApplyingForPage.enterRelief(CRRelief)
      TransferDetailsPage.enterValues()
      MaximumAmountShares.enterMaxValue()
      MinimumAmountShares.enterMinValue()
      BuyDatePage.enterDate(buyDate, buyMonth, BuyYear)
      RolePurchasingCompany.select(CICManager)

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of a user as an Agent - UKSocietas") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to SH03 start page")
      SubmissionsDashboardPage.createNewSh03()
      BeforeYouStart.clickOnContinue()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      AddAReference.enterValue()

      And("User selects and enters transfer details")
      CompanyDetails.enterValues()
      SharePurchase.select()
      TreasuryShares.select()
      ConnectedPersonsPage.select(ConnectedPersonsPage.Yes)
      ApplyingForReliefPage.select(ApplyingForReliefPage.Yes)
      ReliefApplyingForPage.enterRelief(CRRelief)
      TransferDetailsPage.enterValues()
      MaximumAmountShares.enterMaxValue()
      MinimumAmountShares.enterMinValue()
      BuyDatePage.enterDate(buyDate, buyMonth, BuyYear)
      RolePurchasingCompany.select(UKSocietas)

      Then("User verifies check your answers for details entered")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Submission of a user as an Agent - Not Provided") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAs(affinityAgent)

      When("User navigates to SH03 start page")
      SubmissionsDashboardPage.createNewSh03()
      BeforeYouStart.clickOnContinue()
      AboutYourSecuritiesTransfersPage.selectOneOrMore()
      AddAReference.enterValue()

      And("User selects and enters transfer details")
      CompanyDetails.enterValues()
      SharePurchase.select()
      TreasuryShares.select()
      ConnectedPersonsPage.select(ConnectedPersonsPage.Yes)
      ApplyingForReliefPage.select(ApplyingForReliefPage.Yes)
      ReliefApplyingForPage.enterRelief(CRRelief)
      TransferDetailsPage.enterValues()
      MaximumAmountShares.enterMaxValue()
      MinimumAmountShares.enterMinValue()
      BuyDatePage.enterDate(buyDate, buyMonth, BuyYear)
      RolePurchasingCompany.select(NotProvided)

      Then("User verifies check your answers for details entered")
      YouCannotSubmitThisForm.verify(CannotSubmitForm)
    }
  }
}
