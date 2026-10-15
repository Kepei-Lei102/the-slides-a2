"""
🍦 THE ICE-CREAM CHALLENGE — A3b · Where the Promises Are Kept
==============================================================

Paper 4 asks for the same job BOTH WAYS. Nov 2023 gave you a loop and asked
for the recursion; June 2024 gave you the recursion and asked for the loop.
So: write everything twice.

    count_vowels   how many of a e i o u are in a string
    sum_digits     add up the digits of a whole number:  4071 -> 12
    reverse_string "stack" -> "kcats"

Each one gets two functions — one with a loop, one that calls itself — and
they must agree with each other on a thousand random inputs.

HOW TO USE THIS FILE
    1. Fill in the six functions below.
    2. Run the file. The judge runs automatically at the bottom.
    3. Keep going until every row is a ✓.

THE TEST YOU CANNOT FAKE — and this time it runs in BOTH directions
    A3a's judge read this file to check that a recursive function really
    calls itself. That test still runs here. But it now has a twin: the
    ITERATIVE version must NOT call itself, and must actually contain a loop.

    Which means neither version can be the other one wearing a different
    name. Writing the recursion twice fails. Writing the loop twice fails.
    Copying is the one thing the judge is watching for, because copying is
    the one thing that would skip the exercise entirely.

BANNED — the machine may not do your homework
    str.count   reversed()   [::-1]   sum(...) over a string   any library
    that already knows the answer.

────────────────────────────────────────────────────────────────────────────
"""

import random
import re


# ═══ 1 · COUNT THE VOWELS ═══════════════════════════════════════════════

def count_vowels_iterative(text):
    """Count a, e, i, o, u in `text` with a loop.

    One accumulator, one pass. This is the version Nov 2023 Paper 41 called
    IterativeVowels, and it is worth 5 marks.
    """
    # YOUR CODE HERE  (replace this placeholder -- -999 is not a real answer)
    return -999


def count_vowels_recursive(text):
    """The same answer, by calling yourself.

    Base case: an empty string has no vowels. General case: look at the
    FIRST character, then ask yourself about the rest. This is the version
    worth 6 marks, and its first published mark point is simply
    "a recursive call".
    """
    # YOUR CODE HERE
    return -999


# ═══ 2 · ADD UP THE DIGITS ══════════════════════════════════════════════

def sum_digits_iterative(n):
    """Add the digits of a non-negative whole number. 4071 -> 4+0+7+1 = 12.

    Hint that is worth more than the answer: n % 10 is the last digit, and
    n // 10 is everything else. That pair is 'first + rest' for numbers.
    """
    # YOUR CODE HERE
    return -999


def sum_digits_recursive(n):
    """The same, calling yourself.

    Base case: a single digit is its own sum — so which n needs no call at
    all? General case: last digit, plus the sum of what is left.
    """
    # YOUR CODE HERE
    return -999


# ═══ 3 · REVERSE A STRING ═══════════════════════════════════════════════

def reverse_string_iterative(text):
    """"stack" -> "kcats", with a loop. Build the answer one character at a
    time. (No slicing tricks — [::-1] is banned, and the judge looks.)
    """
    # YOUR CODE HERE
    return "-999"


def reverse_string_recursive(text):
    """The same, calling yourself.

    Base case: the empty string reversed is the empty string. General case:
    reverse the REST, then put the first character on the END.

    Notice what this one costs. Every call holds its own character while it
    waits — which is exactly the stack frame you watched in the lesson.
    """
    # YOUR CODE HERE
    return "-999"


# ═══ 🍨 THE SECOND SCOOP — be the compiler ══════════════════════════════
#
# In A2c you built a binary tree. In A3a you walked it recursively. The
# compiler did the remembering for you, on a stack you never saw.
#
# Now do its job. Walk the tree IN ORDER — smallest value first — with NO
# recursion at all, using a stack of your own.
#
# The tree is the exam's own storage: ArrayNodes[i] is [left, data, right]
# and -1 means "no child". The judge builds the trees; you only walk them.
#
# The shape, if you want it:  go left as far as you can, pushing each node
# you pass. When you cannot go left, pop one, output it, and go right.

ArrayNodes = []


def in_order_iterative(root):
    """Return a list of the tree's values in order, using your own stack.

    No recursion. The judge reads this function's source and will say so.
    """
    stack = []          # your stack -- a Python list, push with append, pop with pop
    output = []
    # YOUR CODE HERE
    return output


