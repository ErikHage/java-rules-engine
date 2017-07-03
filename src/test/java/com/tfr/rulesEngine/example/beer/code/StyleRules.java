package com.tfr.rulesEngine.example.beer.code;

import com.tfr.rulesEngine.example.beer.code.Beer;
import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule.simple.SimpleRule;

import static com.tfr.rulesEngine.example.beer.code.Styles.*;

/**
 *
 * Created by Erik on 6/27/2017.
 */
public interface StyleRules {

    Rule<Beer,String> IS_IPA_STYLE = new SimpleRule<>("IPA_RULE", (b) -> b.isStyle(IPA), (b) -> IPA.getName());
    Rule<Beer,String> IS_AMBER_STYLE = new SimpleRule<>("AMBER_RULE", (b) -> b.isStyle(AMBER), (b) -> AMBER.getName());
    Rule<Beer,String> IS_RED_STYLE = new SimpleRule<>("RED_RULE", (b) -> b.isStyle(RED), (b) -> RED.getName());

}
