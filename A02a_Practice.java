/* ═══════════════════════════════════════════════════════════════════════════
 *  A2a · STACKS AND QUEUES — programming homework
 *  9618 §19.1   ·   this is the Paper 4 half of the lesson
 * ═══════════════════════════════════════════════════════════════════════════
 *
 *  HOW TO SUBMIT
 *      Rename this FILE to           A02a_YourName.java
 *      Change nothing inside it.     (The class is not public, so Java does
 *                                     not care what the file is called.)
 *      Submit that one file.
 *
 *  WHICH QUESTIONS ARE IN HERE
 *      The printed sheet is questions 1 to 6. This file is questions 7 to 11.
 *      Together they are one piece of homework with one set of numbers, so
 *      "question 9" means the same thing whichever half you are holding.
 *
 *  HOW TO WORK
 *      Fill in each question where it says YOUR CODE HERE, then run the file.
 *      The tests at the bottom run themselves and tell you, per task, what
 *      passed and what did not. Keep going until the warm-up and questions 7-10
 *      are all green. Question 11 is optional.
 *
 *      You are expected to run this many times. That is the point of having
 *      the tests in the file rather than in my head.
 *
 *  WHAT IS MARKED
 *      Questions 7-10 (19 marks), against the published Cambridge mark points. Marks are for
 *      the METHOD being visibly right, so:
 *
 *        * code that does not compile can still earn marks — submit it anyway;
 *        * code that passes every test can still lose marks if it passes for
 *          the wrong reason.
 *
 *      The warm-up is not marked. Question 11 is not marked. Do them anyway.
 *
 *  THE RULES
 *      A fixed-size array and integer indices. Nothing else.
 *      BANNED:  java.util.Stack   java.util.Queue   ArrayDeque
 *               java.util.LinkedList   ArrayList   any List at all
 *      Importing a Stack to implement a Stack answers a different question.
 * ═══════════════════════════════════════════════════════════════════════════ */

class A02a {

    static final int SIZE = 8;          // small on purpose: it fills up fast

    // ── the stack: an array and ONE integer ───────────────────────────────
    static int[] Contents = new int[SIZE];
    static int   Top      = -1;         // -1 means empty

    // ── the queue: an array and TWO integers ──────────────────────────────
    static int[] Queue       = new int[SIZE];
    static int   HeadPointer = -1;
    static int   TailPointer = -1;


    /* ═══════════════════════════════════════════════════════════════════
     *  WARM-UP  (not marked — two minutes, and it makes the rest easier)
     * ═══════════════════════════════════════════════════════════════════
     *
     *  Two one-line methods. Neither of them changes anything; they only
     *  report. Write them and you will have said out loud, in code, what
     *  "empty" and "the top" actually mean — which is most of Task 1 and 2.
     */

    /** true when the stack holds nothing at all. */
    static boolean IsEmpty() {
        // YOUR CODE HERE
        return false;
    }

