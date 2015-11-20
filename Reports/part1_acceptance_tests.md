# Part 1: Acceptance Testing
## Justification of Approach
The approach we have decided to use is to generate the test cases from the functional requirements. This was first done by defining use cases for each function requirement, and generating scenarios based on those use cases. This approach provides a clear, step-by-step approach for both the testing of the scenario, as well as the expected result. By doing this, a tester can perform test cases with a path and goal in mind. In addition, many other black box testing approaches rely on numerical ranges or sets, and as the functional requirements do not lend themselves to this level of quantification, they approaches are not very well suited for the testing required.

## Achieved Coverage
We believe that we have achieved a relatively high level of coverage. This is due to including all types of messages where applicable, as well as testing scenarios both within, and outside of combined fragments. This involved considering both the origin of messages (i.e., from within or outside the combined fragment) for both the set up of the set, and the test itself.

## Use Cases
### 1: The ability to add a message that creates a new instance (and consequently a new lifetime)
**Title**: Create a New Instance

**Preconditions**: Any required classes have been created with the required methods, User is in the Message View

**Main Scenario**
1. User draws a line from the lifeline of the chosen method
2. User chooses a `<<metaclass>>`
3. User chooses `Class.create()`
4. A new lifeline is created, and is pointed to by the `create()` call

**Alternative Scenarios**
- **1a)** The user draws the line from under a message to a different class
- **1b)** The user draws the line after a combined fragment
- **1c)** The user draws the line from within a combined fragment
- **1d)** The user draws the line after a previous `create`/`destroy` of the same class
- **1e)** The user draws the line after a previous `create`/`destroy` of a different class

**Error Scenarios**
- **1f)** The user attempts to add a message that creates a new instance for a defined class after a reply
- **2a)** The user attempts to add a message that creates a new instance for an undefined class
- **3a)** The user attempts to add a message that creates a new instance for a defined class without a `create` method


### 2: The ability to add a message with a new lifeline as target
**Title**: Use a New Lifeline

**Preconditions**: Any required classes have been created with the required methods, User is in the Message View

**Main Scenario**
1. User draws a line from the primary lifeline 
2. User chooses an attribute
3. User chooses a method on the attribute
4. A new lifeline is created, and is the lifeline of the chosen attribute

**Alternative Scenarios**
- **1a)** The user draws a line after a message to a different class
- **1b)** The user draws a line after a combined fragment
- **1c)** The user draws the line within a combined fragment

**Error Scenarios**
- **2a)** The user attempts to add a message with no class attributes defined
- **3a)** The user attempts to use a private method of the chosen attribute

### 3: The ability to add a message with an existing lifeline as target
**Title**: Add a Message to an Existing Lifeline

**Preconditions**: Any required classes have been created with the required methods, User is in the Message View

**Main Scenario**
1. User draws a line from the primary lifeline, to the lifeline of a pre-existing lifeline of a class attribute
2. User chooses a method on the attribute
3. A new message is added farther down the lifeline of the attribute

**Alterative Scnearios**
- **1a)** The user draws a line from within a combined fragment on the primary lifeline, with the newly-created instance created within the combined fragment
- **1b)** The user draws a line form within a combined fragment on the primary lifeline, with the newly-created instance created outside the combined fragment
- **1c)** The user draws a line from within a combined fragment on the primary lifeline, with the pre-existing lifeline created in that combined fragment
- **1d)** The user draws a line from within a combined fragment on the primary lifeline, with the pre-existing lifeline created outside that combined fragment
- **1e)** The user attempts to draw a line from the primary lifeline, to the start of the lifeline of a created instance

**Error Scenarios**
- **1f)** The user attempts to draw a line from the primary lifeline, to after the destroy message of a class attribute
- **1g)** The user attempts to draw a line from the primary lifeline, to after the destroy message of a newly created instance

### 4: The ability to add a reply message
**Title**: Add a Reply Message

**Preconditions**: Any required classes have been created with the required methods, User is in the Message View

**Main Scenario**
1. User holds down on the primary lifeline, within a combined fragment, until the message window appears, for a message with a non-void return type
2. User chooses `Create Reply Message`
3. A new reply message is created

