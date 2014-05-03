package net.johnbeaton.apps.pdb.activity;

import net.johnbeaton.apps.pdb.data.BookListAdapter;
import net.johnbeaton.apps.pdb.data.EntityListAdapter;
import net.johnbeaton.apps.pdb.model.Book;


public abstract class BookListActivity extends EntityListActivity<Book> {

	public BookListAdapter adapter;

	public BookListActivity() {
		adapter = new BookListAdapter();
	}

	@Override
	protected final EntityListAdapter<Book> getAdapter() {
		return adapter;
	}
}
