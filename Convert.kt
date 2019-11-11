import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>?) {
    val inputFileName = "raw-soap-endpoints.csv"
    val outputFileName = "soap-endpoints.json"
    try {
        val endpoints = ArrayList<Endpoint>()
        // get all lines, remove the header line
        val lines = Files.readAllLines(Paths.get(inputFileName))
        lines.removeAt(0)

        lines.forEach {
            val elements = it.split(',')
            val endpoint = Endpoint(elements[0], elements.subList(1, elements.size).filter {
                it != ""
            })
            endpoints.add(endpoint)
        }

        val outputFile = File(outputFileName)
        val mapper = ObjectMapper()
        outputFile.writeBytes(mapper.writeValueAsBytes(endpoints))

    } catch (e: Exception) {
        println("Reading CSV Error!")
        e.printStackTrace()
    }
}