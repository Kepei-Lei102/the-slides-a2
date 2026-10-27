"""
🍦 THE ICE-CREAM CHALLENGE — A5a · A Record That Owns Its Verbs
==============================================================

Write one small class. Then defend it.

    Temperature(celsius)      -> a new temperature
    get_celsius()             -> the value
    set_celsius(value)        -> change it, but REFUSE the impossible
    get_fahrenheit()          -> the same temperature, the other way

WHAT MAKES THIS ONE DIFFERENT
    Every other judge in this course asked "does your code give the right
    answer?". This one asks that, and then it ATTACKS. It calls your object in
    long random sequences of perfectly legal calls, and your class passes only
    if there is no way — none at all — to leave it holding a temperature below
    absolute zero.

    That is a stronger claim than "the data is private". It is the claim the
    whole idea rests on: an object that cannot be put into a state its own
    rules forbid.

ABSOLUTE ZERO IS −273.15 °C
    There is no colder temperature. A thermometer reading −300 is not a cold
    day, it is a broken program — and your setter is the only place in the
    universe that can say so.

THE ONE THAT CATCHES PEOPLE
    get_fahrenheit() must NOT store a second attribute. Convert when asked.
    A stored copy is a second source of truth, and the judge will change the
    Celsius and then ask for the Fahrenheit to find out whether you kept one.

────────────────────────────────────────────────────────────────────────────
"""

import random
import re

ABSOLUTE_ZERO = -273.15


# ═══ THE CLASS ══════════════════════════════════════════════════════════

class Temperature:
    """A temperature in degrees Celsius that cannot be made impossible."""

    def __init__(self, celsius):
        """Store the starting value. You may assume it is legal."""
        # YOUR CODE HERE
        pass

    def get_celsius(self):
        """Return the temperature in degrees Celsius."""
        # YOUR CODE HERE
        return -999

    def set_celsius(self, value):
        """Change the temperature — but ONLY if value >= ABSOLUTE_ZERO.

        If it is below absolute zero, leave the object exactly as it was.
        Return True if you accepted it and False if you refused.
        """
        # YOUR CODE HERE
        return None

    def get_fahrenheit(self):
        """Return the SAME temperature in degrees Fahrenheit.

            F = C * 9 / 5 + 32

        Work it out when asked. Do not store it.
        """
        # YOUR CODE HERE
        return -999


# ════════════════════════════════════════════════════════════════════════
#  THE JUDGE — everything below this line is the marking, not the homework
# ════════════════════════════════════════════════════════════════════════

def _source():
    try:
        return open(__file__, encoding='utf-8').read()
    except Exception:
        return ""


def _body(name):
    # the LAST method in the class has no 'def' after it, so the body must also stop
    # at the first line in column 0 -- otherwise it swallows the whole judge and
    # every placeholder looks written
    m = re.search(r'^    def %s\(.*?\):\n(.*?)(?=^    def |^\S|\Z)' % name, _source(), re.S | re.M)
    if not m:
        return None
    b = re.sub(r'""".*?"""', '', m.group(1), flags=re.S)
    return re.sub(r'#.*', '', b)


def _unwritten(name):
    b = _body(name)
    if b is None:
        return True
    return re.sub(r'\s', '', b) in ('pass', 'return-999', 'returnNone', '')


def _t_basics():
    if _unwritten('__init__') or _unwritten('get_celsius'):
        return None, "not written yet"
    for c in (0, 20, 100, -273.15, -40):
        t = Temperature(c)
        got = t.get_celsius()
        if got != c:
            return False, "Temperature(%r).get_celsius() gave %r" % (c, got)
    return True, "five starting values, including absolute zero itself"


def _t_setter():
    if _unwritten('set_celsius'):
        return None, "not written yet"
    t = Temperature(20)
    if t.set_celsius(35) is False or t.get_celsius() != 35:
        return False, "set_celsius(35) should have been accepted, and get_celsius() should then be 35"
    for bad in (-274, -300, -1000, ABSOLUTE_ZERO - 0.01):
        t = Temperature(20)
        r = t.set_celsius(bad)
        if t.get_celsius() != 20:
            return False, ("set_celsius(%r) changed the temperature to %r. Below absolute zero it must "
                           "leave the object exactly as it was." % (bad, t.get_celsius()))
        if r is not False:
            return False, "set_celsius(%r) refused correctly but returned %r — it should return False" % (bad, r)
    t = Temperature(20)
    if t.set_celsius(ABSOLUTE_ZERO) is False:
        return False, "absolute zero itself is a legal temperature — the test is 'below', not 'at or below'"
    return True, "accepts legal values, refuses four impossible ones, and gets the boundary right"


def _t_fahrenheit():
    if _unwritten('get_fahrenheit'):
        return None, "not written yet"
    for c, f in ((0, 32.0), (100, 212.0), (-40, -40.0), (37, 98.6)):
        t = Temperature(c)
        got = t.get_fahrenheit()
        if got is None or abs(got - f) > 0.001:
            return False, "Temperature(%r).get_fahrenheit() gave %r, should be %r" % (c, got, f)
    # the one that catches people: is it DERIVED, or did you store a copy?
    t = Temperature(0)
    t.set_celsius(100)
    got = t.get_fahrenheit()
    if got is None or abs(got - 212.0) > 0.001:
        return False, ("after set_celsius(100) the Fahrenheit reading is %r, not 212. You stored a second "
                       "attribute at construction and it is now stale — a getter should WORK IT OUT." % got)
    return True, "four conversions, and it stays correct after the Celsius changes"


def _t_attack():
    """The second scoop: can any sequence of legal calls break it?"""
    for name in ('__init__', 'get_celsius', 'set_celsius'):
        if _unwritten(name):
            return None, "needs the class finished first"
    rng = random.Random(20260813)
    for run in range(300):
        t = Temperature(rng.uniform(-273.15, 500))
        for _ in range(30):
            t.set_celsius(rng.uniform(-600, 600))
            c = t.get_celsius()
            if c is None or c < ABSOLUTE_ZERO - 1e-9:
                return False, ("broken after %d calls — the object is holding %r, which is below absolute "
                               "zero. Every write must go through the check." % (run, c))
    return True, "300 objects × 30 hostile writes each, and not one ended up impossible"


def _run():
    tests = [("the class stores and returns a temperature", _t_basics),
             ("the setter refuses the impossible", _t_setter),
             ("get_fahrenheit is derived, not stored", _t_fahrenheit)]
    width = max(len(t[0]) for t in tests) + 2
    passed = 0

    print("\n" + "=" * 78)
    print("  A5a · THE LOCKED BOX — the judge")
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
    try:
        ok, detail = _t_attack()
    except Exception as e:
        ok, detail = False, "crashed: %s: %s" % (type(e).__name__, e)
    print("  %s  SECOND SCOOP 🍨 — the attack   %s" % ({None: "·", True: "✓", False: "✗"}[ok], detail))

    print("=" * 78)
    if passed == len(tests) and ok:
        print("  %d / %d, and it survived the attack. You wrote an object that cannot be" % (passed, len(tests)))
        print("  put into a state its own rules forbid — which is the whole claim. 🍦")
    else:
        print("  %d / %d." % (passed, len(tests)))
        print("  Read the first failing row. The two that catch nearly everyone: treating")
        print("  absolute zero itself as illegal (the test is 'below', not 'at or below'),")
        print("  and storing Fahrenheit in the constructor so it goes stale the moment the")
        print("  Celsius changes.")
    print("=" * 78 + "\n")


if __name__ == "__main__":
    _run()
