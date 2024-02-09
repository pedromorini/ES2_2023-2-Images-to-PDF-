// Generated code from Butter Knife. Do not modify!
package swati4star.createpdf.fragment;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import swati4star.createpdf.R;

public class AboutUsFragment_ViewBinding implements Unbinder {
  private AboutUsFragment target;

  private View view7f09018c;

  private View view7f090192;

  private View view7f090191;

  private View view7f09018d;

  private View view7f09018b;

  private View view7f09018f;

  private View view7f090190;

  private View view7f09018e;

  @UiThread
  public AboutUsFragment_ViewBinding(final AboutUsFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.layout_email, "method 'sendmail'");
    view7f09018c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sendmail();
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_website, "method 'openWeb'");
    view7f090192 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.openWeb();
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_slack, "method 'joinSlack'");
    view7f090191 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.joinSlack();
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_github, "method 'githubRepo'");
    view7f09018d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.githubRepo();
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_contri, "method 'contributorsList'");
    view7f09018b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.contributorsList();
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_playstore, "method 'openPlaystore'");
    view7f09018f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.openPlaystore();
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_privacy, "method 'privacyPolicy'");
    view7f090190 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.privacyPolicy();
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_license, "method 'license'");
    view7f09018e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.license();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f09018c.setOnClickListener(null);
    view7f09018c = null;
    view7f090192.setOnClickListener(null);
    view7f090192 = null;
    view7f090191.setOnClickListener(null);
    view7f090191 = null;
    view7f09018d.setOnClickListener(null);
    view7f09018d = null;
    view7f09018b.setOnClickListener(null);
    view7f09018b = null;
    view7f09018f.setOnClickListener(null);
    view7f09018f = null;
    view7f090190.setOnClickListener(null);
    view7f090190 = null;
    view7f09018e.setOnClickListener(null);
    view7f09018e = null;
  }
}
