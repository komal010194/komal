package interview.sg.util

import interview.sg.Barley
import interview.sg.dto.AssignmentOneOutput

import scala.io.BufferedSource

class ProcessBarley {
  def processBarleyValues(barleyData: BufferedSource): List[AssignmentOneOutput] = {
    var barleyList: List[Barley] = List()
    for(x<- barleyData.getLines()) {
      var cols = x.split(",").map(_.trim)
      val barley = new Barley
      barley.setCropYear(cols(0))
      barley.setAreaHarvest(cols(1))
      barley.setCropYield(cols(2))
      barley.setProduction(cols(3))
      barley.setImports(cols(4))
      barley.setExports(cols(5))
      barley.setTotalConsumption(cols(6))
      barley.setFoodSeedIndustrialUse(cols(7))
      barley.setFeedUse(cols(8))
      barley.setEndingStocks(cols(9))
      barley.setCountry(cols(10))
      barley.setProductType("barley")

      barleyList :+= barley

    }

    val barleyDataList = barleyList.drop(1)
    var newBarleyList: List[Barley] = List()
    var worldBarleyList:List[Barley] = List()
    var tempList: List[Barley] = List()

    var outputDataList: List[AssignmentOneOutput] = List()

    for(i<- 0 until barleyDataList.length) {
      val b1 = barleyDataList(i)
      b1.setCropYear(Commons.calculateSubstring(b1.getCropYear()))
      newBarleyList :+= b1
      if(b1.getCountry().equals("World")) {
        worldBarleyList :+= b1
      }
    }

    for(i<-0 until newBarleyList.length) {

      val b2 = newBarleyList(i)
      val wy = newBarleyList(i).getCropYear()
      val wb = worldBarleyList.filter(_.getCropYear()== wy)
      var worldProcution = wb(0).getProduction()
      val outputData: AssignmentOneOutput = new AssignmentOneOutput()

      b2.setProductionRate(
        Commons.calculateProductionRate(b2.getCountry(), b2.getProduction(),worldProcution))

      tempList :+= b2
      outputData.setCropYear(tempList(i).getCropYear())
      outputData.setCountry(tempList(i).getCountry())
      outputData.setProductType(tempList(i).getProductType())
      outputData.setProductionRate(tempList(i).getProductionRate())

        outputDataList :+= outputData


    }
    outputDataList
  }

}
