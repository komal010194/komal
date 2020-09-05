package interview.sg

import java.io.{BufferedWriter, FileOutputStream, FileWriter, OutputStreamWriter}

import au.com.bytecode.opencsv.CSVWriter
import interview.sg.dto.AssignmentOneOutput
import interview.sg.util._

import scala.io.{BufferedSource, Source}

object AssignmentOneMain {
  def main(args: Array[String]): Unit = {
    val folderPath: String = "C:/selfproject/societe_generale/data/"
    val fileFormat: String = ".csv"
    val outputFilePath: String = "C:/selfproject/societe_generale/assignment1_output/output.csv"
    var finalOutputDataList: List[AssignmentOneOutput] = List()
    val asgn1 = new AssignmentOneOutput
    asgn1.setCropYear("year")
    asgn1.setCountry("country")
    asgn1.setProductType("product")
    asgn1.setProductionRate("production_rate")
    finalOutputDataList:+= asgn1

    val processBarley: ProcessBarley = new ProcessBarley
    val barleyData = Commons.readInputData(folderPath, "barley", fileFormat)
    finalOutputDataList = finalOutputDataList ::: processBarley.processBarleyValues(barleyData)


    val processBeef: ProcessBeef = new ProcessBeef
    val beefData = Commons.readInputData(folderPath, "beef", fileFormat)
    finalOutputDataList = finalOutputDataList ::: processBeef.processBeefValues(beefData)

    val processCorn: ProcessCorn = new ProcessCorn
    val cornData = Commons.readInputData(folderPath, "corn", fileFormat)
    finalOutputDataList = finalOutputDataList ::: processCorn.processCornValues(cornData)

    val processCotton: ProcessCotton = new ProcessCotton
    val cottonData = Commons.readInputData(folderPath, "cotton", fileFormat)
    finalOutputDataList = finalOutputDataList ::: processCotton.processCottonValues(cottonData)

    val processPork: ProcessPork = new ProcessPork
    val porkData = Commons.readInputData(folderPath, "pork", fileFormat)
    finalOutputDataList = finalOutputDataList ::: processPork.processPorkValues(porkData)

    val processPoultry: ProcessPoultry = new ProcessPoultry
    val poultryData = Commons.readInputData(folderPath, "poultry", fileFormat)
    finalOutputDataList = finalOutputDataList ::: processPoultry.processPoultryValues(poultryData)

    val processRice: ProcessRice = new ProcessRice
    val riceData = Commons.readInputData(folderPath, "rice", fileFormat)
    finalOutputDataList = finalOutputDataList ::: processRice.processRiceValues(riceData)

    val processSorghum: ProcessSorghum = new ProcessSorghum
    val sorghumData = Commons.readInputData(folderPath, "sorghum", fileFormat)
    finalOutputDataList = finalOutputDataList ::: processSorghum.processSorghumValues(sorghumData)

    val processSoybean: ProcessSoybean = new ProcessSoybean
    val soybeanData = Commons.readInputData(folderPath, "soybean", fileFormat)
    finalOutputDataList = finalOutputDataList ::: processSoybean.processSoybeanValues(soybeanData)

    val processSoybeanMeal: ProcessSoybeanMeal = new ProcessSoybeanMeal
    val soybeanMealData = Commons.readInputData(folderPath, "soybean_meal", fileFormat)
    finalOutputDataList = finalOutputDataList ::: processSoybeanMeal.processSoybeanMealValues(soybeanMealData)

    val processSoybeanOil: ProcessSoybeanOil = new ProcessSoybeanOil
    val soybeanOilData = Commons.readInputData(folderPath, "soybean_oil", fileFormat)
    finalOutputDataList = finalOutputDataList ::: processSoybeanOil.processsoybeanOilValues(soybeanOilData)

    val processWheat: ProcessWheat = new ProcessWheat
    val wheatData = Commons.readInputData(folderPath, "wheat", fileFormat)
    finalOutputDataList = finalOutputDataList ::: processWheat.processwheatValues(wheatData)


    writeOutput(outputFilePath,finalOutputDataList)


    println(finalOutputDataList.length)

  }

def writeOutput(outputPath: String, outputs: List[AssignmentOneOutput]) : Unit = {



  val bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath), "UTF-8"))
  val csvSeparator = ","
  for(i<-0 until outputs.length) {
    val oneLine = new StringBuffer()

    oneLine.append(outputs(i).getCropYear())
    oneLine.append(csvSeparator)
    oneLine.append(outputs(i).getCountry())
    oneLine.append(csvSeparator)
    oneLine.append(outputs(i).getProductType())
    oneLine.append(csvSeparator)
    oneLine.append(outputs(i).getProductionRate())

    bw.write(oneLine.toString())
    bw.newLine()
  }

  bw.flush()
  bw.close()




 /* val outputFile = new BufferedWriter(new FileWriter(outputPath))
  val csvWriter = new CSVWriter(outputFile)
  csvWriter.writeAll(outputs)


 val writer = new BufferedWriter(new FileWriter(outputPath))
  for(i<-0 until outputs.length) {
    outputs.foreach(writer.write(i))
  }

  writer.close()*/
}


}
