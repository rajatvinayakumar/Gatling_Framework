package Request
//import java.time.format.DateTimeFormatter


import Config.constants
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PSAddtocart {

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



    // Tag_Search

    val AddToCartSearch = group("BP01_Jpetstore_AddToCart_T03_SearchItem") {
      exec(http("request_19")
        .post("/applicationPetstore/shopping/main.xhtml")
        .headers(headers_15)
        .formParam("j_idt13", "j_idt13")
        .formParam("j_idt13:j_idt39", "${P_SearchItem}")
        .formParam("j_idt13:j_idt42", "Search")
        .formParam("javax.faces.ViewState", "${C_ViewState}")
        .check(regex("showitem.xhtml\\?itemId=(.*?)\"").find.saveAs("C_SelectedItemID"))
        .check(substring("Search for : ").find.exists)
        .check(status.is(200))
        //.check(bodyBytes.is(RawFileBody("com/rerecordingPackage/recordedsimulationrerecording/0019_response.html")))
      )
    }
      .pause(constants.pAddToCartThinkTime)

    // Tag_SelectItem

    val AddToCartSelectItem = group("BP01_Jpetstore_AddToCart_T04_SelectItem") {
      exec(http("request_23")
        .get("/applicationPetstore/shopping/showitem.xhtml?itemId=${C_SelectedItemID}")
        .headers(headers_0)
        //.check(bodyBytes.is(RawFileBody("com/rerecordingPackage/recordedsimulationrerecording/0023_response.html")))
        .check(regex("j_id1:javax.faces.ViewState:0\" value=\"(.*?)\"").find.saveAs("C_CartAddingItemID"))
        .check(substring("Unit cost:").find.exists)
        .check(status.is(200))
      )
    }
      .pause(constants.pAddToCartThinkTime)

    // Tag_ClickAddToCart

    val ClickAddToCart = group("BP01_Jpetstore_AddToCart_T05_ClickAddtoCart") {
      exec(http("request_27")
        .post("/applicationPetstore/shopping/showitem.xhtml")
        .headers(headers_15)
        .formParam("j_idt74", "j_idt74")
        .formParam("javax.faces.ViewState", "${C_CartAddingItemID}")
        .formParam("j_idt74:j_idt83", "j_idt74:j_idt83")
        .formParam("itemId", "${C_SelectedItemID}")
        .check(regex("j_id1:javax.faces.ViewState:0\" value=\"(.*?)\"").find.saveAs("C_CartAddedItemID"))
        .check(substring("Shopping Cart").find.exists)
        .check(status.is(200))
        //.check(bodyBytes.is(RawFileBody("com/rerecordingPackage/recordedsimulationrerecording/0027_response.html")))
      )
    }



}