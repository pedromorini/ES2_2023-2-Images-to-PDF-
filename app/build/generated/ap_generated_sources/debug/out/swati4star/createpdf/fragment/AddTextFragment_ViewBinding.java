// Generated code from Butter Knife. Do not modify!
package swati4star.createpdf.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.lottie.LottieAnimationView;
import com.dd.morphingbutton.MorphingButton;
import java.lang.IllegalStateException;
import java.lang.Override;
import swati4star.createpdf.R;

public class AddTextFragment_ViewBinding implements Unbinder {
  private AddTextFragment target;

  private View view7f0902b6;

  private View view7f0902b7;

  private View view7f0900c9;

  private View view7f09034f;

  @UiThread
  public AddTextFragment_ViewBinding(final AddTextFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.select_pdf_file, "field 'mSelectPDF' and method 'showPdfFileChooser'");
    target.mSelectPDF = Utils.castView(view, R.id.select_pdf_file, "field 'mSelectPDF'", MorphingButton.class);
    view7f0902b6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showPdfFileChooser();
      }
    });
    view = Utils.findRequiredView(source, R.id.select_text_file, "field 'mSelectText' and method 'showTextFileChooser'");
    target.mSelectText = Utils.castView(view, R.id.select_text_file, "field 'mSelectText'", MorphingButton.class);
    view7f0902b7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showTextFileChooser();
      }
    });
    view = Utils.findRequiredView(source, R.id.create_pdf_added_text, "field 'mCreateTextPDF' and method 'openPdfNameDialog'");
    target.mCreateTextPDF = Utils.castView(view, R.id.create_pdf_added_text, "field 'mCreateTextPDF'", MorphingButton.class);
    view7f0900c9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.openPdfNameDialog();
      }
    });
    target.layoutBottomSheet = Utils.findRequiredViewAsType(source, R.id.bottom_sheet, "field 'layoutBottomSheet'", LinearLayout.class);
    target.mRecyclerViewFiles = Utils.findRequiredViewAsType(source, R.id.recyclerViewFiles, "field 'mRecyclerViewFiles'", RecyclerView.class);
    target.mUpArrow = Utils.findRequiredViewAsType(source, R.id.upArrow, "field 'mUpArrow'", ImageView.class);
    target.mDownArrow = Utils.findRequiredViewAsType(source, R.id.downArrow, "field 'mDownArrow'", ImageView.class);
    target.mLayout = Utils.findRequiredViewAsType(source, R.id.layout, "field 'mLayout'", RelativeLayout.class);
    target.mLottieProgress = Utils.findRequiredViewAsType(source, R.id.lottie_progress, "field 'mLottieProgress'", LottieAnimationView.class);
    target.mTextEnhancementOptionsRecycleView = Utils.findRequiredViewAsType(source, R.id.enhancement_options_recycle_view_text, "field 'mTextEnhancementOptionsRecycleView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.viewFiles, "method 'onViewFilesClick'");
    view7f09034f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewFilesClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AddTextFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mSelectPDF = null;
    target.mSelectText = null;
    target.mCreateTextPDF = null;
    target.layoutBottomSheet = null;
    target.mRecyclerViewFiles = null;
    target.mUpArrow = null;
    target.mDownArrow = null;
    target.mLayout = null;
    target.mLottieProgress = null;
    target.mTextEnhancementOptionsRecycleView = null;

    view7f0902b6.setOnClickListener(null);
    view7f0902b6 = null;
    view7f0902b7.setOnClickListener(null);
    view7f0902b7 = null;
    view7f0900c9.setOnClickListener(null);
    view7f0900c9 = null;
    view7f09034f.setOnClickListener(null);
    view7f09034f = null;
  }
}
