package net.johnbeaton.apps.pdb.data;

import net.johnbeaton.apps.pdb.model.Entity;

public interface EntityCursor<T extends Entity> {

	int getCount();

	T getItem(int position);

	void close();
}
