package com.selfdev.chemistkata;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


/**
 * Created by vovkv on 8/22/2019.
 */
public class MoleculeTest {

    @Test
    public void testBrancher() {
        Molecule m1 = new Molecule("methane");
        m1.closer();
        m1.unlock();

        Molecule m = new Molecule("methane").brancher(1).closer();
        assertEquals("CH4", m.getFormula());
        assertEquals(16, m.getMolecularWeight(), 0.1);

        m = new Molecule("octane").brancher(8).closer();
        assertEquals("C8H18", m.getFormula());
        assertEquals(114, m.getMolecularWeight(), 0.1);
    }

    @Test
    public void testBounder() {
        /*
            cyclohexane:
            CH2-CH2-CH2
             |       |
            CH2-CH2-CH2
           ---!!!Next step:!!!---
           Make it work completely based on test for this molecule
        */
        Molecule molecule = new Molecule("");
        molecule.brancher(6);
        molecule.bounder(new T(1, 1, 6, 1));
        molecule.getAtoms().forEach(atom -> System.out.println(atom));
    }

    @Test
    public void testMutate() {
        List<String> expected = Arrays.asList("Atom(O.1: C2,C5)", "Atom(C.2: C3,C3,O1,H)", "Atom(C.3: C2,C2,C4,H)", "Atom(C.4: C3,C5,C5,H)", "Atom(C.5: C4,C4,O1,H)");

        Molecule molecule = new Molecule("Furane: no additional hydrogens while closing the molecule after mutation");
        molecule.brancher(5).bounder(new T(5, 1, 1, 1), new T(5, 1, 4, 1), new T(2, 1, 3, 1)).mutate(new T(1, 1, "O")).closer();
        assertEquals("C4H4O", molecule.getFormula());
        assertEquals(68, molecule.getMolecularWeight(), 0.1);
        assertEquals("Checking bonds (for non-hydrogens)", expected, molecule.toList());
    }


    @Test
    public void testList() {
        Set<String> result = new TreeSet<>();
        Arrays.stream(AtomEnum.values()).forEach(atomEnum -> result.add(atomEnum.name()));
        System.out.println(result);
    }


