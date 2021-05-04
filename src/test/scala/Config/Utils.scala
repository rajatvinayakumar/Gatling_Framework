package Config

import ch.qos.logback.classic.{Level, LoggerContext}
import org.slf4j.LoggerFactory

object Utils {

  def debugInfo() ={
    if (constants.isdebug){
      val context: LoggerContext=LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
      context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    }

  }

}
