package services
import javax.inject._
import java.io.InputStream

import scala.io.Source

class CSVReader @Inject()() {

  def readCSV(source: InputStream, separator: Char = ','): List[List[String]] = {
    val lines = readLines(source)
    val resultLines = lines.map(line => line.split(separator).toList)

    Console.println("---CSV SUCCESSFULLY PARSED---")
    Console.println("csv records loaded: " + resultLines.length)

    resultLines
  }

  private def readLines(source: InputStream): List[String] = {
    var lines: List[String] = List()
    val fileLines = Source.fromInputStream(source).getLines
    while (fileLines.hasNext) {
      //Console.println("reading line: " + i)
      val newLine: String = fileLines.next
      lines = newLine :: lines
    }
    lines
  }

}