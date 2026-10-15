/*
 * A4a · JUDGING ALGORITHMS — questions 7 to 10
 * ============================================
 *
 * Questions 1 to 6 are on the printed sheet. These four are the code half.
 *
 * HOW TO USE THIS FILE
 *     1. Fill in the four methods marked YOUR CODE HERE.
 *     2. javac A04a_Practice.java   then   java A04a_Practice
 *     3. Keep going until every row is a tick.
 *
 * SUBMIT AS   A04a_<YourName>.java     (rename the file; the class is not
 *             public, so nothing inside has to change, and the judge finds
 *             its own source whatever you call it.)
 *
 * WHERE THESE COME FROM
 *     Questions 7 and 8 are ONE real question. November 2024 Paper 41 Q1
 *     asked for CompareStrings() (4 marks), and then for a bubble sort that
 *     uses it (3 marks). It is a sort of WORDS, which is the moment "sort"
 *     stops meaning "put numbers in order" — the algorithm never changed,
 *     only the question you ask about two items.
 *
 *     Question 9 is June 2024 Paper 41 Q1(e)(i), 6 marks, iterative.
 *
 * BANNED — the machine may not do your homework
 *     Arrays.sort   Collections.sort   Arrays.binarySearch   List.indexOf
 *     and, in question 7, any comparison of two whole strings at once.
 */

import java.util.Arrays;
import java.util.Random;

class A04a_Practice {

    // ── 7 · which word comes first? ─────────────────────────────────── [4]
    //
    // Compare First and Second one character at a time, and return
    //     1  if First comes before Second alphabetically
    //     2  if Second comes before First
    //
    // Walk the position along: if the characters there are the same, move to
    // the next one; the moment they differ, you have your answer.
    //
    // The paper's two promises, which you may rely on:
    //     * every string is lower case
    //     * the two strings ALWAYS differ before either one runs out, so you
    //       never have to decide what "cat" versus "cattle" means.
    //
    // s.charAt(i) gives the character at position i. Characters compare with
    // < and > just like numbers, because that is what they are underneath.
    //
    static int CompareStrings(String First, String Second) {
        // YOUR CODE HERE   (replace this placeholder -- -999 is not an answer)
        return -999;
    }

    // ── 8 · bubble sort, on words ───────────────────────────────────── [3]
    //
    // Sort DataArray into ascending alphabetical order and RETURN it.
    //
    // It is the bubble sort you already know. The only change is the line in
    // the middle: instead of asking "is A[k] > A[k+1]?", you ask your own
    // question 7 about that pair, and swap when the answer is 2.
    //
    static String[] BubbleStrings(String[] DataArray) {
        // YOUR CODE HERE
        return null;
    }

    // ── 9 · binary search, iterative ────────────────────────────────── [6]
    //
    // DataStored is in ASCENDING order. Return the index where DataToFind
    // sits, or -1 if it is not there.
    //
    // Keep a window: First and Last. Look at the middle of it. Throw half
    // away. Three things to get right, and they are the three that appear in
    // real scripts:
    //     * the loop must still work when the window is one item wide
    //     * First must move PAST the mid-point (Mid + 1), never to it
    //     * when the window empties, the answer is -1
    //
    static int BinarySearch(int[] DataStored, int DataToFind) {
        // YOUR CODE HERE
        return -999;
    }

    // ── 10 · 🍦 predict, then be measured ───────────────────────────── [2]
    //
    // On a sorted array of n items, what is the LARGEST number of
    // comparisons your question 9 can ever need? Return that number.
    //
    // Do not guess, and do not answer "n". Work it out: each comparison
    // throws away half of what is left, so the question is really "how many
    // times can n be halved before there is nothing left?".
    //
    // Check yourself on the small cases by hand. n = 1 needs 1. n = 2 needs
    // 2. n = 3 still needs only 2. Find the rule that produces those.
    //
    // (Math.log(x) / Math.log(2) is log base 2. Math.floor rounds down.)
    //
    static int MaxComparisons(int n) {
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
                    return n.startsWith("A04a") && n.endsWith(".java");
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
     *  writes a real answer and happens to return -999 somewhere inside it must
     *  get a useful message, not "not written yet". */
    static boolean unwritten(String name) {
        String b = clean(sourceOf(name));
        if (b == null) return true;
        String stripped = b.replaceAll("[\\s{}]", "");
        return stripped.equals("returnnull;") || stripped.equals("return-999;") || stripped.isEmpty();
    }

    // ---- shared test data -------------------------------------------------

    /** Random lower-case words, all the SAME length and all different, so the
     *  paper's promise holds: two of them always differ before either ends. */
    static String[] words(Random rng, int n, int len) {
        java.util.LinkedHashSet<String> set = new java.util.LinkedHashSet<String>();
        while (set.size() < n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) sb.append((char) ('a' + rng.nextInt(26)));
            set.add(sb.toString());
        }
        return set.toArray(new String[0]);
    }

