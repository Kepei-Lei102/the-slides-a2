"""
🍦 THE ICE-CREAM CHALLENGE — A6a · Who Chose the Order?
=======================================================

Three drawers, real files, and your own code counting its own reads.

The judge writes two data files of FIXED-WIDTH records (20 bytes each —
6 for the ID, 14 for the name):

    drawers_serial.dat   200 records in ARRIVAL order   (nobody chose it)
    drawers_seq.dat      the same 200, sorted by ID     (the key chose it)

You process them three ways:

    serial_find(sid)          -> (name or None, reads)
    build_index()             -> a small index of drawers_seq.dat
    seq_find(sid, index)      -> (name or None, reads)
    hash_store(f, sid, name)  -> slot used      } your own hashed file,
    hash_find(f, sid)         -> (name, probes) } MOD 64 + linear probing

WHAT MAKES THIS ONE DIFFERENT
    The judge does not time you — A4a taught why milliseconds lie. It checks
    the READS you report against the reads the organisation genuinely needs:
    a serial find of the 137th arrival must count exactly 137, an absent
    search must count all 200, and your index must let seq_find answer
    WITHOUT reading the whole file. Count honestly; the judge recomputes
    every number from the file layout.

THE POINT (it is the lesson's)
    Serial: append free, search dear — absence costs the whole file.
    Sequential: the index + the order let you stop early.
    Random: compute the address, jump, probe past collisions.

🍨 THE SECOND SCOOP — the load-factor curve
    load_curve() fills fresh hash files fuller and fuller (16, 32, 48, 58
    of 64 slots) and returns the average probes a find needs at each load.
    Nobody in this course has named "load factor" yet. Your own numbers
    are about to.

────────────────────────────────────────────────────────────────────────────
"""

import os
import random

REC   = 20          # bytes per record: 6 for the ID, 14 for the name
N     = 200         # records in each data file
SLOTS = 64          # slots in your hashed file


# ═══ YOUR CODE ══════════════════════════════════════════════════════════

def serial_find(sid):
    """Search drawers_serial.dat from the front, one record at a time.

    Read the file RECORD BY RECORD — f.read(REC) — counting every record
    you read. Return (name, reads) when the ID matches, or (None, reads)
    after reading the whole file without a match. Strip the name.
    """
    # YOUR CODE HERE
    return (None, -999)


def build_index():
    """Read drawers_seq.dat ONCE and build a small index: a list of
    (sid, record_number) pairs for every 10th record (records 0, 10, 20…).

    This is the exam's own mechanism: an index of key fields, kept small,
    searched instead of the file.
    """
    # YOUR CODE HERE
    return []


def seq_find(sid, index):
    """Find sid in drawers_seq.dat using the index: pick the LAST index
    entry whose key is <= sid, seek() straight to that record number, and
    read at most 10 records from there.

    Count one read per index entry you examine and one per record you
    read. The file is sorted, so STOP EARLY the moment a key is bigger
    than sid — that is how order proves absence. Return (name, reads) or
    (None, reads).
    """
    # YOUR CODE HERE
    return (None, -999)


def hash_store(fname, sid, name):
    """Store one record in the hashed file fname (SLOTS fixed slots).

    Home slot = sid % SLOTS. If it is taken, probe linearly (wrapping) to
    the next free slot. Write the record with seek(); a free slot is one
    whose first 6 bytes are blank. Return the slot you used.
    """
    # YOUR CODE HERE
    return -999


def hash_find(fname, sid):
    """Fetch by computing the address: seek(home slot), read one record,
    probe onward while the key there is a DIFFERENT id, stop at a match
    (found) or an empty slot (absent — the mark scheme's own bullet).
    Return (name, probes) or (None, probes).
    """
    # YOUR CODE HERE
    return (None, -999)


# ═══ 🍨 THE SECOND SCOOP ════════════════════════════════════════════════

def load_curve():
    """For each load in (16, 32, 48, 58): build a FRESH hashed file, store
    that many records (use the judge's helper `curve_records(load)` for
    the data), then hash_find every stored ID and return the average
    probes per find. Return the four averages as a list of floats.
    """
    # YOUR CODE HERE
    return []


# ════════════════════════════════════════════════════════════════════════
#  THE JUDGE — everything below this line is the marking, not the homework
# ════════════════════════════════════════════════════════════════════════

