// Generated by view binder compiler. Do not edit!
package com.vineesh.newsapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.vineesh.newsapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ShimmerLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialCardView cardView;

  @NonNull
  public final TextView desc;

  @NonNull
  public final TextView name;

  @NonNull
  public final TextView nameqq;

  @NonNull
  public final ShapeableImageView profileImage;

  private ShimmerLayoutBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialCardView cardView, @NonNull TextView desc, @NonNull TextView name,
      @NonNull TextView nameqq, @NonNull ShapeableImageView profileImage) {
    this.rootView = rootView;
    this.cardView = cardView;
    this.desc = desc;
    this.name = name;
    this.nameqq = nameqq;
    this.profileImage = profileImage;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ShimmerLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ShimmerLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.shimmer_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ShimmerLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardView;
      MaterialCardView cardView = ViewBindings.findChildViewById(rootView, id);
      if (cardView == null) {
        break missingId;
      }

      id = R.id.desc;
      TextView desc = ViewBindings.findChildViewById(rootView, id);
      if (desc == null) {
        break missingId;
      }

      id = R.id.name;
      TextView name = ViewBindings.findChildViewById(rootView, id);
      if (name == null) {
        break missingId;
      }

      id = R.id.nameqq;
      TextView nameqq = ViewBindings.findChildViewById(rootView, id);
      if (nameqq == null) {
        break missingId;
      }

      id = R.id.profileImage;
      ShapeableImageView profileImage = ViewBindings.findChildViewById(rootView, id);
      if (profileImage == null) {
        break missingId;
      }

      return new ShimmerLayoutBinding((ConstraintLayout) rootView, cardView, desc, name, nameqq,
          profileImage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
