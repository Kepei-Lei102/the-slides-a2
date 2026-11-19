/*
 * 🍦 THE ICE-CREAM CHALLENGE — A6a · Who Chose the Order?
 * =======================================================
 *
 * Three drawers, real files, and your own code counting its own reads.
 *
 * The judge writes two data files of FIXED-WIDTH records (20 bytes each —
 * 6 for the ID, 14 for the name):
 *
 *     drawers_serial.dat   200 records in ARRIVAL order   (nobody chose it)
 *     drawers_seq.dat      the same 200, sorted by ID     (the key chose it)
 *
 * You process them three ways:
 *
 *     serialFind(sid)          -> {reads, name}   scan from the front
 *     buildIndex()             -> every 10th key of the sorted file
 *     seqFind(sid, index)      -> {reads, name}   index, seek, stop early
 *     hashStore(f, sid, name)  -> slot used       } your own hashed file,
 *     hashFind(f, sid)         -> {probes, name}  } MOD 64 + linear probing
 *
 * WHAT MAKES THIS ONE DIFFERENT
 *     The judge does not time you — A4a taught why milliseconds lie. It
 *     checks the READS you report against the reads the organisation
 *     genuinely needs: a serial find of the 137th arrival must count exactly
 *     137, an absent search must count all 200, and your index must let
 *     seqFind answer WITHOUT reading the whole file. Count honestly; the
 *     judge recomputes every number from the file layout.
 *
 * 🍨 THE SECOND SCOOP — the load-factor curve
 *     loadCurve() fills fresh hash files fuller and fuller (16, 32, 48, 58
 *     of 64 slots) and returns the average probes a find needs at each
 *     load. Nobody in this course has named "load factor" yet. Your own
 *     numbers are about to.
 *
 * RUN IT
 *     javac ChallengeA06aTheThreeDrawers.java
 *     java ChallengeA06aTheThreeDrawers
 */

import java.io.RandomAccessFile;

class ChallengeA06aTheThreeDrawers {

    static final int REC = 20, N = 200, SLOTS = 64;

    /** A find's answer: how many records were read, and what was found. */
    static class Hit {
        int reads; String name;                    // name == null means absent
        Hit(int reads, String name) { this.reads = reads; this.name = name; }
    }

    // ═══ YOUR CODE ══════════════════════════════════════════════════════

    /** Search drawers_serial.dat from the front, one record at a time.
     *  Read RECORD BY RECORD — a byte[REC] per read — counting every one.
     *  Return the trimmed name when the ID matches, or null after the
     *  whole file. */
    static Hit serialFind(int sid) throws Exception {
        // YOUR CODE HERE
        return new Hit(-999, null);
    }

    /** Read drawers_seq.dat ONCE and build the index: the sid of every
     *  10th record (records 0, 10, 20…) with its record number.
     *  ids[k] and posn[k] together are one entry. */
    static int[][] buildIndex() throws Exception {
        // YOUR CODE HERE — return new int[][]{ids, posn}
        return new int[][]{{}, {}};
    }

    /** Find sid in drawers_seq.dat using the index: pick the LAST entry
     *  whose key is <= sid, seek() straight to that record number, read at
     *  most 10 records. Count one read per index entry examined and one
     *  per record read. The file is sorted — STOP EARLY the moment a key
     *  is bigger than sid. */
    static Hit seqFind(int sid, int[][] index) throws Exception {
        // YOUR CODE HERE
        return new Hit(-999, null);
    }

    /** Store one record in the hashed file (SLOTS fixed slots). Home slot
     *  = sid % SLOTS; if taken, probe linearly (wrapping) to the next free
     *  slot — a free slot's first 6 bytes are blank. Return the slot. */
    static int hashStore(String fname, int sid, String name) throws Exception {
        // YOUR CODE HERE
        return -999;
    }

    /** Fetch by computing the address: seek(home slot), read one record,
     *  probe onward while the key there is a DIFFERENT id; stop at a match
     *  (found) or an empty slot (absent). Count every probe. */
    static Hit hashFind(String fname, int sid) throws Exception {
        // YOUR CODE HERE
        return new Hit(-999, null);
    }

    // ═══ 🍨 THE SECOND SCOOP ════════════════════════════════════════════

    /** For each load in (16, 32, 48, 58): blankHashFile a FRESH file,
     *  store that many records (use curveRecords(load)), then hashFind
     *  every stored ID and average the probes. Four averages back. */
    static double[] loadCurve() throws Exception {
        // YOUR CODE HERE
        return new double[0];
    }

