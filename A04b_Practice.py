"""A04b — The Notation That Survives, questions 7–9 (8 marks).

Q1–6 are on the printed sheet. Copy this file before editing it; submit as
A04b_<YourName>.py. Run: python3 A04b_Practice.py (or your renamed copy).

Before coding, sketch the two searches or trace their inspected indices on
paper. The trace is self/teacher checked, outside the code total.

Q7 [3] linearComparisons(A, target): return the number of elements inspected,
not an index. Stop at the first match; an absent target costs len(A), an empty
list costs 0. Example [3,9,7,1,5], target 7 -> 3 comparisons.
Q8 [3] binaryComparisons(A, target): A is sorted. Use inclusive low/high bounds
and the lower middle index (low + high) // 2. Count one comparison per middle
inspected, stop on a match or an empty window. On [2,4,...,30], target 16 -> 1,
target 2 -> 4, target 27 -> 4. An empty list costs 0.
Q9 [2] growthClass(atN, at2N): positive measured operation counts at n=1000
and n=2000. Return exactly O(1), O(log n), O(n), O(n log n), or O(n^2).
Their approximate ratios are 1, 1.1, 2, 2.2, 4. Choose the nearest class;
these classroom measurements are not a general proof of asymptotic order.
Do not mutate the input lists. Preserve the function names below.

The self-check uses fixed inputs/expected outputs, not reference solutions.
Its provisional question totals are feedback, not a final teacher mark.
Written 22 + code 8 = 30 marks.
"""


def linearComparisons(A, target):
    # Q7 — YOUR CODE HERE
    return None


def binaryComparisons(A, target):
    # Q8 — YOUR CODE HERE
    return None


def growthClass(atN, at2N):
    # Q9 — YOUR CODE HERE
    return None


# SELF-CHECK — keep this section unchanged.
_CASES = (
    (7, 3, "linearComparisons", (
        (([], 7), 0), (([5], 5), 1), (([5], 2), 1),
        (([3, 9, 7, 1, 5], 3), 1), (([3, 9, 7, 1, 5], 7), 3),
        (([3, 9, 7, 1, 5], 5), 5), (([3, 9, 7, 1, 5], 99), 5),
        (([7, 7, 7], 7), 1), (([-3, 0, 8, 12], 8), 3),
    )),
    (8, 3, "binaryComparisons", (
        (([], 7), 0), (([5], 5), 1), (([5], 2), 1),
        (([2, 4], 2), 1), (([2, 4], 4), 2), (([2, 4], 3), 2),
        ((list(range(2, 31, 2)), 16), 1),
        ((list(range(2, 31, 2)), 2), 4),
        ((list(range(2, 31, 2)), 30), 4),
        ((list(range(2, 31, 2)), 27), 4),
        (([1, 1, 1], 1), 1), (([-4, 0, 7, 12, 20, 90], 12), 3),
    )),
    (9, 2, "growthClass", (
        ((100, 100), "O(1)"), ((9, 10), "O(log n)"),
        ((1000, 2000), "O(n)"), ((9000, 20000), "O(n log n)"),
        ((1000000, 4000000), "O(n^2)"), ((40, 40), "O(1)"),
        ((7012, 14012), "O(n)"), ((21000, 46000), "O(n log n)"),
        ((503000, 2006000), "O(n^2)"),
    )),
)


def judge():
    score = 0
    results = []
    for question, worth, name, cases in _CASES:
        passed = True
        for args, expected in cases:
            copied = tuple(x[:] if isinstance(x, list) else x for x in args)
            try:
                got = globals()[name](*copied)
                ok = type(got) is type(expected) and got == expected and copied == args
                detail = f"got {got!r}, expected {expected!r} (keep inputs unchanged)"
            except Exception as exc:
                ok = False
                detail = f"{type(exc).__name__}: {exc}"
            if not ok:
                print(f"Q{question} {name}{args!r}: {detail}")
                passed = False
                break
        results.append(passed)
        score += worth if passed else 0
        print(f"Q{question}: {'PASS' if passed else 'not complete'} ({worth} marks)")
    print(f"Self-check: {score}/8; written work and method are teacher checked.")
    if all(results[:2]):
        for name in ("linearComparisons", "binaryComparisons"):
            worst = [max(globals()[name](list(range(0, 2*n, 2)), t)
                         for t in range(-1, 2*n+1)) for n in (1000, 2000)]
            print(f"Your {name}: worst counts {worst}; doubling ratio {worst[1]/worst[0]:.2f}")
    return score


if __name__ == "__main__":
    judge()
