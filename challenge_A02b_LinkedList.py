"""
🍦 THE ICE-CREAM CHALLENGE — A2b · The Linked List
=================================================

One array. Two lists threaded through it. Nothing else.

    LinkedList[i][0]  is the DATA in node i
    LinkedList[i][1]  is the INDEX of the next node, or -1 for the end

    FirstNode   the index of the first node of the real list  (-1 = empty)
    FirstEmpty  the index of the first node of the FREE list  (-1 = full)

Write three subroutines:

    InsertNode(NewData) -> True/False     insert at the FRONT
    RemoveNode(Item)    -> True/False     remove the FIRST node holding Item
    ListInOrder()       -> [ ... ]        the data, in list order

HOW TO USE THIS FILE
    1. Fill in the three functions below.
    2. Run the file. The judge runs automatically at the bottom.
    3. Keep going until every row is a ✓.

BANNED — the machine may not do your homework
    list.append   list.insert   list.remove   list.pop   del   sorted()
    (append is allowed in ListInOrder ONLY — that one is building a report,
     not the data structure.)
    You get a fixed-size 2D array and integer indices. That is the point:
    Cambridge's exam gives you an array, and so does this file.

THE TEST YOU CANNOT FAKE
    After every single operation the judge checks that the list chain and
    the free chain together cover ALL EIGHT indices, exactly once each.

    Lose a node — forget to hand it back on delete, or read FirstEmpty's
    pointer after you have already overwritten it — and the two chains stop
    adding up. Nothing crashes when that happens. Nothing prints an error.
    Your program keeps working for a while and then eats itself.

    That check is the difference between a linked list that passes a demo
    and one that is actually correct.

────────────────────────────────────────────────────────────────────────────
"""

SIZE = 8                      # deliberately small, so it fills up quickly

# The array starts as ONE list: every node is empty, and they are threaded
# together so that FirstEmpty can walk them. An "empty" linked list is not
# empty — it is a full list of nothing.
LinkedList = [[-1, i + 1] for i in range(SIZE)]
LinkedList[SIZE - 1][1] = -1

FirstNode = -1                # INTEGER — the real list is empty
FirstEmpty = 0                # INTEGER — every node is available


def InsertNode(NewData):
    """Insert NewData at the FRONT of the list.

    Return True if it was stored, False if there is no free node left.

    Four things have to happen, and one of them has to happen FIRST:
      · take the node at FirstEmpty
      · point it at the old FirstNode
      · make it the new FirstNode
      · move FirstEmpty on to the next free node
    """
    global FirstNode, FirstEmpty
    # YOUR CODE HERE
    pass


def RemoveNode(Item):
    """Remove the FIRST node whose data is Item.

    Return True if one was removed, False if Item is not in the list.

    Three cases, and only the first is easy:
      · Item is in the head node
      · Item is further along  (you will need to remember the node BEFORE it)
      · Item is not there at all  -- do nothing, and say so

    Whichever case it is, the node you took out must be handed back to the
    free list. A node you drop on the floor is gone for the rest of the run.
    """
    global FirstNode, FirstEmpty
    # YOUR CODE HERE
    pass


def ListInOrder():
    """Return a Python list of the data, in LIST order — not array order.

    This is the traversal loop from the lesson, appending instead of printing:
        start at FirstNode, take the data, follow the pointer, stop at -1.
    """
    result = [-999]     # placeholder — an impossible answer on purpose, so an
    # YOUR CODE HERE    # untouched file cannot score a tick by accident
    return result


# ═══════════════════════════════════════════════════════════════════════
#  SECOND SCOOP (optional) — the reason linked lists exist
# ═══════════════════════════════════════════════════════════════════════
#
#  Inserting at the front is cheap but it puts the list in reverse order.
#  Write InsertInOrder() so the list stays SORTED ASCENDING at all times.
#
#  You cannot move anything. You have to walk to the right place, remember
#  the node behind you, and thread the new node in between. Getting the
#  first-item and new-smallest cases right is most of the work.
#
#  Leave it as it is if you would rather not; the main judge ignores it.

def InsertInOrder(NewData):
    global FirstNode, FirstEmpty
    # OPTIONAL
    pass


# ────────────────────────────────────────────────────────────────────────────
#  THE JUDGE — you do not need to read past here, but you may
# ────────────────────────────────────────────────────────────────────────────

import random


def _reset():
    global LinkedList, FirstNode, FirstEmpty
    LinkedList = [[-1, i + 1] for i in range(SIZE)]
    LinkedList[SIZE - 1][1] = -1
    FirstNode = -1
    FirstEmpty = 0


