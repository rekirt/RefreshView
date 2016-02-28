/*
Copyright 2015 shizhefei（LuckyJayce）

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.shizhefei.test.controllers.other;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shizhefei.mvc.IDataAdapter;
import com.shizhefei.mvc.MVCHelper;
import com.shizhefei.mvc.ultra.MVCUltraHelper;
import com.shizhefei.test.models.datasource.BookDetailDataSource;
import com.shizhefei.test.models.enties.Book;
import com.shizhefei.test.R;

/***
 * 测试下拉组件的非列表界面
 * 
 * @author LuckyJayce
 *
 */
public class BookDetailActivity extends Activity {

	private MVCHelper<Book> mvcHelper;
	private TextView authorTextView;
	private TextView contentTextView;
	private TextView descriptionTextView;
	private TextView nameTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bookdetail);

		nameTextView = (TextView) findViewById(R.id.name_textView);
		authorTextView = (TextView) findViewById(R.id.author_textView);
		descriptionTextView = (TextView) findViewById(R.id.description_textView);
		contentTextView = (TextView) findViewById(R.id.content_textView);

		PtrClassicFrameLayout contentLayout = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_list_view_frame);
		mvcHelper = new MVCUltraHelper<Book>(contentLayout);
		// 设置数据源
		mvcHelper.setDataSource(new BookDetailDataSource());
		// 设置适配器
		mvcHelper.setAdapter(dataAdapter);

		// 加载数据
		mvcHelper.refresh();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 释放资源
		mvcHelper.destory();
	}

	private IDataAdapter<Book> dataAdapter = new IDataAdapter<Book>() {
		private Book data;

		@Override
		public void notifyDataChanged(Book data, boolean isRefresh) {
			this.data = data;
			authorTextView.setText(data.getAuthor());
			contentTextView.setText(data.getContent());
			descriptionTextView.setText(data.getDescription());
			nameTextView.setText(data.getName());
		}

		@Override
		public boolean isEmpty() {
			return data == null;
		}

		@Override
		public Book getData() {
			return data;
		}
	};

	public void onClickBack(View view) {
		finish();
	}

}
