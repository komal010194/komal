package interview.sg.util

import interview.sg.Sorghum
import interview.sg.dto.AssignmentOneOutput

import scala.io.BufferedSource

class ProcessSorghum {
  def processSorghumValues(sorghumData: BufferedSource): List[AssignmentOneOutput] = {
    var sorghumList: List[Sorghum] = List()
    for(x<- sorghumData.getLines()) {
      var cols = x.split(",").map(_.trim)
      val sorghum = new Sorghum
      sorghum.setCropYear(cols(0))
      sorghum.setAreaHarvest(cols(1))
      sorghum.setCropYield(cols(2))
      sorghum.setProduction(cols(3))
      sorghum.setImports(cols(4))
      sorghum.setExports(cols(5))
      sorghum.setTotalConsumption(cols(6))
      sorghum.setFoodSeedIndustrialUse(cols(7))
      sorghum.setFeedUse(cols(8))
      sorghum.setEndingStocks(cols(9))
      sorghum.setCountry(cols(10))
      sorghum.setProductType("sorghum")

      sorghumList :+= sorghum

    }

    val sorghumDataList = sorghumList.drop(1)
    var newsorghumList: List[Sorghum] = List()
    var worldsorghumList:List[Sorghum] = List()
    var outputDataList: List[AssignmentOneOutput] = List()

    for(i<- 0 until sorghumDataList.length) {
      val b1 = sorghumDataList(i)
      b1.setCropYear(Commons.calculateSubstring(b1.getCropYear()))
      newsorghumList :+= b1
      if(b1.getCountry().equals("World")) {
        worldsorghumList :+= b1
      }
    }

    for(i<-0 until newsorghumList.length) {
      val b2 = newsorghumList(i)
      val wy = newsorghumList(i).getCropYear()
      val wb = worldsorghumList.filter(_.getCropYear()== wy)
      var worldProcution = wb(0).getProduction()
      val outputData: AssignmentOneOutput = new AssignmentOneOutput()

      b2.setProductionRate(
        Commons.calculateProductionRate(b2.getCountry(), b2.getProduction(),worldProcution))

      newsorghumList :+= b2
      outputData.setCropYear(newsorghumList(i).getCropYear())
      outputData.setCountry(newsorghumList(i).getCountry())
      outputData.setProductType(newsorghumList(i).getProductType())
      outputData.setProductionRate(newsorghumList(i).getProductionRate())

      outputDataList :+= outputData
    }
    outputDataList
  }

}
