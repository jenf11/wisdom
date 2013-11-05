package org.ow2.chameleon.wisdom.test.parents;

import org.ow2.chameleon.wisdom.api.cookies.FlashCookie;
import org.ow2.chameleon.wisdom.api.http.Context;
import org.ow2.chameleon.wisdom.api.http.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public class FakeFlashCookie implements FlashCookie {

    private final Map<String, String> data = new HashMap<String, String>();
    
    @Override
    public void init(Context context) { }

    @Override
    public void save(Context context, Result result) { }

    @Override
    public void put(String key, String value) {
        data.put(key, value);
    }

    @Override
    public void put(String key, Object value) {
        data.put(key, value.toString());
    }

    @Override
    public void now(String key, String value) {
        data.put(key, value);
    }

    @Override
    public void error(String value) {
        data.put("error", value);
    }

    @Override
    public void success(String value) {
        data.put("success", value);
    }

    @Override
    public void discard(String key) {
        data.remove(key);
    }

    @Override
    public void discard() {
        data.clear();
    }

    @Override
    public void keep(String key) { }

    @Override
    public void keep() { }

    @Override
    public String get(String key) {
        return data.get(key);
    }

    @Override
    public boolean remove(String key) {
        return (data.remove(key) != null);
    }

    @Override
    public void clearCurrentFlashCookieData() {
        data.clear();
    }

    @Override
    public boolean contains(String key) {
        return data.containsKey(key);
    }

    @Override
    public Map<String, String> getCurrentFlashCookieData() {
        return data;
    }

    @Override
    public Map<String, String> getOutgoingFlashCookieData() {
        return data;
    }
}