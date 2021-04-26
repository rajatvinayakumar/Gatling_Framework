package Scenario
import Config.constants
import Request._
import io.gatling.core.Predef._
import io.gatling.http.Predef._



object BP02_Jpetstore_Search {
    val scn2 = scenario("Search").exec(flushHttpCache).exec(flushSessionCookies).repeat(constants.pRepeatTimes1) {
      pace(constants.pPacing1)

        .exec(feed(constants.puserdetails.circular)).exec(feed(constants.psearchcriteria.circular))

        .exitBlockOnFail {
          exec(
            PSSearchlaunchlogin.PSsearchLaunch,
            PSSearchlaunchlogin.PSsearchLogin,
            PSSearchItem.pssearchitem,
            PSSearchlaunchlogin.PSsearchLogout
          )
        }
    }
  }

