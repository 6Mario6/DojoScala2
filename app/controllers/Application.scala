package controllers

import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import models.{Person,DB}
import play.api.libs.json.Json

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
 val formPerson: Form[Person] = Form {
     
     mapping(
         "name" -> text,
         "age" -> number
         
         
     )(Person.apply)(Person.unapply)
     
 }
     
     
def addPerson = Action{ implicit request => 
    val persona = formPerson.bindFromRequest.get
    DB.save(persona)
    Redirect(routes.Application.index)

}
def getPersons = Action{
    
    val personas = DB.query[Person].fetch
    Ok(Json.toJson(personas))
}

}
