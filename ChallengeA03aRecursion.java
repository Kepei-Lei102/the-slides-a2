/* ══════════════════════════════════════════════════════════════════════════
   🍦 THE ICE-CREAM CHALLENGE — A3a · The Function That Calls Itself
   ══════════════════════════════════════════════════════════════════════════

   Four methods. Every one is three or four lines, and every one calls itself.

       factorial(n)        n × (n-1) × … × 1,  and factorial(0) = 1
       fib(n)              0, 1, 1, 2, 3, 5, 8 …   fib(0) = 0, fib(1) = 1
       gcd(a, b)           the greatest common divisor, Euclid's way
       countLeaves(node)   how many nodes in a binary tree have no children

   HOW TO USE THIS FILE
       1. Fill in the four methods below.
       2. Compile and run:   javac ChallengeA03aRecursion.java
                             java  ChallengeA03aRecursion
       3. Keep going until every row is a ✓.

   BANNED — the machine may not do your homework
       BigInteger.factorial-style helpers, java.math.BigInteger.gcd,
       any library method that already knows the answer.

   THE TEST YOU CANNOT FAKE — and it is a new kind
       Every one of these has a trivial version written with a loop, and a
       loop would pass any test that only checks the answer. So the judge
       does something different: it READS THIS FILE and looks inside your
       four methods for a `for` or a `while`.

       The point of this homework is not the number. It is the technique —
       and the technique is the one thing a results-only test cannot see.

   ══════════════════════════════════════════════════════════════════════════ */

import java.util.HashMap;
import java.util.Map;

public class ChallengeA03aRecursion {

    // ═══════════════════════════════════════════════════════════════════
    //  YOUR CODE
    // ═══════════════════════════════════════════════════════════════════

    /** n! — and factorial(0) is 1, which is the base case.
     *  Two lines of body: the case you answer outright, and the case that
     *  calls itself on something smaller. (long, so 20! still fits.)       */
    static long factorial(int n) {
        // YOUR CODE HERE  (replace this placeholder -- -999 is not a real answer)
        return -999;
    }

    /** The nth Fibonacci number. fib(0) = 0, fib(1) = 1.
     *  TWO base cases (or one that covers both), and a general case that
     *  calls itself TWICE. Read the judge's call count when it finishes.   */
    static long fib(int n) {
        // YOUR CODE HERE
        return -999;
    }

    /** Euclid, c. 300 BCE — the oldest algorithm anyone still runs.
     *  Base case: b is 0, and the answer is a. Otherwise call yourself
     *  with (b, a % b). Two lines, and it has outlived every language it
     *  has ever been written in.                                           */
    static int gcd(int a, int b) {
        // YOUR CODE HERE
        return -999;
    }

    /* A binary tree in the exam's own storage: [left, data, right], -1 = null.
     * The judge builds this for you; you only walk it.                     */
    static int[][] ArrayNodes = new int[1][3];

    /** How many LEAVES (nodes with no children at all) are at or below node?
     *  `node` is an INDEX into ArrayNodes; -1 means there is nothing here.
     *      ArrayNodes[node][0]  left    ArrayNodes[node][2]  right
     *  Three cases: nothing here · a leaf · "my left leaves + my right".   */
    static int countLeaves(int node) {
        // YOUR CODE HERE
        return -999;
    }

    // ═══════════════════════════════════════════════════════════════════
    //  SECOND SCOOP 🍨 (optional) — the one-line fix that changes everything
    // ═══════════════════════════════════════════════════════════════════
    //
    //  fib() above recomputes the same values over and over: fib(30) costs
    //  about 2.7 MILLION calls for a six-digit answer. Nothing is wrong with
    //  the recursion — the problem is that it FORGETS.
    //
    //  Write memoFib(): the same method, but keeping a Map of answers it has
    //  already worked out. Look in the cache first; compute only what is new;
    //  store it before returning.
    //
    //  The judge does not check your answer. It counts your CALLS.

    static Map<Integer, Long> memo = new HashMap<>();

    static long memoFib(int n) {
        // OPTIONAL
        return -999;
    }

    // ────────────────────────────────────────────────────────────────────
    //  THE JUDGE — you do not need to read past here, but you may
    // ────────────────────────────────────────────────────────────────────

