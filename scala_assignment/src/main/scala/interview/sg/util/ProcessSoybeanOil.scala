package interview.sg.util

import interview.sg.SoybeanOil
import interview.sg.dto.AssignmentOneOutput

import scala.io.BufferedSource

class ProcessSoybeanOil {
  def processsoybeanOilValues(soybeanOilData: BufferedSource): List[AssignmentOneOutput] = {
    var soybeanOilList: List[SoybeanOil] = List()
    for(x<- soybeanOilData.getLines()) {
      var cols = x.split(",").map(_.trim)
      val soybeanOil = new SoybeanOil
      soybeanOil.setCropYear(cols(0))
      soybeanOil.setCrush(cols(1))
      soybeanOil.setExtractionRate(cols(2))
      soybeanOil.setProduction(cols(3))
      soybeanOil.setImports(cols(4))
      soybeanOil.setExports(cols(5))
      soybeanOil.setTotalConsumption(cols(6))
      soybeanOil.setFoodUse(cols(7))
      soybeanOil.setIndustryFeedUse(cols(8))
      soybeanOil.setEndingStocks(cols(9))
      soybeanOil.setCountry(cols(10))
      soybeanOil.setProductType("soybeanOil")

      soybeanOilList :+= soybeanOil

    }

    val soybeanOilDataList = soybeanOilList.drop(1)
    var newsoybeanOilList: List[SoybeanOil] = List()
    var worldsoybeanOilList:List[SoybeanOil] = List()
    var outputDataList: List[AssignmentOneOutput] = List()

    for(i<- 0 until soybeanOilDataList.length) {
      val b1 = soybeanOilDataList(i)
      b1.setCropYear(Commons.calculateSubstring(b1.getCropYear()))
      newsoybeanOilList :+= b1
      if(b1.getCountry().equals("World")) {
        worldsoybeanOilList :+= b1
      }
    }

    for(i<-0 until newsoybeanOilList.length) {
      val b2 = newsoybeanOilList(i)
      val wy = newsoybeanOilList(i).getCropYear()
      val wb = worldsoybeanOilList.filter(_.getCropYear()== wy)
      var worldProcution = wb(0).getProduction()
      val outputData: AssignmentOneOutput = new AssignmentOneOutput()

      b2.setProductionRate(
        Commons.calculateProductionRate(b2.getCountry(), b2.getProduction(),worldProcution))

      newsoybeanOilList :+= b2
      outputData.setCropYear(newsoybeanOilList(i).getCropYear())
      outputData.setCountry(newsoybeanOilList(i).getCountry())
      outputData.setProductType(newsoybeanOilList(i).getProductType())
      outputData.setProductionRate(newsoybeanOilList(i).getProductionRate())

      outputDataList :+= outputData
    }
    outputDataList
  }

}
