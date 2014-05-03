package net.johnbeaton.apps.pdb.view;


import net.johnbeaton.apps.pdb.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class EntityListItem extends RelativeLayout {

	public EntityListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.entity_list_item, this);
	}

	public EntityListItem(Context context) {
		this(context,null);
	}

	
}
