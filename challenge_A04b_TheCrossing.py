"""
🍦 THE ICE-CREAM CHALLENGE — A4b · The Notation That Survives
============================================================

Every other challenge in this course asked you to write an ALGORITHM.
This one asks you to write the INSTRUMENT that judges algorithms.

    ratio(fn, n)     -> how much more work fn does when the input doubles
    classify(fn)     -> 'O(1)' / 'O(log n)' / 'O(n)' / 'O(n log n)' / 'O(n^2)'
    crossing(fa, fb) -> the smallest n at which fb finally beats fa

WHY THIS ONE HAS NO MARK SCHEME
    Big O is a Paper 3 topic. It has never once appeared on a Paper 4 — not in
    six years of papers. So this file owes nothing to a mark scheme, and it can
    ask for the thing that is actually useful instead: reading the complexity of
    a program you have never seen, without reading a line of its source.

HOW THE MEASUREMENT WORKS
    Each mystery function below is honest about its own effort: call it with a
    size n and it RETURNS the number of operations it would perform on an input
    of that size. It will not tell you its name, its code, or its order.

    Double n, divide the two counts, and the ratio gives the order away:

        O(1)        ratio ~ 1.0     the work does not depend on n at all
        O(log n)    ratio ~ 1.1     one extra step per doubling
        O(n)        ratio ~ 2.0     double the input, double the work
        O(n log n)  ratio ~ 2.2     a little worse than double, and creeping
        O(n^2)      ratio ~ 4.0     double the input, QUADRUPLE the work

    Measure at n = 1000 and pick the closest. The two that sit near each other
    are O(n) and O(n log n) — 2.00 against about 2.20 — and telling those apart
    is the whole difficulty of this challenge.

BANNED — the machine may not do your homework
    Do not import math and compute the answer from a formula you guessed, and do
    not try to read the mystery functions' source. Measure them.

────────────────────────────────────────────────────────────────────────────
"""

import random
import re


# ═══ 1 · THE RATIO ══════════════════════════════════════════════════════

def ratio(fn, n):
    """Return fn(2n) / fn(n) — how much the work grows when the input doubles.

    fn(n) gives you an operation count for size n. That is all you need.
    """
    # YOUR CODE HERE  (replace this placeholder -- -999 is not a real answer)
    return -999


# ═══ 2 · THE CLASSIFIER ═════════════════════════════════════════════════

def classify(fn):
    """Return the Big O of fn, as one of these EXACT strings:

           'O(1)'   'O(log n)'   'O(n)'   'O(n log n)'   'O(n^2)'

    Measure; do not guess. Use ratio() at a decent size — n = 1000 works —
    and choose the class whose expected ratio is nearest to what you got.

    Careful with the middle of the table. O(n) gives 2.00 exactly, and
    O(n log n) gives about 2.20 at n = 1000. A test like "is the ratio less
    than 3?" will call them both linear and lose you half the marks.
    """
    # YOUR CODE HERE
    return None


# ═══ 🍨 THE SECOND SCOOP — the crossing, found by measurement ════════════
#
# In the lesson you watched a quadratic algorithm with a small constant beat an
# n log n algorithm with a big one, all the way up to n = 144. Now find that
# number yourself, for any pair — without knowing either formula.
#
# fa and fb are cost functions of the same shape as above. At small n, fa is
# cheaper. Somewhere it stops being cheaper. Return the SMALLEST n >= 2 at which
# fb(n) < fa(n), or -1 if that never happens below 100000.

def crossing(fa, fb):
    """Return the smallest n >= 2 where fb costs less than fa, or -1."""
    # YOUR CODE HERE
    return -999


# ════════════════════════════════════════════════════════════════════════
#  THE JUDGE — everything below this line is the marking, not the homework
# ════════════════════════════════════════════════════════════════════════

def _log2(n):
    """A logarithm without importing anything, so the ban list stays simple."""
    k, c = 1, 0
    while k < n:
        k, c = k * 2, c + 1
    return c


# The mystery functions are built HERE, from a table, and their names give
# nothing away. Hard-coding "if fn is const_one" cannot work: which order each
# name carries is decided by the list below, and the list is shuffled by seed.
_ORDERS = {
    'O(1)':        lambda n: 40,
    'O(log n)':    lambda n: 3 * _log2(n) + 5,
    'O(n)':        lambda n: 7 * n + 12,
    'O(n log n)':  lambda n: 2 * n * _log2(n) + n,
    'O(n^2)':      lambda n: n * n // 2 + 3 * n,
}


def _mystery_set(seed):
    """A shuffled batch of (hidden_name, fn, true_order)."""
    rng = random.Random(seed)
    names = ['alpha', 'bravo', 'charlie', 'delta', 'echo', 'foxtrot',
             'golf', 'hotel', 'india', 'juliet', 'kilo', 'lima']
    orders = list(_ORDERS.items()) * 3
    rng.shuffle(orders)
    out = []
    for i, nm in enumerate(names):
        order, fn = orders[i][0], orders[i][1]
        out.append((nm, fn, order))
    return out


def _source_of(name):
    try:
        src = open(__file__, encoding='utf-8').read()
    except Exception:
        return None
    m = re.search(r'^def %s\(.*?\):\n(.*?)(?=^\S|\Z)' % name, src, re.S | re.M)
    if not m:
        return None
    body = re.sub(r'""".*?"""', '', m.group(1), flags=re.S)
    return re.sub(r'#.*', '', body)