    /** the item on top WITHOUT removing it, or -1 if the stack is empty. */
    static int Peek() {
        // YOUR CODE HERE  (replace this placeholder -- -999 is not a real answer)
        return -999;
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 7  ·  Push          [4 marks]
     * ═══════════════════════════════════════════════════════════════════
     *  Store Data on the stack.
     *      return true   if it was stored
     *      return false  if the stack is already full
     */
    static boolean Push(int Data) {
        // YOUR CODE HERE
        return false;
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 8  ·  Pop           [4 marks]
     * ═══════════════════════════════════════════════════════════════════
     *  Remove and return the item on top of the stack.
     *      return the item
     *      return -1     if the stack is empty
     */
    static int Pop() {
        // YOUR CODE HERE  (replace this placeholder)
        return -999;
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 9  ·  Enqueue       [6 marks]   ← the published exam question
     * ═══════════════════════════════════════════════════════════════════
     *  This is June 2025 Paper 43 Q1(b), with a smaller array.
     *
     *  Store Data at the tail of the LINEAR queue.
     *      return true   if it was stored
     *      return false  if the queue is full
     *
     *  Remember: the FIRST item stored must also set HeadPointer.
     *  Forget that and the bug appears in Dequeue, not here — which is
     *  exactly why it is worth a mark of its own.
     */
    static boolean Enqueue(int Data) {
        // YOUR CODE HERE
        return false;
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 10 ·  Dequeue       [5 marks]   ← published, Q1(c)
     * ═══════════════════════════════════════════════════════════════════
     *  Remove and return the item at the head of the queue.
     *      return the item
     *      return -1     if the queue is empty
     */
    static int Dequeue() {
        // YOUR CODE HERE  (replace this placeholder)
        return -999;
    }


    /* ═══════════════════════════════════════════════════════════════════
     *  QUESTION 11 ·  🍦 the circular queue   (optional, not marked)
     * ═══════════════════════════════════════════════════════════════════
     *  Your linear queue above has the flaw from the lesson: enqueue and
     *  dequeue alternately and it reports FULL while holding almost nothing.
     *
     *  Fix it with MOD. You will need CCount, because once the array is a
     *  ring, CHead == CTail no longer tells you full from empty.
     */
    static int[] CQueue = new int[SIZE];
    static int   CHead  = 0, CTail = -1, CCount = 0;

    static boolean CircularEnqueue(int Data) {
        // OPTIONAL
        return false;
    }

    static int CircularDequeue() {
        // OPTIONAL
        return -1;
    }


    /* ────────────────────────────────────────────────────────────────────
     *  THE TESTS
     *
     *  You may read these. You may not change them — the same tests run
     *  when this is marked, so editing them only hides your own bugs.
     * ──────────────────────────────────────────────────────────────────── */

    static int checks = 0, good = 0;
    static String currentTask = "";
    static java.util.LinkedHashMap<String, int[]> tally = new java.util.LinkedHashMap<>();

    static void task(String name) {
        currentTask = name;
        tally.putIfAbsent(name, new int[2]);
    }

    static boolean expect(String what, boolean condition) {
        checks++;
        int[] t = tally.get(currentTask);
        t[1]++;
        if (condition) { good++; t[0]++; }
        else System.out.printf("      x  %s%n", what);
        return condition;
    }

    /** A check that only makes sense if an earlier one passed. Counted, but
     *  reported as "not reached" so the output points at the first real fault
     *  instead of burying it under consequences. */
    static void expectIf(boolean earlierPassed, String what, boolean condition) {
        if (!earlierPassed) {
            checks++; tally.get(currentTask)[1]++;
            System.out.printf("      -  %s   (not reached — fix the line above first)%n", what);
            return;
        }
        expect(what, condition);
    }

    static void reset() {
        Contents = new int[SIZE]; Top = -1;
        Queue = new int[SIZE];    HeadPointer = -1; TailPointer = -1;
        CQueue = new int[SIZE];   CHead = 0; CTail = -1; CCount = 0;
    }

    // ── warm-up ───────────────────────────────────────────────────────────
    static void testWarmUp() {
        task("warm-up  IsEmpty / Peek");
        reset();
        expect("IsEmpty() is true on a brand-new stack", IsEmpty());
        expect("Peek() is -1 when the stack is empty", Peek() == -1);
        Contents[0] = 42; Top = 0;                 // set up by hand, not via Push
        expect("IsEmpty() is false once something is on the stack", !IsEmpty());
        expect("Peek() returns the top item (42)", Peek() == 42);
        expect("Peek() does NOT remove it — Top is still 0", Top == 0);
    }

    // ── task 1 ────────────────────────────────────────────────────────────
    static void testPush() {
        task("Q7   Push");
        reset();
        expect("Push on an empty stack returns true", Push(10));
        expect("... and Top has moved to 0", Top == 0);
        expect("... and the item is at Contents[0]", Contents[0] == 10);
        Push(20); Push(30);
        expect("three pushes leave Top at 2", Top == 2);
        reset();
        boolean allStored = true;
        for (int i = 0; i < SIZE; i++) if (!Push(i)) allStored = false;
        expect("all " + SIZE + " items fit", allStored);
        expectIf(allStored, "the next Push is refused — returns false", !Push(999));
        expectIf(allStored, "a refused Push does not move Top", Top == SIZE - 1);
    }

    // ── task 2 ────────────────────────────────────────────────────────────
    static void testPop() {
        task("Q8   Pop");
        reset();
        expect("Pop on an empty stack returns -1", Pop() == -1);
        boolean pushed = Push(10) && Push(20) && Push(30);
        expectIf(pushed, "Pop returns the LAST item pushed (30)", Pop() == 30);
        expectIf(pushed, "... and Top has come back down to 1", Top == 1);
        expectIf(pushed, "the next Pop returns 20", Pop() == 20);
        expectIf(pushed, "the next Pop returns 10", Pop() == 10);
        expectIf(pushed, "the stack is empty again — Top is -1", Top == -1);
        expect("one Pop too many returns -1", Pop() == -1);
    }

    // ── task 3 ────────────────────────────────────────────────────────────
    static void testEnqueue() {
        task("Q9   Enqueue");
        reset();
        expect("Enqueue on an empty queue returns true", Enqueue(10));
        expect("... and the item is at Queue[0]", Queue[0] == 10);
        expect("... and TailPointer has moved to 0", TailPointer == 0);
        expect("... and HeadPointer was set to 0 — THE MARK PEOPLE DROP",
               HeadPointer == 0);
        reset();
        boolean allStored = true;
        for (int i = 0; i < SIZE; i++) if (!Enqueue(i)) allStored = false;
        expect("all " + SIZE + " items fit", allStored);
        expectIf(allStored, "the next Enqueue is refused — returns false", !Enqueue(999));
        expectIf(allStored, "TailPointer stops at " + (SIZE - 1), TailPointer == SIZE - 1);
    }

    // ── task 4 ────────────────────────────────────────────────────────────
    static void testDequeue() {
        task("Q10  Dequeue");
        reset();
        expect("Dequeue on an empty queue returns -1", Dequeue() == -1);
        boolean filled = Enqueue(10) && Enqueue(20) && Enqueue(30);
        expectIf(filled, "Dequeue returns the FIRST item enqueued (10)", Dequeue() == 10);
        expectIf(filled, "... then 20", Dequeue() == 20);
        expectIf(filled, "... then 30", Dequeue() == 30);
        expectIf(filled, "the queue is empty again — Dequeue returns -1", Dequeue() == -1);

        // the lesson's own demonstration, as a test
        reset();
        Enqueue(1); Enqueue(2); Enqueue(3);
        Dequeue();  Dequeue();  Dequeue();
        for (int i = 0; i < SIZE - 3; i++) Enqueue(100 + i);
        expect("a LINEAR queue reports full once the tail reaches the end, "
               + "even with room at the front (this is correct!)", !Enqueue(7));
    }

    // ── task 5 ────────────────────────────────────────────────────────────
    static boolean testCircular() {
        reset();
        try {
            for (int i = 0; i < SIZE; i++) if (!CircularEnqueue(i)) return false;
            if (CircularEnqueue(99)) return false;              // must refuse when full
            for (int i = 0; i < SIZE; i++) if (CircularDequeue() != i) return false;
            if (CircularDequeue() != -1) return false;          // must report empty
            for (int i = 0; i < SIZE; i++) if (!CircularEnqueue(200 + i)) return false;
            return CircularDequeue() == 200;                    // wrapped and reusable
        } catch (Exception e) { return false; }
    }

    public static void main(String[] args) {
        String line = "=".repeat(72);
        System.out.println("\n" + line);
        System.out.println("  A2a · STACKS AND QUEUES — your own tests");
        System.out.println(line);

        Runnable[] suites = { A02a::testWarmUp, A02a::testPush,
                              A02a::testPop, A02a::testEnqueue, A02a::testDequeue };
        for (Runnable r : suites) {
            try { r.run(); }
            catch (Exception e) {
                System.out.printf("      x  crashed: %s%n", e);
            }
        }

        System.out.println();
        for (java.util.Map.Entry<String, int[]> e : tally.entrySet()) {
            int[] t = e.getValue();
            System.out.printf("  %-3s %-26s %d / %d%n",
                t[0] == t[1] ? "OK" : "->", e.getKey(), t[0], t[1]);
        }

        System.out.println("-".repeat(72));
        boolean scoop = testCircular();
        System.out.println("  " + (scoop ? "OK " : " . ")
            + "Q11  circular queue (optional) " + (scoop ? "working" : "optional / not yet"));

        System.out.println(line);
        if (good == checks) {
            System.out.println("  " + good + " / " + checks + " checks. Everything passes.");
            System.out.println("  Now read your Enqueue once more and ask whether it would still");
            System.out.println("  be right if the array held temperatures instead of positive");
            System.out.println("  integers. Passing the tests and being correct are not the same");
            System.out.println("  thing, and the marking looks at both.");
        } else {
            System.out.println("  " + good + " / " + checks
                + " checks. The x lines above say exactly what went wrong.");
            System.out.println("  Almost every failure here is a missing FULL check or a missing");
            System.out.println("  EMPTY check. The middle of the array is the easy part.");
        }
        System.out.println(line + "\n");
    }
}
