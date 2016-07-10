package jp.tomorrowkey.android.canvas;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import jp.tomorrowkey.android.canvas.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int BITMAP_WIDTH = 400;

    private static final int BITMAP_HEIGHT = 400;

    private static final int RIBBON_MARGIN = 60;

    private static final int RIBBON_WIDTH = 60;

    private static final String RIBBON_TEXT = "おいしい！";

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Bitmap fruitPunchBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fruit_punch);

        Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);

        canvas.drawBitmap(
                fruitPunchBitmap,
                new Rect(0, 0, fruitPunchBitmap.getWidth(), fruitPunchBitmap.getHeight()),
                new Rect(0, 0, 400, 400),
                null);

        Paint backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.YELLOW);
        backgroundPaint.setStyle(Paint.Style.FILL);

        float marginHypotenuse = (float) (RIBBON_MARGIN * Math.sqrt(2));
        float ribbonHypotenuse = (float) (RIBBON_WIDTH * Math.sqrt(2));

        Path path = new Path();
        path.moveTo(BITMAP_WIDTH - marginHypotenuse - ribbonHypotenuse, 0);
        path.lineTo(BITMAP_WIDTH - marginHypotenuse, 0);
        path.lineTo(BITMAP_WIDTH, marginHypotenuse);
        path.lineTo(BITMAP_WIDTH, marginHypotenuse + ribbonHypotenuse);
        canvas.drawPath(path, backgroundPaint);

        Paint textPaint = new Paint();
        textPaint.setTextSize(30);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setColor(Color.BLACK);
        textPaint.setAntiAlias(true);

        int centerX = BITMAP_WIDTH / 2;
        int centerY = BITMAP_HEIGHT / 2;
        canvas.rotate(45, centerX, centerY);

        float centerOfRibbon = BITMAP_WIDTH / 2.0f;
        float topOfBitmap = centerY - (float) (BITMAP_WIDTH / Math.sqrt(2));
        float topOfRibbon = topOfBitmap + RIBBON_MARGIN;
        float middleOfRibbon = topOfRibbon + RIBBON_WIDTH / 2;

        float widthOfText = textPaint.measureText(RIBBON_TEXT);
        float x = centerOfRibbon - widthOfText / 2;

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float heightOfText = fontMetrics.top - fontMetrics.bottom;
        float middleOfText = heightOfText / 2;
        float leading = middleOfText + fontMetrics.bottom;
        float y = middleOfRibbon - leading;

        canvas.drawText(RIBBON_TEXT, x, y, textPaint);

        binding.imageView.setImageBitmap(bitmap);
    }
}