def _unwritten(name):
    b = _source_of(name)
    if b is None:
        return True
    return re.sub(r'\s', '', b) in ('return-999', 'returnNone', '')


def _t_ratio():
    if _unwritten('ratio'):
        return None, "not written yet"
    cases = [
        (_ORDERS['O(1)'],       1000, 1.00),
        (_ORDERS['O(n)'],       1000, 2.00),
        (_ORDERS['O(n^2)'],     1000, 4.00),
        (_ORDERS['O(log n)'],   1000, 1.10),
        (_ORDERS['O(n log n)'], 1000, 2.20),
    ]
    for fn, n, want in cases:
        try:
            got = ratio(fn, n)
        except Exception as e:
            return False, "ratio() crashed: %s: %s" % (type(e).__name__, e)
        if got is None or abs(got - want) > 0.06:
            return False, ("on a function whose true ratio is %.2f, ratio(fn, %d) gave %s. "
                           "It should be fn(2n) divided by fn(n)." % (want, n, got))
    return True, "correct on all five growth classes"


def _t_classify():
    if _unwritten('classify'):
        return None, "not written yet"
    wrong = []
    for seed in (1, 2, 3):
        for nm, fn, order in _mystery_set(seed):
            try:
                got = classify(fn)
            except Exception as e:
                return False, "classify() crashed: %s: %s" % (type(e).__name__, e)
            if got != order:
                wrong.append((nm, got, order))
    if wrong:
        nm, got, order = wrong[0]
        extra = ""
        if {got, order} == {'O(n)', 'O(n log n)'}:
            extra = ("  ← this is THE hard pair. At n = 1000 they are 2.00 against 2.20; "
                     "a threshold anywhere near 3 cannot separate them.")
        return False, ("%d of 36 misread. The first: you said %s, it is %s.%s"
                       % (len(wrong), got, order, extra))
    return True, "36 unseen functions across 3 batches, every one classified correctly"


def _t_crossing():
    if _unwritten('crossing'):
        return None, "optional — not attempted"
    cases = [
        # the same SHAPE as the lesson's crossing widget, but this _log2 counts whole
        # doublings where the widget uses a real logarithm, so the crossing is not 144
        (lambda n: n * n // 2,          lambda n: 10 * n * _log2(n),  "a quadratic against an n log n"),
        (lambda n: 100 * n,             lambda n: n * n,              "linear vs quadratic"),
        (lambda n: 5 * n * n,           lambda n: 5 * n * n + 1,      "never — fb is always dearer"),
    ]
    for fa, fb, label in cases:
        want = -1
        for n in range(2, 100000):
            if fb(n) < fa(n):
                want = n
                break
        try:
            got = crossing(fa, fb)
        except Exception as e:
            return False, "crossing() crashed: %s: %s" % (type(e).__name__, e)
        if got != want:
            return False, "on %s, crossing() gave %s and the answer is %s" % (label, got, want)
    return True, "three pairs, including one that never crosses"


def _run():
    tests = [("ratio — how much the work grows when n doubles", _t_ratio),
             ("classify — 36 functions you cannot read", _t_classify)]
    width = max(len(t[0]) for t in tests) + 2
    passed = 0

    print("\n" + "=" * 78)
    print("  A4b · THE NOTATION THAT SURVIVES — the judge")
    print("=" * 78)

    for label, fn in tests:
        try:
            ok, detail = fn()
        except Exception as e:
            print("  ✗  %-*s   crashed: %s: %s" % (width, label, type(e).__name__, e)); continue
        if ok is None:
            print("  ·  %-*s   not reached — %s" % (width, label, detail))
        elif ok:
            print("  ✓  %-*s   %s" % (width, label, detail)); passed += 1
        else:
            print("  ✗  %-*s   %s" % (width, label, detail))

    print("-" * 78)

    if not _unwritten('classify') and not _unwritten('ratio'):
        print("  📏 your instrument, pointed at one batch:")
        for nm, fn, order in _mystery_set(1)[:5]:
            try:
                r, c = ratio(fn, 1000), classify(fn)
            except Exception:
                r, c = "?", "?"
            mark = "✓" if c == order else "✗"
            rs = ("%.2f" % r) if isinstance(r, float) else str(r)
            print("     %-9s ratio %-6s -> you said %-11s  (%s %s)" % (nm, rs, c, mark, order))

    try:
        ok, detail = _t_crossing()
    except Exception as e:
        ok, detail = False, "crashed: %s: %s" % (type(e).__name__, e)
    print("  %s  SECOND SCOOP 🍨 — crossing   %s" % ({None: "·", True: "✓", False: "✗"}[ok], detail))

    print("=" * 78)
    if passed == len(tests):
        print("  %d / %d.  You can now read the complexity of a program you have never seen," % (passed, len(tests)))
        print("  from nothing but its behaviour. That is what the notation is FOR. 🍦")
    else:
        print("  %d / %d." % (passed, len(tests)))
        print("  Read the first failing row. The one that catches nearly everyone is the")
        print("  O(n) / O(n log n) pair: 2.00 against 2.20, and no threshold near 3 will do.")
    print("=" * 78 + "\n")


if __name__ == "__main__":
    _run()
