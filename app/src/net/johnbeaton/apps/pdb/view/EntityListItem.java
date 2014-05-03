package net.johnbeaton.apps.pdb.view;


import net.johnbeaton.apps.pdb.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EntityListItem extends RelativeLayout {

	private TextView title;

	public EntityListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.entity_list_item, this);
		title = (TextView)findViewById(R.id.item_title);
	}

	public EntityListItem(Context context) {
		this(context,null);
	}


	public void setTitle(CharSequence text) {
		title.setText(text);
	}
}
