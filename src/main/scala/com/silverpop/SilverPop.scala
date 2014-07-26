package com.silverpop

import silverpop.{SelectRecipientRequestType, NONE, Sessionheadertype, SessionMgmtResponseType}

import scalaxb.DataRecord

/**
 * Created by whatamidoing on 26/07/2014.
 */
object SilverPop extends App {


  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent._
  import scala.concurrent.duration._

  val service = (new silverpop.SilverPop_Engage_SoapApi_client_Bindings  with
    scalaxb.Soap11ClientsAsync with
    scalaxb.DispatchHttpClientsAsync {}).service

  val username = "xxx@xxxxx.com"
  val password = "xxxxx"
  val resp: Future[SessionMgmtResponseType] = service.login(username,password)

  val response = Await.result(resp, 5 seconds)
  response.SESSIONID match {
    case Some(sessionId) =>
        println(response.SESSIONID)
    case None =>
      println(response.Fault.get.faulttypesequence1.get.FaultString)
  }

}
