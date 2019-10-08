package com.maroua
import com.ryanharter.ktor.moshi.moshi
import io.ktor.features.ContentNegotiation
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.get
import io.ktor.routing.routing
import Course
import Extensions.top
import Extensions.trouver
import io.ktor.features.*
import io.ktor.http.HttpStatusCode


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(StatusPages) {
        statusFile(HttpStatusCode.NotFound, HttpStatusCode.Unauthorized, filePattern = "error#.html")
    }
    install(ContentNegotiation)
    {
        moshi()
    }
    routing {

        //la liste des cours
        val liste = listOf<Course>(
            Course(1, "java", "facile", true, 2)
            , Course(2, "PHP", "difficile", true, 5),
            Course(3, "C#", "moyen", true, 1)
        )
        //welcome message
        get("/") {
            call.respondText { "Welcome to OpenClassrooms brand new server !" }
        }
        //le cour top
        get("/course/top")
        {



            call.respond(liste.top())
        }
      //les cours par rapport id
        get("/course/{id}")
        {
            val parm=call.parameters["id"]
             if(parm !=null)
             {
                 val valeur= parm.toInt()

               if(liste.trouver(valeur)!=null){

                   call.respond(liste.trouver(valeur)!!)
               }else
               //si le cour n'existe pas voila deux solutions et ça marche pour les deux
                call.respond(HttpStatusCode.NotFound,"No Course were found!");
               //  call.respond(ClientMessages.localizableHTTP_STATUS_CODE("404","NO Course were found !").arguments)
             }

        }

    }
}

