package interview.sg.dto

class AssignmentOneOutput {
  var cropYear: String = ""
  var country: String = ""
  var productType: String = ""
  var productionRate: String = ""

  def setCropYear(year: String): Unit ={
    cropYear = year
  }
  def getCropYear(): String ={
    cropYear
  }
  def setCountry(cr: String): Unit = {
    country = cr
  }
  def getCountry(): String = {
    country
  }
  def setProductType(pt: String): Unit = {
    productType = pt
  }
  def getProductType(): String = {
    productType
  }
  def setProductionRate(pr: String): Unit = {
    productionRate = pr
  }
  def getProductionRate(): String = {
    productionRate
  }
}
