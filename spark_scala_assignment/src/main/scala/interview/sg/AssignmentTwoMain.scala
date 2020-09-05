package interview.sg

import interview.sg.util.AssignmentTwoUDFs
import org.apache.spark.sql.{DataFrame, SQLContext, SaveMode}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object AssignmentTwoMain {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("assignment2").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val fileFormat: String = ".csv"
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    val folderPath: String = "C:/selfproject/societe_generale/data/"

    val outputFilePath = "C:/selfproject/societe_generale/assignment2_output"

    //Read Input Data

    var barleyDF = readInputData(sqlContext, "barley", fileFormat, folderPath)

    var beefDF = readInputData(sqlContext, "beef", fileFormat, folderPath)

    var cornDF = readInputData(sqlContext, "corn", fileFormat, folderPath)

    var cottonDF = readInputData(sqlContext, "cotton", fileFormat, folderPath)

    var porkDF = readInputData(sqlContext, "pork", fileFormat, folderPath)

    var poultryDF = readInputData(sqlContext, "poultry", fileFormat, folderPath)

    var riceDF = readInputData(sqlContext, "rice", fileFormat, folderPath)

    var sorghumDF = readInputData(sqlContext, "sorghum", fileFormat, folderPath)

    var soybeanDF = readInputData(sqlContext, "soybean", fileFormat, folderPath)

    var soybeanMealDF = readInputData(sqlContext, "soybean_meal", fileFormat, folderPath)

    var soybeanOilDF = readInputData(sqlContext, "soybean_oil", fileFormat, folderPath)

    var wheatDF = readInputData(sqlContext, "wheat", fileFormat, folderPath)

    //Merge all the results

    val unionDF = unionAllDataFrames(sqlContext,barleyDF, beefDF, cornDF, cottonDF, porkDF, poultryDF, riceDF, sorghumDF, soybeanDF,
      soybeanMealDF, soybeanOilDF, wheatDF)

    unionDF.persist(StorageLevel.MEMORY_AND_DISK_SER)

    // Write output file to the destination
    unionDF.coalesce(1).write.format("csv").option("header","true").
      option("delimiter",",").mode(SaveMode.Overwrite).save(outputFilePath)






  }

  /**
    * Reads input from the source path
    * @param sqlContext SqlContext
    * @param fileName Name of the input file
    * @param fileFormat Format of the input file
    * @param folderPath folder path of the input file
    * @return
    */

  def readInputData(sqlContext: SQLContext, fileName: String, fileFormat: String, folderPath: String): DataFrame = {

    var inputData = sqlContext.read.format("csv").option("header", "true").
      load(folderPath + fileName + fileFormat)


    inputData

    inputData.registerTempTable("inputData")
    val dfColumns = inputData.columns
    val dateColumn = dfColumns(0)
    sqlContext.udf.register("datesubstring", AssignmentTwoUDFs.getYear _)
    var processedDF = sqlContext.sql(
      s"""
         | SELECT *, datesubstring($dateColumn) AS year, "$fileName" AS product FROM inputData
       """.stripMargin)

    processedDF = calculateProductionRate(sqlContext,processedDF)


processedDF
  }

  /**
    * Calculating production rate of the Input Data
    * @param sqlContext SQLContext
    * @param dataDF input data
    * @return calculated population rate for each year and country
    */




  def calculateProductionRate(sqlContext: SQLContext, dataDF: DataFrame): DataFrame = {
    dataDF.registerTempTable("dataDF")

    val countryValue = "World".toLowerCase
    val productionDF = sqlContext.sql(
      s"""
         | SELECT d.*, CASE WHEN
         | d.country <> "World" THEN (d.production/s.production)*100
         | ELSE d.production END AS production_rate FROM dataDF AS d
         | JOIN(SELECT * FROM dataDF WHERE country = "World") AS s ON
         | d.year = s.year
       """.stripMargin)

    productionDF
  }

  def unionAllDataFrames(sQLContext: SQLContext,barleyDF: DataFrame, beefDF: DataFrame, cornDF: DataFrame,
                         cottonDF: DataFrame, porkDF: DataFrame, poultryDF: DataFrame, riceDF: DataFrame,
                         sorghumDF: DataFrame, soybeanDF: DataFrame, soybeanMealDF: DataFrame, soybeanOilDF: DataFrame,
                         wheatDF: DataFrame): DataFrame ={

    barleyDF.registerTempTable("barleyDF")
    beefDF.registerTempTable("beefDF")
    cornDF.registerTempTable("cornDF")
    cottonDF.registerTempTable("cottonDF")
    porkDF.registerTempTable("porkDF")
    poultryDF.registerTempTable("poultryDF")
    riceDF.registerTempTable("riceDF")
    sorghumDF.registerTempTable("sorghumDF")
    soybeanDF.registerTempTable("soybeanDF")
    soybeanMealDF.registerTempTable("soybeanMealDF")
    soybeanOilDF.registerTempTable("soybeanOilDF")
    wheatDF.registerTempTable("wheatDF")

    var unionDF = sQLContext.sql(
      s"""
         | SELECT year, country, product, production_rate from barleyDF union all
         | SELECT year, country, product, production_rate from beefDF union all
         | SELECT year, country, product, production_rate from cornDF union all
         | SELECT year, country, product, production_rate from cottonDF union all
         | SELECT year, country, product, production_rate from porkDF union all
         | SELECT year, country, product, production_rate from poultryDF union all
         | SELECT year, country, product, production_rate from sorghumDF union all
         | SELECT year, country, product, production_rate from riceDF union all
         | SELECT year, country, product, production_rate from soybeanDF union all
         | SELECT year, country, product, production_rate from soybeanMealDF union all
         | SELECT year, country, product, production_rate from soybeanOilDF union all
         | SELECT year, country, product, production_rate from wheatDF
         |""".stripMargin)

    unionDF = unionDF.filter(unionDF.col("production_rate").isNotNull)

    unionDF

  }

}
