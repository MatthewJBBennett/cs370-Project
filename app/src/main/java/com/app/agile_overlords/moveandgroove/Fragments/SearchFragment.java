package com.app.agile_overlords.moveandgroove.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


import com.app.agile_overlords.moveandgroove.Adapters.FoodAdapter;
import com.app.agile_overlords.moveandgroove.Adapters.NutritionAdapter;
import com.app.agile_overlords.moveandgroove.AppDefines;
import com.app.agile_overlords.moveandgroove.DividerItemDecoration;
import com.app.agile_overlords.moveandgroove.Models.NutritionItemModel;
import com.app.agile_overlords.moveandgroove.Models.SearchResultsModel;
import com.app.agile_overlords.moveandgroove.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brittneyryn on 4/14/16.
 */
public class SearchFragment extends Fragment {
    private EditText searchText;
    private Button searchButton;
    private RecyclerView nutritionRecyclerView;
    private NutritionAdapter adapter;
    //private LinearLayoutManager layoutManager;
    private OnFragmentEvent onFragmentEvent;
    private ArrayList<NutritionItemModel> foodList = new ArrayList<>();
    private FoodAdapter mAdapter;

    public SearchFragment(){

    }

    public static SearchFragment newInstance(){
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchText = (EditText)view.findViewById(R.id.searchText);
        searchButton = (Button)view.findViewById(R.id.searchButton);
        nutritionRecyclerView = (RecyclerView)view.findViewById(R.id.nutritionRecyclerView);
        mAdapter = new FoodAdapter(foodList);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        nutritionRecyclerView.setItemAnimator(new DefaultItemAnimator());

        nutritionRecyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider));

        //layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        nutritionRecyclerView.setLayoutManager(layoutManager);
        // Assigning the Adapter to the RecyclerView. If this isn't done, the view will not populate
        nutritionRecyclerView.setAdapter(adapter);



        return view;
    }

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private GestureDetector gestureDetector;
        private SearchFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final
        SearchFragment.ClickListener clickListener){
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child,recyclerView.getChildPosition(child));
                    }
                }
            });

        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(),e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child,rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void setOnFragmentEvent(OnFragmentEvent onFragmentEvent) {
        this.onFragmentEvent = onFragmentEvent;
    }
    public interface OnFragmentEvent {
        void onEvent(NutritionItemModel item);
    }

}

