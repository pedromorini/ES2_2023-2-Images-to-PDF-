// Generated code from Butter Knife. Do not modify!
package swati4star.createpdf.fragment;

import android.view.View;
import android.widget.ProgressBar;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.dd.morphingbutton.MorphingButton;
import java.lang.IllegalStateException;
import java.lang.Override;
import swati4star.createpdf.R;

public class ZipToPdfFragment_ViewBinding implements Unbinder {
  private ZipToPdfFragment target;

  private View view7f0902b1;

  private View view7f090374;

  @UiThread
  public ZipToPdfFragment_ViewBinding(final ZipToPdfFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.selectFile, "field 'selectFileButton' and method 'showFileChooser'");
    target.selectFileButton = Utils.castView(view, R.id.selectFile, "field 'selectFileButton'", MorphingButton.class);
    view7f0902b1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showFileChooser();
      }
    });
    view = Utils.findRequiredView(source, R.id.zip_to_pdf, "field 'convertButton' and method 'convertZipToPdf'");
    target.convertButton = Utils.castView(view, R.id.zip_to_pdf, "field 'convertButton'", MorphingButton.class);
    view7f090374 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.convertZipToPdf();
      }
    });
    target.extractionProgress = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'extractionProgress'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ZipToPdfFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.selectFileButton = null;
    target.convertButton = null;
    target.extractionProgress = null;

    view7f0902b1.setOnClickListener(null);
    view7f0902b1 = null;
    view7f090374.setOnClickListener(null);
    view7f090374 = null;
  }
}
