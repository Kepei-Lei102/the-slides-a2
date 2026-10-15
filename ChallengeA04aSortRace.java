/*
 * 🍦 THE ICE-CREAM CHALLENGE — A4a · Judging Algorithms
 * =====================================================
 *
 * Four algorithms. You have met all of them, and you have written two.
 *
 *     LinearSearch(A, Target)   -> the index, or -1
 *     BinarySearch(A, Target)   -> the index, or -1   (A is sorted)
 *     BubbleSort(A)             -> the same array, sorted ascending
 *     InsertionSort(A)          -> the same array, sorted ascending
 *
 * HOW THIS ONE IS DIFFERENT
 *     Every other judge in this course asked "is your answer right?". This one
 *     asks that first and then RACES them: same data, same start line. You
 *     cannot win the race by typing well. The winner is decided by the
 *     algorithm you chose, which is the whole point of the unit.
 *
 * THE TEST YOU CANNOT FAKE
 *     A binary search that secretly walks the array still finds everything.
 *     So the judge runs it two thousand times on a million sorted values and
 *     watches the clock. A real binary search does that in a blink. A linear
 *     one cannot, however it is written.
 *
 * BANNED — the machine may not do your homework
 *     Arrays.sort   Arrays.binarySearch   Collections   any library that
 *     searches or sorts for you.
 */

import java.util.Random;

class ChallengeA04aSortRace {

    // ═══ 1 · THE SEARCHES ═══════════════════════════════════════════════

    /** The index of Target in A, or -1. A may be in any order at all. */
    static int LinearSearch(int[] A, int Target) {
        // YOUR CODE HERE  (replace this placeholder -- -999 is not an answer)
        return -999;
    }

    /** The index of Target in the SORTED array A, or -1.
     *
     *  Keep a window: Low and High. Look at the middle. Throw half away.
     *  Three things to get right: the loop must survive a window of one; Low
     *  must move PAST the midpoint, not to it; and when the window empties,
     *  the answer is -1. */
    static int BinarySearch(int[] A, int Target) {
        // YOUR CODE HERE
        return -999;
    }

    // ═══ 2 · THE SORTS ══════════════════════════════════════════════════

    /** Sort A ascending and return it. Compare neighbours, swap, repeat.
     *  You wrote this in pseudocode last year. Same algorithm, typed. */
    static int[] BubbleSort(int[] A) {
        // YOUR CODE HERE
        return null;
    }

    /** Sort A ascending and return it. Carry each item back into the part
     *  behind it, which is always sorted. You have written this before. */
    static int[] InsertionSort(int[] A) {
        // YOUR CODE HERE
        return null;
    }

    // ═══ 🍨 THE SECOND SCOOP — not on your syllabus ═════════════════════
    //
    // Selection sort. Walk the unsorted part, remember WHERE the smallest
    // value is, and when you reach the end, swap it into the first unsorted
    // slot. One swap per pass, and never more.
    //
    // Java hands the judge a bare int[], which it cannot watch the way the
    // Python judge watches a list. So this one reads your source for the
    // property that separates the two algorithms whatever the layout: a bubble
    // sort exchanges NEIGHBOURS, A[k] with A[k+1], and selection sort carries
    // the smallest value home from wherever it was found — which is hardly
    // ever next door. (The Python file counts writes instead, and that check
    // is the stronger of the two.)

    static int[] SelectionSort(int[] A) {
        // YOUR CODE HERE
        return null;
    }

    // ════════════════════════════════════════════════════════════════════
    //  THE JUDGE — everything below this line is the marking, not the work
    // ════════════════════════════════════════════════════════════════════

    static String fail = "";
    static final int PASS = 1, FAIL = 0, SKIP = -1;

