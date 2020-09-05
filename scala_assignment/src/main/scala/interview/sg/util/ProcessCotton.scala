package interview.sg.util

import interview.sg.Cotton
import interview.sg.dto.AssignmentOneOutput

import scala.io.BufferedSource

class ProcessCotton {
  def processCottonValues(cottonData: BufferedSource): List[AssignmentOneOutput] = {
    var cottonList: List[Cotton] = List()
    for(x<- cottonData.getLines()) {
      var cols = x.split(",").map(_.trim)
      val cotton = new Cotton
      cotton.setCropYear(cols(0))
      cotton.setAreaHarvest(cols(1))
      cotton.setCropYield(cols(2))
      cotton.setProduction(cols(3))
      cotton.setImports(cols(4))
      cotton.setExports(cols(5))
      cotton.setTotalConsumption(cols(6))
      cotton.setUnaccountedLoss(cols(7))
      cotton.setEndingStocks(cols(8))
      cotton.setCountry(cols(9))
      cotton.setProductType("cotton")

      cottonList :+= cotton

    }

    val cottonDataList = cottonList.drop(1)
    var newCottonList: List[Cotton] = List()
    var worldCottonList:List[Cotton] = List()
    var outputDataList: List[AssignmentOneOutput] = List()

    for(i<- 0 until cottonDataList.length) {
      val b1 = cottonDataList(i)
      b1.setCropYear(Commons.calculateSubstring(b1.getCropYear()))
      newCottonList :+= b1
      if(b1.getCountry().equals("World")) {
        worldCottonList :+= b1
      }
    }

    for(i<-0 until newCottonList.length) {
      val b2 = newCottonList(i)
      val wy = newCottonList(i).getCropYear()
      val wb = worldCottonList.filter(_.getCropYear()== wy)
      var worldProcution = wb(0).getProduction()
      val outputData: AssignmentOneOutput = new AssignmentOneOutput()

      b2.setProductionRate(
        Commons.calculateProductionRate(b2.getCountry(), b2.getProduction(),worldProcution))

      newCottonList :+= b2
      outputData.setCropYear(newCottonList(i).getCropYear())
      outputData.setCountry(newCottonList(i).getCountry())
      outputData.setProductType(newCottonList(i).getProductType())
      outputData.setProductionRate(newCottonList(i).getProductionRate())

      outputDataList :+= outputData
    }
    outputDataList
  }

}
