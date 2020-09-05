package interview.sg.util

import interview.sg.Rice
import interview.sg.dto.AssignmentOneOutput

import scala.io.BufferedSource

class ProcessRice {
  def processRiceValues(riceData: BufferedSource): List[AssignmentOneOutput] = {
    var riceList: List[Rice] = List()
    for(x<- riceData.getLines()) {
      var cols = x.split(",").map(_.trim)
      val rice = new Rice
      rice.setCropYear(cols(0))
      rice.setAreaHarvest(cols(1))
      rice.setCropYield(cols(2))
      rice.setProduction(cols(3))
      rice.setImports(cols(4))
      rice.setExports(cols(5))
      rice.setTotalConsumption(cols(6))
      rice.setEndingStocks(cols(7))
      rice.setCountry(cols(8))
      rice.setProductType("rice")

      riceList :+= rice

    }

    val riceDataList = riceList.drop(1)
    var newriceList: List[Rice] = List()
    var worldriceList:List[Rice] = List()
    var outputDataList: List[AssignmentOneOutput] = List()

    for(i<- 0 until riceDataList.length) {
      val b1 = riceDataList(i)
      b1.setCropYear(Commons.calculateSubstring(b1.getCropYear()))
      newriceList :+= b1
      if(b1.getCountry().equals("World")) {
        worldriceList :+= b1
      }
    }

    for(i<-0 until newriceList.length) {
      val b2 = newriceList(i)
      val wy = newriceList(i).getCropYear()
      val wb = worldriceList.filter(_.getCropYear()== wy)
      var worldProcution = wb(0).getProduction()
      val outputData: AssignmentOneOutput = new AssignmentOneOutput()

      b2.setProductionRate(
        Commons.calculateProductionRate(b2.getCountry(), b2.getProduction(),worldProcution))

      newriceList :+= b2
      outputData.setCropYear(newriceList(i).getCropYear())
      outputData.setCountry(newriceList(i).getCountry())
      outputData.setProductType(newriceList(i).getProductType())
      outputData.setProductionRate(newriceList(i).getProductionRate())

      outputDataList :+= outputData

    }
    outputDataList
  }

}
