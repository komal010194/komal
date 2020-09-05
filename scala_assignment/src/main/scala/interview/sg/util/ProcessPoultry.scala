package interview.sg.util

import interview.sg.Poultry
import interview.sg.dto.AssignmentOneOutput

import scala.io.BufferedSource

class ProcessPoultry {
  def processPoultryValues(poultryData: BufferedSource): List[AssignmentOneOutput] = {
    var poultryList: List[Poultry] = List()
    for(x<- poultryData.getLines()) {
      var cols = x.split(",").map(_.trim)
      val poultry = new Poultry
      poultry.setCalendarYear(cols(0))
      poultry.setProduction(cols(1))
      poultry.setImports(cols(2))
      poultry.setExports(cols(3))
      poultry.setTotalConsumption(cols(4))
      poultry.setEndingStocks(cols(5))
      poultry.setCountry(cols(6))
      poultry.setProductType("poultry")

      poultryList :+= poultry

    }

    val poultryDataList = poultryList.drop(1)
    var newpoultryList: List[Poultry] = List()
    var worldpoultryList:List[Poultry] = List()
    var outputDataList: List[AssignmentOneOutput] = List()

    for(i<- 0 until poultryDataList.length) {
      val b1 = poultryDataList(i)
      b1.setCalendarYear(Commons.calculateSubstring(b1.getCalendarYear()))
      newpoultryList :+= b1
      if(b1.getCountry().equals("World")) {
        worldpoultryList :+= b1
      }
    }

    for(i<-0 until newpoultryList.length) {
      val b2 = newpoultryList(i)
      val wy = newpoultryList(i).getCalendarYear()
      val wb = worldpoultryList.filter(_.getCalendarYear()== wy)
      var worldProcution = wb(0).getProduction()
      val outputData: AssignmentOneOutput = new AssignmentOneOutput()

      b2.setProductionRate(
        Commons.calculateProductionRate(b2.getCountry(), b2.getProduction(),worldProcution))

      newpoultryList :+= b2
      outputData.setCropYear(newpoultryList(i).getCalendarYear())
      outputData.setCountry(newpoultryList(i).getCountry())
      outputData.setProductType(newpoultryList(i).getProductType())
      outputData.setProductionRate(newpoultryList(i).getProductionRate())

      outputDataList :+= outputData

    }
    outputDataList
  }

}
