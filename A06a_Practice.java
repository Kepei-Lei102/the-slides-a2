/*
 * A6a · WHO CHOSE THE ORDER? — questions 5 to 7
 * ==============================================
 *
 * Questions 1 to 4 are on the printed sheet. These three are the code half.
 *
 * HOW TO USE THIS FILE
 *     1. Fill in the three methods marked YOUR CODE HERE.
 *     2. javac A06a_Practice.java   then   java A06a_Practice
 *     3. Keep going until every row is a tick.
 *
 * SUBMIT AS   A06a_<YourName>.java     (rename the file; the class is not
 *             public, so nothing inside has to change, and the judge finds
 *             its own source whatever you call it.)
 *
 * WHY THERE IS NO MARK SCHEME BEHIND THESE
 *     File organisation is a PAPER 3 topic. Paper 4 has never asked anyone to
 *     write a sequential or random file at a keyboard, so there is no CIE
 *     bullet list for any of this and this file does not pretend otherwise.
 *     What it does instead is make you keep a file IN ORDER with your own
 *     hands — the thing the exam only ever asks you to describe.
 *
 * THE FILES
 *     Every file here is a text file, one record per line, in the form
 *
 *         key,name          e.g.   1737,Ada
 *
 *     where key is a whole number and the file is a SEQUENTIAL file: the
 *     records sit in ascending order of key, and no key appears twice.
 *     The judge works on real files, in a folder it makes for itself, and
 *     it reads what your code left on disk — not what your code returned.
 *
 *     Two helpers are provided at the bottom so that the file-handling does
 *     not eat the lesson:  readLines(path)  and  writeLines(path, lines).
 *     Use them, or java.nio / java.io directly — either is fine.
 */

import java.util.*;
import java.nio.file.*;

class A06a_Practice {

    // ── 5 · put a record IN ITS PLACE ───────────────────────────────── [3]
    //
    // The file at `path` is a sequential file (ascending key order). Insert
    // the record  key,name  so that the file is STILL in order afterwards.
    //
    //     before:  1043,Blessing / 1519,Marcus / 2049,Grace
    //     insert   1737,Ada
    //     after:   1043,Blessing / 1519,Marcus / 1737,Ada / 2049,Grace
    //
    // The file may be empty, or not exist yet — then it ends up with one line.
    // The key is never already present.
    //
    // You cannot open a text file and "make room" in the middle. The exam's
    // own mark scheme says what has to happen instead: "a new version of the
    // file has to be created". So: read it, write a new one with the record
    // in place. (Reading everything into a list and writing it back is fine
    // for question 5. Question 6 is where you learn to do it in one pass.)
    //
    static void insertSequential(String path, int key, String name) throws Exception {
        // YOUR CODE HERE
    }

    // ── 6 · two ordered files become one, in ONE pass ───────────────── [3]
    //
    // `a` and `b` are sequential files with no key in common. Write the file
    // `out` containing every record from both, still in ascending key order.
    //
    //     a:   1043,Blessing / 1737,Ada / 2926,Edsger
    //     b:   1204,Ella / 2049,Grace
    //     out: 1043,Blessing / 1204,Ella / 1737,Ada / 2049,Grace / 2926,Edsger
    //
    // Either file may be empty. Do it the way the lesson's merge did it:
    // one finger on each file, always copy the smaller key, move that
    // finger on. Never sort — the whole point is that ordered files merge
    // WITHOUT sorting, which is why payroll runs are batches.
    //
    static void mergeSorted(String a, String b, String out) throws Exception {
        // YOUR CODE HERE
    }

    // ── 7 · 🍦 the payroll run ───────────────────────────────────────── [2]
    //
    // The oldest sequential-file job there is: a MASTER file and a
    // TRANSACTION file, both sorted by key, become a NEW master file, in a
    // single pass over both.
    //
    // Transaction lines have THREE fields:   key,op,name
    //
    //     op = A   add     — a new record (the key is not in the master)
    //     op = D   delete  — remove that record (the key is in the master;
    //                        the name field is present but empty: "2340,D,")
    //     op = U   update  — replace that record's name (the key is in the master)
    //
    // Each key appears at most once in the transaction file. Write `out`:
    // the new master, in ascending key order, one record per line as usual.
    //
    //     master: 1043,Blessing / 1737,Ada / 2340,Alan / 2926,Edsger
    //     trans:  1250,A,Vint / 1737,U,Ada Lovelace / 2340,D,
    //     out:    1043,Blessing / 1250,Vint / 1737,Ada Lovelace / 2926,Edsger
    //
    // Same two-finger walk as question 6, with a decision at every step.
    //
    static void applyBatch(String master, String trans, String out) throws Exception {
        // YOUR CODE HERE
    }

