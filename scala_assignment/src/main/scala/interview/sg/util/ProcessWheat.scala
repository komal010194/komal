package interview.sg.util

import interview.sg.Wheat
import interview.sg.dto.AssignmentOneOutput

import scala.io.BufferedSource

class ProcessWheat {

  def processwheatValues(wheatData: BufferedSource): List[AssignmentOneOutput] = {
    var wheatList: List[Wheat] = List()
    for(x<- wheatData.getLines()) {
      var cols = x.split(",").map(_.trim)
      val wheat = new Wheat
      wheat.setCropYear(cols(0))
      wheat.setAreaHarvest(cols(1))
      wheat.setCropYield(cols(2))
      wheat.setProduction(cols(3))
      wheat.setImports(cols(4))
      wheat.setExports(cols(5))
      wheat.setTotalConsumption(cols(6))
      wheat.setFoodSeedIndustrialUse(cols(7))
      wheat.setFeedUse(cols(8))
      wheat.setEndingStocks(cols(9))
      wheat.setCountry(cols(10))
      wheat.setProductType("wheat")

      wheatList :+= wheat

    }

    val wheatDataList = wheatList.drop(1)
    var newwheatList: List[Wheat] = List()
    var worldwheatList:List[Wheat] = List()
    var outputDataList: List[AssignmentOneOutput] = List()

    for(i<- 0 until wheatDataList.length) {
      val b1 = wheatDataList(i)
      b1.setCropYear(Commons.calculateSubstring(b1.getCropYear()))
      newwheatList :+= b1
      if(b1.getCountry().equals("World")) {
        worldwheatList :+= b1
      }
    }

    for(i<-0 until newwheatList.length) {
      val b2 = newwheatList(i)
      val wy = newwheatList(i).getCropYear()
      val wb = worldwheatList.filter(_.getCropYear()== wy)
      var worldProcution = wb(0).getProduction()
      val outputData: AssignmentOneOutput = new AssignmentOneOutput()

      b2.setProductionRate(
        Commons.calculateProductionRate(b2.getCountry(), b2.getProduction(),worldProcution))

      newwheatList :+= b2
      outputData.setCropYear(newwheatList(i).getCropYear())
      outputData.setCountry(newwheatList(i).getCountry())
      outputData.setProductType(newwheatList(i).getProductType())
      outputData.setProductionRate(newwheatList(i).getProductionRate())

      outputDataList :+= outputData
    }
    outputDataList
  }

}
