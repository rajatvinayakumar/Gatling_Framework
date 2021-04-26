package Request
import Config.constants
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PSSearchlaunchlogin {

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
  val headers_28 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Accept-Encoding" -> "gzip, deflate",
    "Accept-Language" -> "en-US,en;q=0.9",
    "Origin" -> "http://petstore.smartload.io",
    "Upgrade-Insecure-Requests" -> "1")

  val PSsearchLaunch =   group("BP02_Jpetstore_Search_T01_LaunchURL") {
    exec(http("request_0")
      .get("/applicationPetstore/shopping/main.xhtml")
      .headers(headers_0)

      .check(status.is(200))
      .check(substring("<title>YAPS PetStore</title>").find.exists)
      .check(status.not(404))
      .check(status.in(200,201))
      .check(currentLocationRegex("shopping/main"))
      //.check(bodyBytes.is(RawFileBody("com/rerecordingPackage/recordedsimulationrerecording/0000_response.html")))
    )

  }
.pause(constants.psearchThinkTime)
  val PSsearchLogin = group("BP02_JPetStore_Search_T02_Login") {

    exec(http("Login_1")
      .get("/applicationPetstore/shopping/signon.xhtml;")
      .headers(headers_1)
      .check (regex ("javax.faces.ViewState:2\" value=\"(.+?)\" autocomplete=\"").find.saveAs ("C_ViewState")))


      .exec(http("Login_2")
        .post("/applicationPetstore/shopping/signon.xhtml")
        .headers(headers_28)
        .formParam("j_idt72", "j_idt72")
        .formParam("j_idt72:login", "${P_Username}")
        .formParam("j_idt72:password", "${P_Password}")
        .formParam("j_idt72:j_idt77", "Sign In")
        .formParam("javax.faces.ViewState", "${C_ViewState}")
        .check (regex ("javax.faces.ViewState:0\" value=\"(.+?)\" autocomplete=\"").find.saveAs ("C_ViewState"))
      )

  }
  .pause(constants.psearchThinkTime)
val PSsearchLogout = group("BP02_JPetStore_Search_T04_Logout") {

    exec(http("Logout_1")
      .post("/applicationPetstore/shopping/searchresult.xhtml")
      .headers(headers_28)
      .formParam("j_idt13", "j_idt13")
      .formParam("j_idt13:j_idt39", "fish")
      .formParam("javax.faces.ViewState", "${C_ViewState}")
      .formParam("j_idt13:j_idt29", "j_idt13:j_idt29")

    )

  }
  .pause(constants.psearchThinkTime)
}
