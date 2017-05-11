package com.bluestar.bluestar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 5/2/2017.
 */

public class ViewPagerItemAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    String[] rank;
    String[] country;
    String[] population;
    int[] flag;
    LayoutInflater inflater;

    public ViewPagerItemAdapter(Context context, String[] rank, String[] country,
                            String[] population, int[] flag) {
        this.context = context;
        this.rank = rank;
        this.country = country;
        this.population = population;
        this.flag = flag;
    }

    @Override
    public int getCount() {
        return rank.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        // Declare Variables
        TextView txtrank;
        TextView txtcountry;
        TextView txtpopulation;
        ImageView imgflag;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.view_pager_image_item, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        txtrank = (TextView) itemView.findViewById(R.id.rank);
        txtcountry = (TextView) itemView.findViewById(R.id.country);
        txtpopulation = (TextView) itemView.findViewById(R.id.population);

        // Capture position and set to the TextViews
        txtrank.setText(rank[position]);
        txtcountry.setText(country[position]);
        txtpopulation.setText(population[position]);

        imgflag = (ImageView) itemView.findViewById(R.id.flag);
        imgflag.setImageResource(flag[position]);

        imgflag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (position) {
                    case 0:
                        Intent i = new Intent(context, Gallary.class);
                        v.getContext().startActivity(i);
                        break;
                    case 1:
                        break;

                    case 2:
                        break;

                    default:
                        break;
                }
            }
        });

        /*itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //this will log the page number that was click
                try
                {
                 showAlert("Alert", "you select " + country[position] +"  Population is "+ population[position] , context);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });*/
        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    public static void showAlert(String title, String message, Context context)
    {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    return;
                }
            });
            AlertDialog ad = builder.create();
            ad.setTitle(title);
            ad.setMessage(message);
            ad.show();
            TextView msg = (TextView) ad.findViewById(android.R.id.message);
            msg.setTextSize(15);

            Resources resources = ad.getContext().getResources();

            int alertTitleId = resources.getIdentifier("alertTitle", "id", "android");
            TextView alertTitle = (TextView) ad.getWindow().getDecorView().findViewById(alertTitleId);
            alertTitle.setTextColor(context.getResources().getColor(R.color.StripColor));

            int titleDividerId = resources.getIdentifier("titleDivider", "id", "android");
            View titleDivider = ad.getWindow().getDecorView().findViewById(titleDividerId);
            titleDivider.setBackgroundColor(context.getResources().getColor(R.color.StripColor));


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
