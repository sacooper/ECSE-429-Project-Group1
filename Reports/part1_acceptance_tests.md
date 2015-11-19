# Part 1: Acceptance Testing
## Justification of Approach
The approach we have decided to use is to generate the test cases from the functional requirements. Because the functional requirements are clearly stated, and there are neither clear categories, nor requirements on the parameters provided, generating test cases showing the requirement showing the requirement working as expected, and test cases attempting to create an error will be sufficient to show the is capable of performing the requisite tasks. In addition, this allows for the set of scenarios to be dependent on the requirement, and what is needed it by it.

## Achieved Coverage
** TODO **

## Test Situations
1. The ability to add a message that creates a new instance (and consequently a new lifeline)
    - **1.a)** Attempt to add a message that creates a new instance for a defined class (verify object is created before message sent) (verify lifeline is started at `create`)
        - *1.a.a)* Do so as the first message
        - *1.a.b)* Do so after a message to a different class
        - *1.a.c)* Do so after a combined fragment
        - *1.a.d)* Do so within a combined fragment
        - *1.a.e)* Do so after a previous `create`/`destroy` of the same class
        - *1.a.f)* Do so after a previous `create`/`destroy` of a different class
        - *1.a.g)* Do so after a lifeline has been referenced of the same class, where the previously referenced lifeline was an attribute
    - **1.b)** Attempt to add a message that creates a new instance for a defined class with no `create` method (not possible)
    - **1.c)** Attempt to add a message that cares a new instance for an undefined class (not possible)
    - **1.d)** Attempt to add a message that creates a new instance for a defined class after a reply (should not be possible)
2. The ability to add a message with a new lifeline as target
    - **2.a)** Attempt to add a message that creates a new lifeline, referencing a class attribute (verify the lifeline is created)
        - *2.a.a)* Do so as the first message
        - *2.a.b)* Do so after a message to a different class
        - *2.a.c)* Do so after a combined fragment
        - *2.a.d)* Do so within a combined fragment
    - **2.b)** Attempt to add a message that creates a new lifeline, with no class attributes defined (should not allow you to do so)
    - **2.d)** Attempt to add a message referencing a private method of the class (should not be possible)
3. The ability to add a message with an existing lifeline as target
    - **3.a)** Attempt to add a message to a class attribute who's lifeline already exists (should add the message farther down the lifeline)
        - *3.a.a)* Do so within a combined fragment, for a lifeline created within the combined fragment
        - *3.a.b)* Do so within a combined fragment, for a lifeline created outside of the combined fragment
        - *3.a.c)* Do so outside of a combined fragment
        - *3.a.d)* Do so after a previous reference to an existing lifeline
    - **3.b)** Attempt to add a message to a newly created instance who's lifeline already exists (should add the message farther down the lifeline)
        - *3.b.a)* Do so within a combined fragment, for an instance created inside the fragment
        - *3.b.b)* Do so within a combined fragment, for an instance created outside the fragment
        - *3.b.c)* Do so outside of a combined fragment
        - *3.b.d)* Do so after a previous reference to an existing lifeline
        - *3.b.e)* Do so, attempting to draw the line to the start of the lifeline
    - **3.c)** Attempt to add a message to a lifeline that has been destroyed (should not allow you to do so)
        - *3.c.a*) Do so for a class attribute
        - *3.c.b*) Do so for an instance created by the lifeline
4. The ability to add a reply message
    - **4.a)** Attempt to add a reply message inside of a combined fragment for a method with non-void return type (should work, depending on the type of combined fragment)
        - *4.a.a)* Do so inside of an `opt` fragment (should work)
        - *4.a.b)* Do so inside of a `loop` fragment (should not work)
        - *4.a.c)* Do so inside of a `critical` fragment (should not work)
        - *4.a.d)* Do so inside of a `disruptable` fragment (should not work)
        - *4.a.e)* Do so inside of an `alt` fragment (should work)
    - **4.b)** Attempt to add a reply message for a method with a non-void return type outside of a combined fragment (should not allow you to do so)
    - **4.c)** Attempt to add a reply message for a method with a void return type outside a combined fragment (should not allow you to do so)
    - **4.d)** Attempt to add a reply message for a method with a void return type inside a combined fragment (should not allow you to do so)
5. The ability to delete one or several messages.
    - **5.a)** Attempt to delete a reply message inside an `opt` combined fragment (should allow you to do so)
    - **5.b)** Attempt to delete a `create` message (should allow you to do so) (should delete the entire lifeline created by `create`)
    - **5.c)** Attempt to delete a `destroy` message (should allow you to do so)
    - **5.d)** Attempt to delete a `self` message (should allow you to do so)
    - **5.e)** Attempt to delete a normal message

