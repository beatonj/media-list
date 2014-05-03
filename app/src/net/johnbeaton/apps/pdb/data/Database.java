package net.johnbeaton.apps.pdb.data;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import net.johnbeaton.apps.pdb.model.Book;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class Database {

	private DBHelper helper;

	private ListeningExecutorService service = MoreExecutors
			.listeningDecorator(Executors.newFixedThreadPool(1));


	private static class DBHelper extends SQLiteOpenHelper
	{
		private static final String DB_NAME = "MainDatabase";
		private static final int VERSION = 1;

		public DBHelper(Context context) {
			super(context, DB_NAME, null, VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			String [] statements = {

				"CREATE TABLE Genre (" +
						"id INTEGER PRIMARY KEY, " +
						"name TEXT" +
				");",

				"CREATE TABLE Film ("+
						"id		INTEGER PRIMARY KEY," +
						"title 		TEXT," +
						"own		INTEGER," + /* Whether i own the film */
						"watched		INTEGER," + /* Have i seen it */
						"freebase_id	TEXT," +
						"CHECK(own >= 0 and own <= 1)," + /* bit */
						"CHECK(watched >= 0 and watched <= 1)"+ /* bit */
				");",

				"CREATE TABLE Book (" +
						"id		INTEGER PRIMARY KEY," +
						"title 		TEXT," +
						"own		INTEGER," + /* Whether i own the book */
						"read		INTEGER," + /* Have i read it */
						"freebase_id	TEXT," +
						"CHECK(own >= 0 and own <= 1)," + /* bit */
						"CHECK(read >= 0 and read <= 1)" + /* bit */
					");",

				"CREATE TABLE Film_Genre (" +
						"film_id		INTEGER," +
						"genre_id	INTEGER," +
						"PRIMARY KEY(film_id, genre_id)," +
						"FOREIGN KEY(film_id) REFERENCES Film(id)," +
						"FOREIGN KEY(genre_id) REFERENCES Genre(id)" +
					");",

				"CREATE TABLE Book_Genre (" +
						"book_id		INTEGER," +
						"genre_id	INTEGER," +
						"PRIMARY KEY(book_id, genre_id)," +
						"FOREIGN KEY(book_id) REFERENCES Book(id)," +
						"FOREIGN KEY(genre_id) REFERENCES Genre(id)" +
					");",

				"CREATE TABLE FavouriteFilm(" +
						"film_id		INTEGER PRIMARY KEY," +
						"rating		INTEGER," +
						"FOREIGN KEY(film_id) REFERENCES Film(id)," +
						"CHECK(rating > 0 and rating <= 5)" + /* 1 to 5 */
					");",

				"CREATE TABLE FilmList(" +
					"id		INTEGER PRIMARY KEY," +
					"name		TEXT," +
					"UNIQUE(name)" +
				");",

				"CREATE TABLE FilmListItem(" +
					"film_id		INTEGER," +
					"list_id		INTEGER," +
					"PRIMARY KEY(film_id, list_id)," +
					"FOREIGN KEY(film_id) REFERENCES Film(id)," +
					"FOREIGN KEY(list_id) REFERENCES FilmList(id)" +
				");",

				"CREATE TABLE Country (" +
					"id		INTEGER PRIMARY KEY," +
					"name 		TEXT" +
				");",

				"CREATE TABLE CountryVisit (" +
					"id		INTEGER PRIMARY KEY," +
					"name 		TEXT," +
					"dateEntered	INTEGER NULL," +
					"dateExited	INTEGER NULL" +
				");",

				"INSERT INTO Book(id, title, own, read, freebase_id) VALUES (1, 'Slaughterhouse Five', 1, 1, NULL);"
			};


			for (String statement:statements)
				db.execSQL(statement);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			// Nothing yet.

		}

	}

	public Database(Context context) {
		helper = new DBHelper(context);
	}

	public ListenableFuture<EntityCursor<Book>> getBooks() {


		ListenableFuture<Cursor> dbCursor = query("Book",
				new String[]{"id", "title", "own"}, null, null,
				null, null, null);


		return Futures.transform(dbCursor, new Function<Cursor, EntityCursor<Book>>() {

			@Override
			public EntityCursor<Book> apply(final Cursor c) {
				return new EntityCursor<Book>() {

					public int getCount() {
						return c.getCount();
					}

					public Book getItem(int position) {
						c.moveToPosition(position);
						Book book = new Book();
						book.setId(c.getLong(0));
						book.setName(c.getString(1));
						return book;
					}

					@Override
					public void close() {
						c.close();
					}
				};
			}

		});


	}

	private ListenableFuture<Cursor> query(final String table, final String[] columns,
			final String selection, final String[] selectionArgs, final String groupBy,
			final String having, final String orderBy) {
		return service.submit(new Callable<Cursor>() {
			public Cursor call() {
				SQLiteDatabase db = helper.getReadableDatabase();
				return db.query(table, columns, selection, selectionArgs,
						groupBy, having, orderBy);
			}
		});

	}

}
