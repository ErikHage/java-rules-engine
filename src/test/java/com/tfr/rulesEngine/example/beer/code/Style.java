package com.tfr.rulesEngine.example.beer.code;

/**
 *
 * Created by Erik on 6/27/2017.
 */
public class Style {

     private final String name;
     private final Range<Double> abvRange;
     private final Range<Double> ibuRange;
     private final Range<Double> srmRange;
     private final Range<Double> ogRange;

    public Style(String name, Range<Double> abvRange, Range<Double> ibuRange, Range<Double> srmRange, Range<Double> ogRange) {
        this.name = name;
        this.abvRange = abvRange;
        this.ibuRange = ibuRange;
        this.srmRange = srmRange;
        this.ogRange = ogRange;
    }

    public String getName() {
        return name;
    }

    public Range<Double> getAbvRange() {
        return abvRange;
    }

    public Range<Double> getIbuRange() {
        return ibuRange;
    }

    public Range<Double> getSrmRange() {
        return srmRange;
    }

    public Range<Double> getOgRange() {
        return ogRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Style style = (Style) o;

        return name.equals(style.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
