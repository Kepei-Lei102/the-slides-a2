"""
🍦 THE ICE-CREAM CHALLENGE — A4a · Judging Algorithms
=====================================================

Four algorithms. You have met all of them, and you have written two.

    linear_search(A, target)    -> the index, or -1
    binary_search(A, target)    -> the index, or -1   (A is sorted)
    bubble_sort(A)              -> the same list, sorted ascending
    insertion_sort(A)           -> the same list, sorted ascending

HOW THIS ONE IS DIFFERENT
    Every other judge in this course asked "is your answer right?". This one
    asks that first and then does something else: it RACES them. Same data,
    same start line, counting the only unit that matters — comparisons.

    You cannot win the race. The winner is decided by the algorithm you chose,
    not by how well you typed it, and that is the entire point of the unit.

THE TEST YOU CANNOT FAKE
    A binary search that secretly walks the list still finds everything. So
    the judge counts your comparisons: on 1 023 sorted items, a real binary
    search needs at most 10, and a linear search needs up to 1 023. There is
    no way to fake being logarithmic.

BANNED — the machine may not do your homework
    list.index   list.sort   sorted()   bisect   any library that searches or
    sorts for you.

────────────────────────────────────────────────────────────────────────────
"""

import random
import re


# ═══ 1 · THE SEARCHES ═══════════════════════════════════════════════════

def linear_search(A, target):
    """Return the index of target in A, or -1. A may be in any order at all.

    Count nothing, time nothing — just find it. The judge does the counting.
    """
    # YOUR CODE HERE  (replace this placeholder -- -999 is not a real answer)
    return -999


def binary_search(A, target):
    """Return the index of target in the SORTED list A, or -1.

    Keep a window: Low and High. Look at the middle. Throw half away.

    Three things to get right, and they are the three that appear in real
    scripts: the loop must survive a window of one; Low must move PAST the
    midpoint, not to it; and when the window empties, the answer is -1.
    """
    # YOUR CODE HERE
    return -999


# ═══ 2 · THE SORTS ══════════════════════════════════════════════════════

def bubble_sort(A):
    """Sort A ascending and return it. Compare neighbours, swap, repeat.

    You wrote this in pseudocode last year. Same algorithm, typed.
    """
    # YOUR CODE HERE
    return None


def insertion_sort(A):
    """Sort A ascending and return it.

    Carry each item back into the part behind it, which is always sorted.
    You have written this before — for a lesson about recursion.
    """
    # YOUR CODE HERE
    return None


# ═══ 🍨 THE SECOND SCOOP — the one that is not on your syllabus ══════════
#
# Selection sort. Walk the unsorted part, remember WHERE the smallest value
# is, and when you reach the end, swap it into the first unsorted slot.
#
# The judge will not read your code. It counts how many times you write into
# the list: selection sort writes at most twice per pass, because it swaps
# once. A bubble sort wearing this name writes hundreds of times, and there
# is nowhere to hide.

def selection_sort(A):
    """Sort A ascending and return it — one swap per pass."""
    # YOUR CODE HERE
    return None


# ════════════════════════════════════════════════════════════════════════
#  THE JUDGE — everything below this line is the marking, not the homework
# ════════════════════════════════════════════════════════════════════════

SORTS = ("bubble_sort", "insertion_sort")
ALL_SORTS = SORTS + ("selection_sort",)


class Counted(list):
    """A list that remembers how many times it was written into."""
    writes = 0

    def __setitem__(self, i, v):
        Counted.writes += 1
        list.__setitem__(self, i, v)


class Watched(list):
    """A list that remembers how many of its elements were LOOKED AT."""
    reads = 0

    def __getitem__(self, i):
        if isinstance(i, int):
            Watched.reads += 1
        return list.__getitem__(self, i)


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
    stripped = re.sub(r'\s', '', b)
    return stripped in ('return-999', 'returnNone', '')


def _banned(name):
    b = _source_of(name) or ''
    for bad in ('.index(', '.sort(', 'sorted(', 'bisect'):
        if bad in b:
            return bad
    return None


def _sorted_by(fn, data):
    out = fn(list(data))
    return list(out) if isinstance(out, list) else None


# ---- the searches -------------------------------------------------------

def _t_linear():
    if _unwritten('linear_search'):
        return None, "not written yet"
    b = _banned('linear_search')
    if b:
        return False, "%s does the searching for you — write the loop" % b
    cases = [([3, 9, 7, 1, 5], 7, 2), ([3, 9, 7, 1, 5], 3, 0), ([3, 9, 7, 1, 5], 5, 4),
             ([3, 9, 7, 1, 5], 99, -1), ([], 1, -1), ([4], 4, 0)]
    for data, t, want in cases:
        got = linear_search(list(data), t)
        if got != want:
            return False, "linear_search(%r, %r) gave %r, should be %r" % (data, t, got, want)
    return True, "six cases, including an empty list and a value that is not there"


def _t_binary():
    if _unwritten('binary_search'):
        return None, "not written yet"
    b = _banned('binary_search')
    if b:
        return False, "%s does the searching for you — write the window" % b
    A = [i * 2 for i in range(1024)]
    for t in (0, 2, 1000, 2046, -1, 3, 99999):
        want = t // 2 if (t >= 0 and t % 2 == 0 and t <= 2046) else -1
        got = binary_search(list(A), t)
        if got != want:
            return False, "binary_search(evens up to 2046, %r) gave %r, should be %r" % (t, got, want)
    # the test you cannot fake: it must be logarithmic
    B = Watched([i for i in range(1023)])
    Watched.reads = 0
    binary_search(B, 987)
    reads = Watched.reads
    if reads > 40:
        return False, ("it found everything, but it looked at %d elements of 1 023 — that is a linear "
                       "search wearing a binary search's name. A real one looks at about 10." % reads)
    return True, "correct on 1 023 items, and it only looked at %d of them" % reads


