package scalaxb

import concurrent.Future

trait DispatchHttpClientsAsync extends HttpClientsAsync {
  lazy val httpClient = new DispatchHttpClient {}

  trait DispatchHttpClient extends HttpClient {
    import dispatch._, Defaults._

    val http = new Http()
    def request(in: String, address: java.net.URI, headers: Map[String, String]): concurrent.Future[String] = {
      val req = url(address.toString).setBodyEncoding("UTF-8") <:< headers << in
      http(req > as.String)
    }
  }
}
