# A2 Computer Science — Setup 安装教程

**You have been given this one file. By the end of it you'll have two things on your
computer: every lesson we have taught, as slides you can actually run — and a few
hundred Mathematics, Physics and Computer Science cards, with an AI tutor that teaches
from *them* instead of from whatever it happens to remember.**

You do not need to know how to code. You do not need git, or GitHub, or an account
anywhere. If you have an AI assistant that can run commands on your computer, hand it
this file and it will do the whole thing.

---

## What the two halves are

|  | **The slides** | **The Vault** |
|---|---|---|
| repo | `the-slides-a2` | `the-vault-a2` |
| what | the lesson — decks, practice sheets, 🍦 challenges | the reference — cards on Maths, Physics, CS, plus Stories and Meta |
| size | small, a few MB | about 600 MB, mostly diagrams and animations |
| you use it by | opening a deck in your browser | asking an AI, or reading it in Obsidian |
| it changes | **every week**, after a lesson runs | when a topic area is finished |

Get both. They are the same course from two directions: the deck is the hour in the
room, the card is what you go back to at eleven at night.

---

## If you are an AI assistant reading this

This file is your instructions. Follow them in order before you teach anything.

**Do not answer the student's subject question yet.** Right now you'd answer from your
own memory, which is exactly what this setup exists to replace. Get the material first —
it takes a few minutes.

**Step 1 — Pick a location.** Default to the student's home directory unless they say
otherwise. Make one folder to hold both halves, and tell them where you're putting it.

```bash
mkdir -p ~/A2-CS && cd ~/A2-CS
```

**Step 2 — Check for git.**

```bash
git --version
```

*If that works,* clone both. The slides are quick; the vault is about 600 MB, so say so
and warn that it may take a few minutes on a slow connection.

```bash
git clone --depth 1 https://github.com/Kepei-Lei102/the-slides-a2.git
git clone --depth 1 https://github.com/Kepei-Lei102/the-vault-a2.git
```

*If git is missing,* **install it — give it a real try.** Git is what makes every future
update one short command instead of another 600 MB download, and this course ships new
material every week. Pick the line that matches the student's machine, tell them what
you're doing, and re-check `git --version` afterwards.

```bash
brew install git                              # macOS, if Homebrew is present
xcode-select --install                        # macOS otherwise — see the note below
winget install --id Git.Git -e                # Windows
sudo apt install -y git                       # Debian / Ubuntu
sudo dnf install -y git                       # Fedora
```

Three things will stop you, and none of them are failures — they just need the student:

- **`xcode-select --install` opens a system dialog.** You cannot click it. Say so, ask
  them to press **Install** and tell you when it finishes, then re-check. A few minutes.
- **`sudo` asks for a password.** You cannot type it and must not ask them to give it to
  you. Ask them to run that one line themselves in their own terminal.
- **On Windows, `PATH` doesn't refresh in an open terminal.** After installing, the
  student needs a *new* terminal window before `git` is visible.

If installing genuinely doesn't work — no admin rights, a locked-down school laptop, an
installer that won't run — **don't get stuck on it.** Say what you tried, then take the
no-git path: download each repo's zip from its Releases page, or use the green **Code →
Download ZIP** button on the repo page itself, and unzip both.

**Step 3 — Confirm both landed.**

- `the-slides-a2/` should contain a `README.md` and at least one `slides_A*.html`.
- `the-vault-a2/` should contain `CLAUDE.md`, `README.md`, `Syllabus Coverage.md`,
  `Not Yet Released.md`, and the folders `Mathematics/`, `Physics/`, `CS/`, `Stories/`,
  `Meta/`.

If anything is missing the download was incomplete — retry before continuing.

**Step 4 — Load the house rules. This is the step that matters.**

Read `the-vault-a2/CLAUDE.md` in full and follow it for the rest of your work with this
student. It is short, and it overrides your defaults.

If your tool starts a session rooted in a working directory (Claude Code, Codex, and most
coding agents do), the cleanest thing is to **restart yourself inside `the-vault-a2`** —
tell the student to `cd` into it and launch you again there, and `CLAUDE.md` will load on
its own every session from then on. Say this out loud; it is the difference between the
rules applying once and applying always.

