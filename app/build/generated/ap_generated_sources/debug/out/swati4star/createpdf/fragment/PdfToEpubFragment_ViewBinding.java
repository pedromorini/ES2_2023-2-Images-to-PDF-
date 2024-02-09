// Generated code from Butter Knife. Do not modify!
package swati4star.createpdf.fragment;

import android.view.View;
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

public class PdfToEpubFragment_ViewBinding implements Unbinder {
  private PdfToEpubFragment target;

  private View view7f090252;

  private View view7f09034f;

  private View view7f0902b6;

  @UiThread
  public PdfToEpubFragment_ViewBinding(final PdfToEpubFragment target, View source) {
    this.target = target;

    View view;
    target.mTextView = Utils.findRequiredViewAsType(source, R.id.tv_pdf_to_epub_bottom, "field 'mTextView'", TextView.class);
    view = Utils.findRequiredView(source, R.id.pdf_to_epub, "field 'pdfToEpub' and method 'openExtractText'");
    target.pdfToEpub = Utils.castView(view, R.id.pdf_to_epub, "field 'pdfToEpub'", MorphingButton.class);
    view7f090252 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.openExtractText();
      }
    });
    target.layoutBottomSheet = Utils.findRequiredViewAsType(source, R.id.bottom_sheet, "field 'layoutBottomSheet'", LinearLayout.class);
    target.mRecyclerViewFiles = Utils.findRequiredViewAsType(source, R.id.recyclerViewFiles, "field 'mRecyclerViewFiles'", RecyclerView.class);
    target.mUpArrow = Utils.findRequiredViewAsType(source, R.id.upArrow, "field 'mUpArrow'", ImageView.class);
    target.mDownArrow = Utils.findRequiredViewAsType(source, R.id.downArrow, "field 'mDownArrow'", ImageView.class);
    target.mLayout = Utils.findRequiredViewAsType(source, R.id.layout, "field 'mLayout'", RelativeLayout.class);
    target.mLottieProgress = Utils.findRequiredViewAsType(source, R.id.lottie_progress, "field 'mLottieProgress'", LottieAnimationView.class);
    view = Utils.findRequiredView(source, R.id.viewFiles, "method 'onViewFilesClick'");
    view7f09034f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewFilesClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.select_pdf_file, "method 'selectPdfFile'");
    view7f0902b6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.selectPdfFile();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    PdfToEpubFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTextView = null;
    target.pdfToEpub = null;
    target.layoutBottomSheet = null;
    target.mRecyclerViewFiles = null;
    target.mUpArrow = null;
    target.mDownArrow = null;
    target.mLayout = null;
    target.mLottieProgress = null;

    view7f090252.setOnClickListener(null);
    view7f090252 = null;
    view7f09034f.setOnClickListener(null);
    view7f09034f = null;
    view7f0902b6.setOnClickListener(null);
    view7f0902b6 = null;
  }
}
