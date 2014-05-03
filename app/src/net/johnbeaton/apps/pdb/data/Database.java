package net.johnbeaton.apps.pdb.data;

import net.johnbeaton.apps.pdb.model.Book;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public abstract class Database {

	public static ListenableFuture<EntityCursor<Book>> getBooks() {
		EntityCursor<Book> c = new EntityCursor<Book>() {

			public int getCount() {
				return 1;
			}

			public Book getItem(int position) {
				return new Book();
			}
		};
		return Futures.immediateFuture(c);
	}
	
}
