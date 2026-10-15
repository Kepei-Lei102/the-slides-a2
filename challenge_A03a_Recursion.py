"""
🍦 THE ICE-CREAM CHALLENGE — A3a · The Function That Calls Itself
================================================================

Four functions. Every one of them is three or four lines, and every one of
them calls itself.

    factorial(n)        n × (n-1) × … × 1,  and factorial(0) = 1
    fib(n)              0, 1, 1, 2, 3, 5, 8 …   fib(0) = 0, fib(1) = 1
    gcd(a, b)           the greatest common divisor, Euclid's way
    count_leaves(node)  how many nodes in a binary tree have no children

HOW TO USE THIS FILE
    1. Fill in the four functions below.
    2. Run the file. The judge runs automatically at the bottom.
    3. Keep going until every row is a ✓.

BANNED — the machine may not do your homework
    math.factorial   math.gcd   functools   any library that knows the answer

THE TEST YOU CANNOT FAKE — and it is a new kind
    Every one of these has a trivial version written with a loop, and a loop
    would pass any test that only checks the answer. So the judge does
    something different: it READS THIS FILE and looks inside your four
    functions for a `for` or a `while`.

    The point of this homework is not the number. It is the technique — and
    the technique is the one thing a results-only test cannot see.

────────────────────────────────────────────────────────────────────────────
"""


def factorial(n):
    """n! — and factorial(0) is 1, which is the base case.

    Two lines of body: the case you can answer outright, and the case that
    calls itself on something smaller.
    """
    # YOUR CODE HERE  (replace this placeholder -- -999 is not a real answer)
    return -999


def fib(n):
    """The nth Fibonacci number. fib(0) = 0, fib(1) = 1.

    TWO base cases (or one that covers both), and a general case that calls
    itself TWICE. Keep n small when you test it by hand — and read the
    judge's call count when it finishes.
    """
    # YOUR CODE HERE
    return -999


def gcd(a, b):
    """Euclid, c. 300 BCE — the oldest algorithm anyone still runs.

    The base case is when b is 0: the answer is a. Otherwise call yourself
    with (b, a mod b). Two lines. It has outlived every language it has
    ever been written in.
    """
    # YOUR CODE HERE
    return -999


# A binary tree, in the exam's own storage: [left, data, right], -1 = null.
# The judge builds this for you; you only walk it.
ArrayNodes = []


def count_leaves(node):
    """How many LEAVES (nodes with no children at all) are at or below `node`?

    `node` is an index into ArrayNodes; -1 means there is nothing here.
        ArrayNodes[node][0]  left pointer      ArrayNodes[node][2]  right pointer
        ArrayNodes[node][1]  the data

    Three cases: nothing here · a leaf · everything else, which is
    "the leaves on my left, plus the leaves on my right".
    """
    # YOUR CODE HERE
    return -999


# ═══════════════════════════════════════════════════════════════════════
#  SECOND SCOOP 🍨 (optional) — the one-line fix that changes everything
# ═══════════════════════════════════════════════════════════════════════
#
#  fib() above recomputes the same values over and over: fib(30) costs
#  about 2.7 MILLION calls for an answer with six digits in it. Nothing is
#  wrong with the recursion — the problem is that it FORGETS.
#
#  Write memo_fib(): the same function, but keeping a dictionary of answers
#  it has already worked out. Look in the cache first; only compute what is
#  genuinely new; store it before returning.
#
#  The judge does not check your answer. It counts your CALLS.

_memo = {}


def memo_fib(n):
    # OPTIONAL
    return -999


# ────────────────────────────────────────────────────────────────────────────
#  THE JUDGE — you do not need to read past here, but you may
# ────────────────────────────────────────────────────────────────────────────

import re
import sys


def _build_tree(values):
    """Insert values into the exam's 2D-array tree; returns the root index."""
    global ArrayNodes
    ArrayNodes = [[-1, -1, -1] for _ in range(len(values) + 2)]
    root, free = -1, 0
    for v in values:
        ArrayNodes[free] = [-1, v, -1]
        if root == -1:
            root = 0
        else:
            c = root
            while True:
                if v < ArrayNodes[c][1]:
                    if ArrayNodes[c][0] == -1:
                        ArrayNodes[c][0] = free
                        break
                    c = ArrayNodes[c][0]
                else:
                    if ArrayNodes[c][2] == -1:
                        ArrayNodes[c][2] = free
                        break
                    c = ArrayNodes[c][2]
        free += 1
    return root


def _source_of(name):
    """The body of one function, read out of this very file."""
    try:
        src = open(__file__, encoding='utf-8').read()
    except Exception:
        return None
    m = re.search(r'^def %s\(.*?\):\n(.*?)(?=^\S|\Z)' % name, src, re.S | re.M)
    return m.group(1) if m else None


def _t_loopcheck():
    """The test you cannot fake: each function must call ITSELF, and none of
    them may use a loop. An unwritten function passes neither half — silence
    is not a pass."""
    looped, silent = [], []
    for name in ('factorial', 'fib', 'gcd', 'count_leaves'):
        body = _source_of(name)
        if body is None:
            return None, "could not read this file to check the source"
        code = re.sub(r'""".*?"""', '', body, flags=re.S)      # drop the docstring
        code = re.sub(r'#.*', '', code)                        # drop comments
        if re.search(r'\b(for|while)\b', code):
            looped.append(name)
        if not re.search(r'\b%s\s*\(' % name, code):           # no call to itself
            silent.append(name)
    if silent and len(silent) == 4:
        return None, "none of the four call themselves yet — start with factorial"
    if silent:
        return False, (", ".join(silent) + " never calls itself, so whatever it does, it is not recursion")
    if looped:
        return False, ("a loop is hiding in " + ", ".join(looped)
                       + " — these have to call themselves, or the exercise did not happen")
    return True, "all four call themselves, and not a for or a while among them"


