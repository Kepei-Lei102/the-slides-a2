"""
🍦 THE ICE-CREAM CHALLENGE — A2c · The Binary Tree
=================================================

One 2D array, three columns, two pointers per node. Nothing else.

    ArrayNodes[i][0]  the LEFT pointer  (index of the smaller side, -1 = none)
    ArrayNodes[i][1]  the DATA
    ArrayNodes[i][2]  the RIGHT pointer (index of the larger side, -1 = none)

    RootPointer   index of the root node          (-1 = empty tree)
    FreeNode      the next unused row — a COUNTER, because this tree
                  never deletes (the exam's own simplification)

Write three subroutines:

    AddNode(NodeData)   -> True/False    insert by the walk: smaller left,
                                         otherwise right; False when full
    SearchTree(Item)    -> index or -1   the same walk, reading instead of writing
    InOrderList()       -> [ ... ]       the data, left · node · right, at every node

HOW TO USE THIS FILE
    1. Fill in the three functions below.
    2. Run the file. The judge runs automatically at the bottom.
    3. Keep going until every row is a ✓.

BANNED — the machine may not do your homework
    sorted()   list.sort()   bisect   any tree/heap module
    (append is allowed in InOrderList ONLY — it is building a report,
     not the data structure.)

THE TEST YOU CANNOT FAKE
    The judge builds 200 trees from random data and walks every one of them
    in order. If your insert obeys the rule at EVERY node, the walk comes out
    sorted — every time, with no answer key anywhere in this file. There is
    no way to pass by accident, and nothing to copy: the property IS the test.

────────────────────────────────────────────────────────────────────────────
"""

SIZE = 20

ArrayNodes = [[-1, -1, -1] for _ in range(SIZE)]
RootPointer = -1              # INTEGER — -1 means the tree is empty
FreeNode = 0                  # INTEGER — the next unused row


def AddNode(NodeData):
    """Insert NodeData. Return True if stored, False if the tree is full.

    The steps, in the exam's own order (Nov 2021 P41):
      · refuse if there is no room
      · write the new node at row FreeNode, both pointers -1
      · empty tree?  RootPointer becomes 0
      · otherwise walk: smaller -> left, otherwise -> right, until the
        pointer you would follow is -1 — then ATTACH by writing FreeNode
        into that pointer of the PARENT node
      · count FreeNode up by one
    """
    global RootPointer, FreeNode
    # YOUR CODE HERE
    return False


def SearchTree(Item):
    """Return the INDEX of the node holding Item, or -1 if it is not there.

    The same walk as AddNode — but reading the pointers instead of
    writing them. No loop over the whole array: that answers a
    different (and slower) question.
    """
    # YOUR CODE HERE  (replace this placeholder -- -999 is not a real answer)
    return -999


def InOrderList():
    """Return the data in sorted order by walking the TREE — left subtree,
    then the node, then the right subtree, at every node.

    Three lines and one of them calls itself. Write it, watch it work,
    and hold the question of WHY that is allowed until next lesson.
    """
    result = [-999]     # placeholder — an impossible answer on purpose
    # YOUR CODE HERE
    return result


# ═══════════════════════════════════════════════════════════════════════
#  SECOND SCOOP (optional) — the shape detector
# ═══════════════════════════════════════════════════════════════════════
#
#  Write TreeHeight(): the number of levels in the tree (0 when empty).
#  Then look at what the judge reports for random data against sorted
#  data — the number it prints IS the lesson's rabbit hole.

def TreeHeight():
    # OPTIONAL
    return -999