def _chain(start):
    """Follow a chain of pointers. Returns None if it loops or leaves the array."""
    seen, cur = [], start
    while cur != -1:
        if not isinstance(cur, int) or cur < 0 or cur >= SIZE:
            return None
        if cur in seen:
            return None                     # a cycle: the chain eats itself
        seen.append(cur)
        cur = LinkedList[cur][1]
        if len(seen) > SIZE:
            return None
    return seen


def _partition():
    """The invariant: the two chains together cover every index exactly once."""
    a, b = _chain(FirstNode), _chain(FirstEmpty)
    if a is None:
        return False, "the LIST chain loops or runs off the array"
    if b is None:
        return False, "the FREE chain loops or runs off the array"
    both = sorted(a + b)
    if both == list(range(SIZE)):
        return True, ""
    missing = [i for i in range(SIZE) if i not in both]
    twice = [i for i in set(a) if i in b]
    if twice:
        return False, ("node %s is in the list AND the free list at the same time — "
                       "the next insert will overwrite live data" % twice[0])
    return False, ("node %s belongs to neither chain — it has been lost, and it is "
                   "never coming back" % missing[0])


def _fill(values):
    """Insert some values for a later test to work on.

    Returns None if InsertNode is not storing yet — a test that cannot even
    be set up must say NOT REACHED rather than blame RemoveNode for it.
    """
    for v in values:
        if InsertNode(v) is not True:
            return None
    if ListInOrder() != list(reversed(list(values))):
        return None
    return True


def _t_empty():
    _reset()
    got = ListInOrder()
    if got != []:
        return False, "an empty list must read back as [], got %r" % (got,)
    if RemoveNode(5) is not False:
        return False, "RemoveNode on an empty list must return False"
    return True, ""


def _t_insert_front():
    _reset()
    if InsertNode(10) is not True:
        return False, "InsertNode(10) on an empty list should return True"
    if ListInOrder() != [10]:
        return False, "after one insert the list should read [10], got %r" % (ListInOrder(),)
    InsertNode(20)
    InsertNode(30)
    if ListInOrder() != [30, 20, 10]:
        return False, ("inserting at the FRONT reverses the order — expected "
                       "[30, 20, 10], got %r" % (ListInOrder(),))
    return True, ""


def _t_partition_after_inserts():
    _reset()
    if _fill((5, 1, 2, 3, 8)) is None:
        return None, "InsertNode is not storing yet"
    ok, why = _partition()
    if not ok:
        return False, why
    return True, "5 inserts, both chains still add up"


def _t_remove_head():
    _reset()
    if _fill((1, 2, 3)) is None:            # list reads 3, 2, 1
        return None, "InsertNode is not storing yet"
    if RemoveNode(3) is not True:
        return False, "RemoveNode(3) should find the HEAD node and return True"
    if ListInOrder() != [2, 1]:
        return False, "after removing the head the list should read [2, 1], got %r" % (ListInOrder(),)
    return _partition()


def _t_remove_middle():
    _reset()
    if _fill((1, 2, 3)) is None:            # list reads 3, 2, 1
        return None, "InsertNode is not storing yet"
    if RemoveNode(2) is not True:
        return False, "RemoveNode(2) should find the middle node and return True"
    if ListInOrder() != [3, 1]:
        return False, ("the node in front of 2 must now point past it — expected "
                       "[3, 1], got %r" % (ListInOrder(),))
    return _partition()


def _t_remove_tail():
    _reset()
    if _fill((1, 2, 3)) is None:
        return None, "InsertNode is not storing yet"
    if RemoveNode(1) is not True:
        return False, "RemoveNode(1) should find the LAST node and return True"
    if ListInOrder() != [3, 2]:
        return False, "after removing the tail the list should read [3, 2], got %r" % (ListInOrder(),)
    return _partition()


def _t_remove_missing():
    _reset()
    if _fill((1, 2, 3)) is None:
        return None, "InsertNode is not storing yet"
    before = ListInOrder()
    if RemoveNode(99) is not False:
        return False, "RemoveNode(99) must return False — 99 is not in the list"
    if ListInOrder() != before:
        return False, "a failed RemoveNode must not change the list"
    return _partition()


