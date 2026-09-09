/* ═══════════════════════════════════════════════════════════════════════════
 *  🍦 THE ICE-CREAM CHALLENGE — A2a · Stack and Queue            (Java lane)
 * ═══════════════════════════════════════════════════════════════════════════
 *
 *  Build both structures from an array and some integers. Nothing else.
 *
 *      Stack :  Push(Data) -> true/false        Pop() -> the item, or -1
 *      Queue :  Enqueue(Data) -> true/false     Dequeue() -> the item, or -1
 *
 *  These are the signatures Cambridge marks, so learn them here rather than in
 *  the exam hall:
 *
 *      Push / Enqueue  return  FALSE  when the structure is FULL, TRUE otherwise.
 *      Pop  / Dequeue  return  -1     when the structure is EMPTY.
 *
 *  HOW TO USE THIS FILE
 *      1. Fill in the four methods below.
 *      2. Compile and run:   javac ChallengeA02aStackQueue.java
 *                            java  ChallengeA02aStackQueue
 *      3. Keep going until every row is a ✓.
 *
 *  BANNED — the machine may not do your homework
 *      java.util.Stack     java.util.Queue      java.util.ArrayDeque
 *      java.util.LinkedList                     java.util.List of any kind
 *
 *      You get a fixed-size array and integer indices, which is exactly what
 *      the exam gives you. Importing a Stack to implement a Stack is not a
 *      clever shortcut; it is answering a different question.
 *
 *  WHAT THE JUDGE ACTUALLY DOES
 *      The last test runs TEN THOUSAND random operations against a reference
 *      model and compares every single return value. A structure that works
 *      for the first twenty operations and then quietly drifts will be caught
 *      on operation 3,847 and told exactly which one disagreed.
 * ═══════════════════════════════════════════════════════════════════════════ */

public class ChallengeA02aStackQueue {

    static final int SIZE = 8;          // deliberately small, so it fills up quickly

    /* ═══ THE STACK — an array and one integer ═══════════════════════════ */

    static Integer[] Contents = new Integer[SIZE];
    static Integer   Top      = -1;     // -1 means empty

    /** Store Data. Return true if stored, false if the stack is full. */
    static Boolean Push(Integer Data) {
        // YOUR CODE HERE
        return null;
    }

    /** Return the top item, or -1 if the stack is empty. */
    static Integer Pop() {
        // YOUR CODE HERE
        return null;
    }

    /* ═══ THE QUEUE — an array and two integers ══════════════════════════ */

    static Integer[] Queue       = new Integer[SIZE];
    static Integer   HeadPointer = -1;
    static Integer   TailPointer = -1;

    /** Store Data at the tail. Return true if stored, false if full. */
    static Boolean Enqueue(Integer Data) {
        // YOUR CODE HERE
        return null;
    }

    /** Return the item at the head, or -1 if the queue is empty. */
    static Integer Dequeue() {
        // YOUR CODE HERE
        return null;
    }

    /* ═══ SECOND SCOOP (optional) — the walking queue ════════════════════
     *
     *  The linear queue above has the flaw from the lesson: enqueue and
     *  dequeue alternately and it walks up the array until it reports FULL
     *  while holding almost nothing.
     *
     *  Reclaim those slots with MOD. You will need to count the items,
     *  because once the array is a ring, Head == Tail no longer tells you
     *  whether it is full or empty.
     * ════════════════════════════════════════════════════════════════════ */

    static Integer[] CQueue = new Integer[SIZE];
    static Integer   CHead  = 0, CTail = -1, CCount = 0;

    static Boolean CircularEnqueue(Integer Data) { return null; }   // OPTIONAL
    static Integer CircularDequeue()             { return null; }   // OPTIONAL


    /* ────────────────────────────────────────────────────────────────────
     *  THE JUDGE — you do not need to read past here, but you may
     * ──────────────────────────────────────────────────────────────────── */

    static int passed = 0, total = 0;

    static void reset() {
        Contents = new Integer[SIZE]; Top = -1;
        Queue = new Integer[SIZE]; HeadPointer = -1; TailPointer = -1;
        CQueue = new Integer[SIZE]; CHead = 0; CTail = -1; CCount = 0;
    }

    static void check(String label, String problem) {
        total++;
        if (problem == null) { System.out.printf("  ✓  %-32s%n", label); passed++; }
        else                 { System.out.printf("  ✗  %-32s %s%n", label, problem); }
    }

    static String stackBasic() {
        reset();
        if (!Boolean.TRUE.equals(Push(10))) return "Push(10) on an empty stack should return true";
        Push(20); Push(30);
        if (Pop() != 30) return "Pop should return the LAST item pushed (30)";
        if (Pop() != 20) return "second Pop should return 20";
        if (Pop() != 10) return "third Pop should return 10";
        return null;
    }

    static String stackUnderflow() {
        reset();
        if (Pop() != -1) return "Pop on an empty stack must return -1";
        Push(5); Pop();
        if (Pop() != -1) return "Pop after emptying the stack must return -1 again";
        return null;
    }

    static String stackOverflow() {
        reset();
        for (int i = 0; i < SIZE; i++)
            if (!Boolean.TRUE.equals(Push(i)))
                return "Push returned false with room still left (i=" + i + ")";
        if (!Boolean.FALSE.equals(Push(999))) return "Push on a FULL stack must return false";
        if (Pop() != SIZE - 1) return "after a refused Push, the top must be unchanged";
        return null;
    }

