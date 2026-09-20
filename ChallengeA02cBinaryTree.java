/* ══════════════════════════════════════════════════════════════════════════
   🍦 THE ICE-CREAM CHALLENGE — A2c · The Binary Tree
   ══════════════════════════════════════════════════════════════════════════

   One 2D array, three columns, two pointers per node. Nothing else.

       ArrayNodes[i][0]  the LEFT pointer  (index of the smaller side, -1 = none)
       ArrayNodes[i][1]  the DATA
       ArrayNodes[i][2]  the RIGHT pointer (index of the larger side, -1 = none)

       RootPointer   index of the root node          (-1 = empty tree)
       FreeNode      the next unused row — a COUNTER, because this tree
                     never deletes (the exam's own simplification)

   Write three methods:

       addNode(int v)      -> true/false    insert by the walk: smaller left,
                                            otherwise right; false when full
       searchTree(int v)   -> index or -1   the same walk, reading not writing
       inOrderList()       -> int[]         the data: left · node · right,
                                            at every node

   HOW TO USE THIS FILE
       1. Fill in the three methods below.
       2. Compile and run:   javac ChallengeA02cBinaryTree.java
                             java  ChallengeA02cBinaryTree
       3. Keep going until every row is a ✓.

   BANNED — the machine may not do your homework
       java.util.TreeMap   TreeSet   PriorityQueue   Arrays.sort
       Collections         any Collection at all

   THE TEST YOU CANNOT FAKE
       The judge builds 200 trees from random data and walks every one in
       order. If your insert obeys the rule at EVERY node, the walk comes
       out sorted — every time, with no answer key anywhere in this file.
       The property IS the test.

   ══════════════════════════════════════════════════════════════════════════ */

public class ChallengeA02cBinaryTree {

    static final int SIZE = 20;

    static int[][] ArrayNodes = new int[SIZE][3];
    static int RootPointer = -1;      // -1 means the tree is empty
    static int FreeNode = 0;          // the next unused row

    // ═══════════════════════════════════════════════════════════════════
    //  YOUR CODE
    // ═══════════════════════════════════════════════════════════════════

    /** Insert v. Return true if stored, false if the tree is full.
     *
     *  The steps, in the exam's own order (Nov 2021 P41):
     *    · refuse if there is no room
     *    · write the new node at row FreeNode, both pointers -1
     *    · empty tree?  RootPointer becomes 0
     *    · otherwise walk: smaller -> left, otherwise -> right, until the
     *      pointer you would follow is -1 — then ATTACH by writing FreeNode
     *      into that pointer of the PARENT node
     *    · count FreeNode up by one                                        */
    static boolean addNode(int v) {
        // YOUR CODE HERE
        return false;
    }

    /** Return the INDEX of the node holding v, or -1 if it is not there.
     *  The same walk, reading the pointers instead of writing them.
     *  No loop over the whole array — that answers a slower question.     */
    static int searchTree(int v) {
        // YOUR CODE HERE  (replace this placeholder -- -999 is not a real answer)
        return -999;
    }

    /** The data in sorted order, by walking the TREE: left subtree, node,
     *  right subtree — at every node. countNodes() below gives you the
     *  length; a method that calls itself does the walking. Watch it work,
     *  and hold the question of WHY that is allowed until next lesson.    */
    static int[] inOrderList() {
        // YOUR CODE HERE
        return new int[]{-999};       // placeholder — an impossible answer on purpose
    }

    /** Free helper: how many nodes are in the tree. (FreeNode already
     *  counts them — nothing is ever deleted.)                            */
    static int countNodes() { return FreeNode; }

    // ═══════════════════════════════════════════════════════════════════
    //  SECOND SCOOP (optional) — the shape detector
    // ═══════════════════════════════════════════════════════════════════
    //  treeHeight(): the number of levels (0 when empty). Then compare what
    //  the judge prints for random data against sorted data — that gap is
    //  the lesson's rabbit hole.

    static int treeHeight() {
        // OPTIONAL
        return -999;
    }

