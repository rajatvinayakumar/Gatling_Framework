package Scenario

import Config.constants
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import Request._



object BP01_Jpetstore_AddToCart {


  val scn1 = scenario("AddToCart").exec(flushHttpCache).exec(flushSessionCookies).repeat(constants.pAddToCartRepeatTimes1)  {
    pace(constants.pAddToCartPacing)

    .exec(feed(constants.pAddToCartuserdetails.circular)).exec(feed(constants.pAddToCartsearchcriteria.circular))

      .exitBlockOnFail{
        exec(


          PSAddtocartlaunchlogin.AddToCartLaunch,
          PSAddtocartlaunchlogin.AddToCartLogin,

          PSAddtocart.AddToCartSearch,
          PSAddtocart.AddToCartSelectItem,
          PSAddtocart.ClickAddToCart,

          PSAddtocartlaunchlogin.AddToCartLogout

          )
      }

} }
