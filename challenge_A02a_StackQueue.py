"""
🍦 THE ICE-CREAM CHALLENGE — A2a · Stack and Queue
=================================================

Build both structures from an array and some integers. Nothing else.

    Stack :  Push(Data) -> True/False        Pop() -> the item, or -1
    Queue :  Enqueue(Data) -> True/False     Dequeue() -> the item, or -1

These are the signatures Cambridge marks, so learn them here rather than in
the exam hall:

    Push / Enqueue  return  FALSE  when the structure is FULL, TRUE otherwise.
    Pop  / Dequeue  return  -1     when the structure is EMPTY.

HOW TO USE THIS FILE
    1. Fill in the four functions below.
    2. Run the file. The judge runs automatically at the bottom.
    3. Keep going until every row is a ✓.

BANNED — the machine may not do your homework
    collections.deque    queue.Queue    list.pop() with no index
    list.insert()        list.remove()  del
    You get a fixed-size list and integer indices. That is the whole point:
    Cambridge's exam gives you an array, and so does this file.

    (list.append is banned too — the array already exists at full size.
     You move pointers; you never grow anything.)

WHAT THE JUDGE ACTUALLY DOES
    The last test runs TEN THOUSAND random operations against a reference
    model and compares every single return value. A structure that works
    for the first twenty operations and then quietly drifts will be caught
    on operation 3,847 and told exactly which one disagreed.

────────────────────────────────────────────────────────────────────────────
"""

SIZE = 8                      # deliberately small, so it fills up quickly

# ═══════════════════════════════════════════════════════════════════════
#  THE STACK — an array and one integer
# ═══════════════════════════════════════════════════════════════════════

Contents = [-1] * SIZE
Top = -1                      # -1 means empty


def Push(Data):
    """Store Data. Return True if stored, False if the stack is full."""
    global Top
    # YOUR CODE HERE
    pass


def Pop():
    """Return the top item, or -1 if the stack is empty."""
    global Top
    # YOUR CODE HERE
    pass


# ═══════════════════════════════════════════════════════════════════════
#  THE QUEUE — an array and two integers
# ═══════════════════════════════════════════════════════════════════════

Queue = [-1] * SIZE
HeadPointer = -1
TailPointer = -1


def Enqueue(Data):
    """Store Data at the tail. Return True if stored, False if full."""
    global HeadPointer, TailPointer
    # YOUR CODE HERE
    pass


def Dequeue():
    """Return the item at the head, or -1 if the queue is empty."""
    global HeadPointer
    # YOUR CODE HERE
    pass


# ═══════════════════════════════════════════════════════════════════════
#  SECOND SCOOP (optional) — the walking queue
# ═══════════════════════════════════════════════════════════════════════
#
#  The linear queue above has the flaw from the lesson: enqueue and dequeue
#  alternately and it walks up the array until it reports FULL while holding
#  almost nothing.
#
#  Write CircularEnqueue() and CircularDequeue() that reclaim those slots
#  using MOD. You will need one extra variable — count the items — because
#  once the array is a ring, Head == Tail no longer tells you whether it is
#  full or empty.
#
#  Leave these as they are if you would rather not; the main judge ignores
#  them and reports the scoop separately.

CQueue = [-1] * SIZE
CHead = 0
CTail = -1
CCount = 0


def CircularEnqueue(Data):
    global CHead, CTail, CCount
    # OPTIONAL
    pass


def CircularDequeue():
    global CHead, CTail, CCount
    # OPTIONAL
    pass


# ────────────────────────────────────────────────────────────────────────────
#  THE JUDGE — you do not need to read past here, but you may
# ────────────────────────────────────────────────────────────────────────────

import random


def _reset():
    global Contents, Top, Queue, HeadPointer, TailPointer
    global CQueue, CHead, CTail, CCount
    Contents = [-1] * SIZE
    Top = -1
    Queue = [-1] * SIZE
    HeadPointer = -1
    TailPointer = -1
    CQueue = [-1] * SIZE
    CHead = 0
    CTail = -1
    CCount = 0


_rows = []


def _row(label, ok, detail=""):
    _rows.append((label, ok, detail))


def _t_stack_basic():
    _reset()
    if Push(10) is not True:
        return False, "Push(10) on an empty stack should return True"
    Push(20)
    Push(30)
    if Pop() != 30:
        return False, "Pop should return the LAST item pushed (30)"
    if Pop() != 20:
        return False, "second Pop should return 20"
    if Pop() != 10:
        return False, "third Pop should return 10"
    return True, ""


def _t_stack_underflow():
    _reset()
    if Pop() != -1:
        return False, "Pop on an empty stack must return -1"
    Push(5)
    Pop()
    if Pop() != -1:
        return False, "Pop after emptying the stack must return -1 again"
    return True, ""


