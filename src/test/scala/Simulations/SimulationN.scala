package Simulations

import Request._
import Config._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import java.time.format.DateTimeFormatter
import java.util.concurrent._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import Scenario._

import scala.concurrent.duration._
import scala.language.postfixOps

class SimulationN extends Simulation {

	before {
		println("Simulation is about to start!")
		Utils.debugInfo()
	}



	val httpProtocol = http
		.baseUrl(constants.pbaseurl)
		//.proxy(Proxy("empweb1.ey.net", 8080).httpsPort(8443))
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36")


	//----------------------------------------------------BP01_Addtocart------------------------------------------------------//
	//*****************************************************Smoke Test***********************************************************************//
	//setUp(BP01_Jpetstore_AddToCart.scn1.inject(rampUsers(1) during (1 minutes))).maxDuration(60 minutes).protocols(httpProtocol)
	//*****************************************************Baseline Test********************************************************************//
	//setUp(BP01_Jpetstore_AddToCart.scn1.inject(rampUsers(10) during (10 minutes))).maxDuration(90 minutes).protocols(httpProtocol)


	//----------------------------------------------------BP02_Search------------------------------------------------------//
	//*****************************************************Smoke Test***********************************************************************//
	//setUp(BP02_Jpetstore_Search.scn2.inject(rampUsers(1) during (1 minutes))).maxDuration(60 minutes).protocols(httpProtocol)
	//*****************************************************Baseline Test********************************************************************//
	//setUp(BP02_Jpetstore_Search.scn2.inject(rampUsers(10) during (10 minutes))).maxDuration(90 minutes).protocols(httpProtocol)

	//Sprint N
	//----------------------------------------------------Load Test ------------------------------------------------------//

	   setUp(
			 BP01_Jpetstore_AddToCart.scn1.inject(nothingFor(1 minutes),rampUsers(2) during (1 minutes)),
			 BP02_Jpetstore_Search.scn2.inject(nothingFor(2 minutes), rampUsers(2) during (1 minutes))
	).maxDuration(10 minutes).protocols(httpProtocol)
			 .assertions(
				 global.responseTime.max.lt(10000),
				 forAll.failedRequests.count.lt(5),
				 details("BP02_JPetStore_Search_T02_Login" / "Login_1").successfulRequests.percent.gt(90)
			 )

	after{
		println("Simulation is finished")
	}
}