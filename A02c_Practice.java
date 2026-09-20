/* ═══════════════════════════════════════════════════════════════════════════
 *  A2c · THE BINARY TREE — programming homework
 *  9618 §19.1   ·   this is the Paper 4 half of the lesson
 * ═══════════════════════════════════════════════════════════════════════════
 *
 *  HOW TO SUBMIT
 *      Rename this FILE to           A02c_YourName.java
 *      Change nothing inside it.     (The class is not public, so Java does
 *                                     not care what the file is called.)
 *      Submit that one file.
 *
 *  WHICH QUESTIONS ARE IN HERE
 *      The printed sheet is questions 1 to 6. This file is questions 7 to 10.
 *      Together they are one piece of homework with one set of numbers.
 *
 *  HOW TO WORK
 *      Fill in each question where it says YOUR CODE HERE, then run the file.
 *      The tests at the bottom run themselves and tell you, per question,
 *      what passed. Keep going until the warm-up and questions 7-9 are all
 *      green. Question 10 is optional.
 *
 *  WHAT IS MARKED
 *      Questions 7-9 (13 marks), against the published Cambridge mark points
 *      for November 2021 Paper 41 Question 3. Marks are for the METHOD being
 *      visibly right, so:
 *
 *        * code that does not compile can still earn marks — submit it anyway;
 *        * code that passes every test can still lose marks if it passes for
 *          the wrong reason.
 *
 *      The warm-up is not marked. Question 10 is not marked. Do them anyway.
 *
 *  THE GIVENS  (the paper's part (a), already done for you below)
 *      An ordered binary tree stores INTEGER data in the 2D array ArrayNodes
 *      of SIZE rows: column 0 the left pointer, column 1 the data, column 2
 *      the right pointer; -1 is the null pointer. RootPointer starts at -1,
 *      FreeNode counts the next unused row from 0. A smaller value goes LEFT;
 *      a value EQUAL to a node's data follows "otherwise" — it goes RIGHT.
 *      Nothing in this homework ever deletes a node, so FreeNode only counts
 *      up. (The paper's subroutines read values with INPUT and pass the array
 *      BYREF; here values arrive as parameters and the array is a global.)
 *
 *  THE RULES
 *      One fixed-size 2D array and integer indices. Nothing else.
 *      BANNED:  java.util.TreeMap  TreeSet  PriorityQueue  Arrays.sort
 *               Collections — any Collection at all
 *      Importing a tree to implement a tree answers a different question.
 *
 *  THE TEST YOU CANNOT FAKE
 *      Two of the tests below have no answer key you can copy. One rebuilds
 *      the EXACT table Cambridge printed in the November 2021 mark scheme
 *      from the paper's own ten values. The other builds 50 trees from
 *      random data — and if your insert obeys the rule at every node, the
 *      in-order walk comes out sorted, every single time.
 * ═══════════════════════════════════════════════════════════════════════════ */

class A02c {

    static final int SIZE = 20;         // the paper's own size

    /* ArrayNodes[i][0] left pointer · [i][1] data · [i][2] right pointer */
    static int[][] ArrayNodes = new int[SIZE][3];
    static int RootPointer = -1;        // -1 means the tree is empty
    static int FreeNode = 0;            // the next unused row — a counter


    /* ═══════════════════════════════════════════════════════════════════
     *  WARM-UP  (not marked — two one-liners, and each one is a fact you
     *            need for the sheet's questions 1 and 6)
     * ═══════════════════════════════════════════════════════════════════ */

    /** true when the tree holds no data at all. One comparison. */
    static boolean IsEmpty() {
        // YOUR CODE HERE
        return false;
    }

