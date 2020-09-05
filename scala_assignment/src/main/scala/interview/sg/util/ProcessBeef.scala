package interview.sg.util

import interview.sg.Beef
import interview.sg.dto.AssignmentOneOutput

import scala.io.BufferedSource

class ProcessBeef {

  def processBeefValues(beefData: BufferedSource): List[AssignmentOneOutput] = {
    var beefList: List[Beef] = List()
    for(x<- beefData.getLines()) {
      var cols = x.split(",").map(_.trim)
      val beef = new Beef
      beef.setCalendarYear(cols(0))
      beef.setSlaughter(cols(1))
      beef.setMeatYield(cols(2))
      beef.setProduction(cols(3))
      beef.setImports(cols(4))
      beef.setExports(cols(5))
      beef.setTotalConsumption(cols(6))
      beef.setEndingStocks(cols(7))
      beef.setCountry(cols(8))
      beef.setProductType("beef")

      beefList :+= beef

    }

    val beefDataList = beefList.drop(1)
    var newBeefList: List[Beef] = List()
    var worldBeefList:List[Beef] = List()

    var outputDataList: List[AssignmentOneOutput] = List()


    for(i<- 0 until beefDataList.length) {
      val b1 = beefDataList(i)
      b1.setCalendarYear(Commons.calculateSubstring(b1.getCalendarYear()))
      newBeefList :+= b1
      if(b1.getCountry().equals("World")) {
        worldBeefList :+= b1
      }
    }

    for(i<-0 until newBeefList.length) {
      val b2 = newBeefList(i)
      val wy = newBeefList(i).getCalendarYear()
      val wb = worldBeefList.filter(_.getCalendarYear()== wy)
      var worldProcution = wb(0).getProduction()
      val outputData: AssignmentOneOutput = new AssignmentOneOutput()

      b2.setProductionRate(
        Commons.calculateProductionRate(b2.getCountry(), b2.getProduction(),worldProcution))

      newBeefList :+= b2
      outputData.setCropYear(newBeefList(i).getCalendarYear())
      outputData.setCountry(newBeefList(i).getCountry())
      outputData.setProductType(newBeefList(i).getProductType())
      outputData.setProductionRate(newBeefList(i).getProductionRate())

      outputDataList :+= outputData

    }
    outputDataList
  }

}