    static int buildTree(int[] values) {
        ArrayNodes = new int[values.length + 2][3];
        for (int i = 0; i < ArrayNodes.length; i++) {
            ArrayNodes[i][0] = -1; ArrayNodes[i][1] = -1; ArrayNodes[i][2] = -1;
        }
        int root = -1, free = 0;
        for (int v : values) {
            ArrayNodes[free][0] = -1; ArrayNodes[free][1] = v; ArrayNodes[free][2] = -1;
            if (root == -1) root = 0;
            else {
                int c = root;
                while (true) {
                    if (v < ArrayNodes[c][1]) {
                        if (ArrayNodes[c][0] == -1) { ArrayNodes[c][0] = free; break; }
                        c = ArrayNodes[c][0];
                    } else {
                        if (ArrayNodes[c][2] == -1) { ArrayNodes[c][2] = free; break; }
                        c = ArrayNodes[c][2];
                    }
                }
            }
            free++;
        }
        return root;
    }

    /** the body of one method, read out of this very file.
     *  Matched on the DECLARATION line (static <type> name(...)), never on a
     *  mention in a comment — the header above names all four methods, and an
     *  indexOf search finds those first. */
    static String sourceOf(String name) {
        try {
            String src = new String(java.nio.file.Files.readAllBytes(
                    java.nio.file.Paths.get("ChallengeA03aRecursion.java")), "UTF-8");
            java.util.regex.Matcher m = java.util.regex.Pattern
                    .compile("(?m)^\\s*static\\s+[\\w\\[\\]<>, ]+?\\s+" + name + "\\s*\\(")
                    .matcher(src);
            if (!m.find()) return null;
            int i = m.start();
            int open = src.indexOf('{', i);
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

    static String fail = "";
    static final int PASS = 1, FAIL = 0, SKIP = -1;

    static int tSource() {
        String[] names = {"factorial", "fib", "gcd", "countLeaves"};
        StringBuilder looped = new StringBuilder(), silent = new StringBuilder();
        int silentCount = 0;
        for (String n : names) {
            String body = sourceOf(n);
            if (body == null) { fail = "could not read this file to check the source"; return SKIP; }
            String code = body.replaceAll("(?s)/\\*.*?\\*/", "").replaceAll("//.*", "");
            if (code.matches("(?s).*\\b(for|while)\\b.*")) {
                if (looped.length() > 0) looped.append(", ");
                looped.append(n);
            }
            if (!code.matches("(?s).*\\b" + n + "\\s*\\(.*")) {
                if (silent.length() > 0) silent.append(", ");
                silent.append(n);
                silentCount++;
            }
        }
        if (silentCount == 4) { fail = "none of the four call themselves yet — start with factorial"; return SKIP; }
        if (silent.length() > 0) {
            fail = silent + " never calls itself, so whatever it does, it is not recursion";
            return FAIL;
        }
        if (looped.length() > 0) {
            fail = "a loop is hiding in " + looped + " — these have to call themselves, or the exercise did not happen";
            return FAIL;
        }
        fail = "all four call themselves, and not a for or a while among them";
        return PASS;
    }

    static int tFactorial() {
        if (factorial(0) != 1) { fail = "factorial(0) must be 1 — that is the base case, got " + factorial(0); return FAIL; }
        long want = 1;
        for (int i = 1; i <= 15; i++) {
            want *= i;
            if (factorial(i) != want) { fail = "factorial(" + i + ") should be " + want + ", got " + factorial(i); return FAIL; }
        }
        fail = "0 through 15, all correct"; return PASS;
    }

    static int tFib() {
        long[] seq = new long[16];
        seq[0] = 0; seq[1] = 1;
        for (int i = 2; i < 16; i++) seq[i] = seq[i-1] + seq[i-2];
        for (int i = 0; i < 16; i++)
            if (fib(i) != seq[i]) { fail = "fib(" + i + ") should be " + seq[i] + ", got " + fib(i); return FAIL; }
        fail = "0 through 15 against the sequence itself"; return PASS;
    }

    static int libGcd(int a, int b) { return b == 0 ? a : libGcd(b, a % b); }

    static int tGcd() {
        int[][] pairs = {{48,18},{270,192},{17,5},{100,75},{13,13},{81,27},{1,999}};
        for (int[] p : pairs) {
            int want = libGcd(p[0], p[1]);
            if (gcd(p[0], p[1]) != want) {
                fail = "gcd(" + p[0] + ", " + p[1] + ") should be " + want + ", got " + gcd(p[0], p[1]); return FAIL; }
        }
        fail = "seven pairs"; return PASS;
    }

    static int tLeaves() {
        int[][] cases = {{15,8,19,3,10},{10},{1,2,3,4,5},{50,25,75,10,30,60,90}};
        int[] wants = {3, 1, 1, 4};
        for (int i = 0; i < cases.length; i++) {
            int root = buildTree(cases[i]);
            int got = countLeaves(root);
            if (got != wants[i]) {
                StringBuilder sb = new StringBuilder();
                for (int v : cases[i]) { if (sb.length() > 0) sb.append(", "); sb.append(v); }
                fail = "inserting [" + sb + "] gives " + wants[i] + " leaves, got " + got; return FAIL; }
        }
        if (countLeaves(-1) != 0) { fail = "countLeaves(-1) must be 0 — an empty tree has no leaves"; return FAIL; }
        fail = "four trees, plus the empty one"; return PASS;
    }

    interface Test { int run(); }

    public static void main(String[] args) {
        String[] names = {
            "no loops — they really do call themselves",
            "factorial",
            "fib",
            "gcd — Euclid's own",
            "countLeaves walks the tree"
        };
        Test[] tests = {
            ChallengeA03aRecursion::tSource,
            ChallengeA03aRecursion::tFactorial,
            ChallengeA03aRecursion::tFib,
            ChallengeA03aRecursion::tGcd,
            ChallengeA03aRecursion::tLeaves
        };
        int width = 0;
        for (String s : names) width = Math.max(width, s.length());
        int passed = 0;

        System.out.println();
        System.out.println("==============================================================================");
        System.out.println("  A3a · RECURSION — the judge");
        System.out.println("==============================================================================");
        for (int i = 0; i < tests.length; i++) {
            int r;
            fail = "";
            try { r = tests[i].run(); }
            catch (StackOverflowError e) {
                System.out.printf("  ✗  %-" + width + "s   StackOverflowError — the base case is never "
                                + "reached. Which way is it moving?%n", names[i]);
                continue;
            }
            catch (Exception e) {
                System.out.printf("  ✗  %-" + width + "s   crashed: %s%n", names[i], e);
                continue;
            }
            if (r == SKIP)      System.out.printf("  ·  %-" + width + "s   not reached — %s%n", names[i], fail);
            else if (r == PASS) { System.out.printf("  ✓  %-" + width + "s   %s%n", names[i], fail); passed++; }
            else                System.out.printf("  ✗  %-" + width + "s   %s%n", names[i], fail);
        }

        System.out.println("------------------------------------------------------------------------------");

        /* Java cannot wrap your method to count its calls the way Python can,
           so the scoop is judged on the thing a cache actually buys: TIME.
           The forgetful version of fib(42) is a few seconds of work; a
           remembering one is a few hundred microseconds. */
        String scoop = "·  SECOND SCOOP 🍨 — memoFib   (optional — not attempted)";
        try {
            memo.clear();
            if (memoFib(25) == 75025L) {
                memo.clear();
                long t0 = System.nanoTime();
                long v = memoFib(42);
                long ms = (System.nanoTime() - t0) / 1000000;
                calls = 0; countingFib(25);
                if (v != 267914296L) {
                    scoop = "✗  SECOND SCOOP 🍨 — memoFib(42) should be 267914296, got " + v;
                } else if (ms < 500) {
                    scoop = "✓  SECOND SCOOP 🍨 — memoFib(42) answered in " + ms + " ms"
                          + (memo.size() > 0 ? " with " + memo.size() + " values remembered" : "")
                          + ". The forgetful version needs about 866 million calls for that one — "
                          + "and for fib(25) alone it makes " + calls + ".";
                } else {
                    scoop = "✗  SECOND SCOOP 🍨 — memoFib(42) took " + ms + " ms, so it is still "
                          + "recomputing: check the cache is READ before the work is done, and "
                          + "WRITTEN before the return.";
                }
            }
        } catch (Exception | StackOverflowError e) { }
        System.out.println("  " + scoop);

        System.out.println("==============================================================================");
        if (passed == tests.length) {
            System.out.println("  " + passed + " / " + tests.length + ".  Four methods, no loops, and the machine checked the");
            System.out.println("  SOURCE as well as the answers. You did the technique, not just the arithmetic. 🍦");
        } else {
            System.out.println("  " + passed + " / " + tests.length + ".");
            System.out.println("  Read the first failing row. Nearly every failure here is one of three");
            System.out.println("  things: no base case, a base case the calls never reach (check which way");
            System.out.println("  the argument is moving), or a missing 'return' in front of the recursive");
            System.out.println("  call — that last one computes the answer and throws it away.");
        }
        System.out.println("==============================================================================");
        System.out.println();
    }

    /* the judge's OWN naive fib, purely to have an honest number to compare against */
    static long calls = 0;
    static long countingFib(int n) {
        calls++;
        if (n <= 1) return n;
        return countingFib(n - 1) + countingFib(n - 2);
    }
}
