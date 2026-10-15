/*
 * A3b · WHERE THE PROMISES ARE KEPT — questions 7 to 10
 * =====================================================
 *
 * Questions 1 to 6 are on the printed sheet. These four are the code half.
 *
 * HOW TO USE THIS FILE
 *     1. Fill in the four methods marked YOUR CODE HERE.
 *     2. javac A03b_Practice.java   then   java A03b_Practice
 *     3. Keep going until every row is a tick.
 *
 * SUBMIT AS   A03b_<YourName>.java     (rename the file; the class is not
 *             public, so nothing inside has to change, and the judge finds
 *             its own source whatever you call it.)
 *
 * WHERE THESE COME FROM
 *     Questions 7 and 8 are ONE real question. June 2024 Paper 42 Q3 gave
 *     candidates a recursive insertion sort and asked them to write it (4
 *     marks), then asked for the same job again using iteration (4 marks).
 *     That pairing is the whole point of this lesson, so you get both.
 *
 * BANNED — the machine may not do your homework
 *     Arrays.sort   Collections.sort   any library that already sorts.
 */

import java.util.Arrays;
import java.util.Random;

class A03b_Practice {

    // ── 7 · the sort that calls itself ──────────────────────────────── [4]
    //
    // Sort IntegerArray into ASCENDING order and return it, using recursion.
    //
    // The shape, which is June 2024's own:
    //     if there are 0 or 1 elements, it is already sorted — return it
    //     otherwise: sort the first (NumberElements - 1) elements by calling
    //     yourself, then take the LAST element and slide it back into its
    //     place among the sorted ones.
    //
    static int[] RecursiveInsertion(int[] IntegerArray, int NumberElements) {
        // YOUR CODE HERE   (replace this placeholder -- null is not an answer)
        return null;
    }

    // ── 8 · the same sort, with loops ───────────────────────────────── [4]
    //
    // Same input, same output, no recursion. An outer loop walks the array;
    // an inner loop slides each element back to where it belongs.
    //
    static int[] IterativeInsertion(int[] IntegerArray, int NumberElements) {
        // YOUR CODE HERE
        return null;
    }

    // ── 9 · add up a number's digits, recursively ───────────────────── [3]
    //
    //     SumDigits(4071) is 4 + 0 + 7 + 1 = 12
    //
    // n % 10 is the last digit. n / 10 is everything else. That pair is
    // "first + rest" for numbers, and it is the whole algorithm.
    //
    static int SumDigits(int n) {
        // YOUR CODE HERE
        return -999;
    }

    // ── 10 · 🍦 predict, then be measured ───────────────────────────── [2]
    //
    // Before the judge runs anything: how many frames deep does YOUR
    // question 7 go when it sorts an array of n elements? Return the number.
    //
    // Do not guess. Read your own answer to question 7 and count: how many
    // calls are alive at the deepest moment, before the first one returns?
    //
    // (The judge then puts your question 7 on a thread with a deliberately
    // small stack and finds the size at which it dies. If your formula is
    // right, that number will not surprise you.)
    //
    static int InsertionDepth(int n) {
        // YOUR CODE HERE
        return -999;
    }

    // ════════════════════════════════════════════════════════════════════
    //  THE JUDGE — everything below this line is the marking, not the work
    // ════════════════════════════════════════════════════════════════════

    static String fail = "";
    static final int PASS = 1, FAIL = 0, SKIP = -1;