# ════════════════════════════════════════════════════════════════════════
#  THE JUDGE — everything below this line is the marking, not the homework
# ════════════════════════════════════════════════════════════════════════

VOWELS = "aeiou"
PAIRS = [
    ("count_vowels", count_vowels_iterative, count_vowels_recursive),
    ("sum_digits", sum_digits_iterative, sum_digits_recursive),
    ("reverse_string", reverse_string_iterative, reverse_string_recursive),
]


def _source_of(name):
    """The body of one function, read out of this very file."""
    try:
        src = open(__file__, encoding='utf-8').read()
    except Exception:
        return None
    m = re.search(r'^def %s\(.*?\):\n(.*?)(?=^\S|\Z)' % name, src, re.S | re.M)
    if not m:
        return None
    body = re.sub(r'""".*?"""', '', m.group(1), flags=re.S)     # drop the docstring
    return re.sub(r'#.*', '', body)                              # drop comments


def _unwritten(name):
    body = _source_of(name)
    return body is None or 'return -999' in body or 'return "-999"' in body


# ---- the reference answers, computed by the judge, never shown -------------

def _ref_vowels(text):
    total = 0
    for ch in text:
        if ch in VOWELS:
            total += 1
    return total


def _ref_digits(n):
    total = 0
    while n > 9:
        total += n % 10
        n //= 10
    return total + n


def _ref_reverse(text):
    out = ""
    for ch in text:
        out = ch + out
    return out


REFS = {"count_vowels": _ref_vowels, "sum_digits": _ref_digits, "reverse_string": _ref_reverse}


def _random_input(kind, rng):
    if kind == "sum_digits":
        return rng.randint(0, 10 ** rng.randint(1, 9))
    letters = "abcdefghijklmnopqrstuvwxyz"
    return "".join(rng.choice(letters) for _ in range(rng.randint(0, 14)))


# ---- test 1: each version must BE what it says it is ----------------------

def _t_shapes():
    """The recursive halves must call themselves and hold no loop; the
    iterative halves must loop and never call themselves. Neither can be a
    copy of the other."""
    problems, silent = [], 0
    for base, _, _ in PAIRS:
        it, rec = base + "_iterative", base + "_recursive"
        bi, br = _source_of(it), _source_of(rec)
        if bi is None or br is None:
            return None, "could not read this file to check the source"
        if _unwritten(it) and _unwritten(rec):
            silent += 1
            continue
        if not re.search(r'\b%s\s*\(' % rec, br):
            problems.append("%s never calls itself, so whatever it does, it is not recursion" % rec)
        if re.search(r'\b(for|while)\b', br):
            problems.append("there is a loop inside %s — that is the other half's job" % rec)
        if re.search(r'\b%s\s*\(' % it, bi):
            problems.append("%s calls itself; the iterative version is the one that must not" % it)
        if not re.search(r'\b(for|while)\b', bi):
            problems.append("%s has no loop in it — an iterative version needs one" % it)
        if re.search(r'\[\s*::\s*-\s*1\s*\]', bi + br):
            problems.append("[::-1] does the reversing for you — write the loop and the recursion")
    if silent == len(PAIRS):
        return None, "nothing written yet — start with count_vowels_iterative"
    if problems:
        return False, problems[0] + ("" if len(problems) == 1 else "  (+%d more)" % (len(problems) - 1))
    return True, "each half really is what it claims to be, and neither is a copy of the other"


# ---- test 2: the two versions must agree, and both must be right ----------

def _make_pair_test(base, it_fn, rec_fn):
    def test():
        if _unwritten(base + "_iterative") and _unwritten(base + "_recursive"):
            return None, "not written yet"
        rng = random.Random(20260811)
        ref = REFS[base]
        for _ in range(1000):
            case = _random_input(base, rng)
            want = ref(case)
            got_i, got_r = it_fn(case), rec_fn(case)
            if got_i != want:
                return False, "the LOOP is wrong: %s_iterative(%r) gave %r, should be %r" % (base, case, got_i, want)
            if got_r != want:
                return False, "the RECURSION is wrong: %s_recursive(%r) gave %r, should be %r" % (base, case, got_r, want)
        return True, "1 000 random inputs, and the two versions agree on every one"
    return test


# ---- 🍨 the scoop: the tree walk with your own stack ----------------------

