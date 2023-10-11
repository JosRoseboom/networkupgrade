# Java magazine code challenge solution

This repo contains the solution and its corresponding code. 

## Short answer
The answer is 969813

## Long answer

The code challenge asks for a minimal spanning tree. There are known algorithms for that, like Kruskal and Prim. So let's be modern, and ask ChatGPT: "kruskal algorithm for minimal spanning tree implementation in Java 20". 

The result is ChatGPTSnippet.java next to this readme file in the root of the repo. With some refactoring, the algorithm part is done.

The other part is parsing the input. GraphReader reads the input file based on a Path (used for the real input file) or a string (to feed the example for Java Magazine to validate).

The copied input from Java Magazine indeed returns 1716. So far so good. The file input contains node names with multiple characters and coordinates over 1 digit. Let's create a second version similar to the Java Magazine example, but with some changes:
- A .. F are renamed to 3 character names
- all X are + 100
- all Y are + 100

If the regex is ok, the outcome should still be 1716. Indeed it returns 1716.

Last step is feeding the input file. After running this I can tell Info Support that they will have to spend 969813 to upgrade their network. The switches that are to be connected to achieve that are listed in solution.txt next to this readme file in the root of the repo.

The costs are sorted. If it turns out that Info Support is not able or willing to spend those 969813: how important are those guys connected to switch FYY? Are they really that important? You could save 14288 there :) 

