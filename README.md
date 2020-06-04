# Preferential Deletion Model with Changes in Existing Connections (PDCModel)

## Overview
- **Year:** 2020
- **Language(s):** Java
- **Discipline(s):** Algorithm Design and Analysis, Graph Theory, Network Theory
- **Keywords:** `degree-distribution`, `dynamic-random-graphs`, `dynamic-random-networks`, `graphs`, `graph-algorithms`, `graph-generation`, `graph-theory`, `networks`, `network-theory`, `preferential-deletion`, `preferential-node-deletion`, `web-like-networks`

## Description
The *Preferential Deletion Model with Changes in Existing Connections (PDCModel)* is an extension of the discrete-time dynamic random graph generation process described by Narsingh Deo and Aurel Cami in their 2005 study *Preferential Deletion in Dynamic Models of Web-like Networks*.

This project is the second of a two-part research endeavor into Deo and Cami's paper. The first part simply implements their original model and replicates their findings, while this second part extends the implementation to account for changes in existing connections throughout the graph generation.

The results of this study were the following:
1. The source code for the PDCModel, developed in Java.
2. [*Accounting for Changes in Existing Connections in the Preferential Deletion Model for Web-Like Networks*](/accounting-for-changes-in-existing-connections-in-the-preferential-deletion-model-for-web-like-networks.pdf), a research-style paper discussing the findings from the study using the new model as compared to Deo and Cami's original model. Please note that this is not a published paper. It was simply done in the style of a research paper for the COT 5405: Design and Analysis of Algorithms graduate course at the University of Central Florida.

## Build Instructions
1. Download the `PDCModel` repository.
2. Open `Run.java`. Use this file to modify the values of TIME, TRIALS, and P. These values are defined as follows:
* TIME: the discrete value of time the model will run for.
* TRIALS: the number of trials to run.
* P: the value of p for the model. (There may be various values for p to be used for comparisons. For a detailed explanation of p, see the paper.)
3. Compile `Run.java` using the following command: `javac Run.java`
4. Run the file using the following command: `java Run`
