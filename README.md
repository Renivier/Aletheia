<h1 align="center">
  <img src="https://img.shields.io/badge/Aletheia-%CE%B1%CE%BB%CE%AE%CE%B8%CE%B5%CE%B9%CE%B1-8A2BE2?style=for-the-badge&logo=java&logoColor=white" height="48" alt="Aletheia"/><br/>
  Aletheia
</h1>

<p align="center">
  <em>A Java decompiler focused on reconstructing what compilation tried to hide.</em>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Status-Early_Development-ff69b4?style=flat-square" />
  <img src="https://img.shields.io/badge/Language-Java-orange?style=flat-square&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Target-JVM_Bytecode-blue?style=flat-square" />
  <img src="https://img.shields.io/badge/Focus-Program_Reconstruction-brightgreen?style=flat-square" />
</p>

<p align="center">
  <img src="./aletheia.jpg" alt="Aletheia - Goddess of Code" width="55%"/>
</p>

> Aletheia is an experimental Java bytecode decompiler designed to go beyond conventional source reconstruction.

Most decompilers attempt to produce source code that is semantically equivalent to the compiled program. Aletheia aims to take this further: where information has been lost during compilation, it will attempt to **reconstruct** that information from the evidence that remains.

This may include information such as variable names, semantic roles, and other aspects of the original program's structure.

The distinction between **guessing** and **reconstruction** is fundamental to the project.

Aletheia should not simply invent a variable name that "looks right". It should derive candidate information from observable evidence within the program and determine how strongly that evidence supports the reconstruction.

---

## 🏛️ Why "Aletheia"?


*Truth. Disclosure. Unconcealedness. The revealing of what was hidden.*


**Aletheia** (ἀλήθεια) is an Ancient Greek concept commonly translated as *truth*, but also associated with **unconcealedness, disclosure, revealing, and unhiddenness**.


In Greek mythology, Aletheia was personified as the embodiment of truth.

The name is deliberate.

Compilation removes information.

Obfuscation can remove even more.

Aletheia attempts to uncover what can still be recovered.

The project is therefore not named merely after "truth", but after the idea of **revealing what has been concealed**.

---

## 🔮 Philosophy

Aletheia follows three fundamental principles.

### 1. Reconstruct, Don't Guess

Compiled programs frequently contain enough indirect evidence to infer information that is no longer explicitly represented.

For example, an original variable name may be absent while its:

- Type
- Scope
- Data-flow
- Control-flow relationships
- Usage patterns
- Interactions with methods and fields
- Surrounding program structure

remain available.

Aletheia can use this evidence to construct a reconstruction.

This does **not** mean that the reconstruction is guaranteed to equal the original source.

The system should distinguish between:

> [!NOTE]
> **Known**: Directly represented or recoverable with certainty.

> [!TIP]
> **Reconstructed**: Strongly supported by available evidence.

> [!WARNING]
> **Plausible**: One of several reasonable interpretations.

> [!CAUTION]
> **Unknown**: Insufficient evidence for a meaningful reconstruction.

Aletheia should never present speculation as recovered fact.

---

### 2. Understand the Program Before Reconstructing It

Aletheia is not intended to feed raw bytecode into a model and ask it to produce Java.

The program must first be understood at increasingly higher levels:

```text
.class file
    ↓
Class-file structure
    ↓
Bytecode
    ↓
Instructions
    ↓
Control flow
    ↓
Data flow
    ↓
Program semantics
    ↓
Reconstruction
    ↓
Java source
```

Deterministic analysis should provide as much information as possible before statistical methods are introduced.

---

### 3. Reconstruction Should Improve the Result

The long-term goal is not simply to reproduce whatever information survived compilation.

Aletheia should eventually be capable of producing **useful and semantically appropriate representations** of programs.

For example, if an original identifier has been completely removed, Aletheia may be able to reconstruct a meaningful identifier based on how the variable behaves within the program.

The goal is not to claim that:

```java
int userIndex;
```

was definitely the original source.

The goal is to establish that `userIndex` is a well-supported semantic reconstruction.

---

## ⚡ How Aletheia Differs

Traditional decompilation generally focuses on reconstructing source code that behaves equivalently to the original compiled program.

Aletheia aims to go further.

Consider a compiled variable whose original name has been removed:

```text
Original source ──► Compiler ──► JVM bytecode ──► Debug info removed ──► Aletheia
```

A conventional decompiler may produce:

```java
// Generic fallback identifier
int var1;
```