    static String sourceOf(String name) {
        try {
            java.io.File here = new java.io.File(".");
            java.io.File[] found = here.listFiles(new java.io.FilenameFilter() {
                public boolean accept(java.io.File d, String n) {
                    return n.startsWith("ChallengeA04a") && n.endsWith(".java");
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

    static String clean(String b) {
        return b == null ? null : b.replaceAll("(?s)/\\*.*?\\*/", "").replaceAll("//.*", "");
    }

    static boolean unwritten(String name) {
        String b = clean(sourceOf(name));
        if (b == null) return true;
        String s = b.replaceAll("[\\s{}]", "");
        return s.equals("return-999;") || s.equals("returnnull;") || s.isEmpty();
    }

    static String banned(String name) {
        String b = clean(sourceOf(name));
        if (b == null) return null;
        String[] bad = {"Arrays.sort", "Arrays.binarySearch", "Collections"};
        for (String x : bad) if (b.contains(x)) return x;
        return null;
    }

    static int[] copy(int[] a){ int[] c = new int[a.length]; System.arraycopy(a,0,c,0,a.length); return c; }
    static int[] ref(int[] a) {                              // the judge's own insertion sort
        int[] c = copy(a);
        for (int i = 1; i < c.length; i++) {
            int last = c[i], j = i - 1;
            while (j >= 0 && c[j] > last) { c[j+1] = c[j]; j--; }
            c[j+1] = last;
        }
        return c;
    }
    static boolean same(int[] a, int[] b) {
        if (a == null || b == null || a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) if (a[i] != b[i]) return false;
        return true;
    }
    static int[] sortedBy(String name, int[] data) {
        int[] in = copy(data), out;
        if (name.equals("BubbleSort")) out = BubbleSort(in);
        else if (name.equals("InsertionSort")) out = InsertionSort(in);
        else out = SelectionSort(in);
        return (out != null) ? out : in;
    }

    // ---- the searches ---------------------------------------------------

    static int tLinear() {
        if (unwritten("LinearSearch")) { fail = "not written yet"; return SKIP; }
        String b = banned("LinearSearch");
        if (b != null) { fail = b + " does the searching for you"; return FAIL; }
        int[] A = {3, 9, 7, 1, 5};
        int[][] cases = {{7,2},{3,0},{5,4},{99,-1}};
        for (int[] c : cases) {
            int got = LinearSearch(copy(A), c[0]);
            if (got != c[1]) { fail = "LinearSearch(.., " + c[0] + ") gave " + got + ", should be " + c[1]; return FAIL; }
        }
        if (LinearSearch(new int[0], 1) != -1) { fail = "an empty array must give -1"; return FAIL; }
        fail = "five cases, including an empty array and a value that is not there";
        return PASS;
    }

    static int tBinary() {
        if (unwritten("BinarySearch")) { fail = "not written yet"; return SKIP; }
        String b = banned("BinarySearch");
        if (b != null) { fail = b + " does the searching for you"; return FAIL; }
        int N = 1048576;
        int[] A = new int[N];
        for (int i = 0; i < N; i++) A[i] = i * 2;
        int[] probes = {0, 2, 1000, 2 * (N - 1), -1, 3, 99999999};
        for (int t : probes) {
            int want = (t >= 0 && t % 2 == 0 && t <= 2 * (N - 1)) ? t / 2 : -1;
            int got = BinarySearch(A, t);
            if (got != want) { fail = "BinarySearch(evens, " + t + ") gave " + got + ", should be " + want; return FAIL; }
        }
        Random rng = new Random(20260812L);
        long t0 = System.nanoTime();
        for (int k = 0; k < 2000; k++) BinarySearch(A, 2 * rng.nextInt(N));
        long binUs = (System.nanoTime() - t0) / 1000;
        long linUs = -1;
        if (!unwritten("LinearSearch")) {                     // measured against THEIR linear search
            Random r2 = new Random(20260812L);
            long t1 = System.nanoTime();
            for (int k = 0; k < 200; k++) LinearSearch(A, 2 * r2.nextInt(N));
            linUs = (System.nanoTime() - t1) / 1000 * 10;     // scale 200 searches up to 2 000
        }
        if (linUs > 0 && binUs * 20 > linUs) {
            fail = "it found everything, but 2 000 searches took " + binUs + " microseconds against your linear "
                 + "search's " + linUs + ". Those are the same order of magnitude — this is a linear search "
                 + "wearing a binary search's name.";
            return FAIL;
        }
        if (linUs < 0 && binUs > 60000) {
            fail = "it found everything, but 2 000 searches of a million values took " + (binUs / 1000)
                 + " ms. A real binary search does that in single figures.";
            return FAIL;
        }
        fail = "correct on 1 048 576 values; 2 000 searches took " + binUs + " microseconds"
             + (linUs > 0 ? " against your linear search's " + linUs : "");
        return PASS;
    }

    // ---- the sorts ------------------------------------------------------

    static int tSort(String name) {
        if (unwritten(name)) { fail = "not written yet"; return SKIP; }
        String b = banned(name);
        if (b != null) { fail = b + " sorted it for you — write the sort"; return FAIL; }
        int[][] fixed = { {}, {5}, {2,1}, {1,2,3,4,5}, {5,4,3,2,1}, {7,7,7,7}, {0,-3,9,-3,4} };
        for (int[] d : fixed) {
            int[] got = sortedBy(name, d);
            if (!same(got, ref(d))) {
                fail = name + java.util.Arrays.toString(d) + " gave " + java.util.Arrays.toString(got)
                     + ", should be " + java.util.Arrays.toString(ref(d));
                return FAIL;
            }
        }
        Random rng = new Random(20260812L);
        for (int t = 0; t < 300; t++) {
            int n = rng.nextInt(18);
            int[] d = new int[n];
            for (int i = 0; i < n; i++) d[i] = rng.nextInt(199) - 99;
            if (!same(sortedBy(name, d), ref(d))) { fail = name + " came out wrong on a random array"; return FAIL; }
        }
        fail = "seven fixed cases and 300 random arrays";
        return PASS;
    }

    // ---- 🍨 the scoop: the swap must sit outside the inner loop ---------


    static int tScoop() {
        if (unwritten("SelectionSort")) { fail = "optional — not attempted"; return SKIP; }
        int r = tSort("SelectionSort");
        if (r != PASS) return r;
        String code = clean(sourceOf("SelectionSort"));
        /* The property that separates them, and it survives any layout: a bubble
           sort exchanges ADJACENT positions -- A[k] with A[k+1] -- while selection
           sort swaps the smallest element home from wherever it was found, which is
           never adjacent by construction. */
        java.util.regex.Matcher adj = java.util.regex.Pattern
                .compile("\\[\\s*(\\w+)\\s*\\][^;]{0,60}?\\[\\s*\\1\\s*\\+\\s*1\\s*\\]"
                       + "|\\[\\s*(\\w+)\\s*\\+\\s*1\\s*\\][^;]{0,60}?\\[\\s*\\2\\s*\\]").matcher(code);
        boolean adjacent = adj.find();
        boolean anySwap = java.util.regex.Pattern
                .compile("\\w+\\s*\\[[^\\]]+\\]\\s*=\\s*\\w+\\s*\\[[^\\]]+\\]").matcher(code).find();
        if (!anySwap) { fail = "no swap found — selection sort swaps in place"; return FAIL; }
        if (adjacent) {
            fail = "it sorts, but it exchanges NEIGHBOURS -- A[k] with A[k+1]. That is a bubble sort "
                 + "wearing this name. Selection sort carries the smallest value home from wherever it "
                 + "was found, which is hardly ever next door.";
            return FAIL;
        }
        fail = "sorts correctly, and it swaps the smallest value home rather than exchanging neighbours";
        return PASS;
    }

    // ---- the race -------------------------------------------------------

    static long time(String name, int[] data) {
        int[] d = copy(data);
        long t0 = System.nanoTime();
        sortedBy(name, d);
        return (System.nanoTime() - t0) / 1000;                   // microseconds
    }

    public static void main(String[] args) {
        String[] labels = {"LinearSearch", "BinarySearch — and it must be logarithmic",
                           "BubbleSort", "InsertionSort"};
        int width = 0;
        for (String l : labels) width = Math.max(width, l.length());
        int passed = 0;

        System.out.println();
        System.out.println("==============================================================================");
        System.out.println("  A4a · JUDGING ALGORITHMS — the judge");
        System.out.println("==============================================================================");

        for (int i = 0; i < labels.length; i++) {
            int r;
            try {
                if (i == 0) r = tLinear();
                else if (i == 1) r = tBinary();
                else if (i == 2) r = tSort("BubbleSort");
                else r = tSort("InsertionSort");
            } catch (Exception e) {
                System.out.println("  X  " + pad(labels[i], width) + "   crashed: " + e); continue;
            }
            System.out.println("  " + ((r == PASS) ? "OK " : (r == SKIP ? " . " : " X ")) + " "
                    + pad(labels[i], width) + "   " + (r == SKIP ? "not reached — " : "") + fail);
            if (r == PASS) passed++;
        }

        System.out.println("------------------------------------------------------------------------------");

        String[] sorts = {"BubbleSort", "InsertionSort", "SelectionSort"};
        boolean any = false;
        for (String s : sorts) if (!unwritten(s)) any = true;
        if (any) {
            int N = 4000;
            Random rng = new Random(20260812L);
            int[] shuffled = new int[N], nearly = new int[N];
            for (int i = 0; i < N; i++) { shuffled[i] = rng.nextInt(100000); nearly[i] = i; }
            for (int i = 0; i < N / 500; i++) {
                int a = rng.nextInt(N - 1);
                int t = nearly[a]; nearly[a] = nearly[a + 1]; nearly[a + 1] = t;
            }
            System.out.println("  [] THE RACE — " + N + " items, the same data, microseconds");
            System.out.printf("     %-16s %12s   %14s%n", "", "shuffled", "nearly sorted");
            for (String s : sorts) {
                if (unwritten(s)) continue;
                try {
                    System.out.printf("     %-16s %,12d   %,14d%n", s.replace("Sort", ""), time(s, shuffled), time(s, nearly));
                } catch (Throwable t) { }
            }
            System.out.println("     Nobody here wrote a better program. They chose a different algorithm.");
        }

        int r;
        try { r = tScoop(); }
        catch (Exception e) { r = FAIL; fail = "crashed: " + e; }
        System.out.println("  " + ((r == PASS) ? "OK " : (r == SKIP ? " . " : " X "))
                + " SECOND SCOOP - SelectionSort   " + fail);

        System.out.println("==============================================================================");
        if (passed == labels.length) {
            System.out.println("  " + passed + " / " + labels.length + ".  Four algorithms, all correct — and the race showed you");
            System.out.println("  the thing the marks never do: which one you should have reached for.");
        } else {
            System.out.println("  " + passed + " / " + labels.length + ".");
            System.out.println("  Read the first failing row. The three that catch nearly everyone: a binary");
            System.out.println("  search whose window never shrinks (Low = Mid instead of Mid + 1); a sort that");
            System.out.println("  sorts but forgets to RETURN the array; and an inner loop whose bound is wrong");
            System.out.println("  by one, which sorts everything except the last pair.");
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