    // ═══════════════════════════════════════════════════════════════════
    //  THIRD SCOOP 🍨 (hard) — the tree that refuses to become a stick
    // ═══════════════════════════════════════════════════════════════════
    //
    //  Feed the tree 1–7 in order and every node hangs right — height 7,
    //  search O(n), a linked list in a tree costume. The fix has been known
    //  since 1962 (Adelson-Velsky & Landis — the AVL tree):
    //
    //      KEEP THE INVARIANT: at EVERY node, the two subtree heights
    //      differ by at most 1. Restore it after every insert by ROTATING.
    //
    //  For this scoop the 2D array is the wrong tool — a rotation re-wires
    //  three pointers at once, and numbered columns make that miserable.
    //  Switch to the form June 2024 Paper 42 examines: a class with NAMED
    //  pointers. Notice how much easier the same tree suddenly reads.
    //
    //  The one picture you need — the RIGHT rotation (the left-left case):
    //
    //            z   <- balance +2                 y
    //           / \                              /   \
    //          y   D        rotate z right      x     z
    //         / \           ------------->     / \   / \
    //        x   C                            A   B C   D
    //       / \
    //      A   B          (the in-order reading A x B y C z D is IDENTICAL
    //                      before and after — that is why rotation is
    //                      legal: it changes shape, never order)
    //
    //  balance(node) = height(left) - height(right). After an insert, on
    //  the way back up, the FIRST node at +2 or -2 is fixed by one of four
    //  cases: LL -> rotate right; RR -> rotate left; LR -> rotate the
    //  CHILD left first, then this node right; RL -> the mirror of LR.
    //
    //  Write it RECURSIVELY: avlInsert receives the root of a subtree and
    //  RETURNS the (possibly new) root of that subtree; the caller
    //  reattaches whatever comes back. Earn that idea here and next
    //  lesson (A3, recursion) is a victory lap. Equal values go right.
    //
    //  (Real implementations cache each node's height in a field instead
    //   of recomputing; add one if you want the grown-up version.
    //   Red-black trees solve the same problem with looser balance and
    //   gorier case analysis — meet them at university. AVL is the honest
    //   first climb.)

    static class AVLNode {
        AVLNode left;
        int data;
        AVLNode right;
        AVLNode(int d) { data = d; }
    }

    /** Levels in the subtree at node — 0 for null. Write this one first:
     *  three lines, and one of them calls itself twice. */
    static int avlHeight(AVLNode node) {
        return -999;   // OPTIONAL
    }

    /** Insert v into the subtree rooted at node (null = empty subtree),
     *  rebalance if needed, and RETURN the new root of that subtree.
     *      root = avlInsert(root, 42);   // this is how every call looks */
    static AVLNode avlInsert(AVLNode node, int v) {
        return node;   // OPTIONAL — the hard scoop
    }

    // ────────────────────────────────────────────────────────────────────
    //  THE JUDGE — you do not need to read past here, but you may
    // ────────────────────────────────────────────────────────────────────

    static void reset() {
        ArrayNodes = new int[SIZE][3];
        for (int i = 0; i < SIZE; i++) { ArrayNodes[i][0] = -1; ArrayNodes[i][1] = -1; ArrayNodes[i][2] = -1; }
        RootPointer = -1;
        FreeNode = 0;
    }

    static String fail = "";
    static final int PASS = 1, FAIL = 0, SKIP = -1;

