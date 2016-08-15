package app.fxa.com.appframework.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;


public class BitmapLruCache {

    private LruCache<String, Bitmap> cache;

    public BitmapLruCache() {
        cache = new LruCache<String, Bitmap>(8 * 1024 * 1024) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    public Bitmap getBitmap(String url) {
        return cache.get(url);
    }

    public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url, bitmap);
    }
}