// Generated code from Butter Knife. Do not modify!
package swati4star.createpdf.activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import swati4star.createpdf.R;

public class RearrangeImages_ViewBinding implements Unbinder {
  private RearrangeImages target;

  private View view7f0902d0;

  @UiThread
  public RearrangeImages_ViewBinding(RearrangeImages target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RearrangeImages_ViewBinding(final RearrangeImages target, View source) {
    this.target = target;

    View view;
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'mRecyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.sort, "method 'sortImg'");
    view7f0902d0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sortImg();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    RearrangeImages target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;

    view7f0902d0.setOnClickListener(null);
    view7f0902d0 = null;
  }
}
