package ldp.ldp;

/**
 * Created by quentin on 27/06/2016.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpandListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Group> groups;
    private Drawable image;
    private boolean click;
    private String nom;


    public ExpandListAdapter(Context context, ArrayList<Group> groups) {
        this.context = context;
        this.groups = groups;
        click=Boolean.FALSE;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Child> chList = groups.get(groupPosition).getItems();
        return chList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Child child = (Child) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_item, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.country_name);
        final ImageView iv = (ImageView) convertView.findViewById(R.id.flag);
        tv.setText(child.getName().toString());
        iv.setImageResource(child.getImage());
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                System.out.println("test de click "+child.getName().toString());
                image=iv.getDrawable();
                nom=child.getName().toString();
                //click=Boolean.TRUE;
                // Intent intent2 = new Intent(, InternetPage.class);
                //intent2.putExtra("titre",String.valueOf(textview.getText()));
                //intent2.putExtra("image", (CharSequence) imageView.getDrawable());
                //ImageList imagelist =new ImageList();
                //imagelist.changePage(child.getName().toString());
                //changePage();
                //iv.setMaxHeight(match_parent);
                //iv.setMaxHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                //iv.setMaxWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                if(click==Boolean.FALSE) {
                    System.out.println("test de click "+click);
                    iv.setLayoutParams(new RelativeLayout.LayoutParams(2500, 1400));
                    click=Boolean.TRUE;
                }else {
                    System.out.println("test de click "+click);
                    iv.setLayoutParams(new RelativeLayout.LayoutParams(250, 150));
                    click=Boolean.FALSE;
                }



            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Child> chList = groups.get(groupPosition).getItems();
        return chList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Group group = (Group) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.group_item, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.group_name);
        tv.setText(group.getName());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    public Drawable getimage() {
        return image;
    }
    public boolean onclick() {
        return true;
    }
    public String getnom() {
        return nom;
    }

}
