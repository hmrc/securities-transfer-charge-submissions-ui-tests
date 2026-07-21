import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"   %% "ui-test-runner" % "0.53.0" % Test,
    "org.apache.poi" % "poi-ooxml"      % "5.5.1"  % Test
  )

}
