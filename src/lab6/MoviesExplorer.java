package lab6;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.lang.IllegalArgumentException;

public class MoviesExplorer {

	private static List<String> readFile(String filename) throws FileNotFoundException, IOException {
		List<String> content = new LinkedList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				content.add(line);
			}

			return content;
		}
	}

	public static void main(String[] args) throws Exception {
        // 1) Load the movies
        List<Movie> movies = new ArrayList<>();
        for (String line : readFile("/home/nikola/movies-mpaa.txt")) {
			addMovie(movies, line);
		}
        
        movies.forEach(movie -> {
        	System.out.println(movie); 
        	System.out.println(movie.getActors());
        	});
        
        // 2) Find the number of movies released in 2003
        
        Stream<Movie> releasedIn2003 = movies.stream()
        		.filter(movie -> movie.getYear() == 2003);
        System.out.println(releasedIn2003.count());
        
//        releasedIn2003.forEach(movie -> System.out.println(movie));

        // 3) Find the first movie that contains Lord of the Rings
        
        Stream<Movie> containsLordOfTheRing = movies.stream()
        		.filter(movie -> movie.getTitle().contains("Lord of the Rings"));
        Optional<Movie> optionalMovie = containsLordOfTheRing.findFirst();
        System.out.println(optionalMovie.orElseThrow(IllegalArgumentException::new));

        // 4) Display the films sorted by the release year

        movies.stream().sorted(new Comparator<Movie>() {

			@Override
			public int compare(Movie o1, Movie o2) {
				if (o1.getYear() > o2.getYear()) return 1;
				else if (o1.getYear() == o2.getYear()) return 0;
				else  return -1;
			}
		}).forEach(movie -> System.out.println(movie));
        
        System.out.println();
        
        // 5) Find the first and the last year in the statistics
        
        System.out.println(movies.stream().map(movie -> movie.getYear()).reduce((first, second) -> first));
        System.out.println(movies.stream().map(movie -> movie.getYear()).reduce((first, second) -> second));
        
        System.out.println();
        
        // 6) Print the movies grouped by year
        
        Map<Integer, List<Movie>> moviesByYear = movies.stream().
        		collect(Collectors.groupingBy(Movie::getYear));
        for (Entry <Integer, List<Movie>> moviee : moviesByYear.entrySet()) {
			System.out.println(moviee);
		}

        // 7) Extract all the actors
        
        Stream<List<Actor>> actorsStream = movies.stream().map(Movie::getActors);
        HashSet<Actor> actors = (HashSet<Actor>) actorsStream.flatMap(List::stream)
        		.collect(Collectors.toSet());
        System.out.println(actors.size());
        actors.forEach(actor -> System.out.println(actor));
        
        // 8) Find all the movies with Kevin Spacey
        
        Stream<Movie> movieStream = movies.stream()
        		.filter(movie -> movie.getActors()
        		.contains(new Actor("Kevin", "Spacey")));
        movieStream.forEach(movie -> System.out.println(movie));

    }

	private static void addMovie(List<Movie> movies, String movieInfo) {
		String elements[] = movieInfo.split("/");
		String title = parseMovieTitle(elements);
		String releaseYear = parseMovieReleaseYear(elements);

		Movie movie = new Movie(title, Integer.valueOf(releaseYear));

		for (int i = 1; i < elements.length; i++) {
			String[] name = elements[i].split(", ");
			String lastName = name[0].trim();
			String firstName = "";
			if (name.length > 1) {
				firstName = name[1].trim();
			}

			Actor actor = new Actor(firstName, lastName);
			movie.addActor(actor);
		}

		movies.add(movie);
	}

	private static String parseMovieTitle(String[] elements) {
		return elements[0].substring(0, elements[0].toString().lastIndexOf("(")).trim();
	}

	private static String parseMovieReleaseYear(String[] elements) {
		String releaseYear = elements[0].substring(elements[0].toString().lastIndexOf("(") + 1,
				elements[0].toString().lastIndexOf(")"));
		if (releaseYear.contains(",")) {
			releaseYear = releaseYear.substring(0, releaseYear.indexOf(","));
		}
		return releaseYear;
	}
}