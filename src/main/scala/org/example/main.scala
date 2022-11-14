package org.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Dataset

object main extends App{

  val spark = SparkSession.builder()
    .master("local[*]")
    .appName("FlightDuration")
    .getOrCreate()

  import spark.implicits._

  val flightInputData = org.example.flightInputData
  val flightOutputData = org.example.flightOutputData

  val data: Dataset[flightInputData] = Seq(
     flightInputData("Air India", 1569315038L, 1569319183L)
    ,flightInputData("Air France", 1569290498L, 1569298178L)
    ,flightInputData("Emirates", 1567318178L, 1567351838L)
    ,flightInputData("Etihad", 1569315018L, 1569319163L)
    ,flightInputData("Quatar Airways", 1569320498L, 1569319163L)
  ).toDS()

  println("The input data before transformation: ")
  data.show(false)

  val result: Dataset[flightOutputData] = data.transform(returnDF)

  println("The Output data after transformation: ")
  result.show(false)

  def returnDF(inp: Dataset[flightInputData]): Dataset[flightOutputData] = {
    inp.map(x => transformLogic(x))
  }

  def transformLogic(fid: flightInputData): flightOutputData = {
    val res = (fid.arrivalTime - fid.departureTime) / 60 / 60
    return flightOutputData(fid.flightName, s"$res hrs")
  }
}
