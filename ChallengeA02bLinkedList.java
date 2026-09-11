/* ══════════════════════════════════════════════════════════════════════════
   🍦 THE ICE-CREAM CHALLENGE — A2b · The Linked List
   ══════════════════════════════════════════════════════════════════════════

   One array. Two lists threaded through it. Nothing else.

       LinkedList[i][0]   the DATA in node i
       LinkedList[i][1]   the INDEX of the next node, or -1 for the end

       FirstNode    index of the first node of the real list   (-1 = empty)
       FirstEmpty   index of the first node of the FREE list   (-1 = full)

   Write three methods:

       insertNode(int newData)  -> true/false     insert at the FRONT
       removeNode(int item)     -> true/false     remove the FIRST node holding item
       listInOrder()            -> int[]          the data, in list order

   HOW TO USE THIS FILE
       1. Fill in the three methods below.
       2. Compile and run:   javac ChallengeA02bLinkedList.java
                             java  ChallengeA02bLinkedList
       3. Keep going until every row is a ✓.

   BANNED — the machine may not do your homework
       java.util.LinkedList   java.util.ArrayList   java.util.List
       Collections            Arrays.sort           any Collection at all
       You get a fixed-size 2D int array and integer indices. That is the
       point: Cambridge's exam gives you an array, and so does this file.

   THE TEST YOU CANNOT FAKE
       After every single operation the judge checks that the list chain and
       the free chain together cover ALL EIGHT indices, exactly once each.

       Lose a node — forget to hand it back on delete, or read FirstEmpty's
       pointer after you have already overwritten it — and the two chains
       stop adding up. Nothing throws. Nothing prints an error. The program
       keeps working for a while and then eats itself.

   ══════════════════════════════════════════════════════════════════════════ */

public class ChallengeA02bLinkedList {

    static final int SIZE = 8;              // small on purpose, so it fills up

    /* The array starts as ONE list: every node empty, threaded together so
       FirstEmpty can walk them. An "empty" linked list is not empty — it is
       a full list of nothing. */
    static int[][] LinkedList = new int[SIZE][2];
    static int FirstNode  = -1;
    static int FirstEmpty = 0;

    // ═══════════════════════════════════════════════════════════════════
    //  YOUR CODE
    // ═══════════════════════════════════════════════════════════════════

    /** Insert newData at the FRONT. Return true if stored, false if full.
     *
     *  Four things have to happen, and one of them has to happen FIRST:
     *    · take the node at FirstEmpty
     *    · point it at the old FirstNode
     *    · make it the new FirstNode
     *    · move FirstEmpty on to the next free node                       */
    static boolean insertNode(int newData) {
        // YOUR CODE HERE
        return false;
    }

    /** Remove the FIRST node whose data is item. Return true if one was
     *  removed, false if item is not in the list.
     *
     *  Three cases, and only the first is easy:
     *    · item is in the head node
     *    · item is further along  (remember the node BEFORE it)
     *    · item is not there at all -- do nothing, and say so
     *
     *  Either way the node you took out must be handed back to the free
     *  list. A node dropped on the floor is gone for the rest of the run. */
    static boolean removeNode(int item) {
        // YOUR CODE HERE
        return true;        // placeholder — deliberately the WRONG answer, so an
    }                       // untouched file cannot score a tick by accident

    /** The data, in LIST order — not array order. This is the traversal
     *  loop from the lesson: start at FirstNode, take the data, follow the
     *  pointer, stop at -1.
     *
     *  Return an int[] of exactly the right length. countNodes() below does
     *  the length for you, and walking twice is a perfectly good answer.  */
    static int[] listInOrder() {
        // YOUR CODE HERE
        return new int[]{-999};     // placeholder — an impossible answer on purpose
    }

    /** Free helper: how many nodes are in the list. Walk it — no counter
     *  variable is kept anywhere, because a linked list does not have one. */
    static int countNodes() {
        int n = 0, current = FirstNode, guard = 0;
        while (current != -1 && guard <= SIZE) { n++; current = LinkedList[current][1]; guard++; }
        return n;
    }

