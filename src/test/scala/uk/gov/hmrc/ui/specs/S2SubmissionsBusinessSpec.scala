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
import uk.gov.hmrc.ui.pages.CommonPages.{AuthWizard, CheckYourAnswersPage, RegistrationPage}
import uk.gov.hmrc.ui.pages.businessPages.*
import uk.gov.hmrc.ui.pages.businessPages.SelectYourBusinessPartnershipTypePage.*
import uk.gov.hmrc.ui.pages.businessPages.SelectYourBusinessTypePage.*
import uk.gov.hmrc.ui.pages.individualPages.EnterAddressPage
import uk.gov.hmrc.ui.util.TestDataConstants.*
import uk.gov.hmrc.ui.util.TestDataGenerator.getUKPostCode

class S2SubmissionsBusinessSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("STC frontend Organisation Journeys") {

    Scenario("Register a user as a Business") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsOrganisation()

      When("User navigates to Registration start page")
      RegistrationPage.startRegistration()
      UkOrNotPage.confirmDetails()

      And("User selects business type")
      SelectYourBusinessTypePage.selectType()

      And("User navigates through GRS flow")
      GRSCompanyRegistrationPage.enterCompanyRegistrationNumber(companyRegistrationNumber)
      GRSConfirmYourBusinessPage.confirmDetails()
      GRSYourUTRPage.enterUTR(utr)
      GRSCheckYourAnswersPage.clickContinue()

      And("User enters the required values - address, email, contact")
      YourCompanyAddressPage.enterCountry(ukCountry)
      FindYourCompanysAddressPage.enterPostCode(getUKPostCode)
      SelectYourCompanysAddressPage.selectAddress()
      ConfirmYourCompanysAddressPage.confirm()
      CompanyEmailAddressPage.enterEmailAddress(emailAddress)
      CompanyContactNumberPage.enterContactNumber(contactNumber)

      Then("User verifies success message is displayed")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Register a user as a Business Using Manual Address Entry") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsOrganisation()

      When("User navigates to Registration start page")
      RegistrationPage.startRegistration()
      UkOrNotPage.confirmDetails()

      And("User selects business type")
      SelectYourBusinessTypePage.selectType()

      And("User navigates through GRS flow")
      GRSCompanyRegistrationPage.enterCompanyRegistrationNumber(companyRegistrationNumber)
      GRSConfirmYourBusinessPage.confirmDetails()
      GRSYourUTRPage.enterUTR(utr)
      GRSCheckYourAnswersPage.clickContinue()

      And("User enters the required values - address, email, contact")
      YourCompanyAddressPage.enterCountry(ukCountry)
      FindYourCompanysAddressPage.clickEnterTheAddressManually()
      EnterAddressPage.enterAddressDetails(addressLine1, getUKPostCode)
      ConfirmYourCompanysAddressPage.confirm()
      CompanyEmailAddressPage.enterEmailAddress(emailAddress)
      CompanyContactNumberPage.enterContactNumber(contactNumber)

      Then("User verifies success message is displayed")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Register a user as a Business Using Non UK Address") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsOrganisation()

      When("User navigates to Registration start page")
      RegistrationPage.startRegistration()
      UkOrNotPage.confirmDetails()

      And("User selects business type")
      SelectYourBusinessTypePage.selectType()

      And("User navigates through GRS flow")
      GRSCompanyRegistrationPage.enterCompanyRegistrationNumber(companyRegistrationNumber)
      GRSConfirmYourBusinessPage.confirmDetails()
      GRSYourUTRPage.enterUTR(utr)
      GRSCheckYourAnswersPage.clickContinue()

      And("User enters the required values - address, email, contact")
      YourCompanyAddressPage.enterCountry(nonUkCountry)
      EnterAddressPage.enterAddressDetails(addressLine1, nonUkPostCode)
      ConfirmYourCompanysAddressPage.confirm()
      CompanyEmailAddressPage.enterEmailAddress(emailAddress)
      CompanyContactNumberPage.enterContactNumber(contactNumber)

      Then("User verifies success message is displayed")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    /*Various GRS flows*/
    /*Various Partnership flows*/
    Scenario("Register a user as a Business - Partnership : Scottish Limited") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsOrganisation()

      When("User navigates to Registration start page")
      RegistrationPage.startRegistration()
      UkOrNotPage.confirmDetails()

      And("User selects business type")
      SelectYourBusinessTypePage.selectType(Partnership)

      And("User navigates through GRS flow")
      SelectYourBusinessPartnershipTypePage.selectType()
      GRSCompanyRegistrationPage.enterCompanyRegistrationNumber(companyRegistrationNumberSL)
      GRSConfirmYourBusinessLimitedPage.confirmDetails()
      GRSYourUTRLimitedPage.enterUTR(utr)
      GRSPostcodePartnershipPage.enterPostcode(postcodeSL)
      GRSCheckYourAnswersPage.clickContinue()

      And("User enters the required values - address, email, contact")
      YourCompanyAddressPage.enterCountry(ukCountry)
      FindYourCompanysAddressPage.enterPostCode(getUKPostCode)
      SelectYourCompanysAddressPage.selectAddress()
      ConfirmYourCompanysAddressPage.confirm()
      CompanyEmailAddressPage.enterEmailAddress(emailAddress)
      CompanyContactNumberPage.enterContactNumber(contactNumber)

