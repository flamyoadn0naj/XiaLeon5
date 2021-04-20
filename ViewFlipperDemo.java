
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ViewFlipper;
import com.example.memorydemo.R;

public class ViewFlipperDemo extends Activity {
    private static final String TAG = "ViewFlipperDemo";

    private ViewFlipper mViewFlipper;
    private float mOldTouchValue;

    @Override
    protected void onCreate(Bundle onSavedInstance) {
        super.onCreate(onSavedInstance);

        // 设置为全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_flipper_demo);
        mViewFlipper = findViewById(R.id.viewFlipper1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mOldTouchValue = event.getX();
                break;

                case MotionEvent.ACTION_UP:
                    float currentX = event.getX();

                    // 手指向右滑动: 手指向右滑动时横坐标 X 的值会变大，因此 currentX 的值更大
                    if (mOldTouchValue < currentX) {

                        // 进入屏幕的动效
                        mViewFlipper.setInAnimation(AnimationHelper.inFromLeftAnimation());

                        // 退出屏幕的动效
                        mViewFlipper.setOutAnimation(AnimationHelper.outToRightAnimation());
                        mViewFlipper.showNext();
                    }

                    // 横坐标的值变小，说明是左滑
                    if (mOldTouchValue > currentX) {

                        // 进入屏幕的动效
                        mViewFlipper.setInAnimation(AnimationHelper.inFromRightAnimation());

                        // 退出屏幕的动效
                        mViewFlipper.setOutAnimation(AnimationHelper.outToLeftAnimation());
                        mViewFlipper.showPrevious();
                    }
                    break;

                default:
                    break;
        }
        return super.onTouchEvent(event);
    }
}
