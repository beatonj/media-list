package net.johnbeaton.apps.pdb.model;

import java.util.ArrayList;
import java.util.List;

public class Film extends Entity {
	
	private List<Genre> genres = new ArrayList<Genre>();
	private boolean watched;
	private boolean owned;

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres.clear();
		this.genres.addAll(genres);
	}

	public boolean isWatched() {
		return watched;
	}

	public void setWatched(boolean watched) {
		this.watched = watched;
	}

	public boolean isOwned() {
		return owned;
	}

	public void setOwned(boolean owned) {
		this.owned = owned;
	}
	
}