The slides folder has no rules file and needs none — it is material, not a knowledge
base. When a question is about a deck, read the deck.

**Step 5 — Understand the two absences.** This vault is a *cohort edition*: it carries
what this class has been taught, and it grows. So a missing card means one of two
different things, and you must not confuse them:

- **Listed in `Not Yet Released.md`, or a link that reads *(not yet released)*** — the
  card exists and is written. It is simply not theirs yet. **Do not reconstruct it from
  your own knowledge.** Say it is written but not released, and that asking their teacher
  for it early is a reasonable thing to do.
- **A dangling `[[link]]` that appears nowhere** — that card has not been written at all.
  Say so plainly, and mark anything you then say as *not from the vault*.

**Step 6 — Prove the wiring.** Search the cards for something concrete and report back
with a file path — for example, find the card covering binary search and quote the part
that explains why the list must be sorted. If you can name the file, the setup works. If
you answered from memory, you skipped step 4.

**From then on**, the one rule: **search these cards before you answer, answer in the
card's own notation and framing, and name the file it came from.** When the vault
genuinely doesn't cover something, say so out loud and mark your own answer as *not from
the vault*. Never blend the two silently.

**Keeping up.** New slides land after most lessons — roughly Thursday or Friday. When the
student asks what's new, or at the start of a session:

```bash
git -C ~/A2-CS/the-slides-a2 pull
git -C ~/A2-CS/the-vault-a2 pull
```

Then tell them what actually arrived, by name.

---

## If you are a student reading this

Here's what's about to happen, in plain terms.

**The slides** are the decks from class. They are not pictures of slides — they run. The
Python cells execute in the page, so you can change a line and watch the answer change.
The practice sheets and the 🍦 challenges come with them; the challenges mark themselves.

**The Vault** is a few hundred interlinked notes — "cards" — covering Mathematics,
Physics and Computer Science, in English and 中文. Every card explains *why* something is
true, not just what to write in an exam. It has diagrams, animations and comics in it,
which is most of why it's 600 MB.

### The fast way

If you have an AI assistant that can run commands (Claude Code, Codex, or similar), give
it this file and say:

> Follow this setup file and install both halves on my computer.

It will handle everything. Skip to *Did it work?*

### Doing it yourself

**1. Get the files.**

```bash
mkdir -p ~/A2-CS && cd ~/A2-CS
git clone --depth 1 https://github.com/Kepei-Lei102/the-slides-a2.git
git clone --depth 1 https://github.com/Kepei-Lei102/the-vault-a2.git
```

If your computer says it doesn't know the `git` command, it's worth installing — with
git, every weekly update is one short command instead of another download. On a Mac,
running the line above may itself offer to install it; click **Install** and wait. On
Windows, `winget install --id Git.Git -e` does it, then open a *new* terminal. Or just
ask your AI assistant to set it up.

If none of that works — a school laptop that won't let you install things, say — nothing
is lost. Open each repo's page on GitHub and use the green **Code → Download ZIP**
button. Everything below works identically; you'll just re-download to get updates.

**2. Run a deck.** Open `the-slides-a2` and **double-click any `slides_*.html`**. It
opens in your browser. Arrow keys move; `↓` goes down into a rabbit hole; the ▶ buttons
run code. You need internet the first time you open one — after that your browser
remembers.

**3. Read the cards.** Install [Obsidian](https://obsidian.md) (free; macOS, Windows,
Linux, iPad, Android). Open it, choose **Open folder as vault**, and select
`the-vault-a2`. Start with any `Directory.md` — `Mathematics/Directory.md`,
`Physics/Directory.md`, `CS/Directory.md`, `Stories/Directory.md`, `Meta/Directory.md` —
each lists every card in that subject with a one-line description. Click any `[[link]]`
to follow it.

