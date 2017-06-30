package com.tfr.rulesEngine.example.rule;

import com.tfr.rulesEngine.example.model.Beer;
import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule.simple.SimpleRule;

/**
 *
 * Created by Erik on 6/28/2017.
 */
public interface CharacteristicRules {

    Rule<Beer, String> IS_HOPPY = new SimpleRule<>(
            "Beer Is Hoppy",
            0,
            (b) -> b.getIbu() > 50,
            (b) -> "Hoppy"
    );

    Rule<Beer, String> IS_DARK = new SimpleRule<>(
            "Beer Is Dark",
            0,
            (b) -> b.getSrm() > 80,
            (b) -> "Dark"
    );

    Rule<Beer, String> IS_LIGHT = new SimpleRule<>(
            "Beer Is Light",
            0,
            (b) -> b.getSrm() < 30,
            (b) -> "Light"
    );

    Rule<Beer, String> IS_MALTY = new SimpleRule<>(
            "Beer Is Malty",
            0,
            (b) -> b.getOg() > 1.055,
            (b) -> "Malty",
            ""
    );

    Rule<Beer, String> IS_SESSION = new SimpleRule<>(
            "Beer Is Session",
            0,
            (b) -> b.getAbv() <= 5.0,
            (b) -> "Session"
    );

    Rule<Beer, String> IS_STRONG = new SimpleRule<>(
            "Beer Is Strong",
            0,
            (b) -> b.getAbv() > 7.5,
            (b) -> "Strong"
    );

}