**Error Scenarios**
- **1a)** The user attempt to add a reply message for a method with non-void return type outside of a combined fragment
- **1c)** The user attempt to add a reply message for a method with void return type outside of a combined fragment
- **1b)** The user attempt to add a reply message for a method with void return type inside of a combined fragment

### 5: The ability to delete one or several message
**Title**: Delete A Message

**Preconditions**: Any required classes have been created with the required methods, User is in the Message View

**Main Scenario**
1. The user holds down on a message to a lifeline with multiple messages
2. The user chooses `Delete`
3. The message is deleted

**Alternative Scenario**
- **1a)** The message is to lifeline with only a single message
    - **1a1)** When the user chooses `Delete`, the lifeline is removed as well
- **1b)** The message is inside of a combined fragment
- **1c)** The message is a `create` message
    - **1c1)** The lifeline of the created instance should be removed along with any messages to it
- **1d)** The message is a `destroy` message
- **1e)** The message is a `self` message


## Test Scenarios
### Use Case 1: The ability to add a message that creates a new instance (and consequently a new lifeline)
- **S1.1**
    - 1 → 2 → 3 → 4
    - User creates a new instance of a class and consequently a new lifeline, normal course of events
- **S1.2**
    - 1a → 2 → 3 → 4
    - User creates a new instance of after a message to a different class
- **S1.3**
    - 1b → 2 → 3 → 4
    - User creates a new instance of after a combined fragment
- **S1.4**
    - 1c → 2 → 3 → 4
    - User creates a new instance from within a combined fragment
- **S1.5**
    - 1d → 2 → 3 → 4
    - User creates a new instance after a previous `create`/`destroy` of the same class
- **S1.6**
    - 1e → 2 → 3 → 4
    - User creates a new instance after a previous `create`/`destroy` of a different class
- **S1.7**
    - 1f
    - User cannot create a new instance after a reply
- **S1.8**
    - 1 → 2a
    - User cannot create a new instance of a class that doesn't exist
- **S1.9**
    - 1 → 3a
    - User cannot create a new instance of a class that does not have a `create` method

### Use Case 2: The ability to add a message with a new lifeline as target
- **S2.1**
    - 1 → 2 → 3 → 4
    - User creates a new lifeline to a class attribute
- **S2.2**
    - 1a → 2 → 3 → 4
    - User creates a new lifeline after a message to a different class
- **S2.3**
    - 1b → 2 → 3 → 4
    - User creates a new lifeline after a combined fragment
- **S2.4**
    - 1c → 2 → 3 → 4
    - User creates a new lifeline within a combined fragment
- **S2.5**
    - 1 → 2a
    - Users cannot create a new lifeline using a class attribute, when no attributes are defined
- **S2.6**
    - 1 → 2 → 3a
    - User cannot create a new lifeline using a private method

### Use Case 3: The ability to add a message with an existing lifeline as target
- **S3.1**
    - 1 → 2 → 3
    - User adds a message to an existing lifeline
- **S3.2**
    - 1a → 2 → 3
    - User adds a message from within a combined fragment to the lifeline of a new instance also created inside the fragment
- **S3.3**
    - 1b → 2 → 3
    - User adds a message from within a combined fragment to the lifeline of a new instance created outside the fragment
- **S3.4**
    - 1c → 2 → 3
    - User adds a message from within a combined fragment to the lifeline of a class attributed created inside the fragment
- **S3.5**
    - 1d → 2 → 3
    - User adds a message from within a combined fragment to the lifeline of a class attributed created outside the fragment
- **S3.6**
    - 1e → 2 → 3
    - User draws the line to the start of the newly created lifeline
- **S3.7**
    - 1f
    - User cannot create a message to the lifeline of a class attribute that has been destroyed
- **S3.8**
    - 1g
    - User cannot create a message to the lifeline of a newly created class that has been destroyed

### Use Case 4: The ability to add a reply message
- **S4.1**
    - 1 → 2 → 3
    - User can add a reply message for a non-void method inside a combined fragment
