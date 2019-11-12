package com.tiltedgear.herocreator.util.factory
import com.tiltedgear.herocreator.HeroApp
import com.tiltedgear.herocreator.model.Hero
import scalafx.scene.control.Alert
import scalikejdbc._

import scala.util.{Failure, Random, Success}

object HeroFactory {

  val rand = scala.util.Random

  val possibleFirstNames = Seq(
    "Joshua",
    "Joey",
    "Azzim",
    "Vang",
    "Arellano",
    "Campos",
    "Kelly",
    "Barnett",
    "Blevins",
    "Holmes",
    "Ware",
    "Velazquez",
    "Knapp",
    "Mills",
    "Abbott",
    "Singleton",
    "Goodwin",
    "Compton",
    "Fuentes",
    "Mendoza",
    "Lucas",
    "Shannon",
    "Barker",
    "Garza",
    "Maldonado",
    "Cherry",
    "Miller",
    "Randall",
    "Evans",
    "Nguyen",
    "Escobar",
    "Daniel",
    "Spears",
    "Becker",
    "King",
    "Griffith",
    "Wyatt",
    "Rose",
    "Logan",
    "Glenn",
    "Jennings",
    "Mcpherson",
    "Kent",
    "Joseph",
    "Keller",
    "Burton",
    "Nixon",
    "Guzman",
    "Graves",
    "Olson",
    "Walls",
    "Stephens",
    "Webb",
    "Jones",
    "Byrd",
    "Carrillo",
    "Cross",
    "Mcmahon",
    "Lynn",
    "Lawrence",
    "Gray",
    "Ramos",
    "Brooks",
    "Tran",
    "Oconnor",
    "Poole",
    "Townsend",
    "Arias",
    "Ponce",
    "Hodges",
    "Lewis",
    "Howell",
    "Good",
    "Wood",
    "Leach",
    "Shepard",
    "Kim",
    "Murray",
    "Sexton",
    "Adkins",
    "Orozco",
    "Parsons",
    "Farrell",
    "Rowland",
    "Pratt",
    "Strickland",
    "Hardy",
    "Foley",
    "Price",
    "Freeman",
    "Ali",
    "Powell",
    "Boyd",
    "Barrett",
    "Duarte",
    "Davies",
    "Rollins",
    "Allison",
    "Leblanc",
    "Huerta",
    "Willis",
    "Clarke",
    "Kirk",
    "Morrison",
    "Myers",
    "Rhodes",
    "Herring",
    "Fischer",
    "Randolph",
    "Castro",
    "Golden",
    "Carroll",
    "Bullock",
    "Mccullough",
    "Meadows",
    "Clay",
    "Mullen",
    "Floyd",
    "Wise",
    "Walter",
    "Stone",
    "George",
    "Beck",
    "Garrett",
    "Ramsey",
    "Levy",
    "Church",
    "Michael",
    "Navarro",
    "Bauer",
    "Bailey",
    "Hurley",
    "Sosa",
    "Hinton",
    "Haley",
    "Fitzpatrick",
    "Pruitt",
    "Sherman",
    "Flowers",
    "Zhang",
    "Hays",
    "Velez",
    "Munoz",
    "Harrell",
    "Padilla",
    "Quinn",
    "Sellers",
    "Greer",
    "Cox",
    "Robbins",
    "Li",
    "Santana",
    "Porter",
    "Moreno",
    "Anderson",
    "Manning",
    "Novak",
    "York",
    "Owens",
    "Haynes",
    "Fisher",
    "Lutz",
    "Salas",
    "Diaz",
    "Mcdowell",
    "Rice",
    "Dunn",
    "Chase",
    "Massey",
    "Rowe",
    "Patel",
    "Lester",
    "Whitney",
    "Crosby",
    "Hubbard",
    "Irwin",
    "Harrington",
    "Calhoun",
    "Wright",
    "Moyer",
    "Bird",
    "Bell",
    "Monroe",
    "Bates",
    "Lloyd",
    "Mcclain",
    "Gibbs",
    "Daniels",
    "Jackson",
    "Juarez",
    "Pollard",
    "Ford",
    "Henry",
    "Ortiz",
    "Marsh",
    "Waters",
    "Vincent",
    "Mcclure",
    "Rios",
    "Delacruz",
    "Gaines",
    "Garcia",
    "Farley"
  )
  val possibleLastNames = Seq(
    "Nelson",
    "Muhammad",
    "Ahimaz",
    "Claud",
    "Kelly",
    "Craig",
    "Kitty",
    "Rosella",
    "Patrice",
    "Cletus",
    "Tasha",
    "Delmar",
    "Sammie",
    "Deanna",
    "Brenton",
    "Ora",
    "Ursula",
    "Toby",
    "Monty",
    "Tracey",
    "Rosa",
    "Jerald",
    "Alphonso",
    "Nell",
    "Gabriela",
    "Eileen",
    "Allyson",
    "George",
    "Calvin",
    "Sharron",
    "Rich",
    "Bonnie",
    "Domingo",
    "Bettye",
    "Concetta",
    "Keven",
    "Margo",
    "Hank",
    "Anne",
    "Priscilla",
    "Marcie",
    "Sergio",
    "Angelo",
    "Dion",
    "Tia",
    "Weston",
    "Lester",
    "Joe",
    "Shad",
    "Wendell",
    "Randy",
    "Jaime",
    "Ron",
    "Edna",
    "Natalia",
    "Gale",
    "Moses",
    "Broderick",
    "Neville",
    "Jamel",
    "Isaias",
    "Rachelle",
    "Genaro",
    "Damion",
    "Isiah",
    "Stanford",
    "Lana",
    "Willy",
    "Lucio",
    "Jarred",
    "Tamera",
    "Lorrie",
    "Kris",
    "Jeannine",
    "Damian",
    "Deana",
    "Pat",
    "Zelma",
    "Elmer",
    "Jerold",
    "Maura",
    "Sheri",
    "William",
    "Kellie",
    "Jerrold",
    "Lorena",
    "Ramon",
    "Jeffrey",
    "Jay",
    "Corrine",
    "Twila",
    "Rodrigo",
    "Lidia",
    "Ahmed",
    "Gus",
    "Edwina",
    "Beatrice",
    "Kari",
    "Chuck",
    "Myra",
    "Krista",
    "Barbara",
    "Mckinley",
    "Ike",
    "Jeremy",
    "Amelia",
    "Nichole",
    "Jo",
    "Matt",
    "Dianna",
    "Darla",
    "Ariel",
    "Graham",
    "Dylan",
    "Norris",
    "Eugenio",
    "Darrick",
    "Phoebe",
    "Jacklyn",
    "Jerrell",
    "Cherie",
    "Maryellen",
    "Trisha",
    "Nickolas",
    "Chelsea",
    "Noe",
    "Sebastian",
    "Numbers",
    "Justine",
    "Brian",
    "Felipe",
    "Shawna",
    "Haywood",
    "Waldo",
    "Beau",
    "David",
    "Carson",
    "Krystal",
    "Garth",
    "Garret",
    "Jayne",
    "Travis",
    "Muriel",
    "Tania",
    "Nita",
    "Alberto",
    "Irving",
    "Gregg",
    "Myron",
    "Rebekah",
    "Rachel",
    "Benton",
    "Marlin",
    "Jamison",
    "Granville",
    "Hollie",
    "Tommie",
    "Darnell",
    "Monroe",
    "Effie",
    "Elisa",
    "Coleen",
    "Grover",
    "Gil",
    "Hugh",
    "Santiago",
    "Billie",
    "Teodoro",
    "Suzette",
    "Guy",
    "Enrique",
    "Jeanine",
    "Maxine",
    "Kristopher",
    "Winnie",
    "Clark",
    "Hollis",
    "Trudy",
    "Dwayne",
    "Saul",
    "King",
    "Beatriz",
    "Edith",
    "Sara",
    "Emerson",
    "Marietta",
    "Lowell",
    "Guadalupe",
    "Evangeline",
    "Elvis",
    "Rowena",
    "Patty",
    "Humberto",
    "Colby",
    "Jeannie",
    "Modesto",
    "Freddy",
    "Antoine",
    "Estelle",
    "Marilyn",
    "Emmett",
    "Faye",
    "Fidel"
  )