    /** The body of one method, read out of this very file — found by looking
     *  in the folder, so renaming it for submission changes nothing. */
    static String sourceOf(String name) {
        try {
            java.io.File here = new java.io.File(".");
            java.io.File[] found = here.listFiles(new java.io.FilenameFilter() {
                public boolean accept(java.io.File d, String n) {
                    return n.startsWith("A03b") && n.endsWith(".java");
                }
            });
            if (found == null || found.length == 0) return null;
            String src = new String(java.nio.file.Files.readAllBytes(found[0].toPath()), "UTF-8");
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

    static String clean(String body) {
        return body == null ? null : body.replaceAll("(?s)/\\*.*?\\*/", "").replaceAll("//.*", "");
    }

    /** Unwritten means the body is STILL ONLY the placeholder. A student who
     *  writes a real answer and happens to return null somewhere inside it must
     *  get a useful message, not "not written yet". */
    static boolean unwritten(String name) {
        String b = clean(sourceOf(name));
        if (b == null) return true;
        String stripped = b.replaceAll("[\\s{}]", "");
        return stripped.equals("returnnull;") || stripped.equals("return-999;") || stripped.isEmpty();
    }

    static int[] shuffled(Random rng, int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = rng.nextInt(2000) - 1000;
        return a;
    }

    static int[] reference(int[] a) {
        int[] c = a.clone();
        for (int i = 1; i < c.length; i++) {            // the judge's own insertion sort
            int last = c[i], j = i - 1;
            while (j >= 0 && c[j] > last) { c[j + 1] = c[j]; j--; }
            c[j + 1] = last;
        }
        return c;
    }

    // ---- 7 and 8: do they sort, and is each one what it claims to be? ----

    static int sortTest(String name, boolean recursive) {
        if (unwritten(name)) { fail = "not written yet"; return SKIP; }
        String code = clean(sourceOf(name));
        boolean callsItself = code.matches("(?s).*\\b" + name + "\\s*\\(.*");
        boolean hasLoop = code.matches("(?s).*\\b(for|while)\\b.*");
        if (recursive && !callsItself) {
            fail = name + " never calls itself — the question asked for recursion, and that is mark point one";
            return FAIL;
        }
        if (!recursive && callsItself) {
            fail = name + " calls itself; this is the version that must not";
            return FAIL;
        }
        if (!recursive && !hasLoop) {
            fail = name + " has no loop in it — an iterative sort needs one";
            return FAIL;
        }
        if (code.matches("(?s).*(Arrays\\s*\\.\\s*sort|Collections\\s*\\.\\s*sort).*")) {
            fail = "the library sorted it for you — write the sort";
            return FAIL;
        }
        Random rng = new Random(20260811L);
        int[][] fixed = { {}, {5}, {2,1}, {1,2,3,4,5}, {5,4,3,2,1}, {7,7,7,7}, {0,-3,9,-3,4} };
        for (int[] src : fixed) {
            int[] got = recursive ? RecursiveInsertion(src.clone(), src.length)
                                  : IterativeInsertion(src.clone(), src.length);
            if (got == null) { fail = name + " returned null on " + Arrays.toString(src) + " — it must return the sorted array"; return FAIL; }
            if (!Arrays.equals(got, reference(src))) {
                fail = name + Arrays.toString(src) + " gave " + Arrays.toString(got)
                     + ", should be " + Arrays.toString(reference(src));
                return FAIL;
            }
        }
        for (int t = 0; t < 400; t++) {
            int[] src = shuffled(rng, rng.nextInt(15));
            int[] got = recursive ? RecursiveInsertion(src.clone(), src.length)
                                  : IterativeInsertion(src.clone(), src.length);
            if (got == null || !Arrays.equals(got, reference(src))) {
                fail = name + Arrays.toString(src) + " gave " + Arrays.toString(got)
                     + ", should be " + Arrays.toString(reference(src));
                return FAIL;
            }
        }
        fail = "seven fixed cases (including empty, one element, already sorted, reversed and duplicates) and 400 random arrays";
        return PASS;
    }

    // ---- 9 ----

    static int tSumDigits() {
        if (unwritten("SumDigits")) { fail = "not written yet"; return SKIP; }
        String code = clean(sourceOf("SumDigits"));
        if (!code.matches("(?s).*\\bSumDigits\\s*\\(.*")) {
            fail = "SumDigits never calls itself, so whatever it does, it is not recursion";
            return FAIL;
        }
        if (code.matches("(?s).*\\b(for|while)\\b.*")) {
            fail = "there is a loop inside SumDigits — this one has to call itself";
            return FAIL;
        }
        int[] cases = {0, 7, 10, 4071, 999999, 123456789, 1000000};
        for (int n : cases) {
            int want = 0;
            for (char c : String.valueOf(n).toCharArray()) want += c - '0';
            int got = SumDigits(n);
            if (got != want) { fail = "SumDigits(" + n + ") gave " + got + ", should be " + want; return FAIL; }
        }
        fail = "seven values, including 0, a single digit, and 4071 -> 12";
        return PASS;
    }

    // ---- 10: the prediction, checked against the thing it predicts -------

    /** The depth depends on the base case THEY wrote, not on the one I had in
     *  mind. Stopping at "1 element left" gives n frames; stopping at "0 elements
     *  left" is equally correct and gives n + 1. Read their source and expect the
     *  right one, or this question punishes a correct answer. */
    static int expectedDepth(int n) {
        String code = clean(sourceOf("RecursiveInsertion"));
        if (code == null) return n;
        boolean stopsAtZero = code.matches("(?s).*(NumberElements\\s*<=\\s*0|NumberElements\\s*==\\s*0|NumberElements\\s*<\\s*1).*");
        return stopsAtZero ? n + 1 : n;
    }

    static int tDepth() {
        if (unwritten("InsertionDepth")) { fail = "optional — not attempted"; return SKIP; }
        if (unwritten("RecursiveInsertion")) { fail = "not reached — question 7 has to work first"; return SKIP; }
        int[] sizes = {1, 2, 3, 5, 8, 13, 21, 100};
        for (int n : sizes) {
            int want = expectedDepth(n), said = InsertionDepth(n);
            if (said == want) continue;
            if (said == n && want == n + 1) {
                fail = "for " + n + " elements you predicted " + said + ", but YOUR base case stops at 0 elements "
                     + "left, which costs one extra frame — so it is " + want + ". Count the call that does nothing.";
            } else if (said == n + 1 && want == n) {
                fail = "for " + n + " elements you predicted " + said + ", but YOUR base case stops at 1 element "
                     + "left, so the last call still does work and there are " + want + " frames, not " + said + ".";
            } else {
                fail = "for " + n + " elements you predicted " + said + ", and your question 7 goes " + want
                     + " deep. Each call passes NumberElements - 1 to the next; count until your base case fires.";
            }
            return FAIL;
        }
        fail = "one frame per element (" + (expectedDepth(1) == 1 ? "base case at 1 element left" : "plus one, your base case stops at 0")
             + "), for every size checked — and that is why the next line happens";
        return PASS;
    }

    /** Run their sort on a thread with a deliberately small stack and find the
     *  array size it can no longer survive. Not marked -- it is the lesson. */
    static int breakingPoint() {
        int lo = 1, hi = 200000;
        while (lo < hi) {
            final int mid = (lo + hi + 1) / 2;
            final boolean[] ok = {true};
            Runnable r = new Runnable() {
                public void run() {
                    try {
                        int[] a = new int[mid];
                        for (int i = 0; i < mid; i++) a[i] = i;     // already sorted: pure descent
                        RecursiveInsertion(a, mid);
                    } catch (StackOverflowError e) { ok[0] = false; }
                      catch (Throwable t) { ok[0] = false; }
                }
            };
            Thread th = new Thread(null, r, "probe", 256 * 1024);   // 256 KB of stack
            th.start();
            try { th.join(20000); } catch (InterruptedException e) { }
            if (ok[0]) lo = mid; else hi = mid - 1;
        }
        return lo;
    }

    public static void main(String[] args) {
        String[] labels = {"7 · RecursiveInsertion sorts, and recurses",
                           "8 · IterativeInsertion sorts, and loops",
                           "9 · SumDigits",
                           "10 · your depth prediction 🍦"};
        int width = 0;
        for (String l : labels) width = Math.max(width, l.length());
        int passed = 0, marks = 0;
        int[] worth = {4, 4, 3, 2};

        System.out.println();
        System.out.println("==============================================================================");
        System.out.println("  A3b · questions 7 to 10 — the judge");
        System.out.println("==============================================================================");

        for (int i = 0; i < labels.length; i++) {
            int r;
            try {
                if (i == 0)      r = sortTest("RecursiveInsertion", true);
                else if (i == 1) r = sortTest("IterativeInsertion", false);
                else if (i == 2) r = tSumDigits();
                else             r = tDepth();
            } catch (StackOverflowError e) {
                System.out.println("  X  " + pad(labels[i], width)
                        + "   StackOverflowError — the base case is never reached. Which way is NumberElements moving?");
                continue;
            } catch (Exception e) {
                System.out.println("  X  " + pad(labels[i], width) + "   crashed: " + e);
                continue;
            }
            System.out.println("  " + ((r == PASS) ? "OK " : (r == SKIP ? " . " : " X ")) + " "
                    + pad(labels[i], width) + "   " + (r == SKIP ? "not reached — " : "") + fail);
            if (r == PASS) { passed++; marks += worth[i]; }
        }

        System.out.println("------------------------------------------------------------------------------");

        if (!unwritten("RecursiveInsertion")) {
            try {
                int n = breakingPoint();
                System.out.println("  [] On a thread with 256 KB of stack, your RecursiveInsertion sorts " + n
                        + " elements");
                System.out.println("     and dies on " + (n + 1) + ". One frame per element, and the room ran out.");
                if (!unwritten("IterativeInsertion")) {
                    boolean itSurvives = true;
                    final boolean[] ok = {true};
                    Thread th = new Thread(null, new Runnable() {
                        public void run() {
                            try {
                                int[] a = new int[200000];
                                for (int i = 0; i < a.length; i++) a[i] = i;
                                IterativeInsertion(a, a.length);
                            } catch (Throwable t) { ok[0] = false; }
                        }
                    }, "probe2", 256 * 1024);
                    th.start();
                    try { th.join(60000); } catch (InterruptedException e) { }
                    itSurvives = ok[0];
                    System.out.println("     Your IterativeInsertion on 200 000 elements, same 256 KB: "
                            + (itSurvives ? "fine." : "it failed too — check question 8.")
                            + (itSurvives ? " It never grew the stack at all." : ""));
                }
            } catch (Throwable t) { /* the demonstration is a bonus, never a failure */ }
        }

        System.out.println("==============================================================================");
        System.out.println("  " + marks + " / 13 marks   (" + passed + " of 4 questions complete)");
        if (passed == labels.length) {
            System.out.println("  Both sorts written, both judged on what they ARE as well as what they do,");
            System.out.println("  and you predicted the depth before anything measured it. 🍦");
        } else {
            System.out.println("  Read the first failing row. The three that catch nearly everyone here:");
            System.out.println("  forgetting to RETURN the array; a base case at 0 elements when the last");
            System.out.println("  element still has to be inserted; and sliding elements the wrong way, so");
            System.out.println("  the array ends up sorted downwards.");
        }
        System.out.println("==============================================================================");
        System.out.println();
    }

    static String pad(String s, int w) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < w) sb.append(' ');
        return sb.toString();
    }
}