def _t_recycle():
    """Every node handed back must become usable again."""
    _reset()
    if _fill(range(SIZE)) is None:
        return None, "InsertNode is not storing yet"
    for i in range(SIZE):
        if RemoveNode(i) is not True:
            return None, "RemoveNode is not removing yet"
    if ListInOrder() != []:
        return False, "after removing everything the list should be empty, got %r" % (ListInOrder(),)
    ok, why = _partition()
    if not ok:
        return False, why
    for i in range(SIZE):
        if InsertNode(100 + i) is not True:
            return False, ("only %d of the %d nodes could be reused — the rest were "
                           "lost when they were deleted" % (i, SIZE))
    return True, "all %d nodes came back" % SIZE


def _t_full():
    _reset()
    for i in range(SIZE):
        if InsertNode(i) is not True:
            return False, "InsertNode returned False with room still left (i=%d)" % i
    before = ListInOrder()
    if InsertNode(999) is not False:
        return False, "InsertNode on a FULL list must return False"
    if ListInOrder() != before:
        return False, "a refused insert must leave the list completely unchanged"
    return _partition()


def _t_fuzz():
    """Five hundred random operations, checking the invariant after every one."""
    _reset()
    model = []
    random.seed(20260808)

    for n in range(500):
        if random.random() < 0.55:
            v = random.randrange(1, 40)
            want = len(model) < SIZE               # decided BEFORE the call
            got = InsertNode(v)
            if bool(got) != want:
                return False, "op %d: InsertNode returned %r, expected %r" % (n, got, want)
            if want:
                model = [v] + model
        else:
            v = random.randrange(1, 40)
            want = v in model
            got = RemoveNode(v)
            if bool(got) != want:
                return False, ("op %d: RemoveNode(%d) returned %r, expected %r"
                               % (n, v, got, want))
            if want:
                model = model[:model.index(v)] + model[model.index(v) + 1:]

        if ListInOrder() != model:
            return False, ("op %d: the list reads %r, expected %r"
                           % (n, ListInOrder(), model))
        ok, why = _partition()
        if not ok:
            return False, "op %d: %s" % (n, why)

    return True, "500 operations, invariant held every time"


def _run():
    tests = [
        ("an empty list reads back empty", _t_empty),
        ("insert at the front reverses the order", _t_insert_front),
        ("both chains still add up after inserting", _t_partition_after_inserts),
        ("remove the HEAD node", _t_remove_head),
        ("remove a MIDDLE node", _t_remove_middle),
        ("remove the LAST node", _t_remove_tail),
        ("removing something that is not there", _t_remove_missing),
        ("deleted nodes come back to the free list", _t_recycle),
        ("a FULL list refuses, and is unchanged", _t_full),
        ("500 random operations", _t_fuzz),
    ]
    width = max(len(t[0]) for t in tests)
    passed = 0

    print("\n" + "=" * 78)
    print("  A2b · THE LINKED LIST — the judge")
    print("=" * 78)

    for label, fn in tests:
        try:
            ok, detail = fn()
        except Exception as e:
            print("  ✗  %-*s   crashed: %s: %s" % (width, label, type(e).__name__, e))
            continue
        if ok is None:
            # a test that could not even be set up must not report a made-up
            # failure further down the chain — that buries the real fault
            print("  ·  %-*s   not reached — %s" % (width, label, detail))
        elif ok:
            print("  ✓  %-*s   %s" % (width, label, detail))
            passed += 1
        else:
            print("  ✗  %-*s   %s" % (width, label, detail))

    print("-" * 78)
    _reset()
    try:
        for v in (30, 10, 40, 20, 50):
            InsertInOrder(v)
        ok_scoop, _ = _partition()
        scoop = ListInOrder() == [10, 20, 30, 40, 50] and ok_scoop
    except Exception:
        scoop = False
    print("  %s  SECOND SCOOP — InsertInOrder keeps the list sorted%s"
          % ("✓" if scoop else "·",
             "" if scoop else "   (optional — not attempted, or the order is wrong)"))

    print("=" * 78)
    if passed == len(tests):
        print("  %d / %d.  Nothing was lost. \U0001f366" % (passed, len(tests)))
        print("  Five hundred operations and the two chains covered all eight nodes")
        print("  every single time. That is the guarantee the exam question is really")
        print("  asking for, and it is the one almost nobody checks.")
    else:
        print("  %d / %d." % (passed, len(tests)))
        print("  Read the first failing row. Nearly every failure here is one of three")
        print("  things: the deleted node was never handed back, FirstEmpty's pointer")
        print("  was read after it had already been overwritten, or RemoveNode forgot")
        print("  that the node it wants might be the head.")
    print("=" * 78 + "\n")


if __name__ == "__main__":
    _run()
