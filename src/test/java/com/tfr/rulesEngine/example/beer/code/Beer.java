package com.tfr.rulesEngine.example.beer.code;

/**
 *
 * Created by Erik on 6/27/2017.
 */
public class Beer {

    private final String name;
    private final double abv;
    private final double ibu;
    private final double srm;
    private final double og;

    public Beer(String name, double abv, double ibu, double srm, double og) {
        this.name = name;
        this.abv = abv;
        this.ibu = ibu;
        this.srm = srm;
        this.og = og;
    }

    public String getName() {
        return name;
    }

    public double getAbv() {
        return abv;
    }

    public double getIbu() {
        return ibu;
    }

    public double getSrm() {
        return srm;
    }

    public double getOg() {
        return og;
    }

    public boolean isStyle(Style style) {
        return style.getAbvRange().inRange(abv)
                && style.getIbuRange().inRange(ibu)
                && style.getSrmRange().inRange(srm)
                && style.getOgRange().inRange(og);
    }
}