      Then("User verifies success message is displayed")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Register a user as a Business - Partnership : Limited") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsOrganisation()

      When("User navigates to Registration start page")
      RegistrationPage.startRegistration()
      UkOrNotPage.confirmDetails()

      And("User selects business type")
      SelectYourBusinessTypePage.selectType(Partnership)

      And("User navigates through GRS flow")
      SelectYourBusinessPartnershipTypePage.selectType(Limited)
      GRSCompanyRegistrationPage.enterCompanyRegistrationNumber(companyRegistrationNumberSL)
      GRSConfirmYourBusinessLimitedPage.confirmDetails()
      GRSYourUTRLimitedPage.enterUTR(utr)
      GRSPostcodePartnershipPage.enterPostcode(postcodeSL)
      GRSCheckYourAnswersPage.clickContinue()

      And("User enters the required values - address, email, contact")
      YourCompanyAddressPage.enterCountry(ukCountry)
      FindYourCompanysAddressPage.enterPostCode(getUKPostCode)
      SelectYourCompanysAddressPage.selectAddress()
      ConfirmYourCompanysAddressPage.confirm()
      CompanyEmailAddressPage.enterEmailAddress(emailAddress)
      CompanyContactNumberPage.enterContactNumber(contactNumber)

      Then("User verifies success message is displayed")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Register a user as a Business - Partnership : Limited Liability") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsOrganisation()

      When("User navigates to Registration start page")
      RegistrationPage.startRegistration()
      UkOrNotPage.confirmDetails()

      And("User selects business type")
      SelectYourBusinessTypePage.selectType(Partnership)

      And("User navigates through GRS flow")
      SelectYourBusinessPartnershipTypePage.selectType(LimitedLiability)
      GRSCompanyRegistrationPage.enterCompanyRegistrationNumber(companyRegistrationNumberSL)
      GRSConfirmYourBusinessLimitedPage.confirmDetails()
      GRSYourUTRLimitedPage.enterUTR(utr)
      GRSPostcodePartnershipPage.enterPostcode(postcodeSL)
      GRSCheckYourAnswersPage.clickContinue()

      And("User enters the required values - address, email, contact")
      YourCompanyAddressPage.enterCountry(ukCountry)
      FindYourCompanysAddressPage.enterPostCode(getUKPostCode)
      SelectYourCompanysAddressPage.selectAddress()
      ConfirmYourCompanysAddressPage.confirm()
      CompanyEmailAddressPage.enterEmailAddress(emailAddress)
      CompanyContactNumberPage.enterContactNumber(contactNumber)

      Then("User verifies success message is displayed")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Register a user as a Business - Trust") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsOrganisation()

      When("User navigates to Registration start page")
      RegistrationPage.startRegistration()
      UkOrNotPage.confirmDetails()

      And("User selects business type")
      SelectYourBusinessTypePage.selectType(Trust)

      And("User navigates through GRS flow")
      GRSYourUTRTrustPage.enterUTR(utr)
      GRSPostcodeTrustPage.enterPostcode(postcodeSL)
      GRSCheckYourAnswersPage.clickContinue()

      And("User enters the required values - address, email, contact")
      YourCompanyAddressPage.enterCountry(ukCountry)
      FindYourCompanysAddressPage.enterPostCode(getUKPostCode)
      SelectYourCompanysAddressPage.selectAddress()
      ConfirmYourCompanysAddressPage.confirm()
      CompanyEmailAddressPage.enterEmailAddress(emailAddress)
      CompanyContactNumberPage.enterContactNumber(contactNumber)

      Then("User verifies success message is displayed")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    Scenario("Register a user as a Business - Registered Society") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsOrganisation()

      When("User navigates to Registration start page")
      RegistrationPage.startRegistration()
      UkOrNotPage.confirmDetails()

      And("User selects business type")
      SelectYourBusinessTypePage.selectType(RegisteredSociety)

      And("User navigates through GRS flow")
      GRSCompanyRegistrationPage.enterCompanyRegistrationNumber(companyRegistrationNumberRS)
      GRSConfirmYourBusinessPage.confirmDetails()
      GRSYourUTRPage.enterUTR(utrRS)
      GRSCheckYourAnswersPage.clickContinue()

      And("User enters the required values - address, email, contact")
      YourCompanyAddressPage.enterCountry(ukCountry)
      FindYourCompanysAddressPage.enterPostCode(getUKPostCode)
      SelectYourCompanysAddressPage.selectAddress()
      ConfirmYourCompanysAddressPage.confirm()
      CompanyEmailAddressPage.enterEmailAddress(emailAddress)
      CompanyContactNumberPage.enterContactNumber(contactNumber)

      Then("User verifies success message is displayed")
      CheckYourAnswersPage.verify(checkYourAnswers)
    }

    /*Test data to be updated*/
//    Scenario("Register a user as a Business - Unincorporated Association") {
//      Given("User enters login using the Authority Wizard page")
//      AuthWizard.loginAsOrganisation()
//
//      When("User navigates to Registration start page")
//      RegistrationPage.startRegistration()
//      UkOrNotPage.confirmDetails()
//
//      And("User enters the required values - DOB, address, email, contact")
//      SelectYourBusinessTypePage.confirmDetails(UnincorporatedAssociation)
//    }
  }
}
