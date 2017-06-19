package services

import org.scalatestplus.play.PlaySpec
import play.api.db.{Database, Databases}
import play.api.db.evolutions._

/**
  * Created by lepirlouit on 28/10/16.
  */
class DBServiceSpec extends PlaySpec{

  def withMyDatabase[T](block: Database => T) = {
    Databases.withDatabase(
      name = "default",
      driver = "org.postgresql.Driver",
      url = "jdbc:postgresql://localhost:5432/test",
      config = Map(
        "user" -> "test",
        "password" -> "test"
      )
    )(block)
  }

  withMyDatabase { database =>
    val connection = database.getConnection()
    Evolutions.applyEvolutions(database)
    /*println(DBService.getFuelTankHistory("d-F8771").size==1)*/

    connection.prepareStatement("select * from public.fuel_tank_data")
      .executeQuery().next()
    Evolutions.cleanupEvolutions(database)
  }



}
