package interview.sg.util

import interview.sg.SoybeanMeal
import interview.sg.dto.AssignmentOneOutput

import scala.io.BufferedSource

class ProcessSoybeanMeal {
  def processSoybeanMealValues(soybeanMealData: BufferedSource): List[AssignmentOneOutput] = {
    var soybeanMealList: List[SoybeanMeal] = List()
    for(x<- soybeanMealData.getLines()) {
      var cols = x.split(",").map(_.trim)
      val soybeanMeal = new SoybeanMeal
      soybeanMeal.setCropYear(cols(0))
      soybeanMeal.setCrush(cols(1))
      soybeanMeal.setExtractionRate(cols(2))
      soybeanMeal.setProduction(cols(3))
      soybeanMeal.setImports(cols(4))
      soybeanMeal.setExports(cols(5))
      soybeanMeal.setTotalConsumption(cols(6))
      soybeanMeal.setFoodUse(cols(7))
      soybeanMeal.setFeedUse(cols(8))
      soybeanMeal.setEndingStocks(cols(9))
      soybeanMeal.setCountry(cols(10))
      soybeanMeal.setProductType("soybeanMeal")

      soybeanMealList :+= soybeanMeal

    }

    val soybeanMealDataList = soybeanMealList.drop(1)
    var newsoybeanMealList: List[SoybeanMeal] = List()
    var worldsoybeanMealList:List[SoybeanMeal] = List()
    var outputDataList: List[AssignmentOneOutput] = List()

    for(i<- 0 until soybeanMealDataList.length) {
      val b1 = soybeanMealDataList(i)
      b1.setCropYear(Commons.calculateSubstring(b1.getCropYear()))
      newsoybeanMealList :+= b1
      if(b1.getCountry().equals("World")) {
        worldsoybeanMealList :+= b1
      }
    }

    for(i<-0 until newsoybeanMealList.length) {
      val b2 = newsoybeanMealList(i)
      val wy = newsoybeanMealList(i).getCropYear()
      val wb = worldsoybeanMealList.filter(_.getCropYear()== wy)
      var worldProcution = wb(0).getProduction()
      val outputData: AssignmentOneOutput = new AssignmentOneOutput()

      b2.setProductionRate(
        Commons.calculateProductionRate(b2.getCountry(), b2.getProduction(),worldProcution))

      newsoybeanMealList :+= b2
      outputData.setCropYear(newsoybeanMealList(i).getCropYear())
      outputData.setCountry(newsoybeanMealList(i).getCountry())
      outputData.setProductType(newsoybeanMealList(i).getProductType())
      outputData.setProductionRate(newsoybeanMealList(i).getProductionRate())

      outputDataList :+= outputData

    }
    outputDataList
  }

}