    // ════════════════════════════════════════════════════════════════════
    //  THE JUDGE — everything below this line is the marking, not the work
    // ════════════════════════════════════════════════════════════════════

    static final String[] NAMES = {"Ada", "Grace", "Alan", "Edsger", "Barbara", "Tim",
        "Vint", "Radia", "Donald", "Ken", "Dennis", "Bjarne", "Guido", "James", "Linus", "Margaret"};

    /** Deterministic records — same idea as the Python twin's seeded sample. */
    static int[][] records() {
        java.util.Random rng = new java.util.Random(20260815L);
        java.util.LinkedHashSet<Integer> ids = new java.util.LinkedHashSet<Integer>();
        while (ids.size() < N) ids.add(1000 + rng.nextInt(9000));
        int[][] out = new int[N][2];
        int i = 0;
        for (int sid : ids) { out[i][0] = sid; out[i][1] = i % NAMES.length; i++; }
        return out;
    }

    static void write(String fname, int[][] recs) throws Exception {
        try (RandomAccessFile f = new RandomAccessFile(fname, "rw")) {
            f.setLength(0);
            for (int[] r : recs)
                f.writeBytes(String.format("%-6d%-14s", r[0], NAMES[r[1]]));
        }
    }

    /** Helper for you: a fresh hashed file of SLOTS blank slots. */
    static void blankHashFile(String fname) throws Exception {
        try (RandomAccessFile f = new RandomAccessFile(fname, "rw")) {
            f.setLength(0);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < REC * SLOTS; i++) sb.append(' ');
            f.writeBytes(sb.toString());
        }
    }

    /** Helper for you: `load` deterministic records for the scoop. */
    static int[][] curveRecords(int load) {
        java.util.Random rng = new java.util.Random(load);
        java.util.LinkedHashSet<Integer> ids = new java.util.LinkedHashSet<Integer>();
        while (ids.size() < load) ids.add(1000 + rng.nextInt(9000));
        int[][] out = new int[load][2];
        int i = 0;
        for (int sid : ids) { out[i][0] = sid; out[i][1] = i % NAMES.length; i++; }
        return out;
    }

    static String fail = "";
    static final int PASS = 1, FAIL = 0, SKIP = -1;

    static String wholeSource() {
        try {
            java.io.File[] found = new java.io.File(".").listFiles(new java.io.FilenameFilter() {
                public boolean accept(java.io.File d, String n) {
                    return n.startsWith("ChallengeA06a") && n.endsWith(".java");
                }
            });
            if (found == null || found.length == 0) return null;
            return new String(java.nio.file.Files.readAllBytes(found[0].toPath()), "UTF-8");
        } catch (Exception e) { return null; }
    }

    static boolean unwritten(String name) {
        String src = wholeSource();
        if (src == null) return true;
        java.util.regex.Matcher m = java.util.regex.Pattern
                .compile("(?m)^\\s*static [\\w\\[\\]]+ " + name + "\\(").matcher(src);
        if (!m.find()) return true;
        int open = src.indexOf('{', m.start()), depth = 0;
        for (int k = open; k < src.length(); k++) {
            char ch = src.charAt(k);
            if (ch == '{') depth++;
            if (ch == '}') { depth--; if (depth == 0) {
                String b = src.substring(open, k)
                        .replaceAll("(?s)/\\*.*?\\*/", "").replaceAll("//.*", "").replaceAll("[\\s{}]", "");
                /* braces are stripped above, so the array-literal placeholder
                   must be compared without them too */
                return b.isEmpty() || b.equals("returnnewHit(-999,null);")
                    || b.equals("returnnewint[][],;") || b.equals("return-999;")
                    || b.equals("returnnewdouble[0];");
            }}
        }
        return true;
    }

    static int[][] sortedRecs(int[][] recs) {
        int[][] s = recs.clone();
        java.util.Arrays.sort(s, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) { return a[0] - b[0]; }
        });
        return s;
    }

    static int tSerial(int[][] recs) throws Exception {
        if (unwritten("serialFind")) { fail = "not written yet"; return SKIP; }
        int[] positions = {0, 42, 136, N - 1};
        for (int pos : positions) {
            int sid = recs[pos][0];
            String want = NAMES[recs[pos][1]];
            Hit h = serialFind(sid);
            if (!want.equals(h.name)) {
                fail = "serialFind(" + sid + ") returned " + h.name + ", the file says " + want;
                return FAIL;
            }
            if (h.reads != pos + 1) {
                fail = "serialFind(" + sid + ") reported " + h.reads + " reads. That ID is the record at "
                     + "position " + pos + ", so an honest front-to-back search reads exactly " + (pos + 1)
                     + " records — count every read of REC bytes.";
                return FAIL;
            }
        }
        Hit h = serialFind(4);
        if (h.name != null) { fail = "serialFind(4) found " + h.name + " — 4 is not in the file"; return FAIL; }
        if (h.reads != N) {
            fail = "serialFind(4) reported " + h.reads + " reads for an ABSENT id. A serial file has no "
                 + "shortcut: proving absence costs all " + N + " records — the mark scheme's own bullet.";
            return FAIL;
        }
        fail = "four finds at their exact read counts, and absence honestly costs all " + N;
        return PASS;
    }

    static int tIndex(int[][] recs) throws Exception {
        if (unwritten("buildIndex")) { fail = "not written yet"; return SKIP; }
        int[][] ix = buildIndex();
        int[][] srt = sortedRecs(recs);
        int wantLen = (N + 9) / 10;
        if (ix == null || ix.length != 2 || ix[0].length != wantLen) {
            fail = "the index should hold every 10th record of the SORTED file — " + wantLen
                 + " entries; got " + (ix == null || ix.length != 2 ? "the wrong shape" : ix[0].length + " entries");
            return FAIL;
        }
        for (int k = 0; k < wantLen; k++) {
            if (ix[0][k] != srt[k * 10][0] || ix[1][k] != k * 10) {
                fail = "index entry " + k + " is (" + ix[0][k] + ", " + ix[1][k] + "), should be ("
                     + srt[k * 10][0] + ", " + (k * 10) + ")";
                return FAIL;
            }
        }
        fail = wantLen + " entries, every 10th key of the sorted file";
        return PASS;
    }

    static int tSeq(int[][] recs) throws Exception {
        if (unwritten("seqFind") || unwritten("buildIndex")) { fail = "not written yet"; return SKIP; }
        int[][] ix = buildIndex();
        int[][] srt = sortedRecs(recs);
        int bound = ix[0].length + 11;
        int[] positions = {0, 57, 123, N - 1};
        for (int pos : positions) {
            int sid = srt[pos][0];
            String want = NAMES[srt[pos][1]];
            Hit h = seqFind(sid, ix);
            if (!want.equals(h.name)) {
                fail = "seqFind(" + sid + ") returned " + h.name + ", the file says " + want;
                return FAIL;
            }
            if (h.reads < 1 || h.reads > bound) {
                fail = "seqFind(" + sid + ") reported " + h.reads + " reads — the index (" + ix[0].length
                     + " entries) plus at most 10 records is " + bound + ". Jump with seek(); do not scan.";
                return FAIL;
            }
        }
        Hit h = seqFind(4, ix);
        if (h.name != null) { fail = "seqFind(4) found " + h.name + " — 4 is not in the file"; return FAIL; }
        if (h.reads > bound) {
            fail = "seqFind(4) reported " + h.reads + " reads for an absent id. The file is SORTED: stop "
                 + "at the first key bigger than the target — order is what makes absence cheap.";
            return FAIL;
        }
        fail = "index + jump + stop-early, absence included, never past " + bound + " reads";
        return PASS;
    }

    static int tHash() throws Exception {
        if (unwritten("hashStore") || unwritten("hashFind")) { fail = "not written yet"; return SKIP; }
        blankHashFile("drawers_hash.dat");
        int[][] recs = curveRecords(40);
        int[] slotOf = new int[10000];
        java.util.Arrays.fill(slotOf, -1);
        boolean[] taken = new boolean[SLOTS];
        int[] probesTruth = new int[10000];
        for (int[] r : recs) {                        // the judge's own simulation
            int hh = r[0] % SLOTS, p = 1;
            while (taken[hh]) { hh = (hh + 1) % SLOTS; p++; }
            taken[hh] = true; slotOf[r[0]] = hh; probesTruth[r[0]] = p;
        }
        for (int[] r : recs) {
            int got = hashStore("drawers_hash.dat", r[0], NAMES[r[1]]);
            if (got != slotOf[r[0]]) {
                fail = "hashStore(" + r[0] + ") used slot " + got + " — home slot " + (r[0] % SLOTS)
                     + " plus linear probing lands on " + slotOf[r[0]] + ". Probe one slot at a time, wrapping.";
                return FAIL;
            }
        }
        for (int[] r : recs) {
            Hit h = hashFind("drawers_hash.dat", r[0]);
            if (!NAMES[r[1]].equals(h.name)) {
                fail = "hashFind(" + r[0] + ") returned " + h.name + ", should be " + NAMES[r[1]];
                return FAIL;
            }
            if (h.reads != probesTruth[r[0]]) {
                fail = "hashFind(" + r[0] + ") reported " + h.reads + " probes; the layout needs exactly "
                     + probesTruth[r[0]] + " — count every record read, including the hit.";
                return FAIL;
            }
        }
        Hit h = hashFind("drawers_hash.dat", 1);
        if (h.name != null) { fail = "hashFind(1) found " + h.name + " — 1 was never stored"; return FAIL; }
        if (h.reads > SLOTS) { fail = "hashFind(1) probed " + h.reads + " times — an empty slot ends the search"; return FAIL; }
        fail = "40 records at their exact slots and probe counts, absence stops at an empty slot";
        return PASS;
    }

    static int tCurve() throws Exception {
        if (unwritten("loadCurve")) { fail = "not written yet"; return SKIP; }
        if (unwritten("hashStore") || unwritten("hashFind")) { fail = "needs the hashed drawer first"; return SKIP; }
        double[] got = loadCurve();
        if (got == null || got.length != 4) {
            fail = "loadCurve() should return four averages, one per load (16, 32, 48, 58)"; return FAIL;
        }
        for (double v : got) if (v < 1.0) {
            fail = "an average probe count below 1.0 is impossible — every find reads at least once ("
                 + java.util.Arrays.toString(got) + ")";
            return FAIL;
        }
        if (!(got[3] > got[0] + 0.3)) {
            fail = "the curve is too flat (" + java.util.Arrays.toString(got) + "). At 58 of 64 slots the "
                 + "drawer is 91% full and probe chains bite — if your numbers do not climb, the finds "
                 + "are not really probing.";
            return FAIL;
        }
        if (got[0] > 1.6) {
            fail = String.format("%.2f average probes at only 16/64 full is too many — check the home slot", got[0]);
            return FAIL;
        }
        fail = String.format("avg probes %.2f → %.2f → %.2f → %.2f as the drawer fills. That curve has a name: load factor",
                got[0], got[1], got[2], got[3]);
        return PASS;
    }

    public static void main(String[] args) throws Exception {
        int[][] recs = records();
        write("drawers_serial.dat", recs);
        write("drawers_seq.dat", sortedRecs(recs));

        String[] labels = {"serial — count every read, absence costs the file",
                           "the index — small, sorted, every 10th key",
                           "sequential — jump, scan, stop early",
                           "random — exact slots, exact probes"};
        int width = 0;
        for (String l : labels) width = Math.max(width, l.length());
        int passed = 0;

        System.out.println();
        System.out.println("==============================================================================");
        System.out.println("  A6a · THE THREE DRAWERS — the judge");
        System.out.println("==============================================================================");

        for (int i = 0; i < labels.length; i++) {
            int r;
            try { r = (i == 0) ? tSerial(recs) : (i == 1) ? tIndex(recs) : (i == 2) ? tSeq(recs) : tHash(); }
            catch (Throwable t) { System.out.println("  X  " + pad(labels[i], width) + "   crashed: " + t); continue; }
            System.out.println("  " + ((r == PASS) ? "OK " : (r == SKIP ? " . " : " X ")) + " "
                    + pad(labels[i], width) + "   " + (r == SKIP ? "not reached — " : "") + fail);
            if (r == PASS) passed++;
        }

        System.out.println("------------------------------------------------------------------------------");
        int r5;
        try { r5 = tCurve(); } catch (Throwable t) { r5 = FAIL; fail = "crashed: " + t; }
        System.out.println("  " + ((r5 == PASS) ? "OK " : (r5 == SKIP ? " . " : " X "))
                + " SECOND SCOOP [ice cream] — the load-factor curve   " + fail);

        System.out.println("==============================================================================");
        if (passed == labels.length && r5 == PASS) {
            System.out.println("  " + passed + " / " + labels.length + ", and the curve too. Three drawers, three prices, and your own");
            System.out.println("  numbers just discovered why hash tables are kept part-empty on purpose.");
        } else {
            System.out.println("  " + passed + " / " + labels.length + ".");
            System.out.println("  Read the first failing row. The two that catch nearly everyone: counting");
            System.out.println("  something other than records (read REC bytes at a time and count each one),");
            System.out.println("  and a seqFind that quietly scans from record 0 instead of seeking to where");
            System.out.println("  the index points.");
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
