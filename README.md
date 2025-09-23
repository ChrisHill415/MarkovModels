# Project 1.1: SimpleMarkov, Project 1.2 HashMarkov, Fall 2025

This is the high-level project document for Project P1-Markov-GenAI in CompSci 201 at Duke University, Fall 2025.

See [the details document](docs/details.md) for information on using Git, starting the project, 
and more details about the project including information about the classes and concepts 
that are outlined briefly below. 

*You'll absolutely need to read the information* in the [details document](docs/details.md) to understand how the classes 
in this project work independently and together. In particular, information about testing with `JUnit` is 
in that document and you'll want to test your code with the `JUnit` tests.


## Introduction

Random Markov processes are widely used in Computer Science and in analyzing different forms of data. This project offers an 
occasionally amusing look at a *generative AI model* for creating realistic looking text in a data-driven way by training a model. 
You'll build a simple, almost brute force model as Project 1.1,
continue to build on this project with a more efficient version as Project 1.2, and then a classification problem
after training models as Project 2.

Generative models of the sort you will build are of great interest to researchers in artificial intelligence and machine learning generally, and especially those in the field of *natural language processing* (the use of algorithmic and statistical AI/ML techniques on human language). One recent and powerful example of such text-generation model via statistical machine learning program is the [OpenAI GPT project](https://openai.com/blog/chatgpt). In this
project youll be creating what are reasonably and historically called _small language models_ as opposed to the _large language models_ 
(LLMs) behind ChatGPT, Claude, Gemini, and more.

### Historical details of this assignment (optional, Engagement points)

The historical and literary references below can be read for optional enagagement points by filling out the questions accessible via this form **by the end of Tuesday 9/23**: [https://forms.cloud.microsoft/r/UbNXEm1cDT](https://forms.cloud.microsoft/r/UbNXEm1cDT). 
You don't need to read these references to do the project, but you do to earn six engagement points
 *without looking at or writing code*! 

This assignment has its roots in several places. The true mathematical roots are from a 1948 monolog by Claude Shannon, 
[A Mathematical Theory of Communication](https://people.math.harvard.edu/~ctm/home/text/others/shannon/entropy/entropy.pdf) which discusses in detail the mathematics and intuition behind this assignment. In particular, the `BaseMarkovModel` uses the approach Shannon describes below:

> To construct [a Markov model of order 1], for example, one opens a book at random and selects a letter at random on the page. 
This letter is recorded. The book is then opened to another page and one reads until this letter is encountered. The succeeding 
letter is then recorded. Turning to another page this second letter is searched for and the succeeding letter recorded, etc. It would be 
interesting if further approximations could be constructed, but the labor involved becomes enormous at the next stage.

(See early *two engagement points*, find something interesting about Claude Shannon: https://forms.cloud.microsoft/r/UbNXEm1cDT. 

You can see Shannon's ideas expressed in the so-called [_Infinite Monkey Theorem_](https://en.wikipedia.org/wiki/Infinite_monkey_theorem) which, in turn, is the basis for a somewhat amusing or arguably disturbing short story named _Inflexible Logic_ now found in pages 91-98 from [_Fantasia Mathematica (Google Books)_](http://books.google.com/books?id=9Xw8tMEmXncC&printsec=frontcover&pritnsec=frontcover#PPA91,M1) and reprinted from a 
[1940 New Yorker story called by Russell Maloney](https://www.newyorker.com/magazine/1940/02/03/inflexible-logic). For four engagement points, read this short story and 
the Wikipedia article linked above and answer the questions in the form: https://forms.cloud.microsoft/r/UbNXEm1cDT. You must indicate that you 
actually read, and did not ask an LLM to summarize
(you will know whether that's true). *This early engagement must be completed by the early submission/bonus deadline*.


This assignment has its roots in a Nifty Assignment designed by Joe Zachary from U. Utah, assignments from Princeton designed by 
Kevin Wayne and others, and the work done at Duke starting with Owen Astrachan and continuing with Jeff Forbes, Salman Azhar, Brandon Fain, 
and the UTAs from Compsci 201.

This new version is built for 201 in the Fall 2025 course offering and differs in *several details* from previous versions. This
means that using an LLM or previous solutions may not be relevant.


## General Work for this project

Your goal is to create two working versions of the code started in the `BaseMarkovModel` class. As you'll see below, you'll call the 
new class `SimpleMarkovModel` for part 1.1. You'll build on this code with 
a more efficient version `HashMarkovModel` in part 1.2 for 201.

Summary:
    - fork and clone (or download zip) the project files
    - run ChatGPTDriver which uses the provided `BaseMarkovModel` class (and fails)
    - design, implement, test `SimpleMarkovModel` which uses brute-force to generate text (P1.1)
    - design, implement, test `HashMarkovModel`which does this more efficiently (P1.2)
    - answer analysis questions (can do after p1.1 and after p1.2 or ...)


## Running Driver Code

The primary driver code for this assignment is located in `Chat201Driver.java`. You should be able to run 
the `public static void main` method of `Chat201Driver` 
immediately after cloning the starter code, and should see something like the output shown below (noting that your exact 
runtimes will likely be different / machine dependent). Note that *there is no random text generated* 
because the code in `BaseMarkovModel` does *not* work. You'll change the object returned by the method `getModel` to return
a `SimpleMarkovModel` (uncomment the line for that) and after completing the code in `SimpleMarkovModel` this program
should generate different output.

Expand for example output of `Chat201Driver` with starter code using `BaseMarkovModel`.

```
size of final sequence = 177690

Trained on text data/shakespeare with # tokens = 177690
Training time = 0.101 seconds
Exception in thread "main" java.lang.RuntimeException: 
zero size follows for [conditions,, but, unseal'd-]
        at BaseMarkovModel.randomNextString(BaseMarkovModel.java:190)
        at BaseMarkovModel.generate(BaseMarkovModel.java:212)
        at Chat201Driver.main(Chat201Driver.java:43)
```

This throws an exception because the `Chat201Driver` code uses a `BaseMarkovModel` rather than the version 
you'll write for P1.1: `SimpleMarkovModel`. 
After you write that class and modify the code in `main` to use a `SimpleMarkovModel` you should see randomly-generated text.
You're encouraged
to explore `Chat201Driver` more closely to understand what it is doing. You will find details about the `Chat201Driver` class in the class 
and its comments as well  as in the [details document](docs/details.md) that is part of this project. 
If the method `getModel` returns
either a `SimpleMarkovModel` or a `HashMarkovModel`, the output should be the same except for the timings.

```
size of final sequence = 177674

Trained on text data/shakespeare with # tokens = 177674
Training time = 0.106 seconds
Generated N=1000 random words with order 2 Markov Model
Generating time = 1.522 seconds
----------------------------------
will pay thy poverty and not a penny. Rom. Go to! You'll 
not endure it. You forget yourself To hedge 
----------------------------------
----------------------------------
or shall be shortly, single I'll resolve you, Which 
to you at meals, comfort your bed, And this fair 
----------------------------------
```


## JUnit Tests for both 1.1 and 1.2

You'll develop code in one class for this first part: `SimpleMarkovModel`. This has a corresponding JUnit testing program. See
the [details document](docs/details.md) for information on how to run `JUnit` tests in VSCode and how to read the test results. 
You should *absolutely* make sure your code passes these tests when writing each method, e.g., before coding the next
method: For example, after coding `getFollows` you should use JUnit to
run the tests in `MarkovGenerateTest`. See the [details document](docs/details.md) for *how to run these tests.*

## Coding Part 1: Developing the SimpleMarkovModel Class

In this part you will develop a Markov model for generating random text by extending `BaseMarkovModel` to the class
`SimpleMarkovModel`, and then using that in `Chat201Driver` to test the methods you have written.
See the `BaseMarkovModel` class and the [details document](docs/details.md) for more complete information. The class
`SimpleMarkovModel` has been started, you must complete it.

### Running and Testing SimpleMarkovModel

The `Chat201Driver` sets a `RANDOM_SEED` to initialize the random number generator. You are welcome to change that value to experiment 
and play around with different random generations of text, but you should be sure to set it to 1234 for testing/submitting. 
Note that *if you use the same value for `RANDOM_SEED` you should get the same random text as other students.
If not, something is likely wrong with your implementation.

You can also test your `SimpleMarkovModel` class with the `MarkovGenerateTest` [JUnit tests](#junit-tests). 
Don't forget you may need to edit the code in method `getModel` to return a `SimpleMarkovModel` implementation when running your tests.

Once you are confident that your `SimpleMarkovModel` code is correct, you are ready to submit to Gradescope 
for P1.1 and then move on to P1.2 and the analysis questions.

## Coding Part 2: Developing the HashMarkovModel Class

The `SimpleMarkovModel` class scans the entire training text each time a random word is generated. For the `HashMarkovModel` class you
must scan the training text *once*, and store information in a `HashMap` so that random words can be
generated in `O(1)` time rather than `O(T)` time where `T` is the number of tokens/words in the training text. See
the [details document](docs/details.md) for full details on the `HashMap` instance variable and the methods you
will need to `@Override` in this new, more efficient class. 

## Analysis Questions

Answer the following questions in your analysis. You'll submit your analysis as a separate PDF as a 
separate assignment to Gradescope. Answering these questions will require you to run the driver code to 
generate timing data and to reason about the algorithms and data structures you have implemented. 
We will include a template file for submitting your answers.

### Working Together for Analysis

You're *stronlgy encouraged* to work with others in 201 in completing the analysis section for this project. 
In future projects you'll work on an entire project in pairs, and submit once for the pair. For this project, however, 
each person should submit independently. If you actively work with one or more people in 201, *please make sure* you list each other 
in the analysis document you turn in. 

*For your analysis repsonses* [please use this template](https://coursework.cs.duke.edu/201fall25/p1-markov-fall2025/-/blob/main/docs/p1-markov-analysis.docx?ref_type=heads).

### Big-O/O-notation for analysis questions

For the analysis, let $`N`$ denote the length/number of words of the random text being generated. 
Let $`T`$ denote the number of words of the training text. Assume that *all words are of at most a constant length* 
(say, no more than 35 characters). To help in using the folders nested in the `data` folder, here 
are is some information about total number of words (and the number of files in each folder):

|file    |# files| # total words|
|--------|---------------|--------------|
|shakespeare | 8 | 177,642 |
|melvill e| 4 | 419,777|
|cbronte | 3 | 466,027|
|twain | 5 | 516,074|
|onefile | 1 | 28,196|
|twofiles | 2 | 56,392 |
| hesse | 3 | 149,532|

*Note*: these are the _actual_ number of words in each folder. The markov model sequences will report *more* than these, depending on the order of the
model, because each file has `<START><START> ... <START>` and corresponding `<END>` tags inserted. The differences don't matter when
analyzing using O-notation.

### Question 1 (4 points)

The code prints the size of the training text and how long it takes to train and to generate random text using the simple markov model
embodied in `SimpleMarkovModel`. Copy/paste the sizes and times using different training texts

What is the asymptotic (big O) runtime complexity of each of the methods: `trainDirectory()` `generate()` for the `SimpleMarkovModel`
 implementation in terms of $`N`$ and $`T`$? State your answers, and justify them in *both* of the following ways.

- *Theory*. Explain why you expect `trainDirectory()` in `SimpleMarkovModel` and `generate()` from `SimpleMarkovModel` 
as the model to have the stated runtime complexity by referencing the algorithms/data structures/code used. 
Explain the complexity of each operation/method, accounting for any looping, in the code. 
You may assume that `nextInt` is a constant time operation to generate a random number and that reading the training
files takes `O(T)` time.

- *Experiment*. Run the main method of `Chat201Driver` with *at least* 3 different data folders 
of varying sizes $`T`$ (it is fine to use `alice.txt` for one of them). For each, run 
the main method with *at least* 3 different values of `TEXT_SIZE` (which corresponds to $`N`$). 
So you should have a total of at least 9 data points; use these to fill out a table like the one shown below.
 *Explain how your empirical data does or does not conform to your expectations for the runtime 
 complexity of `generate()`.*

| Data file    | $`T`$    | $`N`$    | Training Time (s)    | Generating time (s)    |
| ------------ | -------- | -------- | -------------------- | ---------------------- |
| twain        | 516,074  | 100      | ...                  | ...                    |
| ...          | ....     | ...      | ...                  | ...                    |

*Suggestions*: You will likely get the clearest data if you include very different values for $`T`$ and $`N`$, 
and preferably larger values -- see the table of sizes above to help you choose text files you'll use. 
You can set $`N`$ directly to much larger values by changing `TEXT_SIZE`, for example to 1,000 or 10,000. 
When doing so, you can set `PRINT_MODE` to `false` in `Chat201Driver` to avoid having a large amount of text printed to your 
terminal. Note that `SimpleMarkovModel` is not necessarily an efficient implementation, 
so it may take a long time to run with large $`T`$ and $`N`$. You do not need to run anything for multiple minutes 
just for data collection for this assignment.  

### Question 2 (4 points)

Same as Question 1, but for `HashMarkovModel` instead of `SimpleMarkovModel`: 
What is the asymptotic (big O) runtime complexity of the methods: `trainDirectory()` 
and for the `generate` method when  a `HashMarkovModel` is used in terms of $`N`$ and $`T`$? 
State your answers, and justify them in *theory and experiment* exactly as you did for Question 1. 

You can use the same training texts and values for $`N`$ as you chose in question 1, w
ith the same suggestions mentioned there. Note that, implemented correctly, `HashMarkovModel` 
should be noticably more efficient at generating random text than `SimpleMarkovModel`, and this should be evident in your analysis.

### Question 3 (4 points)

Markov models like the one you implemented in this project are one example of a larger research area in artificial intelligence (AI) 
and machine learning (ML) called *generative models* for *natural language processing*. Currently, one of the state-of-the-art models is 
 *GPT*, created by [OpenAI](https://openai.com/about/). OpenAI states that their 
 "mission is to ensure that artificial general intelligence---AI systems that are generally smarter than humans—benefits all of humanity."
 Note that in 2024 the model wording was different including: 
 "highly autonomous systems that outperform humans at most economically valuable work—benefits all of humanity." 

GPT is not, however, open-source, meaning that the underlying source code of the model is not freely available and the model is only 
accessible via API calls. Read this [short article from Nature about DeepSeek, an open-source LLM](docs/deepseek-nature.pdf). 

Answer both of the following related questions:
- What do you think of OpenAI's stated mission? In particular, do you think that "highly autonomous systems that outperform humans at most economically valuable work" can benefit all of humanity? Why or why not?
- What is the main message from the Nature article about DeepSeek and other models?
- What questions/areas (you must have at least one) would you like to know more about? 

There is no right or wrong answer to these questions; we are looking for one or two paragraphs of thoughtful reflection.



## Submitting and Grading
You will submit the assignment on Gradescope. You can access Gradescope through the tab on Canvas. 
Please take note that changes/commits on GitLab are NOT automatically synced to Gradescope. 
You are welcome to submit as many times as you like, only the most recent submission will count for a grade. You'll submit once
for P1.1, once for P1.2, and once for analysis.

Don't forget to upload a PDF for the analysis part of this assignment and mark where you answer each question. This is a separate submission in Gradescope.


### Grading

| Section.  | points |
|-----------|--------|
| SimpleMarkovModel  | 6   |
| HashMarkovModel | 8 |
| Analysis  | 12  |
