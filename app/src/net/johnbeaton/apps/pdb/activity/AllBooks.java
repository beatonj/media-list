package net.johnbeaton.apps.pdb.activity;

import net.johnbeaton.apps.pdb.data.Database;
import net.johnbeaton.apps.pdb.data.EntityCursor;
import net.johnbeaton.apps.pdb.model.Book;

import com.google.common.util.concurrent.ListenableFuture;

public class AllBooks extends BookListActivity {

	private Database database;

	public AllBooks() {
		database = new Database(this);
	}

	@Override
	protected ListenableFuture<EntityCursor<Book>> beginCursorLoad() {
		return database.getBooks();
	}

}
