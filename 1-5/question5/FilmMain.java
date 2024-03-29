import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

class Film {
  private String name, language, leadActor, category;
  private int duration, yearOfRelease;

  public Film() {
    name = language = leadActor = category = "";
    duration = yearOfRelease = 0;
  }

  public Film(String name, String language, String leadActor, String category, int duration, int yearOfRelease) {
    this.name = name;
    this.language = language;
    this.leadActor = leadActor;
    this.category = category;
    this.duration = duration;
    this.yearOfRelease = yearOfRelease;
  }

  public void setName(String name) {
    this.name = name.toLowerCase();
    System.out.println("setName: " + this.name);
  }

  public void setLanguage(String language) {
    this.language = language.toLowerCase();
    System.out.println("setLanguage: " + this.language);
  }

  public void setLeadActor(String leadActor) {
    this.leadActor = leadActor.toLowerCase();
    System.out.println("setLeadActor: " + this.leadActor);
  }

  public void setCategory(String category) {
    this.category = category;
    System.out.println("setCategory: " + this.category);
  }

  public void setDuration(int duration) {
    this.duration = duration;
    System.out.println("setDuration: " + this.duration);
  }

  public void setYearOfRelease(int yearOfRelease) {
    this.yearOfRelease = yearOfRelease;
    System.out.println("setYOR: " + this.yearOfRelease);
  }


  public String getName() {
    String returnName = this.name.substring(0,1).toUpperCase() + this.name.substring(1);
    return returnName;
  }

  public String getLanguage() {
    String returnLanguage = this.language.substring(0,1).toUpperCase() + this.language.substring(1);
    // System.out.println("getlang: " + returnLanguage);
    return returnLanguage;
  }

  public String getLeadActor() {
    String returnLeadActor = this.leadActor.substring(0,1).toUpperCase() + this.leadActor.substring(1);
    // System.out.println("returning lead: " + returnLeadActor);
    return returnLeadActor;
  }

  public String getCategory() {
    String returnCategory = this.category.substring(0,1).toUpperCase() + this.category.substring(1);
    return returnCategory;
  }

  public int getDuration() {
    return this.duration;
  }

  public int getYearOfRelease() {
    return this.yearOfRelease;
  }
}

class SourceOfFunctions {

  Film[][] create2dArray(Film films[]) {

    Film[][] array2d = new Film[6][films.length];
    int counter[] = new int[6];
    for(Film film: films) {
      if(film.getYearOfRelease() == 1971)
        array2d[0][counter[0]++] = film;
      else if(film.getYearOfRelease() > 1971 && film.getYearOfRelease() <=1980 )
        array2d[1][counter[1]++] = film;
      else if(film.getYearOfRelease() >= 1981 && film.getYearOfRelease() <=1990 )
        array2d[2][counter[2]++] = film;
      else if(film.getYearOfRelease() > 1991 && film.getYearOfRelease() <=2000 )
        array2d[3][counter[3]++] = film;
      else if(film.getYearOfRelease() > 2001 && film.getYearOfRelease() <=2010 )
        array2d[4][counter[4]++] = film;
      else
        array2d[5][counter[5]++] = film;
    }

    return array2d;
  }

  ArrayList<Film> getRajiniFilms(ArrayList<Film> films) {
    ArrayList<Film> rajiniFilms = new ArrayList<Film>();
    // System.out.println("inside getrajini: " + films);
    for(Film film: films) {
      if((film.getLeadActor().equals("Rajinikanth") || film.getLeadActor().equals("Rajini")) && (film.getLanguage().equals("Tamil"))){
      // System.out.println("adding rajini movie");
        rajiniFilms.add(film);
      }
    }

    return rajiniFilms;
  }

  ArrayList<Film> getArnoldFilms(ArrayList<Film> films) {
    ArrayList<Film> arnoldFilms = new ArrayList<Film>();
    // System.out.println("getarnold: " + films);

    for(Film film: films) {
      if((film.getLeadActor().equals("Arnold"))  && (film.getLanguage().equals("English"))) {
        arnoldFilms.add(film);
        // System.out.println("inside add arnoldfilms");
      }
    }

    return arnoldFilms;
  }

  ArrayList<Film> getComedyFilms(ArrayList<Film> films) {
    ArrayList<Film> comedyFilms = new ArrayList<Film>();

    for(Film film: films) {
      if(film.getCategory().equals("Comedy"))
        comedyFilms.add(film);
    }

    return comedyFilms;
  }

  ArrayList<Film> getComedyFilms(ArrayList<Film> films, String actor) {
    ArrayList<Film> comedyFilmsWithActor = new ArrayList<Film>();
    actor = actor.substring(0,1).toUpperCase() + actor.substring(1);

    for(Film film: films) {
      if(film.getCategory().equals("Comedy") && film.getLeadActor().equals(actor))
        comedyFilmsWithActor.add(film);
    }

    return comedyFilmsWithActor;
  }

