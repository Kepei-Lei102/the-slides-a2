/* ═══════════════════════════════════════════════════════════════════════════
 *  A3a · RECURSION — programming homework
 *  9618 §19.2   ·   this is the Paper 4 half of the lesson
 * ═══════════════════════════════════════════════════════════════════════════
 *
 *  HOW TO SUBMIT
 *      Rename this FILE to           A03a_YourName.java
 *      Change nothing inside it.     (The class is not public, so Java does
 *                                     not care what the file is called.)
 *      Submit that one file.
 *
 *  WHICH QUESTIONS ARE IN HERE
 *      The printed sheet is questions 1 to 6. This file is questions 7 to 10.
 *
 *  WHAT IS MARKED
 *      Questions 7-9 (13 marks). Question 7 is marked against the published
 *      Cambridge mark points for November 2021 Paper 41 Question 3(d).
 *      Marks are for the METHOD being visibly right, so code that does not
 *      compile can still earn them — submit it anyway.
 *
 *  THE GIVENS  (already done for you below)
 *      An ordered binary tree of INTEGERs sits in the 2D array ArrayNodes:
 *      column 0 the left pointer, 1 the data, 2 the right pointer; -1 is the
 *      null pointer. RootPointer is the index of the root, -1 when empty.
 *      Nothing here deletes, so every used row is 0 .. FreeNode-1.
 *      The tests build the tree for you — you only walk it.
 *
 *  THE RULES
 *      All three marked methods must be RECURSIVE.
 *      BANNED: any loop inside them, and any Collection or library that
 *      walks the tree for you.
 *
 *  THE TEST YOU CANNOT FAKE
 *      Every one of these has a trivial version written with a loop, and a
 *      loop would pass any test that only checks the output. So the tests
 *      READ THIS FILE and look inside your three methods for a `for` or a
 *      `while`, and for the method calling itself. The point of this
 *      homework is the technique, not the numbers.
 * ═══════════════════════════════════════════════════════════════════════════ */

class A03a {

    static final int SIZE = 20;

    /* ArrayNodes[i][0] left pointer · [i][1] data · [i][2] right pointer */
    static int[][] ArrayNodes = new int[SIZE][3];
    static int RootPointer = -1;
    static int FreeNode = 0;


