package interview.sg.util

import scala.io.BufferedSource

object Commons {

  def readInputData(inputFolderPath: String, fileName: String, fileFormat: String): BufferedSource = {
    val inputData = io.Source.fromFile(inputFolderPath+fileName+fileFormat)

    inputData
  }

  def calculateSubstring(dateString: String): String = {
    var yearString: Option[String] = None
    if(dateString.contains("/")){
      yearString = Some(dateString.trim.substring(0,4))
    }
    else {
      yearString = Some(dateString.trim)
    }

    yearString.get
  }

  def calculateProductionRate(country: String, countryProduction: String, worldProduction: String): String ={
    var pr: Option[Double] = None
    if(country != "World") {
      pr = Some((countryProduction.replace("--","0").toDouble/ worldProduction.replace("--","0").toDouble) * 100)
    }
    else {
      pr = Some(countryProduction.replace("--","0")toDouble)
    }

    pr.get.toString

  }

}