    // ---- 7 ----------------------------------------------------------------

    static int tCompare() {
        if (unwritten("CompareStrings")) { fail = "not written yet"; return SKIP; }
        String code = clean(sourceOf("CompareStrings"));
        if (!code.contains("charAt") && !code.contains("substring")) {
            fail = "no charAt and no substring anywhere — the paper says the strings must be "
                 + "compared ONE CHARACTER AT A TIME, and that is two of the four marks";
            return FAIL;
        }
        String flat = code.replaceAll("\\s", "");
        if (flat.matches("(?s).*\\bcompareToIgnoreCase\\(.*")) {
            fail = "compareToIgnoreCase compares the whole strings for you";
            return FAIL;
        }
        // a compareTo whose receiver is a bare identifier is a whole-string compare;
        // the mark scheme's own answer calls it on a one-character substring, so that is fine
        if (flat.matches("(?s).*[^\\)\\]]\\b[A-Za-z_]\\w*\\.compareTo\\(\\s*[A-Za-z_]\\w*\\s*\\).*")) {
            fail = "that compares the two whole strings in one call — walk them character by character";
            return FAIL;
        }
        String[][] fixed = {
            {"apple", "banana"}, {"cat", "car"}, {"zebra", "apple"},
            {"kite", "kilt"}, {"aab", "aba"}, {"forest", "forget"}
        };
        for (String[] p : fixed) {
            int want = p[0].compareTo(p[1]) < 0 ? 1 : 2;
            int got = CompareStrings(p[0], p[1]);
            if (got != want) {
                fail = "CompareStrings(\"" + p[0] + "\", \"" + p[1] + "\") gave " + got
                     + ", should be " + want;
                return FAIL;
            }
        }
        Random rng = new Random(20260813L);
        for (int t = 0; t < 500; t++) {
            int len = 3 + rng.nextInt(6);
            String[] pair = words(rng, 2, len);
            int want = pair[0].compareTo(pair[1]) < 0 ? 1 : 2;
            int got = CompareStrings(pair[0], pair[1]);
            if (got != want) {
                fail = "CompareStrings(\"" + pair[0] + "\", \"" + pair[1] + "\") gave " + got
                     + ", should be " + want;
                return FAIL;
            }
            int back = CompareStrings(pair[1], pair[0]);
            if (back == got) {
                fail = "CompareStrings(\"" + pair[0] + "\", \"" + pair[1] + "\") and the same two the "
                     + "other way round both gave " + got + " — swapping the parameters must swap the answer";
                return FAIL;
            }
        }
        fail = "six fixed pairs and 500 random ones, each tested both ways round";
        return PASS;
    }

    // ---- 8 ----------------------------------------------------------------

    static int tBubble() {
        if (unwritten("BubbleStrings")) { fail = "not written yet"; return SKIP; }
        String code = clean(sourceOf("BubbleStrings"));
        if (code.matches("(?s).*(Arrays\\s*\\.\\s*sort|Collections\\s*\\.\\s*sort).*")) {
            fail = "the library sorted it for you — write the sort";
            return FAIL;
        }
        if (!code.contains("CompareStrings")) {
            fail = "it never calls CompareStrings — the question says the bubble sort must use it, "
                 + "and that is mark point two";
            return FAIL;
        }
        // bubble exchanges NEIGHBOURS; a selection sort never indexes [k + 1]
        if (!code.matches("(?s).*\\[\\s*\\w+\\s*\\+\\s*1\\s*\\].*")) {
            fail = "nothing in here ever looks at the element NEXT to another one ([k + 1]). "
                 + "A bubble sort compares and swaps neighbours; whatever this is, it is not one";
            return FAIL;
        }
        if (unwritten("CompareStrings")) {
            fail = "question 7 is still empty, so this cannot be judged until it is written "
                 + "(and if this sorted correctly without it, it is not using it)";
            return SKIP;
        }
        String[][] fixed = {
            {}, {"solo"},
            {"pear", "apple"},
            {"apple", "banana", "cherry"},
            {"cherry", "banana", "apple"},
            {"delta", "alpha", "charlie", "bravo", "echo"}
        };
        for (String[] src : fixed) {
            String[] want = src.clone(); Arrays.sort(want);
            String[] got = BubbleStrings(src.clone());
            if (got == null) {
                fail = "BubbleStrings" + Arrays.toString(src) + " returned nothing — it must RETURN the sorted array";
                return FAIL;
            }
            if (!Arrays.equals(got, want)) {
                fail = "BubbleStrings" + Arrays.toString(src) + " gave " + Arrays.toString(got)
                     + ", should be " + Arrays.toString(want);
                return FAIL;
            }
        }
        Random rng = new Random(4242L);
        for (int t = 0; t < 200; t++) {
            String[] src = words(rng, rng.nextInt(14), 4);
            String[] want = src.clone(); Arrays.sort(want);
            String[] got = BubbleStrings(src.clone());
            if (got == null || !Arrays.equals(got, want)) {
                fail = "BubbleStrings" + Arrays.toString(src) + " gave " + Arrays.toString(got)
                     + ", should be " + Arrays.toString(want);
                return FAIL;
            }
        }
        fail = "six fixed arrays (including empty and one word) and 200 random ones, sorted through your question 7";
        return PASS;
    }