- **S4.2**
    - 1a
    - User cannot add a reply message for a non-void method outside of a combined fragment
- **S4.3**
    - 1b
    - User cannot add a reply message for a void method outside of a combined fragment
- **S4.4**
    - 1c
    - User cannot add a reply message for a void message inside of a combined fragment

### Use Case 5: The ability to delete one or several messages
- **S5.1**
    - 1 → 2 → 3
    - User deletes a messages
- **S5.2**
    - 1a → 2 → 3
    - If a user deletes the last message to a lifeline, that lifeline should be deleted
- **S5.3**
    - 1b → 2 → 3
    - User deletes a message inside a combined fragment
- **S5.4**
    - 1c → 2 → 3
    - If a user deletes a `create` message, the entire lifeline created by the `create` call should be removed along with the messages to it
- **S5.5**
    - 1d → 2 → 3
    - User deletes a `destroy` message
- **S5.6**
    - User deletes a `self` message

## Test Case Execution
### 1: The ability to add a message that creates a new instance (and consequently a new lifeline)
All tests use the model described in **Req1** under `acceptance_testing_models`, and performed on `Test.test()`

#### S1.1
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

#### S1.2
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

#### S1.3
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


#### S1.4
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

#### S1.5
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

#### S1.6
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

#### S1.7
- **Performing Test**
    - Attempt to draw a line underneath the reply message from `test()`
- **Expected Result**
    - The user should not be able to do so
- **Actual Result**
    - TouchCORE functioned as expected
- **Verdict**
    - **Pass**

#### S1.8
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

#### S1.9
- **Performing Test**
    - Draw line from lifeline of `test()`
    - `<<metaclass>>:ClassThree` should not be present as an option
- **Expected Results**
    - Upon drawing the line from the lifeline, `<<metaclass>>:ClassThree` should not be present as an option
- **Actual Results**
    - TouchCORE functioned as expected, and `<<metaclass>>:ClassThree` was not present as an option
- **Verdict**
    - **PASS**


### 2. The ability to add a message with a new lifeline as target
All tests use the model described in **Req2** under `acceptance_testing_models`, and performed on `Test.test()`, unless otherwise noted

#### S2.1
- **Performing Test**
    - Draw a line from `test()` and select `myClassOne:ClassOne`
    - Select `ClassOne.test1()`
- **Expected Result**
    - A new lifeline should be created, referencing `myClassOne`
- **Actual Result**
    - TouchCORE functioned as expected, and a new lifeline was created
- **Verdict**
    - **PASS**

#### S2.2
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

#### S2.3
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

#### S2.4
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

#### S2.5
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

#### S2.6
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

#### S3.1
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

#### S3.2
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

#### S3.3
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

#### S3.4
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

#### S3.5
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

#### S3.6
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

#### S3.7
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

#### S3.8
- **Performing Test**
    - Draw a line from `test()`
    - Select `<<metclass>>:ClassTwo`
    - Select `ClassTwo.testTwo()`
    - Draw a line from `test()` below the previous line, to the lifeline of `classtwo`
    - Select `ClassTwo.destroy()`
    - Draw a line from `test()` below the call to `destroy()` to the lifeline of `classtwo`
- **Expected Result**
    - TouchCORE should not do anything when the line is released on the lifeline after a call to `destroy()`
- **Actual Result**
    - TouchCORE freezes
- **Verdict**
    - **FAIL**


### 4: The ability to add a reply message
All tests use the model described in **Req4** under `acceptance_testing_models`, and performed on `Test.test()` for the non-void methods, and `Test.testVoid()` for the void methods

#### S4.1
- **Performing Test**
    - Create a combined fragment on the primary lifeline
    - Hold down on the lifeline inside of the combined fragment
    - Select `Create Reply Message`
- **Expected Result**
    - The reply message should be created
- **Actual Result**
    - TouchCORE functioned as expected
- **Verdict**
    - **PASS**

#### S4.2
- **Performing Test**
    - Hold down on the primary lifeline of `test()`
- **Expected Result**
    - There should not be an option to create a reply message
- **Actual Result**
    - TouchCORE functioned as expected
- **Verdict**
    - **PASS**