    @Test
    public void test_CreateCarbohydratesAndBoundThemCorrectly_idTracking_rawFormula_molecularWeight() {
        String[] names = {"cyclohexane", "1,1-dimethyl-2-propylcyclohexane", "cubane - one branch", "cubane - two branches", "benzene - double bonds"};
        String[] forms = {"C6H12", "C11H22", "C8H8", "C8H8", "C6H6"};
        double[] mWeights = {84, 154, 104, 104, 78};
        int[][] branches = {{6}, {9, 1, 1}, {8}, {4, 4}, {2, 2, 2}};
        T[][] bounds = {{new T(1, 1, 6, 1)},
                {new T(4, 1, 9, 1), new T(5, 1, 1, 2), new T(5, 1, 1, 3)},
                {new T(3, 1, 6, 1), new T(2, 1, 7, 1), new T(1, 1, 8, 1), new T(4, 1, 1, 1), new T(5, 1, 8, 1)},
                {new T(1, 1, 4, 1), new T(1, 2, 4, 2), new T(1, 1, 1, 2), new T(2, 1, 2, 2), new T(3, 1, 3, 2), new T(4, 1, 4, 2)},
                {new T(1, 1, 2, 1), new T(1, 2, 2, 2), new T(1, 3, 2, 3), new T(2, 1, 1, 2), new T(2, 2, 1, 3), new T(2, 3, 1, 1)}};
        List<List<String>> expecteds = Arrays.asList(Arrays.asList("Atom(C.1: C2,C6,H,H)", "Atom(C.2: C1,C3,H,H)", "Atom(C.3: C2,C4,H,H)", "Atom(C.4: C3,C5,H,H)", "Atom(C.5: C4,C6,H,H)", "Atom(C.6: C1,C5,H,H)"),
                Arrays.asList("Atom(C.1: C2,H,H,H)", "Atom(C.2: C1,C3,H,H)", "Atom(C.3: C2,C4,H,H)", "Atom(C.4: C3,C5,C9,H)", "Atom(C.5: C4,C6,C10,C11)", "Atom(C.6: C5,C7,H,H)", "Atom(C.7: C6,C8,H,H)", "Atom(C.8: C7,C9,H,H)", "Atom(C.9: C4,C8,H,H)", "Atom(C.10: C5,H,H,H)", "Atom(C.11: C5,H,H,H)"),
                Arrays.asList("Atom(C.1: C2,C4,C8,H)", "Atom(C.2: C1,C3,C7,H)", "Atom(C.3: C2,C4,C6,H)", "Atom(C.4: C1,C3,C5,H)", "Atom(C.5: C4,C6,C8,H)", "Atom(C.6: C3,C5,C7,H)", "Atom(C.7: C2,C6,C8,H)", "Atom(C.8: C1,C5,C7,H)"),
                Arrays.asList("Atom(C.1: C2,C4,C5,H)", "Atom(C.2: C1,C3,C6,H)", "Atom(C.3: C2,C4,C7,H)", "Atom(C.4: C1,C3,C8,H)", "Atom(C.5: C1,C6,C8,H)", "Atom(C.6: C2,C5,C7,H)", "Atom(C.7: C3,C6,C8,H)", "Atom(C.8: C4,C5,C7,H)"),
                Arrays.asList("Atom(C.1: C2,C2,C6,H)", "Atom(C.2: C1,C1,C3,H)", "Atom(C.3: C2,C4,C4,H)", "Atom(C.4: C3,C3,C5,H)", "Atom(C.5: C4,C6,C6,H)", "Atom(C.6: C1,C5,C5,H)"));

        for (int i = 0; i < names.length; i++) {
            display(names[i]);
            Molecule m = new Molecule(names[i]).brancher(branches[i]).bounder(bounds[i]).closer();
            testThisMolecule(m, forms[i], mWeights[i], expecteds.get(i));
        }
    }


    @Test
    public void test_MutateAddAddChaining_ValenceNumbersConsistencies() {
        String[] names = {"Furane: no additional hydrogens while closing the molecule after mutation", "isopropylmagnesium bromide"};
        String[] forms = {"C4H4O", "C3H7BrMg"};
        double[] mWeights = {68, 147.3};
        int[][] branches = {{5}, {4, 1}};
        T[][] bounds = {{new T(5, 1, 1, 1), new T(5, 1, 4, 1), new T(2, 1, 3, 1)}, {new T(2, 1, 1, 2)}};
        T[][] adds = {{new T(1, 1, "O")}, {new T(3, 1, "Mg"), new T(4, 1, "Br")}};
        List<List<String>> expecteds = Arrays.asList(
                Arrays.asList("Atom(O.1: C2,C5)", "Atom(C.2: C3,C3,O1,H)", "Atom(C.3: C2,C2,C4,H)", "Atom(C.4: C3,C5,C5,H)", "Atom(C.5: C4,C4,O1,H)"),
                Arrays.asList("Atom(C.1: C2,H,H,H)", "Atom(C.2: C1,C5,Mg3,H)", "Atom(Mg.3: C2,Br4)", "Atom(Br.4: Mg3)", "Atom(C.5: C2,H,H,H)"));

        for (int i = 0; i < names.length; i++) {
            display(names[i]);
            Molecule m = new Molecule(names[i]).brancher(branches[i]).bounder(bounds[i]).mutate(adds[i]).closer();
            testThisMolecule(m, forms[i], mWeights[i], expecteds.get(i));
        }
    }