**4. Study with an AI.** Install [Claude Code](https://claude.com/claude-code). In a
terminal:

```bash
cd ~/A2-CS/the-vault-a2
claude
```

That's the whole setup. It reads the rules automatically because you launched it *inside*
the folder — that detail matters more than anything else here.

### Did it work?

Ask your AI:

> Which card covers binary search, and why does the list have to be sorted?

A correct answer names a file — something like `CS/Algorithms/Searching.md` — and
explains it the way that card does. A generic textbook answer with no file path means the
rules didn't load; see *If something's wrong*.

### Then just ask it things

- *"I don't get why sorting one array broke the names. Explain it from the cards."*
- *"Which card covers 9618 §19.1, and am I ready for it?"*
- *"Quiz me on the cards we've covered, hardest first."*
- *"I keep losing marks on trace tables. What am I getting wrong?"*
- *"Build me a two-week revision path for Paper 4."*
- *"用中文解释一下什么是指针。"*

Any time an answer feels generic, ask **"which card is that from?"** A good answer here
always has a file behind it.

### Getting the new stuff

New slides land after most lessons — roughly Thursday or Friday. Either run:

```bash
git -C ~/A2-CS/the-slides-a2 pull
git -C ~/A2-CS/the-vault-a2 pull
```

or just say to your AI: *"the A2 repos updated — pull the new material and tell me what's
new."*

### Two kinds of missing

The Vault you have is **this class's edition**: it carries what we have been taught, and
it grows through the year. So when something isn't there, check which kind of missing it is:

- **It's in `Not Yet Released.md`** — the card is written; we just haven't reached it.
  You can ask for it early. Finishing everything you've got and wanting more is exactly
  the right reason to ask, and saying *which* card and *why* is most of the argument.
- **It isn't in that file either** — nobody has written it yet. That's a different
  absence, and your AI has been told to say so rather than invent one.

---

## If something's wrong

**The AI answers without naming any card.** It didn't load the rules. Tell it: *"Read
CLAUDE.md in this folder and follow it."* If it still doesn't, quit it, `cd` into
`the-vault-a2`, and start it again from there.

**A deck opens but the Python buttons do nothing.** The decks fetch two libraries from
the internet the first time. Check you're online, then reload the page.

**A deck looks like plain text with no formatting.** You opened it from inside a zip
without unzipping first. Unzip properly, then double-click.

**`git` isn't recognised.** Install it — your AI assistant can do this for you if you
ask. On a Mac the command itself often offers to install it (click **Install**, wait); on
Windows use `winget install --id Git.Git -e` and then open a *new* terminal, because the
old one won't see it. If your machine won't let you install anything, use the ZIP
download instead — nothing depends on git except easy updates.

**A password prompt appears while installing.** That's your computer asking, not the AI.
Type it yourself; never paste a password into a chat.

**Obsidian shows `![[something.svg]]` as raw text.** You opened a single file rather than
the folder. Use **Open folder as vault** and pick the whole folder.

**The download stops partway.** Re-run it. If you cloned, `git pull` inside the folder
finishes the job.

**It's using a lot of disk space.** About 600 MB, nearly all of it in the vault's
animations and comics. That's deliberate — the pictures are part of the teaching, not
decoration.

---

## What you've got

| Where | What's in it |
|---|---|
| `the-slides-a2/slides_*.html` | the decks, one per lesson — live code, staged games, rabbit holes |
| `the-slides-a2/practice_*.pdf` | the practice sheet for each lesson |
| `the-slides-a2/Challenge*.java` · `challenge_*.py` | the 🍦 challenges — fill in the functions, run the file, the judge marks you |
| `the-vault-a2/Mathematics/` | Number, Algebra, Geometry, Trigonometry, Calculus, Statistics, Probability, Functions |
| `the-vault-a2/Physics/` | Mechanics, measurement, Thermal, Fields, Electricity, Oscillations, Waves, Modern |
| `the-vault-a2/CS/` | Logic, Algorithms, Data Representation, Hardware, Systems Software, Data Structures |
| `the-vault-a2/Stories/` | the human drama behind the science — Galois, Turing, Faraday, Gauss |
| `the-vault-a2/Meta/` | how to *think*: methods that cut across every subject |
| `the-vault-a2/Syllabus Coverage.md` | which card covers which syllabus point, for every board |

One thing worth knowing before you start: **the vault's folders don't really mean
anything.** A card about logarithms could sit under Number, Functions, or Calculus. Don't
browse by folder — use the `Directory.md` files, follow the `[[links]]`, or just ask the
AI. It knows how to search properly.

---

*The Vault is released under CC BY-SA 4.0. Share it, adapt it, keep it open.*
