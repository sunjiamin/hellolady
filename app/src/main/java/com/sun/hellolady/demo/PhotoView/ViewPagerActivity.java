/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.sun.hellolady.demo.PhotoView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.sun.hellolady.R;
import com.support.util.imageloader.ImageLoader;
import com.support.util.photoview.PhotoView;

import java.util.ArrayList;


/**
 * Lock/Unlock button is added to the ActionBar.
 * Use it to temporarily disable ViewPager navigation in order to correctly interact with ImageView by gestures.
 * Lock/Unlock state of ViewPager is saved and restored on configuration changes.
 * 
 * Julia Zudikova
 */

public class ViewPagerActivity extends AppCompatActivity {

	private static final String ISLOCKED_ARG = "isLocked";
	
	private ViewPager mViewPager;
	private MenuItem menuLockItem;
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mViewPager = (HackyViewPager) findViewById(R.id.view_pager);
		setContentView(mViewPager);
		SamplePagerAdapter simpleAdapter = new SamplePagerAdapter();
		mViewPager.setAdapter(simpleAdapter);
		
		if (savedInstanceState != null) {
			boolean isLocked = savedInstanceState.getBoolean(ISLOCKED_ARG, false);
			((HackyViewPager) mViewPager).setLocked(isLocked);
		}
		mImageList.add("http://h.hiphotos.baidu.com/image/pic/item/ac345982b2b7d0a213987e5cc9ef76094a369a99.jpg");
		mImageList.add("http://d.hiphotos.baidu.com/image/pic/item/0ff41bd5ad6eddc4aa295b333bdbb6fd53663328.jpg");
		mImageList.add("http://h.hiphotos.baidu.com/image/pic/item/ac345982b2b7d0a213987e5cc9ef76094a369a99.jpg");
		mImageList.add("http://d.hiphotos.baidu.com/image/pic/item/3c6d55fbb2fb4316365895b222a4462308f7d3b7.jpg");
		mImageList.add("http://c.hiphotos.baidu.com/image/pic/item/d1a20cf431adcbef546504c7aeaf2edda2cc9f99.jpg");
		mImageList.add("http://f.hiphotos.baidu.com/image/pic/item/caef76094b36acaf0bb1ec3d7ed98d1000e99c99.jpg");
		mImageList.add("http://b.hiphotos.baidu.com/image/w%3D230/sign=4cb875147f899e51788e3d1772a6d990/377adab44aed2e73f29620c58301a18b86d6fad8.jpg");
		mImageList.add("http://b.hiphotos.baidu.com/image/pic/item/8718367adab44aed2f8e87e3b11c8701a08bfbf0.jpg");
		mImageList.add("http://c.hiphotos.baidu.com/image/pic/item/d8f9d72a6059252d964f2503369b033b5bb5b958.jpg");
		simpleAdapter.notifyDataSetChanged();

	}
	private static ArrayList<String> mImageList = new ArrayList<>();
	static class SamplePagerAdapter extends PagerAdapter {

		private static final int[] sDrawables = { R.drawable.ic_gender_male, R.drawable.ic_gender_male, R.drawable.ic_gender_male,
				R.drawable.ic_gender_male, R.drawable.ic_gender_male, R.drawable.ic_gender_male };

		@Override
		public int getCount() {
			return mImageList.size();
		}

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			PhotoView photoView = new PhotoView(container.getContext());
			//photoView.setImageResource(sDrawables[position]);
			ImageLoader.loadImage(container.getContext(),mImageList.get(position),photoView);
			// Now just add PhotoView to ViewPager and return it
			container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

			return photoView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.viewpager_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menuLockItem = menu.findItem(R.id.menu_lock);
        toggleLockBtnTitle();
        menuLockItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				toggleViewPagerScrolling();
				toggleLockBtnTitle();
				return true;
			}
		});

        return super.onPrepareOptionsMenu(menu);
    }
    
    private void toggleViewPagerScrolling() {
    	if (isViewPagerActive()) {
    		((HackyViewPager) mViewPager).toggleLock();
    	}
    }
    
    private void toggleLockBtnTitle() {
    	boolean isLocked = false;
    	if (isViewPagerActive()) {
    		isLocked = ((HackyViewPager) mViewPager).isLocked();
    	}
    	String title = (isLocked) ? getString(R.string.menu_unlock) : getString(R.string.menu_lock);
    	if (menuLockItem != null) {
    		menuLockItem.setTitle(title);
    	}
    }

    private boolean isViewPagerActive() {
    	return (mViewPager != null && mViewPager instanceof HackyViewPager);
    }
    
	@Override
	protected void onSaveInstanceState(@NonNull Bundle outState) {
		if (isViewPagerActive()) {
			outState.putBoolean(ISLOCKED_ARG, ((HackyViewPager) mViewPager).isLocked());
    	}
		super.onSaveInstanceState(outState);
	}
    
}
