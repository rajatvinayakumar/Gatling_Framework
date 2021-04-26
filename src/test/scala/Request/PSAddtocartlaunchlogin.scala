package Request
//import java.time.format.DateTimeFormatter


import Config.constants
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PSAddtocartlaunchlogin {

  val headers_0 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Proxy-Connection" -> "keep-alive",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_1 = Map(
    "Accept" -> "text/css,*/*;q=0.1",
    "Proxy-Connection" -> "keep-alive")

  val headers_5 = Map("Proxy-Connection" -> "keep-alive")

  val headers_6 = Map(
    "Origin" -> "http://petstore.smartload.io",
    "Proxy-Connection" -> "keep-alive")

  val headers_15 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Cache-Control" -> "max-age=0",
    "Origin" -> "http://petstore.smartload.io",
    "Proxy-Connection" -> "keep-alive",
    "Upgrade-Insecure-Requests" -> "1")

  //val temp_username=csv("data/UserNamePasswordList.csv").circular // .queue , .circular , .random , .shuffle
  //val temp_searchitem=csv("data/SearchCriteriaList.csv").circular


    // Launch ///


    val AddToCartLaunch = group("BP01_Jpetstore_AddToCart_T01_LaunchURL") {
      exec(http("request_0")
        .get("/applicationPetstore/shopping/main.xhtml")
        .headers(headers_0)
        //.check(regex("</button><a href=\"(.*?)\" class=\"navbar-brand\">").find.saveAs("C_JsessionID"))
        //.check(regex("signon\\.xhtml;jsessionid=(.*?)\"").find.saveAs("C_jsessionId"))
        .check(status.is(200))
        .check(substring("<title>YAPS PetStore</title>").find.exists)
        .check(status.not(404))
        .check(status.in(200, 201))
        .check(currentLocationRegex("shopping/main"))
        //.check(bodyBytes.is(RawFileBody("com/rerecordingPackage/recordedsimulationrerecording/0000_response.html")))
      )
    }

      .pause(constants.pAddToCartThinkTime)

    // Login ///

    val AddToCartLogin =  group("BP01_Jpetstore_AddToCart_T02_LaunchURL") {
      exec(http("Login_1")
        .get("/applicationPetstore/shopping/signon.xhtml;")
        .headers(headers_0)
        .check(regex("javax.faces.ViewState:2\" value=\"(.+?)\" autocomplete=\"").find.saveAs("C_ViewState")))


        .exec(http("Login_2")
          .post("/applicationPetstore/shopping/signon.xhtml")
          .headers(headers_15)
          .formParam("j_idt72", "j_idt72")
          .formParam("j_idt72:login", "${P_Username}")
          .formParam("j_idt72:password", "${P_Password}")
          .formParam("j_idt72:j_idt77", "Sign In")
          .formParam("javax.faces.ViewState", "${C_ViewState}")
          .check(regex("javax.faces.ViewState:0\" value=\"(.+?)\" autocomplete=\"").find.saveAs("C_ViewState"))
        )
    }

      .pause(constants.pAddToCartThinkTime)

    // tag_logOut
    val AddToCartLogout = group("BP01_Jpetstore_AddToCart_T06_LaunchURL") {
      exec(http("request_31")
        //.post("/applicationPetstore/shopping/showcart.xhtml?cid=1")
        .post("/applicationPetstore/shopping/showcart.xhtml")
        .headers(headers_15)
        .formParam("j_idt13", "j_idt13")
        .formParam("j_idt13:j_idt39", "${P_SearchItem}")
        .formParam("javax.faces.ViewState", "${C_CartAddedItemID}")
        .formParam("j_idt13:j_idt29", "j_idt13:j_idt29")
        .check(substring("Log in").find.exists)
        .check(status.is(200))
        //.check(bodyBytes.is(RawFileBody("com/rerecordingPackage/recordedsimulationrerecording/0031_response.html")))
      )
    }

}