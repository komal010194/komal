package interview.sg.util

object AssignmentTwoUDFs {
  /** Retrieves the year from the string
    *
    * @param date Date in string
    * @return year from date
    */
  def getYear(date:String): String = {
    var tempArray = date.trim.split("/")
    var yearString: Option[String] = None
    if(tempArray.length == 2) {
       yearString = Some(tempArray.mkString("").trim.substring(0,4))
    }
    else {
      yearString = Some(tempArray.mkString("").trim)
    }

   yearString.get
  }

}
