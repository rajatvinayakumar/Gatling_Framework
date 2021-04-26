package Request

import io.gatling.core.Predef.exec
import io.gatling.http.Predef.http
import Config.constants
import io.gatling.core.Predef._

object PSSearchItem {

  val headers_12 = Map(
    "Accept" -> "image/avif,image/webp,image/apng,image/*,*/*;q=0.8",
    "Accept-Encoding" -> "gzip, deflate",
    "Accept-Language" -> "en-US,en;q=0.9")

  val headers_28 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Accept-Encoding" -> "gzip, deflate",
    "Accept-Language" -> "en-US,en;q=0.9",
    "Origin" -> "http://petstore.smartload.io",
    "Upgrade-Insecure-Requests" -> "1")
val pssearchitem = group("BP02_JPetStore_Search_T03_SearchGoldfish") {

  exec(http("Searchfish_1")
    .post("/applicationPetstore/shopping/main.xhtml")
    .headers(headers_28)
    .formParam("j_idt13", "j_idt13")
    .formParam("j_idt13:j_idt39", "${P_SearchItem}")
    .formParam("j_idt13:j_idt42", "Search")
    .formParam("javax.faces.ViewState", "${C_ViewState}"))


}
  .pause(constants.psearchThinkTime)
}
