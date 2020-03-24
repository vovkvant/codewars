package com.selfdev.chemistkata;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by vovkv on 8/13/2019.
 */
public class Molecule {
    private String name;
    private int atomCounter = 1;
    private int lastBranchId = 0;
    private boolean locked = false;

    Map<Integer, LinkedList<Atom>> branchAtomsMap = new HashMap<>();
    Map<Integer, LinkedList<Atom>> chains = new HashMap<>();

    public Molecule() {
        this.name = "";
    }

    public Molecule(String name) {
        this.name = name;
    }

    //Gives the raw formula of the final molecule as a string
    String getFormula() {
        if (!locked) {
            throw new UnlockedMolecule();
        }
        //C, H, O, other elements in alphabetic order
        List<Atom> atoms = getAtoms();
        Collections.sort(atoms);
        Map<String, AtomicInteger> countAtoms = new LinkedHashMap<>();
        atoms.forEach(atom -> {
            if (countAtoms.get(atom.getElement()) != null) {
                countAtoms.get(atom.getElement()).incrementAndGet();
            } else {
                countAtoms.put(atom.getElement(), new AtomicInteger(1));
            }
        });
        String result = countAtoms.entrySet().stream()
                .filter(entry -> entry.getValue().get() != 0)
                .map(entry -> entry.getKey() + (entry.getValue().get() != 1 ? entry.getValue().toString() : ""))
                .reduce("", (s1, s2) -> s1 + s2);

        return result;
    }


    //The value of the molecular weight of the final molecule in g/mol, as a double value
    double getMolecularWeight() {
        if (!locked) {
            throw new UnlockedMolecule();
        }

        double chainsWeight = chains.values().stream().flatMap(List::stream).map(atom -> atom.getAtomicWeight()).reduce(0.0, (a1, a2) -> a1 + a2);
        return chainsWeight + branchAtomsMap.values().stream().flatMap(List::stream).map(atom -> atom.getAtomicWeight()).reduce(0.0, (a1, a2) -> a1 + a2);
    }


    //A list of Atom objects. Atoms are appended to the list in the order of their creation.
    List<Atom> getAtoms() {
        List<Atom> finalBranchList = branchAtomsMap.values().stream().flatMap(List::stream).collect(Collectors.toList());
        List<Atom> finalChainsList = chains.values().stream().flatMap(List::stream).collect(Collectors.toList());
        finalBranchList.addAll(finalChainsList);
        Collections.sort(finalBranchList, new Comparator<Atom>() {
            @Override
            public int compare(Atom o1, Atom o2) {
                if (o1.id > o2.id) {
                    return 1;
                }
                if (o1.id < o2.id) {
                    return -1;
                }
                return 0;
            }
        });
        return finalBranchList;
    }


    //The name of the molecule, as a string of course (provided or not in the constructor. Default will be the empty string)
    String getName() {
        return name;
    }

    /*
    Add new "branches" of carbons, to the current molecule. All carbons of one branch are bonded together (chained).
    Each argument gives the number of carbons of one branch.
    There can be any number of arguments.
    All branches have to be created in the provided order.
     */
    public Molecule brancher(int... branches) {
        if (locked) {
            throw new LockedMolecule();
        }
        System.out.println("brancher: " + Arrays.toString(branches));
        Arrays.stream(branches).forEach(quantity -> {
            lastBranchId++;
            for (int i = 0; i < quantity; i++) {
                Atom carbonAtom = new Atom("C", atomCounter++);
                if (branchAtomsMap.get(lastBranchId) == null) {
                    branchAtomsMap.put(lastBranchId, new LinkedList<>());
                }
                if (branchAtomsMap.get(lastBranchId).size() != 0) {
                    Atom prevAtom = branchAtomsMap.get(lastBranchId).getLast();
                    prevAtom.addRelatedAtom(carbonAtom);
                    carbonAtom.addRelatedAtom(prevAtom);
                }
                branchAtomsMap.get(lastBranchId).add(carbonAtom);
            }

        });

        return this;
    }

