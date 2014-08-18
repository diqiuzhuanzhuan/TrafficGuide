package diqiuzhuanzhuan.trafficGuide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ant.liao.GifView;

/**
 * Created by long on 2014/7/28.
 */
public class HomePageFragment extends Fragment {

    private boolean mIsActive;
    private View mView;
    private GifView mGifView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mGifView = (GifView)mView.findViewById(R.id.fragment_home_page);
        mGifView.setGifImage(R.raw.train);
        mGifView.setLoopAnimation();
    }

    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_home_page, container, false);
        return mView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onResume() {
        mIsActive = true;
        super.onResume();
        mGifView.restartGifAnimation();
    }


    @Override
    public void onPause() {
        mIsActive = false;
        super.onPause();
        mGifView.pauseGifAnimation();
    }

    public boolean isActive() {
        return mIsActive;
    }

}
