# RulesEngine
**Java 8 rules engine**

##Definitions
**Rule**: An object composed of a name, priority, predicate, and output function.  
**Match**: When the predicate condition of a Rule is met (returns true)  
**RuleSet**: An iterable collection of Rule objects  
**Evaluator**: Uses a Rule/RuleSet to Evaluate an input and produce an output  
**Chaining**: When a Rule points to a following RuleSet to be Evaluated upon a Match   

##Evaluating Rules
When a rule is evaluated, its predicate is tested against the input. If the result is true, then it is considered a Match. After a Match the output function is applied to the input and returns an result. This result is then added to a collection of outputs, which is passed to the caller upon completion of the Evaluation. Depending on the type of Evaluation, this collection may contain one or many outputs. If there are no Matches, the collection returned is empty.

##Use cases:
1. Evaluate a **Single Rule** and receive a **Single Output**
* A single Rule is Evaluated against a given input. If it's a Match, the output function is applied to the input and the result returned as the output collection containing one item. If there is no Match the collection returned is empty.
  
2. Evaluate a **Single RuleSet** and receive a **Single Output**
  *
   
3. Evaluate a **Single RuleSet** and receive **Multiple Outputs**
  *
   
4. Evaluate **Multiple RuleSets** and receive a **Single Output**
  *
   
5. Evaluate **Multiple RuleSets** and receive **Multiple Outputs**
  *