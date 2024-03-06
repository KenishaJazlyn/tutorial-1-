## Module 1
https://eshop-kenishajazlyn.koyeb.app/
### Reflection 1

**You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code.** 

I have check again my source code and evaluate the coding standards that i've learned in this module. I have used variables, function,classes, and arguments with meaningful name and self-descriptive. All of my functions already short, well named, and nicely organized. I already make sure that all my functions do one thing, do it well and do it only.


### Reflection 2

**After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?**

After following the tutorial and attempting to create tests for editing and deleting products, I feel the need to read more on this topic because I still have some questions about it. The number of unit test in a class is not fixed, it depends on the complexity.  Having 100% code coverage does not guarantee that your code is free from bugs or errors. Code coverage measures whether lines of code or branches in control structures have been executed, but it does not assess the quality of the tests or whether all edge cases have been considered. Even with full code coverage, logical errors in the code may not be detected if the tests do not account for all possible input combinations and edge cases.

## Module 2

**List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.**

I have added more tests for Product Controller, Product Service, Home Page Controller, and Home Page Function.Then i checked which part that were not executed by the test suite, and I try to improve some of my tests.


**Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous**

Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!
Yes, i do think that my implementation has met the definition of Continuous Integration and Continuous Deployment. I have implemented testing tool such ad JUnit, OSSF Scorecard, and Sonarcloud for the CI,  and Koyeb for CD. By automatically running tests and quality checks with each commit, and ensuring that code is automatically deployed after passing these checks. 

## Module 3
**Explain what principles you apply to your project!**
I applied `Liskov Substitution Principle`, `Single Responsibility Principle` and `Dependency Inversion Principle`. 
`Liskov Substitution Principle`: Objects of a superclass should be replaceable with objects of its subclasses without breaking the system.

`Single Responsibility Principle` : Each class should be responsible for a single part or functionality of the system.

`Dependency Inversion Principle` : High-level modules should not depend on low-level modules, both should depend on abstractions.

**Explain the advantages of applying SOLID principles to your project with examples.**
`The SOLID principles` ensures my software is modular and easy to maintain, understand, debug, and refactor. Following SOLID also helps save time and effort in both development and maintenance. SOLID prevents your code from becoming rigid and fragile, which helps you build long-lasting software.


**Explain the disadvantages of not applying SOLID principles to your project with examples.**
Not applying the `SOLID principles` can lead to software that is hard to maintain, difficult to extend, and fragile in the face of changes, significantly increasing the technical debt and potentially leading to higher costs in the long term.


# Module 4

**Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.**
    I think the TDD approach very helpful. The core premise of TDD is to write tests before writing the code that passes those tests, which is intended to increase software quality and ensure that the software behaves as expected. While TDD might initially seem to slow down development due to the extra time spent writing tests, the payoff should be in reduced debugging and maintenance time.


**You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.**
    Yes, My tests are Fast, ensuring it don't slow down development. They're Independent, each test can run alone without affecting others. They're Repeatable across any environment, Self-validating with clear pass or fail outcomes, and Timely, developed alongside the code they test.