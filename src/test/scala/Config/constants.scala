package Config

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef.{csv, _}

object  constants {
  val config = ConfigFactory.load("app.properties")

  //****************application details****************

  val pbaseurl =  config.getString("baseurl")
  val isdebug =config.getString("isDebug").toBoolean

  //****************common thinktime - generic use ****************
  val pCThinkTime = config.getString("pausebetweenrequestsC").toInt

  //****************************************AddToCart****************************************
  val pAddToCartuserdetails = csv(config.getString("paddtocartUseridPassword"))
  val pAddToCartsearchcriteria = csv(config.getString("paddtocartitemdetails"))
  val pAddToCartThinkTime = config.getString("pausebetweenrequestsAddtocart").toInt
  val pAddToCartRepeatTimes1 = config.getString("repeat1").toInt
  val pAddToCartPacing = config.getString("PaceinS1").toInt

  //****************************************Search details****************************************

  val puserdetails = csv(config.getString("pSsearchUseridPassword"))
  val psearchcriteria = csv(config.getString("pssearchitemdetails"))
  val psearchThinkTime = config.getString("pausebetweenrequestsSearch").toInt
  val pRepeatTimes1 = config.getString("repeat2").toInt
  val pPacing1 = config.getString("PaceinS2").toInt


}