    // ── helpers you may use ─────────────────────────────────────────────

    /** Every line of the file, in order; an empty list if it does not exist. */
    static List<String> readLines(String path) throws Exception {
        Path p = Paths.get(path);
        if (!Files.exists(p)) return new ArrayList<>();
        List<String> out = new ArrayList<>();
        for (String s : Files.readAllLines(p)) if (!s.isEmpty()) out.add(s);
        return out;
    }

    /** Replace the file with exactly these lines. */
    static void writeLines(String path, List<String> lines) throws Exception {
        Files.write(Paths.get(path), lines);
    }

    /** The key of a record line — the number before the first comma. */
    static int keyOf(String line) {
        return Integer.parseInt(line.substring(0, line.indexOf(',')));
    }

    // ════════════════════════════════════════════════════════════════════
    //  THE JUDGE — everything below this line is the marking, not the work
    // ════════════════════════════════════════════════════════════════════

    static String fail = "";
    static final int PASS = 1, FAIL = 0, SKIP = -1;
    static Path dir;

    static String sourceOf(String name) {
        try {
            java.io.File here = new java.io.File(".");
            java.io.File[] found = here.listFiles(new java.io.FilenameFilter() {
                public boolean accept(java.io.File d, String n) {
                    return n.startsWith("A06a") && n.endsWith(".java");
                }
            });
            if (found == null || found.length == 0) return null;
            String src = new String(Files.readAllBytes(found[0].toPath()), "UTF-8");
            java.util.regex.Matcher m = java.util.regex.Pattern
                    .compile("(?m)^\\s*static\\s+[\\w\\[\\]<>, ]+?\\s+" + name + "\\s*\\(").matcher(src);
            if (!m.find()) return null;
            int open = src.indexOf('{', m.start()), depth = 0;
            for (int k = open; k < src.length(); k++) {
                char ch = src.charAt(k);
                if (ch == '{') depth++;
                if (ch == '}') { depth--; if (depth == 0) return src.substring(open, k); }
            }
        } catch (Exception e) { return null; }
        return null;
    }

    static boolean unwritten(String name) {
        String b = sourceOf(name);
        if (b == null) return true;
        b = b.replaceAll("(?s)/\\*.*?\\*/", "").replaceAll("//.*", "").replaceAll("[\\s{}]", "");
        return b.isEmpty();
    }

    // ---- the references the student's files are measured against ---------

    static String f(String stem) { return dir.resolve(stem + ".txt").toString(); }

    static List<String> refInsert(List<String> lines, int key, String name) {
        List<String> out = new ArrayList<>(lines);
        int i = 0;
        while (i < out.size() && keyOf(out.get(i)) < key) i++;
        out.add(i, key + "," + name);
        return out;
    }