    /*
    Create new bonds between two atoms of already existing branches.
    Each argument is a tuple (python) / T object (java) of four integers giving:
    c1 & b1: carbon and branch number of the first atom
    c2 & b2: carbon and branch number of the second atom
    All numbers are 1-indexed, meaning (1,1,5,3) will bond the first carbon of the first branch with the fifth of the third branch.
    Only positive numbers will be used.
     */
    public Molecule bounder(T... bonds) {
        if (locked) {
            throw new LockedMolecule();
        }
        System.out.println("bounder: " + Arrays.toString(bonds));
        Arrays.stream(bonds).forEach(bond -> {
            if (branchAtomsMap.get(bond.b1) == null || branchAtomsMap.get(bond.b2) == null) {
                throw new InvalidBond();
            }

            if (branchAtomsMap.get(bond.b1).get(bond.c1 - 1) == null || branchAtomsMap.get(bond.b2).get(bond.c2 - 1) == null) {
                throw new InvalidBond();
            }

            Atom atom_1 = branchAtomsMap.get(bond.b1).get(bond.c1 - 1);
            Atom atom_2 = branchAtomsMap.get(bond.b2).get(bond.c2 - 1);
            if (atom_1.isPossibleToAdd(atom_2) && atom_2.isPossibleToAdd(atom_1)) {
                atom_1.addRelatedAtom(atom_2);
                atom_2.addRelatedAtom(atom_1);
            } else {
                throw new InvalidBond();
            }
        });

        return this;
    }


    /*
    Mutate the carbon number nc in the branch nb (reminder: both 1-indexed) to the chemical element elt,
    as a string (this is mutation, the id number of the instance stays the same. See the Atom class specs about that).
     */
    public Molecule mutate(T... mutations) {
        if (locked) {
            throw new LockedMolecule();
        }
        System.out.println("mutate: " + Arrays.toString(mutations));
        Arrays.stream(mutations).forEach(mutation -> {
            if (branchAtomsMap.get(mutation.nb) == null) {
                throw new InvalidBond();
            }

            if (branchAtomsMap.get(mutation.nb).get(mutation.nc - 1) == null) {
                throw new InvalidBond();
            }
            Atom atom = branchAtomsMap.get(mutation.nb).get(mutation.nc - 1);
            atom.mutate(mutation.elt);
        });
        return this;
    }

    /*
    Add a new Atom of kind elt (string) on the carbon number nc in the branch nb. Atoms added this way are
    not considered as being part of the branch they are bonded to.
     */
    public Molecule add(T... atoms) {
        if (locked) {
            throw new LockedMolecule();
        }
        System.out.println("add: " + Arrays.toString(atoms));

        Arrays.stream(atoms).forEach(atom -> {
            Atom newAtom = new Atom(atom.elt, atomCounter++);

            if (branchAtomsMap.get(atom.nb) == null) {
                atomCounter--;
                throw new InvalidBond();
            }

            if (branchAtomsMap.get(atom.nb).get(atom.nc - 1) == null) {
                atomCounter--;
                throw new InvalidBond();
            }

            Atom a = branchAtomsMap.get(atom.nb).get(atom.nc - 1);

            try {
                newAtom.addRelatedAtom(a);
                a.addRelatedAtom(newAtom);
            } catch (InvalidBond e) {
                atomCounter--;
                throw new InvalidBond();
            }

            if (chains.get(atom.nb) == null) {
                LinkedList<Atom> chain = new LinkedList();
                chain.add(newAtom);
                chains.put(atom.nb, chain);
            } else {
                chains.get(atom.nb).add(newAtom);
            }
        });

        return this;
    }

    /*
    Add a new Atom of kind elt (string) on the carbon number nc in the branch nb. Atoms added this way are not considered as
    being part of the branch they are bonded to.
    Special case with add_chaining: if an error occurs at any point during the building of the chain, all its atoms
    have to be removed from the molecule (even the valid ones).
     */
    //!!!!!!!!!!!!!!!this method doesn't work properly
    public Molecule addChaining(int nc, int nb, String... elts) {
        System.out.println("addChaining: nc=" + nc + " nb=" + nb + " " + Arrays.toString(elts));
        if (locked) {
            throw new LockedMolecule();
        }
        LinkedList<Atom> newAtomsList = new LinkedList<>();

        int iterationCounter = 0;
        for (String elt : elts) {
            Atom newAtom = new Atom(elt, atomCounter++);
            iterationCounter++;
            if (newAtomsList.size() != 0) {
                Atom prevAtom = newAtomsList.getLast();
                try {
                    prevAtom.addRelatedAtom(newAtom);
                    newAtom.addRelatedAtom(prevAtom);
                } catch (InvalidBond e) {
                    atomCounter = atomCounter - iterationCounter;
                    throw new InvalidBond();
                }
            }
            newAtomsList.add(newAtom);
        }

        if (branchAtomsMap.get(nb) == null) {
            atomCounter -= newAtomsList.size();
            throw new InvalidBond();
        }

        if (branchAtomsMap.get(nb).get(nc - 1) == null) {
            atomCounter -= newAtomsList.size();
            throw new InvalidBond();
        }

        Atom atom = branchAtomsMap.get(nb).get(nc - 1);
        try {
            newAtomsList.getFirst().addRelatedAtom(atom);
            atom.addRelatedAtom(newAtomsList.getFirst());
        } catch (InvalidBond e) {
            atomCounter -= newAtomsList.size();
            throw new InvalidBond();
        }


        if (chains.get(nb) == null) {
            chains.put(nb, newAtomsList);
        } else {
            chains.get(nb).addAll(newAtomsList);
        }

        return this;
    }


