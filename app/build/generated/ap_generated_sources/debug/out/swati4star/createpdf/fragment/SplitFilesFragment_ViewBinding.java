// Generated code from Butter Knife. Do not modify!
package swati4star.createpdf.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
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

public class SplitFilesFragment_ViewBinding implements Unbinder {
  private SplitFilesFragment target;

  private View view7f0902b1;

  private View view7f0902d8;

  private View view7f09034f;

  @UiThread
  public SplitFilesFragment_ViewBinding(final SplitFilesFragment target, View source) {
    this.target = target;

    View view;
    target.mLottieProgress = Utils.findRequiredViewAsType(source, R.id.lottie_progress, "field 'mLottieProgress'", LottieAnimationView.class);
    view = Utils.findRequiredView(source, R.id.selectFile, "field 'selectFileButton' and method 'showFileChooser'");
    target.selectFileButton = Utils.castView(view, R.id.selectFile, "field 'selectFileButton'", MorphingButton.class);
    view7f0902b1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showFileChooser();
      }
    });
    view = Utils.findRequiredView(source, R.id.splitFiles, "field 'splitFilesButton' and method 'parse'");
    target.splitFilesButton = Utils.castView(view, R.id.splitFiles, "field 'splitFilesButton'", MorphingButton.class);
    view7f0902d8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.parse();
      }
    });
    target.layoutBottomSheet = Utils.findRequiredViewAsType(source, R.id.bottom_sheet, "field 'layoutBottomSheet'", LinearLayout.class);
    target.mUpArrow = Utils.findRequiredViewAsType(source, R.id.upArrow, "field 'mUpArrow'", ImageView.class);
    target.mDownArrow = Utils.findRequiredViewAsType(source, R.id.downArrow, "field 'mDownArrow'", ImageView.class);
    target.mLayout = Utils.findRequiredViewAsType(source, R.id.layout, "field 'mLayout'", RelativeLayout.class);
    target.mRecyclerViewFiles = Utils.findRequiredViewAsType(source, R.id.recyclerViewFiles, "field 'mRecyclerViewFiles'", RecyclerView.class);
    target.mSplittedFiles = Utils.findRequiredViewAsType(source, R.id.splitted_files, "field 'mSplittedFiles'", RecyclerView.class);
    target.splitFilesSuccessText = Utils.findRequiredViewAsType(source, R.id.splitfiles_text, "field 'splitFilesSuccessText'", TextView.class);
    target.mSplitConfitEditText = Utils.findRequiredViewAsType(source, R.id.split_config, "field 'mSplitConfitEditText'", EditText.class);
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
    SplitFilesFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mLottieProgress = null;
    target.selectFileButton = null;
    target.splitFilesButton = null;
    target.layoutBottomSheet = null;
    target.mUpArrow = null;
    target.mDownArrow = null;
    target.mLayout = null;
    target.mRecyclerViewFiles = null;
    target.mSplittedFiles = null;
    target.splitFilesSuccessText = null;
    target.mSplitConfitEditText = null;

    view7f0902b1.setOnClickListener(null);
    view7f0902b1 = null;
    view7f0902d8.setOnClickListener(null);
    view7f0902d8 = null;
    view7f09034f.setOnClickListener(null);
    view7f09034f = null;
  }
}