    static List<String> refMerge(List<String> a, List<String> b) {
        List<String> out = new ArrayList<>();
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            if (keyOf(a.get(i)) < keyOf(b.get(j))) out.add(a.get(i++)); else out.add(b.get(j++));
        }
        while (i < a.size()) out.add(a.get(i++));
        while (j < b.size()) out.add(b.get(j++));
        return out;
    }

    static List<String> refBatch(List<String> master, List<String> trans) {
        List<String> out = new ArrayList<>();
        int i = 0, j = 0;
        while (i < master.size() || j < trans.size()) {
            int mk = i < master.size() ? keyOf(master.get(i)) : Integer.MAX_VALUE;
            int tk = j < trans.size() ? keyOf(trans.get(j)) : Integer.MAX_VALUE;
            if (mk < tk) { out.add(master.get(i++)); continue; }
            String[] t = trans.get(j).split(",", 3);
            if (tk < mk) {                       // A: a new record
                out.add(tk + "," + t[2]); j++; continue;
            }
            // same key: D or U
            if (t[1].equals("U")) out.add(tk + "," + t[2]);
            i++; j++;
        }
        return out;
    }

    static boolean isSorted(List<String> lines) {
        for (int i = 1; i < lines.size(); i++) if (keyOf(lines.get(i - 1)) >= keyOf(lines.get(i))) return false;
        return true;
    }

    static String show(List<String> l) {
        return l.size() <= 6 ? l.toString() : l.subList(0, 6) + "… (" + l.size() + " lines)";
    }

    static String diff(String what, List<String> got, List<String> want) {
        if (got.equals(want)) return null;
        if (!isSorted(got) && new HashSet<>(got).equals(new HashSet<>(want)))
            return what + " has every record but NOT in key order — " + show(got)
                 + "\n         (a record went on the END: that is a serial append, and this is a sequential file)";
        if (got.size() < want.size()) return what + " lost a record: got " + show(got) + ", should be " + show(want);
        if (got.size() > want.size()) return what + " has a record too many: got " + show(got) + ", should be " + show(want);
        return what + " is " + show(got) + ", should be " + show(want);
    }

    static int tInsert() throws Exception {
        if (unwritten("insertSequential")) { fail = "not written yet"; return SKIP; }
        String p = f("seq");
        // named cases: empty file, front, middle, end
        Object[][] cases = {
            { new String[]{}, 1737, "Ada" },
            { new String[]{"1519,Marcus", "2049,Grace"}, 1043, "Blessing" },
            { new String[]{"1043,Blessing", "1519,Marcus", "2049,Grace"}, 1737, "Ada" },
            { new String[]{"1043,Blessing", "1519,Marcus", "2049,Grace"}, 2926, "Edsger" },
        };
        String[] where = {"into an EMPTY file", "at the FRONT", "in the MIDDLE", "at the END"};
        for (int c = 0; c < cases.length; c++) {
            List<String> before = new ArrayList<>(Arrays.asList((String[]) cases[c][0]));
            Files.deleteIfExists(Paths.get(p));
            if (!before.isEmpty()) writeLines(p, before);
            try { insertSequential(p, (Integer) cases[c][1], (String) cases[c][2]); }
            catch (Exception e) { fail = "crashed inserting " + where[c] + ": " + e; return FAIL; }
            if (!Files.exists(Paths.get(p))) { fail = "after inserting " + where[c] + " the file does not exist — did you write to a different name?"; return FAIL; }
            String d = diff("inserting " + where[c] + ", the file", readLines(p),
                            refInsert(before, (Integer) cases[c][1], (String) cases[c][2]));
            if (d != null) { fail = d; return FAIL; }
        }
        // 60 shuffled keys, one at a time, into a fresh file
        Random r = new Random(6061);
        List<Integer> keys = new ArrayList<>();
        for (int k = 1000; k < 1060; k++) keys.add(k);
        Collections.shuffle(keys, r);
        Files.deleteIfExists(Paths.get(p));
        List<String> ref = new ArrayList<>();
        for (int k : keys) {
            insertSequential(p, k, "n" + k);
            ref = refInsert(ref, k, "n" + k);
        }
        String d = diff("after 60 shuffled inserts, the file", readLines(p), ref);
        if (d != null) { fail = d; return FAIL; }
        fail = "empty / front / middle / end, then 60 shuffled keys one at a time — in order every time";
        return PASS;
    }

    static int tMerge() throws Exception {
        if (unwritten("mergeSorted")) { fail = "not written yet"; return SKIP; }
        String a = f("a"), b = f("b"), o = f("out");
        String[][][] cases = {
            { {"1043,Blessing", "1737,Ada", "2926,Edsger"}, {"1204,Ella", "2049,Grace"} },
            { {"1,x", "2,y", "3,z"}, {} },
            { {}, {"1,x", "2,y", "3,z"} },
            { {}, {} },
            { {"1,a", "2,b", "3,c"}, {"4,d", "5,e", "6,f"} },      // all of a before all of b
            { {"4,d", "5,e", "6,f"}, {"1,a", "2,b", "3,c"} },      // and the reverse
            { {"1,a", "3,c", "5,e"}, {"2,b", "4,d", "6,f"} },      // fully interleaved
        };
        String[] names = {"the lesson's five", "b empty", "a empty", "both empty", "a entirely first", "b entirely first", "interleaved"};
        for (int c = 0; c < cases.length; c++) {
            List<String> la = Arrays.asList(cases[c][0]), lb = Arrays.asList(cases[c][1]);
            writeLines(a, la); writeLines(b, lb); Files.deleteIfExists(Paths.get(o));
            try { mergeSorted(a, b, o); }
            catch (Exception e) { fail = "crashed on \"" + names[c] + "\": " + e; return FAIL; }
            if (!Files.exists(Paths.get(o))) { fail = "on \"" + names[c] + "\" the output file was never written"; return FAIL; }
            String d = diff("on \"" + names[c] + "\", the output", readLines(o), refMerge(la, lb));
            if (d != null) { fail = d; return FAIL; }
        }
        // random splits of 200 keys
        Random r = new Random(6062);
        for (int t = 0; t < 20; t++) {
            List<String> la = new ArrayList<>(), lb = new ArrayList<>();
            for (int k = 0; k < 200; k++) (r.nextBoolean() ? la : lb).add((k * 7 + 3) + ",n" + k);
            writeLines(a, la); writeLines(b, lb);
            mergeSorted(a, b, o);
            String d = diff("on a random split of 200 records, the output", readLines(o), refMerge(la, lb));
            if (d != null) { fail = d; return FAIL; }
        }
        fail = "seven named cases (both empties, one file entirely first) and twenty random splits of 200";
        return PASS;
    }

    static int tBatch() throws Exception {
        if (unwritten("applyBatch")) { fail = "not written yet"; return SKIP; }
        String m = f("master"), t = f("trans"), o = f("new");
        List<String> master = Arrays.asList("1043,Blessing", "1737,Ada", "2340,Alan", "2926,Edsger");
        List<String> trans  = Arrays.asList("1250,A,Vint", "1737,U,Ada Lovelace", "2340,D,");
        writeLines(m, master); writeLines(t, trans); Files.deleteIfExists(Paths.get(o));
        try { applyBatch(m, t, o); }
        catch (Exception e) { fail = "crashed on the example batch: " + e; return FAIL; }
        if (!Files.exists(Paths.get(o))) { fail = "the new master was never written"; return FAIL; }
        String d = diff("on the example batch, the new master", readLines(o), refBatch(master, trans));
        if (d != null) { fail = d; return FAIL; }
        // edge: transactions past the end of the master, and before its start
        master = Arrays.asList("500,mid");
        trans  = Arrays.asList("100,A,first", "500,U,middle", "900,A,last");
        writeLines(m, master); writeLines(t, trans); applyBatch(m, t, o);
        d = diff("with adds before and after the whole master, the new master", readLines(o), refBatch(master, trans));
        if (d != null) { fail = d; return FAIL; }
        // edge: delete everything; empty transaction file
        master = Arrays.asList("1,a", "2,b"); trans = Arrays.asList("1,D,", "2,D,");
        writeLines(m, master); writeLines(t, trans); applyBatch(m, t, o);
        d = diff("deleting every record, the new master", readLines(o), refBatch(master, trans));
        if (d != null) { fail = d; return FAIL; }
        writeLines(m, master); writeLines(t, new ArrayList<String>()); applyBatch(m, t, o);
        d = diff("with an EMPTY transaction file, the new master", readLines(o), master);
        if (d != null) { fail = d; return FAIL; }
        // random batches
        Random r = new Random(6063);
        for (int c = 0; c < 20; c++) {
            List<String> ms = new ArrayList<>(), ts = new ArrayList<>();
            for (int k = 0; k < 120; k++) {
                int key = k * 5 + 10; boolean inMaster = r.nextInt(3) > 0;
                if (inMaster) ms.add(key + ",m" + k);
                int op = r.nextInt(4);
                if (!inMaster && op == 0) ts.add(key + ",A,new" + k);
                else if (inMaster && op == 1) ts.add(key + ",D,");
                else if (inMaster && op == 2) ts.add(key + ",U,upd" + k);
            }
            writeLines(m, ms); writeLines(t, ts); applyBatch(m, t, o);
            d = diff("on a random batch of " + ts.size() + " transactions, the new master", readLines(o), refBatch(ms, ts));
            if (d != null) { fail = d; return FAIL; }
        }
        fail = "the example, adds off both ends, delete-everything, an empty batch, and twenty random batches";
        return PASS;
    }

    public static void main(String[] args) throws Exception {
        dir = Files.createTempDirectory("a06a_judge_");
        String[] labels = {"5 · insertSequential", "6 · mergeSorted", "7 · applyBatch 🍦"};
        int width = 0;
        for (String l : labels) width = Math.max(width, l.length());
        int passed = 0, marks = 0;
        int[] worth = {3, 3, 2};

        System.out.println();
        System.out.println("==============================================================================");
        System.out.println("  A6a · questions 5 to 7 — the judge   (files in " + dir + ")");
        System.out.println("==============================================================================");

        int[] res = new int[3];
        for (int i = 0; i < labels.length; i++) {
            int r;
            try { r = (i == 0) ? tInsert() : (i == 1) ? tMerge() : tBatch(); }
            catch (Throwable t) { System.out.println("  X  " + pad(labels[i], width) + "   crashed: " + t); res[i] = FAIL; continue; }
            res[i] = r;
            System.out.println("  " + ((r == PASS) ? "OK " : (r == SKIP ? " . " : " X ")) + " "
                    + pad(labels[i], width) + "   " + (r == SKIP ? "not reached — " : "") + fail);
            if (r == PASS) { passed++; marks += worth[i]; }
        }

        System.out.println("------------------------------------------------------------------------------");

        // the payoff: the SAME 2 000 records, arriving one at a time through THEIR
        // question 5, and then as one batch through THEIR question 6
        if (res[0] == PASS && res[1] == PASS) {
            final int N = 2000;
            List<Integer> keys = new ArrayList<>();
            for (int k = 0; k < N; k++) keys.add(k * 3 + 1);
            Collections.shuffle(keys, new Random(6060));
            String one = f("one_at_a_time");
            Files.deleteIfExists(Paths.get(one));
            long t0 = System.nanoTime();
            for (int k : keys) insertSequential(one, k, "n" + k);
            double tIns = (System.nanoTime() - t0) / 1_000_000.0;

            List<String> sorted = new ArrayList<>();
            for (int k = 0; k < N; k++) sorted.add((k * 3 + 1) + ",n" + (k * 3 + 1));
            String batch = f("batch"), empty = f("empty"), merged = f("merged");
            writeLines(batch, sorted); writeLines(empty, new ArrayList<String>());
            t0 = System.nanoTime();
            mergeSorted(empty, batch, merged);
            double tMrg = (System.nanoTime() - t0) / 1_000_000.0;

            boolean same = readLines(one).equals(readLines(merged));
            System.out.println("  [] The SAME " + N + " records, kept in key order:");
            System.out.printf("     one at a time, through your insertSequential:  %8.1f ms%n", tIns);
            System.out.printf("     as one batch, through your mergeSorted:        %8.1f ms%n", tMrg);
            if (!same)
                System.out.println("     …and the two files DIFFER, which they must not. Check both before reading on.");
            else if (tIns >= 5 * tMrg)
                System.out.println("     Each insert rewrote the whole file — " + N + " inserts wrote about " + (N * (long) N / 2)
                                 + " records to disk to keep " + N + " in order.\n     The batch wrote them once. That is why payroll is a batch,"
                                 + " and why the exam calls sequential files' strength\n     \"processing every record in order\" and never \"adding one\".");
            else
                System.out.println("     Closer than the lesson promised. Raise N in the source to 10 000 and run again —"
                                 + "\n     one of these grows like N, the other like N squared, and it shows soon.");
        }

        System.out.println("==============================================================================");
        System.out.println("  " + marks + " / 8 marks   (" + passed + " of 3 questions complete)");
        if (passed == labels.length) {
            System.out.println("  You kept a file in order, merged two without sorting, and ran a payroll.");
            System.out.println("  Every mark scheme sentence about sequential files is now something you did. 🍦");
        } else {
            System.out.println("  Read the first failing row. The two that catch nearly everyone: appending the");
            System.out.println("  new record on the end (a serial habit), and forgetting the tail of whichever");
            System.out.println("  file did not run out first when merging.");
        }
        System.out.println("==============================================================================");
        System.out.println();

        // leave nothing behind
        try (java.util.stream.Stream<Path> s = Files.walk(dir)) {
            s.sorted(Comparator.reverseOrder()).forEach(p -> { try { Files.delete(p); } catch (Exception e) {} });
        }
    }

    static String pad(String s, int w) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < w) sb.append(' ');
        return sb.toString();
    }
}
