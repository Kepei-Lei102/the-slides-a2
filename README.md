# CS Slides — A2 2026-2027

The interactive decks, practice sheets and 🍦 challenges for **Cambridge 9618 A2
Computer Science**, as they are taught. This repo grows through the year: a
lesson's material appears here after that lesson has run.

**This is not everything that exists.** It is everything you have been taught.
If a unit you want is missing, it is because we have not reached it — ask.

## What is in here

| kind | file | how to use it |
|---|---|---|
| deck | `slides_<unit>_*.html` | **double-click it.** It opens in your browser. Arrow keys move; `↓` enters a rabbit hole; the Python cells run live, in the page |
| practice | `practice_<unit>_*.pdf` | print it or annotate it; answers come back in class |
| 🍦 challenge | `Challenge<unit>*.java`, `challenge_<unit>_*.py` | open in an editor, fill in the functions, run the file — the judge is at the bottom and marks itself |

The decks need an internet connection the first time you open one: they pull
Reveal.js and Pyodide from a CDN. After that your browser caches them.

## Getting it, and keeping it current

```bash
git clone --depth 1 https://github.com/Kepei-Lei102/the-slides-a2.git
```

Then, whenever a lesson has run:

```bash
git pull
```

Or just say to your AI assistant: *"the the-slides-a2 repo updated — pull the new
material and tell me what's new."*

## The other half

The decks teach the lesson; **The Vault** is the reference behind them — a few
hundred cards on Mathematics, Physics and Computer Science, written to be read
rather than presented, and designed for an AI to teach *from*.

```bash
git clone --depth 1 https://github.com/Kepei-Lei102/the-vault-a2.git
```

Full setup, including how to point an AI assistant at it: **`SETUP.md`** in this
repo.

---

*Released slides-2026-09-01 · unit in this edition: A01*