    @Test
    public void test_InvalidBond_invalidBasicBuilds() {

        String[] names = {"No self-bounding",
                "Should fail when exceeding the valence number adding new alkyls to the same atom",
                "Should fail when exceeding the valence number with multiple bonds"};
        int[][] branches = {{6}, {3, 1, 1, 1}, {4}};
        T[][] bounds = {{new T(1, 1, 1, 1)},
                {new T(2, 1, 1, 2), new T(2, 1, 1, 3), new T(2, 1, 1, 4)},
                {new T(2, 1, 3, 1), new T(2, 1, 3, 1), new T(2, 1, 3, 1)}};

        for (int i = 0; i < names.length; i++) {
            display(names[i]);

            boolean checkRaised = true;
            try {
                new Molecule(names[i]).brancher(branches[i]).bounder(bounds[i]).closer();
                checkRaised = false;
            } catch (InvalidBond iEB) {
            } catch (Exception e) {
                fail("Should throw an InvalidBond exception but was " + e);
            }
            assertTrue("Should have thrown an InvalidBond exception", checkRaised);
        }
    }

    @Test
    public void test_InvalidBond_InvalidMutationsOrAdditions_CheckMoleculeIntegrityAfterFailure() {

        String[] names = {"Should fail when mutating a carbon with three atoms already linked to an oxygen",
                "Should fail when mutating a carbon with two double bonds to nitrogen",
                "Should fail when adding a new hydrogen to a carbon with already 4 bonds",
                "Should fail when mutating an atom and then adding too much atoms on it"};
        int[][] branches = {{3, 1}, {3}, {3}, {3}};
        T[][] bounds = {{new T(2, 1, 1, 2)}, {new T(1, 1, 2, 1), new T(3, 1, 2, 1)}, {new T(1, 1, 2, 1), new T(3, 1, 2, 1)}, {new T(1, 1, 2, 1)}};
        String[] execs = {"mutate", "mutate", "add", "mutadd"};
        T[][] tups = {{new T(2, 1, "O")}, {new T(2, 1, "N")}, {new T(2, 1, "H")}, {new T(2, 1, "N"), new T(2, 1, "O")}};

        for (int i = 0; i < names.length; i++) {
            display(names[i]);

            Molecule m = new Molecule(names[i]).brancher(branches[i]);

            m.bounder(bounds[i]);
            if (execs[i].equals("mutadd"))
                m.mutate(tups[i][0]);

            List<String> integrity = extractNoneHToStr(m);

            boolean checkRaised = true;
            try {
                switch (execs[i]) {
                    case "mutate":
                        m.mutate(tups[i]).closer();
                        break;
                    case "add":
                        m.add(tups[i]).closer();
                        break;
                    case "mutadd":
                        m.add(tups[i][1]).closer();
                        break;
                    default:
                        throw new RuntimeException("Wrong configuration of the tests");
                }
                checkRaised = false;
            } catch (InvalidBond iEB) {
                assertEquals("The molecule shouldn't be modified when one of these errors occur", integrity, extractNoneHToStr(m));
            }
            assertTrue("Should have thrown an InvalidBond exception", checkRaised);
        }
    }


    private <U> void display(U msg) {
        System.out.println(msg);
    }

    private List<String> extractNoneHToStr(Molecule m) {
        return m.getAtoms().stream()
                .filter(at -> !at.element.equals("H"))
                .map(at -> at.toString())
                .collect(Collectors.toList());
    }

    private void testThisMolecule(Molecule m, String formula, double mm, List<String> strNoH) {
        assertEquals("Checking bonds (for non-hydrogens)", strNoH, extractNoneHToStr(m));
        assertEquals("Testing raw formula", formula, m.getFormula());
        assertEquals("Testing molecular weight", mm, m.getMolecularWeight(), 0.1);
    }


