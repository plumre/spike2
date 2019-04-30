package com.plumre.consume;

/*
 * Created by renhongjiang on 2019/4/29.
 */

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/4/29 14:16
 */
public class Singletom {

    private Singletom() {
    }

//    private static class Single {
//        private static final Singletom INSTANCE = new Singletom();
//    }
//
//    public static Singletom getInstance() {
//        return Single.INSTANCE;
//    }

    private static volatile Singletom INSTANCE;

    public static Singletom getInstance() {
        if (INSTANCE==null) {
            synchronized (Singletom.class) {
                if (INSTANCE==null) {
                    INSTANCE = new Singletom();
                }
            }
        }
        return INSTANCE;
    }
}