## Test Case Execution
### 1: The ability to add a message that creates a new instance (and consequently a new lifeline)
All tests use the model described in **Req1** under `acceptance_testing_models`, and performed on `Test.test()`

#### 1.a: Attempt to add a message that creates a new instance for a defined class
##### 1.a.a: As the first message from a lifeline
- **Performing Test**
    - Draw line from lifeline of `test()` and release
    - Select `<<metaclass>>:ClassOne`
    - Select `ClassOne.create()`
- **Expected Results**
    - A new lifeline should be created, with an arrow from the original lifeline containing the assignment `classone := create()`
- **Actual Results**
    - TouchCORE functioned as expected
- **Verdict**
    - **PASS**

##### 1.a.b: After a message to a different class
- **Performing Test**
    - Draw line from lifeline of `test()` and release
    - Select `myClassTwo:ClassTwo`
    - Select `ClassTwo.test2()`
    - Draw line from lifeline of `test()` under the previous message and release
    - Select `<<metaclass>>:ClassOne`
    - Select `ClassOne.create()`
- **Expected Results**
    - A new lifeline should be created, with an arrow from the original lifeline containing the assignment `classone := create()`
- **Actual Results**
    - TouchCORE functioned as expected
- **Verdict**
    - **PASS**

#### 1.a.c: After a combined fragment
- **Performing Test**
    - Create a combined fragment
    - Draw line from lifeline of `test()` under the combined fragment and release
    - Select `<<metaclass>>:ClassOne`
    - Select `ClassOne.create()`
- **Expected Results**
    - A new lifeline should be created, with an arrow from the original lifeline containing the assignment `classone := create()`
- **Actual Results**
    - TouchCORE functioned as expected
- **Verdict**
    - **PASS**


##### 1.a.d: Do so within a combined fragment
- **Performing Test**
    - Create a combined fragment
    - Draw line from lifeline of `test()` within the combined fragment and release
    - Select `<<metaclass>>:ClassOne`
    - Select `ClassOne.create()`
- **Expected Results**
    - A new lifeline should be created, with an arrow from the original lifeline containing the assignment `classone := create()`
- **Actual Results**
    - TouchCORE functioned as expected
- **Verdict**
    - **PASS**

##### 1.a.e: Do so after a previous `create`/`destroy` of the same class
- **Performing Test**
    - Draw line from lifeline of `test()` 
    - Select `<<metaclass>>:ClassOne`
    - Select `ClassOne.create()`
    - Draw line from lifeline of `test()` to the first lifeline of `classone` and choose `ClassOne.destroy()`
    - Draw line from lifeline of `test()` underneath the `destroy()` message
    - Select `<<metaclass>>:ClassOne`
    - Select `ClassOne.create()`
- **Expected Results**
    - A new lifeline should be created underneath the lifeline of the previous instance of `classone`
- **Actual Results**
    - TouchCORE functioned as expected
- **Verdict**
    - **PASS**

##### 1.a.f: Do so after a previous `create`/`destroy` of a different class
- **Performing Test**
    - Draw line from lifeline of `test()` 
    - Select `<<metaclass>>:ClassTwo`
    - Select `ClassTwo.create()`
    - Draw line from lifeline of `test()` to the first lifeline of `classtwo` and choose `Classtwo.destroy()`
    - Draw line from lifeline of `test()` underneath the `destroy()` message
    - Select `<<metaclass>>:ClassOne`
    - Select `ClassOne.create()`
- **Expected Results**
    - A new lifeline should be created underneath the lifeline of the previous instance of `classtwo`
- **Actual Results**
    - TouchCORE functioned as expected
- **Verdict**
    - **PASS**

##### 1.a.g: Do so after a lifeline has been referenced of the same class, where the previously referenced lifeline was an attribute
- **Performing Test**
    - Draw line from lifeline of `test()` 
    - Select `myClassOne`
    - Select `ClassOne.test1()`
    - Draw line from lifeline of `test()` underneath the `test1()` message
    - Select `<<metaclass>>:ClassOne`
    - Select `ClassOne.create()`
- **Expected Results**
    - A new lifeline should be created underneath the lifeline of the previous instance of `classtwo`
- **Actual Results**
    - TouchCORE functioned as expected
- **Verdict**
    - **PASS**


#### 1.b: Attempt to add a message that creates a new instance for a defined class with no `create` method (not possible)
- **Performing Test**
    - Draw line from lifeline of `test()`
    - `<<metaclass>>:ClassThree` should not be present as an option
