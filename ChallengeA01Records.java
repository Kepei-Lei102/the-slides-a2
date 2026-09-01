/* ═══════════════════════════════════════════════════════════════════════════
 *  🍦 THE ICE-CREAM CHALLENGE — A1 · Records                    (Java lane)
 * ═══════════════════════════════════════════════════════════════════════════
 *
 *  This is the first rung of the A2 ladder, and it is the program from the top
 *  of the lesson — rebuilt so that it cannot lie to you.
 *
 *      buildClass(names, grades)  ->  an array of records, one per student
 *      sortByGrade(klass)         ->  the SAME people, in grade order
 *
 *  HOW TO USE THIS FILE
 *      1. Fill in the two methods below.
 *      2. Compile and run:     javac ChallengeA01Records.java
 *                              java  ChallengeA01Records
 *      3. Keep going until every row is a ✓.
 *
 *  (The file has to be called ChallengeA01Records.java — Java insists the file
 *   name match the public class. That is not a house rule, it is the language.)
 *
 *  WHY THIS ONE CANNOT BE FAKED
 *      The judge never checks the order alone. It checks that the set of
 *      (name, grade) PAIRS is identical before and after — so if Chen's 58
 *      ends up on Amir, the pairs have changed and you fail, no matter how
 *      beautifully sorted the numbers are.
 *
 *      That is the whole lesson in one assertion: a sort is only correct if
 *      nobody's data changed hands.
 *
 *  BANNED — because these are exactly the mistake the lesson is about
 *      Sorting the grades on their own and writing them back.
 *      Keeping two parallel arrays and sorting them separately.
 *      Touching `names` or `grades` after buildClass() has run.
 * ═══════════════════════════════════════════════════════════════════════════ */

import java.util.Arrays;

public class ChallengeA01Records {

    /* ───────────────────────────────────────────────────────────────────────
     *  The record. In Cambridge pseudocode this is
     *
     *      TYPE TStudent
     *          DECLARE Name  : STRING
     *          DECLARE Grade : INTEGER
     *      ENDTYPE
     *
     *  Java has no TYPE...ENDTYPE, so a record is a small class with fields.
     *  Leave this alone — it is already correct.
     * ─────────────────────────────────────────────────────────────────────── */
    static class Student {
        String name;
        int    grade;
        Student(String name, int grade) { this.name = name; this.grade = grade; }
    }


    /* ═══════════════════════════════════════════════════════════════════════
     *  SCOOP 1 — build the records
     * ═══════════════════════════════════════════════════════════════════════
     *
     *  buildClass({"Amir","Bo"}, {71,94})
     *      ->  { Student("Amir",71), Student("Bo",94) }
     *
     *  The two arrays always have the same length. This is the LAST moment at
     *  which they are allowed to exist separately.
     */
    static Student[] buildClass(String[] names, int[] grades) {
        // YOUR CODE HERE
        return null;
    }


    /* ═══════════════════════════════════════════════════════════════════════
     *  SCOOP 2 — sort them, all of them, together
     * ═══════════════════════════════════════════════════════════════════════
     *
     *  Lowest grade first. Return the sorted array.
     *
     *  Arrays.sort(klass, comparator) is allowed and is the point — but the
     *  comparator has to compare STUDENTS, not loose integers.
     */
    static Student[] sortByGrade(Student[] klass) {
        // YOUR CODE HERE
        return null;
    }


    /* ═══════════════════════════════════════════════════════════════════════
     *  SECOND SCOOP (optional) — keep it stable
     * ═══════════════════════════════════════════════════════════════════════
     *
     *  When two students have the SAME grade they must come out in the order
     *  they went in. A sort that promises this is called STABLE, and it is a
     *  real property with a real name that you will meet again when you write
     *  sorting algorithms yourself.
     */


    /* ────────────────────────────────────────────────────────────────────────
     *  THE JUDGE — you do not need to read past here, but you may
     * ──────────────────────────────────────────────────────────────────────── */

