package org

package object example {

  case class flightInputData(flightName: String, departureTime: BigInt, arrivalTime: BigInt)
  case class flightOutputData(flightName: String, duration: String)

}