NAMES = ["Ada", "Grace", "Alan", "Edsger", "Barbara", "Tim", "Vint", "Radia",
         "Donald", "Ken", "Dennis", "Bjarne", "Guido", "James", "Linus", "Margaret"]


def _records():
    rng = random.Random(20260815)
    ids = rng.sample(range(1000, 9999), N)
    return [(sid, NAMES[i % len(NAMES)]) for i, sid in enumerate(ids)]


def _write(fname, recs):
    with open(fname, "w") as f:
        for sid, name in recs:
            f.write(f"{sid:<6}{name:<14}"[:REC])


def _setup():
    recs = _records()
    _write("drawers_serial.dat", recs)                       # arrival order
    _write("drawers_seq.dat", sorted(recs))                  # the key chose
    return recs


def blank_hash_file(fname):
    """Helper for you: a fresh hashed file of SLOTS blank slots."""
    with open(fname, "w") as f:
        f.write(" " * REC * SLOTS)


def curve_records(load):
    """Helper for you: `load` deterministic records for the scoop."""
    rng = random.Random(load)
    ids = rng.sample(range(1000, 9999), load)
    return [(sid, NAMES[i % len(NAMES)]) for i, sid in enumerate(ids)]


import re

def _source():
    try:
        return open(__file__, encoding='utf-8').read()
    except Exception:
        return ""


def _body(name):
    m = re.search(r'^def %s\(.*?\):\n(.*?)(?=^def |^\S|\Z)' % name, _source(), re.S | re.M)
    if not m:
        return None
    b = re.sub(r'""".*?"""', '', m.group(1), flags=re.S)
    return re.sub(r'#.*', '', b)


def _unwritten(name):
    b = _body(name)
    if b is None:
        return True
    return re.sub(r'\s', '', b) in ('pass', 'return(None,-999)', 'return[]', 'return-999', '')


def _t_serial(recs):
    if _unwritten('serial_find'):
        return None, "not written yet"
    for pos in (0, 42, 136, N - 1):
        sid, want = recs[pos]
        name, reads = serial_find(sid)
        if name != want:
            return False, "serial_find(%d) returned %r, the file says %r" % (sid, name, want)
        if reads != pos + 1:
            return False, ("serial_find(%d) reported %r reads. That ID is the record at position %d, "
                           "so an honest front-to-back search reads exactly %d records — count every "
                           "f.read(REC), not lines or guesses." % (sid, reads, pos, pos + 1))
    name, reads = serial_find(4)                             # absent (ids start at 1000)
    if name is not None:
        return False, "serial_find(4) found %r — 4 is not in the file" % (name,)
    if reads != N:
        return False, ("serial_find(4) reported %r reads for an ABSENT id. A serial file has no "
                       "shortcut: proving absence costs all %d records — the mark scheme's own "
                       "bullet." % (reads, N))
    return True, "four finds at their exact read counts, and absence honestly costs all %d" % N


def _t_index(recs):
    if _unwritten('build_index'):
        return None, "not written yet"
    ix = build_index()
    srt = sorted(recs)
    want = [(srt[i][0], i) for i in range(0, N, 10)]
    if list(ix) != want:
        return False, ("the index should be every 10th record of the SORTED file as (sid, record_number) "
                       "— %d entries starting %s; got %d entries starting %s"
                       % (len(want), want[:2], len(ix or []), list(ix or [])[:2]))
    return True, "%d entries, every 10th key of the sorted file" % len(want)


def _t_seq(recs):
    if _unwritten('seq_find') or _unwritten('build_index'):
        return None, "not written yet"
    ix = build_index()
    srt = sorted(recs)
    bound = len(ix) + 11
    for pos in (0, 57, 123, N - 1):
        sid, want = srt[pos]
        name, reads = seq_find(sid, ix)
        if name != want:
            return False, "seq_find(%d) returned %r, the file says %r" % (sid, name, want)
        if not (1 <= reads <= bound):
            return False, ("seq_find(%d) reported %r reads — the index (%d entries) plus at most 10 "
                           "records is %d. Jump with seek(); do not scan the file."
                           % (sid, reads, len(ix), bound))
    name, reads = seq_find(4, ix)                            # absent, below every key
    if name is not None:
        return False, "seq_find(4) found %r — 4 is not in the file" % (name,)
    if reads > bound:
        return False, ("seq_find(4) reported %r reads for an absent id. The file is SORTED: stop at "
                       "the first key bigger than the target — order is what makes absence cheap."
                       % (reads,))
    return True, "index + jump + stop-early, absence included, never past %d reads" % bound


