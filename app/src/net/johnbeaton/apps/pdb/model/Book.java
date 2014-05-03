package net.johnbeaton.apps.pdb.model;

import java.util.ArrayList;
import java.util.List;

public class Book extends Entity {
	
	private List<Genre> genres = new ArrayList<Genre>();
	private boolean read;
	private boolean owned;

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres.clear();
		this.genres.addAll(genres);
	}

	public boolean isOwned() {
		return owned;
	}

	public void setOwned(boolean owned) {
		this.owned = owned;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}
	
}