- **Expected Results**
    - Upon drawing the line from the lifeline, `<<metaclass>>:ClassThree` should not be present as an option
- **Actual Results**
    - TouchCORE functioned as expected, and `<<metaclass>>:ClassThree` was not present as an option
- **Verdict**
    - **PASS**

#### 1.c: Attempt to add a message that cares a new instance for an undefined class (not possible)
- **Performing Test**
    - Draw line from lifeline of `test()`
    - Search for the class `Foo`
    - No class should be present
- **Expected Results**
    - There should be no option `Foo` in the search box
- **Actual Results**
    - TouchCORE functioned as expected, and `Foo` was not present in the search box
- **Verdict**
    - **PASS**

#### 1.d: Attempt to add a message that creates a new instance for a defined class after a reply (should not be possible)
- **Performing Test**
    - Attempt to draw a line underneath the reply message from `test()`
- **Expected Result**
    - The user should not be able to do so
- **Actual Result**
    - TouchCORE functioned as expected
- **Verdict**
    - **Pass**

### 2. The ability to add a message with a new lifeline as target
All tests use the model described in **Req2** under `acceptance_testing_models`, and performed on `Test.test()`, unless otherwise noted

#### 2.a: Attempt to add a message that creates a new lifeline, referencing a class attribute (verify the lifeline is created)
##### 2.a.a: Do so as the first message
- **Performing Test**
    - Draw a line from `test()` and select `myClassOne:ClassOne`
    - Select `ClassOne.test1()`
- **Expected Result**
    - A new lifeline should be created, referencing `myClassOne`
- **Actual Result**
    - TouchCORE functioned as expected, and a new lifeline was created
- **Verdict**
    - **PASS**

##### 2.a.b: Do so after a message to a different class
- **Performing Test**
    - Draw a line from `test()` and select `myClassOne:ClassOne`
    - Select `ClassOne.test1()`
    - Draw a line from `test()` underneath the previous message, and select `myClassTwo:ClassTwo`
    - Select `ClassTwo.test2()`
- **Expected Result**
    - A lifeline should be created for `myClassOne` and a separate lifeline for `myClassTwo`
- **Actual Result**
    - TouchCORE functioned as expected, and a new lifeline was created
- **Verdict**
    - **PASS**

##### 2.a.c: Do so after a combined fragment
- **Performing Test**
    - Create a combined fragment
    - Draw a line from `test()` underneath the combined fragment, and select `myClassOne:ClassOne`
    - Select `ClassOne.test1()`
- **Expected Result**
    - The new lifeline should be created and referenced by `test1()`
- **Actual Result**
    - TouchCORE functioned as expected, and a new lifeline was created
- **Verdict**
    - **PASS**

##### 2.a.d: Do so within a combined fragment
- **Performing Test**
    - Create a combined fragment
    - Draw a line from `test()` within the combined fragment, and select `myClassOne:ClassOne`
    - Select `ClassOne.test1()`
- **Expected Result**
    - The new lifeline should be created and referenced by `test1()` within the combined fragment
    - The combined fragment should expand to include the reference to `test1()` 
- **Actual Result**
    - TouchCORE functioned as expected, and a new lifeline was created within the combined fragment
- **Verdict**
    - **PASS**
#### 2.b: Attempt to add a message that creates a new lifeline, with no class attributes defined (should not allow you to do so)
- NOTE: This test was performed with class `TestEmpty` on method `testEmpty()`
- **Performing Test**
    - Draw a line from the lifeline of `testEmpty()`
    - Search for `ClassOne`
- **Expected Result**
    - In the search box, no options should be present
- **Actual Result**
    - TouchCORE functioned as expected, and no options were present
- **Verdict**
    - **PASS**
#### 2.d: Attempt to add a message referencing a private method of the class (should not be possible)
- **Performing Test**
    - Draw a line from the lifeline of `test()`
    - Select `myClassOne:ClassOne`
    - There should not be an options `MyClassOne.privateMethod()`
- **Expected Result**
    - `MyClassOne.privateMethod()` should not be present as an option for `myClassOne`
- **Actual Result**
    - `MyClassOne.privateMethod()` was present, and created a lifeline
- **Verdict**
    - **FAIL**
    - The user should not be able to use a private method to create a lifeline

### 3: The ability to add a message with an existing lifeline as target
All tests use the model described in **Req3** under `acceptance_testing_models`, and performed on `Test.test()`