    static String queueBasic() {
        reset();
        Enqueue(10); Enqueue(20); Enqueue(30);
        if (Dequeue() != 10) return "Dequeue should return the FIRST item enqueued (10)";
        if (Dequeue() != 20) return "second Dequeue should return 20";
        return null;
    }

    static String queueEmpty() {
        reset();
        if (Dequeue() != -1) return "Dequeue on an empty queue must return -1";
        Enqueue(7); Dequeue();
        if (Dequeue() != -1) return "Dequeue after emptying the queue must return -1 again";
        return null;
    }

    static String queueFull() {
        reset();
        for (int i = 0; i < SIZE; i++)
            if (!Boolean.TRUE.equals(Enqueue(i)))
                return "Enqueue returned false with room still left (i=" + i + ")";
        if (!Boolean.FALSE.equals(Enqueue(999))) return "Enqueue on a FULL queue must return false";
        return null;
    }

    static String fuzz() {
        reset();
        int[] refStack = new int[SIZE]; int refTop = -1;
        int[] refQueue = new int[10000]; int rqHead = 0, rqTail = -1;
        int refTail = -1;
        java.util.Random rng = new java.util.Random(20260807L);

        for (int n = 0; n < 10000; n++) {
            // A LINEAR queue whose tail has reached the end is full forever, so
            // every few hundred operations everything is emptied and the
            // reference cleared with it. Otherwise the queue would spend most
            // of this test permanently jammed and prove nothing.
            if (n % 250 == 0) {
                reset(); refTop = -1; rqHead = 0; rqTail = -1; refTail = -1;
            }
            int which = rng.nextInt(4);

            if (which == 0) {
                int v = 1 + rng.nextInt(499);
                boolean want = refTop < SIZE - 1;
                Boolean got = Push(v);
                if (want) refStack[++refTop] = v;
                if (!Boolean.valueOf(want).equals(got))
                    return "op " + n + ": Push returned " + got + ", expected " + want;

            } else if (which == 1) {
                int want = (refTop > -1) ? refStack[refTop--] : -1;
                Integer got = Pop();
                if (got == null || got != want)
                    return "op " + n + ": Pop returned " + got + ", expected " + want;

            } else if (which == 2) {
                int v = 1 + rng.nextInt(499);
                boolean want = refTail < SIZE - 1;      // decided BEFORE the call
                Boolean got = Enqueue(v);
                if (want) { refTail++; refQueue[++rqTail] = v; }
                if (!Boolean.valueOf(want).equals(got))
                    return "op " + n + ": Enqueue returned " + got + ", expected " + want;

            } else {
                int want = (rqHead <= rqTail) ? refQueue[rqHead++] : -1;
                Integer got = Dequeue();
                if (got == null || got != want)
                    return "op " + n + ": Dequeue returned " + got + ", expected " + want;
            }
        }
        return null;
    }

    static String safe(java.util.function.Supplier<String> t) {
        try { return t.get(); }
        catch (Exception e) { return "crashed: " + e.getClass().getSimpleName()
                                     + (e.getMessage() == null ? "" : ": " + e.getMessage()); }
    }

    public static void main(String[] args) {
        String line = "=".repeat(76);
        System.out.println("\n" + line);
        System.out.println("  A2a · STACK AND QUEUE — the judge");
        System.out.println(line);

        check("stack: push and pop in order", safe(ChallengeA02aStackQueue::stackBasic));
        check("stack: UNDERFLOW returns -1",  safe(ChallengeA02aStackQueue::stackUnderflow));
        check("stack: OVERFLOW returns false", safe(ChallengeA02aStackQueue::stackOverflow));
        check("queue: first in, first out",   safe(ChallengeA02aStackQueue::queueBasic));
        check("queue: EMPTY returns -1",      safe(ChallengeA02aStackQueue::queueEmpty));
        check("queue: FULL returns false",    safe(ChallengeA02aStackQueue::queueFull));
        check("10 000 random operations",     safe(ChallengeA02aStackQueue::fuzz));

        System.out.println("-".repeat(76));
        boolean scoop;
        try {
            reset();
            for (int i = 0; i < SIZE; i++) CircularEnqueue(i);
            for (int i = 0; i < SIZE; i++) CircularDequeue();
            for (int i = 0; i < SIZE; i++) CircularEnqueue(100 + i);
            Integer first = CircularDequeue();
            scoop = first != null && first == 100;
        } catch (Exception e) { scoop = false; }
        System.out.println("  " + (scoop ? "✓" : "·")
            + "  SECOND SCOOP — the circular queue reclaims its slots"
            + (scoop ? "" : "   (optional — not attempted, or the ring does not wrap)"));

        System.out.println(line);
        if (passed == total) {
            System.out.println("  " + passed + " / " + total
                + ".  Both structures survive everything thrown at them. 🍦");
            System.out.println("  The fuzz test alone ran ten thousand operations without a single");
            System.out.println("  disagreement. That is a stronger guarantee than any exam will ask for.");
        } else {
            System.out.println("  " + passed + " / " + total + ".");
            System.out.println("  Read the first failing row. Almost every failure in this challenge is");
            System.out.println("  one of two things: a missing FULL check, or a missing EMPTY check.");
            System.out.println("  The middle of the array is the easy part.");
        }
        System.out.println(line + "\n");
    }
}
