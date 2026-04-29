package gvsu.cis.edu.cis357finalexam

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

object KtorProvider {

    val client =
        HttpClient(CIO)

}