# Overview

Starting a project to learn Java I knew I needed to make something that required me to use
and learn objects since Java is a Object Oriented Language. I chose to create a financial
tracker, but specifically I wanted it to have the capability of storing events the user
created. Using these stored events I wanted the user to be able to choose events to track
so that when the event occured again it would give a snapshot summary of all their
financial events within that time period. Each event was an individual object which I
attached a unique ID to so that I could connect these events together to create boundaries
for the time periods.

I always thought that financial trackers were useful, but I always wanted one
that would allow me to see my financial records within certain time periods. Since
my last paycheck how have my finances changed? How about since my last rent payment?
Having wanted to write this project for a while and also wanting to learn more about
Java I figured this was a perfect opportunity to use Java to create this software.

{Provide a link to your YouTube demonstration. It should be a 4-5 minute demo of the software running and a walkthrough of the code. Focus should be on sharing what you learned about the language syntax.}

[Software Demo Video](https://www.loom.com/share/aae0141dc3b24327a37197cbd10cff39)
[Software Demo Video PT 2](https://www.loom.com/share/596d1803eaf840b7bdde9c2efa3f4136)
[Software Demo Video PT 3](https://www.loom.com/share/a7749ba710bc40b7b9464d9e7001aec2)

# Development Environment

My IDE of choice for this project was Visual Studio Code.
My primary source of help was a white board.

I used Java along with the Java extensions within Visual Studio Code published
by Microsoft. I found Java libraries to be extensively helpful and as such I
used many of them within my project to attempt a base level of validation. These
libraries include:

java.util.ArrayList;
java.util.HashMap;
static java.lang.System.out;
java.time.LocalDate;
java.io.File;
java.io.FileNotFoundException;
java.io.FileWriter;
java.io.IOException;
java.util.Scanner;

# Useful Websites

When learning Java and its unique characteristics, abilities and syntax I
used a variety of sources including: 

- [Java (programming language)](https://en.wikipedia.org/wiki/Java_(programming_language))
- [W3 Schools Java Tutorial](https://www.w3schools.com/java/default.asp)
- [Java Platform, Standard Edition Documentation](https://docs.oracle.com/en/java/javase/index.html)
- [BeginnersBook](https://beginnersbook.com/java-collections-tutorials/)

# Future Work

This project is ultimately incomplete! There are several ways that a user
could break this software resulting in a crash, or data loss. Some of the 
improvements I would like to make in the future:

- Improved validation to ensure that user inputs match what kind of data my
    software expects and can use.

- Right now my software isn't very efficicent! All of the persistent memory
    comes from one single CSV file that can get very long very fast. I would
    like to learn a bit more abot persistent memory, types, methods and
    update my project in the future.

- Who knew that terminal menus aren't very user friendly? In the future I 
    want to remove the terminal menu and create a proper UI that users can
    have an easier time using and navigating.