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

package uk.gov.hmrc.ui.util

object TestDataConstants {

  // ==== Auth
  // --  Confidence level
  final val lowConfidence: String  = "50"
  final val highConfidence: String = "250"

  // -- Affinity Group
  final val affinityIndividual: String   = "Individual"
  final val affinityOrganisation: String = "Organisation"
  final val affinityAgent: String        = "Agent"

  // -- Enrolment
  final val enrolmentsEnrolmentKey: String    = "HMRC-STC-ORG"
  final val enrolmentsIdentifierName: String  = "STCID"
  final val enrolmentsIdentifierValue: String = "STC0123456789"

  // ==== Test data
  // -- Common
  final val buyDate: String                     = "01"
  final val buyMonth: String                    = "01"
  final val BuyYear: String                     = "2026"
  final val ukCountry: String                   = "United Kingdom"
  final val nonUkCountry: String                = "India"
  final val emailAddress: String                = "abcd@xyz.com"
  final val contactNumber: String               = "+44 1234567890"
  final val checkYourAnswers: String            = "Check your answers"
  final val addressLine1: String                = "A1"
  final val nonUkPostCode: String               = "123456"
  final val companyRegistrationNumber: String   = "AB123456"
  final val companyRegistrationNumberSL: String = "01234567"
  final val companyRegistrationNumberRS: String = "21436587"
  final val utr: String                         = "1234567890"
  final val utrRS: String                       = "5432167812"
  final val postcodeSL: String                  = "AA1 1AA"
  final val CRRelief: String                    = "Charities Relief"
  final val validCRN: String                    = "SN898989"

  final val waitFor1Sec: Int = 1000
  final val waitFor5Sec: Int = 5000

  // -- Local
  final val ukPostCode: String = "ZZ11ZZ"

  // -- QA
  final val ukPostCodeQA: String = "NE32 5JU"
}
