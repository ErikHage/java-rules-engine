# RulesEngine
**Java 8 rules engine**

## Definitions
**Rule**: An object composed of a name, priority, predicate, and output function.  
**Match**: When the predicate condition of a Rule is met (returns true)  
**RuleSet**: An iterable collection of Rule objects  
**RuleMap**: Maps a group name to a RuleSet  
**Evaluator**: Uses a Rule/RuleSet to Evaluate an input and produce an output  

## Evaluating Rules
When a rule is evaluated, its predicate is tested against the input. If the result is true, then it is considered a Match. After a Match the output function is applied to the input and (optionally) returns a result. For a multi match call, this result (if it exists) is then added to a collection of outputs, which is passed to the caller upon completion of the Evaluation. Otherwise, for a single evaluation, the rule's function is applied and the output ignored. Only the output of the final Matched rule is returned as the result.

## Interfaces:
- **\_Rule<I,O> extends Comparable<Rule<I,O>>**  
  - Methods
    - getName(): String
    - getGroupName(): String
    - getNextGroupName(): String
    - getPriority(): int
    - getMatchCondition(): Predicate<I>
    - testMatchCondition(): boolean
    - getOnMatchHandler(): Function<I,O>
    - applyOnMatchHandler(): void
- **\_RuleSet<I,O> extends Iterable<Rule<I,O>>**  
  - Methods
    - size(): int
    - stream(): Stream<\_Rule<I,O>>
    - iterator(): Iterator<\_Rule<I,O>>
    - add(\_Rule<I,O> rule): boolean
    - contains(\_Rule<I,O> rule): boolean
    - remove(\_Rule<I,O> rule): boolean  
- **\_RuleMap<I,O>**
  - Methods
    - getRuleGroup(String groupName): \_RuleSet<I,O>
    - getGroupNames(): Set<String>
    - getNumberOfGroups: int
- **\_Evaluator<I,O>**  
  - Methods
    - evaluate(I input, O output) : EvaluationResult<I,O>
    
## Using this library

### Rules
At minimum a rule requires only 2 inputs: A name and a predicate. The name used as a unique identifier within a set. The predicate is the expression evaluated to determine if this rule is to be applied for the given input I. Optional members are a function or consumer, a priority, a group name, and a next group name. If they are not defined, they will be set to the default values shown below.
##### OnMatchHandler
**Default**: `(i) -> Optional.empty()`  
When a rule is matched, the result is the invocation of the onMatchHandler defined. A function will return an output of type Optional\<O>, while a consumer will perform some or no operations on the input and return an Optional.empty().
##### Priority
**Default**: 0  
Priority defines the order rules are evaluated within their group. Highest priority goes first. If two rules are tied for priority, they are evaluated alphabetically by their name.
##### Group Name
**Default**: "DEFAULT"  
Defines a group of rules to be evaluated as one RuleSet. Once a match is found in that group, evaluation either goes to next group, or halts if there is no next group. The group name "DEFAULT" will always be the first group evaluated.  
##### Next Group Name
**Default**: "HALT"  
Defines a pointer to a group of rules to be evaluated next if the rule with this pointer is matched. A next group of "HALT" defines the end of an evaluation.
##### RuleBuilder  
Rules are instantiated by use of a RuleBuilder. The constructor takes a String and a predicate, and contains methods for additional rule options  
##### Examples
```java
/*
    A rule that evaluates if an integer is greater than or equal to 100, then adds 2 to it and adds it to the output.     
 */

_Rule<Integer,List<Integer>> myRule = new Rule.RuleBuilder<Integer,List<Integer>>("myRule", i -> i >= 100)
    .onMatchHandler((i,o) -> { 
        int result = i + 2;
        o.add(result); 
    })
    .build();
```
```java
/* 
    A rule that evaluates if an integer is greater than or equal to 100, then prints a String. 
    This rule then points to another group called "Some other group" to be evaluated next. 
*/

_Rule<Integer,List<Integer>> myRule = new Rule.RuleBuilder<Integer,List<Integer>>("myRule", i -> i >= 100)
     .onMatchHandler((i,o) -> System.out.println(i + " is greater than or equals to 100"))
     .nextGroupName("Some other group")
     .build();
```  
 
### RuleSets
A collection of Rule objects unique on rule name.
##### add(_Rule<I,O>): boolean
Add a rule to the set, returns true if it is added successfully. Throws DuplicateRuleException if the rule is already contained in the set.
##### contains(_Rule<I,O>): boolean
Return true if the given rule is contained within the set
##### remove(_Rule<I,O>): boolean
Return true if the rule is in the set and successfully removed.
##### size(): int
Returns the number of contained rules.
##### iterator(): Iterator<_Rule<I,O>>
Returns a new Iterator of the contained rules.
##### stream(): Stream-<_Rule<I,O>>
Returns a new Stream of the contained rules.
##### Examples
```java
_RuleSet<String, List<Integer>> ruleSet = new RuleSet<>();

ruleSet.add(someRule1); // true
ruleSet.add(someRule2); // true

ruleSet.size(); // 2

ruleSet.contains(someRule2); // true
ruleSet.contains(someRule3); // false

ruleSet.remove(someRule2);   // true
ruleSet.contains(someRule2); // false

```

### RuleMaps
A map of rule group name to a _RuleSet. Initialized using a populated _RuleSet. 
##### getRuleGroup(String groupName): _RuleSet<I,O>
Get the _RuleSet mapped to the given group name
##### getGroupNames(): Set<String>
Get the names of the groups contained in the map (the key set)
##### getNumberOfGroups(): int
Get the number of groups contained in the map (size)
##### Examples
```java
_RuleSet<String, List<Integer>> ruleSet = new RuleSet<>();
ruleSet.add(someRule1);
ruleSet.add(someRule2);
        
_RuleMap<String, List<Integer>> ruleMap = new RuleMap<>(ruleSet);

ruleMap.getRuleGroup("Some Group Name");
ruleMap.getGroupNames();
ruleMap.getNumberOfGroups();
```

### Evaluators
Instantiated using a _RuleSet, which is used to populate a _RuleMap.
##### evaluate(I input, O output): EvaluationResult<I,O>
Evaluates a given input (facts) against the contained rules and returns the relevant output (knowledge).
```java
_RuleSet<String, List<Integer>> ruleSet = new RuleSet<>();
ruleSet.add(someRule1);
ruleSet.add(someRule2);

_RuleEvaluator<String, List<Integer>> evaluator = new RuleEvaluator<>(ruleSet);
String input = "Some input string";
List<Integer> output = new ArrayList<>();

EvaluationResult<String, List<Integer>> result = evaluator.evaluate(input, output);

result.getKnowledge().value(); // List<Integer>
```