  val possibleOccupations: List[String] = DB readOnly { implicit session =>
    SQL("select * from occupations").map(rs => rs.string("name")).list.apply()
  }

  val possibleRoles: List[String] = DB readOnly { implicit session =>
    SQL("select * from roles").map(rs => rs.string("name")).list.apply()
  }

  val possibleFactions: List[String] = DB readOnly { implicit session =>
    SQL("select * from factions").map(rs => rs.string("name")).list.apply()
  }

  val possibleRaces: List[String] = DB readOnly { implicit session =>
    SQL("select * from races").map(rs => rs.string("name")).list.apply()
  }

  def Generator(): Hero ={
    val nameGenerator: String = Misc.GetRandomElement(possibleFirstNames, rand) + " " + Misc.GetRandomElement(possibleLastNames, rand)

    val role: String = Misc.GetRandomElement(possibleRoles, rand)
    val faction: String = Misc.GetRandomElement(possibleFactions, rand)
    val occupation: String = Misc.GetRandomElement(possibleOccupations, rand)
    val race: String = Misc.GetRandomElement(possibleRaces, rand)

    val hero = new Hero(
      nameGenerator,
      role,
      faction,
      LoreGenerator.generateLore(nameGenerator, faction, role, occupation),
      occupation,
      race,
      rand.nextInt(100)+50,
      rand.nextInt(100)+50,
      rand.nextInt(50)+25,
      rand.nextInt(5)+10
    )

    hero
  }

  def Generator(noOfHeroes: Int) = {
    for(i <- 1 to noOfHeroes){
      val nameGenerator: String = Misc.GetRandomElement(possibleFirstNames, rand) + " " + Misc.GetRandomElement(possibleLastNames, rand)

      val role: String = Misc.GetRandomElement(possibleRoles, rand)
      val faction: String = Misc.GetRandomElement(possibleFactions, rand)
      val occupation: String = Misc.GetRandomElement(possibleOccupations, rand)
      val race: String = Misc.GetRandomElement(possibleRaces, rand)

      val hero = new Hero(
        nameGenerator,
        role,
        faction,
        LoreGenerator.generateLore(nameGenerator, faction, role, occupation),
        occupation,
        race,
        rand.nextInt(100)+50,
        rand.nextInt(100)+50,
        rand.nextInt(50)+25,
        rand.nextInt(5)+10
      )

      hero.save() match {
        case Success(x) =>
          HeroApp.heroData += hero
        case Failure(e) =>
          val alert = new Alert(Alert.AlertType.Warning) {
            initOwner(HeroApp.stage)
            title = "Failed to Save"
            headerText = "Database Error"
            contentText = "Database problem filed to save changes"
          }.showAndWait()
      }

    }
  }

}