    /* ═══════════════════════════════════════════════════════════════════
     *  WARM-UP  (not marked — one line, and it is the shape of all three)
     * ═══════════════════════════════════════════════════════════════════
     *
     *  IsLeaf(Node): true when the node at this index has no children at
     *  all. No recursion needed — just both pointers. Getting this right
     *  makes questions 8 and 9 much easier to see.
     */
    static boolean IsLeaf(int Node) {
        // YOUR CODE HERE
        return false;
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 7  ·  PostOrder      [7 marks]   ← Nov 2021 P41 Q3(d)-style
     * ═══════════════════════════════════════════════════════════════════
     *  A post-order traversal:
     *      · if the left node is not empty, call yourself with the left node
     *      · if the right node is not empty, call yourself with the right node
     *      · output the current node's data
     *
     *  Print one value per line. Call it as PostOrder(RootPointer).
     *
     *  The published scheme's seven marks are: the header with one
     *  parameter and at least one recursive call · checking the left node
     *  is not -1 · the recursive call on the left · checking the right node
     *  is not -1 · the recursive call on the right · outputting the data ·
     *  and all three in the correct order.
     */
    static void PostOrder(int Node) {
        // YOUR CODE HERE
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 8  ·  CountNodes     [3 marks]
     * ═══════════════════════════════════════════════════════════════════
     *  How many nodes are at or below this one? Return 0 for an empty
     *  subtree (Node = -1).
     *
     *  There is no counter variable anywhere, and you may not add one:
     *  the answer is "one for me, plus however many are on each side".
     */
    static int CountNodes(int Node) {
        // YOUR CODE HERE  (replace this placeholder -- -999 is not a real answer)
        return -999;
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 9  ·  TreeHeight     [3 marks]
     * ═══════════════════════════════════════════════════════════════════
     *  How many LEVELS are there at or below this node? An empty subtree
     *  (Node = -1) has height 0; a single node on its own has height 1.
     *
     *  Same shape as question 8 with one word changed: instead of adding
     *  the two sides together, take whichever is TALLER.
     */
    static int TreeHeight(int Node) {
        // YOUR CODE HERE
        return -999;
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 10  ·  🍦 PreOrder    (optional, not marked)
     * ═══════════════════════════════════════════════════════════════════
     *  The third traversal: output the node FIRST, then the left subtree,
     *  then the right. One line moves, and the whole output changes.
     *
     *  Print one value per line, as before.
     */
    static void PreOrder(int Node) {
        // OPTIONAL
    }


    /* ────────────────────────────────────────────────────────────────────
     *  THE TESTS — you do not need to read past here, but you may.
     * ──────────────────────────────────────────────────────────────────── */

    static void reset() {
        ArrayNodes = new int[SIZE][3];
        for (int i = 0; i < SIZE; i++) { ArrayNodes[i][0] = -1; ArrayNodes[i][1] = -1; ArrayNodes[i][2] = -1; }
        RootPointer = -1;
        FreeNode = 0;
    }

    static void add(int v) {
        ArrayNodes[FreeNode][0] = -1; ArrayNodes[FreeNode][1] = v; ArrayNodes[FreeNode][2] = -1;
        if (RootPointer == -1) RootPointer = 0;
        else {
            int c = RootPointer;
            while (true) {
                if (v < ArrayNodes[c][1]) {
                    if (ArrayNodes[c][0] == -1) { ArrayNodes[c][0] = FreeNode; break; }
                    c = ArrayNodes[c][0];
                } else {
                    if (ArrayNodes[c][2] == -1) { ArrayNodes[c][2] = FreeNode; break; }
                    c = ArrayNodes[c][2];
                }
            }
        }
        FreeNode++;
    }
    static void build(int[] values) { reset(); for (int v : values) add(v); }

    interface Runnable0 { void run(); }
    static String captured(Runnable0 r) {
        java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        java.io.PrintStream old = System.out;
        System.setOut(new java.io.PrintStream(buf));
        try { r.run(); } catch (Exception | StackOverflowError e) { } finally { System.setOut(old); }
        return buf.toString().trim().replaceAll("\\s+", " ");
    }

    /** the body of one method, matched on its DECLARATION line, not on a
     *  mention in a comment (the header above names all three). */
    static String sourceOf(String name) {
        try {
            String src = new String(java.nio.file.Files.readAllBytes(
                    java.nio.file.Paths.get(FILENAME)), "UTF-8");
            java.util.regex.Matcher m = java.util.regex.Pattern
                    .compile("(?m)^\\s*static\\s+[\\w\\[\\]<>, ]+?\\s+" + name + "\\s*\\(")
                    .matcher(src);
            if (!m.find()) return null;
            int open = src.indexOf('{', m.start());
            if (open < 0) return null;
            int depth = 0;
            for (int k = open; k < src.length(); k++) {
                char ch = src.charAt(k);
                if (ch == '{') depth++;
                if (ch == '}') { depth--; if (depth == 0) return src.substring(open, k); }
            }
        } catch (Exception e) { return null; }
        return null;
    }
    static String FILENAME = "A03a_Practice.java";

    static int pass = 0, total = 0;
    static java.util.List<String> report = new java.util.ArrayList<>();
    static void check(String group, String label, int state, String detail) {
        total++;
        if (state == 1) { pass++; report.add("  ✓  " + group + "  " + label); }
        else if (state == 0) { report.add("  ✗  " + group + "  " + label + (detail.isEmpty() ? "" : "   — " + detail)); }
        else { report.add("  ·  " + group + "  " + label + "   — not reached: " + detail); }
    }

    /* Cambridge's own ten values, from Nov 2021 P41 Q3(d)(ii) */
    static final int[] PAPER = {10, 5, 15, 8, 12, 6, 20, 11, 9, 4};

    public static void main(String[] args) {

        /* the file may have been renamed for submission — find it either way */
        java.io.File here = new java.io.File(".");
        String[] found = here.list((d, n) -> n.startsWith("A03a") && n.endsWith(".java"));
        if (found != null && found.length > 0) FILENAME = found[0];

        // ── warm-up ───────────────────────────────────────────────────────
        build(new int[]{40, 23, 55});
        boolean w = IsLeaf(1) && IsLeaf(2) && !IsLeaf(0);
        report.add((w ? "  ✓" : "  ·") + "  warm-up  IsLeaf — a node with both pointers at -1"
                 + (w ? "" : "   (both children must be -1; the root of this tree is not a leaf)"));

        // ── the source check ──────────────────────────────────────────────
        String[] names = {"PostOrder", "CountNodes", "TreeHeight"};
        StringBuilder looped = new StringBuilder(), silent = new StringBuilder();
        int silentCount = 0, unreadable = 0;
        for (String n : names) {
            String body = sourceOf(n);
            if (body == null) { unreadable++; continue; }
            String code = body.replaceAll("(?s)/\\*.*?\\*/", "").replaceAll("//.*", "");
            if (code.matches("(?s).*\\b(for|while)\\b.*")) {
                if (looped.length() > 0) looped.append(", ");
                looped.append(n);
            }
            if (!code.matches("(?s).*\\b" + n + "\\s*\\(.*")) {
                if (silent.length() > 0) silent.append(", ");
                silent.append(n); silentCount++;
            }
        }
        if (unreadable > 0)
            check("SRC", "all three are written recursively", -1, "could not read " + FILENAME + " to check the source");
        else if (silentCount == 3)
            check("SRC", "all three are written recursively", -1, "none of the three call themselves yet — start with question 7");
        else if (silent.length() > 0)
            check("SRC", "all three are written recursively", 0, silent + " never calls itself, so whatever it does, it is not recursion");
        else if (looped.length() > 0)
            check("SRC", "all three are written recursively", 0, "a loop is hiding in " + looped + " — all three of these are recursion exercises");
        else
            check("SRC", "all three are written recursively", 1, "");

        // ── QUESTION 7 · PostOrder ────────────────────────────────────────
        build(new int[]{40, 23, 55, 8, 31, 60});
        String got = captured(() -> PostOrder(RootPointer));
        if (got.isEmpty()) {
            check("Q7", "post-order of the sheet's tree (8 31 23 60 55 40)", 0, "it printed nothing");
            check("Q7", "Cambridge's ten values", -1, "PostOrder prints nothing yet");
        } else {
            check("Q7", "post-order of the sheet's tree (8 31 23 60 55 40)",
                  got.equals("8 31 23 60 55 40") ? 1 : 0, "it printed \"" + got + "\"");
            build(PAPER);
            String p = captured(() -> PostOrder(RootPointer));
            check("Q7", "Cambridge's ten values (4 6 9 8 5 11 12 20 15 10)",
                  p.equals("4 6 9 8 5 11 12 20 15 10") ? 1 : 0, "it printed \"" + p + "\"");
        }
        build(new int[]{7});
        String one = captured(() -> PostOrder(RootPointer));
        check("Q7", "a tree of one node prints just that node", one.equals("7") ? 1 : 0,
              one.isEmpty() ? "it printed nothing" : "it printed \"" + one + "\"");

        // ── QUESTION 8 · CountNodes ───────────────────────────────────────
        build(new int[]{40, 23, 55, 8, 31, 60});
        check("Q8", "counts the sheet's tree (6)", CountNodes(RootPointer) == 6 ? 1 : 0,
              "got " + CountNodes(RootPointer));
        build(PAPER);
        check("Q8", "counts Cambridge's ten values (10)", CountNodes(RootPointer) == 10 ? 1 : 0,
              "got " + CountNodes(RootPointer));
        check("Q8", "an empty subtree counts 0", CountNodes(-1) == 0 ? 1 : 0,
              "CountNodes(-1) gave " + CountNodes(-1) + " — that is the base case");

        // ── QUESTION 9 · TreeHeight ───────────────────────────────────────
        build(new int[]{40, 23, 55, 8, 31, 60});
        check("Q9", "height of the sheet's tree (3)", TreeHeight(RootPointer) == 3 ? 1 : 0,
              "got " + TreeHeight(RootPointer));
        build(PAPER);
        check("Q9", "height of Cambridge's ten values (4)", TreeHeight(RootPointer) == 4 ? 1 : 0,
              "got " + TreeHeight(RootPointer));
        build(new int[]{1, 2, 3, 4, 5, 6, 7});
        check("Q9", "sorted input makes a stick: height 7 for 7 nodes", TreeHeight(RootPointer) == 7 ? 1 : 0,
              "got " + TreeHeight(RootPointer) + " — every node hangs right, so the height IS the count");

        // ── the report ────────────────────────────────────────────────────
        System.out.println();
        System.out.println("==============================================================================");
        System.out.println("  A3a · RECURSION — questions 7 to 9");
        System.out.println("==============================================================================");
        for (String line : report) System.out.println(line);
        System.out.println("------------------------------------------------------------------------------");

        build(new int[]{40, 23, 55, 8, 31, 60});
        String pre = captured(() -> PreOrder(RootPointer));
        System.out.println("  " + (pre.equals("40 23 8 31 55 60") ? "✓" : "·")
            + "  QUESTION 10 🍦 — PreOrder gives 40 23 8 31 55 60"
            + (pre.equals("40 23 8 31 55 60") ? "  (node first, and the root leads)"
                                              : "   (optional — not attempted, or the print is in the wrong place)"));

        System.out.println("==============================================================================");
        System.out.println("  " + pass + " / " + total + " checks.");
        if (pass == total) {
            System.out.println("  Three recursive walks, and the machine read your source to be sure they");
            System.out.println("  were recursive. Rename the file to A03a_YourName.java and submit it.");
        } else {
            System.out.println("  Read the FIRST failing row. Nearly every failure here is one of three");
            System.out.println("  things: the base case is missing (what should happen at -1?), the output");
            System.out.println("  line is in the wrong place among the two calls, or the method calls");
            System.out.println("  itself but throws the returned value away instead of using it.");
        }
        System.out.println("==============================================================================");
        System.out.println();
    }
}