    @Test
    public void test_BranchesIntegrity_onlyAtomsFromTheBrancherMethod() {
        //important test for closer/unlock
        Molecule m = new Molecule("test_BranchesIntegrity_onlyAtomsFromTheBrancherMethod");
        m.brancher(1, 6, 4);
        m.addChaining(1, 1, "C", "Br");
        m.mutate(new T(1, 1, "H"));
        m.closer();
        m.unlock();
        System.out.println(m);
        m.bounder(new T(5, 1, 3, 2));
        System.out.println(m);
        m.closer();
        //System.out.println(m.toString());
        List<String> expected = Arrays.asList("Atom(C.1: C2,H,H,H)", "Atom(C.2: C1,C3,H,H)", "Atom(C.3: C2,C4,H,H)", "Atom(C.4: C3,C5,H,H)",
                "Atom(C.5: C4,C6,C9,H)", "Atom(C.6: C5,H,H,H)", "Atom(C.7: C8,H,H,H)", "Atom(C.8: C7,C9,H,H)",
                "Atom(C.9: C5,C8,C10,H)", "Atom(C.10: C9,H,H,H)", "Atom(C.11: Br12,H,H,H)", "Atom(Br.12: C11)");
        assertEquals("Checking bonds (for non-hydrogens)", expected, extractNoneHToStr(m));
        assertEquals(237.0, m.getMolecularWeight(), 0.1);
    }

    @Test
    public void test_Unlock_EmptyMolecule_exception() {
        Molecule m = new Molecule("test_Unlock_EmptyMolecule_exception");
        m.brancher(1);
        m.mutate(new T(1, 1, "H"));
        m.closer();
        m.unlock();
    }

    @Test
    public void test_AtomSpecs_toString_Part2() {
        Molecule m = new Molecule("test_AtomSpecs_toString_Part2");
        m.brancher(5);
        m.mutate(new T(2, 1, "N"));
        m.add(new T(3, 1, "Br"), new T(3, 1, "O"));
        m.addChaining(4, 1, "S", "C", "B");
        m.closer();
        System.out.println(m.toString());

    }

    @Test
    public void test_Unlock_removeEmptyBranchesAfterUnlocking() {
        Molecule m = new Molecule("test_Unlock_removeEmptyBranchesAfterUnlocking");
        m.brancher(1, 5);
        m.bounder(new T(2, 2, 5, 2), new T(4, 2, 1, 1));
        m.mutate(new T(1, 1, "H"));
        m.closer();
        System.out.println(m.toString());
        m.unlock();
        m.add(new T(2, 2, "P"));
        m.add(new T(2, 1, "P"));
        System.out.println(m.toString());
    }