#### 3.a: Attempt to add a message to a class attribute who's lifeline already exists (should add the message farther down the lifeline)
##### 3.a.a: Do so within a combined fragment, when the lifeline was created within the combined fragment
- **Performing Test**
    - Create a combined fragment
    - Draw a line from the lifeline of `test()`, starting from within the combined fragment
    - Select `ClassOne.testFirst()`
    - Draw a line from the lifeline of `test()` below the previous message, but still within the combined fragment to the lifeline of `myClassOne`
    - Select `ClassOne.testSecond()`
- **Expected Result**
    - `testSecond()` should be created below the line for `testFirst()` and point to the previously created lifeline
    - Both the arrows for `testFirst()` and `testSecond()` should be inside of the combined fragment
    - The combined fragment should expand to include the part of the lifeline of `myClassOne` referenced by `testFirst()` and `testSecond()`
- **Actual Result**
    - TouchCORE functioned as expected, and `testSecond()` successfully attached to the lifeline of `myClassOne`
- **Verdict**
    - **PASS**

##### 3.a.a: Do so within a combined fragment, when the lifeline was outside the combined fragment
- **Performing Test**
    - Draw a line from the lifeline of `test()`
    - Select `myClassOne:ClassOne`
    - Select `ClassOne.testFirst()`
    - Create a combined fragment below the previous message
    - Draw a line from the lifeline of `test()` within the combined fragment to the lifeline of `myClassOne`
    - Select `ClassOne.testSecond()`
- **Expected Result**
    - `testSecond()` should be created below the line for `testFirst()` and point to the previously created lifeline
    - The combined fragment should expand to be include the call to `testSecond()` and the part of the lifeline of `myClassOne` where `testSecond()` is called
- **Actual Result**
    - TouchCORE functioned as expected, and the message for `testSecond()` was attached to the preexisting lifeline
- **Verdict**
    - **PASS**

##### 3.a.b: Do so outside of a combined fragment
- **Performing Test**
    - Draw a line from the lifeline of `test()`
    - Select `myClassOne:ClassOne`
    - Select `ClassOne.testFirst()`
    - Draw a line from the lifeline of `test()` below the previous message to the lifeline of `myClassOne`
    - Select `ClassOne.testSecond()`
- **Expected Result**
    - `testSecond()` should be created below the line for `testFirst()` and point to the previously created lifeline
- **Actual Result**
    - TouchCORE functioned as expected, and the message for `testSecond()` was attached to the preexisting lifeline
- **Verdict**
    - **PASS**

##### 3.a.d: Do so after a previous reference to an existing lifeline
- **Performing Test**
    - Draw a line from the lifeline of `test()`
    - Select `myClassOne:ClassOne`
    - Select `ClassOne.testFirst()`
    - Draw a line from the lifeline of `test()` below the previous message to the lifeline of `myClassOne`
    - Select `myClassOne:ClassOne`
    - Select `ClassOne.testSecond()`
    - Draw a line from the lifeline of `test()` below the previous message to the lifeline of `myClassOne`
    - Select `ClassOne.testThird()`
- **Expected Result**
    - `testSecond()` should be created below the line for `testFirst()` and point to the previously created lifeline
    - `testThird()` should be created below the line for `testSecond()` and point to the previously created lifeline
- **Actual Result**
    - TouchCORE functioned as expected, and the message for `testThird()` was attached to the preexisting lifeline after `testSecond()`
- **Verdict**
    - **PASS**
##### 3.a.e: Do so using the same method that created the lifeline
- **Performing Test**
    - Draw a line from the lifeline of `test()`
    - Select `myClassOne:ClassOne`
    - Select `ClassOne.testFirst()`
    - Draw a line from the lifeline of `test()` below the previous message to the lifeline of `myClassOne`
    - Select `ClassOne.testFirst()`
- **Expected Result**
    - `testFirst()` should be created below the line for `testFirst()` and point to the previously created lifeline
- **Actual Result**
    - TouchCORE functioned as expected, and the message for `testFirst()` was created underneath the previous call to `testFirst()`
- **Verdict**
    - **PASS**
#### 3.b: Attempt to add a message to a newly created instance who's lifeline already exists (should add the message farther down the lifeline)
##### 3.b.a: Do so within a combined fragment, for an instance created inside the fragment
- **Performing Test**
    - Create a combined fragment
    - Draw a line from the lifeline of `test()`, starting from within the combined fragment
    - Select `<<metaclass>>:ClassTwo`
    - Select `ClassTwo.create()`
    - Draw a line from the lifeline of `test()`, starting from below the `create()` call but still within the combined fragment to the lifeline of `classtwo`
    - Select `ClassTwo.testTwo()`
