/* ═══════════════════════════════════════════════════════════════════════════
 *  A2b · THE LINKED LIST — programming homework
 *  9618 §19.1   ·   this is the Paper 4 half of the lesson
 * ═══════════════════════════════════════════════════════════════════════════
 *
 *  HOW TO SUBMIT
 *      Rename this FILE to           A02b_YourName.java
 *      Change nothing inside it.     (The class is not public, so Java does
 *                                     not care what the file is called.)
 *      Submit that one file.
 *
 *  WHICH QUESTIONS ARE IN HERE
 *      The printed sheet is questions 1 to 6. This file is questions 7 to 10.
 *      Together they are one piece of homework with one set of numbers, so
 *      "question 8" means the same thing whichever half you are holding.
 *
 *  HOW TO WORK
 *      Fill in each question where it says YOUR CODE HERE, then run the file.
 *      The tests at the bottom run themselves and tell you, per task, what
 *      passed and what did not. Keep going until the warm-up and questions
 *      7-9 are all green. Question 10 is optional.
 *
 *  WHAT IS MARKED
 *      Questions 7-9 (13 marks), against the published Cambridge mark points
 *      for November 2024 Paper 41 Question 3. Marks are for the METHOD being
 *      visibly right, so:
 *
 *        * code that does not compile can still earn marks — submit it anyway;
 *        * code that passes every test can still lose marks if it passes for
 *          the wrong reason.
 *
 *      The warm-up is not marked. Question 10 is not marked. Do them anyway.
 *
 *  THE RULES
 *      One fixed-size 2D array and integer indices. Nothing else.
 *      BANNED:  java.util.LinkedList   ArrayList   any List or Collection
 *      Importing a LinkedList to implement a linked list answers a
 *      different question.
 *
 *  THE TEST YOU CANNOT FAKE
 *      After every operation the tests check that the list chain and the
 *      free chain together cover ALL EIGHT indices, exactly once each.
 *      Lose a node — forget to hand it back, or read FirstEmpty's pointer
 *      after overwriting it — and the chains stop adding up. Nothing crashes
 *      when that happens. The invariant check is how it gets caught anyway.
 * ═══════════════════════════════════════════════════════════════════════════ */

class A02b {

    static final int SIZE = 8;          // small on purpose: it fills up fast

    /* The linked list lives in ONE 2D array:
     *     LinkedList[i][0]   the DATA in node i          (-1 = no data)
     *     LinkedList[i][1]   the INDEX of the next node  (-1 = end of chain)
     *
     * FirstNode   index of the first node of the list       (-1 = empty)
     * FirstEmpty  index of the first node of the FREE list  (-1 = full)
     *
     * The array starts as one long free chain: an "empty" linked list is
     * not empty — it is a full list of nothing.                            */
    static int[][] LinkedList = new int[SIZE][2];
    static int FirstNode  = -1;
    static int FirstEmpty = 0;


    /* ═══════════════════════════════════════════════════════════════════
     *  WARM-UP  (not marked — two minutes, and it makes the rest easier)
     * ═══════════════════════════════════════════════════════════════════
     *
     *  Two tiny methods. Neither changes anything; they only report.
     *  Write them and you will have said, in code, what "empty" means and
     *  what "walk the chain" means — which is most of questions 7 and 9.
     */

    /** true when the list holds no data at all. */
    static boolean IsEmpty() {
        // YOUR CODE HERE
        return false;
    }