# ---- the sorts ----------------------------------------------------------

def _make_sort_test(name):
    def test():
        if _unwritten(name):
            return None, "not written yet"
        b = _banned(name)
        if b:
            return False, "%s sorted it for you — write the sort" % b
        fn = globals()[name]
        fixed = [[], [5], [2, 1], [1, 2, 3, 4, 5], [5, 4, 3, 2, 1], [7, 7, 7, 7], [0, -3, 9, -3, 4]]
        for data in fixed:
            got = _sorted_by(fn, data)
            if got is None:
                return False, "%s(%r) returned nothing — it must RETURN the sorted list" % (name, data)
            if got != sorted(data):
                return False, "%s(%r) gave %r, should be %r" % (name, data, got, sorted(data))
        rng = random.Random(20260812)
        for _ in range(300):
            data = [rng.randint(-99, 99) for _ in range(rng.randint(0, 18))]
            if _sorted_by(fn, data) != sorted(data):
                return False, "%s(%r) came out wrong" % (name, data)
        return True, "seven fixed cases and 300 random lists"
    return test


def _t_scoop():
    if _unwritten('selection_sort'):
        return None, "optional — not attempted"
    if _banned('selection_sort'):
        return False, "a library sorted it for you"
    ok, detail = _make_sort_test('selection_sort')()
    if ok is not True:
        return ok, detail
    rng = random.Random(4242)
    worst = 0
    for _ in range(50):
        data = [rng.randint(0, 999) for _ in range(40)]
        Counted.writes = 0
        selection_sort(Counted(data))
        worst = max(worst, Counted.writes)
    if worst > 2 * 39:
        return False, ("it sorts, but it wrote into the list %d times on 40 items. Selection sort swaps "
                       "at most once per pass — 78 writes. Whatever this is, it is not selection sort." % worst)
    if worst == 0:
        return False, "it never wrote into the list you were given — selection sort swaps IN PLACE"
    return True, "sorts correctly, and at most %d writes on 40 items — one swap per pass" % worst


# ---- the race -----------------------------------------------------------

def _count_comparisons(fn, data):
    """Count element reads, which is what every one of these algorithms spends."""
    Watched.reads = 0
    w = Watched(data)
    fn(w)
    return Watched.reads


def _race():
    rng = random.Random(20260812)
    shuffled = rng.sample(range(1000), 60)
    nearly = list(range(60))
    nearly[9], nearly[10] = nearly[10], nearly[9]
    nearly[40], nearly[41] = nearly[41], nearly[40]
    rows = []
    for name in ALL_SORTS:
        if _unwritten(name):
            continue
        fn = globals()[name]
        try:
            rows.append((name, _count_comparisons(fn, shuffled), _count_comparisons(fn, nearly)))
        except Exception:
            pass
    return rows


def _run():
    tests = [
        ("linear_search", _t_linear),
        ("binary_search — and it must be logarithmic", _t_binary),
        ("bubble_sort", _make_sort_test('bubble_sort')),
        ("insertion_sort", _make_sort_test('insertion_sort')),
    ]
    width = max(len(t[0]) for t in tests)
    passed = 0

    print("\n" + "=" * 78)
    print("  A4a · JUDGING ALGORITHMS — the judge")
    print("=" * 78)

    for label, fn in tests:
        try:
            ok, detail = fn()
        except RecursionError:
            print("  ✗  %-*s   RecursionError — something is calling itself and not stopping" % (width, label)); continue
        except Exception as e:
            print("  ✗  %-*s   crashed: %s: %s" % (width, label, type(e).__name__, e)); continue
        if ok is None:
            print("  ·  %-*s   not reached — %s" % (width, label, detail))
        elif ok:
            print("  ✓  %-*s   %s" % (width, label, detail)); passed += 1
        else:
            print("  ✗  %-*s   %s" % (width, label, detail))

    print("-" * 78)

    rows = _race()
    if rows:
        print("  🏁 THE RACE — 60 items, same data, counting how many elements each one looks at")
        print("     %-16s %10s   %10s" % ("", "shuffled", "nearly sorted"))
        for name, a, b in rows:
            print("     %-16s %10s   %10s" % (name.replace('_sort', ''), f"{a:,}", f"{b:,}"))
        if len(rows) >= 2:
            best = min(rows, key=lambda r: r[2])
            print("     On data that was almost in order, %s did the least work — by a long way."
                  % best[0].replace('_sort', ''))
            print("     Nobody wrote a better program. They chose a different algorithm.")

    try:
        ok, detail = _t_scoop()
    except Exception as e:
        ok, detail = False, "crashed: %s: %s" % (type(e).__name__, e)
    mark = {None: "·", True: "✓", False: "✗"}[ok]
    print("  %s  SECOND SCOOP 🍨 — selection_sort   %s" % (mark, detail))

    print("=" * 78)
    if passed == len(tests):
        print("  %d / %d.  Four algorithms, all correct — and the race showed you the thing" % (passed, len(tests)))
        print("  the marks never do: which one you should have reached for. 🍦")
    else:
        print("  %d / %d." % (passed, len(tests)))
        print("  Read the first failing row. The three that catch nearly everyone: a binary")
        print("  search whose window never shrinks (Low = Mid instead of Mid + 1); a sort that")
        print("  sorts but forgets to RETURN the list; and an inner loop whose bound is wrong")
        print("  by one, which sorts everything except the last pair.")
    print("=" * 78 + "\n")


if __name__ == "__main__":
    _run()
