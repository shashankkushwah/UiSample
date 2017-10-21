package ui.sample.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.util.Pair;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import java.util.List;

/**
 * Created by Shashank on 21/10/2017.
 */

public class AnimationUtils {

    public static ActivityOptions makeSceneTransitionAnimation(Activity activity, List<Pair<View, String>> pairs) {
        return ActivityOptions.makeSceneTransitionAnimation(activity, pairs.toArray(new Pair[pairs.size()]));
    }

    public static AnimatorSet prepareFadeInAnimation(int duration, View... views) {
        return prepareFadeInAnimation(duration, 1, views);
    }

    public static AnimatorSet prepareFadeInAnimation(int duration, float toAlpha, View... views) {
        AnimatorSet animatorSet = new AnimatorSet();
        for (View view : views) {
            if (view == null) continue;
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.ALPHA, 0, toAlpha);
            view.setVisibility(View.VISIBLE);
            animatorSet.play(animator);
        }
        animatorSet.setDuration(duration);
        return animatorSet;
    }

    public static AnimatorSet prepareFadeOutAnimation(int duration, View... views) {
        return prepareFadeOutAnimation(duration, 1, views);
    }

    public static AnimatorSet prepareFadeOutAnimation(int duration, float fromAlpha, View... views) {
        AnimatorSet animatorSet = new AnimatorSet();
        for (View view : views) {
            if (view == null) continue;
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.ALPHA, fromAlpha, 0);
            animatorSet.play(animator);
        }
        animatorSet.setDuration(duration);
        return animatorSet;
    }

    public static void animateSlideUp(View rootView, View... views) {
        prepareSlideUp(rootView, views).start();
    }

    public static AnimatorSet prepareSlideUp(View rootView, View... views) {
        AnimatorSet animatorSet = new AnimatorSet();

        float startY = rootView.getHeight();
        for (View view : views) {
            float endY = 0;
            view.setTranslationY(startY);

            ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, startY, endY);
            view.setVisibility(View.VISIBLE);
            animatorSet.play(animator);
            startY += view.getHeight();
        }
        animatorSet.setDuration(600);
        animatorSet.setInterpolator(new DecelerateInterpolator(1.5f));

        return animatorSet;
    }

    public static void animateSlideDown(View rootView, View... views) {
        prepareSlideDown(rootView, views).start();
    }

    public static AnimatorSet prepareSlideDown(View rootView, View... views) {
        AnimatorSet animatorSet = new AnimatorSet();

        float endY = rootView.getHeight();
        float startY = 0;
        for (View view : views) {
            view.setTranslationY(startY);

            ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, startY, endY);
            animatorSet.play(animator);
        }
        animatorSet.setDuration(600);
        animatorSet.setInterpolator(new AccelerateInterpolator(1.5f));
        return animatorSet;
    }
}
