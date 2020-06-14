
public class Movie {

	private int id;
	private String title;
	private String director;
	private int releaseYear;
	
	public Movie(int id, String title, String director, int releaseYear) {
		super();
		this.setId(id);
		this.setTitle(title);
		this.setDirector(director);
		this.setReleaseYear(releaseYear);
	}
	public Movie() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
}
