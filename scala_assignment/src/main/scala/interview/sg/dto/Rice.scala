package interview.sg

class Rice {

    var cropYear: String = ""
    var areaHarvest: String = ""
    var cropYield:String = ""
    var production: String = ""
    var imports: String = ""
    var exports: String = ""
    var totalConsumption: String = ""
    var endingStocks: String = ""
    var country: String = ""
    var productType: String = ""
    var productionRate: String = ""


    def setCropYear(year: String): Unit ={
      cropYear = year
    }
    def getCropYear(): String ={
      cropYear
    }

    def setAreaHarvest(area: String): Unit ={
      areaHarvest = area
    }
    def getAreaHarvest():String = {
      areaHarvest
    }

    def setCropYield(cy: String): Unit ={
      cropYield = cy
    }
    def getCropYield(): String = {
      cropYield
    }

    def setProduction(prod: String): Unit ={
      production = prod
    }
    def getProduction(): String = {
      production
    }

    def setImports(imp: String): Unit ={
      imports = imp
    }
    def getImports(): String = {
      imports
    }

    def setExports(exp: String): Unit ={
      exports = exp
    }
    def getExports(): String = {
      exports
    }

    def setTotalConsumption(tc: String): Unit = {
      totalConsumption=tc
    }
    def getTotalConsumption(): String = {
      totalConsumption
    }

    def setEndingStocks(es: String): Unit = {
      endingStocks = es
    }
    def getEndingStocks(): String = {
      endingStocks
    }

    def setCountry(cr: String): Unit = {
      country = cr
    }
    def getCountry(): String = {
      country
    }

    def setProductType(pt: String): Unit = {
      productType = pt
    }
    def getProductType(): String = {
      productType
    }

    def setProductionRate(pr: String): Unit = {
      productionRate = pr
    }
    def getProductionRate(): String = {
      productionRate
    }


  }

