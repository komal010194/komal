package interview.sg.util

import interview.sg.Soybean
import interview.sg.dto.AssignmentOneOutput

import scala.io.BufferedSource

class ProcessSoybean {
  def processSoybeanValues(soybeanData: BufferedSource): List[AssignmentOneOutput] = {
    var soybeanList: List[Soybean] = List()
    for(x<- soybeanData.getLines()) {
      var cols = x.split(",").map(_.trim)
      val soybean = new Soybean
      soybean.setCropYear(cols(0))
      soybean.setAreaHarvest(cols(1))
      soybean.setCropYield(cols(2))
      soybean.setProduction(cols(3))
      soybean.setImports(cols(4))
      soybean.setExports(cols(5))
      soybean.setTotalConsumption(cols(6))
      soybean.setFoodUse(cols(7))
      soybean.setFeedSeedWaste(cols(8))
      soybean.setCrush(cols(9))
      soybean.setEndingStocks(cols(10))
      soybean.setCountry(cols(11))
      soybean.setProductType("soybean")

      soybeanList :+= soybean

    }

    val soybeanDataList = soybeanList.drop(1)
    var newsoybeanList: List[Soybean] = List()
    var worldsoybeanList:List[Soybean] = List()
    var outputDataList: List[AssignmentOneOutput] = List()

    for(i<- 0 until soybeanDataList.length) {
      val b1 = soybeanDataList(i)
      b1.setCropYear(Commons.calculateSubstring(b1.getCropYear()))
      newsoybeanList :+= b1
      if(b1.getCountry().equals("World")) {
        worldsoybeanList :+= b1
      }
    }

    for(i<-0 until newsoybeanList.length) {
      val b2 = newsoybeanList(i)
      val wy = newsoybeanList(i).getCropYear()
      val wb = worldsoybeanList.filter(_.getCropYear()== wy)
      var worldProcution = wb(0).getProduction()
      val outputData: AssignmentOneOutput = new AssignmentOneOutput()

      b2.setProductionRate(
        Commons.calculateProductionRate(b2.getCountry(), b2.getProduction(),worldProcution))

      newsoybeanList :+= b2
      outputData.setCropYear(newsoybeanList(i).getCropYear())
      outputData.setCountry(newsoybeanList(i).getCountry())
      outputData.setProductType(newsoybeanList(i).getProductType())
      outputData.setProductionRate(newsoybeanList(i).getProductionRate())

      outputDataList :+= outputData
    }
    outputDataList
  }

}
