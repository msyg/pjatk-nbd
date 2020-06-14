import com.basho.riak.client.api.commands.kv.UpdateValue.Update;

public class UpdateMovie extends Update<Movie>{

	private String newTitle;
	private int newReleaseYear;
	
	public UpdateMovie(String newTitle, int newReleaseYear) {
		this.newTitle = newTitle;
		this.newReleaseYear = newReleaseYear;
	}
	@Override
	public Movie apply(Movie original) {
		original.setTitle(newTitle);
		original.setReleaseYear(newReleaseYear);
		return original;
	}

}