    @Test
    public void randomTest() {
        Molecule m = new Molecule("randomTest");
        m.brancher(7, 9);
        try {
            m.mutate(new T(5, 1, "H"), new T(1, 2, "N"), new T(1, 1, "F"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        m.add(new T(1, 1, "H"), new T(3, 1, "B"), new T(2, 2, "N"));

        try {
            m.addChaining(1, 2, "F", "O", "H");
        } catch (Exception e) {
            e.printStackTrace();
        }
        m.add(new T(3, 1, "O"), new T(2, 1, "O"), new T(2, 2, "O"));
        System.out.println(m.toString());

    }


    static class A {
        public String a;
        public String b;
    }

    @Test
    public void test() {
        A a1 = new A();
        a1.a = "001";
        //a1.b = "002";
        List<A> list = Collections.singletonList(a1);
        Map<String, String> map = list.stream().collect(Collectors.toMap(a -> a.a, a -> a.b));
        System.out.println(map);
    }


    @Test
    public void randomTest2() {
        Molecule m = new Molecule("randomTest2");
        /**
         *
         * brancher: [5, 1]
         * brancher: [1]
         * brancher: [4, 3, 2]
         * brancher: [5, 4, 4]
         * add: [T(1, 9, Mg), T(3, 8, Br)]
         * mutate: [T(4, 9, O)]
         * add: [T(3, 5, Cl), T(1, 3, B)]
         *
         * mutate: [T(1, 3, B), T(2, 8, F)]
         * mutate: [T(1, 2, B), T(2, 8, C)]
         */
        m.brancher(5, 1);
        m.brancher(1);
        m.brancher(4, 3, 2);
        m.brancher(5, 4, 4);
        m.add(new T(1, 9, "Mg"), new T(3, 8, "Br"));
        m.mutate(new T(4, 9, "O"));
        m.add(new T(3, 5, "Cl"), new T(1, 3, "B"));

        try {
            m.mutate(new T(1, 3, "B"), new T(2, 8, "F"));
        } catch (Exception e) {

        }
        m.mutate(new T(1, 2, "B"), new T(2, 8, "C"));
    }

    @Test
    public void randomTest3() {
        Molecule m = new Molecule("randomTest2");
        m.brancher(6);
        System.out.println(m);
        try {
            m.addChaining(1, 1, "F", "F", "B");
            m.mutate(new T(3, 1, "F"), new T(2, 1, "F"));
        } catch (Exception e) {

        }
        m.brancher(4, 9, 2);
        System.out.println(m);
    }


    @Test
    public void randomTest4() {
        Molecule m = new Molecule("randomTest2");
        m.brancher(4);
        m.add(new T(3, 1, "Br"), new T(2, 1, "O"));
        m.brancher(5);
        //m.addChaining(3, 1, "N", "H");
        System.out.println(m);
        m.closer();
        m.unlock();
        System.out.println(m);
    }

    @Test
    public void randomTest5(){
        Molecule m = new Molecule("randomTest5");
        m.brancher(5, 8);
        m.addChaining(2, 1, "Mg", "N", "P");
        m.brancher(3);
        m.add(new T(4, 1, "Mg"), new T(1, 3, "Mg"));
        System.out.println(m);
        m.closer();
        m.unlock();
        System.out.println(m);
    }

    @Test
    public void test_Unlock_removeH_and_idNumbersUpdate(){
        Molecule m = new Molecule("test_Unlock_removeH_and_idNumbersUpdate");
        m.brancher(3);
        m.add(new T(2, 1, "H"));
        m.brancher(1);
        m.bounder(new T(2, 1, 1, 2));
        m.closer();
        m.unlock();
        System.out.println(m);
    }

    @Test
    public void randomTest6(){
        Molecule m = new Molecule("randomTest5");
        m.brancher(2, 1);
        m.add(new T(1, 1, "P"), new T(2, 1, "Mg"));
        m.add(new T(1, 2, "P"), new T(1, 1, "Mg"));
        m.add(new T(1, 1, "F"), new T(2, 1, "Mg"));
        try {
            m.mutate(new T(2, 1, "B"), new T(1, 2, "O"), new T(1, 1, "H"));
        } catch (Exception e) {

        }
        m.closer();
        System.out.println(m);
    }
    /**
     * brancher: [7, 8, 1]
     * add: [T(2, 2, O), T(8, 2, P)]
     * bounder: [T(1, 3, 6, 2)]
     * closer
     * addChaining: nc=1 nb=3 [P]
     * unlock
     * brancher: [1, 4]
     */

    @Test
    public void randomTest7(){
        Molecule m = new Molecule("randomTest5");
        m.brancher(7, 8, 1);
        m.add(new T(2, 2, "O"), new T(8, 2, "P"));
        m.bounder(new T(1, 3, 6, 2));
        m.closer();
        //m.addChaining(1,3, "P");
        m.unlock();
        m.brancher(1,4);
        System.out.println(m);
    }


    @Test
    public void randomTest8(){
        Molecule m = new Molecule("randomTest8");
        m.brancher(7, 2);
        m.mutate(new T(1, 2, "S"), new T(1, 2, "C"), new T(2, 2, "Mg"));
        m.brancher(1, 4);
        m.mutate(new T(2, 1, "P"), new T(1, 4, "H"));
        m.brancher(2);
        m.brancher(2, 5, 8);
        m.closer();
        m.unlock();
        m.addChaining(1, 5, "S", "P", "S");
        m.add(new T(1, 7, "B"));
        m.brancher(7, 8);
        m.add(new T(2, 7, "Br"), new T(1, 1, "Br"), new T(2, 6, "H"));
        System.out.println(m);
        m.closer();
        m.unlock();
        System.out.println(m);
    }
    /**
     * brancher: [7, 2]
     * mutate: [T(1, 2, S), T(1, 2, C), T(2, 2, Mg)]
     * brancher: [1, 4]
     * mutate: [T(2, 1, P), T(1, 4, H)]
     * brancher: [2]
     * brancher: [2, 5, 8]
     * closer
     * unlock
     * addChaining: nc=1 nb=5 [S, P, S]
     * add: [T(1, 7, B)]
     * brancher: [7, 8]
     * add: [T(2, 7, Br), T(1, 1, Br), T(2, 6, H)]
     * closer
     * unlock
     *
     * java.lang.AssertionError: expected:<
     *
     * [Atom(C.1: Br51,P2), Atom(P.2: C1,C3), Atom(C.3: C4,P2), Atom(C.4: C3,C5), Atom(C.5: C4,C6), Atom(C.6: C5,C7), Atom(C.7: C6), Atom(C.8: Mg9), Atom(Mg.9: C8), Atom(C.10), Atom(C.11: C12), Atom(C.12: C11,C13), Atom(C.13: C12), Atom(C.14: C15,S31), Atom(C.15: C14), Atom(C.16: C17), Atom(C.17: C16), Atom(C.18: C19,B34), Atom(C.19: C18,C20,Br50), Atom(C.20: C19,C21), Atom(C.21: C20,C22), Atom(C.22: C21), Atom(C.23: C24), Atom(C.24: C23,C25), Atom(C.25: C24,C26), Atom(C.26: C25,C27), Atom(C.27: C26,C28), Atom(C.28: C27,C29), Atom(C.29: C28,C30), Atom(C.30: C29), Atom(S.31: C14,P32), Atom(P.32: S31,S33), Atom(S.33: P32), Atom(B.34: C18), Atom(C.35: C36), Atom(C.36: C35,C37), Atom(C.37: C36,C38), Atom(C.38: C37,C39), Atom(C.39: C38,C40), Atom(C.40: C39,C41), Atom(C.41: C40), Atom(C.42: C43), Atom(C.43: C42,C44), Atom(C.44: C43,C45), Atom(C.45: C44,C46), Atom(C.46: C45,C47), Atom(C.47: C46,C48), Atom(C.48: C47,C49), Atom(C.49: C48), Atom(Br.50: C19), Atom(Br.51: C1)]
     *
     * > but was:<
     *
     * [Atom(C.1: Br46,P2), Atom(P.2: C1,C3), Atom(C.3: C4,P2), Atom(C.4: C3,C5), Atom(C.5: C4,C6), Atom(C.6: C5,C7), Atom(C.7: C6), Atom(C.8: Mg9), Atom(Mg.9: C8), Atom(C.10), Atom(C.11: C12), Atom(C.12: C11,C13), Atom(C.13: C12), Atom(C.14: C15,S47), Atom(C.15: C14), Atom(C.16: C17), Atom(C.17: C16), Atom(C.18: C19,B50), Atom(C.19: C18,C20,Br51), Atom(C.20: C19,C21), Atom(C.21: C20,C22), Atom(C.22: C21), Atom(C.23: C24), Atom(C.24: C23,C25), Atom(C.25: C24,C26), Atom(C.26: C25,C27), Atom(C.27: C26,C28), Atom(C.28: C27,C29), Atom(C.29: C28,C30), Atom(C.30: C29), Atom(C.31: C32), Atom(C.32: C31,C33), Atom(C.33: C32,C34), Atom(C.34: C33,C35), Atom(C.35: C34,C36), Atom(C.36: C35,C37), Atom(C.37: C36), Atom(C.38: C39), Atom(C.39: C38,C40), Atom(C.40: C39,C41), Atom(C.41: C40,C42), Atom(C.42: C41,C43), Atom(C.43: C42,C44), Atom(C.44: C43,C45), Atom(C.45: C44), Atom(Br.46: C1), Atom(S.47: C14,P48), Atom(P.48: S47,S49), Atom(S.49: P48), Atom(B.50: C18), Atom(Br.51: C19)]>
     */

}
