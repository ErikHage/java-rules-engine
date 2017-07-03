# RulesEngine
**Java 8 rules engine**

## Definitions
**Rule**: An object composed of a name, priority, predicate, and output function.  
**Match**: When the predicate condition of a Rule is met (returns true)  
**RuleSet**: An iterable collection of Rule objects  
**Evaluator**: Uses a Rule/RuleSet to Evaluate an input and produce an output  
**Linking**: When a Rule points to a following RuleSet to be Evaluated upon a Match
**Chaining**: When a Rule points to a following RuleSet to be Evaluated upon a Match using the output of the first ruleSet as the input to the nextGroup.

## Evaluating Rules
When a rule is evaluated, its predicate is tested against the input. If the result is true, then it is considered a Match. After a Match the output function is applied to the input and returns a result. This result is then added to a collection of outputs, which is passed to the caller upon completion of the Evaluation. Depending on the type of Evaluation, this collection may contain one or many outputs. If there are no Matches, the collection returned is empty.

## Use cases:
- Evaluate a **Single Rule** and receive a **Single Output**  
  - A single Rule is Evaluated against a given input. If it's a Match, the output function is applied to the input and the result returned as the output collection containing one item. If there is no Match the collection returned is empty.
  
- Evaluate a **Single RuleSet** and receive a **Single Output**  
  - A single RuleSet is Evaluated against a given input. The Rules are Evaluated against the input in priority order (highest to lowest). When a Match is found, the Evaluation halts and the result from the Matching Rule is returned as the output collection containing one item. If there is no Match the collection returned is empty.
   
- Evaluate a **Single RuleSet** and receive **Multiple Outputs**  
  - A single RuleSet is Evaluated against a given input. The Rules are Evaluated against the input in priority order (highest to lowest). When a Match is found, the result from the Matching Rule is added to the output collection. Evaluation continues until all Rules have been Evaluated, adding the results of Matching rules to the output collection. If there is no Match the collection returned is empty.
   
- Evaluate **Multiple RuleSets** and receive a **Single Output** (result of the final Match)  
  - Multiple RuleSets are Evaluated against a given input. A starting RuleSet is defined, the Matching Rule of which points to the nextGroup RuleSet to be Evaluated. This continues until a Rule is Matched with no pointer to a nextGroup RuleSet. The result of this Rule is returned as the output collection containing one item. If there is no Match the collection returned is empty. 

- Evaluate **Multiple RuleSets** and receive a **Single Output** (chaining results as new inputs)  
  - Multiple RuleSets are Evaluated against a given input. A starting RuleSet is defined, the Matching Rule of which points to the nextGroup RuleSet to be Evaluated. The result of the previously Matched Rule is used as the input to the proceeding RuleSet. This continues until a Rule is Matched with no pointer to a nextGroup RuleSet. The result of this Rule is returned as the output collection containing one item. If there is no Match the collection returned is empty. **Note that in this case the input and output types of the Rules must be the same type**
   
- Evaluate **Multiple RuleSets** and receive **Multiple Outputs**  
  - Multiple RuleSets are Evaluated against a given input. A starting RuleSet is defined, the Matching Rule of which points to the nextGroup RuleSet to be Evaluated. This continues until a Rule is Matched with no pointer to a nextGroup RuleSet. The result of each Matched Rule is added to the output collection. If there is no Match the collection returned is empty.

## Interfaces:
- **Rule<I,O> extends Comparable<Rule<I,O>>**  
  - Methods
    - getName(): String
    - getPriority(): int
    - getPredicate(): Predicate<I>
    - getFunction(): Function<I,O>
  - Implementations
    - SimpleRule<I,O>
    - LinkingRule<I,O>
    - ChainingRule<I> 
- **RuleSet<I,O> extends Iterable<Rule<I,O>>**  
  - Methods
    - getName(): String
    - getRules(): Set<Rule<I,O>>
    - add(Rule<I,O> rule): boolean
    - contains(Rule<I,O> rule): boolean
    - remove(Rule<I,O> rule): boolean
  - Implementations
    - SimpleRuleSet<I,O>
    - LinkingRuleSet<I,O>
    - ChainingRuleSet\<I>     
- **Evaluator<I,O>**  
  - Methods
    - evaluate(I input) : List<O>
  - Implementations
    - SingleRuleEvaluator<I,O>
    - FirstMatchEvaluator<I,O>
    - MultiMatchEvaluator<I,O>
    - LinkingRuleEvaluator<I,O>
    - MultiOutputLinkingRuleEvaluator<I,O>
    - ChainingRuleEvaluator\<I>
    
    
    