    /*
    Finalize the molecule instance, adding missing hydrogens everywhere and locking the object (see "behaviors" part below).
     */
    public Molecule closer() {
        System.out.println("closer");
        if (locked) {
            throw new LockedMolecule();
        }

        locked = true;
        subClose(branchAtomsMap);
        subClose(chains);

        return this;
    }

    private void subClose(Map<Integer, LinkedList<Atom>> map) {
        map.values().forEach(branch -> {
            List<Atom> addedAtoms = new ArrayList<>();
            branch.forEach(atom -> {
                while (atom.getAvailableValency() != 0) {
                    Atom hAtom = new Atom("H", atomCounter++);
                    atom.addRelatedAtom(hAtom);
                    hAtom.addRelatedAtom(atom);
                    addedAtoms.add(hAtom);
                }
            });
            branch.addAll(addedAtoms);
        });
    }


    /*
    Make the molecule modifiable again. Hydrogens should be removed (id numbers of the remaining atoms should be
    continuous, beginning at 1), as well as any empty branch you might encounter during the operation
    (see the related behaviors below for additional information).
     */
    //!!!!!!!!!!!!!!!this method doesn't work properly
    public Molecule unlock() {
        System.out.println("unlock");
        Map<Integer, LinkedList<Atom>> newBranchAtomsMap = new HashMap<>();
        Map<Integer, LinkedList<Atom>> newChains = new HashMap<>();

        int newBranchCounter = 0;
        for (int i = 1; i <= lastBranchId; i++) {
            LinkedList<Atom> branch = branchAtomsMap.get(i);
            LinkedList<Atom> newBranch = createNewBranch(branch);
            if (newBranch.size() != 0) {
                newBranchCounter++;
                newBranchAtomsMap.put(newBranchCounter, newBranch);
            }
        }

        //chains
        for (int i = 1; i <= lastBranchId; i++) {
            //re-calculate chains
            LinkedList<Atom> chain = chains.get(i);
            if (chain != null) {
                LinkedList<Atom> newChain = createNewBranch(chain);
                if (newChain.size() != 0) {
                    newChains.put(i, newChain);
                }
            }
        }

        //re-calculate ids
        atomCounter = 1;
        int chainCursor = 0;
        for (int i = 1; i <= newBranchCounter; i++) {
            LinkedList<Atom> branch = newBranchAtomsMap.get(i);
            for (int j = 0; j < branch.size(); j++) {
                Atom branchAtom = branch.get(j);
                Atom chainAtom = getByCursor(newChains, chainCursor);
                if (chainAtom != null) {
                    if (chainAtom.getId() - branchAtom.getId() > 1) {
                        branchAtom.setId(atomCounter++);
                    } else if(chainAtom.getId() - branchAtom.getId() < 1) {
                        chainAtom.setId(atomCounter++);
                        j--;
                        chainCursor++;
                    } else
                    if(chainAtom.getId() - branchAtom.getId() == 1) {
                        branchAtom.setId(atomCounter++);
                        chainAtom.setId(atomCounter++);
                        chainCursor++;
                    } else {
                        branchAtom.setId(atomCounter++);
                    }
                } else {
                    branchAtom.setId(atomCounter++);
                }
            }
        }

        Atom atom = null;
        while((atom = getByCursor(newChains, chainCursor))!=null) {
            atom.setId(atomCounter++);
            chainCursor++;
        }

        if (newBranchAtomsMap.size() == 0) {
            throw new EmptyMolecule();
        }
        branchAtomsMap = newBranchAtomsMap;
        chains = newChains;
        lastBranchId = newBranchCounter;
        locked = false;
        return this;
    }