    /** how many nodes are in the tree. No loop, no walk — nothing is ever
     *  deleted here, so one of the globals already IS the answer. */
    static int CountNodes() {
        // YOUR CODE HERE  (replace this placeholder -- -999 is not a real answer)
        return -999;
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 7  ·  PrintAll       [2 marks]   ← Nov 2021 P41 Q3(c)
     * ═══════════════════════════════════════════════════════════════════
     *  Print every row of the array IN ARRAY ORDER (row 0 to row SIZE-1),
     *  one row per line, in the order:  left pointer, data, right pointer,
     *  separated by single spaces. Unused rows print as:  -1 -1 -1
     *
     *  Array order, not tree order — this is the debugging view. The tree
     *  view is question 9's job.
     */
    static void PrintAll() {
        // YOUR CODE HERE
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 8  ·  AddNode        [6 marks]   ← Nov 2021 P41 Q3(b)
     * ═══════════════════════════════════════════════════════════════════
     *  Insert NodeData into the tree.
     *      return true   if it was stored
     *      return false  if the tree is full (FreeNode has passed SIZE-1)
     *
     *  The steps, in the exam's own order:
     *      refuse if there is no room · write the new node at row FreeNode,
     *      both pointers -1 · empty tree? RootPointer becomes 0 · otherwise
     *      walk (smaller left, otherwise right) until the pointer you would
     *      follow is -1, then ATTACH by writing FreeNode into that pointer
     *      of the PARENT node · count FreeNode up by one.
     *
     *  The mark the exam expects you to drop: a node stored but never
     *  pointed at is not in the tree. The attach is a write to the PARENT.
     */
    static boolean AddNode(int NodeData) {
        // YOUR CODE HERE
        return false;
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 9  ·  InOrder        [5 marks]   ← Nov 2021 P41 Q3(e)(i)
     * ═══════════════════════════════════════════════════════════════════
     *  Print the data IN ORDER, one value per line, by walking the TREE:
     *  left subtree, then this node, then right subtree — at every node.
     *  Call it as InOrder(RootPointer).
     *
     *  This is the lesson's cliffhanger, typed. The shape is three steps:
     *      if the left pointer is not -1, call InOrder on it;
     *      print this node's data;
     *      if the right pointer is not -1, call InOrder on it.
     *  A method that calls itself. It WILL work — write it, watch it, and
     *  hold the question of WHY it is allowed until next lesson.
     */
    static void InOrder(int Node) {
        // YOUR CODE HERE
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 10  ·  🍦 SearchTree   (optional, not marked)
     * ═══════════════════════════════════════════════════════════════════
     *  The sheet's question 4, as real code: return the INDEX of the row
     *  holding Item, or -1 if it is not in the tree. The same walk as
     *  AddNode — reading the pointers instead of writing them. No loop
     *  over the whole array: that answers a different (slower) question.
     */
    static int SearchTree(int Item) {
        // OPTIONAL  (replace this placeholder -- -999 is not a real answer)
        return -999;
    }


    /* ────────────────────────────────────────────────────────────────────
     *  THE TESTS — you do not need to read past here, but you may.
     *  Run the file; the report tells you what to fix next.
     * ──────────────────────────────────────────────────────────────────── */

    static void reset() {
        ArrayNodes = new int[SIZE][3];
        for (int i = 0; i < SIZE; i++) { ArrayNodes[i][0] = -1; ArrayNodes[i][1] = -1; ArrayNodes[i][2] = -1; }
        RootPointer = -1;
        FreeNode = 0;
    }

    static boolean fill(int[] vals) {
        for (int v : vals) if (!AddNode(v)) return false;
        return true;
    }

    /** run a printing method and capture its lines */
    interface Runnable0 { void run(); }
    static String[] captured(Runnable0 r) {
        java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        java.io.PrintStream old = System.out;
        System.setOut(new java.io.PrintStream(buf));
        try { r.run(); } finally { System.setOut(old); }
        String s = buf.toString().trim();
        return s.isEmpty() ? new String[0] : s.split("\\R+");
    }
    static String norm(String line) { return line.trim().replaceAll("\\s+", " "); }

    /* the exact table Cambridge printed in the Nov 2021 mark scheme, 3(d)(ii) */
    static final int[] PAPER_DATA = {10, 5, 15, 8, 12, 6, 20, 11, 9, 4};
    static final String[] PAPER_ROWS = {
        "1 10 2", "9 5 3", "4 15 6", "5 8 8", "7 12 -1",
        "-1 6 -1", "-1 20 -1", "-1 11 -1", "-1 9 -1", "-1 4 -1"
    };

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
        reset();
        boolean w2 = fill(new int[]{5}) ? !IsEmpty() : false;
        report.add((w1 && w2 ? "  ✓" : "  ·") + "  warm-up  IsEmpty says empty when empty, not-empty after an insert"
                 + (w1 && w2 ? "" : "   (one comparison against RootPointer — and AddNode has to store first)"));
        reset();
        boolean w3 = fill(new int[]{5, 3, 9}) && CountNodes() == 3;
        report.add((w3 ? "  ✓" : "  ·") + "  warm-up  CountNodes (3 inserts → 3, and no loop needed)"
                 + (w3 ? "" : "   (which global already counts every stored node?)"));

        // ── QUESTION 7 · PrintAll (independent of AddNode: rows set by hand) ──
        reset();
        ArrayNodes[0] = new int[]{1, 40, 2};
        ArrayNodes[1] = new int[]{-1, 23, -1};
        ArrayNodes[2] = new int[]{-1, 55, -1};
        RootPointer = 0; FreeNode = 3;
        String[] got = captured(A02c::PrintAll);
        if (got.length == 0) {
            check("Q7", "prints every row, array order, three columns", 0, "it printed nothing");
            check("Q7", "prints all " + SIZE + " rows (unused rows read -1 -1 -1)", 0, "it printed nothing");
        } else {
            boolean firstRows = got.length >= 3 && norm(got[0]).equals("1 40 2")
                             && norm(got[1]).equals("-1 23 -1") && norm(got[2]).equals("-1 55 -1");
            check("Q7", "prints the rows in array order: left, data, right", firstRows ? 1 : 0,
                  firstRows ? "" : "row 0 should print \"1 40 2\", got \"" + norm(got[0]) + "\"");
            boolean all = got.length == SIZE && norm(got[SIZE - 1]).equals("-1 -1 -1");
            check("Q7", "prints all " + SIZE + " rows (unused rows read -1 -1 -1)", all ? 1 : 0,
                  "it printed " + got.length + " line(s) — the published scheme loops through ALL array elements");
        }

        // ── QUESTION 8 · AddNode ──────────────────────────────────────────
        reset();
        boolean r1 = AddNode(15) && RootPointer == 0 && ArrayNodes[0][1] == 15;
        check("Q8", "the first value becomes the root (RootPointer = 0)", r1 ? 1 : 0,
              "after AddNode(15): RootPointer = " + RootPointer + ", row 0 data = " + ArrayNodes[0][1]);
        reset();
        if (!fill(new int[]{15, 8, 19})) {
            check("Q8", "children attach through the PARENT's pointer", -1, "AddNode is not storing yet");
            check("Q8", "the walk continues below the first level", -1, "AddNode is not storing yet");
            check("Q8", "a full tree refuses the 21st insert", -1, "AddNode is not storing yet");
        } else {
            boolean att = ArrayNodes[0][0] == 1 && ArrayNodes[0][2] == 2;
            check("Q8", "children attach through the PARENT's pointer", att ? 1 : 0,
                  "after 15, 8, 19: row 0 must read left=1, right=2 — got left=" + ArrayNodes[0][0] + ", right=" + ArrayNodes[0][2]);
            reset(); fill(new int[]{15, 8, 19, 3, 10});
            boolean deep = ArrayNodes[1][0] == 3 && ArrayNodes[1][2] == 4;
            check("Q8", "the walk continues below the first level", deep ? 1 : 0,
                  "3 and 10 must hang UNDER 8 (row 1: left=3, right=4), got left=" + ArrayNodes[1][0] + ", right=" + ArrayNodes[1][2]);
            reset();
            boolean full = true;
            for (int i = 0; i < SIZE; i++) if (!AddNode(i * 3)) { full = false; break; }
            boolean refuse = full && !AddNode(999);
            check("Q8", "a full tree refuses the 21st insert", refuse ? 1 : 0,
                  full ? "the tree holds SIZE nodes — the next insert must return false"
                       : "AddNode returned false with room still left");
        }

        // ── QUESTION 9 · InOrder ──────────────────────────────────────────
        reset();
        if (!fill(new int[]{15, 8, 19, 3, 10})) {
            check("Q9", "walks left, self, right (3 8 10 15 19)", -1, "AddNode is not storing yet (question 8 first)");
        } else {
            String[] io = captured(() -> InOrder(RootPointer));
            StringBuilder sb = new StringBuilder();
            for (String s : io) sb.append(norm(s)).append(" ");
            String flat = sb.toString().trim();
            check("Q9", "walks left, self, right (3 8 10 15 19)", flat.equals("3 8 10 15 19") ? 1 : 0,
                  flat.isEmpty() ? "it printed nothing" : "it printed \"" + flat + "\"");
        }

        // ── THE PAPER'S OWN DATA · Nov 2021 P41 Q3(d)(ii) ─────────────────
        reset();
        if (!fill(PAPER_DATA)) {
            check("PAPER", "rebuilds the exact table Cambridge printed in the mark scheme", -1, "AddNode is not storing yet");
            check("PAPER", "InOrder gives 4 5 6 8 9 10 11 12 15 20", -1, "AddNode is not storing yet");
        } else {
            String[] rows = captured(A02c::PrintAll);
            boolean tbl = rows.length >= 10;
            String bad = "";
            for (int i = 0; tbl && i < 10; i++)
                if (!norm(rows[i]).equals(PAPER_ROWS[i])) { tbl = false; bad = "row " + i + " should read \"" + PAPER_ROWS[i] + "\", got \"" + norm(rows[i]) + "\""; }
            check("PAPER", "rebuilds the exact table Cambridge printed in the mark scheme", tbl ? 1 : 0,
                  rows.length < 10 ? "PrintAll printed only " + rows.length + " line(s)" : bad);
            String[] io = captured(() -> InOrder(RootPointer));
            StringBuilder sb = new StringBuilder();
            for (String s : io) sb.append(norm(s)).append(" ");
            String flat = sb.toString().trim();
            check("PAPER", "InOrder gives 4 5 6 8 9 10 11 12 15 20", flat.equals("4 5 6 8 9 10 11 12 15 20") ? 1 : 0,
                  flat.isEmpty() ? "InOrder printed nothing" : "it printed \"" + flat + "\"");
        }

        // ── THE TEST YOU CANNOT FAKE · 50 random trees ────────────────────
        java.util.Random rnd = new java.util.Random(20260810L);
        int state = 1; String why = "";
        outer:
        for (int trial = 0; trial < 50; trial++) {
            reset();
            int n = 1 + rnd.nextInt(SIZE);
            int[] vals = new int[n];
            for (int i = 0; i < n; i++) vals[i] = rnd.nextInt(100);
            for (int v : vals) if (!AddNode(v)) { state = -1; why = "AddNode is not storing yet"; break outer; }
            int[] want = vals.clone();
            for (int i = 1; i < want.length; i++) {              // insertion sort — no library calls
                int k = want[i], j = i - 1;
                while (j >= 0 && want[j] > k) { want[j + 1] = want[j]; j--; }
                want[j + 1] = k;
            }
            String[] io = captured(() -> InOrder(RootPointer));
            if (io.length != n) { state = 0; why = "trial " + trial + ": " + n + " values in, " + io.length + " printed out"; break; }
            for (int i = 0; i < n; i++)
                if (Integer.parseInt(norm(io[i])) != want[i]) { state = 0; why = "trial " + trial + ": the walk came out unsorted — the rule broke at some node"; break outer; }
        }
        check("FAKE-PROOF", "50 random trees — every in-order walk comes out sorted", state, why);

        // ── the report ────────────────────────────────────────────────────
        System.out.println();
        System.out.println("==============================================================================");
        System.out.println("  A2c · THE BINARY TREE — questions 7 to 9");
        System.out.println("==============================================================================");
        for (String line : report) System.out.println(line);
        System.out.println("------------------------------------------------------------------------------");

        boolean scoop;
        try {
            reset();
            scoop = fill(new int[]{15, 8, 19, 3, 10})
                 && SearchTree(10) == 4 && SearchTree(15) == 0 && SearchTree(99) == -1;
        } catch (Exception e) { scoop = false; }
        System.out.println("  " + (scoop ? "✓" : "·")
            + "  QUESTION 10 🍦 — SearchTree returns the index, or -1"
            + (scoop ? "" : "   (optional — not attempted, or a walk went wrong)"));

        System.out.println("==============================================================================");
        System.out.println("  " + pass + " / " + total + " checks.");
        if (pass == total) {
            System.out.println("  The paper's own table rebuilt, and fifty random walks all sorted.");
            System.out.println("  Rename the file to A02c_YourName.java and submit it.");
        } else {
            System.out.println("  Read the FIRST failing row — the later ones are usually its echoes.");
            System.out.println("  Nearly every failure here is one of three things: the node was stored");
            System.out.println("  but never ATTACHED (the attach is a write to the PARENT's pointer),");
            System.out.println("  the walk stopped at the first level, or FreeNode was never counted up");
            System.out.println("  so every insert lands on the same row.");
        }
        System.out.println("==============================================================================");
        System.out.println();
    }
}