  Film getShortestFilm(ArrayList<Film> films) {
    Film shortestFilm = films.get(0);
    for(Film film: films) {
      if(shortestFilm.getDuration() > film.getDuration())
        shortestFilm = film;
    }

    return shortestFilm;
  }
}

class FilmMain {
  public static void main(String args[]) throws IOException {

    Scanner scanner = new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n;

    System.out.print("Enter the number of movies: ");
    n = scanner.nextInt();

    ArrayList<Film> films = new ArrayList<Film>();
    int numOfFilms = 0;

    for(int i = 0; i<n; i++) {

      // cannot use the same film object that was declared outside the for loop for all films being entered because ArrayList
      // contains reference to objects, not the objects themselves. each time a new movie info is to be entered, a new reference
      // should be generated so that the ArrayList will point to different film objects on heap
      Film film = new Film();

      System.out.println("Film " + i + ":");
      System.out.println("-------");
      System.out.print("Enter film name: ");
      String fname = br.readLine();
      film.setName(fname);

      System.out.print("Enter film language: ");
      String lang = br.readLine();
      film.setLanguage(lang);

      System.out.print("Enter film lead actor: ");
      String lead = br.readLine();
      film.setLeadActor(lead);

      System.out.print("Enter film category: ");
      String category = br.readLine();
      film.setCategory(category);

      System.out.print("Enter duration of the film: ");
      int duration = Integer.parseInt(br.readLine());
      film.setDuration(duration);

      System.out.print("Enter the year of release: ");
      int yor = Integer.parseInt(br.readLine());
      film.setYearOfRelease(yor);

      films.add(film);
      // System.out.println("films: " + films.size()); -- outputs correct values for size of film
    }

    SourceOfFunctions sof = new SourceOfFunctions();
    // System.out.println("films: " + films.size());-- corect output
    ArrayList<Film> rajiniFilms = sof.getRajiniFilms(films);
    // System.out.println(rajiniFilms.size());
    ArrayList<Film> arnoldFilms = sof.getArnoldFilms(films);
    // System.out.println(arnoldFilms.size());
    ArrayList<Film> comedyFilms = sof.getComedyFilms(films);
    // System.out.println(comedyFilms.size());

    for(Film rajiniFilm: rajiniFilms) {
      System.out.println("Film name: " + rajiniFilm.getName());
      System.out.println("Film language: " + rajiniFilm.getLanguage());
      System.out.println("Film actor: " + rajiniFilm.getLeadActor());
      System.out.println("Film category: " + rajiniFilm.getCategory());
      System.out.println("Film duration: " + rajiniFilm.getDuration());
      System.out.println("Film yearOfRelease: " + rajiniFilm.getYearOfRelease());
    }

    for(Film arnoldFilm: arnoldFilms) {
      System.out.println("Film name: " + arnoldFilm.getName());
      System.out.println("Film language: " + arnoldFilm.getLanguage());
      System.out.println("Film actor: " + arnoldFilm.getLeadActor());
      System.out.println("Film category: " + arnoldFilm.getCategory());
      System.out.println("Film duration: " + arnoldFilm.getDuration());
      System.out.println("Film yearOfRelease: " + arnoldFilm.getYearOfRelease());
    }

    Film shortestFilm = sof.getShortestFilm(arnoldFilms);
    System.out.println("Shortest Arnold film: " + shortestFilm.getName());

    for(Film comedyFilm: comedyFilms) {
      System.out.println("Film name: " + comedyFilm.getName());
      System.out.println("Film language: " + comedyFilm.getLanguage());
      System.out.println("Film actor: " + comedyFilm.getLeadActor());
      System.out.println("Film category: " + comedyFilm.getCategory());
      System.out.println("Film duration: " + comedyFilm.getDuration());
      System.out.println("Film yearOfRelease: " + comedyFilm.getYearOfRelease());
    }

    System.out.println("Enter the actor to get his comedy movies: ");
    String actor = br.readLine();

    ArrayList<Film> actorComedyFilms = sof.getComedyFilms(films, actor);

    for(Film comedyFilm: actorComedyFilms) {
      System.out.println("Film name: " + comedyFilm.getName());
      System.out.println("Film language: " + comedyFilm.getLanguage());
      System.out.println("Film actor: " + comedyFilm.getLeadActor());
      System.out.println("Film category: " + comedyFilm.getCategory());
      System.out.println("Film duration: " + comedyFilm.getDuration());
      System.out.println("Film yearOfRelease: " + comedyFilm.getYearOfRelease());
    }
  }
}
