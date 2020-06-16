# Preferential Deletion Model with Changes in Existing Connections (PDCModel)

## Overview
- **Year:** 2020
- **Language(s):** Java
- **Discipline(s):** Algorithm Design and Analysis, Graph Theory, Network Theory
- **Keywords:** `Degree-Distribution`, `Dynamic-Random-Graphs`, `Dynamic-Random-Networks`, `Graphs`, `Graph-Algorithms`, `Graph-Generation`, `Graph-Theory`, `Networks`, `Network-Theory`, `Preferential-Deletion`, `Preferential-Node-Deletion`, `Web-Like-Networks`

## Description
The *Preferential Deletion Model with Changing in Existing Connections (PDCModel)* is an extension of the discrete-time random graph generation process described by Narsingh Deo and Aurel Cami in their 2005 paper [*Preferential Deletion in Dynamic Models of Web-Like Networks*](https://www.sciencedirect.com/science/article/abs/pii/S0020019006003632).

Based on Deo and Cami's original model, the new *PDCModel* was developed to more accurately describe the behavior of social circles using graph theory (representing people as nodes, and connections as edges) by accounting for changes in connections for every unit of time throughout the random graph generation process. The model was then used in a study to analyze this behavior, resulting in the following:

1. [*Accounting for Changes in Existing Connections in the Preferential Deletion Model for Web-Like Networks*](https://github.com/csbanon/pdc-model/blob/master/paper/pdc-model-paper.pdf), a research-style paper discussing the findings from the study using the new model as compared to Deo and Cami's original model. Please note that this is not a published paper.
2. The source code for the new *PDCModel*, developed in Java.

This project is the second of a two-part research study conducted in the *COT 5405: Design and Analysis of Algorithms* graduate course at the University of Central Florida. The first part presents an implementation of Deo and Cami's original model and replicates their findings, while this second part extends the implementation to account for changes in existing connections throughout the random graph generation process.

## Build Instructions
1. Download the `PDCModel` repository.
2. Open `Run.java`. Use this file to modify the values of TIME, TRIALS, and P. These values are defined as follows:
* TIME: the discrete value of time the model will run for.
* TRIALS: the number of trials to run.
* P: the value of p for the model. (There may be various values for p to be used for comparisons. For a detailed explanation of p, see the paper.)
3. Compile `Run.java` using the following command: `javac Run.java`
4. Run the file using the following command: `java Run`