    static String[] pairsOf(String[] names, int[] grades) {
        String[] p = new String[names.length];
        for (int i = 0; i < names.length; i++) p[i] = names[i] + "=" + grades[i];
        Arrays.sort(p);
        return p;
    }

    static String[] pairsOf(Student[] klass) {
        String[] p = new String[klass.length];
        for (int i = 0; i < klass.length; i++) p[i] = klass[i].name + "=" + klass[i].grade;
        Arrays.sort(p);
        return p;
    }

    static int passed = 0, total = 0;

    static void check(String label, String[] names, int[] grades) {
        total++;
        String[] before = pairsOf(names, grades);
        String problem = null;
        try {
            Student[] klass = buildClass(names.clone(), grades.clone());
            if (klass == null) throw new RuntimeException("buildClass returned null");
            Student[] out = sortByGrade(klass);
            if (out == null) throw new RuntimeException("sortByGrade returned null");

            if (out.length != names.length) {
                problem = names.length + " students in, " + out.length + " out";
            } else if (!Arrays.equals(pairsOf(out), before)) {
                problem = "the pairs changed — somebody has the wrong grade"
                        + "\n           in : " + String.join(", ", before)
                        + "\n           out: " + String.join(", ", pairsOf(out));
            } else {
                for (int i = 1; i < out.length; i++) {
                    if (out[i - 1].grade > out[i].grade) {
                        StringBuilder sb = new StringBuilder();
                        for (Student s : out) sb.append(s.grade).append(" ");
                        problem = "not in grade order: " + sb.toString().trim();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            problem = "crashed: " + e.getClass().getSimpleName()
                    + (e.getMessage() == null ? "" : ": " + e.getMessage());
        }

        if (problem == null) { System.out.printf("  ✓  %-22s%n", label); passed++; }
        else                 { System.out.printf("  ✗  %-22s %s%n", label, problem); }
    }

    public static void main(String[] args) {
        String line = "═".repeat(74);
        System.out.println("\n" + line);
        System.out.println("  🍦  A1 · RECORDS — the judge");
        System.out.println(line);

        check("the lesson's own class", new String[]{"Amir","Bo","Chen","Dara","Eve"},
                                        new int[]{71, 94, 58, 88, 63});
        check("already in order",  new String[]{"A","B","C"},        new int[]{1, 2, 3});
        check("exactly backwards", new String[]{"A","B","C"},        new int[]{3, 2, 1});
        check("one student",       new String[]{"Solo"},             new int[]{50});
        check("empty class",       new String[]{},                   new int[]{});
        check("every grade equal", new String[]{"P","Q","R"},        new int[]{60, 60, 60});
        check("negative and zero", new String[]{"X","Y","Z"},        new int[]{0, -5, 12});
        check("duplicate names",   new String[]{"Sam","Sam","Al"},   new int[]{30, 90, 60});

        System.out.println("─".repeat(74));
        boolean stable;
        try {
            String[] n = {"first", "second", "third", "fourth"};
            int[]    g = {70, 70, 70, 70};
            Student[] out = sortByGrade(buildClass(n, g));
            stable = out != null && out.length == 4;
            for (int i = 0; stable && i < 4; i++) stable = out[i].name.equals(n[i]);
        } catch (Exception e) { stable = false; }
        System.out.println("  " + (stable ? "✓" : "·")
            + "  SECOND SCOOP — stable on equal grades"
            + (stable ? "" : "   (optional — ties came out reordered)"));

        System.out.println(line);
        if (passed == total) {
            System.out.println("  " + passed + " / " + total + ".  Everyone kept their own grade. 🍦");
            if (stable) System.out.println("  And ties held their order — that is a stable sort, and it has a name.");
        } else {
            System.out.println("  " + passed + " / " + total + ".");
            System.out.println("  Read the failing line: it tells you whether the ORDER is wrong");
            System.out.println("  or whether somebody is holding the wrong grade. They are very");
            System.out.println("  different bugs, and only one of them is the one this lesson is about.");
        }
        System.out.println(line + "\n");
    }
}