def _t_stack_overflow():
    _reset()
    for i in range(SIZE):
        if Push(i) is not True:
            return False, "Push returned False with room still left (i=%d)" % i
    if Push(999) is not False:
        return False, "Push on a FULL stack must return False"
    if Pop() != SIZE - 1:
        return False, "after a refused Push, the top must be unchanged"
    return True, ""


def _t_queue_basic():
    _reset()
    Enqueue(10)
    Enqueue(20)
    Enqueue(30)
    if Dequeue() != 10:
        return False, "Dequeue should return the FIRST item enqueued (10)"
    if Dequeue() != 20:
        return False, "second Dequeue should return 20"
    return True, ""


def _t_queue_empty():
    _reset()
    if Dequeue() != -1:
        return False, "Dequeue on an empty queue must return -1"
    Enqueue(7)
    Dequeue()
    if Dequeue() != -1:
        return False, "Dequeue after emptying the queue must return -1 again"
    return True, ""


def _t_queue_full():
    _reset()
    for i in range(SIZE):
        if Enqueue(i) is not True:
            return False, "Enqueue returned False with room still left (i=%d)" % i
    if Enqueue(999) is not False:
        return False, "Enqueue on a FULL queue must return False"
    return True, ""


def _t_fuzz():
    """Ten thousand random operations against a reference model."""
    _reset()
    ref_stack, ref_queue = [], []
    ref_tail = -1              # the reference's own tail; never peek at the student's
    random.seed(20260807)

    for n in range(10000):
        # A LINEAR queue whose tail has reached the end is full forever, so
        # every few hundred operations both structures are emptied and the
        # reference is cleared with them. Otherwise the queue would spend
        # 95% of this test permanently jammed and prove nothing.
        if n % 250 == 0:
            _reset()
            ref_stack, ref_queue, ref_tail = [], [], -1

        which = random.randrange(4)

        if which == 0:
            v = random.randrange(1, 500)
            want = len(ref_stack) < SIZE
            got = Push(v)
            if want:
                ref_stack.append(v)
            if bool(got) != want:
                return False, "op %d: Push returned %r, expected %r" % (n, got, want)

        elif which == 1:
            want = ref_stack.pop() if ref_stack else -1
            got = Pop()
            if got != want:
                return False, "op %d: Pop returned %r, expected %r" % (n, got, want)

        elif which == 2:
            v = random.randrange(1, 500)
            want = ref_tail < SIZE - 1        # decided BEFORE the call
            got = Enqueue(v)
            if want:
                ref_tail += 1
                ref_queue.append(v)
            if bool(got) != want:
                return False, "op %d: Enqueue returned %r, expected %r" % (n, got, want)

        else:
            want = ref_queue.pop(0) if ref_queue else -1
            got = Dequeue()
            if got != want:
                return False, "op %d: Dequeue returned %r, expected %r" % (n, got, want)

    return True, "10000 operations"


def _run():
    tests = [
        ("stack: push and pop in order", _t_stack_basic),
        ("stack: UNDERFLOW returns -1", _t_stack_underflow),
        ("stack: OVERFLOW returns False", _t_stack_overflow),
        ("queue: first in, first out", _t_queue_basic),
        ("queue: EMPTY returns -1", _t_queue_empty),
        ("queue: FULL returns False", _t_queue_full),
        ("10 000 random operations", _t_fuzz),
    ]
    width = max(len(t[0]) for t in tests)
    passed = 0

    print("\n" + "=" * 76)
    print("  A2a · STACK AND QUEUE — the judge")
    print("=" * 76)

    for label, fn in tests:
        try:
            ok, detail = fn()
        except Exception as e:
            print("  \u2717  %-*s   crashed: %s: %s" % (width, label, type(e).__name__, e))
            continue
        if ok:
            print("  \u2713  %-*s   %s" % (width, label, detail))
            passed += 1
        else:
            print("  \u2717  %-*s   %s" % (width, label, detail))

    print("-" * 76)
    _reset()
    try:
        for i in range(SIZE):
            CircularEnqueue(i)
        for i in range(SIZE):
            CircularDequeue()
        for i in range(SIZE):
            CircularEnqueue(100 + i)
        scoop = CircularDequeue() == 100
    except Exception:
        scoop = False
    print("  %s  SECOND SCOOP — the circular queue reclaims its slots%s"
          % ("\u2713" if scoop else "\u00b7",
             "" if scoop else "   (optional — not attempted, or the ring does not wrap)"))

    print("=" * 76)
    if passed == len(tests):
        print("  %d / %d.  Both structures survive everything thrown at them. \U0001f366"
              % (passed, len(tests)))
        print("  The fuzz test alone ran ten thousand operations without a single")
        print("  disagreement. That is a stronger guarantee than any exam will ask for.")
    else:
        print("  %d / %d." % (passed, len(tests)))
        print("  Read the first failing row. Almost every failure in this challenge is")
        print("  one of two things: a missing FULL check, or a missing EMPTY check.")
        print("  The middle of the array is the easy part.")
    print("=" * 76 + "\n")


if __name__ == "__main__":
    _run()
