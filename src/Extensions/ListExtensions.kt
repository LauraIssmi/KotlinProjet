package Extensions
import Course

fun List<Course>.top(): Course {
    var c :Course =this.first()
    var classement =this.first().classement
  for(course in this)
  {
      if(course.classement< classement) {
          classement = course.classement
          c = course
      }
  }
    return c
}
fun List<Course>.trouver(id: Int):Course?{

    //expression lambda
    return this.find { cour : Course -> cour.id==id }
}
