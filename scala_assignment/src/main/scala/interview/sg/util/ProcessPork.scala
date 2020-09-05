package interview.sg.util

import interview.sg.Pork
import interview.sg.dto.AssignmentOneOutput

import scala.io.BufferedSource

class ProcessPork {
  def processPorkValues(porkData: BufferedSource): List[AssignmentOneOutput] = {
    var porkList: List[Pork] = List()
    for(x<- porkData.getLines()) {
      var cols = x.split(",").map(_.trim)
      val pork = new Pork
      pork.setCalendarYear(cols(0))
      pork.setSlaughter(cols(1))
      pork.setMeatYield(cols(2))
      pork.setProduction(cols(3))
      pork.setImports(cols(4))
      pork.setExports(cols(5))
      pork.setTotalConsumption(cols(6))
      pork.setEndingStocks(cols(7))
      pork.setCountry(cols(8))
      pork.setProductType("pork")

      porkList :+= pork

    }

    val porkDataList = porkList.drop(1)
    var newporkList: List[Pork] = List()
    var worldporkList:List[Pork] = List()
    var outputDataList: List[AssignmentOneOutput] = List()

    for(i<- 0 until porkDataList.length) {
      val b1 = porkDataList(i)
      b1.setCalendarYear(Commons.calculateSubstring(b1.getCalendarYear()))
      newporkList :+= b1
      if(b1.getCountry().equals("World")) {
        worldporkList :+= b1
      }
    }

    for(i<-0 until newporkList.length) {
      val b2 = newporkList(i)
      val wy = newporkList(i).getCalendarYear()
      val wb = worldporkList.filter(_.getCalendarYear()== wy)
      var worldProcution = wb(0).getProduction()
      val outputData: AssignmentOneOutput = new AssignmentOneOutput()

      b2.setProductionRate(
        Commons.calculateProductionRate(b2.getCountry(), b2.getProduction(),worldProcution))

      newporkList :+= b2
      outputData.setCropYear(newporkList(i).getCalendarYear())
      outputData.setCountry(newporkList(i).getCountry())
      outputData.setProductType(newporkList(i).getProductType())
      outputData.setProductionRate(newporkList(i).getProductionRate())

      outputDataList :+= outputData

    }
    outputDataList
  }

}