    // ═══════════════════════════════════════════════════════════════════
    //  SECOND SCOOP (optional) — the reason linked lists exist
    // ═══════════════════════════════════════════════════════════════════
    //
    //  Inserting at the front is cheap but leaves the list in reverse order.
    //  Write insertInOrder() so the list stays SORTED ASCENDING at all times.
    //
    //  You cannot move anything. Walk to the right place, remember the node
    //  behind you, and thread the new node in between. The two cases that
    //  cost people marks are the empty list and the new smallest item.

    static boolean insertInOrder(int newData) {
        // OPTIONAL
        return false;
    }

    // ────────────────────────────────────────────────────────────────────
    //  THE JUDGE — you do not need to read past here, but you may
    // ────────────────────────────────────────────────────────────────────

    static void reset() {
        LinkedList = new int[SIZE][2];
        for (int i = 0; i < SIZE; i++) { LinkedList[i][0] = -1; LinkedList[i][1] = i + 1; }
        LinkedList[SIZE - 1][1] = -1;
        FirstNode = -1;
        FirstEmpty = 0;
    }

    static String fail = "";

    /** Follow a chain. Returns null if it loops or leaves the array. */
    static int[] chain(int start) {
        int[] seen = new int[SIZE + 1];
        int n = 0, cur = start;
        while (cur != -1) {
            if (cur < 0 || cur >= SIZE || n > SIZE) return null;
            for (int k = 0; k < n; k++) if (seen[k] == cur) return null;   // a cycle
            seen[n++] = cur;
            cur = LinkedList[cur][1];
        }
        int[] out = new int[n];
        System.arraycopy(seen, 0, out, 0, n);
        return out;
    }

