#!/usr/bin/env bash

BROWSER=$1
ENVIRONMENT=$2
sbt scalafmtAll scalafmtCheckAll scalafmtSbtCheck clean compile -Dbrowser="${BROWSER:=chrome}" -Denvironment="${ENVIRONMENT:=qa}" -Dbrowser.option.headless=true "testOnly uk.gov.hmrc.ui.specs.S1SubmissionsIndividualSpec uk.gov.hmrc.ui.specs.S2SubmissionsBusinessSpec uk.gov.hmrc.ui.specs.S3SubmissionsAgentSpec" testReport

