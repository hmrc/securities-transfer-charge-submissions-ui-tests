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

package uk.gov.hmrc.ui.util

object Env {
  val env: String = Option(System.getProperty("environment")).map(_.toLowerCase).getOrElse("local")

  val baseUrl: String = env match {
    case "dev"     => "https://www.development.tax.service.gov.uk/"
    case "local"   => "http://localhost:9949/"
    case "qa"      => "https://www.qa.tax.service.gov.uk/"
    case "staging" => "https://www.staging.tax.service.gov.uk/"
    case _         => "http://localhost:9949/"
  }
}