# ═══════════════════════════════════════════════════════════════════════
#  THIRD SCOOP 🍨 (hard) — the tree that refuses to become a stick
# ═══════════════════════════════════════════════════════════════════════
#
#  You watched it happen: feed the tree 1–7 in order and every node hangs
#  right — height 7, search O(n), a linked list in a tree costume. The fix
#  has been known since 1962 (Adelson-Velsky & Landis — the AVL tree):
#
#      KEEP THE INVARIANT: at EVERY node, the two subtree heights differ
#      by at most 1. Restore it after every insert by ROTATING.
#
#  For this scoop the 2D array is the wrong tool — a rotation re-wires
#  three pointers at once, and numbered columns make that miserable.
#  Switch to the form June 2024 Paper 42 examines: a class with NAMED
#  pointers. Notice how much easier the same tree suddenly reads.
#
#  The one picture you need — the RIGHT rotation (the left-left case):
#
#            z   <- balance +2                 y
#           / \                              /   \
#          y   D        rotate z right      x     z
#         / \           ------------->     / \   / \
#        x   C                            A   B C   D
#       / \
#      A   B          (the in-order left-to-right reading A x B y C z D
#                      is IDENTICAL before and after — that is why
#                      rotation is legal: it changes shape, never order)
#
#  balance(node) = height(left) − height(right). After an insert, on the
#  way back up, the FIRST node that hits +2 or −2 is fixed by one of four
#  cases:
#      LL  balance +2, new value went left-left     -> rotate right
#      RR  balance −2, right-right                  -> rotate left (mirror)
#      LR  balance +2, left-right   -> rotate the CHILD left first,
#                                      then this node right
#      RL  balance −2, right-left   -> mirror of LR
#
#  Write it RECURSIVELY: AVLInsert receives the root of a subtree and
#  RETURNS the (possibly new) root of that subtree; the caller reattaches
#  whatever comes back. That one idea — insert into a subtree, get back
#  its new root — is the hard version of next lesson. Earn it here and
#  A3 is a victory lap. Keep the tie-break: equal values go right.
#
#  (Real implementations cache each node's height in a field instead of
#   recomputing it — add self.height if you want the grown-up version.
#   Red-black trees solve the same problem with looser balance and gorier
#   case analysis; meet them at university. AVL is the honest first climb.)

class AVLNode:
    def __init__(self, data):
        self.left = None
        self.data = data
        self.right = None


def AVLHeight(node):
    """Levels in the subtree at node — 0 for None. Write this one first;
    it is three lines and one of them calls itself twice."""
    return -999    # OPTIONAL


def AVLInsert(node, data):
    """Insert data into the subtree rooted at node (None = empty subtree),
    rebalance if needed, and RETURN the new root of that subtree.

        Root = AVLInsert(Root, 42)    # this is how every call looks
    """
    return node    # OPTIONAL — the hard scoop


# ────────────────────────────────────────────────────────────────────────────
#  THE JUDGE — you do not need to read past here, but you may
# ────────────────────────────────────────────────────────────────────────────

import random


def _reset():
    global ArrayNodes, RootPointer, FreeNode
    ArrayNodes = [[-1, -1, -1] for _ in range(SIZE)]
    RootPointer = -1
    FreeNode = 0


def _fill(values):
    """Setup helper. None = AddNode is not storing yet -> NOT REACHED."""
    for v in values:
        if AddNode(v) is not True:
            return None
    return True


def _t_root():
    _reset()
    if AddNode(15) is not True:
        return False, "AddNode(15) on an empty tree should return True"
    if RootPointer != 0:
        return False, "the first value must make RootPointer 0, got %r" % (RootPointer,)
    if ArrayNodes[0][1] != 15:
        return False, "row 0 should hold the data 15, got %r" % (ArrayNodes[0][1],)
    return True, ""


def _t_attach():
    _reset()
    if _fill((15, 8, 19)) is None:
        return None, "AddNode is not storing yet"
    if ArrayNodes[0][0] != 1:
        return False, "8 < 15, so row 0's LEFT pointer must become 1, got %r" % (ArrayNodes[0][0],)
    if ArrayNodes[0][2] != 2:
        return False, "19 >= 15, so row 0's RIGHT pointer must become 2, got %r" % (ArrayNodes[0][2],)
    return True, ""


