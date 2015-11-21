package com.unbelievable.uetsupport.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.unbelievable.uetsupport.R;
import com.unbelievable.uetsupport.objects.News;
import com.unbelievable.uetsupport.objects.Recruitment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by huylv on 20/11/2015.
 */
public class RecruitmentAdapter extends ArrayAdapter<Recruitment> {
    private SliderLayout sliderLayoutBanner;
    Context context;
    ArrayList<Recruitment> recruitmentArrayList;
    private DisplayImageOptions option;
    private CardView card_view;
    TextView tvRecruitmentJobType;
    TextView tvRecruitmentQuantity;
    TextView tvRecruitmentSalary;

    public RecruitmentAdapter(Context context, ArrayList<Recruitment> recruitments) {
        super(context,-1,recruitments);
        this.context=context;
        this.recruitmentArrayList= recruitments;
        option = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .cacheInMemory(true).cacheOnDisk(true).build();

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_recruiment_item,parent,false);
        ImageView ivNews = (ImageView)rowView.findViewById(R.id.imgCompanyLogo);
        TextView tvNews = (TextView) rowView.findViewById(R.id.tvRecruitmentTitle);
//        LinearLayout pageView = (LinearLayout)rowView.findViewById(R.id.pageView);
        card_view = (CardView) rowView.findViewById(R.id.card_view);
        tvRecruitmentJobType = (TextView) rowView.findViewById(R.id.tvRecruitmentJobType);
        tvRecruitmentQuantity = (TextView) rowView.findViewById(R.id.tvRecruitmentQuantity);
        tvRecruitmentSalary = (TextView) rowView.findViewById(R.id.tvRecruitmentSalary);

//        if (position == 0) {
//            card_view.setVisibility(View.GONE);
//            sliderLayoutBanner = new SliderLayout(getContext());
//            pageView.setVisibility(View.VISIBLE);
//            pageView.addView(sliderLayoutBanner);
//            ivNews.setVisibility(View.GONE);
//            tvNews.setVisibility(View.GONE);
//            setSliderLayoutBanner();
//        } else {
            tvNews.setText(recruitmentArrayList.get(position).title);
            ivNews.setImageResource(recruitmentArrayList.get(position).logo);
            tvRecruitmentJobType.setText("Công việc: " + recruitmentArrayList.get(position).jobType);
            tvRecruitmentQuantity.setText("Số lượng: " + recruitmentArrayList.get(position).quantity);
            tvRecruitmentSalary.setText("Lương khởi điểm" + recruitmentArrayList.get(position).salary);
//            try {
//                ImageLoader.getInstance().displayImage(recruitmentArrayList.get(position).logo, ivNews, option);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        return rowView;
    }

    @Override
    public int getCount() {
        return recruitmentArrayList.size();
    }

    private void setSliderLayoutBanner() {
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("1", R.mipmap.banner0);
        file_maps.put("2", R.mipmap.banner02);
        file_maps.put("3", R.mipmap.banner03);
        file_maps.put("4", R.mipmap.banner04);

        for (String name : file_maps.keySet()) {
            DefaultSliderView sliderView = new DefaultSliderView(getContext());
            sliderView
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderLayoutBanner.addSlider(sliderView);
        }
        sliderLayoutBanner.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayoutBanner.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayoutBanner.setCustomAnimation(new DescriptionAnimation());
        sliderLayoutBanner.setDuration(4000);
        sliderLayoutBanner.startAutoCycle();
    }
}
