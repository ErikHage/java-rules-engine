package com.tfr.rulesEngine.example.beer.code;

/**
 *
 * Created by Erik on 6/27/2017.
 */
public interface Styles {

    Style IPA = new Style("IPA",
            new Range<>(6.0, 7.5),
            new Range<>(45.0, 85.0),
            new Range<>(25.0, 55.0),
            new Range<>(1.055, 1.085));

    Style AMBER = new Style("Amber",
            new Range<>(5.0, 6.5),
            new Range<>(15.0, 45.0),
            new Range<>(45.0, 75.0),
            new Range<>(1.045, 1.075));

    Style RED = new Style("Red",
            new Range<>(5.0, 6.7),
            new Range<>(15.0, 55.0),
            new Range<>(45.0, 85.0),
            new Range<>(1.045, 1.082));

}