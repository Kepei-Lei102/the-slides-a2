/*
 * 🍦 THE ICE-CREAM CHALLENGE — A3b · Where the Promises Are Kept
 * ==============================================================
 *
 * Paper 4 asks for the same job BOTH WAYS. Nov 2023 gave you a loop and asked
 * for the recursion; June 2024 gave you the recursion and asked for the loop.
 * So: write everything twice.
 *
 *     countVowels    how many of a e i o u are in a string
 *     sumDigits      add up the digits of a whole number:  4071 -> 12
 *     reverseString  "stack" -> "kcats"
 *
 * Each one gets two methods — one with a loop, one that calls itself — and
 * they must agree with each other on a thousand random inputs.
 *
 * HOW TO USE THIS FILE
 *     javac ChallengeA03bBothWays.java
 *     java  ChallengeA03bBothWays
 *
 * THE TEST YOU CANNOT FAKE — and this time it runs in BOTH directions
 *     A3a's judge read this file to check that a recursive method really
 *     calls itself. That test still runs here. But it now has a twin: the
 *     ITERATIVE version must NOT call itself, and must actually contain a
 *     loop.
 *
 *     Neither version can be the other one wearing a different name. Writing
 *     the recursion twice fails. Writing the loop twice fails. Copying is the
 *     one thing the judge is watching for, because copying is the one thing
 *     that would skip the exercise entirely.
 *
 * BANNED — the machine may not do your homework
 *     StringBuilder.reverse()   any library that already knows the answer.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class ChallengeA03bBothWays {

    // ═══ 1 · COUNT THE VOWELS ═══════════════════════════════════════════

    /** Count a, e, i, o, u in Value with a loop.
     *  This is Nov 2023 Paper 41's IterativeVowels, and it is worth 5 marks. */
    static int countVowelsIterative(String Value) {
        // YOUR CODE HERE  (replace this placeholder -- -999 is not a real answer)
        return -999;
    }

    /** The same answer, by calling yourself.
     *  Base case: an empty string has no vowels. General case: look at the
     *  FIRST character, then ask yourself about the rest. Worth 6 marks, and
     *  its first published mark point is simply "a recursive call". */
    static int countVowelsRecursive(String Value) {
        // YOUR CODE HERE
        return -999;
    }

    // ═══ 2 · ADD UP THE DIGITS ══════════════════════════════════════════

    /** Add the digits of a non-negative whole number. 4071 -> 4+0+7+1 = 12.
     *  Hint worth more than the answer: n % 10 is the last digit and n / 10 is
     *  everything else. That pair is 'first + rest' for numbers. */
    static int sumDigitsIterative(int n) {
        // YOUR CODE HERE
        return -999;
    }

    /** The same, calling yourself. Base case: a single digit is its own sum. */
    static int sumDigitsRecursive(int n) {
        // YOUR CODE HERE
        return -999;
    }

    // ═══ 3 · REVERSE A STRING ═══════════════════════════════════════════

    /** "stack" -> "kcats", with a loop. Build the answer one character at a
     *  time. (StringBuilder.reverse() is banned, and the judge looks.) */
    static String reverseStringIterative(String Value) {
        // YOUR CODE HERE
        return "-999";
    }

    /** The same, calling yourself.
     *  Base case: the empty string reversed is the empty string. General case:
     *  reverse the REST, then put the first character on the END.
     *
     *  Notice what this one costs. Every call holds its own character while it
     *  waits — which is exactly the stack frame you watched in the lesson. */
    static String reverseStringRecursive(String Value) {
        // YOUR CODE HERE
        return "-999";
    }

    // ═══ 🍨 THE SECOND SCOOP — be the compiler ══════════════════════════
    //
    // In A2c you built a binary tree. In A3a you walked it recursively. The
    // compiler did the remembering for you, on a stack you never saw.
    //
    // Now do its job. Walk the tree IN ORDER — smallest value first — with NO
    // recursion at all, using a stack of your own.
    //
    // The tree is the exam's own storage: ArrayNodes[i] is {left, data, right}
    // and -1 means "no child". The judge builds the trees; you only walk them.
    //
    // The shape, if you want it: go left as far as you can, pushing each node
    // you pass. When you cannot go left, pop one, output it, and go right.

    static int[][] ArrayNodes = new int[0][];

    /** Return the tree's values in order, using your own stack. No recursion —
     *  the judge reads this method's source and will say so. */
    static List<Integer> inOrderIterative(int Root) {
        int[] Stack = new int[200];        // your stack
        int Top = -1;                      // ...and its one integer
        List<Integer> output = new ArrayList<Integer>();
        // YOUR CODE HERE
        return output;
    }

    // ════════════════════════════════════════════════════════════════════
    //  THE JUDGE — everything below this line is the marking, not the homework
    // ════════════════════════════════════════════════════════════════════

    static String fail = "";
    static final int PASS = 1, FAIL = 0, SKIP = -1;

    /** The body of one method, read out of this very file. Found by looking in
     *  the folder rather than by a fixed name, so renaming the file for
     *  submission does not switch the check off. */
    static String sourceOf(String name) {
        try {
            java.io.File here = new java.io.File(".");
            java.io.File[] found = here.listFiles(new java.io.FilenameFilter() {
                public boolean accept(java.io.File d, String n) {
                    return n.startsWith("ChallengeA03b") && n.endsWith(".java");
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
        return body.replaceAll("(?s)/\\*.*?\\*/", "").replaceAll("//.*", "");
    }

    static boolean unwritten(String name) {
        String b = sourceOf(name);
        return b == null || b.contains("return -999") || b.contains("return \"-999\"");
    }

    static final String[] BASES = {"countVowels", "sumDigits", "reverseString"};

    // ---- the reference answers, computed by the judge, never shown ---------

    static int refVowels(String s) {
        int t = 0;
        for (int i = 0; i < s.length(); i++)
            if ("aeiou".indexOf(s.charAt(i)) >= 0) t++;
        return t;
    }

    static int refDigits(int n) {
        int t = 0;
        while (n > 9) { t += n % 10; n /= 10; }
        return t + n;
    }

    static String refReverse(String s) {
        String out = "";
        for (int i = 0; i < s.length(); i++) out = s.charAt(i) + out;
        return out;
    }

    // ---- test 1: each version must BE what it says it is ------------------

    static int tShapes() {
        StringBuilder problems = new StringBuilder();
        int silent = 0;
        for (String base : BASES) {
            String it = base + "Iterative", rec = base + "Recursive";
            String bi = sourceOf(it), br = sourceOf(rec);
            if (bi == null || br == null) { fail = "could not read this file to check the source"; return SKIP; }
            if (unwritten(it) && unwritten(rec)) { silent++; continue; }
            String ci = clean(bi), cr = clean(br);
            if (!cr.matches("(?s).*\\b" + rec + "\\s*\\(.*"))
                add(problems, rec + " never calls itself, so whatever it does, it is not recursion");
            if (cr.matches("(?s).*\\b(for|while)\\b.*"))
                add(problems, "there is a loop inside " + rec + " — that is the other half's job");
            if (ci.matches("(?s).*\\b" + it + "\\s*\\(.*"))
                add(problems, it + " calls itself; the iterative version is the one that must not");
            if (!ci.matches("(?s).*\\b(for|while)\\b.*"))
                add(problems, it + " has no loop in it — an iterative version needs one");
            if ((ci + cr).matches("(?s).*\\.reverse\\s*\\(.*"))
                add(problems, "StringBuilder.reverse() does the work for you — write the loop and the recursion");
        }
        if (silent == BASES.length) { fail = "nothing written yet — start with countVowelsIterative"; return SKIP; }
        if (problems.length() > 0) { fail = problems.toString(); return FAIL; }
        fail = "each half really is what it claims to be, and neither is a copy of the other";
        return PASS;
    }

    static void add(StringBuilder sb, String msg) {
        if (sb.length() == 0) sb.append(msg);
    }

    // ---- test 2: the two versions must agree, and both must be right ------

    static int tPair(String base) {
        if (unwritten(base + "Iterative") && unwritten(base + "Recursive")) {
            fail = "not written yet";
            return SKIP;
        }
        Random rng = new Random(20260811L);
        String letters = "abcdefghijklmnopqrstuvwxyz";
        for (int trial = 0; trial < 1000; trial++) {
            if (base.equals("sumDigits")) {
                int n = rng.nextInt(1000000000);
                int want = refDigits(n), gi = sumDigitsIterative(n), gr = sumDigitsRecursive(n);
                if (gi != want) { fail = "the LOOP is wrong: sumDigitsIterative(" + n + ") gave " + gi + ", should be " + want; return FAIL; }
                if (gr != want) { fail = "the RECURSION is wrong: sumDigitsRecursive(" + n + ") gave " + gr + ", should be " + want; return FAIL; }
            } else {
                StringBuilder sb = new StringBuilder();
                int len = rng.nextInt(15);
                for (int i = 0; i < len; i++) sb.append(letters.charAt(rng.nextInt(26)));
                String s = sb.toString();
                if (base.equals("countVowels")) {
                    int want = refVowels(s), gi = countVowelsIterative(s), gr = countVowelsRecursive(s);
                    if (gi != want) { fail = "the LOOP is wrong: countVowelsIterative(\"" + s + "\") gave " + gi + ", should be " + want; return FAIL; }
                    if (gr != want) { fail = "the RECURSION is wrong: countVowelsRecursive(\"" + s + "\") gave " + gr + ", should be " + want; return FAIL; }
                } else {
                    String want = refReverse(s), gi = reverseStringIterative(s), gr = reverseStringRecursive(s);
                    if (!want.equals(gi)) { fail = "the LOOP is wrong: reverseStringIterative(\"" + s + "\") gave \"" + gi + "\", should be \"" + want + "\""; return FAIL; }
                    if (!want.equals(gr)) { fail = "the RECURSION is wrong: reverseStringRecursive(\"" + s + "\") gave \"" + gr + "\", should be \"" + want + "\""; return FAIL; }
                }
            }
        }
        fail = "1 000 random inputs, and the two versions agree on every one";
        return PASS;
    }

    // ---- 🍨 the scoop -----------------------------------------------------

    /** The exam's storage: {left, data, right}, -1 = null, equal goes right. */
    static int buildTree(List<Integer> values) {
        ArrayNodes = new int[values.size()][3];
        int root = -1;
        for (int i = 0; i < values.size(); i++) {
            int v = values.get(i);
            ArrayNodes[i][0] = -1; ArrayNodes[i][1] = v; ArrayNodes[i][2] = -1;
            if (root == -1) { root = i; continue; }
            int cur = root;
            while (true) {
                int side = (v < ArrayNodes[cur][1]) ? 0 : 2;
                if (ArrayNodes[cur][side] == -1) { ArrayNodes[cur][side] = i; break; }
                cur = ArrayNodes[cur][side];
            }
        }
        return root;
    }

    static int tScoop() {
        String body = sourceOf("inOrderIterative");
        if (body == null) { fail = "optional — could not read the source"; return SKIP; }
        String code = clean(body);
        if (!code.matches("(?s).*\\bStack\\s*\\[.*")) { fail = "optional — not attempted"; return SKIP; }
        if (code.matches("(?s).*\\binOrderIterative\\s*\\(.*")) {
            fail = "inOrderIterative calls itself — the whole point is to do it WITHOUT recursion";
            return FAIL;
        }
        Random rng = new Random(4242L);
        for (int trial = 0; trial < 200; trial++) {
            int n = 1 + rng.nextInt(24);
            List<Integer> pool = new ArrayList<Integer>();
            for (int i = 1; i < 400; i++) pool.add(i);
            Collections.shuffle(pool, rng);
            List<Integer> values = new ArrayList<Integer>(pool.subList(0, n));
            int root = buildTree(values);
            List<Integer> want = new ArrayList<Integer>(values);
            Collections.sort(want);                   // an in-order walk of a BST IS the sorted order
            List<Integer> got = inOrderIterative(root);
            if (got == null || !got.equals(want)) {
                fail = "on a tree of " + n + " values your walk gave " + got + ", not " + want;
                return FAIL;
            }
        }
        if (!inOrderIterative(-1).isEmpty()) { fail = "an empty tree should give an empty list"; return FAIL; }
        fail = "200 random trees, and every walk came out sorted — with no recursion anywhere";
        return PASS;
    }

    public static void main(String[] args) {
        String[] labels = {"each half is what it claims to be",
                           "countVowels — both ways, same answers",
                           "sumDigits — both ways, same answers",
                           "reverseString — both ways, same answers"};
        int width = 0;
        for (String l : labels) width = Math.max(width, l.length());
        int passed = 0;

        System.out.println();
        System.out.println("==============================================================================");
        System.out.println("  A3b · BOTH WAYS — the judge");
        System.out.println("==============================================================================");

        for (int i = 0; i < labels.length; i++) {
            int r;
            try {
                r = (i == 0) ? tShapes() : tPair(BASES[i - 1]);
            } catch (StackOverflowError e) {
                System.out.println("  X  " + pad(labels[i], width)
                        + "   StackOverflowError — the base case is never reached. Which way is the argument moving?");
                continue;
            } catch (Exception e) {
                System.out.println("  X  " + pad(labels[i], width) + "   crashed: " + e);
                continue;
            }
            String mark = (r == PASS) ? "OK " : (r == SKIP ? " . " : " X ");
            System.out.println("  " + mark + " " + pad(labels[i], width) + "   "
                    + (r == SKIP ? "not reached — " : "") + fail);
            if (r == PASS) passed++;
        }

        System.out.println("------------------------------------------------------------------------------");

        // what did the recursion actually cost? time it on their own code.
        try {
            if (!unwritten("countVowelsRecursive") && countVowelsRecursive("house") == 3) {
                StringBuilder sb = new StringBuilder();
                while (sb.length() < 60000) sb.append("imagine the quick brown fox ");
                String big = sb.toString();
                long t0 = System.nanoTime();
                countVowelsIterative(big);
                long tIter = System.nanoTime() - t0;
                String verdict;
                try {
                    long t1 = System.nanoTime();
                    countVowelsRecursive(big);
                    long tRec = System.nanoTime() - t1;
                    verdict = "your recursion survived " + big.length() + " characters, and took "
                            + (tRec / Math.max(1, tIter)) + "x as long as your loop";
                } catch (StackOverflowError e) {
                    verdict = "your recursion could not do " + big.length()
                            + " characters at all — StackOverflowError — while your loop did it in "
                            + (tIter / 1000000.0) + " ms";
                }
                System.out.println("  [] " + verdict + ".");
                System.out.println("     Same answer, same algorithm. The difference is where the remembering happens.");
            }
        } catch (Throwable ignored) { }

        int r;
        try { r = tScoop(); }
        catch (StackOverflowError e) { r = FAIL; fail = "StackOverflowError — a stack walk should never recurse at all"; }
        catch (Exception e) { r = FAIL; fail = "crashed: " + e; }
        System.out.println("  " + ((r == PASS) ? "OK " : (r == SKIP ? " . " : " X "))
                + " SECOND SCOOP - inOrderIterative   " + fail);

        System.out.println("==============================================================================");
        if (passed == labels.length) {
            System.out.println("  " + passed + " / " + labels.length
                    + ".  Everything written twice, and the judge checked the SOURCE of both");
            System.out.println("  halves as well as their answers. You can now be asked for either one.");
        } else {
            System.out.println("  " + passed + " / " + labels.length + ".");
            System.out.println("  Read the first failing row. The three that catch nearly everyone: a base");
            System.out.println("  case the calls step OVER instead of landing on; a missing 'return' in");
            System.out.println("  front of the recursive call, which computes the answer and throws it");
            System.out.println("  away; and building the reverse the wrong way round — the first character");
            System.out.println("  of the input has to end up LAST.");
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
