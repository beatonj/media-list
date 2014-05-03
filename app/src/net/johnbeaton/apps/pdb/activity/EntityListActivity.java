package net.johnbeaton.apps.pdb.activity;

import java.util.concurrent.Future;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import net.johnbeaton.apps.pdb.R;
import net.johnbeaton.apps.pdb.data.EntityCursor;
import net.johnbeaton.apps.pdb.data.EntityListAdapter;
import net.johnbeaton.apps.pdb.model.Entity;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

public abstract class EntityListActivity<T extends Entity> extends Activity {
	private ListView listView;
	private EntityListAdapter<T> adapter;
	@Override
	protected final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entity_list);
		listView = (ListView)findViewById(R.id.list_view);
		adapter = getAdapter();
		listView.setAdapter(adapter);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Futures.addCallback(beginCursorLoad(), new FutureCallback<EntityCursor<T>>() {
			public void onSuccess(EntityCursor<T> result) {
				adapter.setEntityCursor(result);
			}
			
			public void onFailure(Throwable t) {
				// Do nothing
				adapter.setEntityCursor(null);
			}
		});
	}
	
	protected abstract ListenableFuture<EntityCursor<T>> beginCursorLoad();

	protected abstract EntityListAdapter<T> getAdapter();
	
}