    private Atom getByCursor(Map<Integer, LinkedList<Atom>> branch, int cursor) {
        int counter = 0;
        for(Map.Entry<Integer, LinkedList<Atom>> entry:branch.entrySet()) {
            LinkedList<Atom> chain = entry.getValue();
            if(chain!=null) {
                for(int j = 0; j < chain.size(); j++) {
                    if(counter == cursor) {
                        return chain.get(j);
                    }
                    counter++;
                }
            }
        }
        return null;
    }

    private LinkedList<Atom> createNewBranch(LinkedList<Atom> branch) {
        LinkedList<Atom> newBranch = branch
                .stream()
                .filter(atom -> !"H".equals(atom.getElement()))
                .collect(Collectors.toCollection(LinkedList::new));

        newBranch.stream().forEach(atom -> {
            atom.removeAllHydrogens();
        });
        return newBranch;
    }


    public Molecule unlock2() {
        Map<Integer, LinkedList<Atom>> newBranchAtomsMap = new HashMap<>();
        Map<Integer, LinkedList<Atom>> newChains = new HashMap<>();

        int removed = 0;
        atomCounter = 1;
        int newBranchCounter = 0;
        for (int i = 1; i <= lastBranchId; i++) {
            LinkedList<Atom> branch = branchAtomsMap.get(i);
            LinkedList<Atom> newBranch = createNewBranch(branch);
            if (newBranch.size() != 0) {
                newBranchCounter++;
                newBranchAtomsMap.put(newBranchCounter, newBranch);
            }
        }

        //chains
        for (int i = 0; i <= lastBranchId; i++) {
            //re-calculate chains
            LinkedList<Atom> chain = chains.get(i);
            if (chain != null) {
                LinkedList<Atom> newChain = chain
                        .stream()
                        .filter(atom -> !"H".equals(atom.getElement()))
                        .collect(Collectors.toCollection(LinkedList::new));

                //re-calculate atom ids
                newChain.stream().forEach(atom -> {
                    atom.removeAllHydrogens();
                    atom.setId(atomCounter++);
                });
                if (newChain.size() != 0) {
                    newChains.put(newBranchCounter, newChain);
                }
            }
        }

        if (newBranchAtomsMap.size() == 0) {
            throw new EmptyMolecule();
        }
        branchAtomsMap = newBranchAtomsMap;
        chains = newChains;
        lastBranchId = newBranchCounter;
        locked = false;
        return this;
    }







    /*
    Related behaviors:
    Methods that involve building or doing modifications to the molecule object have to be chainable (ex: molec = Molecule("octane").brancher(8).closer()).
    Building a molecule consists in mutating the original object at each method call.
    An InvalidBond exception should be thrown each tiem you encounter a case where an atom exceed it's valence number or is bounded to itself (about the valence number, see additional information below).
    When a method throws an exception while it has several arguments/atoms to handle, the modifications resulting from the
    valid previous operations must be kept but all the arguments after the error are ignored.
    Special case with add_chaining: if an error occurs at any point during the building of the chain, all its atoms have
    to be removed from the molecule (even the valid ones).
    The whole molecule integrity should hold against any error, meaning that it has to be possible to correctly build the
    molecule object even after an exception has been thrown.
    The fields formula and molecular_weight (python) or the associated getters (java) should throw an UnlockedMolecule exception
    if access to them is attempted while the molecule isn't locked (because we do not want the user able to catch incomplete/invalid information).
    In the same manner, attempts of modification of a molecule object after it has been locked should throw a LockedMolecule
    exception (the closer method follows this behavior too).
    While unlocking a molecule, if by any (bad...) chance you end up with a molecule that does not have any branch left after
    unlocking, throw an EmptyMolecule exception.
    Once unlocked, the molecule has to be modifiable again, in any way.
     */

    @Override
    public String toString() {
        return toList().toString();
    }

    public List<String> toList() {
        return getAtoms().stream()
                .filter(at -> !at.element.equals("H"))
                .map(at -> at.toString())
                .collect(Collectors.toList());
    }

}
