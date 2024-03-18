package com.space.biblemon;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(0);

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(16);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    result.add(new com.space.bible.DataBinderMapperImpl());
    result.add(new com.space.board.DataBinderMapperImpl());
    result.add(new com.space.book.DataBinderMapperImpl());
    result.add(new com.space.chapel.DataBinderMapperImpl());
    result.add(new com.space.core_ui.DataBinderMapperImpl());
    result.add(new com.space.home.DataBinderMapperImpl());
    result.add(new com.space.main.DataBinderMapperImpl());
    result.add(new com.space.mileage.DataBinderMapperImpl());
    result.add(new com.space.notice.DataBinderMapperImpl());
    result.add(new com.space.other.DataBinderMapperImpl());
    result.add(new com.space.partners.DataBinderMapperImpl());
    result.add(new com.space.rothem.DataBinderMapperImpl());
    result.add(new com.space.signin.DataBinderMapperImpl());
    result.add(new com.space.signup.DataBinderMapperImpl());
    result.add(new com.space.timetable.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(67);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "amenity");
      sKeys.put(2, "barcode");
      sKeys.put(3, "bible");
      sKeys.put(4, "buttonHandler");
      sKeys.put(5, "buttonNonHandler");
      sKeys.put(6, "buttonParams");
      sKeys.put(7, "buttonParamsHandler");
      sKeys.put(8, "buttonTitle");
      sKeys.put(9, "calender");
      sKeys.put(10, "cancelButtonHandler");
      sKeys.put(11, "category");
      sKeys.put(12, "chapel");
      sKeys.put(13, "comment");
      sKeys.put(14, "content");
      sKeys.put(15, "data");
      sKeys.put(16, "description");
      sKeys.put(17, "detail");
      sKeys.put(18, "editorAction");
      sKeys.put(19, "editorActionHandler");
      sKeys.put(20, "exitHandler");
      sKeys.put(21, "handler");
      sKeys.put(22, "handlerKokkos");
      sKeys.put(23, "handlerShortcut");
      sKeys.put(24, "handlerVerify");
      sKeys.put(25, "hintText");
      sKeys.put(26, "imgDescription");
      sKeys.put(27, "imgTitle");
      sKeys.put(28, "imgUrl");
      sKeys.put(29, "info");
      sKeys.put(30, "inputStatus");
      sKeys.put(31, "inputText");
      sKeys.put(32, "inputType");
      sKeys.put(33, "inputVerify");
      sKeys.put(34, "itemHandler");
      sKeys.put(35, "kokkos");
      sKeys.put(36, "live");
      sKeys.put(37, "mileage");
      sKeys.put(38, "nextButtonHandler");
      sKeys.put(39, "notice");
      sKeys.put(40, "noticeHandler");
      sKeys.put(41, "noticeType");
      sKeys.put(42, "onClick");
      sKeys.put(43, "partners");
      sKeys.put(44, "policyText");
      sKeys.put(45, "policyTitle");
      sKeys.put(46, "qrcode");
      sKeys.put(47, "rental");
      sKeys.put(48, "reservedHandler");
      sKeys.put(49, "room");
      sKeys.put(50, "search");
      sKeys.put(51, "sliderUri");
      sKeys.put(52, "textInfo");
      sKeys.put(53, "textSignup");
      sKeys.put(54, "time");
      sKeys.put(55, "timeTitle");
      sKeys.put(56, "title");
      sKeys.put(57, "titleEdit");
      sKeys.put(58, "titleInfo");
      sKeys.put(59, "titleSignup");
      sKeys.put(60, "titleStatus");
      sKeys.put(61, "today");
      sKeys.put(62, "uri");
      sKeys.put(63, "user");
      sKeys.put(64, "viewModel");
      sKeys.put(65, "viewTitle");
      sKeys.put(66, "writeHandler");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(0);
  }
}
