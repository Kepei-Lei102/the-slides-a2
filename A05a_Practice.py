"""A05a — A Record That Owns Its Verbs, questions 7–10 (13 marks).

Q1–6 are on the printed sheet. Copy before editing; submit as A05a_<YourName>.py.
Run: python3 A05a_Practice.py (or your renamed copy). No extra packages needed.
First draw the class table and trace two objects with different names/colours
on paper. The diagram is self/teacher checked, outside the code total.

Drone class table — five instance attributes, all STRING:
    DroneName, Colour, CanHover, HasCamera, Waterproof
    The last three store "Yes" or "No". Prefer private attributes.
Q7 [4]: define the five attributes and a constructor with five parameters in
that order; store each parameter. Annotate the attributes as str (or use
comments naming each attribute and its STRING type).
Q8 [3]: GetDroneName(), GetColour(), GetCanHover(), GetHasCamera(),
GetWaterproof(): each takes only self and returns its own attribute.
Q9 [4]: SetColour(newColour): accept exactly a colour in LEGAL_COLOURS,
store it and return True. Otherwise change nothing and return False.
Q10 [2]: DronesMade(): callable on the CLASS before any object exists;
return the number of Drone objects ever constructed. Each new object adds
one; names and other instance attributes remain separate for each object.
Implement the class-level method using @staticmethod or @classmethod.

The checks below contain examples and expected results, not a model class.
They report provisional whole-question feedback; the teacher checks method,
attribute declarations and partial credit. Written 23 + code 13 = 36 marks.
"""

LEGAL_COLOURS = ("Green", "Blue", "Red", "Yellow", "Black")


class Drone:
    # Q7 — add the five attributes and shared counter as needed.
    def __init__(self, DroneName, Colour, CanHover, HasCamera, Waterproof):
        # YOUR CODE HERE
        pass

    # Q8 — write the five getters here.

    # Q9 — write SetColour here.

    # Q10 — write DronesMade here.


# SELF-CHECK — keep this section unchanged.
_FIELDS = ("DroneName", "Colour", "CanHover", "HasCamera", "Waterproof")
_SAMPLES = (("Zip", "Green", "Yes", "No", "Yes"),
            ("Survey", "Blue", "No", "Yes", "No"))


def _stored(obj, name):
    # Accept private, conventional protected or public Python attributes.
    for key in (f"_Drone__{name}", f"_{name}", name):
        if key in vars(obj):
            return vars(obj)[key]
    raise AssertionError(f"missing instance attribute {name}")


def _class_check():
    import inspect
    params = list(inspect.signature(Drone.__init__).parameters.values())
    assert len(params) == 6, "constructor needs self and five parameters"
    for row in _SAMPLES:
        obj = Drone(*row)
        assert tuple(_stored(obj, name) for name in _FIELDS) == row, "store all five inputs"


def _getter_check():
    for row in _SAMPLES:
        obj = Drone(*row)
        for name, expected in zip(_FIELDS, row):
            assert getattr(obj, "Get" + name)() == expected, f"Get{name} returns wrong value"


def _setter_check():
    obj = Drone(*_SAMPLES[0])
    for colour in LEGAL_COLOURS:
        assert obj.SetColour(colour) is True, f"accept {colour}"
        assert obj.GetColour() == colour, "update the colour"
    for colour in ("Purple", "green", "", " Green"):
        before = vars(obj).copy()
        assert obj.SetColour(colour) is False, f"refuse {colour!r}"
        assert vars(obj) == before, "a refusal must change nothing"
        assert obj.GetColour() == "Black", "keep the previous legal colour"


def _counter_check():
    before = Drone.DronesMade()
    assert type(before) is int, "return an integer count"
    rows = (("North", "Green", "Yes", "No", "Yes"),
            ("East", "Blue", "No", "Yes", "No"),
            ("West", "Red", "Yes", "Yes", "No"))
    objects = [Drone(*row) for row in rows]
    assert Drone.DronesMade() == before + 3, "count every newly created object once"
    assert [o.GetDroneName() for o in objects] == [r[0] for r in rows], "names belong to objects"
    objects[0].SetColour("Yellow")
    assert [o.GetColour() for o in objects] == ["Yellow", "Blue", "Red"], "colours belong to objects"
    assert Drone.DronesMade() == before + 3, "a setter does not construct a drone"


def judge():
    score = 0
    # Check the initial count before any test has constructed an object.
    try:
        initial_zero = type(Drone.DronesMade()) is int and Drone.DronesMade() == 0
    except Exception:
        initial_zero = False
    for question, worth, check in ((7, 4, _class_check), (8, 3, _getter_check),
                                    (9, 4, _setter_check), (10, 2, _counter_check)):
        try:
            if question == 10:
                assert initial_zero, "the class count starts at zero before the first object"
            check()
        except Exception as exc:
            print(f"Q{question}: not complete — {type(exc).__name__}: {exc}")
        else:
            score += worth
            print(f"Q{question}: PASS ({worth} marks)")
    print(f"Self-check: {score}/13; declarations, method and partial credit are teacher checked.")
    return score


if __name__ == "__main__":
    judge()