def _build_tree(values):
    """The exam's storage: [left, data, right], -1 = null, equal goes right."""
    global ArrayNodes
    ArrayNodes = []
    root = -1
    for v in values:
        ArrayNodes.append([-1, v, -1])
        here = len(ArrayNodes) - 1
        if root == -1:
            root = here
            continue
        cur = root
        while True:
            side = 0 if v < ArrayNodes[cur][1] else 2
            if ArrayNodes[cur][side] == -1:
                ArrayNodes[cur][side] = here
                break
            cur = ArrayNodes[cur][side]
    return root


def _t_scoop():
    body = _source_of("in_order_iterative")
    if body is None:
        return None, "optional — could not read the source"
    # the skeleton is two empty lists and a return; nothing is pushed or popped yet
    if not re.search(r'\bappend\b|\bpop\b', body):
        return None, "optional — not attempted"
    if re.search(r'\bin_order_iterative\s*\(', body):
        return False, "in_order_iterative calls itself — the whole point is to do it WITHOUT recursion"
    rng = random.Random(4242)
    for trial in range(200):
        values = rng.sample(range(1, 400), rng.randint(1, 24))
        root = _build_tree(values)
        want = sorted(values)                 # an in-order walk of a BST IS the sorted order
        got = in_order_iterative(root)
        if list(got) != want:
            return False, "on the tree built from %r your walk gave %r, not %r" % (values[:8], list(got)[:8], want[:8])
    if in_order_iterative(-1) != []:
        return False, "an empty tree should give an empty list"
    return True, "200 random trees, and every walk came out sorted — with no recursion anywhere"


def _run():
    tests = [("each half is what it claims to be", _t_shapes)]
    for base, it_fn, rec_fn in PAIRS:
        tests.append(("%s — both ways, same answers" % base, _make_pair_test(base, it_fn, rec_fn)))

    width = max(len(t[0]) for t in tests)
    passed = 0

    print("\n" + "=" * 78)
    print("  A3b · BOTH WAYS — the judge")
    print("=" * 78)

    for label, fn in tests:
        try:
            ok, detail = fn()
        except RecursionError:
            print("  ✗  %-*s   RecursionError — the base case is never reached. Which way "
                  "is the argument moving?" % (width, label))
            continue
        except Exception as e:
            print("  ✗  %-*s   crashed: %s: %s" % (width, label, type(e).__name__, e))
            continue
        if ok is None:
            print("  ·  %-*s   not reached — %s" % (width, label, detail))
        elif ok:
            print("  ✓  %-*s   %s" % (width, label, detail))
            passed += 1
        else:
            print("  ✗  %-*s   %s" % (width, label, detail))

    print("-" * 78)

    # what did the recursion actually cost? measure it on their own code.
    try:
        if not _unwritten("count_vowels_recursive") and count_vowels_recursive("house") == 3:
            depth = _deepest(count_vowels_recursive, "a" * 60)
            print("  📊 your count_vowels_recursive went %d frames deep on a 60-character"
                  " string." % depth)
            print("     The loop version went 1 frame deep on the same input. That difference"
                  " is the whole lesson.")
    except Exception:
        pass

    try:
        ok, detail = _t_scoop()
    except RecursionError:
        ok, detail = False, "RecursionError — a stack walk should never recurse at all"
    except Exception as e:
        ok, detail = False, "crashed: %s: %s" % (type(e).__name__, e)
    mark = {None: "·", True: "✓", False: "✗"}[ok]
    print("  %s  SECOND SCOOP 🍨 — in_order_iterative   %s" % (mark, detail))

    print("=" * 78)
    if passed == len(tests):
        print("  %d / %d.  Everything written twice, and the judge checked the SOURCE of both" % (passed, len(tests)))
        print("  halves as well as their answers. You can now be asked for either one. 🍦")
    else:
        print("  %d / %d." % (passed, len(tests)))
        print("  Read the first failing row. The three that catch nearly everyone: a base")
        print("  case the calls step OVER instead of landing on; a missing 'return' in")
        print("  front of the recursive call, which computes the answer and throws it")
        print("  away; and building the reverse the wrong way round — the first character")
        print("  of the input has to end up LAST.")
    print("=" * 78 + "\n")


def _deepest(fn, arg):
    """How many frames of `fn` were alive at once? Measured, not assumed."""
    box = [0, 0]
    name = fn.__name__
    original = globals()[name]

    def wrapper(*a, **k):
        box[0] += 1
        if box[0] > box[1]:
            box[1] = box[0]
        try:
            return original(*a, **k)
        finally:
            box[0] -= 1
    globals()[name] = wrapper
    try:
        wrapper(arg)
    finally:
        globals()[name] = original
    return box[1]


if __name__ == "__main__":
    _run()
