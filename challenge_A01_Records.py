"""
🍦 THE ICE-CREAM CHALLENGE — A1 · Records
=========================================

This is the first rung of the A2 ladder, and it is the program from the top of
the lesson — rebuilt so it cannot lie to you.

    build_class(names, grades)  ->  a list of records, one per student
    sort_by_grade(klass)        ->  the SAME people, in grade order

HOW TO USE THIS FILE
    1. Fill in the two functions below.
    2. Run the file.  The judge runs automatically at the bottom.
    3. Keep going until every row is a ✓.

WHY THIS ONE CANNOT BE FAKED
    The judge never checks the order alone. It checks that the set of
    (name, grade) PAIRS is identical before and after — so if Chen's 58
    ends up on Amir, the pairs have changed and you fail, no matter how
    beautifully sorted the numbers are.

    That is the whole lesson in one assertion: a sort is only correct if
    nobody's data changed hands.

BANNED — because these are exactly the mistake the lesson is about
    Sorting the grades on their own and writing them back.
    Keeping two parallel lists and sorting them separately.
    Anything that touches `names` and `grades` after build_class() has run.

────────────────────────────────────────────────────────────────────────────
"""


# ═══════════════════════════════════════════════════════════════════════
#  SCOOP 1 — build the records
# ═══════════════════════════════════════════════════════════════════════
#
#  A "record" in Python is most naturally a dict with named fields:
#
#      {"name": "Chen", "grade": 58}
#
#  build_class(["Amir", "Bo"], [71, 94])
#      ->  [{"name": "Amir", "grade": 71}, {"name": "Bo", "grade": 94}]
#
#  The two lists always have the same length. This is the LAST moment at
#  which they are allowed to exist separately.

def build_class(names, grades):
    # YOUR CODE HERE
    pass


# ═══════════════════════════════════════════════════════════════════════
#  SCOOP 2 — sort them, all of them, together
# ═══════════════════════════════════════════════════════════════════════
#
#  sort_by_grade([{"name":"Bo","grade":94}, {"name":"Chen","grade":58}])
#      ->  [{"name":"Chen","grade":58}, {"name":"Bo","grade":94}]
#
#  Lowest grade first. Return the sorted list.

def sort_by_grade(klass):
    # YOUR CODE HERE
    pass


# ═══════════════════════════════════════════════════════════════════════
#  SECOND SCOOP (optional) — keep it stable
# ═══════════════════════════════════════════════════════════════════════
#
#  When two students have the SAME grade, they must come out in the order
#  they went in. A sort that promises this is called STABLE, and it is a
#  real property with a real name that you will meet again when you write
#  sorting algorithms yourself.
#
#  If your sort_by_grade already does this, the stability row passes for
#  free — and it is worth understanding WHY it did.


# ────────────────────────────────────────────────────────────────────────────
#  THE JUDGE — you do not need to read past here, but you may
# ────────────────────────────────────────────────────────────────────────────

def _pairs(klass):
    """The multiset of (name, grade) pairs — order deliberately thrown away."""
    return sorted((s["name"], s["grade"]) for s in klass)


def _run():
    cases = [
        ("the lesson's own class", ["Amir", "Bo", "Chen", "Dara", "Eve"],
                                   [71, 94, 58, 88, 63]),
        ("already in order",       ["A", "B", "C"], [1, 2, 3]),
        ("exactly backwards",      ["A", "B", "C"], [3, 2, 1]),
        ("one student",            ["Solo"], [50]),
        ("empty class",            [], []),
        ("every grade equal",      ["P", "Q", "R"], [60, 60, 60]),
        ("negative and zero",      ["X", "Y", "Z"], [0, -5, 12]),
        ("duplicate names",        ["Sam", "Sam", "Al"], [30, 90, 60]),
    ]

    width = max(len(c[0]) for c in cases)
    passed = 0

    print("\n" + "═" * 74)
    print("  🍦  A1 · RECORDS — the judge")
    print("═" * 74)

    for label, names, grades in cases:
        before = sorted(zip(names, grades))
        try:
            klass = build_class(list(names), list(grades))
            out = sort_by_grade(klass)

            if out is None:
                raise AssertionError("sort_by_grade returned None")
            if len(out) != len(names):
                raise AssertionError(
                    "%d students in, %d out" % (len(names), len(out)))

            got = [(s["name"], s["grade"]) for s in out]

            # 1. nobody's data changed hands
            if sorted(got) != before:
                raise AssertionError(
                    "the pairs changed — somebody has the wrong grade\n"
                    + " " * 8 + "in : %s\n" % (before,)
                    + " " * 8 + "out: %s" % (sorted(got),))

            # 2. and it is actually in grade order
            gs = [g for _, g in got]
            if gs != sorted(gs):
                raise AssertionError("not in grade order: %s" % (gs,))

        except AssertionError as e:
            print("  ✗  %-*s   %s" % (width, label, e))
            continue
        except Exception as e:
            print("  ✗  %-*s   crashed: %s: %s"
                  % (width, label, type(e).__name__, e))
            continue

        print("  ✓  %-*s" % (width, label))
        passed += 1

    # ── the optional stability row, reported separately ──────────────────
    print("─" * 74)
    stable = None
    try:
        names = ["first", "second", "third", "fourth"]
        grades = [70, 70, 70, 70]
        out = sort_by_grade(build_class(names, grades))
        stable = [s["name"] for s in out] == names
    except Exception:
        stable = False
    print("  %s  SECOND SCOOP — stable on equal grades%s"
          % ("✓" if stable else "·",
             "" if stable else "   (optional — ties came out reordered)"))

    print("═" * 74)
    if passed == len(cases):
        print("  %d / %d.  Everyone kept their own grade. 🍦" % (passed, len(cases)))
        if stable:
            print("  And ties held their order — that is a stable sort, and it has a name.")
    else:
        print("  %d / %d." % (passed, len(cases)))
        print("  Read the failing line: it tells you whether the ORDER is wrong")
        print("  or whether somebody is holding the wrong grade. They are very")
        print("  different bugs, and only one of them is the one this lesson is about.")
    print("═" * 74 + "\n")


if __name__ == "__main__":
    _run()
