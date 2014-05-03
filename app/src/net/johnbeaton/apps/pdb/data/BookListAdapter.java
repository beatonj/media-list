package net.johnbeaton.apps.pdb.data;

import net.johnbeaton.apps.pdb.model.Book;
import net.johnbeaton.apps.pdb.view.EntityListItem;

public class BookListAdapter extends EntityListAdapter<Book> {

	@Override
	protected void bindView(EntityListItem view, Book item) {
		view.setTitle(item.getName());
	}

}
