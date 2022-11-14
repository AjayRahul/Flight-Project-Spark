package org

package object example {

  case class flightInputData(flightName: String, arrivalTime: BigInt, departureTime: BigInt)
  case class flightOutputData(flightName: String, duration: String)

}