def _t_walk_deeper():
    _reset()
    if _fill((15, 8, 19, 3, 10)) is None:
        return None, "AddNode is not storing yet"
    if ArrayNodes[1][0] != 3 or ArrayNodes[1][2] != 4:
        return False, ("3 and 10 must hang UNDER 8 (row 1: left=3, right=4), got left=%r right=%r "
                       "— the walk has to continue past the first comparison"
                       % (ArrayNodes[1][0], ArrayNodes[1][2]))
    return True, ""


def _t_inorder():
    _reset()
    if _fill((15, 8, 19, 3, 10)) is None:
        return None, "AddNode is not storing yet"
    got = InOrderList()
    if got != [3, 8, 10, 15, 19]:
        return False, "expected [3, 8, 10, 15, 19], got %r" % (got,)
    return True, ""


def _t_search():
    _reset()
    if _fill((15, 8, 19, 3, 10)) is None:
        return None, "AddNode is not storing yet"
    if SearchTree(10) != 4:
        return False, "SearchTree(10) should return its INDEX (4), got %r" % (SearchTree(10),)
    if SearchTree(15) != 0:
        return False, "SearchTree(15) should return 0 — the root counts too"
    if SearchTree(99) != -1:
        return False, "SearchTree(99) must return -1: 99 is not in the tree"
    return True, ""


def _t_full():
    _reset()
    for i in range(SIZE):
        if AddNode(i * 3) is not True:
            return False, "AddNode returned False with room still left (insert %d)" % i
    if AddNode(999) is not False:
        return False, "the tree holds SIZE nodes — the %dst insert must return False" % (SIZE + 1)
    return True, ""


def _t_sorted_input():
    """the degenerate tree must still be CORRECT — just slow"""
    _reset()
    if _fill((1, 2, 3, 4, 5, 6, 7)) is None:
        return None, "AddNode is not storing yet"
    got = InOrderList()
    if got != [1, 2, 3, 4, 5, 6, 7]:
        return False, "sorted input broke the tree: in-order gave %r" % (got,)
    for i in range(6):
        if ArrayNodes[i][2] != i + 1 or ArrayNodes[i][0] != -1:
            return False, ("sorted input must chain every node to the RIGHT — row %d reads %r"
                           % (i, ArrayNodes[i]))
    return True, "and every node hangs right: a list in a tree costume"


def _t_random():
    """200 random trees; the in-order walk must come out sorted, every time."""
    random.seed(20260808)
    for trial in range(200):
        _reset()
        vals = [random.randint(0, 99) for _ in range(random.randint(1, SIZE))]
        for v in vals:
            if AddNode(v) is not True:
                return False, "trial %d: AddNode refused with room left" % trial
        got = InOrderList()
        if got != sorted(vals):
            return False, ("trial %d: inserted %r, the walk gave %r — the rule broke at some node"
                           % (trial, vals, got))
        probe = random.choice(vals)
        if SearchTree(probe) == -1:
            return False, "trial %d: SearchTree(%d) said not-there, but it was inserted" % (trial, probe)
        if SearchTree(-5) != -1:
            return False, "trial %d: SearchTree(-5) must return -1" % trial
    return True, "200 trees, every walk sorted"


def _avl_height(node, seen):
    """The judge's OWN height — never trusts the student's. The seen-set
    catches a rotation bug that wires a node into a cycle."""
    if node is None:
        return 0
    if id(node) in seen:
        raise ValueError("cycle — a rotation re-wired a node into its own subtree")
    seen.add(id(node))
    return 1 + max(_avl_height(node.left, seen), _avl_height(node.right, seen))


def _avl_walk(node, out, cap):
    if node is None or len(out) > cap:
        return
    _avl_walk(node.left, out, cap)
    out.append(node.data)
    _avl_walk(node.right, out, cap)


def _avl_bound(n):
    """The tallest an AVL tree of n nodes can legally be: the smallest
    node-count for height h obeys m(h) = m(h-1) + m(h-2) + 1."""
    a, b, h = 0, 1, 1          # m(0), m(1)
    while b <= n:
        a, b = b, a + b + 1
        h += 1
    return h - 1


