package interview.sg.util

import interview.sg.Corn
import interview.sg.dto.AssignmentOneOutput

import scala.io.BufferedSource

class ProcessCorn {
  def processCornValues(cornData: BufferedSource): List[AssignmentOneOutput] = {
    var cornList: List[Corn] = List()
    for(x<- cornData.getLines()) {
      var cols = x.split(",").map(_.trim)
      val corn = new Corn
      corn.setCropYear(cols(0))
      corn.setAreaHarvest(cols(1))
      corn.setCropYield(cols(2))
      corn.setProduction(cols(3))
      corn.setImports(cols(4))
      corn.setExports(cols(5))
      corn.setTotalConsumption(cols(6))
      corn.setFoodSeedIndustrialUse(cols(7))
      corn.setFeedUse(cols(8))
      corn.setEndingStocks(cols(9))
      corn.setCountry(cols(10))
      corn.setProductType("corn")

      cornList :+= corn

    }

    val cornDataList = cornList.drop(1)
    var newCornList: List[Corn] = List()
    var worldCornList:List[Corn] = List()
    var outputDataList: List[AssignmentOneOutput] = List()

    for(i<- 0 until cornDataList.length) {
      val b1 = cornDataList(i)
      b1.setCropYear(Commons.calculateSubstring(b1.getCropYear()))
      newCornList :+= b1
      if(b1.getCountry().equals("World")) {
        worldCornList :+= b1
      }
    }

    for(i<-0 until newCornList.length) {
      val b2 = newCornList(i)
      val wy = newCornList(i).getCropYear()
      val wb = worldCornList.filter(_.getCropYear()== wy)
      var worldProcution = wb(0).getProduction()
      val outputData: AssignmentOneOutput = new AssignmentOneOutput()

      b2.setProductionRate(
        Commons.calculateProductionRate(b2.getCountry(), b2.getProduction(),worldProcution))

      newCornList :+= b2
      outputData.setCropYear(newCornList(i).getCropYear())
      outputData.setCountry(newCornList(i).getCountry())
      outputData.setProductType(newCornList(i).getProductType())
      outputData.setProductionRate(newCornList(i).getProductionRate())

      outputDataList :+= outputData
    }
    outputDataList
  }

}
