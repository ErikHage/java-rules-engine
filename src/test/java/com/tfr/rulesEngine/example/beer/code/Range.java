package com.tfr.rulesEngine.example.beer.code;

/**
 *
 * Created by Erik on 6/27/2017.
 */
public class Range<N extends Comparable<N>> {

    private final N max;
    private final N min;
//    private final N by;

    public Range(N max, N min) {
        this.max = max;
        this.min = min;
    }

    public N getMax() {
        return max;
    }

    public N getMin() {
        return min;
    }

    public boolean inRange(N n) {
        int maxComp = max.compareTo(n);
        int minComp = min.compareTo(n);

//        System.out.println(String.format("n=%s, minComp=%s, maxComp=%s", n, minComp, maxComp));

        return maxComp <=0 && minComp >= 0;
    }
}