def _t_hash():
    for fn in ('hash_store', 'hash_find'):
        if _unwritten(fn):
            return None, "not written yet"
    blank_hash_file("drawers_hash.dat")
    recs = curve_records(40)
    slots, probes_truth = {}, {}
    for sid, name in recs:                                   # the judge's own simulation
        h = sid % SLOTS
        p = 1
        while h in slots:
            h = (h + 1) % SLOTS
            p += 1
        slots[h] = sid
        probes_truth[sid] = p
    for sid, name in recs:
        got = hash_store("drawers_hash.dat", sid, name)
        want = [k for k, v in slots.items() if v == sid][0]
        if got != want:
            return False, ("hash_store(%d) used slot %r — home slot %d plus linear probing lands on "
                           "%d. Probe one slot at a time, wrapping with MOD."
                           % (sid, got, sid % SLOTS, want))
    for sid, name in recs:
        got_name, probes = hash_find("drawers_hash.dat", sid)
        if got_name != name:
            return False, "hash_find(%d) returned %r, should be %r" % (sid, got_name, name)
        if probes != probes_truth[sid]:
            return False, ("hash_find(%d) reported %r probes; the layout needs exactly %d — count "
                           "every record you read, including the hit." % (sid, probes, probes_truth[sid]))
    got_name, probes = hash_find("drawers_hash.dat", 1)      # absent
    if got_name is not None:
        return False, "hash_find(1) found %r — 1 was never stored" % (got_name,)
    if probes > SLOTS:
        return False, "hash_find(1) probed %r times — an empty slot ends the search" % (probes,)
    return True, "40 records at their exact slots and probe counts, absence stops at an empty slot"


def _t_curve():
    if _unwritten('load_curve'):
        return None, "not written yet"
    for fn in ('hash_store', 'hash_find'):
        if _unwritten(fn):
            return None, "needs the hashed drawer first"
    got = load_curve()
    if not got or len(got) != 4:
        return False, "load_curve() should return four averages, one per load (16, 32, 48, 58)"
    vals = [float(v) for v in got]
    if any(v < 1.0 for v in vals):
        return False, "an average probe count below 1.0 is impossible — every find reads at least once (%r)" % (vals,)
    if not (vals[3] > vals[0] + 0.3):
        return False, ("the curve is too flat (%r). At 58 of 64 slots the drawer is 91%% full and "
                       "probe chains bite — if your numbers do not climb, the finds are not really "
                       "probing." % (vals,))
    if vals[0] > 1.6:
        return False, ("%.2f average probes at only 16/64 full is too many — check the home slot "
                       "calculation (sid %% SLOTS)." % vals[0])
    return True, "avg probes " + " → ".join("%.2f" % v for v in vals) + " as the drawer fills. That curve has a name: load factor"


def _run():
    recs = _setup()
    tests = [("serial — count every read, absence costs the file", lambda: _t_serial(recs)),
             ("the index — small, sorted, every 10th key",         lambda: _t_index(recs)),
             ("sequential — jump, scan, stop early",               lambda: _t_seq(recs)),
             ("random — exact slots, exact probes",                lambda: _t_hash())]
    width = max(len(t[0]) for t in tests) + 2
    passed = 0

    print("\n" + "=" * 78)
    print("  A6a · THE THREE DRAWERS — the judge")
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
        ok, detail = _t_curve()
    except Exception as e:
        ok, detail = False, "crashed: %s: %s" % (type(e).__name__, e)
    print("  %s  SECOND SCOOP 🍨 — the load-factor curve   %s" % ({None: "·", True: "✓", False: "✗"}[ok], detail))

    print("=" * 78)
    if passed == len(tests) and ok:
        print("  %d / %d, and the curve too. Three drawers, three prices, and your own" % (passed, len(tests)))
        print("  numbers just discovered why hash tables are kept part-empty on purpose. 🍦")
    else:
        print("  %d / %d." % (passed, len(tests)))
        print("  Read the first failing row. The two that catch nearly everyone: counting")
        print("  lines instead of records (the files have no newlines — read REC bytes at a")
        print("  time), and a seq_find that quietly scans from record 0 instead of seeking")
        print("  to where the index points.")
    print("=" * 78 + "\n")


if __name__ == "__main__":
    _run()