def _avl_line():
    try:
        if AVLInsert(None, 5) is None:
            return "·  THIRD SCOOP  — the AVL tree  (hard — not attempted)"
        # the killer case first: the sorted input that built the stick
        root = None
        for v in range(1, 32):
            root = AVLInsert(root, v)
        got = []
        _avl_walk(root, got, 40)
        if got != list(range(1, 32)):
            return ("✗  THIRD SCOOP  — rotations broke the ORDER: in-order gave %r… "
                    "— a legal rotation never changes the left-to-right reading" % (got[:8],))
        h = _avl_height(root, set())
        if h > _avl_bound(31):
            return ("✗  THIRD SCOOP  — 1–31 in order built height %d (AVL allows at most %d): "
                    "the tree is correct but the rotations are not firing" % (h, _avl_bound(31)))
        # then the test you cannot fake
        random.seed(19620101)
        for trial in range(200):
            root, vals = None, [random.randint(0, 999) for _ in range(random.randint(1, 60))]
            for v in vals:
                root = AVLInsert(root, v)
            got = []
            _avl_walk(root, got, len(vals) + 5)
            if got != sorted(vals):
                return "✗  THIRD SCOOP  — trial %d: the walk came out unsorted" % trial
            if _avl_height(root, set()) > _avl_bound(len(vals)):
                return ("✗  THIRD SCOOP  — trial %d: %d nodes at height %d — balanced trees "
                        "may not be that tall" % (trial, len(vals), _avl_height(root, set())))
        return ("✓  THIRD SCOOP  — 1–31 in order: the plain tree is 31 deep, yours is %d. "
                "200 random trees, all sorted, all balanced. The stick is dead. 🍨" % h)
    except Exception as e:
        return "✗  THIRD SCOOP  — crashed: %s: %s" % (type(e).__name__, e)


def _run():
    tests = [
        ("the first value becomes the root", _t_root),
        ("children attach on the correct side", _t_attach),
        ("the walk continues below the first level", _t_walk_deeper),
        ("in-order walk comes out sorted", _t_inorder),
        ("search returns the index, or -1", _t_search),
        ("a full tree refuses", _t_full),
        ("sorted input still works (just slowly)", _t_sorted_input),
        ("200 random trees", _t_random),
    ]
    width = max(len(t[0]) for t in tests)
    passed = 0

    print("\n" + "=" * 78)
    print("  A2c · THE BINARY TREE — the judge")
    print("=" * 78)

    for label, fn in tests:
        try:
            ok, detail = fn()
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
    scoop_line = "·  SECOND SCOOP — TreeHeight   (optional — not attempted)"
    try:
        _reset()
        for v in (10, 5, 15, 3, 7, 12, 20):
            AddNode(v)
        h1 = TreeHeight()
        _reset()
        for v in (1, 2, 3, 4, 5, 6, 7):
            AddNode(v)
        h2 = TreeHeight()
        if h1 == 3 and h2 == 7:
            scoop_line = ("✓  SECOND SCOOP — the same 7 values: height %d balanced, height %d sorted. "
                          "That gap is the whole lesson." % (h1, h2))
    except Exception:
        pass
    print("  " + scoop_line)

    print("  " + _avl_line())

    print("=" * 78)
    if passed == len(tests):
        print("  %d / %d.  Two hundred trees and every walk came out sorted. 🍦" % (passed, len(tests)))
        print("  There is no answer key in this file. Your insert obeyed the rule at")
        print("  every node, and the sorted walks are the proof — that is what")
        print("  self-verifying means, and it is a stronger guarantee than marking.")
    else:
        print("  %d / %d." % (passed, len(tests)))
        print("  Read the first failing row. Nearly every failure here is one of three")
        print("  things: the node was stored but never ATTACHED (write the parent's")
        print("  pointer!), the walk stopped at the first level, or FreeNode was never")
        print("  counted up so every insert lands on the same row.")
    print("=" * 78 + "\n")


if __name__ == "__main__":
    _run()