    static boolean fill(int[] vals) {
        for (int v : vals) if (!addNode(v)) return false;
        return true;
    }
    static String show(int[] a) {
        if (a == null) return "null";
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < a.length; i++) { if (i > 0) s.append(", "); s.append(a[i]); }
        return s.append("]").toString();
    }
    static boolean same(int[] a, int[] b) {
        if (a == null || b == null || a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) if (a[i] != b[i]) return false;
        return true;
    }

    static int tRoot() {
        reset();
        if (!addNode(15)) { fail = "addNode(15) on an empty tree should return true"; return FAIL; }
        if (RootPointer != 0) { fail = "the first value must make RootPointer 0, got " + RootPointer; return FAIL; }
        if (ArrayNodes[0][1] != 15) { fail = "row 0 should hold the data 15, got " + ArrayNodes[0][1]; return FAIL; }
        fail = ""; return PASS;
    }
    static int tAttach() {
        reset();
        if (!fill(new int[]{15, 8, 19})) { fail = "addNode is not storing yet"; return SKIP; }
        if (ArrayNodes[0][0] != 1) { fail = "8 < 15, so row 0's LEFT pointer must become 1, got " + ArrayNodes[0][0]; return FAIL; }
        if (ArrayNodes[0][2] != 2) { fail = "19 >= 15, so row 0's RIGHT pointer must become 2, got " + ArrayNodes[0][2]; return FAIL; }
        fail = ""; return PASS;
    }
    static int tWalkDeeper() {
        reset();
        if (!fill(new int[]{15, 8, 19, 3, 10})) { fail = "addNode is not storing yet"; return SKIP; }
        if (ArrayNodes[1][0] != 3 || ArrayNodes[1][2] != 4) {
            fail = "3 and 10 must hang UNDER 8 (row 1: left=3, right=4), got left=" + ArrayNodes[1][0]
                 + " right=" + ArrayNodes[1][2] + " — the walk has to continue past the first comparison";
            return FAIL; }
        fail = ""; return PASS;
    }
    static int tInOrder() {
        reset();
        if (!fill(new int[]{15, 8, 19, 3, 10})) { fail = "addNode is not storing yet"; return SKIP; }
        int[] got = inOrderList();
        if (!same(got, new int[]{3, 8, 10, 15, 19})) {
            fail = "expected [3, 8, 10, 15, 19], got " + show(got); return FAIL; }
        fail = ""; return PASS;
    }
    static int tSearch() {
        reset();
        if (!fill(new int[]{15, 8, 19, 3, 10})) { fail = "addNode is not storing yet"; return SKIP; }
        if (searchTree(10) != 4) { fail = "searchTree(10) should return its INDEX (4), got " + searchTree(10); return FAIL; }
        if (searchTree(15) != 0) { fail = "searchTree(15) should return 0 — the root counts too"; return FAIL; }
        if (searchTree(99) != -1) { fail = "searchTree(99) must return -1: 99 is not in the tree"; return FAIL; }
        fail = ""; return PASS;
    }
    static int tFull() {
        reset();
        for (int i = 0; i < SIZE; i++)
            if (!addNode(i * 3)) { fail = "addNode returned false with room still left (insert " + i + ")"; return FAIL; }
        if (addNode(999)) { fail = "the tree holds SIZE nodes — the " + (SIZE + 1) + "st insert must return false"; return FAIL; }
        fail = ""; return PASS;
    }
    static int tSorted() {
        reset();
        if (!fill(new int[]{1, 2, 3, 4, 5, 6, 7})) { fail = "addNode is not storing yet"; return SKIP; }
        int[] got = inOrderList();
        if (!same(got, new int[]{1, 2, 3, 4, 5, 6, 7})) {
            fail = "sorted input broke the tree: in-order gave " + show(got); return FAIL; }
        for (int i = 0; i < 6; i++)
            if (ArrayNodes[i][2] != i + 1 || ArrayNodes[i][0] != -1) {
                fail = "sorted input must chain every node to the RIGHT — row " + i + " reads ["
                     + ArrayNodes[i][0] + ", " + ArrayNodes[i][1] + ", " + ArrayNodes[i][2] + "]";
                return FAIL; }
        fail = "and every node hangs right: a list in a tree costume"; return PASS;
    }
    static int tRandom() {
        java.util.Random rnd = new java.util.Random(20260808L);   // seeded: same run every time
        for (int trial = 0; trial < 200; trial++) {
            reset();
            int n = 1 + rnd.nextInt(SIZE);
            int[] vals = new int[n];
            for (int i = 0; i < n; i++) vals[i] = rnd.nextInt(100);
            for (int v : vals)
                if (!addNode(v)) { fail = "trial " + trial + ": addNode refused with room left"; return FAIL; }
            int[] want = vals.clone();
            for (int i = 1; i < want.length; i++) {               // insertion sort — no library calls
                int k = want[i], j = i - 1;
                while (j >= 0 && want[j] > k) { want[j + 1] = want[j]; j--; }
                want[j + 1] = k;
            }
            int[] got = inOrderList();
            if (!same(got, want)) {
                fail = "trial " + trial + ": the walk gave " + show(got) + ", expected " + show(want)
                     + " — the rule broke at some node";
                return FAIL; }
            int probe = vals[rnd.nextInt(n)];
            if (searchTree(probe) == -1) { fail = "trial " + trial + ": searchTree(" + probe + ") said not-there, but it was inserted"; return FAIL; }
            if (searchTree(-5) != -1) { fail = "trial " + trial + ": searchTree(-5) must return -1"; return FAIL; }
        }
        fail = "200 trees, every walk sorted"; return PASS;
    }

    /* ── the AVL judge: computes its OWN heights, never trusts yours ── */

    static int judgeHeight(AVLNode n, java.util.Set<AVLNode> seen) {
        if (n == null) return 0;
        if (!seen.add(n)) throw new IllegalStateException("cycle — a rotation re-wired a node into its own subtree");
        return 1 + Math.max(judgeHeight(n.left, seen), judgeHeight(n.right, seen));
    }
    static void judgeWalk(AVLNode n, java.util.List<Integer> out, int cap) {
        if (n == null || out.size() > cap) return;
        judgeWalk(n.left, out, cap);
        out.add(n.data);
        judgeWalk(n.right, out, cap);
    }
    /** The tallest an AVL tree of n nodes can legally be:
     *  the smallest node-count for height h obeys m(h) = m(h-1)+m(h-2)+1. */
    static int avlBound(int n) {
        int a = 0, b = 1, h = 1;
        while (b <= n) { int c = a + b + 1; a = b; b = c; h++; }
        return h - 1;
    }
    static String avlLine() {
        try {
            if (avlInsert(null, 5) == null)
                return "·  THIRD SCOOP  — the AVL tree  (hard — not attempted)";
            // the killer case first: the sorted input that built the stick
            AVLNode root = null;
            for (int v = 1; v <= 31; v++) root = avlInsert(root, v);
            java.util.List<Integer> got = new java.util.ArrayList<>();
            judgeWalk(root, got, 40);
            for (int i = 0; i < 31; i++)
                if (got.size() != 31 || got.get(i) != i + 1)
                    return "✗  THIRD SCOOP  — rotations broke the ORDER: a legal rotation never changes the left-to-right reading";
            int h = judgeHeight(root, new java.util.HashSet<>());
            if (h > avlBound(31))
                return "✗  THIRD SCOOP  — 1–31 in order built height " + h + " (AVL allows at most " + avlBound(31)
                     + "): the tree is correct but the rotations are not firing";
            // then the test you cannot fake
            java.util.Random rnd = new java.util.Random(19620101L);
            for (int trial = 0; trial < 200; trial++) {
                int n = 1 + rnd.nextInt(60);
                int[] vals = new int[n];
                root = null;
                for (int i = 0; i < n; i++) { vals[i] = rnd.nextInt(1000); root = avlInsert(root, vals[i]); }
                int[] want = vals.clone();
                for (int i = 1; i < want.length; i++) {          // insertion sort — no library calls
                    int k = want[i], j = i - 1;
                    while (j >= 0 && want[j] > k) { want[j + 1] = want[j]; j--; }
                    want[j + 1] = k;
                }
                got = new java.util.ArrayList<>();
                judgeWalk(root, got, n + 5);
                if (got.size() != n) return "✗  THIRD SCOOP  — trial " + trial + ": the walk lost or duplicated nodes";
                for (int i = 0; i < n; i++)
                    if (got.get(i) != want[i]) return "✗  THIRD SCOOP  — trial " + trial + ": the walk came out unsorted";
                int hh = judgeHeight(root, new java.util.HashSet<>());
                if (hh > avlBound(n))
                    return "✗  THIRD SCOOP  — trial " + trial + ": " + n + " nodes at height " + hh
                         + " — balanced trees may not be that tall";
            }
            return "✓  THIRD SCOOP  — 1–31 in order: the plain tree is 31 deep, yours is " + h
                 + ". 200 random trees, all sorted, all balanced. The stick is dead. 🍨";
        } catch (Exception e) {
            return "✗  THIRD SCOOP  — crashed: " + e;
        }
    }

    interface Test { int run(); }

    public static void main(String[] args) {
        String[] names = {
            "the first value becomes the root",
            "children attach on the correct side",
            "the walk continues below the first level",
            "in-order walk comes out sorted",
            "search returns the index, or -1",
            "a full tree refuses",
            "sorted input still works (just slowly)",
            "200 random trees"
        };
        Test[] tests = {
            ChallengeA02cBinaryTree::tRoot,
            ChallengeA02cBinaryTree::tAttach,
            ChallengeA02cBinaryTree::tWalkDeeper,
            ChallengeA02cBinaryTree::tInOrder,
            ChallengeA02cBinaryTree::tSearch,
            ChallengeA02cBinaryTree::tFull,
            ChallengeA02cBinaryTree::tSorted,
            ChallengeA02cBinaryTree::tRandom
        };
        int width = 0;
        for (String s : names) width = Math.max(width, s.length());
        int passed = 0;

        System.out.println();
        System.out.println("==============================================================================");
        System.out.println("  A2c · THE BINARY TREE — the judge");
        System.out.println("==============================================================================");
        for (int i = 0; i < tests.length; i++) {
            int r;
            fail = "";
            try { r = tests[i].run(); }
            catch (Exception e) {
                System.out.printf("  ✗  %-" + width + "s   crashed: %s%n", names[i], e);
                continue;
            }
            if (r == SKIP)      System.out.printf("  ·  %-" + width + "s   not reached — %s%n", names[i], fail);
            else if (r == PASS) { System.out.printf("  ✓  %-" + width + "s   %s%n", names[i], fail); passed++; }
            else                System.out.printf("  ✗  %-" + width + "s   %s%n", names[i], fail);
        }

        System.out.println("------------------------------------------------------------------------------");
        String scoop = "·  SECOND SCOOP — treeHeight   (optional — not attempted)";
        try {
            reset();
            for (int v : new int[]{10, 5, 15, 3, 7, 12, 20}) addNode(v);
            int h1 = treeHeight();
            reset();
            for (int v : new int[]{1, 2, 3, 4, 5, 6, 7}) addNode(v);
            int h2 = treeHeight();
            if (h1 == 3 && h2 == 7)
                scoop = "✓  SECOND SCOOP — the same 7 values: height " + h1 + " balanced, height " + h2
                      + " sorted. That gap is the whole lesson.";
        } catch (Exception e) { }
        System.out.println("  " + scoop);
        System.out.println("  " + avlLine());

        System.out.println("==============================================================================");
        if (passed == tests.length) {
            System.out.println("  " + passed + " / " + tests.length + ".  Two hundred trees and every walk came out sorted. 🍦");
            System.out.println("  There is no answer key in this file. Your insert obeyed the rule at every");
            System.out.println("  node, and the sorted walks are the proof.");
        } else {
            System.out.println("  " + passed + " / " + tests.length + ".");
            System.out.println("  Read the first failing row. Nearly every failure here is one of three");
            System.out.println("  things: the node was stored but never ATTACHED (write the parent's");
            System.out.println("  pointer!), the walk stopped at the first level, or FreeNode was never");
            System.out.println("  counted up so every insert lands on the same row.");
        }
        System.out.println("==============================================================================");
        System.out.println();
    }
}