- **Expected Result**
    - An arrow should be drawn from below the `classtwo := create()` call to the lifeline created by it with the label `testTwo()`
    - The combined fragment should expand to include both the `create()` and the `testTwo()` calls
- **Actual Result**
    - The application freezes, and fails to draw the line
- **Verdict**
    - **FAIL**

##### 3.b.b: Do so within a combined fragment, for an instance created outside the fragment
- **Performing Test**
    - Draw a line from the lifeline of `test()`, starting from within the combined fragment
    - Select `<<metaclass>>:ClassTwo`
    - Select `ClassTwo.create()`
    - Create a combined fragment
    - Draw a line from the lifeline of `test()` within the combined fragment to the lifeline of `classtwo`
    - Select `ClassTwo.testTwo()`
- **Expected Result**
    - An arrow should be drawn from below the `classtwo := create()` call to the lifeline created by it with the label `testTwo()`
    - The combined fragment should expand to include just the call to `testTwo()`
- **Actual Result**
    - TouchCORE functioned as expected. The arrow was drawn correctly with the correct label, and the combined fragment expanded
- **Verdict**
    - **PASS**

##### 3.b.c: Do so outside of a combined fragment
- **Performing Test**
    - Draw a line from the lifeline of `test()`, starting from within the combined fragment
    - Select `<<metaclass>>:ClassTwo`
    - Select `ClassTwo.create()`
    - Draw a line from the lifeline of `test()` after the call to `create()`
    - Select `ClassTwo.testTwo()`
- **Expected Result**
    - An arrow should be drawn from below the `classtwo := create()` call to the lifeline created by it with the label `testTwo()`
- **Actual Result**
    - TouchCORE functioned as expected. Both the label and the arrow were correctly drawn
- **Verdict**
    - **PASS**

##### 3.b.d: Do so after a previous reference to an existing lifeline
- **Performing Test**
    - Draw a line from the lifeline of `test()`, starting from within the combined fragment
    - Select `<<metaclass>>:ClassTwo`
    - Select `ClassTwo.create()`
    - Draw a line from the lifeline of `test()` after the call to `create()`
    - Select `ClassTwo.testTwo()`
    - Draw a line from the lifeline of `test()` after the call to `create()`
    - Select `ClassTwo.testTwo()`
- **Expected Result**
    - For both of the calls to `testTwo()`, an arrow should be drawn, in the correct order, below the call to `classtwo := create()` and both with the label `testTwo()`
- **Actual Result**
    - TouchCORE functioned as expected. 
- **Verdict**
    - **PASS** 

##### 3.b.e: Do so, attempting to draw the line to the start of the lifeline
- **Performing Test**
    - Draw a line from the lifeline of `test()`, starting from within the combined fragment
    - Select `<<metaclass>>:ClassTwo`
    - Select `ClassTwo.create()`
    - Draw a line from the lifeline of `test()` after the call to `create()`, but as close to the start of the lifeline as possible
    - Select `ClassTwo.testTwo()`
- **Expected Result**
    - An arrow should with the label `testTwo()` below the start of the lifeline
- **Actual Result**
    - The application freezes and fails to draw the line or label
- **Verdict**
    - **FAIL**

#### 3.c: Attempt to add a message to a lifeline that has been destroyed (should not allow you to do so)
##### 3.c.a: Do so for a class attribute
- **Performing Test**
    - Draw a line from `test()`
    - Select `ClassOne.testFirst()`
    - Draw a line from `test()` below the previous line, to the lifeline of `myClassOne`
    - Select `ClassOne.destroy()`
    - Draw a line from `test()` below the call to `destroy()` to the lifeline of `myClassOne`
- **Expected Result**
    - TouchCORE should not do anything when the line is released on the lifeline after a call to `destroy()`
- **Actual Result**
    - TouchCORE freezes
- **Verdict**
    - **FAIL**

##### 3.c.b: Do so for an instance created by the lifeline
- **Performing Test**
    - Draw a line from `test()`
    - Select `<<metclass>>:ClassTwo`
    - Select `ClassTwo.testTwo()`
    - Draw a line from `test()` below the previous line, to the lifeline of `classtwo`
    - Select `ClassTwo.destroy()`
    - Draw a line from `test()` below the call to `destroy()` to the lifeline of `classtwo`
- **Expected Result**
    - TouchCORE should not do anything when the line is released on the lifeline after a call to `destroy()
- **Actual Result**
    - TouchCORE freezes
- **Verdict**
    - **FAIL**