    // ---- 9 ----------------------------------------------------------------

    static int refLinear(int[] a, int t) {
        for (int i = 0; i < a.length; i++) if (a[i] == t) return i;
        return -1;
    }

    static int tBinary() {
        if (unwritten("BinarySearch")) { fail = "not written yet"; return SKIP; }
        String code = clean(sourceOf("BinarySearch"));
        if (code.matches("(?s).*(Arrays\\s*\\.\\s*binarySearch|Arrays\\s*\\.\\s*asList|\\.indexOf\\s*\\().*")) {
            fail = "a library did the searching — write the window";
            return FAIL;
        }
        int[][] fixed = { {}, {5}, {2, 4}, {1, 3, 5, 7, 9} };
        for (int[] a : fixed) {
            for (int t = -1; t <= 10; t++) {
                int want = refLinear(a, t), got = BinarySearch(a.clone(), t);
                if (got != want) {
                    fail = "BinarySearch(" + Arrays.toString(a) + ", " + t + ") gave " + got
                         + ", should be " + want;
                    return FAIL;
                }
            }
        }
        // every target, present and absent, on a range of sizes
        for (int n = 1; n <= 120; n++) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = i * 2;             // evens, so odds are absent
            for (int t = -1; t <= 2 * n; t++) {
                int want = refLinear(a, t), got = BinarySearch(a.clone(), t);
                if (got != want) {
                    fail = "on the array of " + n + " even numbers, BinarySearch(…, " + t + ") gave "
                         + got + ", should be " + want;
                    return FAIL;
                }
            }
        }
        // the test you cannot fake: a linear scan in disguise finds everything, but slowly.
        // A RATIO against a linear search on the same machine, never a fixed millisecond figure.
        final int N = 2000000, ROUNDS = 40;
        int[] big = new int[N];
        for (int i = 0; i < N; i++) big[i] = i;
        int[] targets = new int[ROUNDS];
        Random rng = new Random(7L);
        for (int i = 0; i < ROUNDS; i++) targets[i] = rng.nextInt(N);
        for (int i = 0; i < ROUNDS; i++) { BinarySearch(big, targets[i]); refLinear(big, targets[i]); }  // warm up
        long t0 = System.nanoTime();
        for (int i = 0; i < ROUNDS; i++) BinarySearch(big, targets[i]);
        long tBin = System.nanoTime() - t0;
        t0 = System.nanoTime();
        for (int i = 0; i < ROUNDS; i++) refLinear(big, targets[i]);
        long tLin = System.nanoTime() - t0;
        if (tBin * 50 > tLin) {
            fail = "it finds everything, but on " + N + " items it took " + (tBin / 1000000)
                 + " ms against a linear search's " + (tLin / 1000000) + " ms. That is a linear "
                 + "search wearing a binary search's name — a real one should be thousands of times faster";
            return FAIL;
        }
        fail = "every target, present and absent, on 124 arrays — and " + (tLin / Math.max(tBin, 1))
             + "x faster than a linear search on 2 000 000 items";
        return PASS;
    }

    // ---- 10 ---------------------------------------------------------------

    /** The true worst case, MEASURED: a reference binary search, run against
     *  every possible target on an array of n items, counting comparisons. */
    static int measuredWorst(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i;
        int worst = 0;
        for (int t = -1; t <= n; t++) {
            int lo = 0, hi = n - 1, c = 0;
            while (lo <= hi) {
                int mid = (lo + hi) / 2; c++;
                if (a[mid] == t) break;
                if (a[mid] < t) lo = mid + 1; else hi = mid - 1;
            }
            if (c > worst) worst = c;
        }
        return worst;
    }

    static int tMaxComparisons() {
        if (unwritten("MaxComparisons")) { fail = "not written yet"; return SKIP; }
        for (int n = 1; n <= 300; n++) {
            int want = measuredWorst(n), got = MaxComparisons(n);
            if (got != want) {
                fail = "for n = " + n + " you predicted " + got + ", but a binary search on " + n
                     + " items can need " + want + " comparisons"
                     + (got == n ? "  (n is the LINEAR search's worst case — this one halves)" : "");
                return FAIL;
            }
        }
        fail = "matches the measured worst case for every n from 1 to 300";
        return PASS;
    }

    // ---- the report -------------------------------------------------------

    static int runTest(int i) {
        if (i == 0) return tCompare();
        if (i == 1) return tBubble();
        if (i == 2) return tBinary();
        return tMaxComparisons();
    }

    /** Run one test on its own thread. The commonest defect in a written binary
     *  search -- First = Mid instead of Mid + 1 -- is an INFINITE LOOP, not a
     *  wrong answer: the window stops shrinking and a value that is not there is
     *  searched for forever. Without this, the judge simply hangs, printing
     *  nothing at all for the question the student got wrong. */
    static int withTimeout(final int which, int seconds) {
        final int[] result = { FAIL };
        final String[] msg = { "" };
        final Throwable[] blew = { null };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try { result[0] = runTest(which); msg[0] = fail; }
                catch (Throwable e) { blew[0] = e; }
            }
        });
        t.setDaemon(true);            // daemon, so a spinning loop cannot keep the JVM alive
        t.start();
        try { t.join(seconds * 1000L); } catch (InterruptedException e) { }
        if (t.isAlive()) {
            fail = "it never finished. After " + seconds + " seconds it was still running, which means an "
                 + "INFINITE LOOP rather than a wrong answer. In a binary search the usual cause is "
                 + "First = Mid instead of First = Mid + 1: the window stops shrinking, so anything that "
                 + "is not in the array is searched for forever";
            return FAIL;
        }
        if (blew[0] != null) {
            fail = (blew[0] instanceof StackOverflowError)
                 ? "StackOverflowError — something calls itself and never stops"
                 : "crashed: " + blew[0];
            return FAIL;
        }
        fail = msg[0];
        return result[0];
    }

    public static void main(String[] args) {
        String[] labels = {"7 · CompareStrings, one character at a time",
                           "8 · BubbleStrings, sorting through question 7",
                           "9 · BinarySearch — and it must really halve",
                           "10 · your worst-case prediction 🍦"};
        int width = 0;
        for (String l : labels) width = Math.max(width, l.length());
        int passed = 0, marks = 0;
        int[] worth = {4, 3, 6, 2};

        System.out.println();
        System.out.println("==============================================================================");
        System.out.println("  A4a · questions 7 to 10 — the judge");
        System.out.println("==============================================================================");

        for (int i = 0; i < labels.length; i++) {
            int r = withTimeout(i, 40);
            System.out.println("  " + ((r == PASS) ? "OK " : (r == SKIP ? " . " : " X ")) + " "
                    + pad(labels[i], width) + "   " + (r == SKIP ? "not reached — " : "") + fail);
            if (r == PASS) { passed++; marks += worth[i]; }
        }

        System.out.println("------------------------------------------------------------------------------");

        // the staircase: why the answer to question 10 is not a straight line
        if (!unwritten("MaxComparisons")) {
            StringBuilder sb = new StringBuilder("  [] worst case as the array grows: ");
            int[] sizes = {1, 2, 4, 8, 16, 32, 64, 128, 256};
            for (int i = 0; i < sizes.length; i++)
                sb.append(sizes[i]).append("->").append(measuredWorst(sizes[i]))
                  .append(i < sizes.length - 1 ? "  " : "");
            System.out.println(sb.toString());
            System.out.println("     The array doubles every time. The cost goes up by ONE. That sentence,");
            System.out.println("     in words, is the two marks Paper 3 gives for O(log n).");
        }

        System.out.println("==============================================================================");
        System.out.println("  " + marks + " / 15 marks   (" + passed + " of 4 questions complete)");
        if (passed == labels.length) {
            System.out.println("  A sort that sorts words, a search that provably halves, and a worst case");
            System.out.println("  you predicted before anything measured it. 🍦");
        } else {
            System.out.println("  Read the first failing row. The three that catch nearly everyone here:");
            System.out.println("  comparing the whole strings instead of walking them; a bubble sort that");
            System.out.println("  forgets to RETURN the array; and First = Mid instead of Mid + 1, which");
            System.out.println("  finds most things and then hangs forever on the ones it cannot find.");
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