    /** The invariant: the two chains together cover every index exactly once. */
    static boolean partition() {
        int[] a = chain(FirstNode), b = chain(FirstEmpty);
        if (a == null) { fail = "the LIST chain loops or runs off the array"; return false; }
        if (b == null) { fail = "the FREE chain loops or runs off the array"; return false; }
        int[] count = new int[SIZE];
        for (int i : a) count[i]++;
        for (int i : b) count[i]++;
        for (int i = 0; i < SIZE; i++) {
            if (count[i] == 2) {
                fail = "node " + i + " is in the list AND the free list at the same time — "
                     + "the next insert will overwrite live data";
                return false;
            }
            if (count[i] == 0) {
                fail = "node " + i + " belongs to neither chain — it has been lost, and it is "
                     + "never coming back";
                return false;
            }
        }
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

    /** Set a test up. Returns false if insertNode is not storing yet, so the
     *  test says NOT REACHED instead of blaming removeNode for it. */
    static boolean fill(int[] values) {
        for (int v : values) if (!insertNode(v)) return false;
        int[] want = new int[values.length];
        for (int i = 0; i < values.length; i++) want[i] = values[values.length - 1 - i];
        return same(listInOrder(), want);
    }

    static final int PASS = 1, FAIL = 0, SKIP = -1;

    static int tEmpty() {
        reset();
        if (!same(listInOrder(), new int[0])) {
            fail = "an empty list must read back as [], got " + show(listInOrder()); return FAIL; }
        if (removeNode(5)) { fail = "removeNode on an empty list must return false"; return FAIL; }
        fail = ""; return PASS;
    }

    static int tInsertFront() {
        reset();
        if (!insertNode(10)) { fail = "insertNode(10) on an empty list should return true"; return FAIL; }
        if (!same(listInOrder(), new int[]{10})) {
            fail = "after one insert the list should read [10], got " + show(listInOrder()); return FAIL; }
        insertNode(20); insertNode(30);
        if (!same(listInOrder(), new int[]{30, 20, 10})) {
            fail = "inserting at the FRONT reverses the order — expected [30, 20, 10], got "
                 + show(listInOrder()); return FAIL; }
        fail = ""; return PASS;
    }

    static int tPartitionAfterInserts() {
        reset();
        if (!fill(new int[]{5, 1, 2, 3, 8})) { fail = "insertNode is not storing yet"; return SKIP; }
        if (!partition()) return FAIL;
        fail = "5 inserts, both chains still add up"; return PASS;
    }

    static int tRemoveHead() {
        reset();
        if (!fill(new int[]{1, 2, 3})) { fail = "insertNode is not storing yet"; return SKIP; }
        if (!removeNode(3)) { fail = "removeNode(3) should find the HEAD node and return true"; return FAIL; }
        if (!same(listInOrder(), new int[]{2, 1})) {
            fail = "after removing the head the list should read [2, 1], got " + show(listInOrder()); return FAIL; }
        return partition() ? PASS : FAIL;
    }

    static int tRemoveMiddle() {
        reset();
        if (!fill(new int[]{1, 2, 3})) { fail = "insertNode is not storing yet"; return SKIP; }
        if (!removeNode(2)) { fail = "removeNode(2) should find the middle node and return true"; return FAIL; }
        if (!same(listInOrder(), new int[]{3, 1})) {
            fail = "the node in front of 2 must now point past it — expected [3, 1], got "
                 + show(listInOrder()); return FAIL; }
        return partition() ? PASS : FAIL;
    }

    static int tRemoveTail() {
        reset();
        if (!fill(new int[]{1, 2, 3})) { fail = "insertNode is not storing yet"; return SKIP; }
        if (!removeNode(1)) { fail = "removeNode(1) should find the LAST node and return true"; return FAIL; }
        if (!same(listInOrder(), new int[]{3, 2})) {
            fail = "after removing the tail the list should read [3, 2], got " + show(listInOrder()); return FAIL; }
        return partition() ? PASS : FAIL;
    }

    static int tRemoveMissing() {
        reset();
        if (!fill(new int[]{1, 2, 3})) { fail = "insertNode is not storing yet"; return SKIP; }
        int[] before = listInOrder();
        if (removeNode(99)) { fail = "removeNode(99) must return false — 99 is not in the list"; return FAIL; }
        if (!same(listInOrder(), before)) { fail = "a failed removeNode must not change the list"; return FAIL; }
        return partition() ? PASS : FAIL;
    }

    static int tRecycle() {
        reset();
        int[] all = new int[SIZE];
        for (int i = 0; i < SIZE; i++) all[i] = i;
        if (!fill(all)) { fail = "insertNode is not storing yet"; return SKIP; }
        for (int i = 0; i < SIZE; i++)
            if (!removeNode(i)) { fail = "removeNode is not removing yet"; return SKIP; }
        if (listInOrder().length != 0) {
            fail = "after removing everything the list should be empty, got " + show(listInOrder()); return FAIL; }
        if (!partition()) return FAIL;
        for (int i = 0; i < SIZE; i++)
            if (!insertNode(100 + i)) {
                fail = "only " + i + " of the " + SIZE + " nodes could be reused — the rest were "
                     + "lost when they were deleted"; return FAIL; }
        fail = "all " + SIZE + " nodes came back"; return PASS;
    }

    static int tFull() {
        reset();
        for (int i = 0; i < SIZE; i++)
            if (!insertNode(i)) { fail = "insertNode returned false with room still left (i=" + i + ")"; return FAIL; }
        int[] before = listInOrder();
        if (insertNode(999)) { fail = "insertNode on a FULL list must return false"; return FAIL; }
        if (!same(listInOrder(), before)) {
            fail = "a refused insert must leave the list completely unchanged"; return FAIL; }
        return partition() ? PASS : FAIL;
    }

    /** Five hundred random operations, checking the invariant after every one. */
    static int tFuzz() {
        reset();
        int[] model = new int[SIZE];
        int len = 0;
        java.util.Random rnd = new java.util.Random(20260808L);   // seeded: same run every time

        for (int n = 0; n < 500; n++) {
            if (rnd.nextDouble() < 0.55) {
                int v = 1 + rnd.nextInt(39);
                boolean want = len < SIZE;                        // decided BEFORE the call
                boolean got = insertNode(v);
                if (got != want) {
                    fail = "op " + n + ": insertNode returned " + got + ", expected " + want; return FAIL; }
                if (want) {
                    for (int k = len; k > 0; k--) model[k] = model[k - 1];
                    model[0] = v; len++;
                }
            } else {
                int v = 1 + rnd.nextInt(39);
                int at = -1;
                for (int k = 0; k < len; k++) if (model[k] == v) { at = k; break; }
                boolean want = at >= 0;
                boolean got = removeNode(v);
                if (got != want) {
                    fail = "op " + n + ": removeNode(" + v + ") returned " + got + ", expected " + want; return FAIL; }
                if (want) { for (int k = at; k < len - 1; k++) model[k] = model[k + 1]; len--; }
            }
            int[] want = new int[len];
            System.arraycopy(model, 0, want, 0, len);
            if (!same(listInOrder(), want)) {
                fail = "op " + n + ": the list reads " + show(listInOrder()) + ", expected " + show(want);
                return FAIL; }
            if (!partition()) { fail = "op " + n + ": " + fail; return FAIL; }
        }
        fail = "500 operations, invariant held every time"; return PASS;
    }

    interface Test { int run(); }

    public static void main(String[] args) {
        String[] names = {
            "an empty list reads back empty",
            "insert at the front reverses the order",
            "both chains still add up after inserting",
            "remove the HEAD node",
            "remove a MIDDLE node",
            "remove the LAST node",
            "removing something that is not there",
            "deleted nodes come back to the free list",
            "a FULL list refuses, and is unchanged",
            "500 random operations"
        };
        Test[] tests = {
            ChallengeA02bLinkedList::tEmpty,
            ChallengeA02bLinkedList::tInsertFront,
            ChallengeA02bLinkedList::tPartitionAfterInserts,
            ChallengeA02bLinkedList::tRemoveHead,
            ChallengeA02bLinkedList::tRemoveMiddle,
            ChallengeA02bLinkedList::tRemoveTail,
            ChallengeA02bLinkedList::tRemoveMissing,
            ChallengeA02bLinkedList::tRecycle,
            ChallengeA02bLinkedList::tFull,
            ChallengeA02bLinkedList::tFuzz
        };
        int width = 0;
        for (String s : names) width = Math.max(width, s.length());
        int passed = 0;

        System.out.println();
        System.out.println("==============================================================================");
        System.out.println("  A2b · THE LINKED LIST — the judge");
        System.out.println("==============================================================================");

        for (int i = 0; i < tests.length; i++) {
            int r;
            fail = "";
            try { r = tests[i].run(); }
            catch (Exception e) {
                System.out.printf("  ✗  %-" + width + "s   crashed: %s%n", names[i], e);
                continue;
            }
            if (r == SKIP) {
                // a test that could not be set up must not invent a failure further
                // down the chain — that buries the real fault
                System.out.printf("  ·  %-" + width + "s   not reached — %s%n", names[i], fail);
            } else if (r == PASS) {
                System.out.printf("  ✓  %-" + width + "s   %s%n", names[i], fail);
                passed++;
            } else {
                System.out.printf("  ✗  %-" + width + "s   %s%n", names[i], fail);
            }
        }

        System.out.println("------------------------------------------------------------------------------");
        reset();
        boolean scoop;
        try {
            int[] vals = {30, 10, 40, 20, 50};
            for (int v : vals) insertInOrder(v);
            scoop = same(listInOrder(), new int[]{10, 20, 30, 40, 50}) && partition();
        } catch (Exception e) { scoop = false; }
        System.out.println("  " + (scoop ? "✓" : "·")
            + "  SECOND SCOOP — insertInOrder keeps the list sorted"
            + (scoop ? "" : "   (optional — not attempted, or the order is wrong)"));

        System.out.println("==============================================================================");
        if (passed == tests.length) {
            System.out.println("  " + passed + " / " + tests.length + ".  Nothing was lost. 🍦");
            System.out.println("  Five hundred operations and the two chains covered all eight nodes every");
            System.out.println("  single time. That is the guarantee the exam question is really asking");
            System.out.println("  for, and it is the one almost nobody checks.");
        } else {
            System.out.println("  " + passed + " / " + tests.length + ".");
            System.out.println("  Read the first failing row. Nearly every failure here is one of three");
            System.out.println("  things: the deleted node was never handed back, FirstEmpty's pointer was");
            System.out.println("  read after it had already been overwritten, or removeNode forgot that the");
            System.out.println("  node it wants might be the head.");
        }
        System.out.println("==============================================================================");
        System.out.println();
    }
}
