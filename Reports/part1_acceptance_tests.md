# Part 1: Acceptance Testing
## Justification of Approach
The approach we have decided to use is to generate the test cases from the functional requirements. Because the functional requirements are clearly stated, and there are neither clear categories, nor requirements on the parameters provided, generating a test case showing the requirement showing the requirement working as expected, and one or more test cases attempting to create an error will be sufficient to show the is capable of performing the requisite tasks.

## Achieved Coverage
** TODO **

## Test Cases
### Test Situations
1. The ability to add a message that creates a new instance (and consequently a new lifeline)
    - **1.a)** Attempt to add a message that creates a new instance for a defined class (verify object is created before message sent) (verify lifeline is started at `create`)
        - Do so as the first message
        - Do so after a message to a different class
        - Do so after a combined fragment
        - Do so within a combined fragment
        - Do so after a previous create/destroy of the same class
    - **1.b)** Attempt to add a message that create a new instance for a defined class with no create method (not possible)
    - **1.c)** Attempt to add a message that cares a new instance for an undefined class (not possible)
    - **1.d)** Attempt to add a message that creates a new instance for a defined class after a reply (should not be possible)
2. The ability to add a message with a new lifeline as target
    - **2.a)** Attempt to add a message that creates a new lifeline, referencing a class attribute (verify the lifeline is create)
        - Do so as the first message
        - Do so after a message to a different class
        - Do so after a combined fragment
        - Do so within a combined fragment
        - Do so after a previous message to the lifeline (i.e. a -> b, a -> b)
        - Do so after a previous message to the lifeline, with another reference inbetween (a -> b, a -> c, a -> b)
    - **2.b)** Attempt to add a message that creates a new lifeline, with no class attributes defined (should not allow you to do so)
3. The ability to add a message with an existing lifeline as target
    - **3.a)** Attempt to add a message to a class attribute who's lifeline already exists (should add the message farther down the lifeline)
    - **3.b)** Attempt to add a message to a newly created instance who's lifeline already exists (should add the message farther down the lifeline)
    - **3.c)** Attempt to add a message to a lifeline that was created but has since been destroyed (should not allow you to do so)
4. The ability to add a reply message
    - **4.a)** Attempt to add a reply message inside of a combined fragment for a method with non-void return type (should work)
    - **4.b)** Attempt to add a reply message for a method with a non-void return type outside of a combined fragment (should not allow you to do so)
    - **4.c)** Attempt to add a reply message for a method with a void return type outside a combined fragment (should not allow you to do so)
    - **4.d)** Attempt to add a reply message for a method with a void return type inside a combined fragment (should not allow you to do so)
5. The ability to delete one or several messages.
    - **5.a)** Attempt to delete a single message