Aletheia aims to determine whether the remaining evidence supports something more meaningful:

```java
// Reconstructed from access patterns & bounds checks
int userIndex;
```

The important question is not:

> "What name sounds reasonable?"

It is:

> "What name is best supported by the program's surviving evidence?"

---

## 🛠️ Current Development

Aletheia is currently being built from the JVM class-file level upward.

The initial implementation focuses on correctly understanding `.class` files before attempting sophisticated reconstruction.

### Current Foundations

- 🟢 Java implementation
- 🟢 Binary class-file parsing
- 🟢 `BinaryReader`
- 🟢 Unsigned JVM primitives (`u1`, `u2`, `u4`)
- 🟢 Class-file header parsing
- 🟢 JVM version parsing
- 🟢 Constant-pool parsing
- 🟢 Structured representation of class-file components

The parser begins with the JVM class-file structure:

```diff
+ Magic
+ Minor Version
+ Major Version
+ Constant Pool
! Access Flags
! This Class
! Super Class
! Interfaces
- Fields
- Methods
- Attributes
```

---

## 📐 Planned Architecture

The intended architecture is approximately:

```text
                    .class
                      │
                      ▼
               ┌─────────────┐
               │ BinaryReader│
               └──────┬──────┘
                      │
                      ▼
               ┌─────────────┐
               │ Class Parser│
               └──────┬──────┘
                      │
                      ▼
              ┌───────────────┐
              │   ClassFile   │
              │      IR       │
              └───────┬───────┘
                      │
             ┌────────┴────────┐
             ▼                 ▼
       Bytecode Parser    Metadata Parser
             │                 │
             └────────┬────────┘
                      ▼
              Program Analysis
                      │
          ┌───────────┴───────────┐
          ▼                       ▼
    Control-Flow Analysis    Data-Flow Analysis
          │                       │
          └───────────┬───────────┘
                      ▼
                Semantic Model
                      │
                      ▼
             Reconstruction Engine
                      │
                      ▼
                Java Generator
```

---

## 🤖 Machine Learning

Machine learning is planned as a potential component of Aletheia's reconstruction system.

It is **not** intended to replace the deterministic decompiler.

Aletheia should first extract structured evidence from the program and provide that information to a reconstruction model.

A future model could potentially receive information such as:

```yaml
Type: int
Scope: local
Reads: 23
Writes: 7
Incremented: true
Used_as_array_index: true
Compared_against: collection_length
Containing_construct: loop
Related_object: users
```

and produce candidate semantic identifiers such as:

```java
userIndex
```

The reconstruction system could then assign confidence to its results and compare multiple candidate interpretations.

A small, specialized language model may eventually be developed specifically for this purpose rather than relying on a general-purpose LLM.

The model should operate on structured program information rather than attempting to replace program analysis entirely.

---

## 🎯 Goals

### Short-Term

- [ ] Complete JVM class-file parser
- [ ] Complete constant-pool implementation
- [ ] Parse fields and methods
- [ ] Parse attributes
- [ ] Parse bytecode
- [ ] Implement JVM instruction representation
- [ ] Build a working basic decompiler

### Medium-Term

- [ ] Build control-flow analysis
- [ ] Build data-flow analysis
- [ ] Reconstruct high-level Java structures
- [ ] Improve expression reconstruction
- [ ] Reconstruct loops and conditionals
- [ ] Handle compiler-generated structures
- [ ] Improve variable scope reconstruction
- [ ] Produce readable Java source

### Long-Term

- [ ] Semantic identifier reconstruction
- [ ] Confidence-aware reconstruction
- [ ] ML-assisted reconstruction
- [ ] Specialized reconstruction model
- [ ] Reconstruction of additional lost program information
- [ ] Integration with the planned Java modloader

---

## 🛑 What Aletheia Is Not

Aletheia is not intended to be:

- A Java source formatter
- A wrapper around another decompiler
- An LLM that blindly converts bytecode into Java
- A system that presents guesses as recovered facts

Its central problem is **program reconstruction**.

---

## 📌 Project Status

<p align="center">
  <img src="./aletheia_chibi.jpg" alt="Aletheia Chibi" width="300"/>
</p>

> [!IMPORTANT]
> **Early Development**
> 
> Aletheia is currently in the low-level class-file parsing stage.
> 
> The project is intentionally being built from the foundations upward. Sophisticated reconstruction will come after the underlying JVM representation and program analysis are reliable.

---


## 📄 License

License information will be added once the project's licensing decision has been made.