    /** how many nodes are in the list. There is no counter variable
     *  anywhere — a linked list does not keep one. Walk and count. */
    static int CountNodes() {
        // YOUR CODE HERE  (replace this placeholder -- -999 is not a real answer)
        return -999;
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 7  ·  OutputList      [2 marks]   ← Nov 2024 P41 Q3(c)(i)
     * ═══════════════════════════════════════════════════════════════════
     *  Output the data in the linked list IN LIST ORDER, one item per line,
     *  by following the pointers from FirstNode.
     *
     *  (Print nothing at all if the list is empty.)
     */
    static void OutputList() {
        // YOUR CODE HERE
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 8  ·  InsertNode      [6 marks]   ← Nov 2024 P41 Q3(b)
     * ═══════════════════════════════════════════════════════════════════
     *  Insert NewData at the FRONT of the list.
     *      return true   if it was stored
     *      return false  if there is no free node left
     *
     *  Four things must happen, and one of them must happen FIRST:
     *      take the node at FirstEmpty · point it at the old FirstNode ·
     *      make it the new FirstNode · move FirstEmpty on.
     *  The published scheme has a bullet that says, in as many words:
     *  read the next-empty index BEFORE any update to that pointer.
     */
    static boolean InsertNode(int NewData) {
        // YOUR CODE HERE
        return false;
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 9  ·  RemoveNode      [5 marks]   ← Nov 2024 P41 Q3(d)(i)
     * ═══════════════════════════════════════════════════════════════════
     *  Remove the FIRST node whose data is Item.
     *      return true   if one was removed
     *      return false  if Item is not in the list
     *
     *  Three cases, and only the first is easy:
     *      the head · further along (remember the node BEFORE it) ·
     *      not there at all (do nothing, and say so).
     *
     *  Whichever case: set the removed node's data to -1 and hand the node
     *  back to the free list. A node dropped on the floor is gone for the
     *  rest of the run — and the tests will tell you exactly which one.
     */
    static boolean RemoveNode(int Item) {
        // YOUR CODE HERE  (replace this placeholder -- true is deliberately WRONG)
        return true;
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 10  ·  🍦 AddToEnd    (optional, not marked)
     * ═══════════════════════════════════════════════════════════════════
     *  June 2021 P41 examined the OTHER insertion policy: add at the END.
     *      return true   if stored,   false  if there is no free node
     *
     *  You have to walk to the last node first — unless the list is empty,
     *  which is the case that catches everyone. Both chains must survive,
     *  exactly as before; the tests check.
     */
    static boolean AddToEnd(int NewData) {
        // OPTIONAL
        return false;
    }


    /* ────────────────────────────────────────────────────────────────────
     *  THE TESTS — you do not need to read past here, but you may.
     *  Run the file; the report tells you what to fix next.
     * ──────────────────────────────────────────────────────────────────── */

    static void reset() {
        LinkedList = new int[SIZE][2];
        for (int i = 0; i < SIZE; i++) { LinkedList[i][0] = -1; LinkedList[i][1] = i + 1; }
        LinkedList[SIZE - 1][1] = -1;
        FirstNode = -1;
        FirstEmpty = 0;
    }

    static String why = "";

    /** follow a chain; null if it loops or leaves the array */
    static int[] chain(int start) {
        int[] seen = new int[SIZE + 1];
        int n = 0, cur = start;
        while (cur != -1) {
            if (cur < 0 || cur >= SIZE || n > SIZE) return null;
            for (int k = 0; k < n; k++) if (seen[k] == cur) return null;
            seen[n++] = cur;
            cur = LinkedList[cur][1];
        }
        int[] out = new int[n];
        System.arraycopy(seen, 0, out, 0, n);
        return out;
    }

    /** the invariant: both chains together cover every index exactly once */
    static boolean partition() {
        int[] a = chain(FirstNode), b = chain(FirstEmpty);
        if (a == null) { why = "the LIST chain loops or runs off the array"; return false; }
        if (b == null) { why = "the FREE chain loops or runs off the array"; return false; }
        int[] c = new int[SIZE];
        for (int i : a) c[i]++;
        for (int i : b) c[i]++;
        for (int i = 0; i < SIZE; i++) {
            if (c[i] == 2) { why = "node " + i + " is in BOTH chains — the next insert will overwrite live data"; return false; }
            if (c[i] == 0) { why = "node " + i + " belongs to NEITHER chain — it has been lost for the rest of the run"; return false; }
        }
        return true;
    }

    static int[] listVals() {
        int[] ch = chain(FirstNode);
        if (ch == null) return null;
        int[] v = new int[ch.length];
        for (int i = 0; i < ch.length; i++) v[i] = LinkedList[ch[i]][0];
        return v;
    }
    static boolean same(int[] a, int[] b) {
        if (a == null || b == null || a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) if (a[i] != b[i]) return false;
        return true;
    }
    static String show(int[] a) {
        if (a == null) return "null";
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < a.length; i++) { if (i > 0) s.append(", "); s.append(a[i]); }
        return s.append("]").toString();
    }
    /** set a test up; false = InsertNode is not storing yet, report NOT REACHED */
    static boolean fill(int[] vals) {
        for (int v : vals) if (!InsertNode(v)) return false;
        int[] want = new int[vals.length];
        for (int i = 0; i < vals.length; i++) want[i] = vals[vals.length - 1 - i];
        return same(listVals(), want);
    }
    /** run OutputList and capture what it printed */
    static String captured() {
        java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        java.io.PrintStream old = System.out;
        System.setOut(new java.io.PrintStream(buf));
        try { OutputList(); } finally { System.setOut(old); }
        return buf.toString().trim();
    }

    static int pass = 0, total = 0;
    static java.util.List<String> report = new java.util.ArrayList<>();

    static void check(String group, String label, int state, String detail) {
        total++;
        if (state == 1) { pass++; report.add("  ✓  " + group + "  " + label); }
        else if (state == 0) { report.add("  ✗  " + group + "  " + label + (detail.isEmpty() ? "" : "   — " + detail)); }
        else { report.add("  ·  " + group + "  " + label + "   — not reached: " + detail); }
    }

    public static void main(String[] args) {

        // ── warm-up (not marked, still reported) ──────────────────────────
        reset();
        boolean w1 = IsEmpty();
        reset(); InsertNode(5);
        boolean w2ok = fillProbe() && !IsEmpty();
        report.add((w1 && w2ok ? "  ✓" : "  ·") + "  warm-up  IsEmpty says empty when empty, not-empty after an insert"
                 + (w1 && w2ok ? "" : "   (finish the warm-up first — it is two lines)"));
        reset();
        if (fillProbe3()) {
            boolean w3 = CountNodes() == 3;
            report.add((w3 ? "  ✓" : "  ·") + "  warm-up  CountNodes walks and counts (3 nodes → 3)");
        } else {
            report.add("  ·  warm-up  CountNodes — not reached: InsertNode is not storing yet");
        }

        // ── QUESTION 7 · OutputList ───────────────────────────────────────
        reset();
        if (!fill(new int[]{5, 1, 2})) {
            check("Q7", "outputs the list in LIST order", -1, "InsertNode is not storing yet");
            check("Q7", "prints nothing on an empty list", -1, "InsertNode is not storing yet");
        } else {
            String got = captured().replaceAll("\\s+", " ");
            check("Q7", "outputs the list in LIST order (2 1 5)", got.equals("2 1 5") ? 1 : 0,
                  got.isEmpty() ? "it printed nothing" : "it printed \"" + got + "\"");
            reset();
            check("Q7", "prints nothing on an empty list", captured().isEmpty() ? 1 : 0,
                  "an empty list must print nothing at all");
        }

        // ── QUESTION 8 · InsertNode ───────────────────────────────────────
        reset();
        check("Q8", "first insert returns true and the list reads [10]",
              (InsertNode(10) && same(listVals(), new int[]{10})) ? 1 : 0, "");
        reset();
        if (!fill(new int[]{10, 20, 30})) {
            check("Q8", "inserting at the FRONT reverses the order", 0,
                  "after 10, 20, 30 the list should read [30, 20, 10], got " + show(listVals()));
            // an untouched array passes the partition trivially, so this check is
            // only evidence once inserts have actually happened
            check("Q8", "both chains still cover all 8 nodes", -1, "nothing was stored yet");
        } else {
            check("Q8", "inserting at the FRONT reverses the order (30 20 10)", 1, "");
            boolean okpart = partition();
            check("Q8", "both chains still cover all 8 nodes", okpart ? 1 : 0, okpart ? "" : why);
        }
        reset();
        boolean all8 = true;
        for (int i = 0; i < SIZE; i++) if (!InsertNode(i)) { all8 = false; break; }
        if (!all8) {
            check("Q8", "all 8 nodes can be used", 0, "InsertNode refused with room still left");
            check("Q8", "a FULL list refuses and is unchanged", -1, "could not fill the list");
        } else {
            check("Q8", "all 8 nodes can be used", 1, "");
            int[] before = listVals();
            boolean refuse = !InsertNode(99) && same(listVals(), before) && partition();
            check("Q8", "a FULL list refuses and is unchanged", refuse ? 1 : 0,
                  refuse ? "" : "the 9th insert must return false and change nothing");
        }

        // ── QUESTION 9 · RemoveNode ───────────────────────────────────────
        reset();
        if (!fill(new int[]{1, 2, 3})) {
            check("Q9", "removes the HEAD node", -1, "InsertNode is not storing yet");
            check("Q9", "removes a MIDDLE node (the predecessor re-points)", -1, "InsertNode is not storing yet");
            check("Q9", "removing a missing item returns false, changes nothing", -1, "InsertNode is not storing yet");
            check("Q9", "removed nodes go back to the free list and can be reused", -1, "InsertNode is not storing yet");
        } else {
            boolean h = RemoveNode(3) && same(listVals(), new int[]{2, 1}) && partition();
            check("Q9", "removes the HEAD node", h ? 1 : 0, h ? "" : (why.isEmpty() ? "expected [2, 1], got " + show(listVals()) : why));
            reset(); fill(new int[]{1, 2, 3});
            why = "";
            boolean m = RemoveNode(2) && same(listVals(), new int[]{3, 1}) && partition();
            check("Q9", "removes a MIDDLE node (the predecessor re-points)", m ? 1 : 0,
                  m ? "" : (why.isEmpty() ? "expected [3, 1], got " + show(listVals()) : why));
            reset(); fill(new int[]{1, 2, 3});
            int[] before = listVals();
            boolean miss = !RemoveNode(99) && same(listVals(), before);
            check("Q9", "removing a missing item returns false, changes nothing", miss ? 1 : 0, "");
            reset();
            boolean cyc = true;
            outer:
            for (int round = 0; round < 3; round++) {
                for (int i = 0; i < SIZE; i++) if (!InsertNode(100 + i)) { cyc = false; break outer; }
                for (int i = 0; i < SIZE; i++) if (!RemoveNode(100 + i)) { cyc = false; break outer; }
                if (!partition()) { cyc = false; break; }
            }
            check("Q9", "removed nodes go back to the free list and can be reused (24 ops)", cyc ? 1 : 0,
                  cyc ? "" : (why.isEmpty() ? "a deleted node was never handed back" : why));
        }

        // ── the report ────────────────────────────────────────────────────
        System.out.println();
        System.out.println("==============================================================================");
        System.out.println("  A2b · THE LINKED LIST — questions 7 to 9");
        System.out.println("==============================================================================");
        for (String line : report) System.out.println(line);
        System.out.println("------------------------------------------------------------------------------");

        reset();
        boolean scoop;
        try {
            scoop = AddToEnd(4) && AddToEnd(8) && AddToEnd(15)
                 && same(listVals(), new int[]{4, 8, 15}) && partition();
        } catch (Exception e) { scoop = false; }
        System.out.println("  " + (scoop ? "✓" : "·")
            + "  QUESTION 10 🍦 — AddToEnd keeps the order AND both chains"
            + (scoop ? "" : "   (optional — not attempted, or a chain broke)"));

        System.out.println("==============================================================================");
        System.out.println("  " + pass + " / " + total + " checks.");
        if (pass == total) {
            System.out.println("  Nothing was lost, nothing overlapped, and every case answered.");
            System.out.println("  Rename the file to A02b_YourName.java and submit it.");
        } else {
            System.out.println("  Read the FIRST failing row — the later ones are usually its echoes.");
            System.out.println("  Nearly every failure here is one of three things: the deleted node was");
            System.out.println("  never handed back, FirstEmpty's pointer was read after it had been");
            System.out.println("  overwritten, or RemoveNode forgot the node might be the head.");
        }
        System.out.println("==============================================================================");
        System.out.println();
    }

    /* two tiny setup probes for the warm-up rows */
    static boolean fillProbe()  { reset(); return InsertNode(5); }
    static boolean fillProbe3() { reset(); return InsertNode(1) && InsertNode(2) && InsertNode(3) && chain(FirstNode) != null && chain(FirstNode).length == 3; }
}
