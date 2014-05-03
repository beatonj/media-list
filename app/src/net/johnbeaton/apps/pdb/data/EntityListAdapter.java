package net.johnbeaton.apps.pdb.data;

import net.johnbeaton.apps.pdb.data.EntityCursor;
import net.johnbeaton.apps.pdb.model.Entity;
import net.johnbeaton.apps.pdb.view.EntityListItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class EntityListAdapter<T extends Entity> extends BaseAdapter {

	private EntityCursor<T> cursor;
	
	public void setEntityCursor(EntityCursor<T> cursor) {
		this.cursor = cursor;
	}

	public int getCount() {
		if (cursor == null) {
			return 0;
		}
		return cursor.getCount();
	}

	public T getItem(int position) {
		return cursor.getItem(position);
	}

	public long getItemId(int position) {
		return cursor.getItem(position).getId();
	}

	public View getView(int position, View convertView, 
			ViewGroup parent) {
		
		EntityListItem item;
		
		if (convertView != null) {
			item = (EntityListItem)convertView;
		}
		else {
			item = new EntityListItem(parent.getContext());			
		}
		bindView(item, getItem(position));
		return item;
	}

	protected abstract void bindView(EntityListItem view, T item);
}