def _t_factorial():
    if factorial(0) != 1:
        return False, "factorial(0) must be 1 — that is the base case, got %r" % (factorial(0),)
    want = 1
    for i in range(1, 11):
        want *= i
        if factorial(i) != want:
            return False, "factorial(%d) should be %d, got %r" % (i, want, factorial(i))
    return True, "0 through 10, all correct"


def _t_fib():
    seq = [0, 1]
    while len(seq) < 16:
        seq.append(seq[-1] + seq[-2])
    for i, want in enumerate(seq):
        if fib(i) != want:
            return False, "fib(%d) should be %d, got %r" % (i, want, fib(i))
    return True, "0 through 15 against the sequence itself"


def _t_gcd():
    import math
    pairs = [(48, 18), (270, 192), (17, 5), (100, 75), (13, 13), (81, 27), (1, 999)]
    for a, b in pairs:
        want = math.gcd(a, b)
        if gcd(a, b) != want:
            return False, "gcd(%d, %d) should be %d, got %r" % (a, b, want, gcd(a, b))
    return True, "seven pairs, checked against the library (which you may not use)"


def _t_leaves():
    cases = [((15, 8, 19, 3, 10), 3),          # 3, 10, 19
             ((10,), 1),                        # the root alone is a leaf
             ((1, 2, 3, 4, 5), 1),              # a right-hand stick: one leaf
             ((50, 25, 75, 10, 30, 60, 90), 4)]
    for values, want in cases:
        root = _build_tree(list(values))
        got = count_leaves(root)
        if got != want:
            return False, "inserting %r gives %d leaves, got %r" % (values, want, got)
    if count_leaves(-1) != 0:
        return False, "count_leaves(-1) must be 0 — an empty tree has no leaves"
    return True, "four trees, plus the empty one"


def _t_deep():
    """Recursion has a depth limit, and a correct answer respects it."""
    try:
        r = factorial(300)
    except RecursionError:
        return False, ("factorial(300) hit Python's recursion limit — correct, but check you are "
                       "not recursing more than once per step")
    return (True, "factorial(300) — 615 digits, and the stack survived it") if r > 0 else (False, "factorial(300) went wrong")


def _count_calls(fn, n):
    """Wrap a global by name and count how many times it is entered."""
    box = [0]
    original = globals()[fn]

    def wrapper(*a, **k):
        box[0] += 1
        return original(*a, **k)
    globals()[fn] = wrapper
    try:
        wrapper(n)
    finally:
        globals()[fn] = original
    return box[0]


def _run():
    tests = [
        ("no loops — they really do call themselves", _t_loopcheck),
        ("factorial", _t_factorial),
        ("fib", _t_fib),
        ("gcd — Euclid's own", _t_gcd),
        ("count_leaves walks the tree", _t_leaves),
        ("it survives a deep call", _t_deep),
    ]
    width = max(len(t[0]) for t in tests)
    passed = 0

    print("\n" + "=" * 78)
    print("  A3a · RECURSION — the judge")
    print("=" * 78)

    for label, fn in tests:
        try:
            ok, detail = fn()
        except RecursionError:
            print("  ✗  %-*s   RecursionError — the base case is never reached. Which way "
                  "is it moving?" % (width, label))
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

    # how expensive was the honest version?
    try:
        if fib(10) == 55:
            n20 = _count_calls('fib', 20)
            print("  📊 your fib(20) made %s calls for a two-digit answer." % f"{n20:,}")
    except Exception:
        pass

    scoop = "·  SECOND SCOOP 🍨 — memo_fib   (optional — not attempted)"
    try:
        _memo.clear()
        if memo_fib(25) == 75025:
            _memo.clear()
            calls = _count_calls('memo_fib', 25)
            plain = _count_calls('fib', 25) if fib(10) == 55 else None
            if calls <= 60:
                scoop = ("✓  SECOND SCOOP 🍨 — memo_fib(25) in %d calls"
                         % calls + (", where the forgetful one took %s. Same recursion; it just "
                                    "stopped throwing its work away." % f"{plain:,}" if plain else ""))
            else:
                scoop = ("✗  SECOND SCOOP 🍨 — memo_fib(25) still took %d calls; the cache is not "
                         "being read before the work is done" % calls)
    except Exception:
        pass
    print("  " + scoop)

    print("=" * 78)
    if passed == len(tests):
        print("  %d / %d.  Four functions, no loops, and the machine checked the SOURCE" % (passed, len(tests)))
        print("  as well as the answers. You did the technique, not just the arithmetic. 🍦")
    else:
        print("  %d / %d." % (passed, len(tests)))
        print("  Read the first failing row. Nearly every failure here is one of three")
        print("  things: no base case, a base case the calls never reach (check which")
        print("  way the argument is moving), or a missing 'return' in front of the")
        print("  recursive call — that last one computes the answer and throws it away.")
    print("=" * 78 + "\n")


if __name__ == "__main__":
    _run()