#### S4.3
- **Performing Test**
    - Create a combined fragment on the primary lifeline of `testVoid()`
    - Hold down on the lifeline inside of the combined fragment
- **Expected Result**
    - There should not be an option to create a reply message
- **Actual Result**
    - TouchCORE functioned as expected
- **Verdict**
    - **PASS**

#### S4.4
- **Performing Test**
    - Hold down on the primary lifeline of `testVoid()`
- **Expected Result**
    - There should not be an option to create a reply message
- **Actual Result**
    - TouchCORE functioned as expected
- **Verdict**
    - **PASS**

### 5: The ability to delete one or several messages
All tests use the model described in **Req4** under `acceptance_testing_models`, and performed on `Test.test()`

#### S5.1
- **Performing Test**
    - Draw a line from the primary lifeline of `test()`
    - Select `myClassOne:ClassOne`
    - Select `ClassOne.test1()`
    - Draw a line from the primary lifeline of `test()` to the lifeline of `myClassOne`
    - Select `ClassOne.test1()`
    - Hold down on the second message until the box appears
    - Select `Delete`
- **Expected Result**
    - The message and the arrow to it should be deleted
- **Actual Result**
    - TouchCORE functioned as expected and the message was deleted
- **Verdict**
    - **PASS**

#### S5.2
- **Performing Test**
    - Draw a line from the primary lifeline of `test()`
    - Select `myClassOne:ClassOne`
    - Select `ClassOne.test1()`
    - Hold down on the second message until the box appears
    - Select `Delete`
- **Expected Result**
    - The message should be deleted, as well as the lifeline of `myClassOne`
- **Actual Result**
    - The message was deleted, however the lifeline was not deleted
- **Verdict**
    - Unclear, as this may be intentional

#### S5.3
- **Performing Test**
    - Create a combined fragment
    - Draw a line from the primary lifeline of `test()` within the combined fragment
    - Select `myClassOne:ClassOne`
    - Select `ClassOne.test1()`
    - Draw a line from the primary lifeline of `test()` within the combined fragment to the lifeline of `myClassOne`
    - Select `ClassOne.test1()`
    - Hold down on the second message until the box appears
    - Select `Delete`
- **Expected Result**
    - The message should be deleted, and the combined fragment should shrink to only include the single method
- **Actual Result**
    - TouchCORE functioned as expected. The message was deleted and the combined fragment shrank
- **Verdict**
    - **PASS**

#### S5.4
- **Performing Test**
    - Draw a line from the primary lifeline of `test()` within the combined fragment
    - Select `<<metaclass>>:ClassOne`
    - Select `ClassOne.create()`
    - Draw a line from the primary lifeline to the lifeline of `classone`
    - Select `ClassOne.test1()`
    - Hold down on the `create` message until the box appears
    - Select `Delete`
- **Expected Result**
    - Both the `create()` and the `test1()` methods should be deleted along with the lifeline of the `classone`
- **Actual Result**
    - Neither the `test1()` nor the lifeline of `classone` were deleted. In addition, the label for `test1()` is no longer aligned with the arrow corresponding to it
- **Verdict**
    - **FAIL**

#### S5.5
- **Performing Test**
    - Draw a line from the primary lifeline of `test()` within the combined fragment
    - Select `<<metaclass>>:ClassOne`
    - Select `ClassOne.create()`
    - Draw a line from the primary lifeline to the lifeline of `classone`
    - Select `ClassOne.destroy()`
    - Hold down on the `destroy` message until the box appears
    - Select `Delete`
- **Expected Result**
    - Both the `destroy()` message and the icon indicating that the lifeline has been destroyed 
- **Actual Result**
    - TouchCORE functioned as expected, and both the message and the icon were deleted
- **Verdict**
    - **PASS**

#### S5.6
- **Performing Test**
    - Hold down on the lifeline of `test()`
    - Select `Create Self Message`
    - Select `ClassOne.test()`
    - Hold down on `test()`
    - Select `Delete`
- **Expected Result**
    - The self message should be deleted
- **Actual Result**
    - The self message was deleted
- **Verdict**
    - **PASS**
